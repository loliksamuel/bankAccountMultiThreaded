package coursera

import math.abs

object sessionSqrt {

  def sqrtStream(x: Double): Stream[Double] = {
    def improve(guess: Double) = (guess + x / guess) / 2
    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses
  }

  def isGoodEnough(guess: Double, x: Double): Boolean =
    abs((guess * guess - x) / x) < 0.0001

  def sqrt(x: Double) = FixedPointCompute.fixedPoint(y => x/y)(1.0)

  def main(args: Array[String]) = {
    val values = List(2.0, 4.0, 1e-6, 0.001, 0.1e-20, 1.0e20, 1.0e50)

    val squareValues = values.map(v => List(v, sqrt(v)))

    squareValues.foreach(println)

    println(sqrtStream(4) take(10) toList)
    println(sqrtStream(4) filter(isGoodEnough(_, 4)) take(10) toList)
  }
}

// [info] Running coursera.sessionSqrt
// List(2.0, 1.4142135623746899)
// List(4.0, 2.000000000000002)
// List(1.0E-6, 0.001)
// List(0.001, 0.03162277660168433)
// List(1.0E-21, 3.162277660168379E-11)
// List(1.0E20, 2.5E19)
// List(1.0E50, 2.5E49)
// List(1.0, 2.5, 2.05, 2.000609756097561, 2.0000000929222947, 2.000000000000002, 2.0, 2.0, 2.0, 2.0)
// List(2.0000000929222947, 2.000000000000002, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0)
// [success] Total time: 1 s, completed 26 juil. 2013 10:53:15
