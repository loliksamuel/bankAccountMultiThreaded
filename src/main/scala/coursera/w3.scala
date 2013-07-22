package coursera

object w3 {
  def sum(a: Int, b: Int, fn: Int => Int): Int =
    if(a > b) 0 else fn(a) + sum(a + 1, b, fn)

  // sum of the integers between a and b
  def sumInts(a: Int, b: Int): Int =
    sum(a, b, identity)

  def sumCubes(a: Int, b: Int) :Int =
    sum(a, b, x => x * x * x)

  def sumFactorials(a: Int, b: Int): Int =
    sum(a, b, w2.factorial)

  def main(args: Array[String]) = {
    val values = List(Tuple2(1, 10),
                      Tuple2(10, 20))

    val functions : Map[String, (Int, Int) => Int] =
      Map("sumInts"       -> sumInts,
          "sumCubes"      -> sumCubes,
          "sumFactorials" -> sumFactorials)

    def displayResult(fnName: String, a: Int, b: Int, r: Int): String =
      fnName + "(" + a + ", " + b + ") = " + r

    functions.foreach { case (fnName, fn) =>
      (for ((a, b) <- values)
         println(displayResult(fnName, a, b, fn(a, b))))
    }

    w2.title("end")
  }
}

// [info] Running coursera.w3
// sumInts(1, 10) = 55
// sumInts(10, 20) = 165
// sumCubes(1, 10) = 3025
// sumCubes(10, 20) = 42075
// sumFactorials(1, 10) = 4037913
// sumFactorials(10, 20) = 267631616

// ######### end

// [success] Total time: 0 s, completed 22 juil. 2013 15:40:33
