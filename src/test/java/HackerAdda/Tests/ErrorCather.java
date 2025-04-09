package HackerAdda.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import HackerAdda.Testcomponents.BaseTests;
import HackerAdda.pageObjects.CartPage;
import HackerAdda.pageObjects.LoadingPage;
import HackerAdda.pageObjects.ProductCatalogue;

public class ErrorCather extends BaseTests {

	@SuppressWarnings("deprecation")
//	public static void main(String[] args) throws InterruptedException, IOException {
		
	      @Test
		public void loginError() throws InterruptedException, IOException {
	
		LoadingPage loadingPage=launchApplication();
		 ProductCatalogue productCatalogu=loadingPage.loginPageLocation("lakshmikarthik136@gmail.com", "Lakshmi@13");
		Assert.assertEquals("Incorrect email or password.", loadingPage.grtErrorText());
		
		
		
	
		
		
	}
	
	  @Test
			public void submitOrder() throws InterruptedException, IOException {
			String ProdName="ADIDAS ORIGINAL";
			LoadingPage loadingPage=launchApplication();
			 ProductCatalogue productCatalogu=loadingPage.loginPageLocation("lakshmikarthik13256@gmail.com", "Lakshmi@123");
		
			
			List<WebElement> products =productCatalogu.getProductList();
			productCatalogu.addToCart(ProdName);
			CartPage cartPage =productCatalogu.clickOnCart();
			 
			boolean item=cartPage.findCartProducts("GUDIDAS ORIGINALS");
			Assert.assertFalse(item);

}
}
