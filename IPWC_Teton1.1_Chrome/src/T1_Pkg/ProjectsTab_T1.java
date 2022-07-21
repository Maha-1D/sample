package T1_Pkg;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;



public class ProjectsTab_T1 extends InitializeTest_T1 {
	static String ProjectName,ProjectNameSwitcher;

	@Test
	public void aNavigateToProjectsTab() throws InterruptedException {
		logger = report.createTest("Navigate to Projects Tab");
        // Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Projects')]")).click();
		//Thread.sleep(3000);
		if(driver.findElements(By.id("profile.bar")).size()>0)
		{
	       driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Projects')]")).click();
		}
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ProjectsGrid_GridPanel"))));
        //    Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.id("ProjectsGrid_GridPanel")).isDisplayed());
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