package T1_Pkg;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;



public class TasksTab_T1 extends InitializeTest_T1 {
	static String SubTaskName;
	
	static String StartDate;
	static String EndDate;
	static int PCValueActual;
	static int PCValueExp;
	static double ActualWork;
	static double TotalWork;
	static double TotalWorkCalc;

	// create a calendar

	@Test
	public void aNavigateToTasksTab() throws InterruptedException {
		logger = report.createTest("Navigate to Task Tab");
        // Thread.sleep(5000);
		action.moveToElement(driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Tasks')]"))).click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-bar-div"))));
		Thread.sleep(1000 * sleepMultiple);
		if(driver.findElements(By.id("WorkcenterSectionContent")).size()>0)
		{
			driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Tasks')]")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-bar-div"))));
			Thread.sleep(1000 * sleepMultiple);
		}
       
		Assert.assertTrue(driver.findElement(By.id("menu-bar-div")).isDisplayed());
	}

	@Test
	public void bCreateNewMasterTask() throws IOException, InterruptedException {

		String[] numWBS = null;
		List<WebElement> getWBS = driver.findElements(By.xpath("//div[contains(@id,'TaskTree_TreeGridPanel-targetEl')]//table/tbody/tr/td[contains(@class ,'WBS')]"));
		int i=0;
		for (WebElement td : getWBS) {
				numWBS[i]=td.getText();
				i++;
				}
		  
		//Thread.sleep(2000 * sleepMultiple);		
		logger = report.createTest("Create New Master Task");
		// Click on Insert Button

		driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']/div[1]/ul/li[2]/div/span[4]/span[2]")).click();
        Thread.sleep(500);
		
		if(driver.findElements(By.id("mf-menu-cache-76")).size()<1)
		{
			driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']/div[1]/ul/li[2]/div/span[4]/span[2]")).click();
           // Thread.sleep(2000);
		}
		driver.switchTo().frame("mf-menu-cache-76");
		//Thread.sleep(2000 * sleepMultiple);
		// Select Task option in the drop down
		action.moveToElement(driver.findElement(By.xpath("//html/body/div/div/div/table/tbody/tr[1]/td[2]"))).click().build().perform();
		driver.switchTo().defaultContent();
        //Thread.sleep(8000);
		// Enter fields for the new task
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("title"))));
		driver.findElement(By.id("title")).clear();
	//	Thread.sleep(5000);
		driver.findElement(By.id("title")).sendKeys("Selenium Test Master Task " + df.format(dateobj));
		MasterTaskName = driver.findElement(By.id("title")).getAttribute("value");
        System.out.println("Master task name is " + MasterTaskName);
		// logger.log(Status.INFO,"Fields required to create Task are entered",
		// logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver,"MasterTaskFields")));

       if (driver.findElements(By.id("StartBy")).size()>0 && driver.findElements(By.id("CompleteBy")).size()>0)   
       {
        // Set Today's date to Start date
		driver.findElement(By.id("StartBy")).sendKeys(df1.format(dateobj));
		StartDate = driver.findElement(By.id("StartBy")).getAttribute("value");
       
		// add 3 days to the calendar
		cal.add(Calendar.DATE, 3);
		Date endDate = cal.getTime();
		
         
		// Set End date 3 days from today's date
		driver.findElement(By.id("CompleteBy")).sendKeys(df1.format(endDate));
		EndDate = driver.findElement(By.id("CompleteBy")).getAttribute("value");

       }
       
       // Thread.sleep(3000);
		// click on Save button
		driver.findElement(By.xpath("html/body/table/tbody/tr[3]/td/div/div/input[1]")).click();
		Thread.sleep(500 * sleepMultiple);
                
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-bar-div"))));
				// Assert if Task is created
		Assert.assertTrue(driver.findElement(By.xpath("//table/tbody/tr/td/div/span/div/span[contains(text(),'"+ MasterTaskName + "')]")).isDisplayed());
	
	}

	@Test
	public void fCreateNewSubTask() throws IOException, InterruptedException {

		logger = report.createTest("Create a Sub Task");
        //Thread.sleep(2000);
		// Click on Insert Button
		driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']/div[1]/ul/li[2]/div/span[4]/span[2]")).click();
       // Thread.sleep(2000 * sleepMultiple);
		driver.switchTo().frame("mf-menu-cache-76");

		// Select Subtask option
		driver.findElement(By.xpath("html/body/div/div/div/table/tbody/tr[2]/td[2]")).click();
		driver.switchTo().defaultContent();
      //  Thread.sleep(3000 * sleepMultiple);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("title"))));
		// Enter fields for the new task
		driver.findElement(By.id("title")).sendKeys("Selenium Test Sub Task" + df.format(dateobj));
		SubTaskName = driver.findElement(By.id("title")).getAttribute("value");
		if (instName.contentEquals("GTSTT"))
		{
	        
		}
        else{
		// Enter mandatory fields only
		List<WebElement> mandatoryFields = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/div/table/tbody/tr/td/form/table/tbody/tr[1]/td/div/div/div/table/tbody")).findElements(By.tagName("td"));
		for (WebElement td : mandatoryFields) {
			if (td.getAttribute("class").contentEquals("lbl")) {
				if (td.getText().contains("*")) {
					System.out.println("Inside mandatory field" + td.getText());
					WebElement nextElement = td.findElement(By.xpath("./following-sibling::*[1]"));
					if (nextElement.getAttribute("class").contentEquals("inpt")) {
						System.out.print("Inside next input");

						List<WebElement> childElements = nextElement.findElements(By.xpath("./child::*"));
						for (WebElement e : childElements) {
							System.out.println("checking child elements");
							System.out.println(e.getTagName());
							FieldName = e.getAttribute("id");
							if (e.getTagName().contains("select")) {
								System.out.println(FieldName);
								WebElement selectFieldDropdown = nextElement.findElement(By.id(FieldName));
								Select selectDropdownOption = new Select(selectFieldDropdown);
								selectDropdownOption.selectByIndex(1);
								System.out.println("This is dropdown");
								break;

							}
							if (e.getTagName().contains("textarea")) {
								e.findElement(By.id(FieldName)).sendKeys("This is the test data for");
								break;
							}

						}

					}
				}
			}
		} }
		logger.log(Status.INFO,"Fields required to create a sub task are entered");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "SubTaskFields"));

		// click on Save button
		driver.findElement(By.xpath("html/body/table/tbody/tr[3]/td/div/div/input[1]")).click();
		Thread.sleep(500 * sleepMultiple);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-bar-div"))));
		// Assert if SubTask is created
		Assert.assertTrue(driver.findElement(By.xpath("//table/tbody/tr/td/div/span/div/span[contains(text(),'"+ SubTaskName + "')]")).isDisplayed());

	}

	@Test
	public void gPromoteTask() throws IOException, InterruptedException {

		logger = report.createTest("Promote the Sub Task");
        
		// Click on Promote Button
	    if(driver.findElement(By.xpath("//tbody/tr/td/div[1]/ul/li[2]/div/span[13]/span[2]/img")).isEnabled())
	    {
	    	driver.findElement(By.xpath("//tbody/tr/td/div[1]/ul/li[2]/div/span[13]/span[2]/img")).click();
	        Thread.sleep(1000);
	    }
	    else
	    {
	    	driver.findElement(By.id("WBS_false-textEl")).click();
	    	if(driver.findElement(By.xpath("//div[@id = 'TaskTree_TreeGridPanel-body']/div/div[2]/table[1]/tbody/tr/td[2]/div/div/span")).getText().equals("1"))
	    	{
	    		driver.findElement(By.xpath("//tbody/tr/td/div[1]/ul/li[2]/div/span[13]/span[2]/img")).click();
		        Thread.sleep(1000);
		   	}
	    	else
	    	{
	    		driver.findElement(By.id("WBS_false-textEl")).click();
	    		driver.findElement(By.xpath("//tbody/tr/td/div[1]/ul/li[2]/div/span[13]/span[2]/img")).click();
		        Thread.sleep(1000);
	    	}
	    }
	    	
		driver.switchTo().frame("DialogPaneFrame1");
		driver.switchTo().frame("DialogContainerBodyContentFrame");
         Thread.sleep(200);
		logger.log(Status.INFO, "The Promote pop-up is shown");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "PromotePopUp"));
		driver.findElement(By.id("YesButton_Label")).click();
		driver.switchTo().defaultContent();
        //Thread.sleep(3000);

		Thread.sleep(500 * sleepMultiple);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-bar-div"))));
		
		List<WebElement> getSubTaskRow = driver.findElements(By.xpath("//div[contains(@id,'TaskTree_TreeGridPanel-targetEl')]//table/tbody/tr/td/div/span/div/span[contains(text(),'"+ SubTaskName+ "')]/ancestor::td/preceding-sibling::td"));
		//Thread.sleep(2000);
		for (WebElement td : getSubTaskRow) {
			if (td.getAttribute("class").contains("WBS")) {
				if (!td.getText().contains(".")){
							Assert.assertFalse(td.getText().contains("."));
				break;
			    }
			    else
			    {
			       logger.log(Status.INFO, "The Promote test case assertion has failed. Possible performance related issue. Re-asserting now");
	        	  // Thread.sleep(8000);
	     		   if (td.getAttribute("class").contains("WBS")) {
	     		 	Assert.assertFalse(td.getText().contains("."));
	     				break;
	     			}}}		
		
         }
        
	}

	@Test
	public void hDemoteTask() throws IOException, InterruptedException {

		logger = report.createTest("Demote the Sub Task");
        try{
		// Click on Demote Button
		driver.findElement(By.xpath("//table/tbody/tr/td/div[1]/ul/li[2]/div/span[14]/span[2]/img")).click();
		//Thread.sleep(2000);
		driver.switchTo().frame("DialogPaneFrame1");
		
		driver.switchTo().frame("DialogContainerBodyContentFrame");
//Thread.sleep(1000);
		logger.log(Status.INFO, "The Demote pop-up is shown");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "DemotePopUp"));
		driver.findElement(By.id("YesButton_Label")).click();

		driver.switchTo().defaultContent();
       //  Thread.sleep(3000);
		Thread.sleep(500 * sleepMultiple);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-bar-div"))));
		
		List<WebElement> getSubTaskRow = driver.findElements(By.xpath("//div[contains(@id,'TaskTree_TreeGridPanel-targetEl')]//table/tbody/tr/td/div/span/div/span[contains(text(),'"+ SubTaskName+ "')]/ancestor::td/preceding-sibling::td"));
		//Thread.sleep(3000);
		for (WebElement td : getSubTaskRow) {
			if (td.getAttribute("class").contains("WBS")) {
				Assert.assertTrue(td.getText().contains("."));
				break;
			}
		}}
        catch(AssertionError ae) {
        	logger.log(Status.INFO, "The Demote test case assertion has failed. Possible performance related issue. Re-asserting now");
        	List<WebElement> getSubTaskRow = driver.findElements(By.xpath("//div[contains(@id,'TaskTree_TreeGridPanel-targetEl')]//table/tbody/tr/td/div/span/div/span[contains(text(),'"+ SubTaskName+ "')]/ancestor::td/preceding-sibling::td"));
    		//Thread.sleep(4000);
    		for (WebElement td : getSubTaskRow) {
    			if (td.getAttribute("class").contains("WBS")) {
    				Assert.assertTrue(td.getText().contains("."));
    				break;
    			}}
            }
	}

	@Test
	public void iDeleteTask() throws IOException, InterruptedException {
		logger = report.createTest("Delete the Master Task");

		// Click on Delete Button
	
		// Select Delete icon
		executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//div[@id = 'menu-bar-div']/ul/li[2]/div/span[19]/span[2]/img")));
		driver.findElement(By.xpath("//div[@id = 'menu-bar-div']/ul/li[2]/div/span[19]/span[2]/img")).click();

		//Thread.sleep(2000);
		
		driver.switchTo().frame("DialogPaneFrame1").switchTo().frame("DialogContainerBodyContentFrame");
		logger.log(Status.INFO, "The Delete pop-up is shown");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "TaskDelete"));
		driver.findElement(By.id("YesButton_Label")).click();
		driver.switchTo().defaultContent();
		//Thread.sleep(5000 * sleepMultiple);
		Thread.sleep(2000 * sleepMultiple);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//iframe[@id='DialogPaneFrame1']"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-bar-div"))));
		// Assert if Task is deleted
		List<WebElement> listOfDoc = driver.findElement(By.xpath("//div[contains(@id,'TaskTree_TreeGridPanel-body')]//table/tbody/tr/td/div/span/div")).findElements(By.tagName("span"));
		for (WebElement span : listOfDoc) {
			if (span.getText().contentEquals(SubTaskName)) {
				Assert.fail();
				break;
			}
		}
	}

  @Test
	public void jCheckIfMSProjectEnabled() throws InterruptedException,IOException {
		logger = report.createTest("Verify if MS Project is enabled");

		// click on Tools
		executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']//ul/li[2]/div/span[7]/span[2]")));
		
		driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']//ul/li[2]/div/span[7]/span[2]")).click();
		Thread.sleep(2000 * sleepMultiple);		
		
		driver.switchTo().frame("mf-menu-cache-105");
		//Thread.sleep(200 * sleepMultiple);
		// Select Import option
		driver.findElement(By.xpath("//div[@id='scroll-container']//td[contains(text(),'Import Project')]")).click();
		driver.switchTo().defaultContent();

		Thread.sleep(500);
		driver.switchTo().frame("DialogPaneFrame1");
		driver.switchTo().frame("DialogContainerBodyContentFrame");
		logger.log(Status.INFO, "The Import panel is visible");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "MSEnabled"));
        Thread.sleep(200 * sleepMultiple);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("DialogPanel_HeaderPanel"))));
		Assert.assertTrue(driver.findElement(By.id("DialogPanel_HeaderPanel")).isDisplayed());

	}

	@Test
	public void kNavigateToGanttView() throws InterruptedException {

		driver.findElement(By.id("CancelButton_Label")).click();
		logger = report.createTest("Verify if Gantt View is displayed");
		
		driver.switchTo().defaultContent();
		Thread.sleep(500 * sleepMultiple);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-bar-div"))));
		
		executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id='NewGanttViewButton_Icon']")));
        
       Thread.sleep(500);
       driver.findElement(By.xpath("//*[@id='NewGanttViewButton_Icon']")).click();
			//Thread.sleep(2000 * sleepMultiple);
       wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ganttTimelineContent"))));
       
			if(driver.findElements(By.id("ganttTimelineContent")).size()<1)
			{
				System.out.println("Gantt not opened. clicking again");
				driver.findElement(By.xpath("//*[@id='NewGanttViewButton_Icon']")).click();
                Thread.sleep(1000);
			}
                 	
	else {
		 Assert.assertTrue(driver.findElement(By.id("ganttTimelineContent")).isDisplayed());	
			
        }	
       
	}
	
	@Test
	public void lExportGanttView() throws InterruptedException, IOException {

		
		logger = report.createTest("Verify if Gantt View is exported");
		action.moveToElement(driver.findElement(By.xpath("//img[@src = 'images/doctypes/pdf.svg']"))).click().build().perform();
		Thread.sleep(500);
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "GanttExport"));
     
		Assert.assertTrue(driver.findElement(By.xpath("//img[@src = 'images/doctypes/pdf.svg']")).isEnabled());		
	}
		
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				logger.fail(result.getThrowable().getMessage());
				logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
				System.out.println("The test for " + result.getName()+ " is failed");
			} catch (Throwable error) {
				logger.log(Status.INFO,
						"Cause of error is " + error.getMessage());
			}
		} else {
			logger.pass("The test case " + result.getName() + "is passed");
			logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, result.getName()));
			System.out.println("The test for " + result.getName()+ " is pass");
		}
		
		report.flush();
	}

}
