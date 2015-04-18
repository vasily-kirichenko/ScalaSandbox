private object Bench {
  def bench(input: Array[Byte]) (digestName: String) = {
    import java.security.MessageDigest
    val digest = MessageDigest.getInstance(digestName)
    Util.time(digest.getAlgorithm) {
      (1 to 100).foreach(_ => digest.digest(input))
    }
  }
}

object Main {
  def main(args: Array[String]) = {
    val input = new Array[Byte](10000000)
    Seq("MD5", "SHA1", "SHA-256", "SHA-512")
      .foreach { Bench.bench (input) }
  }
}