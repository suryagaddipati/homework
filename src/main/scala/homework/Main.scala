package homework
import java.util.Calendar
object Main extends App {
  println("Hello")
  sealed trait View
  object Views {
    object Three extends View {} //sorted by last name, descending.
  }
  def sort(records: Seq[Record], v: View): Seq[Record] = records
  case class Record(lastName: String, firstName: String, gender: Char, favoriteColor: String, dateOfBirth: Calendar)
}
