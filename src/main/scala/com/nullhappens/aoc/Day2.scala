package com.nullhappens.aoc

import cats.effect._
import cats.implicits._

object Day2 extends IOApp.Simple {

  sealed trait Direction
  case object Forward extends Direction
  case object Down extends Direction
  case object Up extends Direction

  final case class Instruction(direction: Direction, value: Int)

  final case class Position(horizontal: Int, depth: Int)

  override def run: IO[Unit] =
    for {
      lines <- Utilities
        .loadSolutionFile[IO]("/day2.txt")
        .evalMap(x => IO.fromEither(parseInstruction(x)))
        .compile
        .toList
      _ <- IO.println(s"solution for part 1: ${part1(lines)}")
      _ <- IO.println(s"solution for part 2: ${part2(lines)}")
    } yield ()

  def parseDirection(s: String): Either[Throwable, Direction] =
    if (s === "forward")
      Right(Forward)
    else if (s === "down")
      Right(Down)
    else if (s === "up")
      Right(Up)
    else
      Left(
        new RuntimeException(
          s"direction $s is not a member of forward | up | down"
        )
      )

  def parseInstruction(
      s: String
  ): Either[Throwable, Instruction] = {
    s.split(" ").toList match {
      case d :: a :: Nil =>
        for {
          direction <- parseDirection(d)
          value <- Utilities.parseInt(a)
        } yield Instruction(direction, value)
      case _ => Left(new RuntimeException(s"input string $s is malformed"))
    }
  }

  def part1(ls: List[Instruction]): Int = {
    def loop(position: Position, ls: List[Instruction]): Position =
      ls match {
        case instruction :: next =>
          instruction.direction match {
            case Forward =>
              loop(
                Position(
                  position.horizontal + instruction.value,
                  position.depth
                ),
                next
              )
            case Down =>
              loop(
                Position(
                  position.horizontal,
                  position.depth + instruction.value
                ),
                next
              )
            case Up =>
              loop(
                Position(
                  position.horizontal,
                  position.depth - instruction.value
                ),
                next
              )
          }
        case Nil => position
      }
    val finalPosition = loop(Position(0, 0), ls)
    finalPosition.horizontal * finalPosition.depth
  }

  def part2(ls: List[Instruction]): Int = ls.length

}
