package coursera

import scala.annotation.tailrec

class Rational(val numer: Int, val denom: Int) {
  require(denom != 0, "denominator must be non zero")

  def this(numer: Int) = this(numer, 1)

  def +(r: Rational): Rational =
    new Rational(r.numer * denom + numer * r.denom, r.denom * denom)

  def unary_-(): Rational = new Rational(-numer, denom)

  def -(r: Rational): Rational = this + -r

  def < (r: Rational): Boolean = numer * r.denom < r.numer * denom

  def max(r: Rational): Rational = if (this < r) r else this

  override def toString(): String = {
    val g = sessionTailRecursiveOrNot.gcd(numer, denom)
    numer / g + "/" + denom / g
  }
}

object Rational {
  def main(args: Array[String]) = {
    val x = new Rational(1, 3)
    val y = new Rational(5, 7)
    val z = new Rational(3, 2)

    println(x + y)
    println(-y)
    println(x + x)
    println(x - y - z)
    println(y + y)
    println(x < y)
    println(y < x)
    println(x max y)
    println(y max x)

    val a = new Rational(2)
    println(a)
  }
}

// [info] Running coursera.Rational
// 22/21
// 5/-7
// 2/3
// -79/42
// 10/7
// true
// false
// 5/7
// 5/7
// 2/1
// [success] Total time: 3 s, completed 23 juil. 2013 14:32:46
