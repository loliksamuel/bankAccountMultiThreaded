package coursera

object sessionNQueens {
  def main(args: Array[String]) = {

    def queens(n: Int): Set[List[Int]] = {

      def isSafe(col: Int, queens: List[Int]): Boolean = {
        val row = queens.length
        val queensWithRow = (row - 1 to 0 by -1) zip queens
        queensWithRow forall {
          case (r, c) => col != c && math.abs(col - c) != row - r
        }
      }

      def placeQueens(k: Int): Set[List[Int]] =
        if (k == 0) Set(Nil)
        else
          for {
            queens <- placeQueens(k - 1)
            col <- 0 until n
            if isSafe(col, queens)
          } yield col :: queens

      placeQueens(n)
    }

    def show(queens: List[Int]) = {
      val lines = for (col <- queens.reverse)
                  yield Vector.fill(queens.length)("* ").updated(col, "X "). mkString
      "\n" + (lines mkString "\n")
    }

    println(queens(4) map show mkString "\n")
  }
}

// [info] Running coursera.sessionNQueens

// * * X *
// X * * *
// * * * X
// * X * *

// * X * *
// * * * X
// X * * *
// * * X *
// [success] Total time: 1 s, completed 25 juil. 2013 15:42:20
