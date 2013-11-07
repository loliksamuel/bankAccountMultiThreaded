package coursera

import java.util.Random


trait Generator[+T] {
  def generate: T
}

object Generator {
  def main(args: List[String]) = {
    val rand = new Random
    println(rand.nextInt())
  }

  val integers = new Generator[Int] {
      val rand = new Random
      def generate = rand.nextInt()
    }

  val booleans = new Generator[Boolean] {
      def generate = integers.generate > 0
    }

  val pairs = new Generator[(Int, Int)] {
      def generate = (integers.generate, integers.generate)
    }
}
