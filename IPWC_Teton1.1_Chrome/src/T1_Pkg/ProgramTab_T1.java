package T1_Pkg;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;



public class ProgramTab_T1 extends InitializeTest_T1 {

	@Test
	public void aNavigateToProgramTab() {

		logger = report.createTest("Navigate to ProgramTab");

		if (driver.findElement(By.xpath("//div[@id = 'nav']//span[contains(text(),'Projects')]")).isDisplayed()) {
			InitializeTest_T1.highLightElement(driver,driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/a/span")));
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'nav']//span[contains(text(),'Projects')]")).isDisplayed());
			InitializeTest_T1.removeHighLightElement(driver,driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/a/span")));
		} 
		else if (driver.findElements(By.xpath("//div[@id = 'nav']//span[contains(text(),'Program')]")).size()>0)
		   {
			
			driver.findElement(By.xpath("//div[@id = 'nav']//span[contains(text(),'Program')]")).click();
			InitializeTest_T1.highLightElement(driver,driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/a/span")));
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'nav']//span[contains(text(),'Projects')]")).isDisplayed());
			InitializeTest_T1.removeHighLightElement(driver,driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/a/span")));
		}
		else
		{
			driver.findElement(By.xpath("//div[@id = 'nav']//span[contains(text(),'Program')]")).click();
			InitializeTest_T1.highLightElement(driver,driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/a/span")));
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'nav']//span[contains(text(),'Overview')]")).isDisplayed());
			InitializeTest_T1.removeHighLightElement(driver,driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/a/span")));
		}

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			   logger.fail(result.getThrowable().getMessage());
			   logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
				System.out.println("The test for "+result.getName()+ " is failed");
				} else {
			logger.pass("The test case " + result.getName() + "is passed");
			logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
			System.out.println("The test for "+result.getName()+ " is pass");
			}
		
		report.flush();
	}

}
