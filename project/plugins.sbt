ivyLoggingLevel := UpdateLogging.Quiet
Compile / scalacOptions ++= Seq("-feature", "-deprecation", "-Xfatal-warnings")

classpathTypes += "maven-plugin"

addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.1.20")
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.9.33")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.4")
