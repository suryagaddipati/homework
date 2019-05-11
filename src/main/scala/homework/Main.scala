package homework
import java.util.Calendar
object Main extends App {

  val source = scala.io.Source.fromFile("file.txt")
  val records = try source.getLines.map(lineToRecord(_,""))
  finally source.close()

  def lineToRecord(line: String, seperator:String): Record = ???

  sealed trait View {
    def sort(records: Seq[Record]): Seq[Record]
  }
  object Views {
    object One extends View { // sorted by gender (females before males) then by last name ascending.
      def sort(records: Seq[Record]): Seq[Record] = {
        val (m, f) = records.partition(_.gender equals 'M')
        f.sortBy(_.lastName) union m.sortBy(_.lastName)
      }
    }
    object Two extends View { //sorted by birth date, ascending
      def sort(records: Seq[Record]): Seq[Record] = records.sortBy(_.dateOfBirth)
    }
    object Three extends View { //sorted by last name, descending.
      def sort(records: Seq[Record]): Seq[Record] = records.sortBy(_.lastName)(Ordering.by((_: String).size).reverse)
    }
  }
  // def sort(records: Seq[Record], v: View): Seq[Record] =
  def sort(records: Seq[Record], v: View): Seq[Record] = v.sort(records)
  case class Record(lastName: String, firstName: String, gender: Char, favoriteColor: String, dateOfBirth: Calendar)
}
