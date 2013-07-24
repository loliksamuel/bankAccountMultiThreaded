object sessionHOFList {
  def main(args: Array[String]) = {
    def scaleList(xs: List[Double], factor: Double): List[Double] = xs map (x => x * factor)

    val elems = List(1.0, 1.5, 2.9, 4.5)
    println(scaleList(elems, 0.5))
  }
}

// [info] Running sessionHOFList
// List(0.5, 0.75, 1.45, 2.25)
// [success] Total time: 3 s, completed 24 juil. 2013 11:20:42
