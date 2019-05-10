package homework
import java.util.Calendar
object Main extends App {
  println("Hello")
  sealed trait View
  object Views {
    object One extends View {}
  }
  def sort(records: Seq[Record], v: View): Seq[Record] = ???
  case class Record(lastName: String, firstName: String, gender: Char, favoriteColor: String, dateOfBirth: Calendar)
}
