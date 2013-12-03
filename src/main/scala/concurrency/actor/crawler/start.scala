package concurrency.actor

import com.ning.http.client.AsyncHttpClient

object WebClient {
  private val client = new AsyncHttpClient

  case class BadStatus(status: Int) extends RuntimeException

  def get(url: String) = {
    val response = client.prepareGet(url).execute().get
    if (response.getStatusCode < 400)
      response.getResponseBodyExcerpt(131072)
    else
      throw new BadStatus(response.getStatusCode)
  }
}
