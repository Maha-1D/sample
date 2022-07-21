package T1_Pkg;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class IPWCLogin_T1 extends InitializeTest_T1 {
	
	@Test
	public void aGetIPWCLoginPage() throws InterruptedException {
		
		DateTime = df.format(dateobj);
		htmlReporter = new ExtentHtmlReporter(filePath.concat("" + instName +"SeleniumScriptsReports/FF_"+instName+"_" + DateTime	+ "/QLOTest_" + DateTime + ".html"));
		
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		logger = report.createTest("Test for login page");
		switch(instName)
		{
		case "BLUEJAY" : 
			driver.get("https://w3-01.ibm.com/software/project/bluejay/");
		    break;
		    
		case "BLUEJAY EXT" : 
			driver.get("https://www-150.ibm.com/software/project/bluejay/");
		    break;
		    
			
		case "GTS" : 
			driver.get("https://w3-01.ibm.com/software/ipwc/gts/");
		    break;
		 
		case "GTS EXT" : 
			driver.get("https://www-150.ibm.com/software/ipwc/gts/");
		    break;
		    
		case "GTSTT" : 
			driver.get("https://w3-01.ibm.com/software/ipwc/pmgtstt/");
			break;
		    
		case "GTSTT EXT" : 
			driver.get("https://www-150.ibm.com/software/ipwc/pmgtstt/");
			break;
			
			
		case "PMIBMUS" : 
			driver.get("https://w3-01.ibm.com/software/ipwc/ibm/");
		    break;
		    
		case "PMIBMUS EXT" : 
			driver.get("https://www-150.ibm.com/software/ipwc/ibm/");
		    break;
		    
		case "IGA" : 
			driver.get("https://w3-01.ibm.com/software/ipwc/iga/");
		    break;
		    
		case "PMEU" : 
			driver.get("https://w3-01.ibm.com/software/ipwc/pmeu/");
		    break;
		
		case "PMEU EXT" : 
			driver.get("https://www-150.ibm.com/software/ipwc/pmeu/");
		    break;
		    
		case "PMIBMIN" : 
			driver.get("https://w3-08.ibm.com/software/ipwc/in/");
		    break;    
		
		case "AU" : 
			driver.get("https://w3-07.ibm.com/software/ipwc/au/");
		    break;
		    
		case "AU2" : 
			driver.get("https://w3-07.ibm.com/software/ipwc/asia_pacific/au/");
		    break;
		    
		case "AU2 EXT" : 
			driver.get("https://www-750.ibm.com/software/ipwc/asia_pacific/au/");
		    break;
		    
		    
		case "MAA" :
			driver.get("https://w3-03.ibm.com/software/maa/finance/");
			break;
			
		case "PSAPP" : 
			driver.get("https://maaccelerator.com/us/psapp1/");
			break;
			
		case "SLPREVIEW" : 
			driver.get("https://ipwc-dr.ihost.com/softlayer/preview/aquilA3");
			break;
		
		case "GDPR" : 
			driver.get("https://eu.ipwc.ihost.com/eu/GDPR/");
			break;
			
		case "PWDEMO" :
			driver.get("https://ipwc.ihost.com/us/pwdemo");
			break;
			
		case "PAPlayground" :
			driver.get("https://ipwc.ihost.com/us/paplayground");
			break;
			
		case "KENEXA" :
			driver.get("https://ipwc.ihost.com/us/ibmsmarterworkforce");
			break;
			
		case "NTRS" :
			driver.get("https://ipwc.ihost.com/us/ntrs");
			break;
		case "MA2" :
			driver.get("https://maaccelerator.ihost.com/us/ma2/");
			break;
			
		case "PA2" :
			driver.get(" https://ipwc.ihost.com/us/pa2");
			break;
			
		case "SUNCOR" :
			driver.get("https://ipwc.ihost.com/us/suncor");
			break;
			
		case "MA3" :
			driver.get("https://maaccelerator.ihost.com/us/ma3/");
			break;
			
		case "PA3" :
			driver.get(" https://ipwc.ihost.com/us/pA3");
			break;
			
					
		case "MERLIN" :
			driver.get("https://ipwc.ihost.com/us/merlin");
			break;
				
		case "HALLIBURTON" :
			driver.get("https://maaccelerator.ihost.com/us/mac/");
			break;
			
		case "AXP" : 
			  driver.get("https://ipwc.ihost.com/us/AXP");
			  break;
			  
			case "AA" : 
			    driver.get("https://maaccelerator.ihost.com/us/aa/");
			    break;
			    
			case "CISCO" : 
			     driver.get("https://maaccelerator.ihost.com/us/cisco/");
			     break;
			     
			 case "DELL" :
			      driver.get("https://maaccelerator.ihost.com/us/diamond/");
			      break;
			      
			case "MA1" :
			    driver.get("https://maaccelerator.ihost.com/us/ma1/");
			    break;
			    
			case "MADEMO" : 
			     driver.get("https://maaccelerator.ihost.com/us/mademo/");
			     break;
			     
			case "MAPlayGround" :
			      driver.get("https://maaccelerator.ihost.com/us/maplayground/");
			      break;
			      
			case "MATraining" : 
			      driver.get("https://maaccelerator.ihost.com/us/matraining");
			      break;
			      
			case "NASCO" : 
			      driver.get("https://ipwc.ihost.com/us/nasco/");
			      break;
			      
			case  "PWTraining" : 
			      driver.get("https://ipwc.ihost.com/us/pwtraining/");
			      break;
			      
			case "SMARTROOM" : 
			     driver.get("https://maaccelerator.ihost.com/us/smartroom/");
			     break;
			     
			case "NEWRELIC" :
				driver.get("https://ipwc-cloud.com/aquilT1newrelic/");
				break;
			
			case "WALMART" : 
			     driver.get("https://maaccelerator.ihost.com/us/walmart");
			     break;
			     
			case "BEN" : 
			     driver.get("https://au.ipwc.ihost.com/au1/");
			     break;
			
			case "CLOUD AU2" : 
			     driver.get("https://g41p-r-00000802.az1.ash.cpc.ibm.com/software/ipwc/asia_pacific/au/");
			     break;
			     
			case "FCB" : 
			     driver.get("https://maaccelerator.ihost.com/us/fcb/");
			     break;
			     
			default : 
			     System.out.println("Please enter the correct instance name");
			     logger.info("Please enter the correct instance name");
			     break;
			
		}
		
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		executor.executeScript("window.focus()");
		
		Thread.sleep(1000);
		
		if(driver.findElements(By.xpath("//div[@id='credsDiv']")).size()>0)
		{
		                System.out.println("found web sign in");	
		                //Thread.sleep(1000);
						driver.findElement(By.xpath("//div[@id='credsDiv']")).click();
			            Thread.sleep(500);
			            Assert.assertTrue(driver.findElement(By.xpath("//*[@name = 'username']")).isDisplayed());
		}
		else if(instName.contains("PSAPP") || instName.contains("MERLIN") )
		{
			driver.findElement(By.xpath("//input[@id='companyEmail']")).click();
			driver.findElement(By.xpath("//input[@id='companyEmail']")).clear();
			driver.findElement(By.xpath("//input[@id='companyEmail']")).sendKeys(username);
			
			driver.findElement(By.xpath("//span[contains(text(),'Continue')]")).click();
			Thread.sleep(2000);
			if(driver.findElements(By.xpath("//div[@id='credsDiv']")).size()>0)
			{
			                System.out.println("found web sign in");	
			                Thread.sleep(2000);
							driver.findElement(By.xpath("//div[@id='credsDiv']")).click();
			}
			Assert.assertTrue(driver.findElement(By.xpath("//*[@name = 'username']")).isDisplayed());
		}
		
					
		if(instName.contains("EXT"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id = 'username']")));
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id = 'username']")).isDisplayed());
		}
		else if(instName.contains("HALLIBURTON"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name = 'username']")));
			Assert.assertTrue(driver.findElement(By.xpath("//*[@name = 'username']")).isDisplayed());
		}
		else 
		{
	  	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
			Assert.assertTrue(driver.findElement(By.name("password")).isDisplayed());
		}
	
	}

	@Test
	public void bEnterLoginCredentials() throws InterruptedException {
		logger = report.createTest("Enter Login Credentials");
		
        
		if(instName.contains("EXT"))
		{
			driver.findElement(By.xpath("//*[@id = 'username']")).click();
			driver.findElement(By.xpath("//*[@id = 'username']")).clear();
			driver.findElement(By.xpath("//*[@id = 'username']")).sendKeys(username);
			
			driver.findElement(By.id("continue-button")).click();
			Thread.sleep(500);
			System.out.println("going to pwd now");
			driver.switchTo().defaultContent();
			//action.moveToElement(driver.findElement(By.name("password"))).sendKeys(password);
			if(driver.findElements(By.xpath("//div[@id='credsDiv']")).size()>0)
			{
			                System.out.println("found web sign in");	
			                //Thread.sleep(2000);
							driver.findElement(By.xpath("//div[@id='credsDiv']")).click();
				            Thread.sleep(1000);
		    }
			driver.findElement(By.xpath("//*[@name = 'username']")).click();
			driver.findElement(By.xpath("//*[@name = 'username']")).clear();
			driver.findElement(By.xpath("//*[@name = 'username']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			
			//Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id = 'login-button']")).click();
			
		}
		
		if(instName.contains("HALLIBURTON"))
		{
			driver.findElement(By.xpath("//*[@name = 'username']")).click();
			driver.findElement(By.xpath("//*[@name = 'username']")).clear();
			driver.findElement(By.xpath("//*[@name = 'username']")).sendKeys(username);
			
			driver.findElement(By.id("idp-discovery-submit")).click();
			Thread.sleep(2000);
			System.out.println("going to pwd now");
			driver.switchTo().defaultContent();
			//action.moveToElement(driver.findElement(By.name("password"))).sendKeys(password);
			
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id = 'okta-signin-submit']")).click();
			
		}
		
		if(driver.findElements(By.xpath("//*[@name = 'username' or @id = 'userid' or @id = 'desktop']")).size()>0)
		{
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@name = 'username' or @id = 'userid' or @id = 'desktop']")).click();
			driver.findElement(By.xpath("//*[@name = 'username' or @id = 'userid' or @id = 'desktop']")).clear();
			driver.findElement(By.xpath("//*[@name = 'username' or @id = 'userid' or @id = 'desktop']")).sendKeys(username);
	
			driver.findElement(By.name("password")).click();
			driver.findElement(By.name("password")).clear();
			driver.findElement(By.name("password")).sendKeys(password);
			
			
				
				//Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id = 'login-button' or @id ='btn_signIn' or @id ='btn_signin' or @id = 'btnLogin'] ")).click();
			
		}
		/*else {
			driver.findElement(By.name("password")).click();
			driver.findElement(By.name("password")).clear();
			driver.findElement(By.name("password")).sendKeys(password);
			
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id = 'btnLogin' or @id ='btn_signIn' or @id ='signinbutton' or @id ='btn_signin']")).click();
			
		 }*/
		if (driver.findElements(By.xpath("//input[@id='otp-input']")).size()>0)
		{
			System.out.println("Please enter the OTP and continue ");
			Thread.sleep(5000);
			
		}
		//System.out.println("8000 * sleepMultiple = " + 8000 * sleepMultiple);
		//Thread.sleep(500 * sleepMultiple);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='MainViewPaneTable']")));
			
        
       	Assert.assertTrue(driver.findElement(By.id("masthead-mainbar")).isDisplayed());
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
			System.out.println("The test for " + result.getName() + " is pass");
		}
		
		report.flush();
	}

}