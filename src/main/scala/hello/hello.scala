package hello;

object Hi {
  def main(args: Array[String]): Unit = {
    if (args.length > 0)
      println("Hello " + args(0) + "!")

    val m = max(1, 2)
    println("the max is:" + m)

    playWithMap()

    println(factorial(30));
  }

  def max(x: Int, y: Int): Int = {
    if (x > y) x else y
  }

  def playWithMap(): Unit = {
    val capitals = Map("US" -> "Washington",
                       "France" -> "Paris")

    println(capitals("France"));
  }

  def factorial(x: BigInt): BigInt =
    if (x == 0) 1 else x * factorial(x-1)
}
