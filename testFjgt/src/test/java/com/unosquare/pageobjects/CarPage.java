package com.unosquare.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CarPage extends PageBase {

	@FindBy(how = How.CSS, using = "a.nav-logo-link")
	private WebElement homePageLogoButton;
	
	private WebDriver driver;
	
	public CarPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public AmazonHomePage returnToHomePage() {
		homePageLogoButton.click();
		
		return new AmazonHomePage(driver);
	}
}
