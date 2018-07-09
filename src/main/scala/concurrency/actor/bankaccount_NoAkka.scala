package concurrency.actor

import akka.actor.{Actor, ActorRef, Props}

// good practice to define messages inside companion object

object  Bank {

}

class Bank   {


}

object Account {

}

class Account {
  private var _balance = 0


  def balance = _balance

  def deposit(ammount: Int): Boolean = {
    if (ammount < 0) false
    else {
      _balance += ammount//risk! it is not thread safe! need to use synchronize or lock mechanism
      true
    }

  }

  def withdraw(ammount: Int): Boolean = {
    if (ammount < 0 || ammount > _balance) false
    else {
      _balance -= ammount
      true
    }

  }
}
