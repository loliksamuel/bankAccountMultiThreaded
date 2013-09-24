package coursera

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

class PascalSuite extends FunSuite {
  test("pascal tests") {
    assert(sessionPascal.pascal(0,2) === 1)
    assert(sessionPascal.pascal(1,2) === 2)
    assert(sessionPascal.pascal(1,3) === 3)
    assert(sessionPascal.pascal(5, 8) === 56)
    assert(sessionPascal.pascal(4, 8) === 70)
  }
}
