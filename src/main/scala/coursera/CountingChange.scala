package coursera

object sessionCountChange {

  def countChange(money: Int, coins: List[Int]): Int = {
    def countchange(money: Int, coins: List[Int]): Int = {
      if (money < 0) 0
      else if (money == 0) 1
      else coins match {
        case Nil     => 0
        case x :: xs => countchange(money, xs) + countchange(money - x, coins)
      }
    }

    countchange(money, coins.sortWith(_ < _).reverse)
  }

  def main(args: Array[String]) = {
    val tests = List((300, List(5,10,20,50,100,200,500)),
                     (301, List(5,10,20,50,100,200,500)),
                     (4,   List(1,2)),
                     (100, List(50,25,10,5,1)))
    println(tests)

    tests map (x => { val (money, coins) = x
                       countChange(money, coins) }) map println

    println("tests")
  }
}
