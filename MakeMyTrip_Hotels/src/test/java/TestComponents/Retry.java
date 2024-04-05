package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	int count=0;
	int tries=2;
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<tries) {
			count++;
			return true;
		}
		return false;
	}

}
