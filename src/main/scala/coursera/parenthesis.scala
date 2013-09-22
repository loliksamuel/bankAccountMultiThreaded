package coursera

object sessionParenthesis {
  def balance(chars: List[Char]): Boolean = {
    def bal(chars: List[Char], countLeft: Int, countRight: Int): Boolean = chars match {
        case Nil     => countLeft == countRight
        case '('::xs => bal(xs, 1+countLeft, countRight)
        case ')'::xs => if (countLeft < 1+countRight) false else bal(xs, countLeft, 1+countRight)
        case _::xs   => bal(xs, countLeft, countRight)
    }

    bal(chars, 0, 0)
  }

  def main(args: Array[String]) = {
    val tests = List(
        "(if (zero? x) max (/ 1 x))",
        "I told him (that it’s not (yet) done). (But he wasn’t listening)",
        ":-)",
        "())(",
        "()(",
        "(((((((((((((((((((((())))))))))))))))))))))))))",
        "(((((((((((((((((((((())))))))))))))))))))))")

    tests map (x => println(x + " => " + balance(x.toList)))

    println("End")
  }

}
