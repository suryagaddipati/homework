package homework
import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike
import org.json4s._
import org.json4s.jackson.JsonMethods._

class WebAppSpec extends ScalatraSuite with FunSuiteLike {

  protected implicit val jsonFormats: Formats = DefaultFormats
  addServlet(classOf[WebApp], "/*")

  test("posting records") {
    post("/records", "") {
      status should equal(200)
    }

    post("/records", "Meow | Chairman | M | Red | 12/26/1893") {

      get("/records/gender") {

        status should equal(200)
        val parsedBody = parse(body)
        val records = parsedBody.extract[List[Cli.Record]]
        records.size should equal(1)
      }
    }

  }
}
