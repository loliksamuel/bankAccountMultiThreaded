package p99

import org.scalatest.FunSuite


class ex99FunTests extends FunSuite {
  test("compress") {
    assert(ex99.compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e)) == List('a,'b,'c,'d,'e))
  }

}
