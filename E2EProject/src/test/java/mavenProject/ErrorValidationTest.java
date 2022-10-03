package mavenProject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import dataFormat.DataReader;
import pageObject.HomePageObjects;
import resources.RetryFailedTestcases;
import resources.base;

public class ErrorValidationTest extends base {

	private static Logger log = LogManager.getLogger(base.class.getName());

	HomePageObjects hpObj;

	@Test(dataProvider = "getData", groups = { "ErrorValidations",
			"MultipleDataValidations" }, retryAnalyzer = RetryFailedTestcases.class)
	public void validateError(HashMap<String, String> input) throws InterruptedException {

		hpObj = new HomePageObjects(driver);
		hpObj.loginPage(input.get("email"), input.get("pswd"));

		log.info(hpObj.getLoginError());
		Assert.assertEquals(hpObj.getLoginError(), "Incorrect email or password.");
		
		
		driver.findElement(By.id("userEmail")).clear();
		driver.findElement(By.id("userPassword")).clear();
		Thread.sleep(2000);

	}

//	@DataProvider
//	public Object[][] getData() {
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", prop.getProperty("email"));
//		map1.put("pswd", prop.getProperty("wrongPassword"));
//
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "ravneet@gmail.com");
//		map2.put("pswd", "password1212");
//
//		return new Object[][] { { map1 }, { map2 } };
//		
//		======================= OR =============================

//		// @DataProvider
//		// public Object[][] getData()
//		//
//		// return new Object[][] { {prop.getProperty("email"),prop.getProperty("wrongPassword")}, 
//        //		                   {"ravneet@gmail.com", "password1212"} };
//		//
//		// }
//
//	}   =============== DATA from JSON File ====================

	@DataProvider
	public Object[][] getData() throws IOException {
		DataReader dr = new DataReader();
		List<HashMap<String, String>> data = dr.getJsonData();
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
