package coursera

abstract class IntSet {
  /**
    * to include an element in the set
    */
  def incl(x: Int): IntSet
  /**
    * is the element contains in the set
    */
  def contains(x: Int): Boolean
}

class Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new Leaf(x, new Empty, new Empty)
}

class Leaf(elem: Int, l: IntSet, r: IntSet) extends IntSet {

  def contains(x: Int): Boolean =
    if      (x < elem) l contains x
    else if (elem < x) r contains x
    else true

  def incl(x: Int): IntSet =
    if      (x < elem) new Leaf(elem, l incl x, r)
    else if (elem < x) new Leaf(elem, l,        r incl x)
    else this
}

object IntSet {
  def main(args: Array[String]) = {
    val t1 = new Leaf(7,
                      new Leaf(5, new Empty, new Empty),
                      new Leaf(8, new Empty, new Empty))

    println(t1)
  }
}
