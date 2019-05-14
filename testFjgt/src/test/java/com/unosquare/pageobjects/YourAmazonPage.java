package com.unosquare.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YourAmazonPage extends PageBase {

	@FindBy(how = How.ID, using ="createAccountSubmit")
	private WebElement createYourAccountButton;
	
	@FindBy(how = How.ID, using = "ap_customer_name")
	private WebElement nameField;
	
	@FindBy(how = How.ID, using = "ap_email")
	private WebElement emailField;
	
	@FindBy(how = How.ID, using = "ap_password")
	private WebElement passwordField;
	
	@FindBy(how = How.ID, using = "ap_password_check")
	private WebElement checkPasswordField;
	
	@FindBy(how = How.XPATH, using = "//div[@class='a-box a-spacing-extra-large']")
	private WebElement fieldsContainer;
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public YourAmazonPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		this.driver = driver;
	}
	
	public YourAmazonPage clickOnCreateAccount() {
		createYourAccountButton.click();
		wait.until(ExpectedConditions.visibilityOf(nameField));
		
		return this;
	}
	
	public YourAmazonPage onlyfillTextFields(String name, String email, String password) {
		nameField.sendKeys(name);
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		checkPasswordField.sendKeys(password);
		
		return this;
	}
	
	public String getTextInFieldsContainer() {
		return fieldsContainer.getText();
	}
}
