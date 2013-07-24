object sessionHOFList {
  def main(args: Array[String]) = {
    def squareList(xs: List[Int]): List[Int] = xs match {
      case Nil     => xs
      case y :: ys => y * y :: squareList(ys)
    }

    def mapSquare(xs: List[Int]): List[Int] = xs map (x => x * x)

    val elems = List(1.0, 1.5, 2.9, 4.5)
    val elemsMap = elems map (x => x * 0.5)

    println(elemsMap)

    val ilems = List(1, 3, 5, 7)

    println(squareList(ilems))
    println(mapSquare(ilems))
  }
}

// [info] Running sessionHOFList
// List(0.5, 0.75, 1.45, 2.25)
// List(1, 9, 25, 49)
// List(1, 9, 25, 49)
// [success] Total time: 3 s, completed 24 juil. 2013 11:26:25
