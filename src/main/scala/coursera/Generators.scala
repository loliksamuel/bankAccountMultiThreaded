package coursera

import java.util.Random

trait Generator[+T] {
  self => // an alias for this

  def generate: T

  val integers = new Generator[Int] {
      val rand = new Random
      def generate = rand.nextInt()
    }

  def map[S](f: T => S): Generator[S] = new Generator[S] {
      def generate = f(self.generate)
    }

  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
      def generate = f(self.generate).generate
    }

  def pairs[T, U](t: Generator[T], u:Generator[U]) = for {
      x <- t
      y <- u
    } yield (x, y)

  def single[T](x: T): Generator[T] = new Generator[T] {
      def generate = x
    }

  def choose(lo: Int, hi: Int): Generator[Int] =
    for(x <- integers) yield lo + x % (hi - lo)

  def oneOf[T](xs: T*): Generator[T] =
    for(idx <- choose(0, xs.length)) yield xs(idx)

}

object TestGenerators {

  def main(args: List[String]) = {

    val integers = new Generator[Int] {
        val rand = new Random
        def generate = rand.nextInt
      }

    integers.pairs(integers, integers)
  }
}
