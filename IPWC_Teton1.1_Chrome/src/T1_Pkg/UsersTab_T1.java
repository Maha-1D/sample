package T1_Pkg;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;



public class UsersTab_T1 extends InitializeTest_T1 {
	static String newUser = new String();
	static String searchUser = new String() ;
	
	
	@Test
	public void aNavigateToUsersTab() {
		logger = report.createTest("Navigate to UsersTab");

		driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Users')]")).click();

		// Click on Users tab
		driver.findElement(By.id("tab-1011-btnEl")).click();
		driver.switchTo().frame("userGridTabiframe");
		Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='pg_bc_r-0_c-0']/div")).isDisplayed());
	}

	@Test
	public void bAddNewUserToProgram() throws IOException, InterruptedException {
		
		if (instName.contentEquals("IGA") && username.contentEquals("ipwcapm@us.ibm.com")){
			
			//logger.log(Status.INFO, ".",logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "IGA_error")));
			logger.log(Status.PASS, "This test case is ignored in IGA because the records are not available for the L1 team");
			
		/*	newUser = "sogupta8@in.ibm.com";
			searchUser = "Sonali";
		
			System.out.println("The instance name is : " + instName);
			
		logger = report.createTest("Add a new user record in program");
		int userPresent = 1;
		List<WebElement> userRow = driver.findElements(By.xpath("//table[@id = 'pg_RowsTable']//descendant::td"));
		
		for (WebElement td : userRow) {
			if (td.getText().contains(newUser)) {
				System.out.println("Resource already added to project.");
				userPresent = 0;
				logger.log(Status.INFO, "The user record is already added to the project.",logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "UserAddBefore")));
				td.click();
				break;
			}}
			System.out.println("userPresent is " +userPresent);
			
			if(userPresent == 1)
			{
				// Click on New button
				driver.findElement(By.xpath("//tr[@id = 'WorkcenterMenuRow']//ul/li[2]/div/span[1]/span[2]")).click();
				Thread.sleep(2000);
				driver.switchTo().frame(1);
		         Thread.sleep(2000);
				// Click on user access
				driver.findElement(By.xpath("html/body/div/div/div/table/tbody/tr[1]")).click();
				driver.switchTo().defaultContent();
				 if(driver.findElements(By.id("userGridTabiframe")).size()<1)
				{
		        	driver.findElement(By.xpath("//tr[@id = 'WorkcenterMenuRow']//ul/li[2]/div/span[1]/span[2]")).click();
		    		Thread.sleep(2000);
		    		driver.switchTo().frame(1);
		             Thread.sleep(2000);
		    		// Click on user access
		    		driver.findElement(By.xpath("html/body/div/div/div/table/tbody/tr[1]")).click();
		    		driver.switchTo().defaultContent();
		        }

				driver.switchTo().frame("userGridTabiframe");
		        Thread.sleep(2000);
				// Enter record to be searched
				driver.findElement(By.id("searchusers-inputEl")).sendKeys(newUser);
				Thread.sleep(2000);
				driver.findElement(By.id("searchusers-trigger-search")).click(); // Click Search

Thread.sleep(5000);
				Thread.sleep(2000);
				List<WebElement> selectedUsers = driver.findElement(By.id("AvailableUsersGridPanel-body")).findElements(By.tagName("span"));
				for (WebElement span : selectedUsers) {
					if (span.getText().contains(searchUser)) {
						System.out.println(span.getText());
						logger.log(Status.INFO, "The user record is selected",
						logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "UserAdd")));
						action.moveToElement(span).doubleClick().perform();
						break;
					}
					break;
				}
				driver.findElement(By.id("okButton_Label")).click();
				driver.switchTo().defaultContent();
				if(driver.findElements(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Users')]")).size()<1)
				{
					driver.switchTo().frame("userGridTabiframe");
					driver.findElement(By.id("closeButton_Label")).click();
					driver.switchTo().defaultContent();
				}
				
				driver.switchTo().frame("userGridTabiframe");
Thread.sleep(5000);
				Thread.sleep(5000);
				List<WebElement> addUserRows = driver
						.findElement(By.id("pg_RowsTable")).findElements(By.tagName("div"));
				for (WebElement div : addUserRows) {
					if (div.getText().contains(newUser)) {
						Assert.assertTrue(div.isDisplayed());
						break;
					}
					break;
				}
			   }
			   */ }
		else {
					
			newUser = "alexandru.gurita@ro.ibm.com";
			searchUser = "ALEX";
				
			System.out.println("The instance name is : " + instName);
			
			
			int userPresent = 1;
			List<WebElement> userRow = driver.findElements(By.xpath("//table[@id = 'pg_RowsTable']//descendant::td"));
			
			for (WebElement td : userRow) {
				if (td.getText().contains(newUser)) {
					System.out.println("Resource already added to project.");
					userPresent = 0;
					logger.log(Status.INFO, "The user record is already added to the project.");
					logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "UserAddBefore"));
					td.click();
					break;
				}}
				System.out.println("userPresent is " +userPresent);
				
				if(userPresent == 1)
				{
					// Click on New button
					driver.findElement(By.xpath("//tr[@id = 'WorkcenterMenuRow']//ul/li[2]/div/span[1]/span[2]")).click();
					Thread.sleep(2000);
					driver.switchTo().frame(1);
			        Thread.sleep(2000);
					//Click on user access
			         System.out.println("The first time click on new useR");
					driver.findElement(By.xpath("//*[@id='menu-items-table']/tbody/tr[1]/td[2]")).click();
					driver.switchTo().defaultContent();
					 if(driver.findElements(By.id("userGridTabiframe")).size()<1)
					{
						 System.out.println("The second time click on new useR");
						 driver.findElement(By.xpath("//tr[@id = 'WorkcenterMenuRow']//ul/li[2]/div/span[1]/span[2]")).click();
			    		Thread.sleep(2000);
			    		driver.switchTo().frame(1);
			             Thread.sleep(2000);
			    		// Click on user access
			             driver.findElement(By.xpath("//*[@id='menu-items-table']/tbody/tr[1]/td[2]")).click();
			    		driver.switchTo().defaultContent();
			        }
					
					 logger = report.createTest("Add a new user record in program");
					driver.switchTo().frame("userGridTabiframe");
			        Thread.sleep(2000);
					// Enter record to be searched
					driver.findElement(By.id("searchusers-inputEl")).sendKeys(newUser);
					Thread.sleep(2000);
					driver.findElement(By.id("searchusers-trigger-search")).click(); // Click Search

					Thread.sleep(5000);
					List<WebElement> selectedUsers = driver.findElement(By.id("AvailableUsersGridPanel-body")).findElements(By.tagName("span"));
					for (WebElement span : selectedUsers) {
						if (span.getText().contains(searchUser)) {
							System.out.println(span.getText());
							logger.log(Status.INFO, "The user record is selected");
							logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "UserAdd"));
							Thread.sleep(4000);
							action.moveToElement(span).doubleClick().perform();
							break;
						}
						break;
					}
					driver.findElement(By.id("okButton_Label")).click();
					Thread.sleep(3000);
					driver.switchTo().defaultContent();
					if(driver.findElements(By.id("closeButton_Label")).size()>0)
					{
						driver.switchTo().frame("userGridTabiframe");
						driver.findElement(By.id("closeButton_Label")).click();
						driver.switchTo().defaultContent();
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("userGridTabiframe");
					Thread.sleep(5000);
					Thread.sleep(5000);
					List<WebElement> addUserRows = driver
							.findElement(By.id("pg_RowsTable")).findElements(By.tagName("div"));
					for (WebElement div : addUserRows) {
						if (div.getText().contains(newUser)) {
							Assert.assertTrue(div.isDisplayed());
							break;
						}
						break;
					}
				}
		}
	}

	@Test
	public void bDeleteUserRecord() throws IOException, InterruptedException {
		logger = report.createTest("Delete the user's record");
		if (instName.contentEquals("IGA") && username.contentEquals("ipwcapm@us.ibm.com")){
			
			logger.log(Status.PASS, "This test case is ignored in IGA because the records are not available for the L1 team");
			
		/*	
			newUser = "sogupta8@in.ibm.com";
			searchUser = "Sonali";
		
Thread.sleep(5000);
		List<WebElement> userRow = driver.findElements(By
				.xpath("//table[@id = 'pg_RowsTable']//descendant::td"));
		for (WebElement td : userRow) {
			if (td.getText().contains(newUser)) {
				System.out.println("Identified with name");
				td.click();
				break;
			}
		}

		// click on the Edit button
		driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr/td/table/tbody/tr[2]/td/ul/li[2]/div/span[3]/span[2]")).click();
		//driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr/td/table/tbody/tr[2]/td/ul/li[2]/div/span[3]/span[2]")).click();
		
Thread.sleep(5000);
		driver.switchTo().frame("mf-menu-cache-21");
        Thread.sleep(1000);
		// Click Remove Access option
		driver.findElement(By.xpath("html/body/div/div/div/table/tbody/tr[6]/td[2]")).click();
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("userGridTabiframe");
		driver.switchTo().frame("DialogPaneFrame1").switchTo()
				.frame("DialogContainerBodyContentFrame");
		logger.log(Status.INFO, "The Delete pop-up is shown", logger
				.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "UserDelete")));
		driver.findElement(By.id("YesButton_Label")).click();
		driver.switchTo().defaultContent();

		driver.switchTo().frame("userGridTabiframe");
		Thread.sleep(3000);
Thread.sleep(5000);
Thread.sleep(5000);
		Thread.sleep(3000);
		List<WebElement> addUserRows = driver.findElements(By.xpath("//table[@id = 'pg_RowsTable']//descendant::div"));
		for (WebElement div : addUserRows) {
			if (div.getText().contains(newUser)) {

				Assert.fail();
				break;
			}
			break;
		}
		driver.switchTo().defaultContent();
		
		 */ 
	   }
        else {
        	   			
        	newUser = "alexandru.gurita@ro.ibm.com";
			searchUser = "ALEX";
    		
            Thread.sleep(2000);
    		List<WebElement> userRow = driver.findElements(By
    				.xpath("//table[@id = 'pg_RowsTable']//descendant::td"));
    		for (WebElement td : userRow) {
    			if (td.getText().contains(newUser)) {
    				System.out.println("Identified with name");
    				td.click();
    				break;
    			}
    		}

    		// click on the Edit button
    		driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr/td/table/tbody/tr[2]/td/ul/li[2]/div/span[3]/span[2]")).click();
    		//driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr/td/table/tbody/tr[2]/td/ul/li[2]/div/span[3]/span[2]")).click();
    		
Thread.sleep(5000);
    		driver.switchTo().frame(1);
            Thread.sleep(1000);
    		// Click Remove Access option
    		driver.findElement(By.xpath("html/body/div/div/div/table/tbody/tr[6]/td[2]")).click();
    		Thread.sleep(3000);
    		driver.switchTo().defaultContent();
    		driver.switchTo().frame("userGridTabiframe");
    		driver.switchTo().frame("DialogPaneFrame1").switchTo()
    				.frame("DialogContainerBodyContentFrame");
    		logger.log(Status.INFO, "The Delete pop-up is shown"); 
    		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "UserDelete"));
    		driver.findElement(By.id("YesButton_Label")).click();
    		driver.switchTo().defaultContent();

    		driver.switchTo().frame("userGridTabiframe");
    		
    		Thread.sleep(3000);
    		List<WebElement> addUserRows = driver.findElements(By.xpath("//table[@id = 'pg_RowsTable']//descendant::div"));
    		for (WebElement div : addUserRows) {
    			if (div.getText().contains(newUser)) {

    				Assert.fail();
    				break;
    			}
    			break;
    		}
    		driver.switchTo().defaultContent(); 
    		driver.findElement(By.xpath("//*[@id='WorkcenterHeaderRow']/td/table/tbody/tr/td/label")).click();
        }
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				logger.fail(result.getThrowable().getMessage());
				logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
				System.out.println("The test for " + result.getName()
						+ " is failed");
				} catch (Throwable error) {
				logger.log(Status.INFO,
						"Cause of error is " + error.getMessage());}}
		else {
			logger.pass("The test case " + result.getName() + "is passed");
			logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
			System.out.println("The test for "+result.getName()+ " is pass");
			}
		
		report.flush();
	}
}
