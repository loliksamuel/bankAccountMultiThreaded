package coursera

import scala.annotation.tailrec

class Rational(x: Int, y: Int) {
  private val g = w2.gcd(x, y)

  val numer = x / g
  val denom = y / g

  def add(r: Rational): Rational =
    new Rational(r.numer * denom + numer * r.denom, r.denom * denom)

  def neg(): Rational = new Rational(-numer, denom)

  def sub(r: Rational): Rational = add(r.neg)

  def less(r: Rational): Boolean = numer * r.denom < r.numer * denom

  def max(r: Rational): Rational = if (less(r)) r else this

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
    println(y.add(y))
    println(x.less(y))
    println(y.less(x))
    println(x.max(y))
    println(y.max(x))
  }
}

// [info] Running coursera.Rational
// 22/21
// 5/-7
// 0/1
// -79/42
// 10/7
// true
// false
// 5/7
// 5/7
// [success] Total time: 1 s, completed 23 juil. 2013 10:43:08
