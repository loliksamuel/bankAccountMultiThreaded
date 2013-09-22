package coursera

object sessionPascal {

  def pascal(c: Int, r: Int): Int = {

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

    triangleRow(r)(c)
  }

  def main(args: Array[String]) = {
    println(pascal(0, 2))
    println(pascal(1, 2))
    println(pascal(1, 3))
  }

}
