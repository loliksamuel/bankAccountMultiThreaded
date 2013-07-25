package coursera

object sessionSequence {
  def main(args: Array[String]) = {
    println(1 until 5)
    println(1 to 5)
    println(1 to 10 by 3)
    println(6 to 1 by -2)
  }

}

// [info] Running coursera.sessionSequence
// Range(1, 2, 3, 4)
// Range(1, 2, 3, 4, 5)
// Range(1, 4, 7, 10)
// Range(6, 4, 2)
// [success] Total time: 6 s, completed 25 juil. 2013 14:29:56
