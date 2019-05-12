package homework
import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.scalamock.scalatest.MockFactory
import scala.collection.mutable.ListBuffer

class WebAppSpec extends ScalatraSuite with FunSuiteLike with MockFactory {
  val records          = mock[Records]
  val recordList = new ListBuffer[Record]()
  addServlet(new WebApp(records, recordList), "/*")

  test("sorting records by gender") {
    (records.sort _) expects (recordList, "1")
    get("/records/gender") {}
  }

  test("sorting records by birthDate") {
    (records.sort _) expects (recordList, "2")
    get("/records/birthdate") {}
  }
  test("sorting records by lastName") {
    (records.sort _) expects (recordList, "3")
    get("/records/name") {}
  }

  test("posting records"){
    (records.lineToRecord _).expects("record"," | ")
    post("/records","record"){}
  }
}
