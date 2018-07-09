package concurrency.actor

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

// Define message protocol
sealed trait Msg
case class DepositMoney (amt: BigDecimal)                     extends Msg
case class WithdrawMoney(amt: BigDecimal)                     extends Msg
case class ConfigMaxBalanceAllowance(maxBalance: BigDecimal)  extends Msg
case class GetMaxBalanceAllowance()                           extends Msg
case class GetBalance()                                       extends Msg

class BankAccount2 extends Actor{

  // Account State
  var balance       : BigDecimal = 0.0
  var maximumBalance: BigDecimal = 0.0


  // Message handler
  override def receive: Receive = {
    case m: DepositMoney => {
                        if (m.amt+balance > maximumBalance){ // Exceed maximum balance allowance
                          println("Operation Deposit  is not allowed: exceeded maximum balance allowance")
                        }
                        else {  // update balance
                          balance +=  m.amt
                        }
    }

    case m: WithdrawMoney => {
                        if (m.amt > balance){ // Not enough money
                          println("Operation Withdraw is not allowed: not enough money")
                        }
                        else { // update balance
                          balance -= m.amt
                        }
    }

    case m: ConfigMaxBalanceAllowance => maximumBalance = m.maxBalance
    case m: GetMaxBalanceAllowance    => sender ! maximumBalance // reply message back to sender
    case m: GetBalance                => sender ! balance // reply balance back to sender
    case _ => println("Invalid message")
  }

}
