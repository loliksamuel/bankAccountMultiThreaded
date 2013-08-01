package p99

object ex99 {
  def last[T](l: List[T]): T = l match {
      case Nil      => throw new NoSuchElementException("Nil.last")
      case x :: Nil => x
      case _ :: xs  => last(xs)
    }

  def penultimate[T](l: List[T]): T = l match {
      case Nil           => throw new NoSuchElementException("Nil.last")
      case x :: Nil      => throw new NoSuchElementException("Nil.last")
      case x :: _ :: Nil => x
      case _ :: xs       => penultimate(xs)
    }

  def main(args: Array[String]) = {
    println(last(List(1, 2, 3, 4)))
    println(last(List("1", "2", "3", "4")))
  }
}
