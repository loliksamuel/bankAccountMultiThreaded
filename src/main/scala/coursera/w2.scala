package coursera

object w2 {

  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  def main(args: Array[String]) = {
    val values = List(Tuple2(2, 4),
                      Tuple2(14, 21),
                      Tuple2(3, 99),
                      Tuple2(8, 1024),
                      Tuple2(1, 119))
    (for ((a, b) <- values) println("gcd(" + a + ", " + b + ") = " + gcd(a, b)))
  }
}
