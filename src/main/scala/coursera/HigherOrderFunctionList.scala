object sessionHOFList {
  def main(args: Array[String]) = {

    // filter only elements that are strictly positive
    def posElems(xs: List[Int]): List[Int] = xs match {
        case Nil     => Nil
        case y :: ys => if (y > 0) y :: posElems(ys)
                        else       posElems(ys)
      }

    val elems = List(1.0, 1.5, 2.9, 4.5)
    println(elems map (x => x * 0.5))

    val ilems = List(1, 3, 5, 7)
    println(ilems map (x => x * x))

    val nlems = List(-1, 1, 10, -2, 45)
    println(posElems(nlems))
  }
}

// [info] Running sessionHOFList
// List(0.5, 0.75, 1.45, 2.25)
// List(1, 9, 25, 49)
// List(1, 10, 45)
// [success] Total time: 2 s, completed 24 juil. 2013 11:32:34
