package p99

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object ex99Spec extends Properties("ex99") {
  property("last") = forAll { (l: List[Int], x: Int) =>
      l match {
        case Nil    => ex99.last(x :: l) == x
        case _ :: _ => ex99.last(l) == ex99.last(x :: l)
      }
    }
}
