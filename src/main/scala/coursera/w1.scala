package coursera

import math.abs

object w1 {

  def sqrt(x: Double) = w32.fixedPoint(y => (y + x/y) / 2)(1.0)

  def main(args: Array[String]) = {
    val values = List(2.0, 4.0, 1e-6, 0.001, 0.1e-20, 1.0e20, 1.0e50)

    val squareValues = values.map(v => List(v, sqrt(v)))

    squareValues.foreach(println)
  }
}

// [info] Running coursera.w1
// List(2.0, 1.4142135623746899)
// List(4.0, 2.000000000000002)
// List(1.0E-6, 0.001)
// List(0.001, 0.03162277660168433)
// List(1.0E-21, 3.162277660168379E-11)
// List(1.0E20, 2.5E19)
// List(1.0E50, 2.5E49)
// [success] Total time: 2 s, completed 22 juil. 2013 18:52:31
