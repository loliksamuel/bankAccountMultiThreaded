package coursera

import math.abs

object w32 {
  val tolerance = 0.0001

  def isCloseEnough(x: Double, y: Double): Boolean =
    abs((x - y) / x) / x < tolerance

  def fixedPoint(f: Double => Double) (firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = averageDamp(f)(guess)
      if(isCloseEnough(guess, next)) next else iterate(next)
    }
    iterate(firstGuess)
  }

  def averageDamp(f: Double => Double)(x: Double) :Double =
    (x + f(x)) / 2

  def main(args: Array[String]) = {
    println(fixedPoint(x => 1 + x/2)(1))
  }
}

// [info] Running coursera.w32
// 1.999755859375
// [success] Total time: 3 s, completed 22 juil. 2013 18:44:11
