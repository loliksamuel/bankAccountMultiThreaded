package coursera

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]

  def toString: String
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")

  override def toString: String = "."
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false

  override def toString: String = "(" + head + ", " + tail.toString + ")"
}

object ConsListSession {
  def main(args: Array[String]) = {
    def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])

    println(singleton[Int](1))
    println(singleton[Boolean](true))
  }
}
