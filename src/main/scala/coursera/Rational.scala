package coursera

class Rational(x: Int, y: Int) {
  def numer = x
  def denom = y
}

object Rational {
  def main(args: Array[String]) = {
    val r = new Rational(1, 2)

    println(r)
  }
}
