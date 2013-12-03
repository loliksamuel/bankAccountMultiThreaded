package concurrency.actor

import akka.actor.{Actor, Status}
import akka.pattern.pipe
import java.util.concurrent.Executor
import scala.concurrent.ExecutionContext
import scala.concurrent._

object Getter {
  case object Done
  case object Abort

//  case
  val A_TAG = "(?i)<a ([^>]+)>.+?</a>".r
  val HREF_ATTR = """\s*(?i)href\s*=\s*(?:"([^"]*)"|'([^']*)'|([^'">\s]+))\s*""".r

  def findLinks(body: String): Iterator[String] = {
    for {
      anchor <- A_TAG.findAllMatchIn(body)
      HREF_ATTR(dquot, quot, bare) <- anchor.subgroups
    } yield if (dquot != null) dquot
    else if (quot != null) quot
    else bare
  }
}

/** Class in charge of fetching the url's content.
  * The depth transits simply in the message from input to output.
  */
class Getter(url: String, depth: Int) extends Actor {
  import Getter._

  // flawed design in akka for the moment
  implicit val exec = context.dispatcher.asInstanceOf[Executor with ExecutionContext]

  // compute the http request in future, when done, sends to itself the result (cf. receive method)
  val requestFuture = WebClient get url pipeTo self

  def receive = {
    case body: String => // send as much checks to the controller as there are links
                         for(link <- findLinks(body)) context.parent ! Controller.Check(link, depth)
                         stop()
    case _            => stop()
  }

  def stop(): Unit = {
    context.parent ! Done
    context.stop(self)
  }
}

object GetterTryout extends Application {
  val bodyToParse1 = """<a class=gb1 href="http://www.google.fr/imghp?hl=fr&tab=wi">Images</a> <a class=gb1 href="http://maps.google.fr/maps?hl=fr&tab=wl">Maps</a> <a class=gb1 href="https://play.google.com/?hl=fr&tab=w8">Play</a> <a class=gb1 href="http://www.youtube.com/?gl=FR&tab=w1">YouTube</a>"""

  Getter.findLinks(bodyToParse1) foreach println // prints nothing, the class attribute does not pass

  val bodyToParse2 = """<a href="http://www.google.fr/imghp?hl=fr&tab=wi">Images</a> <a href="http://maps.google.fr/maps?hl=fr&tab=wl">Maps</a> <a href="https://play.google.com/?hl=fr&tab=w8">Play</a> <a href="http://www.youtube.com/?gl=FR&tab=w1">YouTube</a>"""

  Getter.findLinks(bodyToParse2) foreach println // prints the links

  val bodyToParse3 = """<a href="link1">Images</a> <a href="link2">Maps</a> <a href="link3">Play</a> <a href="link4">YouTube</a>"""

  Getter.findLinks(bodyToParse3) foreach println // prints the links

}
