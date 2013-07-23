package coursera

class Rational(x: Int, y: Int) {
  def numer = x
  def denom = y

  def add(r: Rational): Rational =
    new Rational(r.numer * denom + numer * r.denom, r.denom * denom)

  def makeString(r: Rational): String = r.numer + "/" + r.denom

  override def toString(): String = makeString(this)
}

object Rational {
  def main(args: Array[String]) = {
    val r0 = new Rational(1, 2)
    val r1 = new Rational(2, 3)

    println(r0.add(r1))
  }
}

// [info] Running coursera.Rational
// 7/6
// [success] Total time: 2 s, completed 23 juil. 2013 09:52:37
