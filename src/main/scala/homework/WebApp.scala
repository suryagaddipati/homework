package homework

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import scala.collection.mutable.ListBuffer
import Records._

class WebApp extends ScalatraServlet with JacksonJsonSupport {

  before() {
    contentType = formats("json")
  }
  val records = new ListBuffer[Record]()
  post("/records") {
    val recordStr = request.body
    printf(recordStr)
    records += lineToRecord(recordStr," | ") 
  }
  get("/records/gender") {
    records 
  }

  protected implicit val jsonFormats: Formats = DefaultFormats
}
