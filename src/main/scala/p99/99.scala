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
    else throw new NoSuchElementException("not in bounds")
  }

  // built-in: l.length
  // length of a list
  def length[T](l: List[T]): Int = (l foldLeft 0) { (acc, _) => 1 + acc }

  def reverse[T](l: List[T]): List[T] =
    (l foldLeft List[T]()) ((lst, y) => y :: lst)

  def isPalindrome[T](l: List[T]): Boolean = l match {
      case Nil      => true
      case x :: Nil => true
      case _        => {
        val (fst, end) = l splitAt l.length/2
        reverse(fst) == end
      }
  }

  def flatten[T](l: List[List[T]]): List[T] = (l foldLeft List[T]()) ((lst, x) => x ++ lst)

  def compress[T](l:List[T]): List[T] =
    (l.reverse foldLeft List[T]()) {(lst, e) => if (lst.isEmpty || lst.head != e) e::lst else lst}

  def pack[T](l:List[T]): List[List[T]] =
    (l.reverse foldLeft List(List[T]())) { (lst, e) => lst match {
        case List(Nil)    => List(List(e))
        case (x::xs)::ys  => if (x == e) (e::x::xs)::ys else List(e)::(x::xs)::ys
      }
    }

  def encode[T](s: List[T]): List[(Int, T)] =
    pack(s) map { l => (l.length, l.head) }

  def runLengthEncodingModified[T](s:List[T]): List[Any] =
    encode(s) map { t => if (t._1 == 1.0) t._2 else t }

  def decode[T](s: List[(Int, T)]): List[T] =
    s flatMap { x => List.make(x._1, x._2) }

  def encode1[T](l:List[T]): List[(Int, T)] =
    (l.reverse foldLeft List[(Int, T)]())
      { (lst, e) => lst match {
         case List()      => List((1, e))
         case (n, x)::xs  => if (x == e) (n+1, e)::xs else (1, e)::(n, x)::xs
       }
      }

  def main(args: Array[String]) = {
    // if scratch needed...
    println(reverse(List()))
    println(reverse(List(1)))

    val res = compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    println(res)

    val sres = compress(List("-1", "-1"))
    println(sres)
  }
}
