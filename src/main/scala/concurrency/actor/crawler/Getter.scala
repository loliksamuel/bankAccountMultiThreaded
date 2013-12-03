package concurrency.actor

object Getter {
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

object GetterTryout extends Application {
  val bodyToParse1 = """<a class=gb1 href="http://www.google.fr/imghp?hl=fr&tab=wi">Images</a> <a class=gb1 href="http://maps.google.fr/maps?hl=fr&tab=wl">Maps</a> <a class=gb1 href="https://play.google.com/?hl=fr&tab=w8">Play</a> <a class=gb1 href="http://www.youtube.com/?gl=FR&tab=w1">YouTube</a>"""

  Getter.findLinks(bodyToParse1) foreach println // prints nothing, the class attribute does not pass

  val bodyToParse2 = """<a href="http://www.google.fr/imghp?hl=fr&tab=wi">Images</a> <a href="http://maps.google.fr/maps?hl=fr&tab=wl">Maps</a> <a href="https://play.google.com/?hl=fr&tab=w8">Play</a> <a href="http://www.youtube.com/?gl=FR&tab=w1">YouTube</a>"""

  Getter.findLinks(bodyToParse2) foreach println // prints the links

  val bodyToParse3 = """<a href="link1">Images</a> <a href="link2">Maps</a> <a href="link3">Play</a> <a href="link4">YouTube</a>"""

  Getter.findLinks(bodyToParse3) foreach println // prints the links

}
