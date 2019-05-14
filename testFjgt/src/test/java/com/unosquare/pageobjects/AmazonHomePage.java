package com.unosquare.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonHomePage extends PageBase{

	@FindBy(how = How.ID, using = "twotabsearchtextbox") 
    private WebElement inputSearch;
	
	@FindBy(how = How.XPATH, using = "//span[@id='nav-search-submit-text']/following-sibling::input") 
    private WebElement searchButton;
	
	@FindBy(how = How.ID, using = "issDiv0")
	private WebElement firstSuggestionInSearch;
	
	@FindBy(how = How.XPATH, using = "//img[@data-image-index='0']")
	private WebElement firstSearchedProduct;
	
	@FindBy(how = How.ID, using = "nav-your-amazon")
	private WebElement yourAmazonLink;
	
	@FindBy(how = How.ID, using = "ap_email")
	private WebElement emailField;
	
	private WebDriver driver;
	
	//We can create a custom expected condition to wait for complete page
	//to load in case of ajax or API calls, for the simplicity of exercise 
	// and demostration I used the common waits.
	//General wait to be used in the class.
	private WebDriverWait wait;
	
    public AmazonHomePage(WebDriver driver) {
    	PageFactory.initElements(driver, this);
    	wait = new WebDriverWait(driver, 10);
    	this.driver = driver;
	}
    
    public SearchPage searchForProduct(String product) {
    	inputSearch.sendKeys(product);
    	searchButton.click();
    	wait.until(ExpectedConditions.visibilityOf(firstSearchedProduct));
    	
    	return new SearchPage(driver);
    }
    
    public SearchPage searchForTheFirstSuggestionProduct(String product) {
    	inputSearch.sendKeys(product);
    	wait.until(ExpectedConditions.visibilityOf(firstSuggestionInSearch));
    	firstSuggestionInSearch.click();
    	wait.until(ExpectedConditions.visibilityOf(firstSearchedProduct));
    	
    	return new SearchPage(driver);
    }
    
    public SearchPage searchForTheSuggestedProductNumber(String product, int number) {
    	inputSearch.sendKeys(product);
    	wait.until(ExpectedConditions.visibilityOf(firstSuggestionInSearch));
    
    	//We can create here a "dynamic" WebElement based on the number
    	//to be set in custom xpath or another identifier in the case of
    	//page it could be something like:
    	driver.findElement(By.id("issDiv" + (number - 1))).click();
    	wait.until(ExpectedConditions.visibilityOf(firstSearchedProduct));
    	
    	return new SearchPage(driver);
    }
    
    public YourAmazonPage goToYourAmazon() {
    	yourAmazonLink.click();
    	wait.until(ExpectedConditions.visibilityOf(emailField));
    	
    	return new YourAmazonPage(driver);
    }
}
