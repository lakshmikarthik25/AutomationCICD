package HackerAdda.Stepdifination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import HackerAdda.Testcomponents.BaseTests;
import HackerAdda.pageObjects.CartPage;
import HackerAdda.pageObjects.ConfirmMessage;
import HackerAdda.pageObjects.LoadingPage;
import HackerAdda.pageObjects.PlaceOrder;
import HackerAdda.pageObjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDifinationImpl extends BaseTests{
	public LoadingPage loadingPage;
	 public ProductCatalogue productCatalogu;
	public	ConfirmMessage confirmMessage;

	@Given("I landed on  Ecommers page")
	public void i_landed_on_Ecommers_page() throws IOException {
		 loadingPage=launchApplication();
	}
	
	@Given("^Login by using username (.+) and pasward (.+)$")
	public void login_by_using_username_and_pasward(String username,String pasward) {
		  productCatalogu=loadingPage.loginPageLocation(username,pasward);

	}
	@When("^add the product into the cart (.+)$")
    public void add_the_product_into_the_cart(String ProdName) throws InterruptedException {
		List<WebElement> products =productCatalogu.getProductList();
		productCatalogu.addToCart(ProdName);
		
	}
	@When("^checkout product (.+) and submit order$")
	public void checkout_product_and_submit_order(String ProdName) {
		CartPage cartPage =productCatalogu.clickOnCart();
		 
		boolean item=cartPage.findCartProducts(ProdName);
		Assert.assertTrue(item);
		PlaceOrder placeOrder = cartPage.clickCheckOut();
		
		placeOrder.selectCountry("india");
		 confirmMessage =placeOrder.placeOrder();
	}
	@Then("{string} message is displayed in the confirmationPage")
	public void message_is_displayed_in_the_confirmantionPage(String string) {
		String finalText=confirmMessage.getConfirmText();
		Assert.assertTrue(finalText.equalsIgnoreCase(string));
		driver.close();
	}
	 @Then("^\"([^\"]*)\" message displayed$")
	    public void message_displayed(String expectedErrorMessage) {
			Assert.assertEquals(expectedErrorMessage, loadingPage.grtErrorText());
			driver.close();
	    }
}
