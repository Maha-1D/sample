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

import com.aventstack.extentreports.Status;



public class Decisions_T1 extends InitializeTest_T1{
	static String DecisionName,DecisionNameSwitcher;

	
	
  @Test
  	public void aNavigateToDecisionsTab() throws InterruptedException {
	  logger = report.createTest("Navigate to Decisions Tab");
	    if (driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Decisions')]")).size()>0)
	    {	    
		// Select Decisions tab
		
		driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Decisions')]")).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("DecisionsGrid_GridPanel-bodyWrap"))));
				
		if(driver.findElements(By.id("filter-trigger-picker")).size()>0)
		{
			action.moveToElement(driver.findElement(By.id("filter-trigger-picker"))).click().sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ENTER).build().perform();
	        
			System.out.println("clicked at the first attempt with scrolling");
			Thread.sleep(1000);
	       
		}
		else{
	    System.out.println("In first else");
	    executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("filter-trigger-picker")));
			Thread.sleep(300);
			action.moveToElement(driver.findElement(By.id("filter-trigger-picker"))).click().sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ENTER).build().perform();
	        
			Thread.sleep(1000);
		} 
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("DecisionsGrid_GridPanel-bodyWrap"))));
		Assert.assertTrue(driver.findElement(By.id("DecisionsGrid_GridPanel-body")).isDisplayed());	
	 }
	    else {
	    	 logger.skip("Decisions work center not available in this instance");
	    }
  
  }

	
	@Test
	public void cCreateNewDecision() throws IOException, InterruptedException {
		logger = report.createTest("Create a new Decision");
		
		// Select "New" option
		if (driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Decisions')]")).size()>0)
	    {	    
		
		action.moveToElement(driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']/ul/li[2]/div/span[1]/span[2]"))).click().build().perform();
		// Adding fields of the New DEcision Creation form
		driver.findElement(By.id("title")).clear();
		driver.findElement(By.id("title")).sendKeys("Selenium Test Decision " + df.format(dateobj));
		DecisionName = driver.findElement(By.id("title")).getAttribute("value");
		driver.findElement(By.name("description")).sendKeys("Description for test decision from Selenium");

		// Select Owner of the Decision
		driver.findElement(By.id("assignmentInput")).sendKeys(ElementOwner);

		

		logger.log(Status.INFO, "All fields for new Decision are entered");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "DecisionFields"));
               
		// Save the Decision
		driver.findElement(By.name("save")).click();
	    Thread.sleep(200);
	      // Click the Close button
	         driver.findElement(By.xpath("//td[@id ='MainViewPaneTableCell']/ul/li[2]/div/span[14]/span[2]/label")).click();
	         Thread.sleep(1000);
	 		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("DecisionsGrid_GridPanel-bodyWrap"))));

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'DecisionsGrid_GridPanel-body']//table/tbody/tr/td/div/div/span[@title = '"+ DecisionName+ "']")).isDisplayed());
	    }
		else {
			logger.skip("Decisions work center not available in this instance");
		
		}
	}

	@Test
	public void dDeleteDecision() throws IOException, InterruptedException {

		logger = report.createTest("Delete the created Decision");
		// The newly created Decision is already in selected state
		if (driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Decisions')]")).size()>0)
	    {
	    //driver.findElement(By.xpath("//div[@id = 'RisksGrid_GridPanel-body']/div/div[2]/table/tbody/tr/td/div/div/span[@title = '"+ RiskName + "']/ancestor::td")).click();

		driver.findElement(By.id("DeleteButton_Icon")).click();
		Thread.sleep(100);
		driver.switchTo().frame("DialogPaneFrame1").switchTo().frame("DialogContainerBodyContentFrame");
		logger.log(Status.INFO, "The Delete pop-up is shown");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "RiskDelete"));
		driver.findElement(By.id("YesButton_Label")).click();
		driver.switchTo().defaultContent();
		
       
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("DecisionsGrid_GridPanel-bodyWrap"))));
		List<WebElement> AssumptionRowsDeleted = driver.findElement(By.xpath("//div[@id = 'DecisionsGrid_GridPanel-body']//table/tbody/tr/td/div/div")).findElements(By.tagName("span"));
		for (WebElement span : AssumptionRowsDeleted) {	
		if (span.getText().contentEquals(DecisionName)) {
				Assert.fail();
				break;
			}
		}
	}
		else {
			logger.skip("Decisions work center not available in this instance");
		}

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				logger.fail(result.getThrowable().getMessage());
				logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
				System.out.println("The test for "+result.getName()+ " is failed");
				} catch (Throwable error) {
				logger.log(Status.INFO,"Cause of error is " + error.getMessage());
			}
		} else {
			logger.pass("The test case " + result.getName() + "is passed");
			logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
			System.out.println("The test for "+result.getName()+ " is pass");
			}
		
		report.flush();
	}

}

