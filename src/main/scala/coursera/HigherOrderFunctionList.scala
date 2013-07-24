object sessionHOFList {
  def main(args: Array[String]) = {
    val elems = List(1.0, 1.5, 2.9, 4.5)
    val elemsMap = elems map (x => x * 0.5)

    println(elemsMap)
  }
}

// [info] Running sessionHOFList
// List(0.5, 0.75, 1.45, 2.25)
// [success] Total time: 6 s, completed 24 juil. 2013 11:22:39
