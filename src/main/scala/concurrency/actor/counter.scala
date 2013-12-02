package concurrency.actor

import akka.actor.Actor
import akka.actor.Props

class Counter1 extends Actor {
  var count = 0

  def receive = {
    case "incr" =>
      println("Received 'incr' message")
      count += 1
    case "get"  => sender ! count
  }
}

class Counter1CounterMain extends Actor {

  val counter = context.actorOf(Props[Counter1], "counter1")

  counter ! "incr"
  counter ! "incr"
  counter ! "incr"
  counter ! "get"

  def receive = {
    case count: Int =>
      println(s"count was $count")
      context.stop(self)
  }
}

// ╭─tony@dagobah(1,57,) 20:10:48 ~/repo/perso/scala-lab (master)
// ╰─➤  sbt "run-main akka.Main concurrency.actor.CounterMain"
// [info] Loading global plugins from /home/tony/.sbt/plugins
// [info] Set current project to scala-lab (in build file:/home/tony/repo/perso/scala-lab/)
// [info] Running akka.Main concurrency.actor.CounterMain
// count was 3
// [INFO] [12/02/2013 20:11:36.294] [Main-akka.actor.default-dispatcher-4] [akka://Main/user/app-terminator] application s
// upervisor has terminated, shutting down
// [success] Total time: 4 s, completed 2 déc. 2013 20:11:36
