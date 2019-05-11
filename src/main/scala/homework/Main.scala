package homework
import java.util.Calendar
import java.time._
import java.time.format._
import java.{util => ju}
import java.util.GregorianCalendar
import java.util.Locale
object Main extends App {

  val source = scala.io.Source.fromFile("file.txt")
  val records = try source.getLines.map(lineToRecord(_, '|'))
  finally source.close()

  def lineToRecord(line: String, seperator: Char): Record = {
    val lParts = line.split(seperator).map(_.trim)
    Record(lParts(0), lParts(1), lParts(2).charAt(0), lParts(3), parseDate(lParts(4)))
  }

  def parseDate(dateStr: String): ju.Calendar = {
    val df   = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
    val date = LocalDate.parse(dateStr, df);
    GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
  }
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
  def sort(records: Seq[Record], v: View): Seq[Record] = v.sort(records)
  case class Record(lastName: String, firstName: String, gender: Char, favoriteColor: String, dateOfBirth: Calendar)
}
