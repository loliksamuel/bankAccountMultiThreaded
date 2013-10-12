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

  def isPalindrome[T](l: List[T]): Boolean = l.reverse == l

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

  def duplicate[T](l: List[T]): List[T] = duplicateN(2, l)

  def duplicateN[T](n: Int, l: List[T]): List[T] =
    l flatMap (List.make(n, _))

  def dropN[T](n: Int, l: List[T]): List[T] =
    l.zipWithIndex filter { v => ((v._2 + 1) % n != 0) } map { v => v._1 }

  def splitN[T](n: Int, l: List[T]): (List[T], List[T]) = (l.take(n), l.drop(n))

  def slice[T](start: Int, end: Int, l: List[T]): List[T] = l.drop(start).take (end - start)

  def rotate[T](n: Int, l: List[T]): List[T] = {
    def rotatePositiveIndex(n: Int, l: List[T]): List[T] = {
      val (end, start) = splitN(n, l)
      start ++ end
    }

    if (n < 0) rotatePositiveIndex((n + l.length) % l.length, l)
    else rotatePositiveIndex(n, l)
  }

  def removeAt[T](k: Int, l: List[T]): (List[T], Option[T]) = {
    def internalRemoveK(k: Int, l:List[T]) = {
      val (start, end) = splitN(k, l)
      (start ++ end.tail, Some(end.head))
    }

    if (k < 0) throw new NoSuchElementException
    else if (k >= l.length) (l, None)
    else internalRemoveK(k, l)
  }

  def insertAt[T](n: T, k: Int, l: List[T]): List[T] = l.splitAt(k) match {
      case (pre, post) => pre ++ (n :: post)
  }

  def range(start: Int, end: Int): List[Int] = {
    def internalRange(start: Int, end: Int, l: List[Int]): List[Int] =
        if (start > end) l.reverse
        else internalRange(start + 1, end, start :: l)

    internalRange(start, end , List())
  }

  def randomSelect[T](n: Int, l: List[T]): List[T] = {
    def internalRandomSelect(k: Int, lst: List[T], r: util.Random): List[T] =
      if (k == 0) Nil
      else removeAt(r.nextInt(lst.length), lst) match {
        case (nl, Some(e)) => e :: internalRandomSelect(k - 1, nl, r)
        case (nl, None)    => nl
      }

    internalRandomSelect(n, l, new util.Random)
  }

  def lotto[T](n: Int, end: Int): List[Int] = randomSelect(n + 1, Range(1, n).toList)

  def randomPermute[T](l: List[T]): List[T] = {
    val (nl, Some(v)) = removeAt((new util.Random).nextInt(l.length), l)
    v :: nl
  }

  def main(args: Array[String]) = {
  }
}
