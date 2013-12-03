package concurrency.actor

import akka.actor.{Actor, ActorSystem, Props}

class Toggle extends Actor {
  def happy: Receive = {
    case "How are you?" =>
      sender ! "happy"
      context become sad
    case _ => stop()
  }

  def sad: Receive = {
    case "How are you?" =>
      sender ! "sad"
      context become happy
    case _ => stop()
  }

  def receive = happy

  def stop(): Unit = context.stop(self)
}

import akka.testkit.{TestProbe, TestKit, ImplicitSender}
import scala.concurrent.duration._

object ToggleTest extends Application {
  // First sample
  implicit val system = ActorSystem("TestSys")
  val toggle = system.actorOf(Props[Toggle])
  val p = TestProbe()

  p.send(toggle, "How are you?")
  p.expectMsg("happy")

  p.send(toggle, "How are you?")
  p.expectMsg("sad")

  p.send(toggle, "unknown")
  p.expectNoMsg(1.second)

  system.shutdown

  // running inside a TestKit
  new TestKit(ActorSystem("TestSys")) with ImplicitSender {
    val toggle = system.actorOf(Props[Toggle])
    toggle ! "How are you?"
    expectMsg("happy")
    toggle ! "How are you?"
    expectMsg("sad")
    toggle ! "unknown"
    expectNoMsg(1.second)
    system.shutdown()
  }

  println("done")

}
