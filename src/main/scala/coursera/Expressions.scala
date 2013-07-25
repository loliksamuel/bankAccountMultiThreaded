package coursera

trait Expr {
  def eval: Int = this match {
      case Number(n)   => n
      case Sum(e1, e2) => e1.eval + e2.eval
      case Pdt(e1, e2) => e1.eval * e2.eval
  }
}

case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Pdt(e1: Expr, e2: Expr) extends Expr

object sessionExpressions {

  def main(args: Array[String]) = {
    println(Sum(Number(10), Number(-5)).eval)
    println(Pdt(Sum(Number(10), Number(-5)), Number(2)).eval)
  }

}

// [info] Running coursera.sessionExpressions
// 5
// 10
// [success] Total time: 3 s, completed 25 juil. 2013 12:52:17
