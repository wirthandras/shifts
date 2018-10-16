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

	private static WebDriver driver;
	private static ScreenMapper screenMapper;

	@Before
	public static void setUp() {
		driver = new FirefoxDriver();
		screenMapper = new ScreenMapper();
	}

	@Given("^Open the application$")
	public void open_the_application() throws Throwable {
		driver.get("http://localhost:9000");
	}

	@Then("^The (.*) button is visible$")
	public void theElementIsVisible(String screenName) throws Throwable {
		WebElement findElement = driver.findElement(By.id(screenMapper.getId(screenName)));
		assertThat(true, is(findElement.isDisplayed()));
	}

	@Then("^The (.*) button is hidden$")
	public void theElementIsHidden(String screenName) throws Throwable {
		WebElement findElement = driver.findElement(By.id(screenMapper.getId(screenName)));
		assertThat(false, is(findElement.isDisplayed()));
	}

	@When("^The (.*) button is pressed$")
	public void theDetailsButtonIsPressed(String screenName) throws Throwable {
		WebElement findElement = driver.findElement(By.id(screenMapper.getId(screenName)));
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
