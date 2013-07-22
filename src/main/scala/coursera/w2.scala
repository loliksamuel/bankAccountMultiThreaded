package coursera

import scala.annotation.tailrec

object w2 {

  @tailrec
  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  def factorial(n: Int): Int = {
    @tailrec
    def fact(n: Int, r: Int): Int =
      if (n == 0) r else fact(n-1, n*r)

    fact(n, 1)
  }

  def title(s: String) = println("\n######### " + s + "\n")

  def main(args: Array[String]) = {

    title("gcd")
    val values = List(Tuple2(2, 4),
                      Tuple2(14, 21),
                      Tuple2(3, 99),
                      Tuple2(8, 1024),
                      Tuple2(1, 119))
    (for ((a, b) <- values) println("gcd(" + a + ", " + b + ") = " + gcd(a, b)))

    title("factorial")

    val fvalues = List(1,2,3,4,5,6,7,8,9)

    fvalues.map(factorial).map(println)

    title("end")
  }
}
