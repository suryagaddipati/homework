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
  describe("Sorting") {
    it("Sorts by view 3") {
      forAll(recordsGen) { (records: List[Main.Record]) =>
        val sortedByLastNameDesc = Main.sort(records, Main.Views.Three)
        val lastNames = sortedByLastNameDesc .map(_.lastName)
        lastNames shouldBe lastNames.sorted(Ordering.by((_: String).size).reverse)

      }
    }
  }

}
