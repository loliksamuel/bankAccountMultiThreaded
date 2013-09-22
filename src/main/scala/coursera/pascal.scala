package coursera

object sessionPascal {

  def triangle(xs: List[Int]): List[Int] = {

    def tri(xss: List[Int], acc: List[Int]): List[Int] = xss match {
        case List(x)        => x :: acc
        case (x :: y :: ys) => tri((y :: ys), ((x+y) :: acc))
      }

    tri(xs,  List(1))
  }

  //      (Range(0,n) foldLeft [1]) ((x,_) -> triangle x)

  def triangleRow(n: Int): List[Int] = {
    def tRow(n: Int, r: List[Int]): List[Int] = {
      if (n == 0) r else tRow(n-1, triangle(r))
    }

    tRow(n, List(1))
  }

  def pascal(c: Int, r: Int): Int = triangleRow(r)(c)

  def triangleRows(n: Int) = Range(0,n) map triangleRow

  def pprint(n: Int):Unit = triangleRows(n) map println

  def main(args: Array[String]) = {

    println(pascal(0, 2))
    println(pascal(1, 2))
    println(pascal(1, 3))

    pprint(10)
  }

}

// [info] Running coursera.sessionPascal
// 1
// 2
// 3
// List(1)
// List(1, 1)
// List(1, 2, 1)
// List(1, 3, 3, 1)
// List(1, 4, 6, 4, 1)
// List(1, 5, 10, 10, 5, 1)
// List(1, 6, 15, 20, 15, 6, 1)
// List(1, 7, 21, 35, 35, 21, 7, 1)
// List(1, 8, 28, 56, 70, 56, 28, 8, 1)
// List(1, 9, 36, 84, 126, 126, 84, 36, 9, 1)
// [success] Total time: 1 s, completed 22 sept. 2013 12:00:17
