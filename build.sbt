ThisBuild / scalaVersion := "2.13.3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.github.tksugimoto"
ThisBuild / organizationName := "tksugimoto"

lazy val root = (project in file("."))
  .settings(
    name := "Scala Sandbox",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % Test
  )
