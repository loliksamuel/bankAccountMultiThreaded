package coursera

object Prime {
  def isPrime(n: Int): Boolean =
    (2 until n) forall (x => n % x != 0)
}
