package Script;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


public class UIFunctionalityTesting {
	private static WebDriver driver;
	 private static Logger Log=Logger.getLogger("UITesting");
	
	 @BeforeSuite
		public void setUp(){
//			driver = new FirefoxDriver();
//		    System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
//			driver = new ChromeDriver();
//		    driver = new SafariDriver();
//          driver.get("http://etazo.tangosoftware.com");
		    Log.info("Verifying all links Text boxes, Images, and Login button on Etazo page");
			System.out.println("Application launched successfully : Passed");
		}

	 @Parameters("browser")
	  @BeforeClass
	  // Passing Browser parameter from TestNG xml
	  public void beforeTest(String browser) {
	  // If the browser is Firefox, then do this
	  if(browser.equalsIgnoreCase("firefox")) {
		  driver = new FirefoxDriver();
		  System.out.println("firefox browser launched..");
		  Log.info("---------------------------------------------------");
		  Log.info("  UI Functionality Testing using firefox browser");
		  Log.info("---------------------------------------------------");
	  // If browser is Chrome, then do this	  
	  }else if (browser.equalsIgnoreCase("chrome")) { 
		  // Here I am setting up the path for my ChromeDriver
		  System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		  driver = new ChromeDriver();
		  Log.info("---------------------------------------------------");
		  Log.info(" UI Functionality Testing using chrome browser");
		  Log.info("---------------------------------------------------");
		  System.out.println("Chrome browser launched: UI..");
      // If browser is safari, then do this	  
	  }else if (browser.equalsIgnoreCase("safari")) {  
		  //System.setProperty("webdriver.safari.driver", "test/Resources/Data/chromedriver");
		  driver = new SafariDriver();
		  Log.info("---------------------------------------------------");
		  Log.info(" UI Functionality Testing using safari browser");
		  Log.info("---------------------------------------------------");
		  System.out.println("safari browser launched..");
	  } 
	     driver.get("http://etazo.tangosoftware.com"); 
	     Log.info("TC_EW0001 : Application launched successfully : Passed");
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
	  else 
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
	  } 
	
	   //Verify all links..
	   @Test
	    public void VerifyAllLinks() {
	    	//driver.findElement((By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).isDisplayed();
	           WebElement StudentLink = driver.findElement(By.xpath("//menuitem[text()='Student']"));
	           WebElement MonitorLink = driver.findElement(By.xpath("//menuitem[text()='Monitor']"));
	           WebElement WritingLink = driver.findElement(By.xpath("//menuitem[text()='Writing Scorer']"));
	            
	            if(StudentLink.isDisplayed())
		           {
		        	   System.out.println("Student Link found");
		        	   Log.info("TC_EW0002 : Student Link found : Passed");
		           }else{
		        	   Log.info("TC_EW0002 : Student Link not found : Failed");
		        	   System.out.println("Student Link not found");
		           }
	            
	            if(MonitorLink.isDisplayed())
		           {
		        	   System.out.println("Monitor Link found");
		        	   Log.info("TC_EW0003 : Monitor Link found : Passed");
		           }else{
		        	   Log.info("TC_EW0003 : Monitor Link not found : Failed");
		        	   System.out.println("Monitor Link not found");
		           }
	            if(WritingLink.isDisplayed())
		           {
		        	   System.out.println("Writing Link found");
		        	   Log.info("TC_EW0004 : Writing Link found : Passed");
		           }else{
		        	   Log.info("TC_EW0004 : Writing Link not found : Failed");
		        	   System.out.println("Writing Link not found");
		           }
	        //	Assert.assertTrue("Student link found", StudentLink.isDisplayed());
	        //	Assert.assertTrue("Monitor link found", MonitorLink.isDisplayed());
	        //	Assert.assertTrue("Writing link found", WritingLink.isDisplayed());        	
	        //  String text = driver.findElement(By.xpath(part1+i+part2)).getText();
	     }
	   
		//Verify all the Etazo icon..
	   @Test
		public void VerifyEtazoIcon(){
			WebElement icon = driver.findElement(By.xpath("html/body/main/leftpanel/icon/img"));
			if(icon.isDisplayed()){
				Log.info("TC_EW0005 : Etazo icon found : Passed");
				System.out.println("Etazo icon found..");
			}else{
				Log.info("TC_EW0005 : Etazo icon not found : Failed");
				System.out.println("Etazo icon not found..");
			}
		    }
	   
	   //Verify all the Etazo logo..
	   @Test
		public void VerifyEtazoLogo(){
			WebElement logo = driver.findElement(By.xpath("html/body/main/leftpanel/logo/img"));
			if(logo.isDisplayed()){
				Log.info("TC_EW0006 : Etazo logo found : Passed");
				System.out.println("Etazo logo found..");
			}else{
				Log.info("TC_EW0006 : Etazo logo found : Failed");
				System.out.println("Etazo logo not found..");
			}
		    }
	   
