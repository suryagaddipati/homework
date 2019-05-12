package homework

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class WebApp extends ScalatraServlet with JacksonJsonSupport {

  before() {
    contentType = formats("json")
  }

  get("/") {
    "hello"
  }

  protected implicit val jsonFormats: Formats = DefaultFormats
}
