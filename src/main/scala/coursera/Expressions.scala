package coursera

trait Expr

case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Pdt(e1: Expr, e2: Expr) extends Expr

object sessionExpressions {

  def eval(e: Expr): Int = e match {
      case Number(n)   => n
      case Sum(e1, e2) => eval(e1) + eval(e2)
      case Pdt(e1, e2) => eval(e1) * eval(e2)
  }

  def main(args: Array[String]) = {
    println(eval(new Sum(new Number(10), new Number(-5))))
    println(eval(new Pdt(new Sum(new Number(10), new Number(-5)), new Number(2))))
  }

}

// [info] Running coursera.sessionExpressions
// 5
// 10
// [success] Total time: 2 s, completed 25 juil. 2013 12:09:00
