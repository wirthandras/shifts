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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
public class CucumberSteps {

	private static WebDriver driver;
	private static ScreenMapper screenMapper;
	private static WebDriverWait wait;
	
	private static final String HOST = "http://localhost:9000";

	@Before
	public static void setUp() {
		driver = new FirefoxDriver();
		screenMapper = new ScreenMapper();
		wait = new WebDriverWait(driver, 400);
	}

	@Given("^Open the application$")
	public void open_the_application() throws Throwable {
		driver.get(HOST);
	}

	@Then("^The (.*) button is visible$")
	public void theElementIsVisible(String element) throws Throwable {
		WebElement findElement = driver.findElement(By.id(screenMapper.getId(element)));
		assertThat(true, is(findElement.isDisplayed()));
	}

	@Then("^The (.*) message is visible$")
	public void theMessageIsVisible(String element) throws Throwable {
		WebElement findElement = driver.findElement(By.id(screenMapper.getId(element)));
		assertThat(true, is(findElement.isDisplayed()));
	}

	@Then("^The (.*) message is hidden$")
	public void theMessageIsHidden(String element) throws Throwable {
		WebElement findElement = driver.findElement(By.id(screenMapper.getId(element)));
		assertThat(false, is(findElement.isDisplayed()));
	}

	@Then("^The (.*) is added$")
	public void theElementIsAdded(String elementName) throws Throwable {
		WebElement element = driver.findElement(By.id(screenMapper.getId(elementName)));
		assertThat(true, is(element.isDisplayed()));
	}

	@Then("^The (.*) form is visible$")
	public void theFormIsVisible(String form) throws Throwable {
		WebElement element = driver.findElement(By.id(screenMapper.getId(form)));
		assertThat(true, is(element.isDisplayed()));
	}

	@Then("^The (.*) button is hidden$")
	public void theElementIsHidden(String screenName) throws Throwable {
		WebElement element = driver.findElement(By.id(screenMapper.getId(screenName)));
		assertThat(false, is(element.isDisplayed()));
	}

	@When("^The (.*) button is hovered$")
	public void theDetailsButtonIsPressed(String button) throws Throwable {
		WebElement element = driver.findElement(By.id(screenMapper.getId(button)));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	@When("^The (.*) button is pressed$")
	public void theButtonIsPressed(String button) throws Throwable {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(screenMapper.getId(button))));
		element.click();
	}

	@When("^The (.*) form is hovered$")
	public void theButtonIsHovered(String button) throws Throwable {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(screenMapper.getId(button))));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	@Then("^The (.*) input field is visible$")
	public void thePlateNumberInputFieldIsVisible(String inputField) throws Throwable {
		WebElement findElement = driver.findElement(By.id(screenMapper.getId(inputField)));
		assertThat(true, is(findElement.isDisplayed()));
	}

	@Then("^The Car type dropdown list is visible$")
	public void theCarTypeDropdownListIsVisible() throws Throwable {
		WebElement findElement = driver.findElement(By.id("carTypeDropdown"));
		assertThat(true, is(findElement.isDisplayed()));
	}

	@When("^Set \"([^\"]*)\" to Plate Number input field$")
	public void setToPlateNumberInputField(String arg1) throws Throwable {
		WebElement findElement = driver.findElement(By.id("plateNumber"));
		assertThat(true, is(findElement.isDisplayed()));
	}
	
	@Given("^The user is on the create new car page$")
	public void theUserIsOnTheCreateNewCarPage() throws Throwable {
		driver.get(HOST + "/newcar");
	}
	
	@Given("^The user is on the shift planner page$")
	public void the_user_is_on_the_create_new_car_page() throws Throwable {
		driver.get(HOST + "/shiftplanner");
	}	

	@After
	public static void tearDown() {
		if (driver != null) {
			driver.close();
		}
	}

}
