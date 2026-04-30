// #Sireum
/*
 Copyright (c) 2017-2026,Robby, Kansas State University
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.sireum.cli

import org.sireum._
import org.sireum.message.Reporter
import org.sireum.presentasi.Presentation

object Presentasi {

  @datatype trait Media {
    def filename: String
    def duration: Z
    def timeline: Z
  }

  @datatype class Image(val filename: String, val timeline: Z) extends Media {
    @strictpure override def duration: Z = 0
  }

  @datatype class Sound(val filepath: Os.Path, val text: String, val duration: Z, val timeline: Z) extends Media {
    override def filename: String = {
      return filepath.name
    }
  }

  @datatype class Video(val filepath: Os.Path, val duration: Z, val timeline: Z, val start: F64, val end: F64,
                        val muted: B, val volume: F64, val rate: F64) extends Media {
    override def filename: String = {
      return filepath.name
    }
  }

  val INVALID_ARGS: Z = -2
  val INVALID_PATH: Z = -3
  val INVALID_SPEC: Z = -4
  val INVALID_RESOURCE: Z = -4
  val NO_FFMPEG: Z = -5
  val kind: String = "Presentasi"

  // ffprobe-based media-info helpers — replace prior JavaFX MediaPlayer
  // probes that lived in PresentasiJFX. ffprobe is a hard dep of the
  // offline `.cmd` assembly path, so no new tooling requirement.
  def getMediaDurationMillis(uri: String): Option[Z] = {
    val r = Os.proc(ISZ[String]("ffprobe", "-v", "error",
      "-show_entries", "format=duration",
      "-of", "default=noprint_wrappers=1:nokey=1",
      uriToPathString(uri))).run()
    if (!r.ok) {
      return None()
    }
    val s = ops.StringOps(r.out).trim
    R(s) match {
      case Some(seconds) =>
        // Match prior Math.ceil(seconds * 1000) behavior: ceil to whole
        // ms so timeline arithmetic never undershoots.
        val ms: R = seconds * conversions.Z.toR(z"1000")
        val msTrunc: Z = conversions.R.toZ(ms)
        val msCeil: Z = if (conversions.Z.toR(msTrunc) < ms) msTrunc + 1 else msTrunc
        return Some(msCeil)
      case _ => return None()
    }
  }

  def getSoundDuration(uri: String): Option[Z] = {
    return getMediaDurationMillis(uri)
  }

  def getVideoDuration(uri: String): Option[Z] = {
    return getMediaDurationMillis(uri)
  }

  def checkImage(uri: String): B = {
    return Os.proc(ISZ[String]("ffprobe", "-v", "error", uriToPathString(uri))).run().ok
  }

  // file:/// URIs come from Os.Path.toUri; ffprobe wants a plain path on
  // the filesystem (or a "file:" URL — but plain path avoids URL-decode
  // surprises with spaces).
  def uriToPathString(uri: String): String = {
    val s = ops.StringOps(uri)
    if (s.startsWith("file:")) {
      val rest = s.substring(5, uri.size)
      val r = ops.StringOps(rest)
      if (r.startsWith("///")) {
        return r.substring(2, rest.size)
      }
      return rest
    }
    return uri
  }

  def printHelp(help: String): Z = {
    println(help)
    println()
    println(
      st"""Available MaryTTS voices are: cmu-bdl-hsmm, cmu-rms-hsmm, cmu-slt-hsmm, dfki-obadiah-hsmm, dfki-prudence-hsmm, dfki-spike-hsmm
          |
          |For AWS, please refer to https://docs.aws.amazon.com/polly/latest/dg/voicelist.html
          |
          |For Azure, please refer to https://docs.microsoft.com/en-us/azure/cognitive-services/speech-service/rest-text-to-speech
          |
          |For ElevenLabs, please refer to https://elevenlabs.io/docs/api-reference/text-to-speech
          |
          |For Google Cloud, please refer to https://cloud.google.com/text-to-speech/docs/voices""".render)
    return 0
  }

  def gen(o: Cli.SireumPresentasiGenOption, reporter: Reporter): Z = {

    @pure def formatTime(millis: Z): String = {
      def format(n: Z, digits: Z): String = {
        var r: String = s"$n"
        for (_ <- 0 until (digits - r.size)) {
          r = s"0$r"
        }
        return r
      }
      val ms = millis % 1000
      val s = millis / 1000
      val m = s / 60
      val h = m / 60
      return s"${format(h, 2)}:${format(m % 60, 2)}:${format(s % 60, 2)}:${format(ms, 3)}"
    }

    @pure def localTemplate(timeline: Z, i: Z, prevTimelineOpt: Option[Z]): ST = {
      prevTimelineOpt match {
        case Some(prevTimeline) =>
          if (prevTimeline == timeline) {
            return st"""final long t_$i = t_${i - 1};"""
          } else {
            return st"""final long t_$i = t_${i - 1} + ${timeline - prevTimelineOpt.get}L; // $timeline (${formatTime(timeline)})"""
          }
        case _ =>
          return st"""final long t_$i = ${timeline}L; // $timeline (${formatTime(timeline)})"""
      }
    }

    @strictpure def soundTemplate(filepath:String, timeline: Z, i: Z, prevTimelineOpt: Option[Z], text: String): ST =
      st"""${localTemplate(timeline, i, prevTimelineOpt)}
          |medias.add(new Sound("/audio/$filepath", t_$i, "${ops.StringOps(text).escapeST}"));"""

    @strictpure def imageTemplate(filename: String, timeline: Z, i: Z, prevTimelineOpt: Option[Z], n: Z): ST =
      st"""${localTemplate(timeline, i, prevTimelineOpt)}
          |medias.add(new Image("/image/$filename", t_$i)); // #$n"""

    @strictpure def videoTemplate(filename: String, timeline: Z, muted: B, rate:F64, start: F64, end: F64, i: Z,
                                  prevTimelineOpt: Option[Z], n: Z): ST =
      st"""${localTemplate(timeline, i, prevTimelineOpt)}
          |medias.add(new Video("/video/$filename", t_$i, $muted, $rate, $start, $end));  // #$n"""

    @strictpure def presentasiTemplate(name: String, granularity: Z, vseekDelay: Z, textVolume: F64, end: Z, medias: ISZ[ST]): ST =
      st"""// Auto-generated by Sireum Presentasi
          |import javafx.application.Application;
          |import javafx.application.Platform;
          |import javafx.geometry.Rectangle2D;
          |import javafx.scene.Scene;
          |import javafx.scene.image.ImageView;
          |import javafx.scene.layout.StackPane;
          |import javafx.scene.media.MediaPlayer;
          |import javafx.scene.media.MediaView;
          |import javafx.stage.Screen;
          |import javafx.stage.Stage;
          |import javafx.util.Duration;
          |
          |import java.net.URL;
          |import java.util.HashMap;
          |import java.util.LinkedList;
          |import java.util.List;
          |
          |public class $name extends Application {
          |
          |    public final static long TIMELINE_GRANULARITY = $granularity;
          |    public final static long VSEEK_DELAY = $vseekDelay;
          |    public final static double TEXT_VOLUME = $textVolume;
          |
          |    public final static HashMap<String, javafx.scene.media.Media> mediaMap = new HashMap<String, javafx.scene.media.Media>();
          |
          |    public static javafx.scene.media.Media getJfxMedia(String uri) {
          |        javafx.scene.media.Media r = mediaMap.get(uri);
          |        if (r == null) {
          |           r = new javafx.scene.media.Media(uri);
          |           mediaMap.put(uri, r);
          |        }
          |        return r;
          |    }
          |
          |    public interface Media {
          |        String getUri();
          |        boolean isReady();
          |        boolean hasError();
          |        long getDurationMillis();
          |        long getTimeline();
          |    }
          |
          |    public final static class Image implements Media {
          |        public final String uri;
          |        public final ImageView imageView;
          |        private final long timeline;
          |
          |        public Image(final String path, final long timeline) {
          |            this.uri = getResourceUri(path);
          |            this.timeline = timeline;
          |            this.imageView = new ImageView(new javafx.scene.image.Image(uri));
          |        }
          |
          |        public String getUri() { return this.uri; }
          |
          |        public boolean isReady() {
          |            return true;
          |        }
          |
          |        public boolean hasError() {
          |            return false;
          |        }
          |
          |        public long getDurationMillis() {
          |            return 0L;
          |        }
          |
          |        public long getTimeline() {
          |            return this.timeline;
          |        }
          |    }
          |
          |    public final static class Sound implements Media {
          |        public final String uri;
          |        private final long timeline;
          |        private final String text;
          |        public MediaPlayer mediaPlayer;
          |        private boolean ready = false;
          |        private boolean error = false;
          |        private long duration = 0L;
          |
          |        public Sound(final String path, final long timeline, final String text) {
          |            this.uri = getResourceUri(path);
          |            this.timeline = timeline;
          |            this.text = text;
          |            try {
          |              this.mediaPlayer = new MediaPlayer(getJfxMedia(this.uri));
          |              this.mediaPlayer.setOnReady(() -> {
          |                  this.ready = true;
          |                  this.duration = (long) Math.ceil(mediaPlayer.getTotalDuration().toMillis());
          |              });
          |              this.mediaPlayer.setOnError(() -> this.error = true);
          |            } catch (Throwable e) {
          |              e.printStackTrace();
          |              System.err.println("Could not load: " + path);
          |              System.err.flush();
          |              System.exit(-1);
          |            }
          |        }
          |
          |        public String getUri() { return this.uri; }
          |
          |        public boolean isReady() {
          |            return this.ready;
          |        }
          |
          |        public boolean hasError() {
          |            return this.error;
          |        }
          |
          |        public long getDurationMillis() {
          |            return this.duration;
          |        }
          |
          |        public long getTimeline() {
          |            return this.timeline;
          |        }
          |
          |        public String getText() {
          |            return this.text;
          |        }
          |    }
          |
          |    public final static class Video implements Media {
          |        public final String uri;
          |        private final long timeline;
          |        public MediaView mediaView;
          |        public final boolean muted;
          |        public final double rate;
          |        public final double startMillis;
          |        public final double endMillis;
          |        private boolean ready;
          |        private boolean error;
          |        private long duration;
          |
          |        public Video(final String path, final long timeline, final boolean muted, final double rate, final double startMs, final double endMs) {
          |            this.uri = getResourceUri(path);
          |            this.timeline = timeline;
          |            this.rate = rate;
          |            this.startMillis = startMs;
          |            this.endMillis = endMs;
          |            this.muted = muted;
          |            try {
          |              final MediaPlayer mediaPlayer = new MediaPlayer(getJfxMedia(this.uri));
          |              mediaPlayer.setOnReady(() -> {
          |                  this.ready = true;
          |                  this.duration = (long) Math.ceil(mediaPlayer.getTotalDuration().toMillis());
          |              });
          |              mediaPlayer.setOnError(() -> this.error = true);
          |              this.mediaView = new MediaView(mediaPlayer);
          |            } catch (Throwable e) {
          |              e.printStackTrace();
          |              System.err.println("Could not load: " + path);
          |              System.err.flush();
          |              System.exit(-1);
          |            }
          |        }
          |
          |        public String getUri() { return this.uri; }
          |
          |        public boolean isReady() {
          |            return this.ready;
          |        }
          |
          |        public boolean hasError() { return this.error; }
          |
          |        public long getDurationMillis() {
          |            return this.duration;
          |        }
          |
          |        public long getTimeline() {
          |            return this.timeline;
          |        }
          |    }
          |
          |    private final LinkedList<Media> medias = new LinkedList<>();
          |    private Stage stage = null;
          |    private long startTime = 0;
          |    private int slideNo = 0;
          |    private boolean fullScreen = true;
          |    private double width = -1;
          |    private double height = -1;
          |
          |    public static String getResourceUri(final String path) {
          |        try {
          |            final URL url = Presentasi.class.getResource(path);
          |            if (url != null) {
          |                return url.toURI().toASCIIString();
          |            }
          |            throw new RuntimeException("Could not load " + path);
          |        } catch (final Throwable t){
          |            t.printStackTrace();
          |        }
          |        Platform.exit();
          |        return null;
          |    }
          |
          |    @Override
          |    public void init() {
          |        final List<String> args = getParameters().getRaw();
          |        if (args.size() > 0) {
          |            for (String arg : getParameters().getRaw()) {
          |                try {
          |                    int i = arg.indexOf('x');
          |                    if (i >= 0) {
          |                        width = Integer.parseInt(arg.substring(0, i));
          |                        height = Integer.parseInt(arg.substring(i + 1));
          |                        fullScreen = false;
          |                    } else if (arg.charAt(0) == '#') {
          |                        slideNo = Integer.parseInt(arg.substring(1));
          |                        startTime = 0;
          |                    } else {
          |                        startTime = Long.parseLong(arg);
          |                        slideNo = 0;
          |                    }
          |                } catch (Throwable t) {
          |                    System.err.println("Invalid argument " + arg);
          |                    System.err.flush();
          |                    Platform.exit();
          |                }
          |            }
          |        }
          |
          |        ${(medias, "\n")}
          |
          |        final long end = ${end}L; // ${formatTime(end)}
          |
          |        if (width == -1 || height == -1) {
          |            final Rectangle2D rect = Screen.getPrimary().getBounds();
          |            width = rect.getWidth();
          |            height = rect.getHeight();
          |        }
          |
          |        for (final Media media : medias) {
          |            final String uri = media.getUri();
          |            System.out.print("Loading " + uri + " ... ");
          |            System.out.flush();
          |            while (!media.isReady()) {
          |                if (media.hasError()) {
          |                    System.err.println("failed");
          |                    System.err.flush();
          |                    Platform.exit();
          |                }
          |                sleep(100);
          |            }
          |            System.out.println("done");
          |            System.out.flush();
          |        }
          |
          |        final Thread thread = new Thread(() -> {
          |            while (Presentasi.this.stage == null) Presentasi.sleep(100);
          |            final int size = medias.size();
          |            long start = System.currentTimeMillis();
          |            int i = 0;
          |            if (slideNo > 0) {
          |                int j = 0;
          |                while (i < size && j < slideNo) {
          |                    if (!(medias.get(i) instanceof Sound)) {
          |                        j = j + 1;
          |                    }
          |                    i++;
          |                }
          |                while (i < size && medias.get(i) instanceof Sound) i++;
          |                if (i < size) start = start - medias.get(i).getTimeline();
          |            } else if (startTime > 0) {
          |                while (i < size && medias.get(i).getTimeline() < startTime) {
          |                    i++;
          |                }
          |                while (i < size && medias.get(i) instanceof Sound) i++;
          |                if (i < size) start = start - medias.get(i).getTimeline();
          |            }
          |            while (i < size) {
          |                final Media media = medias.get(i);
          |                while (System.currentTimeMillis() - start <= media.getTimeline()) Presentasi.sleep(TIMELINE_GRANULARITY);
          |                if (media instanceof Sound) {
          |                    final Sound sound = ((Sound) media);
          |                    final MediaPlayer player = sound.mediaPlayer;
          |                    player.setVolume(TEXT_VOLUME);
          |                    player.setOnEndOfMedia(() -> player.dispose());
          |                    player.play();
          |                } else if (media instanceof Image) {
          |                    final Image graphic = (Image) media;
          |                    final StackPane root = new StackPane();
          |                    final ImageView imageView = graphic.imageView;
          |                    root.getChildren().add(imageView);
          |                    imageView.setPreserveRatio(true);
          |                    imageView.setSmooth(true);
          |                    imageView.setFitWidth(width);
          |                    imageView.setFitHeight(height);
          |                    stage.getScene().setRoot(root);
          |                    Platform.runLater(() -> stage.show());
          |                } else if (media instanceof Video) {
          |                    final Video video = (Video) media;
          |                    final StackPane root = new StackPane();
          |                    final MediaView mediaView = video.mediaView;
          |                    root.getChildren().add(mediaView);
          |                    mediaView.setPreserveRatio(true);
          |                    mediaView.setSmooth(true);
          |                    mediaView.setFitWidth(width);
          |                    mediaView.setFitHeight(height);
          |                    final MediaPlayer player = mediaView.getMediaPlayer();
          |                    player.setStartTime(Duration.millis(video.startMillis));
          |                    player.setRate(video.rate);
          |                    player.setOnEndOfMedia(() -> player.dispose());
          |                    if (video.endMillis > 0.0) player.setStopTime(Duration.millis(video.endMillis));
          |                    player.setVolume(1.0);
          |                    player.setMute(video.muted);
          |                    player.setOnEndOfMedia(() -> player.dispose());
          |                    Platform.runLater(() -> {
          |                        player.play();
          |                        if (video.startMillis > 0) {
          |                            Presentasi.sleep(VSEEK_DELAY);
          |                        }
          |                        stage.getScene().setRoot(root);
          |                        stage.show();
          |                    });
          |                }
          |                i++;
          |            }
          |            while (System.currentTimeMillis() - start <= end) Presentasi.sleep(TIMELINE_GRANULARITY);
          |            Platform.exit();
          |        });
          |        thread.setDaemon(true);
          |        thread.start();
          |    }
          |
          |    public static void sleep(final long millis) {
          |        try {
          |            Thread.sleep(millis);
          |        } catch (final Throwable t) {
          |            // skip
          |        }
          |    }
          |
          |    @Override
          |    public void start(final Stage primaryStage) {
          |        primaryStage.setFullScreenExitHint("");
          |        primaryStage.setFullScreen(fullScreen);
          |        primaryStage.setResizable(!fullScreen);
          |        final Scene scene = new Scene(new StackPane(), width, height);
          |        primaryStage.setScene(scene);
          |        this.stage = primaryStage;
          |    }
          |
          |}"""

    @strictpure def presentasiSwingTemplate(name: String, granularity: Z, vseekDelay: Z, textVolume: F64, end: Z, medias: ISZ[ST]): ST =
      st"""// Auto-generated by Sireum Presentasi (Swing + VLCJ preview).
          |// Parallel to ${name}.java; uses VLCJ for media playback so HEVC/AV1
          |// inputs work out of the box.  Requires libvlc on the host
          |// (e.g. `brew install --cask vlc` on macOS); the Maven coord
          |// `uk.co.caprica:vlcj` provides the Java bindings.
          |import java.awt.Color;
          |import java.awt.Graphics;
          |import java.awt.GraphicsDevice;
          |import java.awt.GraphicsEnvironment;
          |import java.awt.Rectangle;
          |import java.awt.image.BufferedImage;
          |import java.net.URL;
          |import java.util.LinkedList;
          |import javax.imageio.ImageIO;
          |import javax.swing.JComponent;
          |import javax.swing.JFrame;
          |import javax.swing.SwingUtilities;
          |
          |import uk.co.caprica.vlcj.player.base.MediaPlayer;
          |import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
          |import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;
          |import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;
          |import uk.co.caprica.vlcj.player.component.callback.FilledCallbackImagePainter;
          |
          |public class ${name} {
          |
          |    public final static long TIMELINE_GRANULARITY = $granularity;
          |    public final static long VSEEK_DELAY = $vseekDelay;
          |    public final static double TEXT_VOLUME = $textVolume;
          |
          |    public interface Media {
          |        String getUri();
          |        long getTimeline();
          |    }
          |
          |    public final static class Image implements Media {
          |        public final String uri;
          |        public final BufferedImage image;
          |        private final long timeline;
          |
          |        public Image(final String path, final long timeline) {
          |            this.uri = getResourceUri(path);
          |            this.timeline = timeline;
          |            BufferedImage img = null;
          |            try {
          |                img = ImageIO.read(java.net.URI.create(this.uri).toURL());
          |            } catch (final Throwable t) {
          |                t.printStackTrace();
          |                System.err.println("Could not load: " + path);
          |                System.err.flush();
          |                System.exit(-1);
          |            }
          |            this.image = img;
          |        }
          |
          |        public String getUri() { return this.uri; }
          |        public long getTimeline() { return this.timeline; }
          |    }
          |
          |    public final static class Sound implements Media {
          |        public final String uri;
          |        public final AudioPlayerComponent player;
          |        private final long timeline;
          |        private final String text;
          |
          |        public Sound(final String path, final long timeline, final String text) {
          |            this.uri = getResourceFilePath(path);
          |            this.timeline = timeline;
          |            this.text = text;
          |            this.player = new AudioPlayerComponent();
          |        }
          |
          |        public String getUri() { return this.uri; }
          |        public long getTimeline() { return this.timeline; }
          |        public String getText() { return this.text; }
          |    }
          |
          |    public final static class Video implements Media {
          |        public final String uri;
          |        public final CallbackMediaPlayerComponent component;
          |        public final boolean muted;
          |        public final double rate;
          |        public final double startMillis;
          |        public final double endMillis;
          |        private final long timeline;
          |
          |        public Video(final String path, final long timeline, final boolean muted, final double rate, final double startMs, final double endMs) {
          |            this.uri = getResourceFilePath(path);
          |            this.timeline = timeline;
          |            this.rate = rate;
          |            this.startMillis = startMs;
          |            this.endMillis = endMs;
          |            this.muted = muted;
          |            // FilledCallbackImagePainter scales the video to fill the
          |            // surface bounds.  The default (FixedCallbackImagePainter)
          |            // draws at natural size and centers, which leaves pillarbox
          |            // bars when the surface dimensions don't exactly match the
          |            // video buffer (e.g. when the JFrame's content pane is slightly
          |            // narrower than 1920 due to macOS window-border insets).
          |            this.component = new CallbackMediaPlayerComponent(
          |                null, null, null, true,
          |                new FilledCallbackImagePainter(),
          |                null, null, null);
          |        }
          |
          |        public String getUri() { return this.uri; }
          |        public long getTimeline() { return this.timeline; }
          |    }
          |
          |    public final static class ImagePanel extends JComponent {
          |        private final java.awt.Image image;
          |
          |        public ImagePanel(final java.awt.Image image) {
          |            this.image = image;
          |            setBackground(Color.BLACK);
          |            setOpaque(true);
          |        }
          |
          |        @Override
          |        protected void paintComponent(final Graphics g) {
          |            super.paintComponent(g);
          |            g.setColor(Color.BLACK);
          |            g.fillRect(0, 0, getWidth(), getHeight());
          |            if (image == null) return;
          |            final int w = getWidth();
          |            final int h = getHeight();
          |            final int iw = image.getWidth(this);
          |            final int ih = image.getHeight(this);
          |            if (iw <= 0 || ih <= 0) return;
          |            final double scale = Math.min((double) w / iw, (double) h / ih);
          |            final int dw = (int) (iw * scale);
          |            final int dh = (int) (ih * scale);
          |            final int dx = (w - dw) / 2;
          |            final int dy = (h - dh) / 2;
          |            g.drawImage(image, dx, dy, dw, dh, this);
          |        }
          |    }
          |
          |    private final LinkedList<Media> medias = new LinkedList<>();
          |    private JFrame frame = null;
          |    private long startTime = 0;
          |    private int slideNo = 0;
          |    private boolean fullScreen = true;
          |    private int width = -1;
          |    private int height = -1;
          |
          |    public static String getResourceUri(final String path) {
          |        try {
          |            final URL url = ${name}.class.getResource(path);
          |            if (url != null) {
          |                return url.toString();
          |            }
          |            throw new RuntimeException("Could not load " + path);
          |        } catch (final Throwable t) {
          |            t.printStackTrace();
          |        }
          |        System.exit(-1);
          |        return null;
          |    }
          |
          |    // VLCJ's MediaPlayer.play() expects a real filesystem path or a
          |    // protocol it understands — Java's class.getResource() URL is
          |    // either file:/... (proyek-run, classpath dir) or jar:file:...!/...
          |    // (packaged jar) and the latter VLC cannot open.  Resolve to an
          |    // absolute file path: file: URLs are converted directly; jar:
          |    // resources are extracted once to a stable temp file.
          |    public static String getResourceFilePath(final String path) {
          |        try {
          |            final URL url = ${name}.class.getResource(path);
          |            if (url == null) throw new RuntimeException("Could not load " + path);
          |            if ("file".equals(url.getProtocol())) {
          |                return new java.io.File(url.toURI()).getAbsolutePath();
          |            }
          |            // jar: or other protocol — extract to a stable temp file.
          |            final java.io.File tmp = new java.io.File(System.getProperty("java.io.tmpdir"),
          |                "presentasi-${name}" + path.replace('/', '_').replace('\\', '_'));
          |            if (!tmp.isFile() || tmp.length() == 0) {
          |                try (final java.io.InputStream in = ${name}.class.getResourceAsStream(path)) {
          |                    if (in == null) throw new RuntimeException("Could not load " + path);
          |                    java.nio.file.Files.copy(in, tmp.toPath(),
          |                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);
          |                }
          |                tmp.deleteOnExit();
          |            }
          |            return tmp.getAbsolutePath();
          |        } catch (final Throwable t) {
          |            t.printStackTrace();
          |        }
          |        System.exit(-1);
          |        return null;
          |    }
          |
          |    public static void sleep(final long millis) {
          |        try {
          |            Thread.sleep(millis);
          |        } catch (final Throwable t) {
          |            // skip
          |        }
          |    }
          |
          |    public void run(final String[] args) {
          |        for (final String arg : args) {
          |            try {
          |                final int x = arg.indexOf('x');
          |                if (x >= 0) {
          |                    width = Integer.parseInt(arg.substring(0, x));
          |                    height = Integer.parseInt(arg.substring(x + 1));
          |                    fullScreen = false;
          |                } else if (arg.charAt(0) == '#') {
          |                    slideNo = Integer.parseInt(arg.substring(1));
          |                    startTime = 0;
          |                } else {
          |                    startTime = Long.parseLong(arg);
          |                    slideNo = 0;
          |                }
          |            } catch (final Throwable t) {
          |                System.err.println("Invalid argument " + arg);
          |                System.err.flush();
          |                System.exit(-1);
          |            }
          |        }
          |
          |        ${(medias, "\n")}
          |
          |        final long endMs = ${end}L;
          |
          |        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
          |        final GraphicsDevice device = ge.getDefaultScreenDevice();
          |        if (width == -1 || height == -1) {
          |            final Rectangle bounds = device.getDefaultConfiguration().getBounds();
          |            width = bounds.width;
          |            height = bounds.height;
          |        }
          |
          |        final ${name} self = this;
          |        SwingUtilities.invokeLater(() -> {
          |            self.frame = new JFrame();
          |            self.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          |            self.frame.setSize(width, height);
          |            self.frame.getContentPane().setBackground(Color.BLACK);
          |            if (fullScreen) {
          |                // Maximised borderless instead of exclusive fullscreen
          |                // (device.setFullScreenWindow) — exclusive mode on
          |                // macOS captures all input and blocks Cmd-Tab.  This
          |                // looks identical but lets the user switch apps.
          |                self.frame.setUndecorated(true);
          |                final java.awt.Rectangle r = device.getDefaultConfiguration().getBounds();
          |                self.frame.setBounds(r);
          |                self.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
          |            } else {
          |                self.frame.setLocationRelativeTo(null);
          |            }
          |            self.frame.setVisible(true);
          |        });
          |
          |        final Thread thread = new Thread(() -> {
          |            while (self.frame == null) sleep(100);
          |            final int size = medias.size();
          |            long start = System.currentTimeMillis();
          |            int i = 0;
          |            if (slideNo > 0) {
          |                int j = 0;
          |                while (i < size && j < slideNo) {
          |                    if (!(medias.get(i) instanceof Sound)) j = j + 1;
          |                    i++;
          |                }
          |                while (i < size && medias.get(i) instanceof Sound) i++;
          |                if (i < size) start = start - medias.get(i).getTimeline();
          |            } else if (startTime > 0) {
          |                while (i < size && medias.get(i).getTimeline() < startTime) i++;
          |                while (i < size && medias.get(i) instanceof Sound) i++;
          |                if (i < size) start = start - medias.get(i).getTimeline();
          |            }
          |            while (i < size) {
          |                final Media media = medias.get(i);
          |                while (System.currentTimeMillis() - start <= media.getTimeline()) sleep(TIMELINE_GRANULARITY);
          |                if (media instanceof Sound) {
          |                    final Sound sound = (Sound) media;
          |                    final MediaPlayer player = sound.player.mediaPlayer();
          |                    player.audio().setVolume((int) Math.round(TEXT_VOLUME * 100));
          |                    player.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
          |                        @Override
          |                        public void finished(final MediaPlayer mp) {
          |                            SwingUtilities.invokeLater(() -> sound.player.release());
          |                        }
          |                    });
          |                    player.media().play(sound.uri);
          |                } else if (media instanceof Image) {
          |                    final Image graphic = (Image) media;
          |                    SwingUtilities.invokeLater(() -> {
          |                        final ImagePanel panel = new ImagePanel(graphic.image);
          |                        self.frame.setContentPane(panel);
          |                        self.frame.revalidate();
          |                        self.frame.repaint();
          |                    });
          |                } else if (media instanceof Video) {
          |                    final Video video = (Video) media;
          |                    final MediaPlayer player = video.component.mediaPlayer();
          |                    player.controls().setRate((float) video.rate);
          |                    player.audio().setMute(video.muted);
          |                    player.audio().setVolume(100);
          |                    player.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
          |                        @Override
          |                        public void timeChanged(final MediaPlayer mp, final long newTime) {
          |                            if (video.endMillis > 0 && newTime >= video.endMillis) {
          |                                mp.controls().stop();
          |                            }
          |                        }
          |                        @Override
          |                        public void finished(final MediaPlayer mp) {
          |                            SwingUtilities.invokeLater(() -> video.component.release());
          |                        }
          |                    });
          |                    SwingUtilities.invokeLater(() -> {
          |                        self.frame.setContentPane(video.component);
          |                        self.frame.revalidate();
          |                        self.frame.repaint();
          |                        player.media().play(video.uri);
          |                        if (video.startMillis > 0) {
          |                            sleep(VSEEK_DELAY);
          |                            player.controls().setTime((long) video.startMillis);
          |                        }
          |                    });
          |                }
          |                i++;
          |            }
          |            while (System.currentTimeMillis() - start <= endMs) sleep(TIMELINE_GRANULARITY);
          |            System.exit(0);
          |        });
          |        thread.setDaemon(false);
          |        thread.start();
          |    }
          |
          |    public static void main(final String[] args) {
          |        final ${name} app = new ${name}();
          |        app.run(args);
          |    }
          |}"""

    @strictpure def imgSegTemplate(timeline: Z, name: String): ST =
      st"""ImgSeg($timeline, "${ops.StringOps(name).escapeST}")"""

    @strictpure def vidSegTemplate(timeline: Z, dur: Z, start: F64, end: F64, muted: B, rate: F64, name: String): ST =
      st"""VidSeg($timeline, $dur, ${start}d, ${end}d, $muted, ${rate}d, "${ops.StringOps(name).escapeST}")"""

    @strictpure def sndSegTemplate(timeline: Z, dur: Z, name: String): ST =
      st"""SndSeg($timeline, $dur, "${ops.StringOps(name).escapeST}")"""

    @strictpure def assembleCmdTemplate(name: String, end: Z, videoWidth: Z, videoHeight: Z, videoFps: Z,
                                        audioServiceName: String, audioVoiceSeg: ST, segs: ISZ[ST]): ST =
      st"""::/*#! 2> /dev/null                                   #
          |@ 2>/dev/null # 2>nul & echo off & goto BOF           #
          |if [ -z "$${SIREUM_HOME}" ]; then                      #
          |  echo "Please set SIREUM_HOME env var"               #
          |  exit -1                                             #
          |fi                                                    #
          |exec "$${SIREUM_HOME}/bin/sireum" slang run "$$0" "$$@"  #
          |:BOF
          |setlocal
          |if not defined SIREUM_HOME (
          |  echo Please set SIREUM_HOME env var
          |  exit /B -1
          |)
          |"%SIREUM_HOME%\bin\sireum.bat" slang run %0 %*
          |exit /B %errorlevel%
          |::!#*/
          |// #Sireum
          |// Auto-generated by Sireum Presentasi.
          |// Offline assembly of $name.wav, $name.mp4, and $name-srt.mp4 from the
          |// generation-time media timing. Complements $name.java (live recording)
          |// and $name.srt/$name.vtt (subtitles). Run via `sireum slang run` or
          |// execute directly if the file is marked executable.
          |import org.sireum._
          |
          |@datatype trait Seg { def timeline: Z }
          |@datatype class ImgSeg(val timeline: Z, val name: String) extends Seg
          |@datatype class VidSeg(val timeline: Z, val dur: Z, val startMs: F64, val endMs: F64,
          |                       val muted: B, val rate: F64, val name: String) extends Seg
          |@datatype class SndSeg(val timeline: Z, val dur: Z, val name: String) extends Seg
          |
          |val segs: ISZ[Seg] = ISZ(
          |  ${(segs, ",\n")}
          |)
          |val totalMs: Z = $end
          |val videoWidth: Z = $videoWidth
          |val videoHeight: Z = $videoHeight
          |val videoFps: Z = $videoFps
          |
          |val path = Os.slashDir.up.up.up.up
          |val imageDir = path / "jvm" / "src" / "main" / "resources" / "image"
          |val videoDir = path / "jvm" / "src" / "main" / "resources" / "video"
          |val audioDir = path / "jvm" / "src" / "main" / "resources" / "audio" / "$audioServiceName"$audioVoiceSeg
          |
          |val outDir = path / "out" / "presentasi" / "$name"
          |outDir.mkdirAll()
          |val tempDir = outDir / "temp"
          |tempDir.removeAll()
          |tempDir.mkdirAll()
          |
          |val wav = outDir / "$name.wav"
          |val mp4 = outDir / "$name.mp4"
          |val srt = outDir / "$name.srt"
          |val vtt = outDir / "$name.vtt"
          |val mp4Subtitled = outDir / "$name-srt.mp4"
          |val vcodec: String = if (Os.isMac) "hevc_videotoolbox" else "libx265"
          |// Encoding preset selected via the first CLI arg (default = balanced
          |// direct-download; "youtube" = high-bitrate source for YouTube to
          |// re-encode from; "small" = tighter for lightweight self-hosting).
          |// hevc_videotoolbox needs explicit b:v + maxrate + bufsize to actually
          |// cap output; libx265 honours CRF directly.
          |val preset: String = if (Os.cliArgs.size > 0) Os.cliArgs(0) else "default"
          |println(s"Encoding preset: $$preset")
          |val vcodecArgs: ISZ[String] = if (preset == "youtube") {
          |  if (Os.isMac) {
          |    ISZ[String]("-b:v", "25M", "-maxrate", "30M", "-bufsize", "60M")
          |  } else {
          |    ISZ[String]("-crf", "21", "-preset", "medium")
          |  }
          |} else if (preset == "small") {
          |  if (Os.isMac) {
          |    ISZ[String]("-b:v", "5M", "-maxrate", "6M", "-bufsize", "12M")
          |  } else {
          |    ISZ[String]("-crf", "27", "-preset", "medium")
          |  }
          |} else {
          |  if (Os.isMac) {
          |    ISZ[String]("-b:v", "8M", "-maxrate", "10M", "-bufsize", "20M")
          |  } else {
          |    ISZ[String]("-crf", "25", "-preset", "medium")
          |  }
          |}
          |
          |def ffSilence(out: Os.Path, ms: Z): Unit = {
          |  Os.proc(ISZ("ffmpeg", "-y", "-f", "lavfi", "-fflags", "+genpts",
          |    "-i", "anullsrc=channel_layout=mono:sample_rate=8000",
          |    "-t", s"$${ms}ms", "-c:a", "pcm_s16le", "-ar", "44100", "-ac", "2",
          |    out.string)).runCheck()
          |}
          |def ffAudio(in: Os.Path, out: Os.Path): Unit = {
          |  Os.proc(ISZ("ffmpeg", "-y", "-i", in.string,
          |    "-c:a", "pcm_s16le", "-ar", "44100", "-ac", "2", out.string)).runCheck()
          |}
          |def ffVideoAudio(in: Os.Path, out: Os.Path, startMs: F64, endMs: F64): Unit = {
          |  val t: ISZ[String] = if (endMs > 0d) ISZ[String]("-t", s"$${endMs - startMs}ms") else ISZ[String]()
          |  Os.proc(ISZ[String]("ffmpeg", "-y", "-ss", s"$${startMs}ms") ++ t ++
          |    ISZ[String]("-i", in.string, "-c:a", "pcm_s16le", "-ar", "44100", "-ac", "2", out.string)).runCheck()
          |}
          |def ffConcatA(filelist: Os.Path, out: Os.Path): Unit = {
          |  Os.proc(ISZ("ffmpeg", "-y", "-f", "concat", "-safe", "0", "-i", filelist.string,
          |    "-c:a", "copy", out.string)).runCheck()
          |}
          |val vf: String = s"scale=$$videoWidth:$$videoHeight:force_original_aspect_ratio=decrease,pad=$$videoWidth:$$videoHeight:-1:-1:color=black,fps=$$videoFps"
          |def ffImageClip(img: Os.Path, out: Os.Path, ms: Z): Unit = {
          |  Os.proc(ISZ[String]("ffmpeg", "-y", "-loop", "1", "-framerate", videoFps.string,
          |    "-i", img.string, "-t", s"$${ms}ms", "-vf", vf,
          |    "-c:v", vcodec) ++ vcodecArgs ++ ISZ[String]("-pix_fmt", "yuv420p", "-g", "30", "-an",
          |    "-f", "mpegts", out.string)).runCheck()
          |}
          |def ffVideoClip(in: Os.Path, out: Os.Path, startMs: F64, endMs: F64, rate: F64, dur: Z, displayMs: Z): Unit = {
          |  // When endMs <= 0 we play to end-of-file; use bake-in dur (ffprobe-measured at gen time)
          |  // as the natural source duration so padding math works out correctly.  Earlier versions
          |  // computed srcDur as 0 in this case which made tpad freeze the last frame for displayMs,
          |  // doubling the demo segment in the assembled video.
          |  val startMsZ: Z = conversions.R.toZ(conversions.F64.toR(startMs))
          |  val srcDurMs: Z = if (endMs > 0d) conversions.R.toZ(conversions.F64.toR(endMs)) - startMsZ
          |                    else dur - startMsZ
          |  val playedMs: Z = if (rate != 1d && rate > 0d)
          |                      srcDurMs * 1000000 / conversions.R.toZ(conversions.F64.toR(rate * 1000000d))
          |                    else srcDurMs
          |  val padMs: Z = if (displayMs > playedMs) displayMs - playedMs else 0
          |  val rateFilter: String = if (rate != 1d) s",setpts=PTS/$$rate" else ""
          |  val padFilter: String = if (padMs > 0) s",tpad=stop_mode=clone:stop_duration=$${padMs}ms" else ""
          |  val filter = s"$$vf$$rateFilter$$padFilter"
          |  Os.proc(ISZ[String]("ffmpeg", "-y", "-ss", s"$${startMs}ms",
          |    "-t", s"$${srcDurMs}ms",
          |    "-i", in.string, "-vf", filter,
          |    "-c:v", vcodec) ++ vcodecArgs ++ ISZ[String]("-pix_fmt", "yuv420p", "-g", "30", "-an",
          |    "-f", "mpegts", out.string)).runCheck()
          |}
          |
          |// === Build .wav: sounds + extracted video audio + silence gaps ===
          |println(s"Generating $$wav ...")
          |var audioParts = ISZ[Os.Path]()
          |var silN: Z = 0
          |var prevEnd: Z = 0
          |for (seg <- segs) {
          |  seg match {
          |    case s: SndSeg =>
          |      if (prevEnd < s.timeline) {
          |        val silence = tempDir / s"silence-$$silN.wav"
          |        silN = silN + 1
          |        audioParts = audioParts :+ silence
          |        ffSilence(silence, s.timeline - prevEnd)
          |      }
          |      val part = tempDir / s"audio-$${audioParts.size}.wav"
          |      audioParts = audioParts :+ part
          |      ffAudio(audioDir / s.name, part)
          |      prevEnd = s.timeline + s.dur
          |    case v: VidSeg =>
          |      if (!v.muted) {
          |        if (prevEnd < v.timeline) {
          |          val silence = tempDir / s"silence-$$silN.wav"
          |          silN = silN + 1
          |          audioParts = audioParts :+ silence
          |          ffSilence(silence, v.timeline - prevEnd)
          |        }
          |        val part = tempDir / s"audio-$${audioParts.size}.wav"
          |        audioParts = audioParts :+ part
          |        ffVideoAudio(videoDir / v.name, part, v.startMs, v.endMs)
          |        prevEnd = v.timeline + v.dur
          |      }
          |    case _: ImgSeg =>
          |  }
          |}
          |if (prevEnd < totalMs) {
          |  val silence = tempDir / s"silence-$$silN.wav"
          |  audioParts = audioParts :+ silence
          |  ffSilence(silence, totalMs - prevEnd)
          |}
          |val audioList = tempDir / "audio-list.txt"
          |audioList.writeOver(st"$${(for (p <- audioParts) yield st"file '$$p'", "\n")}".render)
          |ffConcatA(audioList, wav)
          |println()
          |
          |// === Render visual segments as HEVC .ts, then concat + mux .wav into .mp4 ===
          |println(s"Generating $$mp4 ...")
          |var visuals = ISZ[Seg]()
          |for (seg <- segs) {
          |  seg match {
          |    case _: ImgSeg => visuals = visuals :+ seg
          |    case _: VidSeg => visuals = visuals :+ seg
          |    case _ =>
          |  }
          |}
          |var videoParts = ISZ[Os.Path]()
          |for (i <- visuals.indices) {
          |  val v = visuals(i)
          |  val displayMs: Z = if (i + 1 < visuals.size) visuals(i + 1).timeline - v.timeline else totalMs - v.timeline
          |  val part = tempDir / s"visual-$$i.ts"
          |  videoParts = videoParts :+ part
          |  v match {
          |    case img: ImgSeg => ffImageClip(imageDir / img.name, part, displayMs)
          |    case vid: VidSeg => ffVideoClip(videoDir / vid.name, part, vid.startMs, vid.endMs, vid.rate, vid.dur, displayMs)
          |    case _ =>
          |  }
          |}
          |val videoList = tempDir / "video-list.txt"
          |videoList.writeOver(st"$${(for (p <- videoParts) yield st"file '$$p'", "\n")}".render)
          |Os.proc(ISZ("ffmpeg", "-y", "-f", "concat", "-safe", "0", "-i", videoList.string,
          |  "-i", wav.string,
          |  "-c:v", "copy", "-tag:v", "hvc1", "-c:a", "aac", "-q:a", "2", "-b:a", "192k",
          |  "-movflags", "+faststart", mp4.string)).console.runCheck()
          |println()
          |
          |// === Merge embedded video subtitles with the master narration SRT ===
          |//
          |// The master Presentasi.srt covers slide-by-slide narration only.  Any
          |// VidSeg whose source mp4 ships with an embedded subtitle track (e.g. a
          |// recorded demo with mov_text captions) gets its entries extracted, time-
          |// shifted to the master timeline, and appended.  Result: Presentasi-srt.mp4
          |// has subtitles end-to-end including during demo segments.
          |def srtPad2(n: Z): String = {
          |  if (n < 10) {
          |    return s"0$$n"
          |  } else {
          |    return s"$$n"
          |  }
          |}
          |def srtPad3(n: Z): String = {
          |  if (n < 10) {
          |    return s"00$$n"
          |  } else if (n < 100) {
          |    return s"0$$n"
          |  } else {
          |    return s"$$n"
          |  }
          |}
          |def srtFormatTs(ms: Z): String = {
          |  val total: Z = if (ms < 0) z"0" else ms
          |  val h: Z = total / 3600000
          |  val rem1: Z = total % 3600000
          |  val m: Z = rem1 / 60000
          |  val rem2: Z = rem1 % 60000
          |  val sec: Z = rem2 / 1000
          |  val msPart: Z = rem2 % 1000
          |  return s"$${srtPad2(h)}:$${srtPad2(m)}:$${srtPad2(sec)},$${srtPad3(msPart)}"
          |}
          |def srtParseTs(raw: String): Z = {
          |  val sOps = ops.StringOps(raw)
          |  val trimmed = sOps.trim
          |  val parts: ISZ[String] = ops.StringOps(trimmed).split((c: C) => c == ':')
          |  if (parts.size < 3) {
          |    return z"0"
          |  }
          |  val h: Z = Z(parts(0)).getOrElse(z"0")
          |  val m: Z = Z(parts(1)).getOrElse(z"0")
          |  // Tolerate either ',' or '.' as the seconds/ms separator.
          |  val secParts: ISZ[String] = ops.StringOps(parts(2)).split(
          |    (c: C) => c == '.' || c == ',')
          |  val sec: Z = Z(secParts(0)).getOrElse(z"0")
          |  val ms: Z = if (secParts.size > 1) Z(secParts(1)).getOrElse(z"0") else z"0"
          |  return h * 3600000 + m * 60000 + sec * 1000 + ms
          |}
          |def srtParse(content: String): ISZ[(Z, Z, String)] = {
          |  // Normalize line endings, then split into blocks separated by blank lines
          |  // by replacing the "\\n\\n" separator with a single sentinel char and
          |  // splitting on that.  StringOps.split takes a (C => B) predicate so we
          |  // can't split on a multi-char delimiter directly.
          |  val n1 = ops.StringOps(content).replaceAllLiterally("\r\n", "\n")
          |  val n2 = ops.StringOps(n1).replaceAllLiterally("\r", "\n")
          |  // StringOps.split takes a single-char predicate; replace the multi-char
          |  // blank-line separator with a unique control char so we can split on it.
          |  val n3 = ops.StringOps(n2).replaceAllLiterally("\n\n", "")
          |  val blocks: ISZ[String] = ops.StringOps(n3).split((c: C) => c == '')
          |  var entries = ISZ[(Z, Z, String)]()
          |  for (blk <- blocks) {
          |    val rawLines: ISZ[String] = ops.StringOps(blk).split((c: C) => c == '\n')
          |    val lines: ISZ[String] =
          |      for (rl <- rawLines if ops.StringOps(rl).trim.size > 0) yield rl
          |    if (lines.size >= 2) {
          |      // Locate the timestamp line (first line containing " --> ")
          |      var arrowIdx: Z = -1
          |      for (i <- 0 until lines.size) {
          |        if (arrowIdx < 0 && ops.StringOps(lines(i)).stringIndexOf(" --> ") >= 0) {
          |          arrowIdx = i
          |        }
          |      }
          |      if (arrowIdx >= 0 && arrowIdx + 1 < lines.size) {
          |        val ts = lines(arrowIdx)
          |        val arrowPos: Z = ops.StringOps(ts).stringIndexOf(" --> ")
          |        if (arrowPos > 0) {
          |          val startMs: Z = srtParseTs(ops.StringOps(ts).substring(0, arrowPos))
          |          val endMs: Z = srtParseTs(
          |            ops.StringOps(ts).substring(arrowPos + 5, ts.size))
          |          val bodyLines: ISZ[String] =
          |            for (i <- arrowIdx + 1 until lines.size) yield lines(i)
          |          val body = st"$${(bodyLines, "\n")}".render
          |          entries = entries :+ ((startMs, endMs, body))
          |        }
          |      }
          |    }
          |  }
          |  return entries
          |}
          |def srtEmit(entries: ISZ[(Z, Z, String)]): String = {
          |  // Insertion-sort by start time (entries are short, performance fine).
          |  var sorted = ISZ[(Z, Z, String)]()
          |  for (e <- entries) {
          |    var insertAt: Z = sorted.size
          |    var i: Z = 0
          |    while (i < sorted.size && insertAt == sorted.size) {
          |      if (sorted(i)._1 > e._1) {
          |        insertAt = i
          |      }
          |      i = i + 1
          |    }
          |    if (insertAt == sorted.size) {
          |      sorted = sorted :+ e
          |    } else {
          |      val before: ISZ[(Z, Z, String)] = ops.ISZOps(sorted).slice(0, insertAt)
          |      val after: ISZ[(Z, Z, String)] = ops.ISZOps(sorted).slice(insertAt, sorted.size)
          |      sorted = before :+ e
          |      for (a <- after) {
          |        sorted = sorted :+ a
          |      }
          |    }
          |  }
          |  val blocks: ISZ[String] =
          |    for (i <- 0 until sorted.size) yield srtBlock(i + 1, sorted(i))
          |  return st"$${(blocks, "\n")}".render
          |}
          |@strictpure def srtBlock(idx: Z, e: (Z, Z, String)): String =
          |  s"$$idx\n$${srtFormatTs(e._1)} --> $${srtFormatTs(e._2)}\n$${e._3}\n"
          |def extractVideoSubs(videoPath: Os.Path, segStartMs: F64, segEndMs: F64,
          |                     rate: F64, masterTimeline: Z): ISZ[(Z, Z, String)] = {
          |  val tempSrt = tempDir / s"sub-$${videoPath.name}.srt"
          |  if (tempSrt.exists) {
          |    tempSrt.removeAll()
          |  }
          |  val r = Os.proc(ISZ[String]("ffmpeg", "-y", "-loglevel", "error", "-i", videoPath.string,
          |    "-map", "0:s:0", "-c:s", "srt", tempSrt.string)).run()
          |  if (!r.ok || !tempSrt.exists) {
          |    return ISZ[(Z, Z, String)]()
          |  }
          |  val raw = srtParse(tempSrt.read)
          |  val startMsInt: Z = conversions.R.toZ(conversions.F64.toR(segStartMs))
          |  val endMsInt: Z = if (segEndMs > 0d) conversions.R.toZ(conversions.F64.toR(segEndMs)) else z"-1"
          |  val effRate: F64 = if (rate <= 0d) 1d else rate
          |  var shifted = ISZ[(Z, Z, String)]()
          |  for (e <- raw) {
          |    val s = e._1
          |    val en = e._2
          |    val inRange: B = s >= startMsInt && (endMsInt < z"0" || en <= endMsInt)
          |    if (inRange) {
          |      val sShift: Z = shiftMs(s, startMsInt, masterTimeline, effRate)
          |      val eShift: Z = shiftMs(en, startMsInt, masterTimeline, effRate)
          |      shifted = shifted :+ ((sShift, eShift, e._3))
          |    }
          |  }
          |  return shifted
          |}
          |// F64 → Z (truncated) via the R intermediary; R has toZ but no toF64,
          |// hence the asymmetry in the conversion API.
          |@strictpure def f64ToZ(d: F64): Z = conversions.R.toZ(conversions.F64.toR(d))
          |// Map a source-relative timestamp into the master timeline, accounting
          |// for clip start offset and playback rate.  Math is done in Z by scaling
          |// by 1_000_000 so we don't lose fractional rate values like 0.5 or 1.5.
          |@strictpure def shiftMs(srcMs: Z, segStartMs: Z, masterTimeline: Z, rate: F64): Z =
          |  masterTimeline + (srcMs - segStartMs) * 1000000 / f64ToZ(rate * 1000000d)
          |
          |val srtMerged = outDir / "$name-merged.srt"
          |val srtForMux: Os.Path = if (srt.exists) {
          |  var entries = srtParse(srt.read)
          |  for (seg <- segs) {
          |    seg match {
          |      case v: VidSeg =>
          |        val videoPath = videoDir / v.name
          |        if (videoPath.exists) {
          |          val vidSubs = extractVideoSubs(videoPath, v.startMs, v.endMs, v.rate, v.timeline)
          |          if (vidSubs.size > 0) {
          |            println(s"  + $${vidSubs.size} subtitle entries from $${v.name}")
          |            entries = entries ++ vidSubs
          |          }
          |        }
          |      case _ =>
          |    }
          |  }
          |  srtMerged.writeOver(srtEmit(entries))
          |  println(s"Wrote merged $$srtMerged ($${entries.size} entries)")
          |  srtMerged
          |} else {
          |  srt
          |}
          |
          |// === Mux subtitles into .mp4 ===
          |if (srtForMux.exists) {
          |  println(s"Generating $$mp4Subtitled ...")
          |  Os.proc(ISZ("ffmpeg", "-y", "-i", mp4.string, "-i", srtForMux.string,
          |    "-c", "copy", "-c:a", "copy", "-c:s", "mov_text",
          |    "-metadata:s:s:0", "language=eng", mp4Subtitled.string)).runCheck()
          |  println()
          |} else {
          |  println(s"No .srt found; skipping subtitled mp4")
          |}"""

    val path: Os.Path = o.args match {
      case ISZ() => return printHelp(o.help)
      case ISZ(p, _*) => Os.path(p).canon
    }

    val hasFFmpeg = proc"ffmpeg -h".run().ok
    if (o.slides && !hasFFmpeg) {
      reporter.error(None(), kind, "FFmpeg is required when using --slides")
    }
//    if (o.record && !hasFFmpeg) {
//      reporter.error(None(), kind, "FFmpeg is required when using --record")
//    }
    if (reporter.hasError) {
      reporter.printMessages()
      return NO_FFMPEG
    }

    val presentasi = path / "bin" / "presentasi.cmd"
    val voiceArg: String = o.voice match {
      case Some(v) => v
      case _ => o.service match {
        case Cli.SireumPresentasiGenService.Mary => "dfki-spike-hsmm"
        case Cli.SireumPresentasiGenService.Aws => "Amy"
        case Cli.SireumPresentasiGenService.Azure => "en-GB-RyanNeural"
        case Cli.SireumPresentasiGenService.Elevenlabs => "Rachel"
        case Cli.SireumPresentasiGenService.Google => "en-GB-Chirp3-HD-Umbriel"
      }
    }
    val args = ISZ(presentasi.string, o.service.string, voiceArg) ++ ops.ISZOps(o.args).drop(1)
    val specs: ISZ[Presentation] = if (presentasi.exists) {
      val outTemp = Os.temp()
      val r = SlangRunner.run(Cli.SireumSlangRunOption("", args, F, None(), Some(outTemp.string), F, F))
      if (r != 0) {
        eprintln(outTemp.read)
        return INVALID_SPEC
      }

      org.sireum.presentasi.JSON.toPresentation(outTemp.read) match {
        case Either.Left(obj) => ISZ(obj)
        case _ =>
          eprintln(s"Failed to process $path")
          return INVALID_SPEC
      }
    } else {
      Presentasi.Ext.parseMarkdowns(ops.ISZOps(args).drop(1), path, reporter)
    }
    if (reporter.hasIssue) {
      reporter.printMessages()
      return INVALID_SPEC
    }

    val resources = path / "jvm" / "src" / "main" / "resources"
    val source = path / "jvm" / "src" / "main" / "java"
    val image = resources / "image"
    val video = resources / "video"
    image.mkdirAll()
    video.mkdirAll()

    val audioDir = (resources / "audio").canon
    var audio = audioDir
    val service: Cli.SireumPresentasiText2speechService.Type = o.service match {
      case Cli.SireumPresentasiGenService.Azure =>
        audio = audio / "azure"
        Cli.SireumPresentasiText2speechService.Azure
      case Cli.SireumPresentasiGenService.Aws =>
        audio = audio / "aws"
        Cli.SireumPresentasiText2speechService.Aws
      case Cli.SireumPresentasiGenService.Elevenlabs =>
        audio = audio / "elevenlabs"
        Cli.SireumPresentasiText2speechService.Elevenlabs
      case Cli.SireumPresentasiGenService.Google =>
        audio = audio / "google"
        Cli.SireumPresentasiText2speechService.Google
      case Cli.SireumPresentasiGenService.Mary =>
        audio = audio / "marytts"
        Cli.SireumPresentasiText2speechService.Mary
    }
    o.voice match {
      case Some(voice) => audio = audio / voice
      case _ =>
    }
    audio.mkdirAll()

    @pure def isGeneratedAudio(p: Os.Path): B = {
      if (p.ext == "wav" || p.ext == "mp3") {
        val cis = conversions.String.toCis(p.name)
        if (cis.size > 7 && cis(6) == '-' && ops.ISZOps(ops.ISZOps(cis).take(6)).
          forall((c: C) => 'A' <= c && c <= 'Z' || '0' <= c && c <= '9') ||
          cis.size > 11 && cis(cis.size - 11) == '-' && ops.ISZOps(ops.ISZOps(ops.ISZOps(cis).drop(cis.size - 10)).dropRight(4)).
            forall((c: C) => 'A' <= c && c <= 'Z' || '0' <= c && c <= '9')) {
          return T
        }
      }
      return F
    }

    var generatedAudioFiles = HashSSet.empty[Os.Path]
    for (p <- audio.list if isGeneratedAudio(p)) {
      generatedAudioFiles = generatedAudioFiles + p
      if (o.force) {
        p.removeAll()
      }
    }

    val (format, ext): (Cli.SireumPresentasiText2speechOutputFormat.Type, String) = o.outputFormat match {
      case Cli.SireumPresentasiGenOutputFormat.Mp3 if o.service != Cli.SireumPresentasiGenService.Mary =>
        (Cli.SireumPresentasiText2speechOutputFormat.Mp3, "mp3")
      case _ => (Cli.SireumPresentasiText2speechOutputFormat.Wav, "wav")
    }

    for (spc <- specs) {
      var spec = spc
      if (o.slice.nonEmpty) {
        var entries = ISZ[org.sireum.presentasi.Presentation.Entry]()
        var indices = HashSet.empty[Z]
        for (i <- o.slice) {
          Z(ops.StringOps(i).trim) match {
            case Some(n) => indices = indices + n
            case _ =>
              reporter.error(None(), kind, s"Invalid slice argument: $i")
              reporter.printMessages()
              return INVALID_ARGS
          }
        }
        for (i <- spec.entries.indices if indices.contains(i)) {
          entries = entries :+ spec.entries(i)
        }
        spec = spec(entries = entries)
      }

      def processText(text: String, start: Z): (ISZ[Media], Z) = {
        def fingerprint(t: String): String = {
          val c = crypto.SHA3.init256
          c.update(conversions.String.toU8is(t))
          return st"${(ops.ISZOps(c.finalise()).take(3), "")}".render
        }

        def process(sound: Sound): Sound = {
          var sub = conversions.String.fromCis(
            for (c <- conversions.String.toCis(
              if (sound.text.size > 15) ops.StringOps(sound.text).substring(0, 15) else sound.text)) yield
              if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')) c else '_')
          var filename = s"${fingerprint(sound.text)}-$sub.$ext"
          var p = audio / filename
          if (!p.exists) {
            sub = conversions.String.fromCis(
              for (c <- conversions.String.toCis(
                if (sound.text.size > 32) ops.StringOps(sound.text).substring(0, 32) else sound.text)) yield
                if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')) c else '_')
            filename = s"$sub-${fingerprint(sound.text)}.$ext"
            p = audio / filename
          }
          val temp = Os.tempFix("", ".txt")
          temp.writeOver(sound.text)
          val engine: Cli.SireumPresentasiText2speechEngine.Type = o.engine match {
            case Cli.SireumPresentasiGenEngine.Neural => Cli.SireumPresentasiText2speechEngine.Neural
            case Cli.SireumPresentasiGenEngine.Standard => Cli.SireumPresentasiText2speechEngine.Standard
          }
          val code = text2speech(Cli.SireumPresentasiText2speechOption(
            help = "",
            args = ISZ(temp.string),
            force = F,
            output = Some(p.string),
            service = service,
            voice = o.voice,
            awsPath = o.awsPath,
            gender = o.gender,
            key = o.key,
            lang = o.lang,
            outputFormat = format,
            region = o.region,
            voiceLang = o.voiceLang,
            engine = engine,
            elevenLabsKey = o.elevenLabsKey,
            elevenLabsModel = o.elevenLabsModel,
            googleKey = o.googleKey
          ))
          temp.removeAll()
          println(s"Loading $p ...")
          val durOpt = getSoundDuration(p.toUri)
          println()
          generatedAudioFiles = generatedAudioFiles - p
          durOpt match {
            case Some(dur) if code == 0 => return sound(filepath = p, duration = dur)
            case _ =>
              reporter.error(None(), kind, s"""Failed to load: "${sound.text}"""")
              return sound
          }
        }

        var sounds = ISZ[Media]()

        def newSound(curr: Z): Sound = {
          return Sound(Os.path(""), "", 0, curr)
        }

        def parseVolume(vol: String, vpath: String): F64 = {
          F64(vol) match {
            case Some(v) if 0.0 <= v && v <= 1.0 =>
              return v
            case _ =>
              reporter.error(None(), kind, s"Invalid volume for $vpath [0.0 .. 1.0]: $vol")
              return 1.0
          }
        }

        var currSound = newSound(start)

        def storeSound(): Unit = {
          if (currSound.text != "") {
            val sound = process(currSound)
            sounds = sounds :+ sound
            currSound = newSound(sound.timeline + sound.duration)
          } else {
            currSound = newSound(currSound.timeline)
          }
        }

        for (l <- ops.StringOps(text).split((c: C) => c == '\n')) {
          ops.StringOps(l).trim match {
            case string"" =>
              storeSound()
              currSound = currSound(timeline = currSound.timeline + spec.textDelay)
            case line =>
              val lineOps = ops.StringOps(line)
              if (lineOps.startsWith("[") && lineOps.endsWith("]")) {
                val dir = ops.StringOps(lineOps.substring(1, line.size - 1)).trim
                Z(dir) match {
                  case Some(n) =>
                    storeSound()
                    currSound = currSound(timeline = currSound.timeline + n)
                  case _ =>
                    var volume: F64 = 0.0
                    val apath: Os.Path = if (ops.StringOps(dir).indexOf(';') >= 0) {
                      ops.StringOps(dir).split((c: C) => c == ';') match {
                        case ISZ(vol, p) =>
                          volume = parseVolume(ops.StringOps(vol).trim, p)
                          Os.path(ops.StringOps(p).trim)
                        case _ =>
                          reporter.error(None(), kind, s"Could not parse: $line (expecting [ <volume> ; ] <audio-path> )")
                          Os.path("")
                      }
                    } else {
                      Os.path(dir)
                    }
                    if (apath.string == "") {
                      // skip
                    } else if (apath.exists) {
                      val target = audio / apath.name
                      if (target.string != apath.string) {
                        apath.copyOverTo(target)
                        println(s"Wrote $target")
                        println()
                      }
                      getSoundDuration(apath.toUri) match {
                        case Some(dur) =>
                          if (currSound.text != "") {
                            storeSound()
                            currSound = currSound(timeline = currSound.timeline + spec.textDelay)
                          }
                          currSound = currSound(filepath = target, duration = dur)
                          sounds = sounds :+ currSound
                          currSound = newSound(currSound.timeline + dur)
                        case _ => reporter.error(None(), kind, s"Failed to load: $apath")
                      }
                    } else {
                      reporter.error(None(), kind, s"$apath does not exist")
                    }
                }
              } else {
                currSound = currSound(text = if (currSound.text == "") line else s"${currSound.text} $line")
              }
          }
        }

        storeSound()

        if (sounds.isEmpty) {
          return (sounds, start)
        } else {
          val last = sounds(sounds.size - 1)
          return (sounds, last.timeline + last.duration)
        }
      }

      val outDir = path / "out" / "presentasi" / spec.name
      val slides = outDir / "Slides"
      outDir.mkdirAll()
      for (f <- outDir.list if !ops.StringOps(f.name).startsWith("ScreenRecording")) {
        f.removeAll()
      }
      slides.mkdirAll()
      var medias = ISZ[Media]()
      var curr: Z = 0
      var first = T
      var transcript = ISZ[ST]()
      val hasCWebP = proc"cwebp -h".run().ok

      for (entry <- spec.entries) {
        def processTarget(d: Os.Path, e: Presentation.Entry): (String, Os.Path) = {
          val p = Os.path(e.path)
          val target = d / p.name
          if (target.canon.string != p.canon.string) {
            p.copyOverTo(target)
            println(s"Wrote $target")
            println()
          }
          println(s"Loading $p ...")
          return (p.toUri, target)
        }
        def addTranscriptEntry(p: Os.Path, sounds: ISZ[Media]): Unit = {
          var transcriptItems = ISZ[ST]()
          for (sound <- sounds) {
            val text = sound.asInstanceOf[Sound].text
            if (text.size > 0) {
              transcriptItems = transcriptItems :+ st"* ${spec.cc.get(text).getOrElse(text)}"
            }
          }
          transcript = transcript :+
            st"""![${p.name}](${slides.relativize(p)})
                |
                |${(transcriptItems, "\n\n")}"""
        }
        entry match {
          case entry: Presentation.Slide =>
            val (uri, target) = processTarget(image, entry)
            val ok = checkImage(uri)
            println()
            if (ok) {
              val gap: Z = if (entry.delay == 0) if (first) 0 else spec.delay else entry.delay
              medias = medias :+ Image(target.name, curr + gap)
              val (sounds, last) = processText(entry.text, curr)
              var p = Os.path(entry.path)
              if (hasCWebP && o.slides && p.ext == "png") {
                val webp = slides / s"${ops.StringOps(p.name).substring(0, p.name.size - p.ext.size - 1)}.webp"
                if (!webp.exists || webp.lastModified < p.lastModified) {
                  Os.proc(ISZ("cwebp", "-lossless", p.string, "-o", webp.string)).runCheck()
                }
                p = webp
              }
              addTranscriptEntry(p, sounds)
              medias = medias ++ sounds
              curr = last
            } else {
              reporter.error(None(), kind, s"Could not load image ${entry.path}")
            }
          case entry: Presentation.Video =>
            val (uri, target) = processTarget(video, entry)
            val durOpt = getVideoDuration(uri)
            println()
            durOpt match {
              case Some(dur) =>
                val durR = conversions.Z.toR(dur)
                val start: F64 = if (entry.start < 0.0) {
                  0.0
                } else if (conversions.F64.toR(entry.start) > durR) {
                  reporter.error(None(), kind, s"Invalid start for video ${entry.path}: ${entry.start}")
                  0.0
                } else {
                  entry.start
                }
                val end: F64 = if (entry.end == 0.0 || conversions.F64.toR(entry.end) > durR) {
                  0.0
                } else if (entry.end < start) {
                  reporter.error(None(), kind, s"Invalid end for video ${entry.path}: ${entry.end}")
                  0.0
                } else {
                  entry.end
                }
                val volume: F64 = if (0.0 <= entry.volume && entry.volume <= 1.0) {
                  entry.volume
                } else {
                  reporter.error(None(), kind, s"Invalid volume for video ${entry.path} [0.0 .. 1.0]: ${entry.volume}")
                  1.0
                }
                val gap: Z = if (entry.delay == 0) if (first) 0 else spec.delay else entry.delay
                val rate: F64 = if (0.0 < entry.rate && entry.rate <= 8.0) {
                  entry.rate
                } else {
                  reporter.error(None(), kind, s"Invalid rate for video ${entry.path} (0.0 .. 8.0]: ${entry.volume}")
                  1.0
                }
                medias = medias :+ Video(target, dur, curr + gap, start, end, entry.textOpt.nonEmpty, volume, rate)
                val newCurr = curr + gap + (
                  if (end == 0.0) conversions.R.toZ(durR / conversions.F64.toR(rate)) + 1
                  else conversions.R.toZ(conversions.F64.toR(end - start) / conversions.F64.toR(rate)) + 1)
                var p = Os.path(entry.path)
                if (o.slides) {
                  val ss = entry.start / 1000d
                  val t: ISZ[String] =
                    if (entry.end > 0d) ISZ[String]("-t", ((entry.end - entry.start) / 1000d).string)
                    else ISZ[String]()
                  val gif = slides / s"${ops.StringOps(p.name).substring(0, p.name.size - p.ext.size - 1)}.${o.videoHeight}.$ss${if (t.isEmpty) "" else s"-${t(1)}"}.gif"
                  if (!gif.exists || gif.lastModified < p.lastModified) {
                    println(s"Generating $gif ...")
                    Os.proc(ISZ[String]("ffmpeg", "-y", "-i", p.value, "-ss", ss.string) ++ t ++ ISZ[String]("-loop", "0", "-filter_complex",
                      s"fps=${o.videoFps}, scale=-1:${o.videoHeight}[s]; [s]split[a][b]; [a]palettegen[palette]; [b][palette]paletteuse",
                      gif.string
                    )).runCheck()
                    println()
                  }
                  p = gif
                }
                entry.textOpt match {
                  case Some(text) =>
                    val (sounds, last) = processText(text, curr)
                    addTranscriptEntry(p, sounds)
                    medias = medias ++ sounds
                    curr = if (entry.useVideoDuration) newCurr else last
                  case _ =>
                    addTranscriptEntry(p, ISZ())
                    curr = newCurr
                }
              case _ =>
                reporter.error(None(), kind, s"Could not load video ${entry.path}")
            }
        }
        first = F
      }

      reporter.printMessages()
      if (reporter.hasIssue) {
        return INVALID_RESOURCE
      }

      var mediaSTs = ISZ[ST]()
      var previousTimelineOpt: Option[Z] = None()
      val audioDirUriSize = audioDir.toUri.size
      var n = 0
      var ccSrt = ISZ[ST]()
      var ccVtt = ISZ[ST]()

      for (i <- medias.indices) {
        medias(i) match {
          case media: Image =>
            mediaSTs = mediaSTs :+ imageTemplate(media.filename, media.timeline, i, previousTimelineOpt, n)
            n = n + 1
          case media: Video =>
            mediaSTs = mediaSTs :+ videoTemplate(media.filename, media.timeline, media.muted,
              media.rate, media.start, media.end, i, previousTimelineOpt, n)
            n = n + 1
          case media: Sound =>
            val mediaUri = media.filepath.toUri
            val ccText = spec.cc.get(media.text).getOrElse(media.text)
            mediaSTs = mediaSTs :+ soundTemplate(ops.StringOps(mediaUri).substring(audioDirUriSize, mediaUri.size),
              media.timeline, i, previousTimelineOpt, ccText)
            val t = media.timeline + spec.vseekDelay
            if (ccText.size > 0) {
              val startSrt = Ext.formatCcTime(T, t + o.cc)
              val endSrt = Ext.formatCcTime(T, t + o.cc + media.duration)
              val startVtt = Ext.formatCcTime(F, t + o.cc)
              val endVtt = Ext.formatCcTime(F, t + o.cc + media.duration)
              ccSrt = ccSrt :+
                st"""${ccSrt.size + 1}
                    |$startSrt --> $endSrt
                    |$ccText"""
              ccVtt = ccVtt :+
                st"""${ccVtt.size + 1}
                    |$startVtt --> $endVtt
                    |$ccText"""
            }
        }
        previousTimelineOpt = Some(medias(i).timeline)
      }

      val f = source / s"${spec.name}.java"
      val transcriptFile = slides / s"readme.md"
      val srtFile = outDir / s"${spec.name}.srt"
      val vttFile = outDir / s"${spec.name}.vtt"
      val end: Z = if (medias.size > 0) {
        val last = medias(medias.size - 1)
        last.timeline + last.duration + spec.trailing
      } else {
        0
      }

      if (o.swing) {
        f.writeOver(presentasiSwingTemplate(spec.name, spec.granularity, spec.vseekDelay, spec.textVolume, end, mediaSTs).render)
      } else {
        f.writeOver(presentasiTemplate(spec.name, spec.granularity, spec.vseekDelay, spec.textVolume, end, mediaSTs).render)
      }
      println(s"Wrote $f")
      if (o.record) {
        srtFile.writeOver(st"${(ccSrt, "\n\n")}".render)
        println(s"Wrote $srtFile")
        vttFile.writeOver(
          st"""WEBVTT
              |
              |${(ccVtt, "\n\n")}""".render
        )
        println(s"Wrote $vttFile")

        val audioServiceName: String = o.service match {
          case Cli.SireumPresentasiGenService.Aws => "aws"
          case Cli.SireumPresentasiGenService.Azure => "azure"
          case Cli.SireumPresentasiGenService.Elevenlabs => "elevenlabs"
          case Cli.SireumPresentasiGenService.Google => "google"
          case Cli.SireumPresentasiGenService.Mary => "marytts"
        }
        val audioVoiceSeg: ST = o.voice match {
          case Some(voice) => st""" / "${ops.StringOps(voice).escapeST}""""
          case _ => st""
        }
        var cmdSegs = ISZ[ST]()
        for (m <- medias) {
          m match {
            case img: Image => cmdSegs = cmdSegs :+ imgSegTemplate(img.timeline, img.filename)
            case vid: Video => cmdSegs = cmdSegs :+ vidSegTemplate(vid.timeline, vid.duration, vid.start, vid.end,
              vid.muted, vid.rate, vid.filename)
            case snd: Sound => cmdSegs = cmdSegs :+ sndSegTemplate(snd.timeline, snd.duration, snd.filepath.name)
          }
        }
        val cmdFile = source / s"${spec.name}.cmd"
        cmdFile.writeOver(assembleCmdTemplate(spec.name, end, 1920, 1080, 30,
          audioServiceName, audioVoiceSeg, cmdSegs).render)
        cmdFile.chmod("+x")
        println(s"Wrote $cmdFile")
      }
      if (o.slides) {
        transcriptFile.writeOver(st"${(transcript, "\n\n----\n\n")}".render)
        println(s"Wrote $transcriptFile")
      }
      if (o.clean) {
        for (f <- generatedAudioFiles.elements) {
          f.removeAll()
          println(s"Removed $f")
        }
      }
    }
    return 0
  }

  // Premade voice name-to-ID mappings from https://elevenlabs-sdk.mintlify.app/voices/premade-voices
  val elevenLabsVoiceMap: HashMap[String, String] = HashMap.empty[String, String] ++ ISZ(
    "Adam" ~> "pNInz6obpgDQGcFmaJgB", "Alice" ~> "Xb7hH8MSUJpSbSDYk0k2",
    "Antoni" ~> "ErXwobaYiN019PkySvjV", "Arnold" ~> "VR6AewLTigWG4xSOukaG",
    "Bill" ~> "pqHfZKP75CvOlQylNhV4", "Brian" ~> "nPczCjzI2devNBz1zQrb",
    "Callum" ~> "N2lVS1w4EtoT3dr4eOWO", "Charlie" ~> "IKne3meq5aSn9XLyUdCD",
    "Charlotte" ~> "XB0fDUnXU5powFXDhCwa", "Chris" ~> "iP95p4xoKVk53GoZ742B",
    "Clyde" ~> "2EiwWnXFnvU5JabPnv8n", "Daniel" ~> "onwK4e9ZLuTAKqWW03F9",
    "Dave" ~> "CYw3kZ02Hs0563khs1Fj", "Domi" ~> "AZnzlk1XvdvUeBnXmlld",
    "Dorothy" ~> "ThT5KcBeYPX3keUQqHPh", "Drew" ~> "29vD33N1CtxCmqQRPOHJ",
    "Emily" ~> "LcfcDJNUP1GQjkzn1xUU", "Ethan" ~> "g5CIjZEefAph4nQFvHAz",
    "Fin" ~> "D38z5RcWu1voky8WS1ja", "Freya" ~> "jsCqWAovK2LkecY7zXl4",
    "George" ~> "JBFqnCBsd6RMkjVDRZzb", "Gigi" ~> "jBpfuIE2acCO8z3wKNLl",
    "Giovanni" ~> "zcAOhNBS3c14rBihAFp1", "Glinda" ~> "z9fAnlkpzviPz146aGWa",
    "Grace" ~> "oWAxZDx7w5VEj9dCyTzz", "Harry" ~> "SOYHLrjzK2X1ezoPC6cr",
    "James" ~> "ZQe5CZNOzWyzPSCn5a3c", "Jeremy" ~> "bVMeCyTHy58xNoL34h3p",
    "Jessie" ~> "t0jbNlBVZ17f02VDIeMI", "Joseph" ~> "Zlb1dXrM653N07WRdFW3",
    "Josh" ~> "TxGEqnHWrfWFTfGW9XjX", "Liam" ~> "TX3LPaxmHKxFdv7VOQHJ",
    "Lily" ~> "pFZP5JQG7iQjIQuC4Bku", "Matilda" ~> "XrExE9yKIg1WjnnlVkGX",
    "Michael" ~> "flq6f7yk4E4fJM5XTYuZ", "Mimi" ~> "zrHiDhphv9ZnVXBqCLjz",
    "Nigel" ~> "l9yjqAhh8GXv7ZJxsLZO", "Nicole" ~> "piTKgcLEGmPE4e6mEKli",
    "Patrick" ~> "ODq5zmih8GrVes37Dizd",
    "Paul" ~> "5Q0t7uMcjvnagumLfvZi", "Rachel" ~> "21m00Tcm4TlvDq8ikWAM",
    "Sam" ~> "yoZ06aMxZJJ28mfd3POQ", "Sarah" ~> "EXAVITQu4vr4xnSDxMaL",
    "Serena" ~> "pMsXgVXv3BLzUgSXRplE", "Sophie" ~> "khYwAWwYSjlxlcrwGQ16",
    "Thomas" ~> "GBv7mTt0atIp3Br8iCZE"
  )

  def elevenLabsResolveVoiceId(voice: String, key: String): String = {
    elevenLabsVoiceMap.get(voice) match {
      case Some(id) => return id
      case _ =>
    }
    // If it looks like an ID already (contains digits and uppercase), use as-is
    if (ops.StringOps(voice).size > 15) {
      return voice
    }
    // Runtime lookup via API
    println(s"Looking up ElevenLabs voice: $voice")
    Os.proc(ISZ("curl", "-s",
      s"https://api.elevenlabs.io/v2/voices?search=$voice",
      "--header", s"xi-api-key: $key")).redirectErr.run().out match {
      case out if ops.StringOps(out).contains("voice_id") =>
        // Extract first voice_id from JSON response
        val so = ops.StringOps(out)
        val i = so.stringIndexOf("\"voice_id\"")
        if (i >= 0) {
          val rest = ops.StringOps(so.substring(i + 12, out.size))
          val j = rest.stringIndexOf("\"")
          if (j >= 0) {
            val id = rest.substring(0, j)
            println(s"  Resolved '$voice' -> $id")
            return id
          }
        }
        eprintln(s"Could not parse voice ID from ElevenLabs API response for: $voice")
        return voice
      case _ =>
        eprintln(s"Could not resolve ElevenLabs voice: $voice")
        return voice
    }
  }

  def text2speech(o: Cli.SireumPresentasiText2speechOption): Z = {

    def maryTTS(javaHome: Os.Path, maryTtsJar: Os.Path, voice: String, input: Os.Path, output: Os.Path): OsProto.Proc.Result = {
      val javaExe: Os.Path = if (Os.isWin) javaHome / "bin" / "java.exe" else javaHome / "bin" / "java"
      return proc"$javaExe -jar $maryTtsJar -o $output -v $voice -i $input".env(ISZ("JAVA_HOME" ~> javaHome.string)).console.runCheck()
    }

    @pure def isSoundFile(path: Os.Path): B = {
      return path.ext == "mp3" || path.ext == "wav" || path.ext == "webm" || path.ext == "ogg"
    }

    @pure def outputFile(output: Os.Path, inputFilename: String, i: Z, line: String, ext: String): Os.Path = {
      var num: String = s"$i-"
      while (num.size < 3) {
        num = s"0$num"
      }
      if (output == Os.cwd) {
        var cis = ISZ[C]()
        for (c <- conversions.String.toCStream(line).take(16)) {
          cis = cis :+ (if ('A' <= c && c <= 'Z' || '0' <= c && c <= '9') c else '_')
        }
        return output.canon / s"$num${conversions.String.fromCis(cis)}.$ext"
      } else if (isSoundFile(output)) {
        return output
      } else {
        return output.up.canon / s"$num$inputFilename.$ext"
      }
    }

    val inputFile: Os.Path = o.args match {
      case ISZ() =>
        return printHelp(o.help)
      case ISZ(p) =>
        val f = Os.path(p)
        if (!f.isFile) {
          eprintln(s"$p is not a file")
          Os.exit(-1)
          halt("")
        }
        f
      case _ =>
        printHelp(o.help)
        return -1
    }
    val javaHome = SireumApi.javaHomeOpt.get
    val maryTtsJar: Os.Path = SireumApi.homeOpt.get / "lib" / "marytts_text2wav.jar"

    val output: Os.Path = o.output match {
      case Some(p) =>
        val f = Os.path(p)
        if (!isSoundFile(f)) {
          f.mkdirAll()
        }
        f
      case _ => Os.cwd
    }

    o.service match {
      case Cli.SireumPresentasiText2speechService.Azure =>
        val key: String = o.key match {
          case Some(k) => k
          case _ =>
            Os.env("AZURE_KEY") match {
              case Some(k) => k
              case _ =>
                eprintln("Please supply your Azure subscription key via CLi option --key or via the AZURE_KEY env var")
                Os.exit(-1)
                halt("")
            }
        }
        var i = 1
        val tmp = Os.tempFix("", if (Os.isWin) ".bat" else "")
        val echoOffOpt: Option[String] = if (Os.isWin) Some("@echo off") else None()
        tmp.removeOnExit()
        val voice = o.voice.getOrElseEager("en-GB-RyanNeural")
        val (format, ext): (String, String) = o.outputFormat match {
          case Cli.SireumPresentasiText2speechOutputFormat.Mp3 => ("audio-48khz-192kbitrate-mono-mp3", "mp3")
          case Cli.SireumPresentasiText2speechOutputFormat.Wav => ("raw-48khz-16bit-mono-pcm", "wav")
          case Cli.SireumPresentasiText2speechOutputFormat.Webm => ("webm-24khz-16bit-mono-opus", "webm")
          case Cli.SireumPresentasiText2speechOutputFormat.Ogg => ("ogg-48khz-16bit-mono-opus", "ogg")
        }
        for (line <- inputFile.readLineStream if ops.StringOps(line).trim.size > 0) {
          val out = outputFile(output, inputFile.name, i, line, ext)
          if (!out.exists || o.force || out.size == 0) {
            println(s"Synthesizing: $line")
            tmp.writeOver(
              st"""$echoOffOpt
                  |curl --location --request POST 'https://${o.region.get}.tts.speech.microsoft.com/cognitiveservices/v1' --header 'Ocp-Apim-Subscription-Key: $key' --header 'Content-Type: application/ssml+xml' --header 'X-Microsoft-OutputFormat: $format' --header 'User-Agent: curl' --data-raw '<speak version="1.0" xml:lang="${o.lang.get}"><voice xml:lang="${o.voiceLang.get}" xml:gender="${o.gender.get}" name="$voice">${ops.StringOps(line).replaceAllLiterally("'", "'\\''")}</voice></speak>' -o $out""".render)
            tmp.chmod("+x")
            proc"$tmp".console.runCheck()
            if (ext == "wav") {
              Ext.pcm2wav(out, 48000)
            }
            println()
          } else {
            println(s"Skipping already generated: $line")
            println()
          }
          i = i + 1
        }
      case Cli.SireumPresentasiText2speechService.Aws =>
        val aws = Os.path(o.awsPath.get)
        val tmp = Os.tempFix("", if (Os.isWin) ".bat" else "")
        val echoOffOpt: Option[String] = if (Os.isWin) Some("@echo off") else None()
        tmp.removeOnExit()
        var i = 1
        val voice = o.voice.getOrElseEager("Amy")
        val engine: String = o.engine match {
          case Cli.SireumPresentasiText2speechEngine.Neural => "neural"
          case Cli.SireumPresentasiText2speechEngine.Standard => "standard"
        }
        val (outputFormat, ext, rate): (String, String, Z) = o.outputFormat match {
          case Cli.SireumPresentasiText2speechOutputFormat.Mp3 => ("mp3", "mp3", 24000)
          case Cli.SireumPresentasiText2speechOutputFormat.Ogg => ("ogg_vorbis", "ogg", 24000)
          case Cli.SireumPresentasiText2speechOutputFormat.Wav => ("pcm", "wav", 16000)
          case Cli.SireumPresentasiText2speechOutputFormat.Webm =>
            eprintln("AWS does not support webm output format")
            return -1
        }
        for (line <- inputFile.readLineStream if ops.StringOps(line).trim.size > 0) {
          val out = outputFile(output, inputFile.name, i, line, ext)
          if (!out.exists || o.force) {
            println(s"Synthesizing: $line")
            tmp.writeOver(
              st"""$echoOffOpt
                  |$aws polly synthesize-speech --engine $engine --language-code ${o.lang} --output-format $outputFormat --sample-rate $rate --text "<speak>$line</speak>" --text-type "ssml" --voice-id "$voice" $out""".render)
            tmp.chmod("+x")
            proc"$tmp".console.runCheck()
            if (ext == "wav") {
              Ext.pcm2wav(out, 16000)
            }
            println()
          } else {
            println(s"Skipping already generated: $line")
            println()
          }
          i = i + 1
        }
      case Cli.SireumPresentasiText2speechService.Elevenlabs =>
        val key: String = o.elevenLabsKey match {
          case Some(k) => k
          case _ =>
            Os.env("ELEVENLABS_API_KEY") match {
              case Some(k) => k
              case _ =>
                eprintln("Please supply your ElevenLabs API key via CLI option --elevenlabs-key or via the ELEVENLABS_API_KEY env var")
                Os.exit(-1)
                halt("")
            }
        }
        var i = 1
        val tmp = Os.tempFix("", if (Os.isWin) ".bat" else "")
        val echoOffOpt: Option[String] = if (Os.isWin) Some("@echo off") else None()
        tmp.removeOnExit()
        val voiceName = o.voice.getOrElseEager("Daniel")
        val voiceId = elevenLabsResolveVoiceId(voiceName, key)
        val model = o.elevenLabsModel.getOrElseEager("eleven_multilingual_v2")
        val (accept, ext): (String, String) = o.outputFormat match {
          case Cli.SireumPresentasiText2speechOutputFormat.Mp3 => ("audio/mpeg", "mp3")
          case Cli.SireumPresentasiText2speechOutputFormat.Wav => ("audio/wav", "wav")
          case Cli.SireumPresentasiText2speechOutputFormat.Ogg => ("audio/ogg", "ogg")
          case Cli.SireumPresentasiText2speechOutputFormat.Webm => ("audio/webm", "webm")
        }
        for (line <- inputFile.readLineStream if ops.StringOps(line).trim.size > 0) {
          val out = outputFile(output, inputFile.name, i, line, ext)
          if (!out.exists || o.force || out.size == 0) {
            println(s"Synthesizing: $line")
            val escapedLine = ops.StringOps(ops.StringOps(line).replaceAllLiterally("\\", "\\\\")).replaceAllLiterally("\"", "\\\"")
            tmp.writeOver(
              st"""$echoOffOpt
                  |curl -s --location --request POST "https://api.elevenlabs.io/v1/text-to-speech/$voiceId" --header "xi-api-key: $key" --header "Content-Type: application/json" --data-raw "{\"text\":\"$escapedLine\",\"model_id\":\"$model\"}" --output "$out" --header "Accept: $accept"""".render)
            tmp.chmod("+x")
            proc"$tmp".console.runCheck()
            if (out.exists && ops.StringOps(out.read).startsWith("{\"detail\"")) {
              eprintln(s"ElevenLabs API error: ${out.read}")
              out.removeAll()
            }
            println()
          } else {
            println(s"Skipping already generated: $line")
            println()
          }
          i = i + 1
        }
      case Cli.SireumPresentasiText2speechService.Google =>
        val key: String = o.googleKey match {
          case Some(k) => k
          case _ =>
            Os.env("GOOGLE_API_KEY") match {
              case Some(k) => k
              case _ =>
                eprintln("Please supply your Google Cloud API key via CLI option --google-key or via the GOOGLE_API_KEY env var")
                Os.exit(-1)
                halt("")
            }
        }
        var i = 1
        val tmp = Os.tempFix("", if (Os.isWin) ".bat" else "")
        val echoOffOpt: Option[String] = if (Os.isWin) Some("@echo off") else None()
        tmp.removeOnExit()
        val voice = o.voice.getOrElseEager("en-GB-Chirp3-HD-Umbriel")
        val langCode = ops.StringOps(voice).substring(0, 5)
        val (audioEncoding, ext): (String, String) = o.outputFormat match {
          case Cli.SireumPresentasiText2speechOutputFormat.Mp3 => ("MP3", "mp3")
          case Cli.SireumPresentasiText2speechOutputFormat.Wav => ("LINEAR16", "wav")
          case Cli.SireumPresentasiText2speechOutputFormat.Ogg => ("OGG_OPUS", "ogg")
          case Cli.SireumPresentasiText2speechOutputFormat.Webm => ("WEBM_OPUS", "webm")
        }
        for (line <- inputFile.readLineStream if ops.StringOps(line).trim.size > 0) {
          val out = outputFile(output, inputFile.name, i, line, ext)
          if (!out.exists || o.force || out.size == 0) {
            println(s"Synthesizing: $line")
            val trimmed = ops.StringOps(line).trim
            val lastChar: String = if (trimmed.size > 0) ops.StringOps(trimmed).substring(trimmed.size - 1, trimmed.size) else ""
            val textLine: String = if (lastChar != "" && !ops.StringOps(".!?").contains(lastChar)) s"$line." else line
            val escapedLine = ops.StringOps(ops.StringOps(textLine).replaceAllLiterally("\\", "\\\\")).replaceAllLiterally("\"", "\\\"")
            val jsonTmp = Os.temp()
            jsonTmp.removeOnExit()
            val b64Tmp = Os.temp()
            b64Tmp.removeOnExit()
            jsonTmp.writeOver(
              s"""{"input":{"text":"$escapedLine"},"voice":{"languageCode":"$langCode","name":"$voice"},"audioConfig":{"audioEncoding":"$audioEncoding"}}""")
            tmp.writeOver(
              st"""$echoOffOpt
                  |curl -s -X POST "https://texttospeech.googleapis.com/v1/text:synthesize?key=$key" -H "Content-Type: application/json" -d @$jsonTmp -o $b64Tmp""".render)
            tmp.chmod("+x")
            proc"$tmp".console.runCheck()
            if (b64Tmp.exists && b64Tmp.size > 0) {
              val response = b64Tmp.read
              if (ops.StringOps(response).contains("\"audioContent\"")) {
                // Extract base64 audio content and decode
                val so = ops.StringOps(response)
                val startIdx = so.stringIndexOf("\"audioContent\": \"")
                if (startIdx >= 0) {
                  val afterKey = ops.StringOps(so.substring(startIdx + 17, response.size))
                  val endIdx = afterKey.stringIndexOf("\"")
                  if (endIdx >= 0) {
                    val b64 = afterKey.substring(0, endIdx)
                    conversions.String.fromBase64(b64) match {
                      case Either.Left(u8s) =>
                        out.writeOverU8s(u8s)
                        if (ext == "wav") {
                          Ext.pcm2wav(out, 24000)
                        }
                      case _ =>
                    }
                  }
                }
              } else if (ops.StringOps(response).contains("\"error\"")) {
                eprintln(s"Google TTS API error: $response")
              }
            }
            jsonTmp.removeAll()
            b64Tmp.removeAll()
            println()
          } else {
            println(s"Skipping already generated: $line")
            println()
          }
          i = i + 1
        }
      case Cli.SireumPresentasiText2speechService.Mary =>
        var i = 1
        val tmp = Os.temp()
        tmp.removeOnExit()
        val voice = o.voice.getOrElseEager("dfki-spike-hsmm")
        for (line <- inputFile.readLineStream if ops.StringOps(line).trim.size > 0) {
          val out = outputFile(output, inputFile.name, i, line, "wav")
          if (!out.exists || o.force) {
            println(s"Synthesizing: $line")
            tmp.writeOver(line)
            maryTTS(javaHome, maryTtsJar, voice, tmp, out)
            println()
          } else {
            println(s"Skipping already generated: $line")
            println()
          }
          i = i + 1
        }
    }
    return 0
  }

  @ext("Presentasi_Ext") object Ext {

    def pcm2wav(path: Os.Path, srate: Z): Unit = $

    def parseMarkdowns(args: ISZ[String], path: Os.Path, reporter: message.Reporter): ISZ[presentasi.Presentation] = $

    def formatCcTime(isSrt: B, ms: Z): String = $
  }
}
