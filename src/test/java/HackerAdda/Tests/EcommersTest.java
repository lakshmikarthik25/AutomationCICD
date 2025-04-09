package HackerAdda.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import HackerAdda.Testcomponents.BaseTests;
import HackerAdda.Testcomponents.Retry;
import HackerAdda.pageObjects.CartPage;
import HackerAdda.pageObjects.ConfirmMessage;
import HackerAdda.pageObjects.LoadingPage;
import HackerAdda.pageObjects.OrderPage;
import HackerAdda.pageObjects.PlaceOrder;
import HackerAdda.pageObjects.ProductCatalogue;

public class EcommersTest extends BaseTests {

	//	public static void main(String[] args) throws InterruptedException, IOException {
	String ProdName="ADIDAS ORIGINAL";
		
	@Test(dataProvider="getData",groups= {"Objects"})
		public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		LoadingPage loadingPage=launchApplication();
		 ProductCatalogue productCatalogu=loadingPage.loginPageLocation(input.get("email"),input.get("pasward"));
	
		
		List<WebElement> products =productCatalogu.getProductList();
		productCatalogu.addToCart(input.get("ProdName"));
		CartPage cartPage =productCatalogu.clickOnCart();
		 
		boolean item=cartPage.findCartProducts(input.get("ProdName"));
		Assert.assertTrue(item);
		PlaceOrder placeOrder = cartPage.clickCheckOut();
		
		placeOrder.selectCountry("india");
		ConfirmMessage confirmMessage =placeOrder.placeOrder();
		

		
		String finalText=confirmMessage.getConfirmText();
		Assert.assertTrue(finalText.equalsIgnoreCase("Thankyou for the order."));
	}
		
		
		@SuppressWarnings("deprecation")
		@Test(dependsOnMethods= {"submitOrder"},retryAnalyzer=Retry.class)
	public void orderHistoryDetail() throws IOException {
	     
          ProductCatalogue productCatalogu=loadingPage.loginPageLocation("lakshmikarthik13256@gmail.com", "Lakshmi@123");
		 OrderPage orderPage =productCatalogu.checkOrderDetails();
		 Assert.assertTrue(orderPage.findOrderedProducts(ProdName)); 
		 
	}
		
		
		
		@DataProvider
		public Object[][] getData() throws IOException {
			List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\HackerAdda\\data\\purchaseOrder.json");
			return new Object [][] {{data.get(0)},{data.get(1)}};
//			HashMap<String,String> map= new HashMap<String,String>();
//			map.put("email", "lakshmikarthik13256@gmail.com");
//			map.put("pasward", "Lakshmi@123");
//			map.put("ProdName", "ADIDAS ORIGINAL");
//			
//			HashMap<String,String> map1= new HashMap<String,String>();
//			map.put("email", "lakshmikarthik13@gmail.com");
//			map.put("pasward", "Lakshmi@123");
//			map.put("ProdName", "ZARA COAT 3");
			
//	or		return new Object [][] {{"lakshmikarthik13256@gmail.com", "Lakshmi@123","ADIDAS ORIGINAL"},{"lakshmikarthik13@gmail.com", "Lakshmi@123","ZARA COAT 3"}};

		}
		
		

}
