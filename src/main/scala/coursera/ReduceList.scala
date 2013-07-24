object sessionReduceList {
  def main(args: Array[String]) = {
    def sumList(xs: List[Int]): Int = xs match {
        case Nil     => 0
        case y :: ys => y + sumList(ys)
      }

    val elems = List(1, 3, 5, 7)
    println(sumList(elems))
  }
}

// [info] Running sessionReduceList
// 16
// [success] Total time: 5 s, completed 24 juil. 2013 12:24:33
