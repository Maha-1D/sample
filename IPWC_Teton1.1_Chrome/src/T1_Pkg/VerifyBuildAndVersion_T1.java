package T1_Pkg;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;



import com.aventstack.extentreports.Status;



public class VerifyBuildAndVersion_T1 extends InitializeTest_T1 {

	@Test
	public void aClickOnAboutButton() throws InterruptedException {
		logger = report.createTest("Click On About Button");
		 if(driver.findElements(By.xpath("//div[@id='footer_CollapsedLinks']")).size()<1)
		 {
			 Thread.sleep(3000);
		 }
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='imgbox']")));
		Thread.sleep(500 * sleepMultiple);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='imgbox']"))).click().build().perform();
		
		Thread.sleep(200);
		if (driver.findElements(By.xpath("//*[@id='profileIconMenu_list']")).size()<1)
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='imgbox']")));
			
			driver.findElement(By.xpath("//*[@id='imgbox']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='profileIconMenu_list']")));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='profileIconMenu_list']/li[1]/a/span")).click();
			
		}
		else {
		driver.findElement(By.xpath("//*[@id='profileIconMenu_list']/li[1]/a/span")).click();
		
		   }
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DialogPaneFrame1")));
		
	    driver.switchTo().frame("DialogPaneFrame1");
		Thread.sleep(200 * sleepMultiple);
		Assert.assertTrue(driver.findElement(By.id("DialogHeader")).isDisplayed());
					
	}

	@Test
	public void bVerifyBuild() throws InterruptedException {
		logger = report.createTest("Verify Build");
		driver.switchTo().frame("DialogContainerBodyContentFrame");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BuildNumber")));
		String ActualBuild = driver.findElement(By.id("BuildNumber")).getText();
		//InitializeTest_T1.highLightElement(driver,driver.findElement(By.id("BuildNumber")));
		Assert.assertEquals(ActualBuild, expBuild);
		//InitializeTest_T1.removeHighLightElement(driver,driver.findElement(By.id("BuildNumber")));
		
		
}
		

	@Test
	public void cVerifyVersion() throws InterruptedException, IOException {
		logger = report.createTest("Verify Version");
		
		String ActualVersion = driver.findElement(By.id("VersionNumber")).getText();
		// InitializeTest_T1.highLightElement(driver,driver.findElement(By.id("VersionNumber")));
		Assert.assertEquals(ActualVersion,expVersion);
		
		// Thread.sleep(3000);
	//	InitializeTest_T1.removeHighLightElement(driver,driver.findElement(By.id("VersionNumber")));
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='CancelButton_Button']")).click();
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("CloseButton_Button")));
		driver.switchTo().defaultContent();
		
		
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				logger.fail(result.getThrowable().getMessage());
				logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
				System.out.println("The test for "+result.getName()+ " is failed");
				} catch (Throwable error) {
				logger.log(Status.INFO,
						"Cause of error is " + error.getMessage());
			}
		} else {
			logger.pass("The test case " + result.getName() + "is passed");
			logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
			System.out.println("The test for "+result.getName()+ " is pass");}
		
		report.flush();
	}

}
