package com.nullhappens.aoc

import munit.FunSuite
import Day6._

class Day6Spec extends FunSuite {

  val input = transform("3,4,3,1,2".split(",").toList)
    .getOrElse(fail("Error transforming input data"))

  test("tick works") {
    assertEquals(tick(input, 0, 18).length, 26)
    assertEquals(tick(input, 0, 80).length, 5934)
  }

}
