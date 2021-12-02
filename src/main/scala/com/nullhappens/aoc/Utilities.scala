package com.nullhappens.aoc

import cats.effect._
import cats.implicits._
import fs2.io._
import fs2.text

object Utilities {

  def loadSolutionFile[F[_]: Sync](
      fileName: String
  ): fs2.Stream[F, String] =
    readInputStream(
      Sync[F].delay(getClass().getResourceAsStream(fileName)),
      4096,
      true
    ).through(text.utf8.decode)
      .through(text.lines)

  def parseInt(s: String): Option[Int] =
    Either.catchNonFatal(Integer.parseInt(s)).toOption

  def parseLong(s: String): Option[Long] =
    Either.catchNonFatal(java.lang.Long.parseLong(s)).toOption
}
