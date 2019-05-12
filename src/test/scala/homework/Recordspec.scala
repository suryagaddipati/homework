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
import Records._

class RecordsSpec extends FunSpec with Matchers {
  describe("Output formatting") {

    it("Prints in the same format as the input") {
      val record = Record("Meow", "Chairman", "M", "Red", "12/26/1893")
      assert( formatRecord(record," | ") == "Meow | Chairman | M | Red | 12/26/1893")
    }
  }

  describe("Record Parsing") {
    it("Parses a record from string using the delimiter") {
      val record = lineToRecord("Meow | Chairman | M | Red | 12/26/1893", " | ")

      assert(record == Record("Meow", "Chairman", "M", "Red","12/26/1893"))
    }
  }
  describe("Sorting") {
    val recordGen = for {
      lastName      <- Gen.alphaStr
      firstName     <- Gen.alphaStr
      gender        <- Gen.oneOf("M", "F")
      favoriteColor <- Gen.alphaStr
      dateOfBirth   <- Gen.calendar
    } yield
      Record(
        lastName,
        firstName: String,
        gender,
        favoriteColor,
        formatDate( dateOfBirth)
      )
    val recordsGen = Gen.listOf(recordGen)
    it("Sorts by view 3") {
      forAll(recordsGen) { (records: List[Record]) =>
        val sortedByLastNameDesc = sort(records, "3")
        val lastNames            = sortedByLastNameDesc.map(_.lastName)
        lastNames shouldBe lastNames.sorted(Ordering.by((_: String).size).reverse)
      }
    }
    it("Sorts by view 2") {
      forAll(recordsGen) { (records: List[Record]) =>
        val sortedByDateAsc    = sort(records, "2")
        val dataOfBirthsSorted = sortedByDateAsc.map(_.dateOfBirth)
        dataOfBirthsSorted shouldBe dataOfBirthsSorted.map(parseDate(_)).sorted.map(formatDate)
      }
    }
    it("Sorts by view 1") {
      forAll(recordsGen) { (records: List[Record]) =>
        val sortedByGenderLastNameAsc = sort(records, "1")
        val (males, females)          = sortedByGenderLastNameAsc.partition(_.gender equals "M")
        val gendersSorted             = sortedByGenderLastNameAsc.map(_.gender)
        gendersSorted shouldBe gendersSorted.sorted
        males.map(_.lastName) shouldBe males.map(_.lastName).sorted
        females.map(_.lastName) shouldBe females.map(_.lastName).sorted
      }
    }
    it("Throws Exception on invalid view option"){
      assertThrows[IllegalArgumentException] { 
        sort(List(), "55")
      }
    }
  }

}
