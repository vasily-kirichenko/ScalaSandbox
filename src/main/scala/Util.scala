object Util {
  def time[A](name: String) (f: => A) = {
    val s = System.nanoTime
    val ret = f
    println(s"$name elapsed ${(System.nanoTime-s)/1e6} ms")
    ret
  }
}