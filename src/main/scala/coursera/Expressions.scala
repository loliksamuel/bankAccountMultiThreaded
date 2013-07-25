package coursera

trait Expr {
  def eval: Int
}

class Number(n: Int) extends Expr {
  def eval: Int = n
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  def eval: Int = e1.eval + e2.eval
}

class Pdt(e1: Expr, e2: Expr) extends Expr {
  def eval: Int = e1.eval * e2.eval
}

object sessionExpressions {
  def main(args: Array[String]) = {
    println(new Sum(new Number(10), new Number(-5)).eval)
    println(new Pdt(new Sum(new Number(10), new Number(-5)), new Number(2)).eval)
  }

}

// [info] Running coursera.sessionExpressions
// 5
// 10
// [success] Total time: 2 s, completed 25 juil. 2013 12:00:08
