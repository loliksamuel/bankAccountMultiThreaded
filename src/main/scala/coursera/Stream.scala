package coursera

object sessionStream {
  def main(args: Array[String]) = {
    val xs = Stream.cons(1, Stream.cons(2, Stream.empty))
    val ys = Stream(10, 20, 30)

    println("Tail not consumed: " + xs)
    println("Tail not consumed: " + ys)

    val xxs = xs map (_ + 1)
    println("Tail not consumed    : " +  xxs)
  }
}

// [info] Running coursera.sessionStream
// Stream(1, ?)
// [success] Total time: 4 s, completed 26 juil. 2013 09:38:55
