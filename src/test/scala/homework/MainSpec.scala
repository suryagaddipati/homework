package homework

import org.scalatest._
import scala.collection.JavaConverters._
import org.scalatest.prop.GeneratorDrivenPropertyChecks._
import org.scalacheck.Gen
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.time._
import java.time.format._
import java.{util => ju}
import java.util.GregorianCalendar


class MainSpec extends FunSpec with Matchers {
  def formatDate(date: java.util.Calendar): String = {
  val df   = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
  val zone = ZoneId.systemDefault();
    df.format(LocalDateTime.ofInstant(date.toInstant, zone))
  }
  describe("Record Parsing") {
    it("Parses a record from string using the delimiter") {
      val record = Main.lineToRecord("Meow | Chairman | M | Red | 12/26/1893", '|')

      assert(record == Main.Record("Meow", "Chairman", 'M', "Red", Main.parseDate("12/26/1893")))
    }
  }
  describe("Sorting") {
    val recordGen = for {
      lastName      <- Gen.alphaStr
      firstName     <- Gen.alphaStr
      gender        <- Gen.oneOf('M', 'F')
      favoriteColor <- Gen.alphaStr
      dateOfBirth   <- Gen.calendar
    } yield
      Main.Record(
        lastName,
        firstName: String,
        gender,
        favoriteColor,
        dateOfBirth
      )
    val recordsGen = Gen.listOf(recordGen)
    it("Sorts by view 3") {
      forAll(recordsGen) { (records: List[Main.Record]) =>
        val sortedByLastNameDesc = Main.sort(records, Main.Views.Three)
        val lastNames            = sortedByLastNameDesc.map(_.lastName)
        lastNames shouldBe lastNames.sorted(Ordering.by((_: String).size).reverse)
      }
    }
    it("Sorts by view 2") {
      forAll(recordsGen) { (records: List[Main.Record]) =>
        val sortedByDateAsc    = Main.sort(records, Main.Views.Two)
        val dataOfBirthsSorted = sortedByDateAsc.map(_.dateOfBirth)
        dataOfBirthsSorted.map(formatDate) shouldBe dataOfBirthsSorted.sorted.map(formatDate)
      }
    }
    it("Sorts by view 1") {
      forAll(recordsGen) { (records: List[Main.Record]) =>
        val sortedByGenderLastNameAsc = Main.sort(records, Main.Views.One)
        val (males, females)          = sortedByGenderLastNameAsc.partition(_.gender equals 'M')
        val gendersSorted             = sortedByGenderLastNameAsc.map(_.gender)
        gendersSorted shouldBe gendersSorted.sorted
        males.map(_.lastName) shouldBe males.map(_.lastName).sorted
        females.map(_.lastName) shouldBe females.map(_.lastName).sorted
      }
    }
  }

}
