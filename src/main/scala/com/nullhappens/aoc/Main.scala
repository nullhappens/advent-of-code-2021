package com.nullhappens.aoc

import cats.effect._
import cats.implicits._
import org.typelevel.log4cats.slf4j.Slf4jLogger
import org.typelevel.log4cats.Logger

object Main extends IOApp.Simple {

  implicit def unsafeLogger = Slf4jLogger.getLogger[IO]

  override def run: IO[Unit] =
    for {
      _ <- Logger[IO].info("Logger works!")
      _ <- IO.println("Printer works!")
      _ <- IO
        .raiseError(new RuntimeException("ERROR HAPPENED"))
        .onError { e =>
          Logger[IO].error(e)("Error being logged")
        }
        .attempt
        .void
    } yield ()

}
