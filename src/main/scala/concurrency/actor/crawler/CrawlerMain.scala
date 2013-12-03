package concurrency.actor

import akka.actor.{Actor, Props, ReceiveTimeout}
import scala.concurrent.duration._

class CrawlerMain extends Actor {
  import Receptionist._
//  import context.dispatcher

  val receptionist = context.actorOf(Props[Receptionist], "receptionist")

  receptionist ! Get("http://www.google.com/1")
  receptionist ! Get("http://www.google.com/2")
  receptionist ! Get("http://www.google.com/3")
  receptionist ! Get("http://www.google.com/4")

  context.setReceiveTimeout(10.seconds)
//  context.system.scheduler.scheduleOnce(20.seconds, self, ReceiveTimeout)

  def receive = {
    case ReceiveTimeout =>
      println("Failure by timeout!")
      context.stop(self)

    case Result(url, links) =>
      println(s"$url -> $links")
      context.stop(self)

    case Failed(url) =>
      println("Failure by error")
      context.stop(self)
  }

  override def postStop(): Unit = {
    WebClient.shutdown
  }

}
