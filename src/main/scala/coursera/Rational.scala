package coursera

import scala.annotation.tailrec

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be non zero")

  def this(x: Int) = this(x, 1)

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

    val a = new Rational(2)
    println(a)
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
// [error] (run-main) java.lang.IllegalArgumentException: requirement failed: denominator must be non zero
// java.lang.IllegalArgumentException: requirement failed: denominator must be non zero
// 	at scala.Predef$.require(Predef.scala:214)
// 	at coursera.Rational.<init>(Rational.scala:6)
// 	at coursera.Rational$.main(Rational.scala:42)
// 	at coursera.Rational.main(Rational.scala)
// 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
// 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
// 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
// 	at java.lang.reflect.Method.invoke(Method.java:606)
// [trace] Stack trace suppressed: run last compile:run for the full output.
// java.lang.RuntimeException: Nonzero exit code: 1
// 	at scala.sys.package$.error(package.scala:27)
// [trace] Stack trace suppressed: run last compile:run for the full output.
// [error] (compile:run) Nonzero exit code: 1
// [error] Total time: 1 s, completed 23 juil. 2013 10:47:01
