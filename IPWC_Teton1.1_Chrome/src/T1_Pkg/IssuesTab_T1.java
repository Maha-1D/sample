package T1_Pkg;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;



public class IssuesTab_T1 extends InitializeTest_T1 {

	static String IssueName,IssueNameSwitcher;

	@Test
	public void aNavigateToIssuesTab() throws InterruptedException {
		
		logger = report.createTest("Navigate to IssuesTab");

		driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Issues')]")).click();
		Thread.sleep(1000 * sleepMultiple);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("filter-trigger-picker")));
		
       	             
		if(driver.findElements(By.id("filter-trigger-picker")).size()>0)
		{
			System.out.println("clicked at the first attempt with scrolling");
			Thread.sleep(500);
			//driver.findElement(By.id("filter-trigger-picker")).click();
	        
			action.moveToElement(driver.findElement(By.id("filter-trigger-picker"))).click().sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ENTER).build().perform();
	        
			Thread.sleep(1000);
	       
		}
		else{
	    System.out.println("In first else");
			executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("filter-trigger-picker")));
			//Thread.sleep(3000);
			action.moveToElement(driver.findElement(By.id("filter-trigger-picker"))).click().sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ENTER).build().perform();
	        
			Thread.sleep(500);
		} 
		Thread.sleep(500 * sleepMultiple);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("IssuesGrid_GridPanel-bodyWrap"))));
		
	
	    Assert.assertTrue(driver.findElement(By.id("MainViewPaneTableCell")).isDisplayed());
			
				
	}


	@Test
	public void cCreateNewIssue() throws IOException, InterruptedException {
		logger = report.createTest("Create a new Issue");

		// Click on New Button
		driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']/ul/li[2]/div/span[1]/span[2]")).click();
      
		// Enter all fields for new Issue
		driver.findElement(By.id("title")).sendKeys("Test Selenium Issue " + df.format(dateobj));
		IssueName = driver.findElement(By.id("title")).getAttribute("value");

		logger.log(Status.INFO, "The fields for Issue are entered");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "IssueFields"));
     	// Click on Save button
		driver.findElement(By.xpath("//*[@id='MainViewPaneFooterContent']/input[1]")).click();
		
		
		System.out.println("Save cliked or not ");
		executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//td[@id ='MainViewPaneTableCell']/ul/li[2]/div/span[14]/span[2]/label")));
		
		// Click the Close button
		driver.findElement(By.xpath("//td[@id ='MainViewPaneTableCell']/ul/li[2]/div/span[14]/span[2]/label")).click();
		//action.moveToElement(driver.findElement(By.xpath("//td[@id ='MainViewPaneTableCell']/ul/li[2]/div/span[11]/span[2]/label"))).click().build().perform();

		Thread.sleep(500 * sleepMultiple);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("IssuesGrid_GridPanel-bodyWrap"))));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'IssuesGrid_GridPanel']//table/tbody/tr/td/div/div/span[@title = '"+ IssueName + "']")).isDisplayed());

	}

	@Test
	public void dAssignOwnerToIssue() throws IOException, InterruptedException {
		logger = report.createTest("Assign Issue Owner");

	//	driver.findElement(By.xpath("//div[@id = 'IssuesGrid_GridPanel-body']/div/div[2]/table/tbody/tr/td/div/div/span[@title = '"+ IssueName + "']/ancestor::td")).click();

		// driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/div[2]/table/tbody/tr/td/form[3]/div[2]/div/div[2]/div/div/div[2]/div[2]/div/table[contains(text(),'"+IssueName+
		// "')]")).click();

		// Select the Assign button
		driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']/ul/li[2]/div//span[contains(text(),'Assign')]")).click();
		//Thread.sleep(2000);
		if(driver.findElements(By.xpath("//iframe[1]")).size()<1)
		{
			System.out.println("Clicking Assign again");
			action.moveToElement(driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']/ul/li[2]/div/span[5]/span[2]"))).click().build().perform();
            Thread.sleep(1000);
		}
		driver.switchTo().frame(1);

		// Click on Owner option
		driver.findElement(By.xpath("html/body/div/div/div/table/tbody/tr[1]/td[2]")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(1000); 
		driver.switchTo().frame(("DialogPaneFrame1"));
		// Assign Owner name
		driver.switchTo().frame("DialogContainerBodyContentFrame");

        Thread.sleep(1000);

		
		driver.findElement(By.id("SearchInput")).sendKeys(partialName); // Send
																		// partial
																		// name
																		// for
																		// search

		List<WebElement> listOfOwners = driver.findElement(By.id("SourceListContainer")).findElements(By.tagName("div"));

		for (WebElement div : listOfOwners) {
			if (div.getText().contains(partialName)) {
				div.click();
				break;
			}
			break;
		}
		driver.findElement(By.id("MoveRightButton_Label")).click();
		logger.log(Status.INFO, "Issue Owner is Assigned");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "IssueOwner"));
		driver.findElement(By.id("OkButton_Label")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(500 * sleepMultiple);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("IssuesGrid_GridPanel-bodyWrap"))));
		
		// Assert if Owner is assigned
		List<WebElement> getIssueRow = driver.findElements(By.xpath("//table/tbody/tr/td/span[contains(text(),'" + IssueName+ "')]/following-sibling::td"));
		for (WebElement td : getIssueRow) {
			if (td.getAttribute("id").contains("assignment")) {
				Assert.assertTrue(td.getText().contains(partialName));
				break;
			}
		}
	}

	@Test
	public void eDeleteIssue() throws IOException, InterruptedException {
		logger = report.createTest("Delete Issue");
		//driver.findElement(By.xpath("//div[@id = 'IssuesGrid_GridPanel-body']/div/div[2]/table/tbody/tr/td/div/div/span[@title = '"+ IssueName + "']")).click();;
		driver.findElement(By.id("DeleteButton_Icon")).click();
		
		driver.switchTo().frame("DialogPaneFrame1").switchTo().frame("DialogContainerBodyContentFrame");
		logger.log(Status.INFO, "The Delete pop-up is shown");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "IssueDelete"));

		// Click on the delete button
		driver.findElement(By.id("YesButton_Label")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(500 * sleepMultiple);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("IssuesGrid_GridPanel-bodyWrap"))));
		
		List<WebElement> IssueRowsDeleted = driver.findElement(By.id("IssuesGrid_GridPanel")).findElements(By.tagName("td"));
		for (WebElement td : IssueRowsDeleted) {
			if (td.getText().contentEquals(IssueName)) {
				Assert.fail();
				break;
			}
		}

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				logger.fail(result.getThrowable().getMessage());
				logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
				System.out.println("The test for " + result.getName()
						+ " is failed");
				} catch (Throwable error) {
				logger.log(Status.INFO,
						"Cause of error is " + error.getMessage());
			}
		} else {
			logger.pass("The test case " + result.getName() + "is passed");
			logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
			System.out.println("The test for "+result.getName()+ " is pass");
			}
	
		report.flush();
	}

}
