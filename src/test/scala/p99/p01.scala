package p99

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object p01Spec extends Properties("p01") {
  property("last") = forAll {
      l: List[Int] => l match {
        case Nil     => true
        case x :: xs => p01.last(l) == l.last
      }
    }
}
