package coursera

trait List[T] {

}

class Nil[T] extends List[T] {

}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {

}
