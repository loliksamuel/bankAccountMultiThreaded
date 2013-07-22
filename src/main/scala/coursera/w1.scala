package coursera

import math.abs

object w1 {

  def sqrt(x: Double) = {
    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double) =
      abs(guess * guess - x) / x < 0.001

    def improve(guess: Double) =
      (guess + x/guess) / 2

    sqrtIter(1.0)
  }

  def main(args: Array[String]) = {
    val values = List(2.0, 4.0, 1e-6, 0.001, 0.1e-20, 1.0e20, 1.0e50)

    val squareValues = values.map(v => List(v, sqrt(v)))

    squareValues.foreach(println)
  }
}

// [info] Running coursera.session
// List(2.0, 1.4142156862745097)
// List(4.0, 2.000609756097561)
// List(1.0E-6, 0.0010000001533016628)
// List(0.001, 0.03162278245070105)
// List(1.0E-21, 3.1633394544890125E-11)
// List(1.0E20, 1.0000021484861237E10)
// List(1.0E50, 1.0000003807575104E25)
// [success] Total time: 1 s, completed 22 juil. 2013 12:08:33
