import java.net.URL
import org.openqa.selenium.{WebDriver, Capabilities}
import org.openqa.selenium.remote.{DesiredCapabilities, RemoteWebDriver}
import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import org.openqa.selenium.chrome._
import scala.collection.JavaConversions._

object IntegrationSpec extends Specification {

  class SauceWebDriver(remoteAddress: URL, desiredCapabilities: Capabilities) extends RemoteWebDriver(remoteAddress, desiredCapabilities) {
    def this() = {
      this({
        val username = System.getenv("SAUCE_USERNAME")
        val accessKey = System.getenv("SAUCE_ACCESS_KEY")
        new URL(s"http://${username}:${accessKey}@localhost:4445/wd/hub")
      }, {
        new DesiredCapabilities(Map("tunnel-identifier" -> System.getenv("TRAVIS_JOB_NUMBER")))
      })
    }
  }

  def webDriverForEnv: Class[WebDriver] = {
    if (System.getenv("SAUCE_USERNAME") != null)
      classOf[SauceWebDriver].asInstanceOf[Class[WebDriver]]
    else
      classOf[ChromeDriver].asInstanceOf[Class[WebDriver]]
  }

  "Application" should {
    "return OK whose body is \"1+1=3, a+b=ab\"" in new WithBrowser(webDriver = webDriverForEnv) {
      browser.goTo("/")
      val root = browser.$("html")
      browser.$("body").getTexts().get(0) aka ("text of <body/> in: " + root.first.html) must equalTo("1+1=3, a+b=ab")
    }
  }
}
