package coursera

import math.abs

object session {

  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)

  def isGoodEnough(guess: Double, x: Double) =
    abs(guess * guess - x) < 0.001

  def improve(guess: Double, x: Double) =
    (guess + x/guess) / 2

  def sqrt(x: Double) = sqrtIter(1.0, x)

  def main(args: Array[String]) = {
    val values = List(2.0, 4.0, 1e-6, 0.001, 0.1e-20, 1.0e20/*, 1.0e50*/)

    val squareValues = values.map(v => List(v, sqrt(v)))

    squareValues.foreach(println)
  }
}

// [info] Running coursera.session 4
// List(2.0, 1.4142156862745097)
// List(4.0, 2.0000000929222947)
// List(1.0E-6, 0.031260655525445276)
// List(0.001, 0.04124542607499115)
// List(1.0E-21, 0.03125)
// List(1.0E20, 1.0E10)
// sqrt(1.0E20): 1.0E10
// ^D^D  C-c C-c^C
// Process sbt exited abnormally with code 130
