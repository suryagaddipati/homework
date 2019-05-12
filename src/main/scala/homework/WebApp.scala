package homework

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import scala.collection.mutable.ListBuffer

class WebApp(rc: Records, recordList: ListBuffer[Record]) extends ScalatraServlet with JacksonJsonSupport {
  before() {
    contentType = formats("json")
  }
  post("/records") {
    val recordStr = params.get("record").get 
    val seperator = params.get("seperator").get 
    recordList += rc.lineToRecord(recordStr, seperator)
  }
  get("/records/gender") {
    rc.sort(recordList, "1")
  }

  get("/records/birthdate") {
    rc.sort(recordList, "2")
  }

  get("/records/name") {
    rc.sort(recordList, "3")
  }
  protected implicit val jsonFormats: Formats = DefaultFormats
}
