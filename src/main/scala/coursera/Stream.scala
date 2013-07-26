package coursera

object sessionStream {
  def main(args: Array[String]) = {
    def from(n: Int): Stream[Int] = n #:: from(n+1)

    def streamRange(lo: Int, hi: Int): Stream[Int] =
        if (lo >= hi) Stream.empty
        else lo #:: streamRange(lo + 1, hi)

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

    val secondprimeWithList = ((1000 to 10000) filter Prime.isPrime)(1)
    val secondprimeWithStream = ((1000 to 10000).toStream filter Prime.isPrime)(1)

    println("second prime number between [1000,10000] - list: " + secondprimeWithList)
    println("second prime number between [1000,10000] - list: " + secondprimeWithStream)
  }
}

// [info] Running coursera.sessionStream
// Tail not consumed: Stream(1, ?)
// Tail not consumed: Stream(10, ?)
// Tail not consumed    : Stream(2, ?)
// 0 stream: Stream(0, ?)
// list: List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
// second prime number between [1000,10000] - list: 1013
// second prime number between [1000,10000] - list: 1013
// [success] Total time: 1 s, completed 26 juil. 2013 10:12:23
