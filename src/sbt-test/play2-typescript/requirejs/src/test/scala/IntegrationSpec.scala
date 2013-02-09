import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import org.openqa.selenium.chrome._

object IntegrationSpec extends Specification {
  "Application" should {
    "return OK whose body is \"1+1=3, a+b=ab\"" in new WithBrowser(webDriver = classOf[ChromeDriver]) {
      browser.goTo("/")
      browser.$("body").getTexts().get(0) must equalTo("1+1=3, a+b=ab")
    }
  }
}
