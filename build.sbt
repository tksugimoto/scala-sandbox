val scala2_12 = "2.12.12"
val scala2_13 = "2.13.3"

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.github.tksugimoto"
ThisBuild / organizationName := "tksugimoto"

lazy val root = (project in file("."))
  .settings(
    name := "Scala Sandbox",
    scalaVersion := scala2_13,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % Test,
  )

lazy val main = (project in file("cross-build/main"))
  .settings(scalaVersion := scala2_13)
  .settings(crossScalaVersions := Seq(scala2_12, scala2_13))
  .dependsOn(common)

lazy val common = (project in file("cross-build/common"))
  .settings(scalaVersion := scala2_13)
  .settings(crossScalaVersions := Seq(scala2_12, scala2_13))

lazy val A = (project in file("cross-build/A"))
  .settings(
    scalaVersion := scala2_12,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.7" % Test, // Scala 2.13 非対応
  )
  .dependsOn(main)

lazy val B = (project in file("cross-build/B"))
  .settings(
    scalaVersion := scala2_13,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % Test,
  )
  .dependsOn(main)
