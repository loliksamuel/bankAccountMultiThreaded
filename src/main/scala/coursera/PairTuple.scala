import math.Ordering

object sessionPairTuple {
  def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
      if (n == 0) xs
      else {
        def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
            case (Nil, ys) => ys
            case (xs, Nil) => xs
            case (x :: xss, y :: yss) => if (ord.lt(x,y)) x :: merge(xss, ys)
                                         else         y :: merge(xs, yss)
        }
        val (f, s) = xs splitAt n
        merge(msort(f), msort(s))
      }
  }

  def main(args: Array[String]) = {
    val elems = List(10, 8 , 3, 1, 20)
    println(msort(elems))

    val fruits = List("apple", "pineapple", "orange", "banana")
    println(msort(fruits))
  }
}

// [info] Running sessionPairTuple
// List(1, 3, 8, 10, 20)
// List(apple, banana, orange, pineapple)
// [success] Total time: 5 s, completed 24 juil. 2013 11:07:52
