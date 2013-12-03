package concurrency.actor

import akka.actor.{Actor, ActorRef, Props}

object Receptionist {
  private case class Job(client: ActorRef, url: String)
  case class Get(url: String)
  case class Result(url: String, links: Set[String])
  case class Failed(url: String)
}

/**
  */
class Receptionist extends Actor {
  import Receptionist._

  def receive = waiting

  /** Waiting for some action
    * Get(url)
    */
  val waiting: Receive = {
    // First reception of a Get, we enqueue our first job
    case Get(url) =>
      context.become(running(Vector(Job(sender, url))))
  }

  def running(queue: Vector[Job]): Receive = {
    // Reception of a new Get(url), we enqueue another job for the url
    case Get(url)                 =>
      context.become(enqueueJob(queue, Job(sender, url)))

    case Controller.Result(links) =>
      val Job(client, url) = queue.head
      client ! Result(url, links)
      context.stop(sender)
      context.become(runNext(queue.tail))
  }

  var reqNo = 0
  def runNext(queue: Vector[Job]): Receive = {
    if (queue.isEmpty) waiting
    else {
      val controller = context.actorOf(Props[Controller], s"crawlerController$reqNo")
      controller ! Controller.Check(queue.head.url, 2)
      running(queue)
    }
  }

  def enqueueJob(queue: Vector[Job], job: Job): Receive = {
    if (queue.size > 3) {
      sender ! Failed(job.url)
      running(queue)
    } else {
      running(queue :+ job)
    }
  }

}
