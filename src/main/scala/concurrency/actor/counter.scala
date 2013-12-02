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

  val counter = context.actorOf(Props[Counter], "counter")

  counter ! "incr"
  counter ! "incr"
  counter ! "get"

  context.stop(self)

  def receive = {
    case count: Int => println(s"count was $count")
  }
}
