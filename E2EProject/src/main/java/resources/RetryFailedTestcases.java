package resources;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestcases implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1; // How many time it should rerun the failed testcase

	@Override
	public boolean retry(ITestResult result) {

		if (count < maxTry) {
			count++;
			return true;
		}

		return false;
	}

}
