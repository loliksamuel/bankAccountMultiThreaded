package concurrency.actor

import akka.actor.{Actor, Props, ReceiveTimeout}
import scala.concurrent.duration._

class CrawlerMain extends Actor {
  import Receptionist._
  import context.dispatcher

  val receptionist = context.actorOf(Props[Receptionist], "receptionist")

  receptionist ! Get("http://www.google.com/1")
  receptionist ! Get("http://www.google.com/2")
  receptionist ! Get("http://www.google.com/3")
  receptionist ! Get("http://www.google.com/4")

  context.system.scheduler.scheduleOnce(10.seconds, self, ReceiveTimeout)

  def receive = {
    case ReceiveTimeout =>
      println("Timeout!")
      context.stop(self)

    case Result(url, links) =>
      println(links.toVector.sorted.mkString(s"Results for '$url':\n", "\n", "\n"))

    case Failed(url) =>
      println(s"Fetch $url failed!")
  }

  override def postStop(): Unit = {
    WebClient.shutdown
  }

}
