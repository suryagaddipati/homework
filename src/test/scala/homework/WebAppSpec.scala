package homework
import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike
import org.json4s._
import org.json4s.jackson.JsonMethods._
import Records._

class WebAppSpec extends ScalatraSuite with FunSuiteLike {

  protected implicit val jsonFormats: Formats = DefaultFormats
  addServlet(classOf[WebApp], "/*")

  test("posting records") {
    post("/records", "Meow | Chairman | M | Red | 12/26/1893") {
      status should equal(200)
    }

    get("/records/gender") {
      post("/records", "Meow | Chairman | M | Red | 12/26/1893") {}
      status should equal(200)
      val parsedBody = parse(body)
      val records    = parsedBody.extract[List[Record]]
      records.size should equal(1)
      records(0) should equal( Record("Meow", "Chairman", "M", "Red","12/26/1893"))
    }

  }
}
