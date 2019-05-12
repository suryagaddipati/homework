import Dependencies._

ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "homework"
ThisBuild / organizationName := "homework"
val ScalatraVersion = "2.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "homework",
    libraryDependencies += scalaTest        % Test,
    libraryDependencies += "org.scalactic"  %% "scalactic" % "3.0.5",
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0" % "test",
    libraryDependencies += "org.scalamock" %% "scalamock" % "4.1.0" % Test,

      libraryDependencies ++= Seq (
      "org.scalatra"      %% "scalatra"           % ScalatraVersion,
      "org.scalatra"      %% "scalatra-scalatest" % ScalatraVersion % "test",
      "ch.qos.logback"    % "logback-classic"     % "1.2.3" % "runtime",
      "org.eclipse.jetty" % "jetty-webapp"        % "9.4.9.v20180320" % "runtime",
      "javax.servlet"     % "javax.servlet-api"   % "3.1.0" % "provided",
      "org.scalatra"      %% "scalatra-json"      % ScalatraVersion,
      "org.json4s"        %% "json4s-jackson"     % "3.5.2"
    )
  )
  enablePlugins(ScalatraPlugin)