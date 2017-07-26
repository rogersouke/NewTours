package pages

import driver.Driver

/**
  * Created by roger on 24/07/17.
  */
object HomePage extends Driver {

  def homepageRegisterButton = find(xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a"))

  def clickOnRegisterButton = homepageRegisterButton.get.underlying.click()
}
