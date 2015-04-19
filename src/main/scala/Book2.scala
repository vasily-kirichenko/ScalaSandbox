object Book24 {
  def curry[A, B, C](f: (A, B) => C) : A => (B => C) =
    a => b => f(a, b)
}
