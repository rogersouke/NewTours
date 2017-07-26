package driver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}
import org.scalatest.selenium.WebBrowser


trait Driver extends FeatureSpec with GivenWhenThen with WebBrowser with Matchers {

  System.setProperty("webdriver.chrome.driver", "/home/roger/Documents/chromedriver")
  implicit val driver: WebDriver = new ChromeDriver()
}
