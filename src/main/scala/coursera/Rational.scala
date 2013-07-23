package coursera

class Rational(x: Int, y: Int) {
  def numer = x
  def denom = y

  override def toString(): String = "{n: " + numer + ", d: " + denom + "}"
}

object Rational {
  def main(args: Array[String]) = {
    val r = new Rational(1, 2)

    println(r)
  }
}
