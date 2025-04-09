package HackerAdda.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HackerAdda.Abstractcomponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	public  OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	


	@FindBy(css="tbody td:nth-child(3)")
	 List<WebElement> orderedList;
   
//   @FindBy(css=".totalRow button")
//   WebElement checkOutButton;
   
  public boolean findOrderedProducts(String ProdName) {
		 boolean item= orderedList.stream().anyMatch(items->items.getText().equalsIgnoreCase(ProdName));
		return item;
  }
  
  

}
