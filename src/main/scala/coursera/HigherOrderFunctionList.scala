object sessionHOFList {
  def main(args: Array[String]) = {

    val elems = List(1.0, 1.5, 2.9, 4.5)
    println(elems map (x => x * 0.5))

    val ilems = List(1, 3, 5, 7)
    println(ilems map (x => x * x))

    val nlems = List(-1, 1, 10, -2, 45)
    println(nlems filter    (x => x > 0))
    println(nlems filterNot (x => x > 0))
    println(nlems partition (x => x > 0))

    println(nlems takeWhile (x => x < 0))
    println(nlems dropWhile (x => x < 0))
    println(nlems span      (x => x < 0))
  }
}

// [info] Running sessionHOFList
// List(0.5, 0.75, 1.45, 2.25)
// List(1, 9, 25, 49)
// List(1, 10, 45)
// List(-1, -2)
// (List(1, 10, 45),List(-1, -2))
// List(-1)
// List(1, 10, -2, 45)
// (List(-1),List(1, 10, -2, 45))
// [success] Total time: 0 s, completed 24 juil. 2013 11:41:24
