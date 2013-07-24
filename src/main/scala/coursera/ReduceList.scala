object sessionReduceList {
  def main(args: Array[String]) = {
    def sum(xs: List[Int]): Int = (0 :: xs) reduceLeft (_ + _)
    def pdt(xs: List[Int]): Int = (1 :: xs) reduceLeft (_ * _)

    val elems = List(1, 3, 5, 7)
    println(sum(elems))
    println(pdt(elems))
  }
}

// [info] Running sessionReduceList
// 16
// 105
// [success] Total time: 0 s, completed 24 juil. 2013 12:29:44
