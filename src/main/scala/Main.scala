object Main {
  def main(args: Array[String]) : Unit = {
    val f = Book24.curry((x: Int, y: Int) => x + y)
    println(f(1)(2))
  }
}