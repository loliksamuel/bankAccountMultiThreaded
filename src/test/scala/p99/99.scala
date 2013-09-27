package p99

import org.scalacheck._
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object ex99ScalaCheck extends Properties("ex99") {
  property("last") = forAll { (l: List[AnyVal], x: AnyVal) =>
      l match {
        case Nil    => ex99.last(x :: l) == x
        case _ :: _ => ex99.last(l) == ex99.last(x :: l)
      }
    }

  property("penultimate") = forAll { (l: List[AnyVal], x: AnyVal, y: AnyVal) =>
      l match {
        case Nil      => ex99.penultimate(x :: y :: Nil) == x
        case z :: Nil => ex99.penultimate(x :: z :: Nil) == x
        case _ :: _   => ex99.penultimate(l) == ex99.penultimate(x :: l)
      }
    }

  property("nth") = forAll { (l: List[AnyVal], n: Int, x: AnyVal) =>
      try {
        ex99.nth(n, l) == ex99.nth(n+1, x :: l)
      } catch {
        case e: NoSuchElementException    => true // expected
        case _: Throwable                 => false // absolutely not expected
      }
    }

  property("length") = forAll { (l: List[AnyVal], x: AnyVal) =>
    ex99.length(x :: l) == 1 + ex99.length(l)
    }

  property("reverse") = forAll { l: List[AnyVal] =>
      ex99.reverse(ex99.reverse(l)) == l
    }

  property("palindrome") = forAll { l: List[AnyVal] =>
      ex99.isPalindrome(l) == (l.reverse == l)
    }

  property("flatten") = forAll { (l: List[List[AnyVal]], x: List[AnyVal]) =>
      val fl = ex99.flatten(x :: l)
      x forall (fl contains _)
    }

  property("compress") = forAll { l: List[AnyVal] =>
      ex99.compress(l).size == l.toSet.size
    }
}
