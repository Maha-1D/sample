package T1_Pkg;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;



public class PersonalTab_T1 extends InitializeTest_T1 {
	@Test
	public void aNavigateToPersonalTab() throws InterruptedException {
		logger = report.createTest("Navigate to Personal Tab");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'nav']//span[contains(text(),'Personal')]")));
		if(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]//span[contains(text(), 'Tasks')]")).isDisplayed())
		{
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul//span")).isDisplayed());
			
		
		}
		else {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id = 'nav']//span[contains(text(),'Personal')]")).click();
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]//span")).isDisplayed());
			
			
		}
		
		
	}

	@Test
	public void cNavigateToMyTasksTab() throws InterruptedException {
		logger = report.createTest("Navigate to My Tasks Tab");
		// click on My Tasks option
		Thread.sleep(5000);
		if (driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Tasks')]")).size()>0)
		{
		action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Tasks')]"))).click().build().perform();
		//Thread.sleep(4000 * sleepMultiple);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("workcenter-title-label"))));
		Thread.sleep(1000 * sleepMultiple);
		} 
		else
		{
			logger.skip("Please check if the My Tasks Tab is activated");
			System.out.println("Please check if the My Tasks Tab is activated");
		}
		
		if(driver.findElement(By.className("workcenter-title-label")).isDisplayed()) {
			Assert.assertTrue(driver.findElement(By.className("workcenter-title-label")).getText().contains("Tasks"));
			
		}
		else
		{
			action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Tasks')]"))).click().build().perform();
			Thread.sleep(1000 * sleepMultiple);
			Assert.assertTrue(driver.findElement(By.className("workcenter-title-label")).getText().contains("Tasks"));
		}
		
		
		
		
	}

	@Test
	public void dNavigateToMyRisksTab() throws InterruptedException {
		logger = report.createTest("Navigate to My Risks Tab");
		// Click on Risks option
		
		if (driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Risks')]")).size()>0)
        {
		action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Risks')]"))).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='pg_HeaderRow']")));
		//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Risks')]"))));
		Thread.sleep(1000 * sleepMultiple);
        }
		else
		{
			logger.skip("Please check if the My Risks Tab is activated");
			System.out.println("Please check if the My Risks Tab is activated");
		}
        
					
		if(!driver.findElement(By.className("workcenter-title-label")).getText().contains("Risks"))
		{
			action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Risks')]"))).click().build().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='pg_HeaderRow']")));
			//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Risks')]"))));
			Thread.sleep(1000 * sleepMultiple);
		}
		
		
		Assert.assertTrue(driver.findElement(By.className("workcenter-title-label")).getText().contains("Risks"));
		
	}

	@Test
	public void eNavigateToMyIssuesTab() throws InterruptedException {
		logger = report.createTest("Navigate to My Issues Tab");
        //Thread.sleep(7000);
		
		if (driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Risks')]")).size()>0)
		{
		action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Issues')]"))).click().build().perform();
		Thread.sleep(1000 * sleepMultiple);
		}
		else
		{
			logger.skip("Please check if the My Issues Tab is activated");
			System.out.println("Please check if the My Issues Tab is activated");
		}
		
		if(!driver.findElement(By.className("workcenter-title-label")).getText().contains("Issues"))
		{
			action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Issues')]"))).click().build().perform();
			Thread.sleep(2000 * sleepMultiple);
		}
		
		Assert.assertTrue(driver.findElement(By.className("workcenter-title-label")).getText().contains("Issues"));
		
	}

	@Test
	public void fNavigateToMyDecisionsTab() throws InterruptedException {
		logger = report.createTest("Navigate to My Decisions Tab");
        
		if (driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Decisions')]")).size()>0) {
		 action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Decisions')]"))).click().build().perform();
		
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='WorkcenterSectionContent']")));
		// wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Risks')]"))));
		 Thread.sleep(1000 * sleepMultiple);
		}	
	
		else {
			logger.skip("Please check if the My Decisions Tab is activated");
			System.out.println("Please check if the My Decisions Tab is activated");
		}
		
		if(!driver.findElement(By.className("workcenter-title-label")).getText().contains("Decisions"))
		{
			action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Decisions')]"))).click().build().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='pg_HeaderRow']")));
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Risks')]"))));
			Thread.sleep(1000 * sleepMultiple);
		}

		 Assert.assertTrue(driver.findElement(By.className("workcenter-title-label")).getText().contains("Decisions"));
		
	}
	
	@Test
	public void gNavigateToMyAssumptionsTab() throws InterruptedException {
		logger = report.createTest("Navigate to My Assumptions Tab");
		
		if (driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Assumptions')]")).size()>0)
		{
		action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Assumptions')]"))).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='pg_HeaderRow']")));
		//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Risks')]"))));
		Thread.sleep(1000 * sleepMultiple);
		}
		
		else {
			logger.skip("My Assumptions Tab not available");
	}
	
	if(!driver.findElement(By.className("workcenter-title-label")).getText().contains("Assumptions"))
	{
		action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Assumptions')]"))).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='pg_HeaderRow']")));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Risks')]"))));
		Thread.sleep(1000 * sleepMultiple);
	}
	
	Assert.assertTrue(driver.findElement(By.className("workcenter-title-label")).getText().contains("Assumptions"));
	
	}
	
	@Test
	public void hNavigateToMyProfileTab() throws InterruptedException {
		logger = report.createTest("Navigate to My Profile Tab");
       
		action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[2]/ul/li/a/span[contains(text(), 'Profile')]"))).click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("WorkcenterdetailHeaderTitleTextCell"))));
		//Thread.sleep(2000 * sleepMultiple);
		
		Assert.assertTrue(driver.findElement(By.id("WorkcenterdetailHeaderTitleTextCell")).isDisplayed());
		
		
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
