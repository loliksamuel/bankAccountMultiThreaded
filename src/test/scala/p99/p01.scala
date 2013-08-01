package p99

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object p01Spec extends Properties("p01") {
  property("last") = forAll { (l: List[Int], x: Int) =>
      l match {
        case Nil    => p01.last(x :: l) == x
        case _ :: _ => p01.last(l) == p01.last(x :: l)
      }
    }
}
