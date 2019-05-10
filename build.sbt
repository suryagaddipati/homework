import Dependencies._
resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"


ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "homework",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5",
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0" % "test"


  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.

