package coursera

trait Expr {
  def isNumber: Boolean
  def isSum: Boolean
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
}

class Number(n: Int) extends Expr {
  def isNumber: Boolean = true
  def isSum: Boolean = false
  def numValue: Int = n
  def leftOp: Expr = throw new Error("Number.leftOp")
  def rightOp: Expr = throw new Error("Number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  def isNumber: Boolean = false
  def isSum: Boolean = true
  def numValue: Int = throw new Error("Sum.numValue")
  def leftOp: Expr = e1
  def rightOp: Expr = e2
}

object sessionExpressions {
  def main(args: Array[String]) = {
    def eval(e: Expr): Int = {
      if(e.isNumber) e.numValue
      else if(e.isSum) eval(e.leftOp) + eval(e.rightOp)
      else throw new Error("Unknown Expression " + e)
    }

    println(eval(new Sum(new Number(10), new Number(-5))))
  }

}

// [info] Running coursera.sessionExpressions
// 5
// [success] Total time: 6 s, completed 25 juil. 2013 11:45:39
