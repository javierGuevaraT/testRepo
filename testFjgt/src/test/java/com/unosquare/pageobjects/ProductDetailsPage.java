package com.unosquare.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage extends PageBase {

	@FindBy(how = How.ID, using = "add-to-cart-button")
	private WebElement addToCarButton;
	
	@FindBy(how = How.ID, using = "price_inside_buybox")
	private WebElement labelPriceInDetails;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(), 'Carrito')]")
	private WebElement showCarButton;
	
	@FindBy(how = How.ID, using = "attach-close_sideSheet-link")
	private WebElement closeAddToCarContainer;
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public ProductDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		this.driver = driver;
	}
	
	public String getPriceInDetails() {
		return labelPriceInDetails.getText();
	}
	
	public CarPage addProductToCar() {
		addToCarButton.click();
		wait.until(ExpectedConditions.visibilityOf(showCarButton));
		
		return new CarPage(driver);
	}
	
//	public AmazonHomePage returnToHomePageFromCarContainer() {
//		closeAddToCarContainer.click();
//		wait.until(ExpectedConditions.invisibilityOf(freeShipMessage));
//		homePageLogoButton.click();
//		
//		return new AmazonHomePage(driver);
//	}
	
}
