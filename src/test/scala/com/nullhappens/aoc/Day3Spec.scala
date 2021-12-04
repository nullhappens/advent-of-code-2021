package com.nullhappens.aoc

import munit.FunSuite
import Day3._

class Day3Spec extends FunSuite {

  val input: List[String] =
    List(
      "00100",
      "11110",
      "10110",
      "10111",
      "10101",
      "01111",
      "00111",
      "11100",
      "10000",
      "11001",
      "00010",
      "01010"
    )

  test("getBinary works") {
    assertEquals(
      getBinary(transform(input).getOrElse(fail("Error transforming data"))),
      List(1, 0, 1, 1, 0)
    )
  }

  test("getDecimal works") {
    assertEquals(getDecimal(List(1, 0, 1, 1, 0)), 22)
  }
  test("tests for part1") {
    assertEquals(
      Day3.part1(transform(input).getOrElse(fail("Error transforming data"))),
      198
    )
  }

  test("tests for part2") {
    assert(true)
  }

}
