import sbt.Keys._

name := """pagelets-seed"""

version := "0.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala).
  settings(Seq(
    scalaVersion := "2.11.8",
    routesImport += "org.splink.pagelets.Binders._"),
    libraryDependencies ++= Seq(
      cache,
      ws,
      "org.splink" %% "pagelets" % "0.0.2",
      "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
      "org.mockito" % "mockito-core" % "1.10.19" % Test,
      "org.webjars.bower" % "bootstrap" % "3.3.7",
      "org.webjars" % "jquery" % "3.1.1"
    )
  )

LessKeys.compress in Assets := true
LessKeys.optimization in Assets := 100

includeFilter in uglify := GlobFilter("*.js")
// also minify the bootstrap javascript files
excludeFilter in uglify := new SimpleFileFilter(f =>
  !f.getPath.contains("lib/bootstrap/js") &&
    !f.getPath.contains("assets/javascripts"))

includeFilter in(Assets, LessKeys.less) := "*.less"
excludeFilter in(Assets, LessKeys.less) := "_*.less"

pipelineStages in Assets := Seq(uglify)

