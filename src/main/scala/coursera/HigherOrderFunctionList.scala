object sessionHOFList {
  def main(args: Array[String]) = {
    val elems = List(1.0, 1.5, 2.9, 4.5)
    println(elems map (x => x * 0.5))

    val ilems = List(1, 3, 5, 7)
    println(ilems map (x => x * x))
  }
}

// [info] Running sessionHOFList
// List(0.5, 0.75, 1.45, 2.25)
// List(1, 9, 25, 49)
// List(1, 9, 25, 49)
// [success] Total time: 3 s, completed 24 juil. 2013 11:26:25
