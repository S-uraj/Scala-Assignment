name := "scala_assignment"

version := "0.1"

scalaVersion := "2.13.7"


libraryDependencies++=Seq(
  "com.github.tototoshi" %% "scala-csv" % "1.3.10",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)
