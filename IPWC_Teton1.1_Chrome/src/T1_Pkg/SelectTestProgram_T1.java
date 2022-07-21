package T1_Pkg;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;





public class SelectTestProgram_T1 extends InitializeTest_T1 {
	@Test
	public void aSelectTestProgram() throws InterruptedException {
		//Thread.sleep(4000);
		
		logger = report.createTest("Select Test Program");
		
		driver.findElement(By.id("deal-switcher")).click();

		// Select our test program
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deal_list")));
        //Thread.sleep(6000);
			List<WebElement> rows = driver.findElement(By.id("deal_list")).findElements(By.tagName("span"));
				for (WebElement span : rows) {
				if ((span.getText().contentEquals("Automate QLO")) || (span.getText().contentEquals("STAOPS Change Management"))|| (span.getText().contentEquals("Test program for L3/L4/Ops Teams")) || (span.getText().contentEquals("Selenium Test Program")) || (span.getText().contentEquals("IBM: STAOPS Change Management"))) 
					{	
					System.out.println(span.getText());
					String selectedprogram = span.getText();
					span.click();
					programToBeClicked = selectedprogram;
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='footer_CollapsedLinks']")));
				//InitializeTest_T1.highLightElement(driver,driver.findElement(By.xpath("//button[@id = 'deal-switcher']/span[@class = 'dropdown-body']")));
				
				Assert.assertTrue(driver.findElement(By.xpath("//button[@id = 'deal-switcher']/span[@class = 'dropdown-body']")).getText().contentEquals(selectedprogram));
				
				System.out.println(programToBeClicked);
				//InitializeTest_T1.removeHighLightElement(driver,driver.findElement(By.xpath("//button[@id = 'deal-switcher']/span[@class = 'dropdown-body']")));
			     break;
				}}  
		
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
			System.out.println("The test for "+result.getName()+ " is pass");}
		
		report.flush();
	}

}