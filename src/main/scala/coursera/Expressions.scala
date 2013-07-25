package coursera

trait Expr {
  def eval: Int = this match {
      case Number(n)   => n
      case Sum(e1, e2) => e1.eval + e2.eval
      case Pdt(e1, e2) => e1.eval * e2.eval
  }

  def show: String = this match {
      case Number(n)   => n.toString
      case Sum(e1, e2) => "(" + e1.show + " + " + e2.show + ")"
      case Pdt(e1, e2) => "(" + e1.show + " * " + e2.show + ")"
    }
}

case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Pdt(e1: Expr, e2: Expr) extends Expr

object sessionExpressions {

  def main(args: Array[String]) = {
    val e1 = Sum(Number(10), Number(-5))
    val e2 = Pdt(Sum(Number(10), Number(-5)), Number(2))

    println(e1.show + ": " + e1.eval)
    println(e2.show + ": " + e2.eval)
  }

}

// [info] Running coursera.sessionExpressions
// (10 + -5): 5
// ((10 + -5) * 2): 10
// [success] Total time: 1 s, completed 25 juil. 2013 13:02:15
