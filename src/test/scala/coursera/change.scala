package coursera

import org.scalatest.FunSuite

class CountChangeSuite extends FunSuite {
  test("countChange: example given in instructions") {
    assert(sessionCountChange.countChange(4,List(1,2)) === 3)
    assert(sessionCountChange.countChange(300,List(5,10,20,50,100,200,500)) === 1022)
    assert(sessionCountChange.countChange(301,List(5,10,20,50,100,200,500)) === 0)
    assert(sessionCountChange.countChange(300,List(500,5,50,100,20,200,10)) === 1022)
    assert(sessionCountChange.countChange(100,List(1,5,10,25,50)) === 292)
  }
}
