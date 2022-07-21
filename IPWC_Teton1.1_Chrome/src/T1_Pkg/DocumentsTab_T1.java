package T1_Pkg;

import java.io.File;
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



public class DocumentsTab_T1 extends InitializeTest_T1 {

	static String FolderName;
	static String ExploreFolderName;
	static String folderID;

	@Test
	public void aNavigateToDocumentTab() throws InterruptedException {
		logger = report.createTest("Navigate to Document view");
		action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Documents')]"))).click().build().perform();
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='DocumentTree_TreeGridPanel']/div")));
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='DocumentTree_TreeGridPanel' or @id = 'tcontainer']")).isDisplayed());
	}

	
	@Test
	public void cCreateNewFolder() throws IOException, InterruptedException {
		logger = report.createTest("Create a new Folder");

		// Select Master Folder
		//driver.findElement(By.xpath("//span[@title = '" + programToBeClicked + " Documents']")).click();
		
		driver.findElement(By.xpath("//div[@id='DocumentTree_TreeGridPanel-body']//table//span[@title = '" + programToBeClicked + " Documents']")).click();
		
		if (driver.findElements(By.xpath("//div[@id='menu-bar-div']//span[contains(text(),'File')]")).size()>0) {
			driver.findElement(By.xpath("//div[@id='menu-bar-div']//span[contains(text(),'File')]")).click();
		}
		else {
		// Select File dropdown
		driver.findElement(By.xpath("//div[@id='PageSpan']/ul/li[2]/div/span[1]/span[2]")).click();
		}
                // Select File dropdown
		
		driver.switchTo().frame("mf-menu-cache-18");
		// Select new folder option
		driver.findElement(By.xpath(".//*[@id='menu-items-table']/tbody/tr[3]/td[2]")).click();
		driver.switchTo().defaultContent();

		driver.switchTo().frame("DialogPaneFrame1").switchTo().frame("DialogContainerBodyContentFrame");
       
      
		// Enter folder name
		driver.findElement(By.id("NameInput")).clear();
		driver.findElement(By.id("NameInput")).sendKeys("Test Selenium Folder " + df.format(dateobj));
		FolderName = driver.findElement(By.id("NameInput")).getAttribute("value");
		logger.log(Status.INFO, "The Folder name is entered");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "FolderCreation"));

		// Click create button
		driver.findElement(By.id("CreateButton_Label")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='DocumentTree_TreeGridPanel']/div")));
		
		//List<WebElement> folderList = driver.findElement(By.id("r-1children")).findElements(By.tagName("div"));
		
		List<WebElement> folderList = driver.findElement(By.xpath("//div[@class='x-grid-item-container']")).findElements(By.xpath(".//span[starts-with(@id, 'Title')]"));
		
		for (WebElement span : folderList) {
			//if (span.getAttribute("class").contains("left1")) {
				if (span.getText().contains(FolderName)) {
					System.out.println(span.getText());
					span.click();
					Assert.assertTrue(span.isDisplayed());
					break;
				}
				break;
			//}
			//break;
		}
	}
	
	@Test
	public void eSendNotification() throws IOException, InterruptedException {
  
		logger = report.createTest("Send notification");
		//driver.findElement(By.xpath("//*[@title = '" + programToBeClicked + " Documents']")).click();
		
		//driver.findElement(By.xpath("//div[@id ='r-1|c-0']/div[3]")).click();
		
		//wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//div[@id='r-1']/div/div[3]", selected)))
		// Click on Tools
		action.moveToElement(driver.findElement(By.xpath("//span[@id = 'PageSpan']/ul/li[2]/div/span[6]/span[2]"))).click().build().perform();
		//driver.findElement(By.xpath("//td[@id='MainViewPaneTableCell']/ul[1]//span[contains(text(),'Tools')]")).click();
		
		 driver.switchTo().frame("mf-menu-cache-56");
				
		driver.findElement(By.xpath("html/body/div/div/div/table/tbody/tr[4]/td[2]")).click();
		driver.switchTo().defaultContent();
        Thread.sleep(500);
		driver.switchTo().frame("DialogPaneFrame1").switchTo().frame("DialogContainerBodyContentFrame");
       
		
		driver.findElement(By.id("RecipientsInput")).sendKeys(ElementOwner);
		Thread.sleep(200);
		driver.findElement(By.id("NotificationMessageTextarea")).sendKeys("This is test Selenium notification");
		logger.log(Status.INFO, "The notification details are entered");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "Notification"));
		// Click ok button
		driver.findElement(By.id("OkButton_Label")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='DocumentTree_TreeGridPanel']/div")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='DocumentTree_TreeGridPanel' or @id = 'tcontainer']")).isDisplayed());
    	
			   
	}
