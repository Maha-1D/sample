package T1_Pkg;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;



public class LogoutIPWC_T1 extends InitializeTest_T1 {
	@Test
	public void aClickOnLogoutButton() throws InterruptedException {
		logger = report.createTest("Click on logout button");
		Thread.sleep(4000);
		
		if (instName.contains("EXT"))
		{
			logger.log(Status.INFO, "External instances do not have Logout button");
		}
		else {
		if(driver.findElements(By.xpath("//*[@id='imgbox']")).size()<1)
		{
			executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='imgbox']")));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='imgbox']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='profileIconMenu_list']/li/a/span[contains(text(),'Logout')]")).click();
			Thread.sleep(4000);
			System.out.println("Logout button not there. Scrolled up");
		}
		else
		{
			driver.findElement(By.xpath("//*[@id='imgbox']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='profileIconMenu_list']/li/a/span[contains(text(),'Logout')]")).click();
			Thread.sleep(2000);
			System.out.println("Logout present");
		}
		
		Thread.sleep(4000);
		
        String logoutURL = driver.getCurrentUrl() ;
        
        if (instName.contentEquals("HALLIBURTON")) {
        	 Assert.assertTrue(logoutURL.contains("signout"));
        }
        else { 
        Assert.assertTrue(logoutURL.contains("logout"));
       
        }}

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			
				logger.fail(result.getThrowable().getMessage());
				logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
				System.out.println("The test for " + result.getName()+ " is failed");
			} 
		 else {
			logger.pass("The test case " + result.getName() + "is passed");
			logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
			System.out.println("The test for " + result.getName() + " is pass");
		}
		
		report.flush();
		//System.out.println(System.getProperty("user.home")+ "/Desktop/"+instName+"SeleniumScriptsReports/FF_"+instName+"_" + DateTime + "/QLOTest_" + DateTime+ ".html");
		
		
	}

}
