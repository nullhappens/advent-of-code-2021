package com.nullhappens.aoc

import munit.FunSuite

class Day1Spec extends FunSuite {

  val input = List(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

  test("tests for part1") {
    assert(Day1.part1(input) == 7)
  }

  test("tests for part2") {
    assert(Day1.part2(input) == 5)
  }
}
