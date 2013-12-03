package concurrency.actor

import akka.actor.{Actor, Props, ReceiveTimeout}
import scala.concurrent.duration._

class CrawlerMain extends Actor {
  import Receptionist._
  import context.dispatcher

  val receptionist = context.actorOf(Props[Receptionist], "receptionist")

  receptionist ! Get("http://adumont.fr/blog")
  receptionist ! Get("http://adumont.fr")
  receptionist ! Get("http://github.com/ardumont")

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
