import AssemblyKeys._

assemblySettings

name := "example-java-parallel-helloworld"

organization := "org.sample"

libraryDependencies ++= Seq(
  "io.prediction" %% "core" % "0.8.5" % "provided",
  "org.apache.spark" %% "spark-core" % "1.2.0" % "provided")
