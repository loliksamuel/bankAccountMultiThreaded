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

object CounterMain {
  case object Done
}

class CounterMain extends Actor {

  override def preStart(): Unit = {
//    val counterMain = context.actorOf(Props[CounterMain], "countermain")
    val counter = context.actorOf(Props[Counter], "counter")

    counter ! "incr"
    counter ! "incr"
    counter ! "get"

    self ! CounterMain.Done
  }

  def receive = {
    case count: Int =>
      println(s"count was $count")
    case CounterMain.Done =>
      context.stop(self)
  }
}
