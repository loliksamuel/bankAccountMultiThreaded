addCommandAlias("c", "compile")

addCommandAlias("t", "test")

addCommandAlias("tc", "test:compile")

addCommandAlias("r", "run")

name := "scala-lab"

version := "1.0"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.10.2"

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.10.1" % "test",
  "org.scala-lang.modules" %% "scala-async" % "0.9.0-M2"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "2.0.M8" % "test"

libraryDependencies += "junit" % "junit" % "4.11" % "test"
