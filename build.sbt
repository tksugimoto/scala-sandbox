ThisBuild / scalaVersion := "2.13.3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.github.tksugimoto"
ThisBuild / organizationName := "tksugimoto"

val AkkaVersion = "2.6.10"

lazy val root = (project in file("."))
  .settings(
    name := "Scala Sandbox",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.2" % Test,
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe.akka" %% "akka-persistence-cassandra" % "1.0.4",
      "com.typesafe.akka" %% "akka-persistence-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-persistence-query" % AkkaVersion,
      "com.typesafe.akka" %% "akka-cluster-tools" % AkkaVersion,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
      "io.altoo" %% "akka-kryo-serialization" % "1.1.5",
    ),
  )
