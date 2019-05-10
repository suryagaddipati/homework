package homework

import org.scalatest._
import scala.collection.JavaConverters._
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import org.scalacheck.Gen

class MainSpec extends FunSpec with Matchers {
  val recordGen = for {
    lastName <- Gen.alphaStr
    firstName <- Gen.alphaStr
    gender <- Gen.oneOf('M', 'F')
    favoriteColor <- Gen.alphaStr
    dateOfBirth <- Gen.calendar
  } yield
    Main.Record(
      lastName,
      firstName: String,
      gender,
      favoriteColor,
      dateOfBirth
    )
  val recordsGen = Gen.listOf(recordGen)
  def formatDate(date: java.util.Calendar): String = {

    import java.time.format.DateTimeFormatter
    import java.util.Locale

    import java.time._
    import java.time.format._
    val df = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

    val zone = ZoneId.systemDefault();
    df.format(LocalDateTime.ofInstant(date.toInstant, zone))
  }
  describe("Sorting") {
    it("Sorts by view 3") {
      forAll(recordsGen) { (records: List[Main.Record]) =>
        val sortedByLastNameDesc = Main.sort(records, Main.Views.Three)
        val lastNames = sortedByLastNameDesc.map(_.lastName)
        lastNames shouldBe lastNames.sorted(Ordering.by((_: String).size).reverse)
      }
    }
    it("Sorts by view 2") {

      forAll(recordsGen) { (records: List[Main.Record]) =>
        val sortedByDateAsc = Main.sort(records, Main.Views.Two)
        val dataOfBirthsSorted = sortedByDateAsc.map(_.dateOfBirth)
        dataOfBirthsSorted.map(formatDate) shouldBe dataOfBirthsSorted.sorted.map(formatDate)
      }
    }
  }

}
