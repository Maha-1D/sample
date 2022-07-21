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



public class ReportsTab_T1 extends InitializeTest_T1 {
	static String ReportName;
	static String category;

	@Test
	public void aNaviagteToReportsTab() throws InterruptedException {
		logger = report.createTest("Navigate to Reports Tab");

		driver.findElement(By.xpath("//div[@id = 'nav']/ul/li[3]/ul/li/a/span[contains(text(), 'Reports')]")).click();
	    Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("MainViewPaneTableCell"))));
   
        Assert.assertTrue(driver.findElement(By.id("MainViewPaneTableCell")).isDisplayed());
	}

	@Test
	public void bCreateNewReport() throws IOException, InterruptedException {
		logger = report.createTest("Create a new report");

		// Click on New button
		driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']//ul/li[2]/div/span[1]/span[2]")).click();
		
		if(driver.findElements(By.id("mf-menu-cache-19")).size()<1)
		{
			driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']//ul/li[2]/div/span[1]/span[2]")).click();
            Thread.sleep(1000);
		}
			
		driver.switchTo().frame("mf-menu-cache-19");

		// Select Report option from dropdown
		driver.findElement(By.xpath("html/body/div/div/div/table/tbody/tr[1]/td[2]")).click();
		driver.switchTo().defaultContent();

		Thread.sleep(300);
		// Enter all data for creating new report
		WebElement selectReportType = driver.findElement(By.id("reportType"));
		Select selectReportTypeOption = new Select(selectReportType);
		selectReportTypeOption.selectByIndex(7);

		logger.log(Status.INFO, "The report wizard is displayed");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "ReportWizard"));

		// Select Next
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[1]/nobr/input[1]")).click();
        Thread.sleep(300);
		// Click on checkbox
		driver.findElement(By.xpath(".//*[@id='filterByDate']")).click();

		// Select Next again
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[1]/nobr/input[2]")).click();
        Thread.sleep(300);
		// Select columns
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[2]/table/tbody/tr[2]/td[1]/select/option[2]")).click();
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[2]/table/tbody/tr[2]/td[2]/input[1]")).click();
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[2]/table/tbody/tr[2]/td[1]/select/option[4]")).click();
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[2]/table/tbody/tr[2]/td[2]/input[1]")).click();
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[2]/table/tbody/tr[2]/td[1]/select/option[8]")).click();
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[2]/table/tbody/tr[2]/td[2]/input[1]")).click();
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[2]/table/tbody/tr[2]/td[1]/select/option[9]")).click();
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[2]/table/tbody/tr[2]/td[2]/input[1]")).click();

		logger.log(Status.INFO, "The report fields are displayed"); 
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "ReportColumn"));

		// Select next
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[1]/nobr/input[2]")).click();
         Thread.sleep(300);

		// Select next
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[1]/nobr/input[2]")).click();
           Thread.sleep(300);

		// Enter report title
		driver.findElement(By.id("title")).sendKeys("Test Selenium Report " + df.format(dateobj));
		ReportName = driver.findElement(By.id("title")).getAttribute("value");

		// Select Finish
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/div/table/tbody/tr/td/form/div[1]/nobr/input[2]")).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("MainViewPaneTableCell"))));
		
		// Assert if report is created
		List<WebElement> reportList = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/div[2]/table/tbody/tr/td/div[3]/table")).findElements(By.tagName("td"));
		for (WebElement td : reportList) {
			if (td.getAttribute("class").contains("titlerow")) {
				Assert.assertTrue(td.getText().contentEquals(ReportName));
				break;
			}
		}
	}

	@Test
	public void cOpenReport() throws IOException, InterruptedException {
		logger = report.createTest("Open an existing report");
       	// Click on admin reports arrow or PSD arrow
        
        System.out.println("Opening report");
		WebElement locOfReport = driver.findElement(By.xpath("//td[@id = 'MainViewPaneTableCell']//table/tbody//tr[contains(@class,'titlerow')]/td[contains(text(), '"+ReportName+"')]"));
				locOfReport.click();
			
				Thread.sleep(500);
				driver.findElement(By.xpath("//*[@id='MainViewPaneTableCell']//ul/li[2]/div/span[2]/span[2]")).click();
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//iframe"))));
		        Assert.assertTrue(driver.findElement(By.id("frmSetupReportForm")).isDisplayed());

	}

	@Test
	public void dExportReportToExcel() throws IOException, InterruptedException {
		logger = report.createTest("Export report to Excel");
		
		// Export to Excel
		action.moveToElement(driver.findElement(By.xpath("//img[@src = 'images/nav/export_excel.svg']"))).click().build().perform();
        Thread.sleep(500);
		logger.log(Status.INFO, "The excel download prompt is shown"); 
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "XlsDownload"));
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.findElement(By.id("frmSetupReportForm")).isDisplayed());
	}

	@Test
	public void eExportToPDF() throws IOException, InterruptedException {
		logger = report.createTest("Export report to PDF");
		// Export to PDF
		
		action.moveToElement(driver.findElement(By.xpath("//img[@src = 'images/dialog/report/export_to_pdf.svg']"))).click().build().perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//iframe[@id='DialogPaneFrame1']"))));
		driver.switchTo().frame("DialogPaneFrame1").switchTo().frame("DialogContainerBodyContentFrame");
		
		driver.findElement(By.id("ExportButton_Label")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("CloseButton_Label"))));
		logger.log(Status.INFO, "The pdf download prompt is shown");
		logger.addScreenCaptureFromPath(InitializeTest_T1.captureScreenshot(driver, "PdfDownload"));

		// Close PDF export wizard
		driver.findElement(By.id("CloseButton_Label")).click();

		driver.switchTo().defaultContent();
         Thread.sleep(500 * sleepMultiple);
	
		Assert.assertTrue(driver.findElement(By.id("frmSetupReportForm")).isDisplayed());
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
