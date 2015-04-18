object Fib {
  def fib (n: Int) : Int = {
    if (n < 2) n
    else fib(n-1) + fib(n-2)
  }
}

//    Util.time {
//      for (n <- 0 to 39)
//        println("Fib(%d) = %d".format(n, Fib.fib(n)))
//    }