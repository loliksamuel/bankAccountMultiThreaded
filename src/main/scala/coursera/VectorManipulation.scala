package coursera

object sessionVectorManipulation {

  def main(args: Array[String]) = {
    val nums = Vector(1, 2, 3, -88)

    val names = Vector("one", "two", "three", "minus eighty eight")

    println(nums)
    println(names)

    println(names zip nums)
  }

}

// [info] Running coursera.sessionVectorManipulation
// Vector(1, 2, 3, -88)
// Vector(one, two, three, minus eighty eight)
// Vector((one,1), (two,2), (three,3), (minus eighty eight,-88))
// [success] Total time: 23 s, completed 25 juil. 2013 14:38:22
