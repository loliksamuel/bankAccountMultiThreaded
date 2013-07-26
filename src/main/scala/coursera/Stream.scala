package coursera

object sessionStream {
  def main(args: Array[String]) = {
    def streamRange(lo: Int, hi: Int): Stream[Int] =
        if (lo >= hi) Stream.empty
        else Stream.cons(lo, streamRange(lo + 1, hi))

    def listRange(lo: Int, hi: Int): List[Int] =
        if (lo >= hi) Nil
        else lo :: listRange(lo + 1, hi)

    val xs = Stream.cons(1, Stream.cons(2, Stream.empty))
    val ys = Stream(10, 20, 30)

    println("Tail not consumed: " + xs)
    println("Tail not consumed: " + ys)

    val xxs = xs map (_ + 1)
    println("Tail not consumed    : " +  xxs)

    println("stream: " + streamRange(0, 10))
    println("list: " + listRange(0, 10))
  }
}

// [info] Running coursera.sessionStream
// Tail not consumed: Stream(1, ?)
// Tail not consumed: Stream(10, ?)
// Tail not consumed    : Stream(2, ?)
// stream: Stream(0, ?)
// list: List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
// [success] Total time: 1 s, completed 26 juil. 2013 09:47:32
