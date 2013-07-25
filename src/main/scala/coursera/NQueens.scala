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

    println(queens(4))
  }
}

// [info] Running coursera.sessionNQueens
// Set(List(1, 3, 0, 2), List(2, 0, 3, 1))
// [success] Total time: 21 s, completed 25 juil. 2013 15:36:00
