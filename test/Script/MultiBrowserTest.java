package Script;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

public class MultiBrowserTest {
	public WebDriver driver;
	  
	  @Parameters("browser")
	  @BeforeClass
	  // Passing Browser parameter from TestNG xml
	  public void beforeTest(String browser){
	  // If the browser is Firefox, then do this
	  if(browser.equalsIgnoreCase("firefox")) {
		  driver = new FirefoxDriver();
		  System.out.println("firefox browser launched..");
	  // If browser is Chrome, then do this	  
	  }else if (browser.equalsIgnoreCase("chrome")) { 
		  // Here I am setting up the path for my ChromeDriver
		  System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		  driver = new ChromeDriver();
		  System.out.println("Chrome browser launched..");
		  // If browser is Safari, then do this	  
	  }else if (browser.equalsIgnoreCase("safari")) {  
		  // Here I am setting up the path for my SafariDriver
		  //System.setProperty("webdriver.safari.driver", "test/Resources/Data/chromedriver");
		  driver = new SafariDriver();
		  System.out.println("safari browser launched..");
	  } 
	      // Doesn't the browser type, lauch the Website
	      driver.get("http://etazo.tangosoftware.com"); 
	  }
	  
	  @Parameters("browser") 
	  @AfterMethod(alwaysRun = true) 
	  public void catchExceptions(ITestResult result, String browser) throws IOException 
	  { 
	  System.out.println("result" + result); 
	  String methodName = result.getName(); 
	  System.out.println(methodName); 
	  if (result.isSuccess() == false) 
	  { 
	  if(browser.equalsIgnoreCase("firefox")) 
	  { 
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd-hh.mm.ss"); 
		  String destFile = dateFormat.format(new Date()) + ".png"; 
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		  FileUtils.copyFile(scrFile, new File("/Users/shivaleelah/Desktop/Firefox/Success_screenshots" + "/" +destFile)); 
	  } 
	  else if (browser.equalsIgnoreCase("safari")) 
	  { 
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd-hh.mm.ss"); 
		  String destFile = dateFormat.format(new Date()) + ".png"; 
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		  FileUtils.copyFile(scrFile, new File("/Users/shivaleelah/Desktop/Safari/Success_screenshots" + "/" +destFile)); 
	  } 
	  else if (browser.equalsIgnoreCase("chrome")) 
	  { 
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd-hh.mm.ss"); 
		  String destFile = dateFormat.format(new Date()) + ".png"; 
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		  FileUtils.copyFile(scrFile, new File("/Users/shivaleelah/Desktop/Chrome/Success_screenshots" + "/" +destFile)); 
	  } 
	  } 
	  else 
	  { 
	  if(browser.equalsIgnoreCase("firefox")) 
	  { 
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd-hh.mm.ss"); 
		  String destFile = dateFormat.format(new Date()) + ".png"; 
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		  FileUtils.copyFile(scrFile, new File("/Users/shivaleelah/Desktop/Firefox/Failure_screenshots" + "/" +destFile)); 
	  } 
	  else if (browser.equalsIgnoreCase("safari")) 
	  { 
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd-hh.mm.ss"); 
	  String destFile = dateFormat.format(new Date()) + ".png"; 
	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
	  FileUtils.copyFile(scrFile, new File("/Users/shivaleelah/Desktop/Safari/Failure_screenshots" + "/" +destFile)); 
	  } 
	  else if (browser.equalsIgnoreCase("chrome")) 
	  { 
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd-hh.mm.ss"); 
	  String destFile = dateFormat.format(new Date()) + ".png"; 
	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
	  FileUtils.copyFile(scrFile, new File("/Users/shivaleelah/Desktop/Chrome/Failure_screenshots" + "/" +destFile)); 
	  } 
	  } 
	  } 
	
	  @Test
	  public void login() throws InterruptedException{		  
		  driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
		  long timeoutInSeconds = 2;
		  WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds); 
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("districtID")));
		  driver.findElement(By.id("districtID")).sendKeys("161970");
		  driver.findElement(By.id("assessmentID")).sendKeys("18864A2");
		  driver.findElement(By.id("studentID")).sendKeys("81021");
		  driver.findElement(By.id("loginbutton")).click();
		  Thread.sleep(2000);
		  System.out.println("Login function executed!!");
		  Thread.sleep(2000);

		  driver.findElement(By.xpath("//*[@id='question-1']/bubbles/circle[1]")).click();	
		  driver.findElement(By.cssSelector("#question-6 > #answer")).sendKeys("test1");
		  driver.findElement(By.cssSelector("#question-7 > #answer")).sendKeys("test2");
		  driver.findElement(By.cssSelector("#question-8 > #answer")).sendKeys("test3");

	  }
	  
	  @AfterClass 
	  public void afterTest(){
		  driver.quit();
	  }
}
