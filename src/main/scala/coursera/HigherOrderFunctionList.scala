object sessionHOFList {
  def main(args: Array[String]) = {
    def pack[T](xs: List[T]): List[List[T]] = xs match {
        case Nil     => Nil
        case y :: ys =>
          val (yss, rss) = xs span (x => x == y)
          yss :: pack(rss)
      }

    def encode[T](xs: List[T]): List[(T, Int)] = pack(xs) map (x => (x.head, x.length))

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

    val listToPack = List("a", "a", "a", "b", "c", "c", "a")
    println(pack(listToPack))
    println(encode(listToPack))
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
// List(List(a, a, a), List(b), List(c, c), List(a))
// List((a,3), (b,1), (c,2), (a,1))
// [success] Total time: 1 s, completed 24 juil. 2013 12:17:14
