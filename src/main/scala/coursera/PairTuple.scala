//import scala.util._

object sessionPairTuple {
  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
      if (n == 0) xs
      else {
        def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
            case (Nil, ys) => ys
            case (xs, Nil) => xs
            case (x :: xss, y :: yss) => if (x < y) x :: merge(xss, ys)
                                         else       y :: merge(xs, yss)
        }
        val (f, s) = xs splitAt n
        merge(msort(f), msort(s))
      }
  }

  def main(args: Array[String]) = {
    val elems = List(10, 8 , 3, 1, 20)

    println(msort(elems))
  }
}

// [info] Running sessionPairTuple
// List(1, 3, 8, 10, 20)
// [success] Total time: 6 s, completed 24 juil. 2013 09:24:51
