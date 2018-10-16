package hu.wirthandras.shifts.cucumber;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

/**
 * Gecko Driver is required for execution
 */
@RunWith(Cucumber.class)
//TODO find the correct solution
@Ignore("Restrict to run with JUnit")
public class NavigationCucumberTest {

	@Autowired
	private static WebDriver driver;

	@Before
	public static void setUp() {
		driver = new FirefoxDriver();
	}

	@Given("^Open the application$")
	public void open_the_application() throws Throwable {
		driver.get("http://localhost:9000");
	}

	@Then("^The Home button is visible$")
	public void the_Home_button_is_available() throws Throwable {
		WebElement findElement = driver.findElement(By.id("home"));
		assertThat(true, is(findElement.isDisplayed()));
	}

	@Then("^The Generate button is visible$")
	public void the_Generate_button_is_available() throws Throwable {
		WebElement findElement = driver.findElement(By.id("generate"));
		assertThat(true, is(findElement.isDisplayed()));
	}

	@Then("^The Employees button is hidden$")
	public void the_Employees_button_is_hidden() throws Throwable {
		WebElement findElement = driver.findElement(By.id("employees"));
		assertThat(false, is(findElement.isDisplayed()));
	}

	@Then("^The Cars button is hidden$")
	public void the_Cars_button_is_hidden() throws Throwable {
		WebElement findElement = driver.findElement(By.id("cars"));
		assertThat(false, is(findElement.isDisplayed()));
	}

	@Then("^The Shifts button is hidden$")
	public void the_Shifts_button_is_hidden() throws Throwable {
		WebElement findElement = driver.findElement(By.id("shifts"));
		assertThat(false, is(findElement.isDisplayed()));
	}

	@Then("^The Employees button is visible$")
	public void the_Employees_button_is_visible() throws Throwable {
		WebElement findElement = driver.findElement(By.id("employees"));
		assertThat(true, is(findElement.isDisplayed()));
	}

	@Then("^The Cars button is visible$")
	public void the_Cars_button_is_visible() throws Throwable {
		WebElement findElement = driver.findElement(By.id("cars"));
		assertThat(true, is(findElement.isDisplayed()));
	}

	@Then("^The Shifts button is visible$")
	public void the_Shifts_button_is_visible() throws Throwable {
		WebElement findElement = driver.findElement(By.id("shifts"));
		assertThat(true, is(findElement.isDisplayed()));
	}

	@Then("^The New Car button is hidden$")
	public void the_New_Car_button_is_hidden() throws Throwable {
		WebElement findElement = driver.findElement(By.id("newCar"));
		assertThat(false, is(findElement.isDisplayed()));
	}

	@Then("^The New Employee button is hidden$")
	public void the_New_Employee_button_is_hidden() throws Throwable {
		WebElement findElement = driver.findElement(By.id("newEmployee"));
		assertThat(false, is(findElement.isDisplayed()));
	}

	@When("^The Details button is pressed$")
	public void the_Details_buttin_is_pressed() throws Throwable {
		WebElement findElement = driver.findElement(By.id("details"));
		Actions action = new Actions(driver);
		action.moveToElement(findElement).build().perform();
	}

	@After
	public static void tearDown() {
		if (driver != null) {
			driver.close();
		}
	}

}
