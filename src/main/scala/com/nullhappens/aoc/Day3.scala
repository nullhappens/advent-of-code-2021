package com.nullhappens.aoc

import cats.effect._
import cats.implicits._
import scala.collection.mutable.ListBuffer

object Day3 extends IOApp.Simple {

  override def run: IO[Unit] =
    for {
      lines <- Utilities
        .loadSolutionFile[IO]("/day3.txt")
        .compile
        .toList
      matrix <- IO.fromEither(transform(lines))
      _ <- IO.println(s"solution for part 1: ${part1(matrix)}")
      _ <- IO.println(s"solution for part 2: ${part2(matrix)}")
    } yield ()

  def transform(ls: List[String]): Either[Throwable, Array[Array[Int]]] =
    ls.map(_.split("").toList.traverse(Utilities.parseInt).map(_.toArray))
      .sequence
      .map(_.toArray)

  def getBinary(mx: Array[Array[Int]]): List[Int] = {
    val number = new ListBuffer[Int]()

    for (i <- 0 until mx(0).length) {
      var zeroes = 0 // scalafix: ok
      var ones = 0 // scalafix: ok
      for (j <- 0 until mx.length) {
        if (mx(j)(i) === 0)
          zeroes += 1
        else
          ones += 1
      }
      if (zeroes > ones)
        number += 0
      else
        number += 1
    }
    number.toList
  }

  def getDecimal(xs: List[Int]): Int =
    xs.reverse.zipWithIndex.map { case (value, exp) =>
      value * Math.pow(2, exp.doubleValue()).intValue()
    }.sum

  def part1(ls: Array[Array[Int]]): Int = {
    val epsilon = getBinary(ls)
    val gamma = epsilon.map {
      case x if x === 0 => 1
      case x if x === 1 => 0
    }
    getDecimal(epsilon) * getDecimal(gamma)
  }

  def part2(ls: Array[Array[Int]]): Int = ls.length

}
