package coursera

object sessionSequence {
  def main(args: Array[String]) = {
    val fruit = Set("apple", "banana", "pear")
    val s = (1 to 6).toSet

    println(1 until 5)
    println(1 to 5)
    println(1 to 10 by 3)
    println(6 to 1 by -2)

    println(fruit)
    println(s)
  }

}

// [info] Running coursera.sessionSequence
// Range(1, 2, 3, 4)
// Range(1, 2, 3, 4, 5)
// Range(1, 4, 7, 10)
// Range(6, 4, 2)
// Set(apple, banana, pear)
// Set(5, 1, 6, 2, 3, 4)
