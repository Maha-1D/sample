package T1_Pkg;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;



public class TimesheetsTab_T1 extends InitializeTest_T1 {
	@Test
	public void aNavigateToTimesheetsTab() throws InterruptedException {
		logger = report.createTest("Timesheets");
	
		if(driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Timesheets')]")).size()>0){
				logger = report.createTest("Navigate to Timesheets");
		        driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Timesheets')]")).click();
		       wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("MyTimesheetGrid_GridPanel"))));
		        Assert.assertTrue(driver.findElement(By.id("MyTimesheetGrid_GridPanel")).isDisplayed());
	 
		}
		else {
		
			logger.skip("Timesheets work center not available in this instance");
		 }
		
	}

	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			
				logger.fail(result.getThrowable().getMessage());
				logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
				System.out.println("The test for "+result.getName()+ " is failed");} 
		else {
			logger.pass("The test case " + result.getName() + "is passed");
			logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
			System.out.println("The test for "+result.getName()+ " is pass");}
		
		report.flush();
	}

}
