package p99

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object ex99Spec extends Properties("ex99") {
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

  property("nth") = forAll { (l: List[AnyVal], n: Int) =>
      try {
        ex99.nth(n, l) == l(n)
      } catch {
        case e: IndexOutOfBoundsException => true // expected
        case e: NoSuchElementException    => true // expected
        case _: Throwable                 => false // absolutely not expected
      }
    }
}
