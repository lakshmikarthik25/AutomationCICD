package HackerAdda.Testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
//again  another one..

	//just new comments
	//did u like it
	 int count =0;
	 int maxTry=2;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry) {
			count++;
			return true;
		}
  		return false;
	}

}
