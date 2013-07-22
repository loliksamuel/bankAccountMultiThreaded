package coursera

object w3 {
  def sum(a: Int, b: Int, fn: Int => Int): Int =
    if(a > b) 0 else fn(a) + sum(a + 1, b, fn)

  // sum of the integers between a and b
  def sumInts(a: Int, b: Int): Int =
    sum(a, b, identity)

  def sumCubes(a: Int, b: Int) :Int = {
    def cube(a: Int): Int = a * a * a

    sum(a, b, cube)
  }

  def sumFactorials(a: Int, b: Int): Int =
    sum(a, b, w2.factorial)

  def main(args: Array[String]) = {
    println(sumInts(1, 10))
    println(sumCubes(1, 10))
    println(sumFactorials(1, 10))
  }
}

// [info] Running coursera.w3
// 55
// 3025
// 4037913
// [success] Total time: 1 s, completed 22 juil. 2013 15:11:55
