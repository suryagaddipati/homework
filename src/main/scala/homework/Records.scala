package homework
import java.util.Calendar
import java.time._
import java.time.format._
import java.{util => ju}
import java.util.GregorianCalendar
import java.util.Locale
import java.util.regex._
case class Record(lastName: String, firstName: String, gender: String, favoriteColor: String, dateOfBirth: String)
trait Records {
  def lineToRecord(line: String, seperator: String): Record
  def sort(records: Seq[Record], view: String): Seq[Record] 
}
object RecordsImpl extends Records {

  def formatRecord(r: Record, sep: String): String = List(r.lastName, r.firstName, r.gender, r.favoriteColor, r.dateOfBirth).mkString(sep)

  def lineToRecord(line: String, seperator: String): Record = {
    val lParts = line.split(Pattern.quote(seperator)).map(_.trim)
    Record(lParts(0), lParts(1), lParts(2), lParts(3), lParts(4))
  }

  val df = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
  def formatDate(date: java.util.Calendar): String = {
    val zone = ZoneId.systemDefault();
    df.format(LocalDateTime.ofInstant(date.toInstant, zone))
  }

  def parseDate(dateStr: String): ju.Calendar = {
    val date = LocalDate.parse(dateStr, df);
    GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
  }
  def sort(records: Seq[Record], view: String): Seq[Record] = view match {
    case "1" => {
      val (m, f) = records.partition(_.gender equals "M")
      f.sortBy(_.lastName) union m.sortBy(_.lastName)
    }
    case "2" => records.sortBy(r => parseDate(r.dateOfBirth))
    case "3" => records.sortBy(_.lastName)(Ordering.by((_: String).size).reverse)
    case _   => throw new IllegalArgumentException("Choose between view options 1,2,3.")
  }
}
