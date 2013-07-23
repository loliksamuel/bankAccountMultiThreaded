package coursera

class Rational(x: Int, y: Int) {
  def numer = x
  def denom = y

  def add(r: Rational): Rational =
    new Rational(r.numer * denom + numer * r.denom, r.denom * denom)

  def neg(): Rational = new Rational(-numer, denom)

  override def toString(): String = numer + "/" + denom
}

object Rational {
  def main(args: Array[String]) = {
    val r0 = new Rational(1, 2)
    val r1 = new Rational(2, 3)

    println(r0.add(r1))
    println(r0.neg)

  }
}

// [info] Running coursera.Rational
// 7/6
// -1/2
// [success] Total time: 1 s, completed 23 juil. 2013 09:55:13
