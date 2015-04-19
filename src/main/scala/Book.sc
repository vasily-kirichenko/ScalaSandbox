def curry[A, B, C](f: (A, B) => C) : A => (B => C) =
  a => b => f(a, b)

def fmt(i: Int, s: String) = s"$i, $s"
val fmtc = curry(fmt)
fmtc(2)("dd")

def uncurry[A, B, C](f: A => (B => C)) : (A, B) => C =
  (a, b) => f(a)(b)

uncurry(fmtc)(5, "uuu")
