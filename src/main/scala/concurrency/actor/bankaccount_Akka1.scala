package concurrency.actor

import akka.actor.{Actor, Props, ActorRef}

// good practice to define messages inside companion object

object BankAccount1 {

  case class Deposit (amount: Int) {  require(amount > 0)  }

  case class Withdraw(amount: Int) {  require(amount > 0)  }

  case class Balance(name: String)

  case object Done

  case object Failed

}



class BankAccount1 extends Actor {
      import BankAccount1._
      // Account State
      var balance : Int = 0
      var maximumBalance: BigDecimal = 0.0


      override def receive = {
            case Deposit(amount)  =>
                                    balance += amount
                                    sender ! Done
            case Withdraw(amount) =>
                                    if (balance < amount)
                                        sender ! Failed
                                    else {
                                        balance -= amount
                                        sender ! Done
                                    }
            case Balance(name)    =>
                                    sender ! (name, balance)
            case _                =>
                                    sender ! Failed
      }
}

object WireTransfer {
  case class  Transfer(from: ActorRef, to: ActorRef, amount: Int)
  case object Done
  case object Failed
}

class WireTransfer extends Actor {
  import WireTransfer._

  def receive: Receive = {
    case Transfer(from, to, amount) =>
      from ! BankAccount1.Withdraw(amount)
      context.become(awaitFrom(to, amount, sender))
  }

  def awaitFrom(to: ActorRef, amount: Int, customer: ActorRef): Receive = {
    case BankAccount1.Done =>
      to ! BankAccount1.Deposit(amount)
      context.become(awaitTo(customer))
    case BankAccount1.Failed =>
      customer ! Failed
      context.stop(self)
  }

  def awaitTo(client: ActorRef): Receive = {
    case BankAccount1.Done =>
      client ! Done
      context.stop(self)

    case BankAccount1.Failed =>
      client ! Failed
      context.stop(self)
  }
}

class TransferMain extends Actor {
  val accountA = context.actorOf(Props[BankAccount1], "accountA")
  val accountB = context.actorOf(Props[BankAccount1], "accountB")

  accountA ! BankAccount1.Deposit(80)
  accountB ! BankAccount1.Deposit(20)

  def receive: Receive = {
    case BankAccount1.Done => transfer(100)
  }

  def transfer(amount: Int): Unit = {
    val transaction = context.actorOf(Props[WireTransfer], "transfer")

    transaction ! WireTransfer.Transfer(accountA, accountB, amount)
    context.become({
                     case WireTransfer.Done =>
                       println("Wire transfer success!")
                       checkAccounts
                     case WireTransfer.Failed =>
                       println("Wire transfer failure!")
                       context.stop(self)
                   })
  }

  def checkAccounts: Unit = {
    accountA ! BankAccount1.Balance("A")
    accountB ! BankAccount1.Balance("B")

    context.become({case (name, amount) =>
                     println(s"Account '$name' amount: $amount")
                   })
  }
}
