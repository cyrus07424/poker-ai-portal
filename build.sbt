name := """poker-ai-portal"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.8"

crossScalaVersions := Seq("2.11.12", "2.12.8")

pipelineStages := Seq(digest)

includeFilter in(Assets, LessKeys.less) := "*.less"

sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

// Libraries
libraryDependencies ++= Seq(
  guice,
  filters,
  ehcache,
  javaJdbc,
  javaWs,
  "org.postgresql" % "postgresql" % "42.2.5",
  "com.typesafe.play" %% "play-mailer" % "6.0.1",
  "com.typesafe.play" %% "play-mailer-guice" % "6.0.1",
  "org.mindrot" % "jbcrypt" % "0.4",
  "org.webjars" %% "webjars-play" % "2.6.3",
  "org.webjars" % "jquery" % "3.3.1-2",
  "org.webjars.bower" % "github-com-raryosu-Rin" % "v3.3.7-2",
  "org.webjars" % "font-awesome" % "5.6.1",
)

// Testing libraries for dealing with CompletionStage...
libraryDependencies ++= Seq(
  "org.assertj" % "assertj-core" % "3.6.2" % Test,
  "org.awaitility" % "awaitility" % "2.0.0" % Test,
)

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

// Imports for views
TwirlKeys.templateImports ++= Seq(
  "play.api.i18n._",
  "views.html.helper._",
  "constants._",
  "utils._"
)