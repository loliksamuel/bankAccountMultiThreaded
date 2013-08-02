package p99

import scala.annotation.tailrec


object ex99 {
  // built-in: l.last
  def last[T](l: List[T]): T = l match {
      case Nil      => throw new NoSuchElementException("Nil.last")
      case x :: Nil => x
      case _ :: xs  => last(xs)
    }

  // built-in: l.init.last
  def penultimate[T](l: List[T]): T = l match {
      case Nil           => throw new NoSuchElementException("Nil.last")
      case x :: Nil      => throw new NoSuchElementException("Nil.last")
      case x :: _ :: Nil => x
      case _ :: xs       => penultimate(xs)
    }

  // IndexOutOfBoundException
  // NoSuchElementException
  def nth[T](n: Int, l: List[T]): T = {
    def internalNth(n: Int, l: List[T]): T = (n ,l) match {
        case (_, Nil)     => throw new NoSuchElementException("List consumed, no element was found.")
        case (0, x :: _)  => x
        case (n, _ :: xs) => internalNth(n-1, xs)
      }

    if (n >= 0) internalNth(n, l)
    else throw new IndexOutOfBoundsException("not in bounds")
  }

  // built-in: l.length
  // length of a list
  def length[T](l: List[T]): Int = (l foldRight 1) { (_, acc) => 1 + acc }

  def main(args: Array[String]) = {
    println(last(List(1, 2, 3, 4)))
    println(last(List("1", "2", "3", "4")))
    println(nth(2, List("1", "2", "3", "4")))

    try { nth(-1, List("1", "2", "3", "4")) }
    catch {
      case e: IndexOutOfBoundsException => println("negative index - expected out of bounds exception")
    }

    try { nth(100, List("1", "2", "3", "4")) }
    catch {
      case e: IndexOutOfBoundsException => println("index > upper bound - expected out of bounds exception")
    }

    println("end")
  }
}
