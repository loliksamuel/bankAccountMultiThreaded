package coursera

object sessionListManipulation {
  def main(args: Array[String]) = {
    val fruits = List("apples", "oranges", "pears")
      println(fruits)

    val fruits2 = "apples" :: ("oranges" :: ("pears" :: Nil))
      println(fruits2)
  }
}

// [info] Running coursera.sessionListManipulation
// List(apples, oranges, pears)
// List(apples, oranges, pears)
// [success] Total time: 4 s, completed 25 juil. 2013 13:22:05
