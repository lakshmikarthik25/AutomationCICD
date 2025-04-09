package HackerAdda.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HackerAdda.Abstractcomponents.AbstractComponents;

public class LoadingPage extends AbstractComponents{
	
	WebDriver driver;
	public  LoadingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	WebElement userEmail=driver.findElement(By.id("userEmail"));
   @FindBy(id="userEmail")
   WebElement EmailEle;
   
   @FindBy(id="userPassword")
   WebElement paswardEle;
   
   @FindBy(id="login")
   WebElement loginBtn;
   
   @FindBy(css="div[aria-label='Incorrect email or password.']")
   WebElement errorPopup;
   
   public ProductCatalogue loginPageLocation(String Email,String pasward) {
	   EmailEle.sendKeys(Email);
	   paswardEle.sendKeys(pasward);
	   loginBtn.click();
	   ProductCatalogue productCatalogu = new ProductCatalogue(driver);
	   return productCatalogu;
   }
	public void sourseLink() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String grtErrorText() {
		waitUntilWebelementLoad(errorPopup);
		return errorPopup.getText();
		 
	}

}
