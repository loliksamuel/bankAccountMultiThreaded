package concurrency.actor

import akka.actor.Actor
import akka.actor.Props

class Counter2 extends Actor {
  def counter(n: Int): Receive = {
    case "incr" => context.become(counter(n + 1))
    case "get"  => sender ! n
  }

  def receive = counter(0)
}

class Counter2CounterMain extends Actor {

  val counter = context.actorOf(Props[Counter2], "counter2")

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

// ╭─tony@dagobah(0,63,) 20:16:41 ~/repo/perso/scala-lab (master)
// ╰─➤  sbt "run-main akka.Main concurrency.actor.Counter2CounterMain"
// [info] Loading global plugins from /home/tony/.sbt/plugins
// [info] Set current project to scala-lab (in build file:/home/tony/repo/perso/scala-lab/)
// [info] Running akka.Main concurrency.actor.Counter2CounterMain
// count was 3
// [INFO] [12/02/2013 20:24:59.468] [Main-akka.actor.default-dispatcher-4] [akka://Main/user/app-terminator] application s
// upervisor has terminated, shutting down
// [success] Total time: 4 s, completed 2 déc. 2013 20:24:59
