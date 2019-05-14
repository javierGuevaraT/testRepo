package com.unosquare.test.stepdefinitions;

import static com.unosquare.utils.ConfigReader.globalConfig;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.unosquare.pageobjects.AmazonHomePage;
import com.unosquare.pageobjects.CarPage;
import com.unosquare.pageobjects.ProductDetailsPage;
import com.unosquare.pageobjects.SearchPage;
import com.unosquare.pageobjects.YourAmazonPage;
import com.unosquare.utils.ConfigReader;
import com.unosquare.exceptions.InvalidBrowserException;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {

	//Initialization variables
	private static boolean requiresConfiguration = true;
	private String mainUrl;
	private WebDriver driver;
	
	//Page Objects definitions
	private AmazonHomePage homePage;
	private SearchPage searchPage;
	private ProductDetailsPage detailsPage;
	private CarPage carPage;
	private YourAmazonPage yourPage;
	
	//Variables obtained
	private String priceInSearch;
	private String priceInDetails;
	private String lastString;
	
	@Before
	public void globalSetUp() throws InvalidBrowserException {
		//Cucumber does not permit a globalSetUp, the if is to "configure" only once as a workaround
		if (requiresConfiguration) {
			ConfigReader.loadConfiguration();
			//The current configuration is only with few properties, but can store more depends on the needs.
			mainUrl = globalConfig.getMainUrl();
			
			requiresConfiguration = false;
		}
		
		switch(globalConfig.getBrowser()) {
		
			case FIREFOX://I set this as an example of another implementation. No drivers downloaded. 
						 driver = new FirefoxDriver();
				break;
			case CHROME: //Driver configured in conf.json to work on MacOs, Chrome v74, windows driver also downloaded.
						 System.setProperty("webdriver.chrome.driver", globalConfig.getDriversLocation() + globalConfig.getDriverName());
				     	 driver = new ChromeDriver();
				break;
			default: throw new InvalidBrowserException("Invalid browser definition in config.json"); 
		}
	}
	
	@Given("^the Amazon url$")
	public void the_Amazon_url() throws Throwable {
		driver.get(mainUrl);
	}

	@When("^I search for \"([^\"]*)\" to store the price$")
	public void i_search_for_to_store_the_price(String arg1) throws Throwable {
	    homePage = new AmazonHomePage(driver);
	    searchPage = homePage.searchForProduct(arg1);
	}

	@When("^click on the first result to compare the price in details and add it to the cart$")
	public void click_on_the_first_result_to_compare_the_price_in_details_and_add_it_to_the_cart() throws Throwable {
	    priceInSearch = searchPage.getFirstProductPrice();
	    detailsPage = searchPage.seeFirstProductDetails();
		priceInDetails = detailsPage.getPriceInDetails();
		carPage = detailsPage.addProductToCar();
	}

	@When("^later move to the main page to go to Your Amazon$")
	public void later_move_to_the_main_page_to_go_to_Your_Amazon() throws Throwable {
	    carPage.returnToHomePage();
	    yourPage = homePage.goToYourAmazon();
	}

	@When("^I want to create an account, I have to fill my name \"([^\"]*)\" my email \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void i_want_to_create_an_account_I_have_to_fill_my_name_my_email_and_password(String arg1, String arg2, String arg3) throws Throwable {
		lastString = yourPage.clickOnCreateAccount()
			.onlyfillTextFields(arg1, arg2, arg3)
			.getTextInFieldsContainer();
	}

	@Then("^I should validate the prices I saw are the same$")
	public void i_should_validate_the_prices_I_saw_are_the_same() throws Throwable {
	    assertEquals(priceInSearch, priceInDetails);
	}

	@Then("^the page contains the text in spanish \"([^\"]*)\"$")
	public void the_page_contains_the_text_in_spanish(String arg1) throws Throwable {
		assertTrue(lastString.contains(arg1));
	}
	
	@After
	public void tearDown() {
		driver.close();
	}
}
