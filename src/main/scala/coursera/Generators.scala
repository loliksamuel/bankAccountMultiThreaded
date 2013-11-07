package coursera.generators

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

object StreamGenerator {
  val integers: Generator[Int] = new Generator[Int] {
      val rand = new Random
      def generate = rand.nextInt()
    }

  val booleans: Generator[Boolean] = for (x <- integers) yield x > 0

  def lists: Generator[Stream[Int]] = for {
      isEmpty <- booleans
      list    <- if (isEmpty) emptyLists else nonEmptyLists
    } yield list

  def emptyLists: Generator[Stream[Int]] = new Generator[Stream[Int]] {
      def generate = Stream.empty
    }

  def nonEmptyLists: Generator[Stream[Int]] = for {
      head <- integers
      tail <- lists
    } yield head #:: tail

  def main(args: List[String]) = {

    val integers = new Generator[Int] {
        val rand = new Random
        def generate = rand.nextInt
      }

    val someRand = integers.pairs(integers, integers)

    println("Generate some random int list: " + lists)
  }
}
