package com.nullhappens.aoc

import munit.FunSuite
import Day2._
import cats.implicits._

class Day2Spec extends FunSuite {

  val input =
    List("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")

  val expected =
    List(
      Instruction(Forward, 5),
      Instruction(Down, 5),
      Instruction(Forward, 8),
      Instruction(Up, 3),
      Instruction(Down, 8),
      Instruction(Forward, 2)
    )

  test("parseInstructions works properly") {
    input
      .traverse(s => Day2.parseInstruction(s))
      .map(l => assertEquals(l, expected))
      .getOrElse(fail("FAILURE"))
  }

  test("tests for part1") {
    assertEquals(Day2.part1(expected), 150)
  }

  test("tests for part2") {
    assert(true)
  }

}
