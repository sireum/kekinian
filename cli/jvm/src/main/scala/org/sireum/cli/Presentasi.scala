// #Sireum
/*
 Copyright (c) 2017-2021, Robby, Kansas State University
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

  @datatype class Sound(val filename: String, val text: String, val duration: Z, val timeline: Z) extends Media

  @datatype class Video(val filename: String, val duration: Z, val timeline: Z, val start: F64, val end: F64,
                        val muted: B, val volume: F64, val rate: F64) extends Media

  val INVALID_ARGS: Z = -2
  val INVALID_PATH: Z = -3
  val INVALID_SPEC: Z = -4
  val INVALID_RESOURCE: Z = -4

  def printHelp(help: String): Z = {
    println(help)
    println()
    println(
      st"""Available MaryTTS voices are: cmu-bdl-hsmm, cmu-rms-hsmm, cmu-slt-hsmm, dfki-obadiah-hsmm, dfki-prudence-hsmm, dfki-spike-hsmm
          |
          |For AWS, please refer to https://docs.aws.amazon.com/polly/latest/dg/voicelist.html
          |
          |For Azure, please refer to https://docs.microsoft.com/en-us/azure/cognitive-services/speech-service/rest-text-to-speech""".render)
    return 0
  }

  def gen(o: Cli.SireumPresentasiGenOption): Z = {

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

    @strictpure def soundTemplate(filename: String, timeline: Z, i: Z, prevTimelineOpt: Option[Z]): ST =
      st"""${localTemplate(timeline, i, prevTimelineOpt)}
          |medias.add(new Sound("/audio/$filename", t_$i));"""

    @strictpure def imageTemplate(filename: String, timeline: Z, i: Z, prevTimelineOpt: Option[Z]): ST =
      st"""${localTemplate(timeline, i, prevTimelineOpt)}
          |medias.add(new Image("/image/$filename", t_$i));"""

    @strictpure def videoTemplate(filename: String, timeline: Z, muted: B, rate:F64, start: F64, end: F64, i: Z,
                                  prevTimelineOpt: Option[Z]): ST =
      st"""${localTemplate(timeline, i, prevTimelineOpt)}
          |medias.add(new Video("/video/$filename", t_$i, $muted, $rate, $start, $end));"""

    @strictpure def presentasiTemplate(name: String, trailing: Z, granularity: Z, textVolume: F64, end: Z, medias: ISZ[ST]): ST =
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
          |import java.util.LinkedList;
          |import java.util.List;
          |
          |public class $name extends Application {
          |
          |    public final static long TRAILING = $trailing;
          |    public final static long TIMELINE_GRANULARITY = $granularity;
          |    public final static double TEXT_VOLUME = $textVolume;
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
          |        public final MediaPlayer mediaPlayer;
          |        private boolean ready = false;
          |        private boolean error = false;
          |        private long duration = 0L;
          |
          |        public Sound(final String path, final long timeline) {
          |            this.uri = getResourceUri(path);
          |            this.timeline = timeline;
          |            this.mediaPlayer = new MediaPlayer(new javafx.scene.media.Media(uri));
          |            this.mediaPlayer.setOnReady(() -> {
          |                this.ready = true;
          |                this.duration = (long) Math.ceil(mediaPlayer.getTotalDuration().toMillis());
          |            });
          |            this.mediaPlayer.setOnError(() -> this.error = true);
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
          |    }
          |
          |    public final static class Video implements Media {
          |        public final String uri;
          |        private final long timeline;
          |        public final MediaView mediaView;
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
          |            final MediaPlayer mediaPlayer = new MediaPlayer(new javafx.scene.media.Media(uri));
          |            mediaPlayer.setOnReady(() -> {
          |                this.ready = true;
          |                if (endMs > 0.0) {
          |                    this.duration = (long) Math.ceil(endMs - startMs);
          |                } else {
          |                    this.duration = (long) Math.ceil(mediaPlayer.getTotalDuration().toMillis());
          |                }
          |            });
          |            mediaPlayer.setOnError(() -> this.error = true);
          |            this.mediaView = new MediaView(mediaPlayer);
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
          |        switch (args.size()) {
          |            case 0:
          |                break;
          |            case 1:
          |                final String arg = args.get(0);
          |                try {
          |                    if (arg.charAt(0) == '#') {
          |                        slideNo = Integer.parseInt(arg.substring(1));
          |                    } else {
          |                        startTime = Long.parseLong(arg);
          |                    }
          |                } catch (Throwable t) {
          |                    System.err.println("Invalid argument " + arg);
          |                    System.err.flush();
          |                    Platform.exit();
          |                }
          |                break;
          |            default:
          |                System.err.println("Invalid arguments");
          |                System.err.flush();
          |                Platform.exit();
          |        }
          |
          |        ${(medias, "\n")}
          |
          |        final long end = ${end}L; // ${formatTime(end)}
          |
          |        final Rectangle2D rect = Screen.getPrimary().getBounds();
          |        final double width = rect.getWidth();
          |        final double height = rect.getHeight();
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
          |                    final MediaPlayer mediaPlayer = ((Sound) media).mediaPlayer;
          |                    mediaPlayer.setVolume(TEXT_VOLUME);
          |                    mediaPlayer.play();
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
          |                    stage.getScene().setRoot(root);
          |                    final MediaPlayer player = mediaView.getMediaPlayer();
          |                    player.setStartTime(Duration.millis(video.startMillis));
          |                    player.setRate(video.rate);
          |                    if (video.endMillis > 0.0) player.setStopTime(Duration.millis(video.endMillis));
          |                    player.setVolume(1.0);
          |                    player.setMute(video.muted);
          |                    Platform.runLater(() -> {
          |                        stage.show();
          |                        player.play();
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
          |        primaryStage.setFullScreen(true);
          |        primaryStage.setResizable(false);
          |        primaryStage.setFullScreenExitHint("");
          |        final Scene scene = new Scene(new StackPane(), primaryStage.getMaxWidth(), primaryStage.getMaxHeight());
          |        primaryStage.setScene(scene);
          |        this.stage = primaryStage;
          |    }
          |
          |}"""

    val path: Os.Path = o.args match {
      case ISZ() => return printHelp(o.help)
      case ISZ(p) => Os.path(p).canon
      case _ =>
        eprintln(st"Invalid arguments: ${(o.args, " ")}".render)
        return INVALID_ARGS
    }
    val presentasi = path / "bin" / "presentasi.cmd"
    if (!presentasi.exists) {
      eprintln(s"$presentasi does not exists")
      return INVALID_PATH
    }

    val outTemp = Os.temp()
    val r = SlangRunner.run(Cli.SireumSlangRunOption("", ISZ(presentasi.string), None(),
      Some(outTemp.string), F, F))
    if (r != 0) {
      eprintln(outTemp.read)
      return INVALID_SPEC
    }

    val spec: Presentation = org.sireum.presentasi.JSON.toPresentation(outTemp.read) match {
      case Either.Left(o) => o
      case _ =>
        eprintln(s"Failed to process $path")
        return INVALID_SPEC
    }

    val resources = path / "jvm" / "src" / "main" / "resources"
    val source = path / "jvm" / "src" / "main" / "java"
    val audio = resources / "audio"
    val image = resources / "image"
    val video = resources / "video"
    audio.mkdirAll()
    image.mkdirAll()
    video.mkdirAll()

    if (o.force) {
      for (p <- audio.list if p.ext === "wav" || p.ext === "mp3") {
        val cis = conversions.String.toCis(p.name)
        if (cis.size > 7 && cis(6) == '-' && ops.ISZOps(ops.ISZOps(cis).take(6)).
          forall((c: C) => 'A' <= c && c <= 'Z' || '0' <= c && c <= '9')) {
          p.removeAll()
        }
      }
    }

    val reporter = Reporter.create
    val (service, ext): (Cli.SireumPresentasiText2speechService.Type, String) = o.service match {
      case Cli.SireumPresentasiGenService.Azure => (Cli.SireumPresentasiText2speechService.Azure, "mp3")
      case Cli.SireumPresentasiGenService.Aws => (Cli.SireumPresentasiText2speechService.Aws, "mp3")
      case Cli.SireumPresentasiGenService.Mary => (Cli.SireumPresentasiText2speechService.Mary, "wav")
    }

    def processText(text: String, start: Z): (ISZ[Media], Z) = {
      def fingerprint(t: String): String = {
        val c = crypto.SHA3.init256
        c.update(conversions.String.toU8is(t))
        return st"${(ops.ISZOps(c.finalise()).take(3), "")}".render
      }
      def process(sound: Sound): Sound = {
        val sub = conversions.String.fromCis(
          for (c <- conversions.String.toCis(
            if (sound.text.size > 15) ops.StringOps(sound.text).substring(0, 15) else sound.text)) yield
            if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')) c else '_')
        val filename = s"${fingerprint(sound.text)}-$sub.$ext"
        val p = audio / filename
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
          outputFormat = Cli.SireumPresentasiText2speechOutputFormat.Mp3,
          region = o.region,
          voiceLang = o.voiceLang,
          engine = engine
        ))
        temp.removeAll()
        println(s"Loading $p ...")
        val dur = SireumApi.getSoundDuration(p.toUri)
        println()
        dur match {
          case Some(dur) if code == 0 => return sound(filename = p.name, duration = dur)
          case _ =>
            reporter.error(None(), "presentasi", s"""Failed to load: "${sound.text}"""")
            return sound
        }

      }

      var sounds = ISZ[Media]()
      var curr = start

      def newSound: Sound = {
        return Sound("", "", 0, curr + spec.delay)
      }

      def parseVolume(vol: String, vpath: String): F64 = {
        F64(vol) match {
          case Some(v) if 0.0 <= v && v <= 1.0 =>
            return v
          case _ =>
            reporter.error(None(), "presentasi", s"Invalid volume for $vpath [0.0 .. 1.0]: $vol")
            return 1.0
        }
      }

      var currSound = newSound
      def storeSound(): Unit = {
        if (currSound.text =!= "") {
          val sound = process(currSound)
          sounds = sounds :+ sound
          curr = curr + sound.duration + spec.delay
        }
        currSound = newSound
      }
      for (l <- ops.StringOps(text).split((c: C) => c === '\n')) {
        ops.StringOps(l).trim match {
          case string"" => storeSound()
          case line =>
            val lineOps = ops.StringOps(line)
            if (lineOps.startsWith("[") && lineOps.endsWith("]")) {
              val dir = ops.StringOps(lineOps.substring(1, line.size - 1)).trim
              Z(dir) match {
                case Some(n) =>
                  storeSound()
                  currSound = currSound(timeline = currSound.timeline - spec.delay + n)
                case _ =>
                  var volume: F64 = 0.0
                  val apath: Os.Path = if (ops.StringOps(dir).indexOf(';') >= 0) {
                    ops.StringOps(dir).split((c: C) => c === ';') match {
                      case ISZ(vol, p) =>
                        volume = parseVolume(ops.StringOps(vol).trim, p)
                        Os.path(ops.StringOps(p).trim)
                      case _ =>
                        reporter.error(None(), "presentasi", s"Could not parse: $line (expecting [ <volume> ; ] <audio-path> )")
                        Os.path("")
                    }
                  } else {
                    Os.path(dir)
                  }
                  if (apath.string === "") {
                    // skip
                  } else if (apath.exists) {
                    val target = audio / apath.name
                    if (target.string != apath.string) {
                      apath.copyOverTo(target)
                      println(s"Wrote $target")
                      println()
                    }
                    SireumApi.getSoundDuration(apath.toUri) match {
                      case Some(dur) =>
                        if (currSound.text =!= "") {
                          storeSound()
                        }
                        currSound = currSound(filename = target.name, duration = dur)
                        sounds = sounds :+ currSound
                        curr = currSound.timeline + dur
                        currSound = newSound
                      case _ => reporter.error(None(), "presentasi", s"Failed to load: $apath")
                    }
                  } else {
                    reporter.error(None(), "presentasi", s"$apath does not exist")
                  }
              }
            } else {
              currSound = currSound(text = if (currSound.text === "") line else s"${currSound.text} $line")
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

    var medias = ISZ[Media]()
    var curr: Z = 0
    var first = T
    for (entry <- spec.entries) {
      entry match {
        case entry: Presentation.Slide =>
          val p = Os.path(entry.path)
          val target = image / p.name
          if (target.canon.string != p.canon.string) {
            p.copyOverTo(target)
            println(s"Wrote $target")
            println()
          }
          println(s"Loading $p ...")
          val ok = SireumApi.checkImage(p.toUri)
          println()
          if (ok) {
            val gap: Z = if (entry.delay == 0) if (first) 0 else spec.delay else entry.delay
            medias = medias :+ Image(target.name, curr + gap)
            val (sounds, last) = processText(entry.text, curr)
            medias = medias ++ sounds
            curr = last
          } else {
            reporter.error(None(), "presentasi", s"Could not load image ${entry.path}")
          }
        case entry: Presentation.Video =>
          val p = Os.path(entry.path)
          val target = video / p.name
          if (target.canon.string != p.canon.string) {
            p.copyOverTo(target)
            println(s"Wrote $target")
            println()
          }
          println(s"Loading $p ...")
          val dur = SireumApi.getVideoDuration(p.toUri)
          println()
          dur match {
            case Some(dur) =>
              val durR = conversions.Z.toR(dur)
              val start: F64 = if (entry.start < 0.0) {
                0.0
              } else if (conversions.F64.toR(entry.start) > durR) {
                reporter.error(None(), "presentasi", s"Invalid start for video ${entry.path}: ${entry.start}")
                0.0
              } else {
                entry.start
              }
              val end: F64 = if (entry.end == 0.0) {
                0.0
              } else if (entry.end < start || conversions.F64.toR(entry.end) > durR) {
                reporter.error(None(), "presentasi", s"Invalid end for video ${entry.path}: ${entry.end}")
                0.0
              } else {
                entry.end
              }
              val volume: F64 = if (0.0 <= entry.volume && entry.volume <= 1.0) {
                entry.volume
              } else {
                reporter.error(None(), "presentasi", s"Invalid volume for video ${entry.path} [0.0 .. 1.0]: ${entry.volume}")
                1.0
              }
              val gap: Z = if (entry.delay == 0) if (first) 0 else spec.delay else entry.delay
              val rate: F64 = if (0.0 < entry.rate && entry.rate <= 8.0) {
                entry.rate
              } else {
                reporter.error(None(), "presentasi", s"Invalid rate for video ${entry.path} (0.0 .. 8.0]: ${entry.volume}")
                1.0
              }
              medias = medias :+ Video(target.name, dur, curr + gap, start, end, entry.textOpt.nonEmpty, volume, rate)
              entry.textOpt match {
                case Some(text) =>
                  val (sounds, last) = processText(text, curr)
                  medias = medias ++ sounds
                  curr = last
                case _ =>
                  curr = curr + gap + (
                    if (end == 0.0) conversions.R.toZ(conversions.Z.toR(dur) * conversions.F64.toR(rate)) + 1
                    else conversions.R.toZ(conversions.F64.toR(end - start)) + 1)
              }
            case _ =>
              reporter.error(None(), "presentasi", s"Could not load video ${entry.path}")
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
    for (i <- medias.indices) {
      medias(i) match {
        case media: Image => mediaSTs = mediaSTs :+ imageTemplate(media.filename, media.timeline, i, previousTimelineOpt)
        case media: Video => mediaSTs = mediaSTs :+ videoTemplate(media.filename, media.timeline, media.muted,
          media.rate, media.start, media.end, i, previousTimelineOpt)
        case media: Sound => mediaSTs = mediaSTs :+ soundTemplate(media.filename, media.timeline, i, previousTimelineOpt)
      }
      previousTimelineOpt = Some(medias(i).timeline)
    }

    val f = source / s"${spec.name}.java"
    val end: Z = if (medias.size > 0) {
      val last = medias(medias.size - 1)
      last.timeline + last.duration + spec.trailing
    } else {
      0
    }

    f.writeOver(presentasiTemplate(spec.name, spec.trailing, spec.granularity, spec.textVolume, end, mediaSTs).render)
    println(s"Wrote $f")
    return 0
  }

  def text2speech(o: Cli.SireumPresentasiText2speechOption): Z = {

    def maryTTS(javaHome: Os.Path, maryTtsJar: Os.Path, voice: String, input: Os.Path, output: Os.Path): OsProto.Proc.Result = {
      val javaExe: Os.Path = if (Os.isWin) javaHome / "bin" / "java.exe" else javaHome / "bin" / "java"
      return proc"$javaExe -jar $maryTtsJar -o $output -v $voice -i $input".env(ISZ("JAVA_HOME" ~> javaHome.string)).console.runCheck()
    }

    @strictpure def isSoundFile(path: Os.Path): B =
      path.ext == "mp3" || path.ext == "wav" || path.ext == "webm" || path.ext == "ogg"

    @pure def outputFile(output: Os.Path, inputFilename: String, i: Z, line: String, ext: String): Os.Path = {
      var num: String = s"$i-"
      while (num.size < 3) {
        num = s"0$num"
      }
      if (output == Os.cwd) {
        var cis = ISZ[C]()
        for (c <- conversions.String.toCStream(line).take(16)) {
          cis = cis :+ (if (c === '.' || c === ',' || c === '!' || c === ' ' || c === '*' || c === '+') '_' else c)
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
          case Cli.SireumPresentasiText2speechOutputFormat.Pcm => ("raw-48khz-16bit-mono-pcm", "wav")
          case Cli.SireumPresentasiText2speechOutputFormat.Webm => ("webm-24khz-16bit-mono-opus", "webm")
          case Cli.SireumPresentasiText2speechOutputFormat.Ogg => ("ogg-48khz-16bit-mono-opus", "ogg")
        }
        for (line <- inputFile.readLineStream if ops.StringOps(line).trim.size > 0) {
          val out = outputFile(output, inputFile.name, i, line, ext)
          if (!out.exists || o.force) {
            println(s"Synthesizing: $line")
            tmp.writeOver(
              st"""$echoOffOpt
                  |curl --location --request POST 'https://${o.region.get}.tts.speech.microsoft.com/cognitiveservices/v1' --header 'Ocp-Apim-Subscription-Key: $key' --header 'Content-Type: application/ssml+xml' --header 'X-Microsoft-OutputFormat: $format' --header 'User-Agent: curl' --data-raw '<speak version="1.0" xml:lang="${o.lang.get}"><voice xml:lang="${o.voiceLang.get}" xml:gender="${o.gender.get}" name="$voice">${ops.StringOps(line).replaceAllLiterally("'", "'\\''")}</voice></speak>' -o $out""".render)
            tmp.chmod("+x")
            proc"$tmp".console.runCheck()
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
          case Cli.SireumPresentasiText2speechOutputFormat.Pcm => ("pcm", "wav", 16000)
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
            println()
          } else {
            println(s"Skipping already generated: $line")
            println()
          }
          i = i + 1
        }
      case Cli.SireumPresentasiText2speechService.Mary =>
        if (!maryTtsJar.exists) {
          val cacheJar: Os.Path = Os.env("SIREUM_CACHE") match {
            case Some(c) => Os.path(c) / "sireum" / maryTtsJar.name
            case _ => Os.home / "Downloads" / "sireum" / maryTtsJar.name
          }
          maryTtsJar.up.mkdirAll()
          if (!cacheJar.exists) {
            cacheJar.up.mkdirAll()
            println("Please wait while downloading MaryTTS text2wav ...")
            cacheJar.downloadFrom("https://github.com/sireum/rolling/releases/download/marytts-text2wav/txt2wav.jar")
            cacheJar.copyOverTo(maryTtsJar)
          }
          println()
        }
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
}
