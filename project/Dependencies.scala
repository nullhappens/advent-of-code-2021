import sbt._

object Dependencies {
  val scalaTest = "org.scalatest" %% "scalatest" % "3.2.9"
  val scalafixOrganizeImports =
    "com.github.liancheng" %% "organize-imports" % "0.4.0"

  val log4Cats = "org.typelevel" %% "log4cats-slf4j" % "2.1.1"

  object FS2 {
    private val fs2Version = "3.2.2"
    val core = "co.fs2" %% "fs2-core" % fs2Version
    val io = "co.fs2" %% "fs2-io" % fs2Version
  }

  object Compiler {
    val kindProjector =
      ("org.typelevel" %% "kind-projector" % "0.13.2").cross(CrossVersion.full)
    val contextApplied = "org.augustjune" %% "context-applied" % "0.1.4"
  }

  object log4j2 {
    private val log4j2Version = "2.14.1"
    val core = "org.apache.logging.log4j" % "log4j-core" % log4j2Version
    val api = "org.apache.logging.log4j" % "log4j-api" % log4j2Version
    val slf4j = "org.apache.logging.log4j" % "log4j-slf4j-impl" % log4j2Version
  }
}
