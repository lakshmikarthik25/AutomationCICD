package HackerAdda.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HackerAdda.Abstractcomponents.AbstractComponents;

public class ConfirmMessage extends AbstractComponents {
	WebDriver driver;
	public  ConfirmMessage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement ConfirmationText;
//	String finalText=driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	public String getConfirmText() {
		String finalText=ConfirmationText.getText();
		return finalText;
	}
}
