package T1_Pkg;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

//static WebDriver driver = new ChromeDriver();
public class InitializeTest_T1 extends RunTestNg_T1 {
	
	static WebDriver driver = new ChromeDriver();
	//static WebDriver driver = new ChromeDriver();
	
	static Actions action = new Actions(driver);
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports report;
	static ExtentTest logger;
	static String DateTime ;
	static Wait<WebDriver> wait = new FluentWait<WebDriver> (driver).withTimeout(Duration.ofSeconds(50)).pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
	static WebElement waitElement;
	static DateFormat df = new SimpleDateFormat("dd-MM-yy_HH-mm");
	static DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
	static DateFormat df2 = new SimpleDateFormat("EEEE");
	static DateFormat df3 = new SimpleDateFormat("mm:ss:ms");
	// Store the current window handle
	//static String winHandleBefore ;
	static Date dateobj = new Date();
	static String FieldName ;
	Calendar cal = Calendar.getInstance();
	static String MasterTaskName;
	static String currentWeekEnd ;
	static String programToBeClicked;
	//static String uploadPath = filePath.concat("Selenium_Resource/Sample_Documents/au_user.txt").substring(1).replace('/', '\\');
	static JavascriptExecutor executor = (JavascriptExecutor)driver;
	
	
	
	static public String captureScreenshot(WebDriver driver,
			String ScreenshotName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
		String imagePath = filePath.concat("" +instName+"SeleniumScriptsReports\\FF_"+instName+"_"+ DateTime + "\\Screenshots\\" + ScreenshotName+ df.format(dateobj) + ".jpg");
		File screenshotDest = new File(imagePath);

		FileUtils.copyFile(screenshotFile, screenshotDest);

		return imagePath;
	}

	static public void highLightElement(WebDriver driver, WebElement element) {
		

		executor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",element);

	}

	static public void removeHighLightElement(WebDriver driver,
			WebElement element) {
		
		executor.executeScript("arguments[0].setAttribute('style','border: solid 2px white');",element);

	}

}
