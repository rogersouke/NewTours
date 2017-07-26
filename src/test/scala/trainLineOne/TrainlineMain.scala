package trainLineOne

import driver.Driver
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import pages.{HomePage, RegisterPage}


/**
  * Created by roger on 21/07/17.
  */
class TrainlineMain extends Driver {

  feature("To test Newstours website"){

    scenario("User click on register button"){
      Given("You are on the Newtour Landing Page")
      goTo("http://newtours.demoaut.com/")

      When("I click on the registration link")
//      clickOn(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a"))
      HomePage.clickOnRegisterButton

      Then("content is there")
      val textFoundOnPage = find(cssSelector("body > div > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(3) > td > p > font")).get.text
      val textOnRegistrationPage: String = "To create your account"
//      assert(textFoundOnPage == textOnRegistrationPage)
      textFoundOnPage should include (textOnRegistrationPage)

      And("Page url is correct")
      val validUrl = "http://newtours.demoaut.com/mercuryregister.php"
      val urlOnPage = driver.getCurrentUrl
      urlOnPage should include (validUrl)

    }

    scenario("Register as a user"){
      Given("A user visit the website for the first time")
      goTo("http://newtours.demoaut.com/")
      When("user clicks on register")
      clickOn(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a"))
      And("User fills in the registration form")
      RegisterPage.firstNameField("kam")
      RegisterPage.lastNameField("ngoulong")
      find(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td[2]/input")).get.underlying.sendKeys("1111111")
      find(xpath(".//*[@id='userName']")).get.underlying.sendKeys("test@test.com")
      find(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/input")).get.underlying.sendKeys("5 Barrington Road")
      find(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[8]/td[2]/input")).get.underlying.sendKeys("durrington-on-sea")
      find(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/input")).get.underlying.sendKeys("Worthing")
      find(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[10]/td[2]/input")).get.underlying.sendKeys("West Sussex")
      find(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[11]/td[2]/input")).get.underlying.sendKeys("BN3 4ER")
      singleSel(name("country")).value = "214"
      find(xpath(".//*[@id='email']")).get.underlying.sendKeys("TupacShakur")
      find(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[15]/td[2]/input")).get.underlying.sendKeys("Alleyezonme")
      find(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[16]/td[2]/input")).get.underlying.sendKeys("Alleyezonme")
      And("User clicks submit")
      clickOn(name("register"))
      Then("User will be registered")
      val registrationConfirationText = find(xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[1]/font/b")).get.text
      val textOnRegistrationConfirmationPage: String = "Dear Tupac Shakur,"

      registrationConfirationText shouldBe  textOnRegistrationConfirmationPage

      And("The url contains create_account_success.php")

      val validUrl = "create_account_success.php"
      val urlOnPage = driver.getCurrentUrl
       urlOnPage should include (validUrl)
    }

    scenario("Login with valid registered account") {
      Given("A user has already registerd for newstour")
      goTo("http://newtours.demoaut.com/")
      When("A user clicks on sign-on")
      clickOn(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a"))
      And("User enters the username and password")
      textField(name("userName")).value = "TupacShakur"
      pwdField(name("password")).value = "Alleyezonme"
      And("User clicks submit")
      clickOn(name("login"))
      Then("User will be redirected to flight finder page")
      val flightFinderPage = find(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font")).get.text
      val textExpectedOnFlightFinderPage: String = "Use our Flight Finder to search for the lowest fare on participating airlines. Once you've booked your flight, don't forget to visit the Mercury Tours Hotel Finder to reserve lodging in your destination city."

      flightFinderPage shouldBe textExpectedOnFlightFinderPage

    }

    scenario("Login with an invalid account"){

      Given("An unregisterd user clicks on sign-on")
      goTo("http://newtours.demoaut.com/")
      clickOn(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a"))
      When("User enters the username and password")
      textField(name("userName")).value = "tupacshakur"
      pwdField(name("password")).value = "init"
      And("User clicks on submit")
      clickOn(name("login"))
      Then("The user will remain on thesame page")
      val loginPage = find(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p/font")).get.text
      val textExpectedOnLoginPage: String = "Welcome back to Mercury Tours! Enter your user information to access the member-only areas of this site. If you don't have a log-in, please fill out the registration form."
      loginPage shouldBe  textExpectedOnLoginPage
    }

    scenario("User the flight finder page"){
      Given("A registered user have logged in")
      goTo("http://newtours.demoaut.com/")
      clickOn(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a"))
      textField(name("userName")).value = "TupacShakur"
      pwdField(name("password")).value = "Alleyezonme"
      clickOn(name("login"))
      When("fill in the flight details section")
      find(xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td[2]/b/font/input[2]")).get.underlying.click()
      And("fill in the preferences section")
      singleSel(name("passCount")).value = "1"
      singleSel(name("fromPort")).value = "Zurich"
      singleSel(name("fromMonth")).value = "6"
      singleSel(name("fromDay")).value = "12"
      find(xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/font/input")).get.underlying.click()
//      singleSel(name("airline"): "Blue Skies Airlines")
//      singleSel(name("airline")).value = "Blue Skies Airlines"
      val selectItem = new Select(driver.findElement(By.name("airline")))
      selectItem.selectByIndex(1)


      And("User click on continue")
      Then("user will be directed to select flight page")
    }

  }

}
