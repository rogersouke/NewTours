package pages

import driver.Driver


object RegisterPage extends Driver{

  def firstNameField(whateveryouwanttocallthis: String) = textField(name("firstName")).value = whateveryouwanttocallthis

  def lastNameField(anything: String) = textField(name("lastName")).value = anything


}
