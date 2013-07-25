package coursera

trait List1[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List1[T]
  def toString: String
}

case object Nil1 extends List1[Nothing] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  override def toString: String = "."
}

case class Cons1[T](val head: T, val tail: List1[T]) extends List1[T] {
  def isEmpty: Boolean = false

  override def toString: String = "(" + head + ", " + tail.toString + ")"
}

object ConsListSession {

  def main(args: Array[String]) = {
    def singleton[T](elem: T) = new Cons1[T](elem, Nil1)

    def nth[T](n: Int, l: List1[T]): T =
      if (n < 0 || l.isEmpty) throw new IndexOutOfBoundsException("Out of range")
      else if (n == 0) l.head
      else nth(n-1, l.tail)

    def last[T](xs: List1[T]): T = xs match {
        case Nil1           => throw new Error("last of empty list")
        case Cons1(x, Nil1) => x
        case Cons1(y,ys)      => last(ys)
      }

    //  def init[T](xs: List[T]): T = xs match {
    //     case List()  => throw new Error("init of empty list")
    //     case List(x) => List()
    //     case y :: ys => y :: init(ys)
    //   }

    // def concat[T](xs: List[T], ys: List[T]) = xs match {
    //     case List() => ys
    //     case x :: zs => x :: concat(zs, ys)
    //   }

    // def reverse[T](xs: List[T]): List[T] = xs match {
    //     case List() => xs
    //     case y :: ys => reverse(ys) ++ List(y)
    //   }

    def catchAndPrint[T](index: Int, l: List1[T]) =
      try {
        nth(index, l)
      } catch {
        case e: IndexOutOfBoundsException => println("exception caught as expected: " + e)
      }

    println(singleton[Int](1))
    println(singleton[Boolean](true))

    val listTypes = new Cons1[Int](1, new Cons1[Int](2, Nil1))
    println(listTypes)

    println(nth(0, listTypes))
    println(nth(1, listTypes))

    catchAndPrint[Int](-1, listTypes)
    catchAndPrint[Int](3, listTypes)

    println(last(listTypes))
    println("end")
  }
}

// [info] Running coursera.ConsListSession
// (1, .)
// (true, .)
// (1, (2, .))
// 1
// 2
// exception caught as expected: java.lang.IndexOutOfBoundsException: Out of range
// exception caught as expected: java.lang.IndexOutOfBoundsException: Out of range
// 2
// end
// [success] Total time: 5 s, completed 25 juil. 2013 09:46:49
