package homework
import java.util.Calendar
object Main extends App {
  println("Hello")
  sealed trait View
  object Views {
    object Three extends View {} //sorted by last name, descending.
    object Two extends View {} //sorted by last name, descending.
  }
  def sort(records: Seq[Record], v: View): Seq[Record] = records.sortBy(_.lastName)(Ordering.by((_: String).size).reverse)
  case class Record(lastName: String, firstName: String, gender: Char, favoriteColor: String, dateOfBirth: Calendar)
}
