package coursera

object sessionListManipulation {

  def main(args: Array[String]) = {
    def insert(x: Int, xs: List[Int]): List[Int] = xs match {
        case Nil => x :: Nil
        case y :: ys => if (x < y) x :: insert(y, ys)
                        else       y :: insert(x, ys)
      }

    def isort(xs: List[Int]): List[Int] = xs match {
        case Nil => Nil
        case y :: ys => insert(y, isort(ys))
      }

    val fruits = List("apples", "oranges", "pears")
      println(fruits)

    val fruits2 = "apples" :: "oranges" :: "pears" :: Nil
      println(fruits2)

    val listToSort = List(10, 8, 100, 0)
      println(isort(listToSort))
  }
}

// [info] Running coursera.sessionListManipulation
// List(apples, oranges, pears)
// List(apples, oranges, pears)
// List(0, 8, 10, 100)
// [success] Total time: 1 s, completed 25 juil. 2013 13:34:48
