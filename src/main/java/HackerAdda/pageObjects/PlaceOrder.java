package HackerAdda.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HackerAdda.Abstractcomponents.AbstractComponents;

public class PlaceOrder extends AbstractComponents{
	WebDriver driver;
	public  PlaceOrder(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryOptions;
	@FindBy(xpath="(//button[contains(@class,'list-group-item')])[2]")
     WebElement clickOnCountry;
	@FindBy(css=".action__submit")
	WebElement clickOnPlaceOrder;
	
	By countryNeeded=By.cssSelector(".ta-results");
	
	
	public void selectCountry(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(countryOptions, country).click().build().perform();
		waitUntilProductLoad(countryNeeded);
		
		clickOnCountry.click();
	}
	
	public ConfirmMessage placeOrder() {
		clickOnPlaceOrder.click();
		return new ConfirmMessage(driver);
	}
	
}
