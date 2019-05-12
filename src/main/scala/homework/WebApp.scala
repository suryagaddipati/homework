package homework

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import scala.collection.mutable.ListBuffer
import Cli._


class WebApp extends ScalatraServlet with JacksonJsonSupport {

  before() {
    contentType = formats("json")
  }
val records =  new ListBuffer[Cli.Record]()
 post("/records") {
  }

  protected implicit val jsonFormats: Formats = DefaultFormats
}
