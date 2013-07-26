package coursera

// Glass: Int
// State: Vector[Int] (one entry per glass)
// Moves:
//     Empty(glass)
//     Fill(glass)
//     Pour(from, to)

class Pouring(capacity: Vector[Int]) {
// states

  type State = Vector[Int]

  val initialState = capacity map (x => 0)

// moves

  trait Move {
    def change(state: State): State
  }

  case class Empty(glass: Int) extends Move {
    def change(state: State) = state updated (glass, 0)
  }

  case class Fill(glass: Int)  extends Move {
    def change(state: State) = state updated (glass, capacity(glass))

  }

  case class Pour(from: Int, to: Int) extends Move {
    def change(state: State) = {
      val freeVolumeInTo =  capacity(to) - state(to)
      val amount =  state(from) min freeVolumeInTo
      state updated (from, state(from) - amount)
      state updated (to, state(to) + amount)
      state
    }
  }

  val glasses = 0 until capacity.length

  val moves =
    (for (g <- glasses) yield Empty(g)) ++
      (for (g <- glasses) yield Fill(g)) ++
      (for (from <- glasses; to <- glasses) yield Pour(from, to))

// Paths

    class Path(history: List[Move], val endState: State) {
      def extend(move: Move) = new Path(move :: history, move change endState)

      override def toString = (history.reverse mkString " ") + " --> " + endState
     }

    val initialPath = new Path(Nil, initialState)

    def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
        if (paths.isEmpty) Stream.empty
        else {
          val more = for {
              path <- paths
              next <- moves map path.extend
              if !(explored contains next.endState)
            } yield next
          paths #:: from(more, explored ++ (more map (_.endState)))
        }

  val pathSets = from(Set(initialPath), Set(initialState))

  def solutions(target: Int): Stream[Path] =
      for {
        pathSet <- pathSets
        path <- pathSet
        if path.endState contains target
      } yield path

}

object WaterPouringProblem {
  def main(args: Array[String]) = {
    val problem = new Pouring(Vector(4, 7, 19))

    println(problem.moves)
    println(problem.pathSets.take(3).toList)
    println(problem.solutions(17))
  }
}

// [info] Running coursera.WaterPouringProblem
// Vector(Empty(0), Empty(1), Fill(0), Fill(1), Pour(0,0), Pour(0,1), Pour(1,0), Pour(1,1))
// List(Set( --> Vector(0, 0)), Set(Pour(1,1) --> Vector(0, 0), Empty(0) --> Vector(0, 0), Fill(1) --> Vector(0, 7), Pour(0,1) --> Vector(0, 0), Fill(0) --> Vector(4, 0), Pour(0,0) --> Vector(0, 0), Pour(1,0) --> Vector(0, 0), Empty(1) --> Vector(0, 0)), Set(Fill(0) Empty(0) --> Vector(0, 0), Fill(1) Pour(0,0) --> Vector(0, 7), Pour(0,1) Empty(1) --> Vector(0, 0), Pour(1,1) Empty(1) --> Vector(0, 0), Empty(0) Empty(1) --> Vector(0, 0), Pour(1,1) Fill(1) --> Vector(0, 7), Empty(1) Empty(0) --> Vector(0, 0), Pour(1,1) Pour(1,0) --> Vector(0, 0), Empty(0) Pour(0,0) --> Vector(0, 0), Pour(0,0) Empty(0) --> Vector(0, 0), Empty(1) Empty(1) --> Vector(0, 0), Pour(0,1) Pour(0,0) --> Vector(0, 0), Empty(0) Pour(1,1) --> Vector(0, 0), Pour(1,0) Pour(1,0) --> Vector(0, 0), Pour(1,1) Pour(0,0) --> Vector(0, 0), Pour(0,0) Fill(0) --> Vector(4, 0), Fill(0) Empty(1) --> Vector(4, 0), Fill(1) Pour(1,1) --> Vector(0, 7), Empty(0) Fill(0) --> Vector(4, 0), Fill(0) Pour(0,1) --> Vector(4, 0), Pour(0,0) Fill(1) --> Vector(0, 7), Fill(0) Fill(0) --> Vector(4, 0), Empty(0) Pour(0,1) --> Vector(0, 0), Pour(1,0) Empty(1) --> Vector(0, 0), Pour(0,1) Fill(1) --> Vector(0, 7), Empty(0) Pour(1,0) --> Vector(0, 0), Pour(0,0) Pour(1,0) --> Vector(0, 0), Pour(0,0) Pour(1,1) --> Vector(0, 0), Fill(1) Empty(0) --> Vector(0, 7), Pour(0,1) Empty(0) --> Vector(0, 0), Pour(0,1) Pour(0,1) --> Vector(0, 0), Pour(1,1) Empty(0) --> Vector(0, 0), Pour(0,1) Pour(1,1) --> Vector(0, 0), Pour(0,0) Empty(1) --> Vector(0, 0), Pour(1,0) Pour(0,0) --> Vector(0, 0), Empty(1) Pour(1,1) --> Vector(0, 0), Pour(0,0) Pour(0,0) --> Vector(0, 0), Empty(1) Pour(1,0) --> Vector(0, 0), Fill(0) Pour(0,0) --> Vector(4, 0), Fill(1) Empty(1) --> Vector(0, 0), Pour(1,1) Fill(0) --> Vector(4, 0), Empty(0) Empty(0) --> Vector(0, 0), Fill(0) Pour(1,0) --> Vector(4, 0), Fill(1) Pour(0,1) --> Vector(0, 7), Pour(1,1) Pour(0,1) --> Vector(0, 0), Pour(1,0) Pour(0,1) --> Vector(0, 0), Empty(0) Fill(1) --> Vector(0, 7), Fill(1) Pour(1,0) --> Vector(0, 7), Pour(0,0) Pour(0,1) --> Vector(0, 0), Empty(1) Fill(0) --> Vector(4, 0), Empty(1) Fill(1) --> Vector(0, 7), Fill(0) Pour(1,1) --> Vector(4, 0), Pour(1,0) Pour(1,1) --> Vector(0, 0), Fill(1) Fill(0) --> Vector(4, 7), Empty(1) Pour(0,0) --> Vector(0, 0), Pour(0,1) Fill(0) --> Vector(4, 0), Fill(0) Fill(1) --> Vector(4, 7), Fill(1) Fill(1) --> Vector(0, 7), Empty(1) Pour(0,1) --> Vector(0, 0), Pour(1,1) Pour(1,1) --> Vector(0, 0), Pour(1,0) Fill(1) --> Vector(0, 7), Pour(0,1) Pour(1,0) --> Vector(0, 0), Pour(1,0) Fill(0) --> Vector(4, 0), Pour(1,0) Empty(0) --> Vector(0, 0)))
// [success] Total time: 2 s, completed 26 juil. 2013 11:37:54
