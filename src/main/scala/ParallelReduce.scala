import concurrent.duration.Duration
import concurrent.{Await, Future}
import concurrent.ExecutionContext.Implicits.global

object ParallelReduce {
  def reduce [A](f: (A, A) => A, a: Array[A]) : A = {
    def reduceRec(a: Array[A]) : Future[A] = {
      a.length match {
        case 1 => Future(a(0))
        case 2 => Future {
          f(a(0), a(1))
        }
        case len => {
          val h = len / 2
          val o1a = reduceRec(a.slice(0, h))
          for {
            o2 <- reduceRec(a.slice(h, a.length))
            o1 <- o1a
          } yield f(o1, o2)
        }
      }
    }

    if (a.isEmpty)
      throw new Exception("Sequence contains no elements")
    else
      Await.result(reduceRec(a), Duration.Inf)
  }

  def run() = {
    val a = Array.range(1, 50000000)
    Util.time { reduce((x: Int, y: Int) => x + y, a) }
  }
}
