package com.unosquare.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends PageBase {

	@FindBy(how = How.XPATH, using = "//img[@data-image-index='0']")
	private WebElement firstSearchedProduct;
	
	@FindBy(how = How.ID, using = "add-to-cart-button")
	private WebElement addToCarButton;
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public SearchPage(WebDriver driver) {
    	PageFactory.initElements(driver, this);
    	wait = new WebDriverWait(driver, 10);
    	this.driver = driver;
	}
	
	public String getFirstProductPrice() {
		//I had to use the JavascriptExecutor because my environment or specifically
		//my locale is converting the decimal to a different char with getText() method,
		//so to not have fails this will take it directly.
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return document.getElementsByClassName('a-offscreen').item(0).textContent;");
	}
	
	public ProductDetailsPage seeFirstProductDetails() {
		firstSearchedProduct.click();
		wait.until(ExpectedConditions.elementToBeClickable(addToCarButton));
		
		return new ProductDetailsPage(driver);
	}
}