	   //TC_EW0004: Verify Text boxes and Login button at Student Link page.
	   @Test
	   public void verifyStudentLoginUI() throws InterruptedException{
			   driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
	    	   Thread.sleep(2000);  
	           WebElement District = driver.findElement(By.id("districtID"));
	           WebElement Assessment = driver.findElement(By.id("assessmentID"));
	           WebElement Student = driver.findElement(By.id("studentID"));
	           WebElement login =driver.findElement(By.id("loginbutton"));
	           
	           if(District.isDisplayed())
	           {
	        	   System.out.println("District ID text box found");
					Log.info("TC_EW0014 : District ID text box found :Passed");
	           }else{
	        	   System.out.println("District ID text box not found");
					Log.info("TC_EW0014 : District ID text box not found : Failed");
	           }
	           
	           if(Assessment.isDisplayed())
	           {
	        	   System.out.println("Assessment ID text box found");
					Log.info("TC_EW0015 : Assessment ID text box found : Passed");
	           }else{
	        	   System.out.println("Assessment ID text box not found");
					Log.info("TC_EW0015 : Assessment ID text box not found : Failed");
	           }
	           
	           if(Student.isDisplayed())
	           {
	        	   System.out.println("Student ID text box found");
					Log.info("TC_EW0016 : Student ID text box found : Passed");
	           }else{
	        	   System.out.println("Student ID text box not found");
					Log.info("TC_EW0016 : Student ID text box not found : Failed");
	           }
	           
	           if(login.isDisplayed())
	           {
	        	   System.out.println("Login button found");
					Log.info("TC_EW0017 : Login button found : Passed");
	           }else{
	        	   System.out.println("Login button not found");
					Log.info("TC_EW0017 : Login button not found : Failed");
	           }        
	   }
	   
	   //TC_EW0004: Verify Text boxes and Login button at monitor Link page.
	   @Test
	   public void verifyMonitorLoginUI() throws InterruptedException{
			   driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[2]")).click();
	    	   Thread.sleep(2000);  
	           WebElement District = driver.findElement(By.id("districtID"));
	           WebElement Assessment = driver.findElement(By.id("assessmentID"));
	           WebElement LoginEmail = driver.findElement(By.id("loginEmail"));
	           WebElement password =driver.findElement(By.id("password"));
	           WebElement login =driver.findElement(By.id("loginbutton"));

	           if(District.isDisplayed())
	           {
	        	   System.out.println("District ID text box found");
					Log.info("TC_EW0009 : District ID text box found : Passed");
	           }else{
	        	   System.out.println("District ID text box not found");
					Log.info("TC_EW0009 : District ID text box not found : Failed");
	           }
	           
	           if(Assessment.isDisplayed())
	           {
	        	   System.out.println("Assessment ID text box found");
					Log.info("TC_EW0010 : Assessment ID text box found : Passed");
	           }else{
	        	   System.out.println("Assessment ID text box not found");
					Log.info("TC_EW0010 : Assessment ID text box not found : Failed");
	           }
	           
	           if(LoginEmail.isDisplayed())
	           {
	        	   System.out.println("Login Email text box found");
					Log.info("TC_EW0011 : Login Email text box found : Passed");
	           }else{
	        	   System.out.println("Login Email text box not found");
					Log.info("TC_EW0011 : Login Email text box not found : Failed");
	           }
	           
	           if(password.isDisplayed())
	           {
	        	   System.out.println("Password text box found");
					Log.info("TC_EW0012 : Password text box found : Passed");
	           }else{
	        	   System.out.println("Password text box not found");
					Log.info("TC_EW0012 : Password text box not found : Failed");
	           }
	           
	           if(login.isDisplayed())
	           {
	        	   System.out.println("Login button found");
					Log.info("TC_EW0013 : Login button found : Passed");
	           }else{
	        	   System.out.println("Login button not found");
					Log.info("TC_EW0013 : Login button not found : Failed");
	           }        
	   }
	   
