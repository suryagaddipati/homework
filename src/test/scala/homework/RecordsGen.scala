package homework
import RecordsImpl.formatDate
import org.scalacheck.Gen

object RecordsGen {
  def recordsGen() = {
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
        formatDate(dateOfBirth)
      )
    Gen.listOf(recordGen)
  }

}
