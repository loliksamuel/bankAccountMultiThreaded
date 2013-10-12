package p99

import org.scalacheck._
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import p99.ex99._

object ex99ScalaCheck extends Properties("ex99") {
  property("last") = forAll { (l: List[AnyVal], x: AnyVal) =>
      l match {
        case Nil    => last(x :: l) == x
        case _ :: _ => last(l) == last(x :: l)
      }
    }

  property("penultimate") = forAll { (l: List[AnyVal], x: AnyVal, y: AnyVal) =>
      l match {
        case Nil      => penultimate(x :: y :: Nil) == x
        case z :: Nil => penultimate(x :: z :: Nil) == x
        case _ :: _   => penultimate(l) == penultimate(x :: l)
      }
    }

  property("nth") = forAll { (l: List[AnyVal], n: Int, x: AnyVal) =>
      try {
        nth(n, l) == nth(n+1, x :: l)
      } catch {
        case e: NoSuchElementException    => true // expected
        case _: Throwable                 => false // absolutely not expected
      }
    }

  property("length") = forAll { (l: List[AnyVal], x: AnyVal) =>
    length(x :: l) == 1 + length(l)
    }

  property("reverse") = forAll { l: List[AnyVal] =>
      reverse(reverse(l)) == l
    }

  property("palindrome") = forAll { l: List[AnyVal] =>
      isPalindrome(l) == (l.reverse == l)
    }

  property("flatten") = forAll { (l: List[List[AnyVal]], x: List[AnyVal]) =>
      val fl = flatten(x :: l)
      x forall (fl contains _)
    }

  // property("randomSelect") = forAll { (l: List[List[AnyVal]], k: Int) =>
  //     randomSelect(k, l) forall (l contains _)
  //   }

  // property("lotto") = forAll { (k: Int, end: Int) =>
  //     val l = Range(1, end).toList
  //     lotto(k, end) forall (l contains _)
  //   }
}
