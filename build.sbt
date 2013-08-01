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

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.10.1" % "test"
)
