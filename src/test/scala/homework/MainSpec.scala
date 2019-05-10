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
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean =
    if (as.size <= 1) true
    else ordered(as(0), as(1)) && isSorted(as.drop(1), ordered)

  describe("Sorting") {
    it("Sorts by view 1") {
      forAll(recordsGen) { (records: List[Main.Record]) =>
        val sortedRecords = Main.sort(records, Main.Views.One)
        isSorted(sortedRecords.map(_.firstName).toArray, ((x: String, y: String) => x < y)) shouldBe true

      }
    }
  }

}
