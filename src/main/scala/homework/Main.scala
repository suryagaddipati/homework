package homework
import java.util.Calendar
object Main extends App {
  println("Hello")
  sealed trait View {
    def sort(records: Seq[Record]): Seq[Record]
  }
  object Views {
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
