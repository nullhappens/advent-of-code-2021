package com.nullhappens.aoc

import cats.effect._
import cats.implicits._

object Day1 extends IOApp.Simple {

  override def run: IO[Unit] =
    for {
      lines <- Utilities
        .loadSolutionFile[IO]("/day1.txt")
        .filter(_.length() > 0)
        .evalMap(x => IO.fromEither(parseInt(x)))
        .compile
        .toList
      _ <- IO.println(s"solution for part 1: ${part1(lines)}")
      _ <- IO.println(s"solution for part 2: ${part2(lines)}")
    } yield ()

  def parseInt(s: String): Either[Throwable, Int] =
    Either.catchNonFatal(Integer.parseInt(s))

  def part1(ls: List[Int]): Int =
    ls
      .sliding(2)
      .toList
      .map(ls => ls.reduceLeft { (x, y) => y - x })
      .filter(_ > 0)
      .length

  def part2(ls: List[Int]): Int =
    part1(ls.sliding(3).map(_.sum).toList)

}
