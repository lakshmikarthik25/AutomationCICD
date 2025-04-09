package HackerAdda.Testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import HackerAdda.resourses.ExtentReporter;

public class Listeners extends BaseTests implements ITestListener {

	ExtentTest test;
	 ExtentReports report = ExtentReporter.getObject();
	 ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + test);
       test= report.createTest(result.getMethod().getMethodName());
       extentTest.set(test);
       
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        extentTest.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
//        test.log(Status.FAIL, "Test passed");
        extentTest.get().fail(result.getThrowable());
        try {
        	
        driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }
        catch(Exception e1){
        	e1.printStackTrace();
        }
        
        
        String filePath = null;
		try {
			filePath = takeScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: " + result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("Test failed with timeout: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test execution started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test execution finished: " + context.getName());
        report.flush();
    }
}
