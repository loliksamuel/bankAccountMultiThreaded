package default;

object Hello {
  def main(args: Array[String]) :Unit = {
    val user = "tony";
    println("Hello " + user + "!");

    val m = max(1,2);
    println("the max is:" + m);
  }

  def max(x :Int, y :Int) :Int = {
    if (x > y) x else y
  }
}
