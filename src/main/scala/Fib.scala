import scala.annotation.tailrec
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object Fib {
  def sfib(n: Int): Int = {
    if (n < 2) n
    else sfib(n - 1) + sfib(n - 2)
  }

  import scala.concurrent.ExecutionContext.Implicits.global

  def fib(n: Int, level: Int): Future[Int] = {
    if (n < 2) {
      Future(n)
    }
    else if (n < level) {
      Future(sfib(n))
    }
    else {
      val n2f = fib(n - 2, level)
      for {
        n1 <- fib(n - 1, level)
        n2 <- n2f
      } yield n1 + n2
    }
  }

  def run() {
    Util.time("seq") {
      Fib.sfib(42)
    }
    (13 to 20) foreach { level =>
      Util.time(level.toString) {
        Await.result(Fib.fib(42, level), Duration.Inf)
      }
    }
  }
}

//    Util.time {
//      for (n <- 0 to 39)
//        println("Fib(%d) = %d".format(n, Fib.fib(n)))
//    }