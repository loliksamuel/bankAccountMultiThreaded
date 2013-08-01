package p99

object p01 {
  def last[T](l: List[T]): T = l match {
      case Nil      => throw new Error ("List.last on Nil")
      case x :: Nil => x
      case x :: xs  => last(xs)
    }

  def main(args: Array[String]) = {

    println(last(List(1, 2, 3, 4)))
  }
}
