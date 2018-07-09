package concurrency.actor

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern._
import concurrency.actor.BankAccount1.{Balance, Deposit, Withdraw}

import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

object App {
   def main(args: Array[String])={





     println("\n\nBank account with akka actors bankaccount1: ")
     implicit val timeout = Timeout(5 seconds)
     val system = ActorSystem("bankaccount1")
     val accountA = system.actorOf(Props[BankAccount1], "accountA")
     val accountB = system.actorOf(Props[BankAccount1], "accountB")
     for( i <-  0 to 20 ){
           val am1 = scala.util.Random.nextInt(100)+1
           //println("am1 = "+ am1)
           accountA ! (Deposit(am1))
           val am2 = scala.util.Random.nextInt(100)+1
           //println("am2 = "+ am2)
           accountA ! (Withdraw(am2))

           val bal1 = accountA ! Balance
            val future = accountA ? Balance("accountA")
            val result = Await.result(future, timeout.duration)
           println(s"Current balance: ${result}")
     }


     val future = accountA ? Balance("accountA")
     val result = Await.result(future, timeout.duration)
     println(s"___Current balance: ${result}")





     println("\n\nBank account with akka actors bankaccount2: ")
     val system2 = ActorSystem("simple-account")
     val accountC = system.actorOf(Props[BankAccount2], "accountC")

     // Test sending command message
     accountC ! ConfigMaxBalanceAllowance(2000.00) // set maximum allowance
     accountC ! DepositMoney (200.00)
     accountC ! WithdrawMoney(100.00)
     accountC ! DepositMoney (200.00)
     // Test withdraw overdraft; Actor should not allow
     accountC ! WithdrawMoney(301.00)

     // Test deposit more than maximum allowance
     accountC ! DepositMoney(2000.00)

     val future2 = accountC ? GetBalance()
     val result2 = Await.result(future2, timeout.duration)

     println(s"Current balance: ${result2}")







     println("\n\nBank account without akka actors: ")
     val acc :Account = new Account
     for(  i <-  0 to 20){
       val am1 = scala.util.Random.nextInt(100)
       acc.deposit(am1)
       val am2 = scala.util.Random.nextInt(100)
       acc.withdraw(am2)

       val bal = acc.balance
       println("balance = "+ bal)
     }

     val bal = acc.balance
     println("___balance = "+ bal)



   }
}
