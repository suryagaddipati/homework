package homework
import RecordsImpl._
object Cli {
  def main(args: Array[String]): Unit = {
    val source = scala.io.Source.fromFile(args(0))
    try {
      val records = source.getLines.map(lineToRecord(_, args(1)))
      sort(records.toIndexedSeq, args(2)).foreach(r => println(formatRecord(r, " | ")))
    } finally {
      source.close()
    }
  }
}
