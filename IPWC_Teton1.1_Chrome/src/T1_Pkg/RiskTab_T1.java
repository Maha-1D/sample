package T1_Pkg;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;



public class RiskTab_T1 extends InitializeTest_T1 {
	static String RiskName,RiskNameSwitcher;

	@Test
	public void aNavigateToRiskTab() throws InterruptedException {
		logger = report.createTest("Navigate to Risk Tab");
		
		 if (driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Risks')]")).size()>0)
		    {	    
		// Select Risks tab
		action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Risks')]"))).click().build().perform();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='risksContainer']"))));
		
		if(driver.findElements(By.xpath("//div[@id='risksContainer']")).size()<0)
		{
			driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Risks')]")).click();
		}
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='RisksGrid_GridPanel-body']/div")));
		Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='WorkcenterHeaderRow']/td/table/tbody/tr/td[2]/label")).getText().contains("Risks"));
	    }
		 else
		 {
	    	 logger.skip("Risks work center not available in this instance");
	    }
	} 
	
	
	@Test
	public void cCreateNewRisk() throws IOException, InterruptedException {
		logger = report.createTest("Create a new Risk");
		 if (driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Risks')]")).size()>0)
		    {	
		// Select "New" option
		action.moveToElement(driver.findElement(By.xpath("//td[@id='MainViewPaneTableCell']//span[@class='middle'][contains(text(),'New')]"))).click().build().perform();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("title")));
		// Adding fields of the New Risk Creation form
       // Thread.sleep(3000  * sleepMultiple);
        driver.findElement(By.id("title")).clear();
		//Thread.sleep(3000);
		driver.findElement(By.id("title")).sendKeys("Selenium Test Risk " + df.format(dateobj));
		RiskName = driver.findElement(By.id("title")).getAttribute("value");
		driver.findElement(By.name("description")).sendKeys("Description for test risk from Selenium");

		// Select Owner of the risk
		driver.findElement(By.id("assignmentInput")).sendKeys(ElementOwner);
      	logger.log(Status.INFO, "All fields for new Risk are entered");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "RiskFields"));
       // Thread.sleep(2000);
		// Save the Risk
		driver.findElement(By.name("save")).click();
		
	    Thread.sleep(200 * sleepMultiple);
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='RisksGrid_GridPanel-body']/div")));
	    
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'RisksGrid_GridPanel-body']//table/tbody/tr/td/div/div/span[@title = '"+ RiskName + "']")).isDisplayed());
		
	    }
		 else
		 {
				logger.skip("Risks work center not available in this instance");
			
			}
	}

	@Test
	public void dDeleteRisk() throws IOException, InterruptedException {

		logger = report.createTest("Delete the created Risk");
		
		if (driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Risks')]")).size()>0)
	    {	
		// The newly created Risk is already in selected state
		
	    //driver.findElement(By.xpath("//div[@id = 'RisksGrid_GridPanel-body']/div/div[2]/table/tbody/tr/td/div/div/span[@title = '"+ RiskName + "']/ancestor::td")).click();

		driver.findElement(By.id("DeleteCommandButton_Icon")).click();
		//Thread.sleep(2000);
		driver.switchTo().frame("DialogPaneFrame1").switchTo().frame("DialogContainerBodyContentFrame");
		logger.log(Status.INFO, "The Delete pop-up is shown");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "RiskDelete"));
		
		driver.findElement(By.id("YesButton_Label")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(200 * sleepMultiple);
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='RisksGrid_GridPanel-body']/div")));
       	List<WebElement> createdRisk = driver.findElement(By.xpath("//div[@id = 'RisksGrid_GridPanel-body']/div/div[2]/table/tbody/tr/td/div/div")).findElements(By.tagName("span"));
		for (WebElement span : createdRisk) {
			if (span.getAttribute("id").contains("Title")) {
				if (span.getText().contentEquals(RiskName)) {
					Assert.fail();
					break;
				}
				break;
			}
			break;
		}

	    }
		else
		 {
				logger.skip("Risks work center not available in this instance");
			
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
