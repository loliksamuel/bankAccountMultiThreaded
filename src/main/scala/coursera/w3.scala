package coursera

object w3 {
  // sum of the integers between a and b
  def sumInts(a: Int, b: Int): Int =
    if(a > b) 0 else a + sumInts(a + 1, b)

  def sumCubes(a: Int, b: Int) :Int = {
    def cube(a: Int): Int = a * a * a

    def sum(a: Int, b: Int): Int =
      if(a > b) 0 else cube(a) + sum(a + 1, b)

    sum(a, b)
  }

  def sumFactorials(a: Int, b: Int): Int =
    if(a > b) 0 else w2.factorial(a) + sumFactorials(a + 1, b)

  def main(args: Array[String]) = {
    println(sumInts(1, 10))
    println(sumCubes(1, 10))
    println(sumFactorials(1, 10))
  }
}