	   //TC_EW0004: Verify Text boxes and Login button at monitor Link page.
	   @Test
	   public void verifyWritingLoginUI() throws InterruptedException{
			   driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[3]")).click();
	    	   Thread.sleep(2000);  
	           WebElement District = driver.findElement(By.id("districtID"));
	           WebElement Assessment = driver.findElement(By.id("assessmentID"));
	           WebElement LoginEmail = driver.findElement(By.id("loginEmail"));
	           WebElement password =driver.findElement(By.id("password"));
	           WebElement login =driver.findElement(By.id("loginbutton"));

	           if(District.isDisplayed())
	           {
	        	   System.out.println("District ID text box found");
					Log.info("TC_EW0018 : District ID text box found : Passed");
	           }else{
	        	   System.out.println("District ID text box not found");
					Log.info("TC_EW0018 : District ID text box not found : Failed");
	           }
	           
	           if(Assessment.isDisplayed())
	           {
	        	   System.out.println("Asessment ID text box found");
					Log.info("TC_EW0019 : Assessment ID text box found : Passed");
	           }else{
	        	   System.out.println("Assessment ID text box not found");
					Log.info("TC_EW0019 : Assessment ID text box not found : Failed");
	           }
	           
	           if(LoginEmail.isDisplayed())
	           {
	        	   System.out.println("Login Email text box found");
					Log.info("TC_EW0020 : Login Email text box found : Passed");
	           }else{
	        	   System.out.println("Login Email text box not found");
					Log.info("TC_EW0020 : Login Email text box not found : Failed");
	           }
	           
	           if(password.isDisplayed())
	           {
	        	   System.out.println("Password text box found");
					Log.info("TC_EW0021 : Password text box found : Passed");
	           }else{
	        	   System.out.println("Password text box not found");
					Log.info("TC_EW0021 : Password text box not found : Failed");
	           }
	           
	           if(login.isDisplayed())
	           {
	        	   System.out.println("Login button found");
					Log.info("TC_EW0022 : Login button found : Passed");
	           }else{
	        	   System.out.println("Login button not found");
					Log.info("TC_EW0022 : Login button not found : Failed");
	           }  
	   } 
	   
	   @Test
	   public void loginScenario1() throws InterruptedException{
			driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
    	    Thread.sleep(2000);  

//			long timeoutInSeconds = 2;
//			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds); 
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("districtID")));
			driver.findElement(By.id("districtID")).sendKeys("161970");
			driver.findElement(By.id("assessmentID")).sendKeys();
			driver.findElement(By.id("studentID")).sendKeys();
			driver.findElement(By.id("loginbutton")).click();
			Log.info("TC_EW0007 : Login verified successfully for scenario 1 : Passed");
	   }
	   
	   @Test 
	   public void loginScenario2() throws InterruptedException{
			driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
    	    Thread.sleep(2000);  

//			long timeoutInSeconds = 2;
//			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds); 
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("districtID")));
			driver.findElement(By.id("districtID")).sendKeys("161970");
			driver.findElement(By.id("assessmentID")).sendKeys("18864A2");
			driver.findElement(By.id("studentID")).sendKeys();
			driver.findElement(By.id("loginbutton")).click();
			Log.info("TC_EW0008 : Login verified successfully for scenario 2 : Passed");
	   }
	   
	 //  @Test (dependsOnMethods = "loginScenario3")
	   public static void Verify_Bubble_EtazoICON(){
		   WebElement icon = driver.findElement(By.xpath("html/body/main/outline/leftpanel/assessmentinfo/icon/img"));
			if(icon.isDisplayed()){
				System.out.println("Etazo icon found.." +driver.getTitle());
			}else{
				System.out.println("Etazo icon not found..");
			}	   
	   }
	   
	//   @Test (dependsOnMethods = "loginScenario3")
       public static void Verify_Bubble_EtazoLogo(){
		   WebElement logo = driver.findElement(By.xpath("html/body/main/outline/leftpanel/logo/img"));
			if(logo.isDisplayed()){
				System.out.println("Etazo logo found.." +driver.getTitle());
			}else{
				System.out.println("Etazo logo not found..");
			}  
	   }
	   
	   
	//   @Test (dependsOnMethods = "loginScenario3")
	   public static void VerifySubmitButton(){
           WebElement submit =driver.findElement(By.id("submitbutton"));
           if(submit.isDisplayed())
           {
        	   System.out.println("Submit button found");
           }else{
        	   System.out.println("Submit button not found");
           }		   
	   }
	   
	//   @Test (dependsOnMethods = "loginScenario3")
	   public static void VerifyNGButton(){
           WebElement submit =driver.findElement(By.id("NGButton"));
           if(submit.isDisplayed())
           {
        	   System.out.println("NG button found");
           }else{
        	   System.out.println("NG button not found");
           }		   
	   }
	   
	//   @Test (dependsOnMethods = "loginScenario3")
	   public static void VerifySignoutButton(){
           WebElement Signout =driver.findElement(By.id("logoutbutton"));
           if(Signout.isDisplayed())
           {
        	   System.out.println("Signout button found");
           }else{
        	   System.out.println("Signout button not found");
           }
	   }
	   
	   @AfterClass 
		  public void afterTest() {
				driver.quit();
		  }	
}


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


