package com.nullhappens.aoc

import cats.effect._
import cats.implicits._
import cats.effect.IOApp
import scala.annotation.tailrec

object Day6 extends IOApp.Simple {

  override def run: IO[Unit] =
    for {
      lines <- Utilities
        .loadSolutionFile[IO]("/day6.txt")
        .compile
        .toList
      fish <- IO.fromEither(
        transform(lines)
      )
      _ <- IO.println(s"solution for part 1: ${part1(fish)}")
      _ <- IO.println(s"solution for part 2: ${part2(fish)}")
    } yield ()

  def transform(input: List[String]): Either[Throwable, List[Int]] =
    input.flatTraverse(s => s.split(",").toList.traverse(Utilities.parseInt))

  @tailrec
  def tick(pond: List[Int], iter: Int, target: Int): List[Int] = {
    println(s"iter: ${iter}")
    if (iter === target)
      pond
    else {
      val newFish = (0 until pond.count(_ === 0)).map(_ => 8).toList
      val newPond = pond.map { x =>
        if (x <= 0) {
          6
        } else
          x - 1
      }
      tick(newPond ::: newFish, iter + 1, target)
    }
  }

  def part1(ls: List[Int]): Int =
    tick(ls, 0, 80).length

  def part2(ls: List[Int]): Int =
    tick(ls, 0, 256).length

}
