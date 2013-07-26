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
  /**
    * Union of 2 sets
    */
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new Leaf(x, Empty, Empty)
  def union(other: IntSet): IntSet = other
  override def toString = "."
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

   def union(other: IntSet): IntSet =
    (l union (r union (other))) incl elem

  override def toString = "{" + l + elem + r + "}"
}

object IntSet {
  def main(args: Array[String]) = {
    val t1 = new Leaf(7,
                      new Leaf(5, Empty, Empty),
                      new Leaf(8, Empty, Empty))

    println(t1)
    println(t1 union t1)

    val t2 = new Leaf(12,
                      new Leaf(5, Empty, Empty),
                      Empty)

    println(t1 union t2)
  }
}

// [info] Running coursera.IntSet
// {{.5.}7{.8.}}
// {{.5.}7{.8.}}
// {{.5{{.7.}8.}}12.}
// [success] Total time: 5 s, completed 26 juil. 2013 09:22:46
