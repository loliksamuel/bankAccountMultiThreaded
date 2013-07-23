package coursera

trait IntList {

}

class Nil extends IntList {

}

class Cons(val head: Int, val tail: IntList) extends IntList {

}
