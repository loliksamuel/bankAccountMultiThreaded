package hello;

object Hi {
  def main(args: Array[String]) :Unit = {
    if (args.length > 0)
      println("Hello " + args(0) + "!")

    val m = max(1,2)
    println("the max is:" + m)
  }

  def max(x :Int, y :Int) :Int = {
    if (x > y) x else y
  }
}
