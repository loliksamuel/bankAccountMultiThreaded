package coursera

object Prime {
  def from(n: Int): Stream[Int] = n #:: from(n+1)

  def isPrime(n: Int): Boolean =
    (2 until n) forall (x => n % x != 0)

  def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail filter (_ % s.head != 0))

  def primes(n: Int): Stream[Int] = sieve(from(2)) take n

  // haskell version
  // sieve :: [Int] -> [Int]
  // sieve (p:ps) = p : sieve [n | n <- ps, n `mod` p /= 0]

  def main(args: Array[String]) = {
    println(primes(100) toList)
  }

}

// [info] Running coursera.Prime
// List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541)
// [success] Total time: 2 s, completed 26 juil. 2013 10:39:37
