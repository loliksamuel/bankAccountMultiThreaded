package coursera

class Rational(x: Int, y: Int) {
  def numer = x
  def denom = y

  def add(r: Rational): Rational =
    new Rational(r.numer * denom + numer * r.denom, r.denom * denom)

  def neg(): Rational = new Rational(-numer, denom)

  def sub(r: Rational): Rational = add(r.neg)

  override def toString(): String = numer + "/" + denom
}

object Rational {
  def main(args: Array[String]) = {
    val x = new Rational(1, 3)
    val y = new Rational(5, 7)
    val z = new Rational(3, 2)

    println(x.add(y))
    println(y.neg)
    println(x.sub(x))

    println(x.sub(y).sub(z))
  }
}

// [info] Running coursera.Rational
// 7/6
// -1/2
// [success] Total time: 1 s, completed 23 juil. 2013 09:55:13
