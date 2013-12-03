package concurrency.actor

object Controller {
  case class Check(link: String, depth: Int)
}
