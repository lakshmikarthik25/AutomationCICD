package HackerAdda.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HackerAdda.Abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	public  CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	


   @FindBy(css=".cartSection h3")
   List<WebElement> cartProductList;
   
   @FindBy(css=".totalRow button")
   WebElement checkOutButton;
   
  public boolean findCartProducts(String ProdName) {
		 boolean item= cartProductList.stream().anyMatch(items->items.getText().equals(ProdName));
		 
		return item;
  }
  
  public PlaceOrder clickCheckOut() {
	  checkOutButton.click();
	return  new PlaceOrder(driver);
  }

}
