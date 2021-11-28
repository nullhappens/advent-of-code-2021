import sbt._
import Dependencies._

ThisBuild / scalafixDependencies += scalafixOrganizeImports

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.nullhappens"
ThisBuild / organizationName := "nullhappens"

lazy val root = (project in file("."))
  .settings(
    name := "Advent of Code 2021",
    fork := true,
    addCompilerPlugin(Compiler.kindProjector),
    addCompilerPlugin(Compiler.contextApplied),
    addCommandAlias("cpl", "compile"),
    addCommandAlias("fmt", "; scalafmtSbt; compile:scalafmt"),
    addCommandAlias("check", "; scalafmtSbtCheck; compile:scalafmtCheck"),
    addCommandAlias("lint", "; compile:scalafix --check"),
    addCommandAlias("fix", "; compile:scalafix; test:scalafix"),
    libraryDependencies ++= Seq(
      FS2.core,
      FS2.io,
      log4Cats,
      log4j2.core,
      log4j2.api,
      log4j2.slf4j,
      scalaTest % Test
    )
  )

