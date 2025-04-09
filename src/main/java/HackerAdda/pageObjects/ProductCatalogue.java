package HackerAdda.pageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HackerAdda.Abstractcomponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	public  ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//    
//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
	
	@FindBy(css=".col-lg-4")
	  List<WebElement> ProductList;
	
	By ProductListBy = By.cssSelector(".col-lg-4");
	By cartListBy=By.cssSelector(".card-body button:last-of-type");
	By toastMessage =By.cssSelector("#toast-container");
	By afterLoading=By.cssSelector("ng-animating");
	
	public List<WebElement> getProductList() {
		waitUntilProductLoad(ProductListBy);
		return ProductList;
	}
	
	public WebElement getElementsByName(String ProdName) {
		WebElement prdt= getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(ProdName)).findFirst().orElse(null);
		return prdt;
	}
	public void addToCart(String ProdName) throws InterruptedException {
		WebElement prdt= getElementsByName(ProdName);
		prdt.findElement(cartListBy).click();
		waitUntilProductLoad(toastMessage);
		waitUntilInvisible(afterLoading);
		
	}

}
