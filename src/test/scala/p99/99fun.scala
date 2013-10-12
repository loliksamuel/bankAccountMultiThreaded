package p99

import org.scalatest.FunSuite
import p99.ex99._

class ex99FunTests extends FunSuite {
  def assertion[T](actual: T, expected: T) = {
    assert(actual == expected)
  }

  def ass[T](actual: T, expected: T) = {
    println("actual:" + actual)
    println("expected:" + expected)
    assertion(actual, expected)
  }

  test("compress.") {
    assert(compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e)) == List('a,'b,'c,'d,'e))
    assert(compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) == List('a,'b,'c,'a,'d,'e))
  }

  test("pack.") {
    assert(pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e)) == List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('d), List('e, 'e, 'e, 'e)))
  }

  test("encode - run-length encoding.") {
    assert(encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e)) == List((4, 'a), (1, 'b), (2, 'c), (1, 'd), (4, 'e)))
  }

  test("encode - run-length encoding modified.") {
    assert(runLengthEncodingModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e)) == List((4, 'a), 'b, (2, 'c), 'd, (4, 'e)))
  }

  test("decode - run-length encoding.") {
    assert(decode(List((4, 'a), (1, 'b), (2, 'c), (1, 'd), (4, 'e))) == List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e))
  }

  test("encode1 - direct run-length encoding.") {
    assert(encode1(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'd, 'e, 'e, 'e, 'e)) == List((4, 'a), (1, 'b), (2, 'c), (1, 'd), (4, 'e)))
  }

  test("duplicate element in double.") {
    assert(duplicate(List(1, 2, 3, 4)) == List(1, 1, 2, 2, 3, 3, 4, 4))
    assert(duplicate(List(3, 2)) == List(3, 3, 2, 2))
  }

  test("duplicate element in a given amount of time.") {
    assert(duplicateN(3, List(1, 2, 3, 4)) == List(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4))
    assert(duplicateN(5, List(3, 2)) == List(3, 3, 3, 3, 3, 2, 2, 2, 2, 2))
  }

  test("drop every nth element on the list.") {
    assert(dropN(3, List(1, 2, 3, 4)) == List(1, 2, 4))
    assert(dropN(2, List(3, 2, 1, 0, 4)) == List(3, 1, 4))
  }

  test("split a list at a given position into 2 lists.") {
    assert(splitN(3, List(1, 2, 3, 4)) == (List(1, 2, 3), List(4)))
    assert(splitN(2, List(3, 2, 1, 0, 4)) == (List(3, 2), List(1, 0, 4)))
  }

  test("slice") {
    assert(slice(0, 3, List(1, 2, 3, 4, 5)) == List(1, 2, 3))
    assert(slice(4, 5, List(1, 2, 3, 4, 5, 6, 7)) == List(5))
  }

  test("rotate") {
    assert(rotate(0, List(1, 2, 3, 4, 5)) == List(1, 2, 3, 4, 5))
    assert(rotate(1, List(1, 2, 3, 4, 5)) == List(2, 3, 4, 5, 1))
    assert(rotate(2, List(1, 2, 3, 4, 5)) == List(3, 4, 5, 1, 2))
    assert(rotate(5, List(1, 2, 3, 4, 5)) == List(1, 2, 3, 4, 5))
    assert(rotate(-1, List(1, 2, 3, 4, 5)) == List(5, 1, 2, 3, 4))
  }

  test("removeAt") {
    assert(removeAt(0, List(1, 2, 3, 4, 5)) == (List(2, 3, 4, 5), Some(1)))
    assert(removeAt(1, List(1, 2, 3, 4, 5)) == (List(1, 3, 4, 5), Some(2)))
    assert(removeAt(2, List(1, 2, 3, 4, 5)) == (List(1, 2, 4, 5), Some(3)))
    assert(removeAt(6, List(1, 2, 3, 4, 5)) == (List(1, 2, 3, 4, 5), None))

    intercept[NoSuchElementException] {
      removeAt(-1, List())
    }
  }

  test("insertAt") {
    assert(insertAt(-1, 0, List(1, 2, 3, 4, 5)) == List(-1, 1, 2, 3, 4, 5))
    assert(insertAt(-1, 1, List(1, 2, 3, 4, 5)) == List(1, -1, 2, 3, 4, 5))
    assert(insertAt('f, 3, List('a, 'b, 'c, 'd, 'e)) == List('a, 'b, 'c, 'f, 'd, 'e))
  }

  test("range") {
    assert(range(0, 2) == List(0, 1, 2))
    assert(range(2, 5) == List(2, 3, 4, 5))
    assert(range(5, 5) == List(5))
  }

  test("randomSelect") {
    assert(randomSelect(3, List(1, 2, 4, 6, 10)).length == 3)
    assert(randomSelect(4, List(1, 2, 4, 6, 10)).length == 4)
    assert(randomSelect(5, List(1, 2, 4, 6, 10)).length == 5)
  }

  test("randomPermute") {
    val list = List(1, 2, 3, 4, 5)
    assert(randomPermute(list).toSet == list.toSet)
    assert(randomPermute(list).length == list.length)
  }
}
