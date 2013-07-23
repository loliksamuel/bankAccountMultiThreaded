package coursera

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def ::(x: T):List[T]
  def toString: String
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  def ::(x: T):List[T] = new Cons(x, this)
  override def toString: String = "."
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false
  def ::(x: T):List[T] = new Cons(x, this)

  override def toString: String = "(" + head + ", " + tail.toString + ")"
}

object ConsListSession {
  def main(args: Array[String]) = {
    def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])

    def nth[T](n: Int, l: List[T]): T =
      if (n < 0 || l.isEmpty) throw new IndexOutOfBoundsException("Out of range")
      else if (n == 0) l.head
      else nth(n-1, l.tail)

    def last[T](xs: List[T]): T = xs match {
        case List()  => throw new Error("last of empty list")
        case List(x) => x
        case y :: ys => last(ys)
      }

     def init[T](xs: List[T]): T = xs match {
        case List()  => throw new Error("init of empty list")
        case List(x) => List()
        case y :: ys => y :: init(ys)
      }

    def concat[T](xs: List[T], ys: List[T]) = xs match {
        case List() => ys
        case x :: zs => x :: concat(zs, ys)
      }

    def reverse[T](xs: List[T]): List[T] = xs match {
        case List() => xs
        case y :: ys => reverse(ys) ++ List(y)
      }

    def catchAndPrint[T](index: Int, l: List[T]) =
      try {
        nth(index, l)
      } catch {
        case e: IndexOutOfBoundsException => println("exception caught as expected: " + e)
      }

    println(singleton[Int](1))
    println(singleton[Boolean](true))

    val listTypes = new Cons[Int](1, new Cons[Int](2, new Nil[Int]))
    println(listTypes)

    println(nth(0, listTypes))
    println(nth(1, listTypes))

    catchAndPrint[Int](-1, listTypes)
    catchAndPrint[Int](3, listTypes)

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
// end
// [success] Total time: 2 s, completed 23 juil. 2013 15:15:15
