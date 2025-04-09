package HackerAdda.Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import HackerAdda.pageObjects.LoadingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests {
    public LoadingPage loadingPage;
	public WebDriver driver;
	public WebDriver dataInitializer() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\HackerAdda\\resourses\\GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");

		if (browserName.contains("chrome")) {
		    ChromeOptions options = new ChromeOptions();
		    
		    if (browserName.contains("headless")) {
		        options.addArguments("--headless=new"); // Use the latest headless mode
		        options.addArguments("--disable-gpu"); // Disable GPU acceleration
		        options.addArguments("--no-sandbox"); // Required for Jenkins
		        options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource issues
		        options.addArguments("--remote-allow-origins=*"); // Avoid remote origin errors
		    }

		    WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver(options);
		    driver.manage().window().setSize(new Dimension(1440, 900));

		    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lakshmikarthik\\Documents\\chromedriver.exe");
		

            
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Invalid browser specified: " + browserName);
        }

		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		String jsonData=FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data= mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>(){
	});
		return data;
		
}
	
	public String takeScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	@BeforeTest(alwaysRun=true)
	public LoadingPage launchApplication() throws IOException {
		
		driver=dataInitializer();
		loadingPage= new LoadingPage(driver);
		loadingPage.sourseLink();
		return  loadingPage;
	}
	@AfterTest(alwaysRun=true)
	public void Exit() {
		driver.close();
	}
}