/*
	@Test
	public void fCreateFolderWithExplore() throws IOException,
			InterruptedException {
		logger = report.createTest("Create a new Folder with Explore");
		
		 
    Thread.sleep(2000);
		// Select Master Folder
    //driver.findElement(By.xpath("//*[@title = '" + programToBeClicked + " Documents']")).click();
	
		if (driver.findElements(By.xpath("//div[@id='menu-bar-div']//span[contains(text(),'File')]")).size()>0) {
			driver.findElement(By.xpath("//div[@id='menu-bar-div']//span[contains(text(),'File')]")).click();
		}
		else {
		// Select File dropdown
		driver.findElement(By.xpath("//div[@id='PageSpan']/ul/li[2]/div/span[1]/span[2]")).click();
		}
        Thread.sleep(5000);
         if(driver.findElements(By.id("mf-menu-cache-18")).size()<1){
        	 Thread.sleep(5000);
        	 action.moveToElement(driver.findElement(By.xpath("//div[@id='r-1']/div[3]"))).click().build().perform();
     		
     		// Select File dropdown
     		driver.findElement(By.xpath("//div[@id='PageSpan']/ul/li[2]/div/span[1]/span[2]")).click();
        }
		
        driver.switchTo().frame("mf-menu-cache-18");
		// Click on explore
		Thread.sleep(3000);
       	
		action.moveToElement(driver.findElement(By.xpath("html/body/div/div/div/table/tbody/tr[9]/td[2]"))).click().build().perform();
		driver.switchTo().defaultContent();
		//Thread.sleep(3000);
        
		driver.switchTo().frame("DialogPaneFrame1").switchTo().frame("DialogContainerBodyContentFrame");

		// Pop up code
         Thread.sleep(20000);
        
	 
		// Click on new folder
		action.moveToElement(driver.findElement(By.xpath("//div[@id = 'ithit_interface_new_folder_0']/span[@class = 'dijitReset dijitInline dijitIcon iconNewFolder']"))).click().build().perform();
		// Folder name
		driver.findElement(By.id("ithit_interface_new_folder")).clear();
		driver.findElement(By.id("ithit_interface_new_folder")).sendKeys(
				"Selenium Explore Test Folder " + df.format(dateobj));
		ExploreFolderName = driver.findElement(By.id("ithit_interface_new_folder")).getAttribute("value");
		logger.log(Status.INFO, "The Folder name is entered"); 
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "ExploreFolderCreation"));
		// Click Ok button
		driver.findElement(By.id("ihDijit_dijit_form_Button_3")).click();
		Thread.sleep(6000);
        
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id ='ihDijit_dojox_grid__View_2']//div[@class = 'dojoxGridCellSpacer']/span[contains(text(),'"+ ExploreFolderName+"')]")).isDisplayed());
        Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("DialogPaneFrame1");
		
		//Close button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//img[@id = 'DialogContainerCloseButtonImage']")).click();
		
		Thread.sleep(15000);
		driver.switchTo().defaultContent();
        Thread.sleep(2000);
        
		
	}  */
/*
	@Test
	public void gUploadDocumentWithExplore() throws IOException,
			InterruptedException {
		logger = report.createTest("Upload document with Explore");
		try {
		// Select created folder
			
					    
			driver.findElement(By.xpath("//div[@id = 'ithit_interface_foldersTree_0']//div[@class = 'ygtvchildren']//a[contains(text(),'"+programToBeClicked+"')]")).click();   
		   driver.findElement(By.xpath("//div[@id = 'ithit_interface_foldersTree_0']//div[@class = 'ygtvchildren']//a[contains(text(),'"+programToBeClicked+"')]/ancestor::table//following-sibling::div[@class='ygtvchildren']//a[contains(text(),'Documents')]")).click();;
		   driver.findElement(By.xpath("//div[@id = 'ithit_interface_foldersTree_0']//div[@class = 'ygtvchildren']//a[contains(text(),'"+programToBeClicked+"')]/ancestor::table//following-sibling::div[@class='ygtvchildren']//td[4]/div[@class='ygtvspacer']")).click();;
		   WebElement folderLoc = (driver.findElement(By.xpath("//div[@id = 'ithit_interface_foldersTree_0']//div[@class = 'ygtvchildren']//a[contains(text(),'"+ExploreFolderName+"')]")));
	       action.click(folderLoc).build().perform();
		   Thread.sleep(2000);
		    
		    driver.findElement(By.id("ithit_interface_upload_0_label")).click();
		    
					     	
			    String f1 = filePath.concat("/Selenium_Resource/AUTOIT Scripts/UploadFile.exe "	+ uploadPath + "");
			    Runtime.getRuntime().exec(f1);

			    Thread.sleep(5000);
Thread.sleep(5000);
			    // Browse button
			    Assert.assertTrue(driver.findElement(By.xpath("//div[@id ='ihDijit_dojox_grid__View_2']//div[@class = 'dojoxGridScrollbox']//span[contains(text(),'au')]")).isDisplayed());
		        
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("DialogPaneFrame1");
				driver.findElement(By.xpath("//img[@id = 'DialogContainerCloseButtonImage']")).click();
				driver.switchTo().defaultContent();
Thread.sleep(5000);
		
			if(driver.findElements(By.id("DialogPaneFrame1")).size()>0)
			{
				System.out.println("trying to close explore");
				driver.findElement(By.xpath("//img[@id = 'DialogContainerCloseButtonImage']")).click();
				driver.switchTo().defaultContent();
Thread.sleep(5000);
				Assert.fail();
				logger.log(Status.INFO, "Please test Explore box manually", logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "ExploreIssue")));
			}
						
	}
	catch (Exception e){
			// Check for Explore Error
				logger.log(Status.INFO, "Please test Explore box manually", logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "ExploreError")));
				//Get error message content
				//logger.log(Status.INFO, "The error message " + driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[3]/div[2]/table/tbody/tr[1]/td/div")).getText());
				driver.findElement(By.id("ihDijit_dijit_form_Button_33")).click();
				
			
   }	
}
		
*/	

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
	     	logger.fail(result.getThrowable().getMessage());
	     	logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
				System.out.println("The test for " + result.getName()+ " is failed");
			   							
							
		} else {
			logger.pass("The test case " + result.getName() + "is passed");
			logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
			System.out.println("The test for " + result.getName() + " is pass");
		}
		
		report.flush();
	}

}
