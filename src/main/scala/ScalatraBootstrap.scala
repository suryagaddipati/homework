import homework._ 
import org.scalatra._
import javax.servlet.ServletContext
import scala.collection.mutable.ListBuffer

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new WebApp(RecordsImpl,new ListBuffer[Record]()), "/*")
  }
}
