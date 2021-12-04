package com.nullhappens.aoc

import cats.effect._
import cats.implicits._
import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec

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

  def getEpsilon(mx: Array[Array[Int]]): List[Int] = {
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

  def getGamma(epsilon: List[Int]): List[Int] =
    epsilon.map {
      case x if x === 0 => 1
      case x if x === 1 => 0
    }

  def getDecimal(xs: List[Int]): Int =
    xs.reverse.zipWithIndex.map { case (value, exp) =>
      value * Math.pow(2, exp.doubleValue()).intValue()
    }.sum

  def getVerticalSlice(pos: Int, mx: Array[Array[Int]]): List[Int] = {
    val slice = new ListBuffer[Int]()
    for (j <- 0 until mx.length) {
      slice += mx(j)(pos)
    }
    slice.toList
  }

  def filterValues(mx: Array[Array[Int]], pos: Int, value: Int) =
    mx.filter { xs =>
      xs(pos) === value
    }

  def filterMatrix(mx: Array[Array[Int]], f: (Int, Int) => Int) = {
    @tailrec
    def loop(mx: Array[Array[Int]], pos: Int): List[Int] = {
      if (mx.length === 1)
        mx(0).toList
      else {
        val slice = getVerticalSlice(pos, mx)
        val zeroes = slice.count(_ === 0)
        val ones = slice.count(_ === 1)
        loop(filterValues(mx, pos, f(zeroes, ones)), pos + 1)
      }
    }
    loop(mx, 0)
  }

  def getOxygen(mx: Array[Array[Int]]): List[Int] =
    filterMatrix(mx, (z, o) => if (o >= z) 1 else 0)

  def getCO2(mx: Array[Array[Int]]): List[Int] =
    filterMatrix(mx, (z, o) => if (z <= o) 0 else 1)

  def part1(ls: Array[Array[Int]]): Int = {
    val epsilon = getEpsilon(ls)
    val gamma = getGamma(epsilon)
    getDecimal(epsilon) * getDecimal(gamma)
  }

  def part2(ls: Array[Array[Int]]): Int = {
    val o2 = getOxygen(ls)
    val co2 = getCO2(ls)
    getDecimal(o2) * getDecimal(co2)
  }

}
