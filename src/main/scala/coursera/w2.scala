package coursera

import scala.annotation.tailrec

object w2 {

  @tailrec
  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  def factorial(n: Int): Int =
    if (n == 0) 1 else n * factorial (n-1)

  def main(args: Array[String]) = {
    def title(s: String) = println("\n######### " + s + "\n")

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
