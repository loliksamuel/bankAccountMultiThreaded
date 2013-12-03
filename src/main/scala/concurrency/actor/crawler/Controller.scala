package concurrency.actor

import akka.actor.{Actor, Props, ActorRef}

object Controller {
  case class Check(link: String, depth: Int)
  case class Result(cache: Set[String])
}

/** Class in charge of spawning Getter to retrieve url's content.
  */
class Controller extends Actor {
  import Controller._

  var cache = Set.empty[String]
  var children = Set.empty[ActorRef]

  def receive = {
    case Check(url, depth) =>
      if(!cache(url) && depth > 0) // spawn a new Getter in charge of retrieving the url's content
        children += context.actorOf(Props(new Getter(url, depth - 1)))
      cache += url
    case Getter.Done       => //
        children -= sender
        if (children.isEmpty) context.parent ! Result(cache) // computation done, we send the result to the caller
  }


}
