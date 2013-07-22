package coursera

import scala.annotation.tailrec

object w3 {
  def sum(fn: Int => Int) (a: Int, b: Int): Int = {

    @tailrec
    def sumTR(a: Int, r: Int): Int =
      if(a > b) r else sumTR(a + 1, fn(a) + r)

    sumTR(a, 0)
  }

  def main(args: Array[String]) = {
    val values = List(Tuple2(1, 10),
                      Tuple2(10, 20))

    val functions : Map[String, (Int, Int) => Int] =
      Map("sumInts"       -> sum(identity),
          "sumCubes"      -> sum(x => x * x * x),
          "sumFactorials" -> sum(w2.factorial))

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

// [success] Total time: 1 s, completed 22 juil. 2013 16:13:13
