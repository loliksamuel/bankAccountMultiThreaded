object sessionReduceList {
  def main(args: Array[String]) = {
    def sum(xs: List[Int]): Int = (xs foldLeft 0) (_ + _)
    def pdt(xs: List[Int]): Int = (xs foldLeft 1) (_ * _)

    val elems = List(1, 3, 5, 7)
    println(sum(elems))
    println(pdt(elems))
  }
}

// [info] Running sessionReduceList
// 16
// 105
// [success] Total time: 1 s, completed 24 juil. 2013 12:31:35
