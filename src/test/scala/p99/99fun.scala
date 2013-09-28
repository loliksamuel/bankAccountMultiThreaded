package p99

import org.scalatest.FunSuite


class ex99FunTests extends FunSuite {
  test("compress") {
    assert(ex99.compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e)) == List('a,'b,'c,'d,'e))
    assert(ex99.compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) == List('a,'b,'c,'a,'d,'e))
  }

  test("pack") {
    assert(ex99.pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e)) == List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('d), List('e, 'e, 'e, 'e)))
  }

  test("encode - run-length encoding") {
    assert(ex99.encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e)) == List((4, 'a), (1, 'b), (2, 'c), (1, 'd), (4, 'e)))
  }

  test("encode - run-length encoding modified") {
    assert(ex99.runLengthEncodingModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e)) == List((4, 'a), 'b, (2, 'c), 'd, (4, 'e)))
  }

  test("decode - run-length encoding") {
    assert(ex99.decode(List((4, 'a), (1, 'b), (2, 'c), (1, 'd), (4, 'e))) == List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e))
  }

  test("encode1 - direct run-length encoding") {
    assert(ex99.encode1(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e)) == List((4, 'a), (1, 'b), (2, 'c), (1, 'd), (4, 'e)))
  }

  test("duplicate element in double") {
    assert(ex99.duplicate(List(1, 2, 3, 4)) == List(1, 1, 2, 2, 3, 3, 4, 4))
    assert(ex99.duplicate(List(3, 2)) == List(3, 3, 2, 2))
  }

  test("duplicate element in a given amount of time double") {
    assert(ex99.duplicateN(3, List(1, 2, 3, 4)) == List(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4))
    assert(ex99.duplicateN(5, List(3, 2)) == List(3, 3, 3, 3, 3, 2, 2, 2, 2, 2))
  }

}
