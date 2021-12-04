package com.nullhappens.aoc

import munit.FunSuite
import Day3._

class Day3Spec extends FunSuite {

  val input =
    transform(
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
    ).getOrElse(fail("Error transforming data"))

  test("getVerticalSlice works") {
    assertEquals(
      getVerticalSlice(0, input),
      List(0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0),
      0
    )
    assertEquals(
      getVerticalSlice(1, input),
      List(0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1),
      1
    )
    assertEquals(
      getVerticalSlice(2, input),
      List(1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0),
      2
    )
    assertEquals(
      getVerticalSlice(3, input),
      List(0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1),
      3
    )
    assertEquals(
      getVerticalSlice(4, input),
      List(0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0),
      4
    )
  }

  test("getEpsilon works") {
    assertEquals(
      getEpsilon(input),
      List(1, 0, 1, 1, 0)
    )
  }

  test("getDecimal works") {
    assertEquals(getDecimal(List(1, 0, 1, 1, 0)), 22)
  }

  test("getOxygen works") {
    assertEquals(getOxygen(input).mkString, "10111")
  }

  test("getCO2 works") {
    println("******* CO2")
    assertEquals(getCO2(input).mkString, "01010")
  }

  test("tests for part1") {
    assertEquals(
      Day3.part1(input),
      198
    )
  }

  test("tests for part2") {
    assert(true)
  }

}
