package coursera

import scala.annotation.tailrec

object sessionFold {
  def fold(fn: Int => Int, op: (Int, Int) => Int, initial: Int)(a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, r: Int): Int =
      if(a > b) r else loop(a + 1, op(fn(a), r))

    loop(a, initial)
  }

  def sum(fn: Int => Int) (a: Int, b: Int): Int = fold(fn, (x, y) => x + y, 0)(a, b)
  def pdt(fn: Int => Int) (a: Int, b: Int): Int = fold(fn, (x, y) => x * y, 1)(a, b)

  def factorial(n: Int) = pdt(identity)(1, n)

  def main(args: Array[String]) = {
    def title(s: String) = println("\n######### " + s + "\n")
    title("sum of a function values (cube, factorial, etc...)")

    val values = List(Tuple2(1, 10),
                      Tuple2(10, 20))

    val functions : Map[String, (Int, Int) => Int] =
      Map("sumInts"       -> sum(identity),
          "sumCubes"      -> sum(x => x * x * x),
          "sumFactorials" -> sum(factorial))

    def displayResult(fnName: String, a: Int, b: Int, r: Int): String =
      fnName + "(" + a + ", " + b + ") = " + r

    functions.foreach { case (fnName, fn) =>
      (for ((a, b) <- values)
         println(displayResult(fnName, a, b, fn(a, b))))
    }

    title("factorial")

    val fvalues = List(1,2,3,4,5,6,7,8,9)

    fvalues.map(factorial).map(println)

    title("end")
  }
}

// ######### sum of a function values (cube, factorial, etc...)

// sumInts(1, 10) = 55
// sumInts(10, 20) = 165
// sumCubes(1, 10) = 3025
// sumCubes(10, 20) = 42075
// sumFactorials(1, 10) = 4037913
// sumFactorials(10, 20) = 267631616

// ######### factorial

// 1
// 2
// 6
// 24
// 120
// 720
// 5040
// 40320
// 362880

// ######### end

// [success] Total time: 1 s, completed 22 juil. 2013 18:19:56
