package concurrency.actor

import akka.actor.Actor
import akka.actor.Props

class Counter extends Actor {
  var count = 0

  def receive = {
    case "incr" => count += 1
    case "get"  => sender ! count
  }
}

class CounterMain extends Actor {

  def start: Unit = {
    val counter = context.actorOf(Props[Counter], "counter")

    counter ! "incr"
    counter ! "incr"
    counter ! "get"

    self ! "end"
  }

  def receive = {
    case "start"    => start
    case count: Int => println(s"count was $count")
    case "end"      => context.stop(self)
  }
}

object CounterActorTryout {
  import akka.actor.{Props, Actor, ActorSystem}

  def main(args: Array[String]) = {
    val system = ActorSystem("Tryout")

    val c = system.actorOf(Props[CounterMain])

    c ! "start"
  }


}
