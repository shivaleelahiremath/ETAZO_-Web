package Script;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Student_Link {
	private static Logger Log=Logger.getLogger("Student_Link");
	String[][] tabArray = null;
	Workbook workbk;
	Sheet sheet;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/Student.xls";
	WebDriver driver;
    Map<String, Object[]> testresultdata;

/*	 @Parameters("browser")
	 @BeforeClass
	  // Passing Browser parameter from TestNG xml
    public void beforeTest(String browser) {
	     Log.info("Student Link administration document released with Displays Score, Display Missed Items and Allow Incomplete selection");
	  // If the browser is Firefox, then do this
	  if(browser.equalsIgnoreCase("firefox")) {
		  driver = new FirefoxDriver();
		  Log.info("-------------------------------------------------");
		  Log.info("   Student_Link Testing using firefox browser");
		  Log.info("-------------------------------------------------");
		  System.out.println("firefox browser launched..");
	  // If browser is Chrome, then do this	  
	  }else if (browser.equalsIgnoreCase("chrome")) { 
		  // Here I am setting up the path for my ChromeDriver
		  System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		  driver = new ChromeDriver();
		  Log.info("-------------------------------------------------");
		  Log.info("   Student_Link Testing using chrome browser");
		  Log.info("-------------------------------------------------");
		  System.out.println("Chrome browser launched..: Student Link");
		  // If browser is safari, then do this	  
	  }else if (browser.equalsIgnoreCase("safari")) {  
		  driver = new SafariDriver();
		  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  Log.info("-------------------------------------------------");
		  Log.info("   Student_Link Testing using safari browser");
		  Log.info("-------------------------------------------------");
		  System.out.println("safari browser launched..");
	  } 
	  driver.get("http://etazo.tangosoftware.com"); 
	  }
	  */
    
	@Test
	public void getExcelData() throws Exception {
		Workbook workbk = Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = workbk.getSheet("Sheet2");
		rowCount = sht.getRows();
		colCount = sht.getColumns();
		tabArray = new String[rowCount][colCount - 2];
		System.out.println("erow: " + rowCount);
		System.out.println("ecol: " + colCount);
		System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		driver = new ChromeDriver();
//		WebDriver driver = new FirefoxDriver(); 7
//	    driver = new SafariDriver();
		for (int row = 6; row < 7; row++) {
			driver.get("http://etazo.tangosoftware.com");
			driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
			administration(sht, row, driver);
		}
	}
	
	public void administration(Sheet sheet, int rowIndex, WebDriver driver) throws BiffException, IOException, InterruptedException {
		//driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
		//WebElement myDynamicElement = driver.findElement(By.id("myDynamicElement"));
		String DistrictID = sheet.getCell(0, rowIndex).getContents();
		String AssessmentID = sheet.getCell(1, rowIndex).getContents();
		String StudentID = sheet.getCell(2, rowIndex).getContents();
		System.out.println("Values received are :"+DistrictID+" "+AssessmentID+" "+StudentID);	 
		Thread.sleep(2000);
		driver.findElement(By.id("districtID")).sendKeys(DistrictID);
	    driver.findElement(By.id("assessmentID")).sendKeys(AssessmentID);
	    driver.findElement(By.id("studentID")).sendKeys(StudentID);
//	    driver.findElement(By.id("loginbutton")).click();
//      System.out.println("Student Login Successfull"); 
        Log.info("Student Link administration for Student ID : " +StudentID);
	    try{
		    driver.findElement(By.id("loginbutton")).click();
    		Thread.sleep(5000);
	        if (driver.getTitle().equals("Etazo Bubble Sheet"))
	        {
	        	System.out.println("Login Successfully : Passed");
	        	Log.info("Login Successful : Passed");
	        	      //add pass entry to the excel sheet
	        	System.out.println("add entry to the sheet..: Pass");
      	      testresultdata.put("12", new Object[] {1d, "navigate to site and login", "site opens and login success","Pass"});	        	    
	        } 
	        else if(driver.getTitle().equals("Etazo Score Sheet"))
	        {
	        	System.out.println("Student already administered for this assessment : Passed");
	        	Log.info("Student already administered for this assessment : Passed");
	        	 //add fail entry to the excel sheet
//       		 System.out.println("add entry to the sheet..: fail");
//   	      testresultdata.put("12", new Object[] {1d, "navigate to site and login", "site opens and login success","Fail"});
	    	    Thread.sleep(2000);  
	    	    driver.findElement(By.id("logoutbutton")).click();
	    	}
	        }catch(Exception e){
			  	System.out.println("Login not sucessfull..");	
	        	Log.info("Login not Successful : Failed");
//	     	      testresultdata.put("12", new Object[] {1d, "navigate to site and login", "site opens and login success","Fail"});
	    	}

        if (driver.getTitle().equals("Etazo Bubble Sheet")){
        //Multiple choice questions...
		for(int i=3, question=1; i<8; i++, question++){
	        String sValue =sheet.getCell(i, rowIndex).getContents();
    	    Thread.sleep(1000);  
			//System.out.println("Index: " +question+ " Answer Selection: " +sValue);
	        String[] answers = sValue.split(",");
			for (int j=0; j< answers.length; j++){
	        	if(!answers[j].isEmpty()){
	        		driver.findElement(By.xpath("//*[@id='question-"+question+"']/bubbles/circle["+answers[j].trim()+"]")).click();
	        	}
	        }
	    }
		//*[@id="answer"]
		
	    //Fill in the blanks questions..
		for(int i=8, question=6; i<13; i++, question++){
	        String sValue =sheet.getCell(i, rowIndex).getContents();
		//	System.out.println("Index: " +question+ " Answer Selection: " +sValue);
		//	WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));
		    if(!sValue.isEmpty()){
		//    	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        //		WebElement myDynamicElement = driver.findElement(By.id("myDynamicElement"));
		    	driver.findElement(By.xpath("//sa[@id='question-"+question+"']/input")).sendKeys(sValue);
		    }
        }
       
		//Place value, Exact point value and Advance place value questions..
		for(int i=13, question = 11; i<28; i++, question++) {
	        String sValue =sheet.getCell(i, rowIndex).getContents();
    	 // Thread.sleep(1000);  
		 // System.out.println("Index: " +question+ " Answer Selection: " +sValue);
	        char[] answer = sValue.toCharArray();
	        	for (int j=1, digit=1; j<=answer.length; j++,digit++){
	        		boolean isElementDisplayed = false;
	                try {
	                 isElementDisplayed = driver.findElement(By.xpath("(//pv[@id='question-"+question+"']//td[@id='digit-" +digit+"']/circle)[text()='"+answer[j-1]+"']")).isDisplayed();
	                } 
	                catch (Exception e) {
	                //System.out.println("Element is not displayed");
	                }
	                if(isElementDisplayed){
	                 driver.findElement(By.xpath("(//pv[@id='question-"+question+"']//td[@id='digit-" +digit+"']/circle)[text()='"+answer[j-1]+"']")).click();
	                }        		         
	        	    }
	     }

		// True or false
		for (int i = 28, question=26; i < 33; i++, question++) {
			String sValue = sheet.getCell(i, rowIndex).getContents().trim();
    	//    Thread.sleep(1000);  
		//  System.out.println("Index: " + question + " Answer Selection: " + sValue);
			if(!sValue.isEmpty()){
				driver.findElement(By.xpath("//mc[@id='question-"+question+"']/bubbles/circle[text()='"+sValue+"']")).click();
			}
		}
		
	    Thread.sleep(2000);  
        driver.findElement(By.id("submitbutton")).click();	
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("html/body/div[3]/div[3]/div/button[1]")).click();
	    Thread.sleep(3000);  
	    driver.findElement(By.xpath("//*[@id='confirm']/text")).click();
	    Thread.sleep(3000);  
	    driver.findElement(By.xpath("html/body/div[3]/div[3]/div/button")).click();
	    Thread.sleep(3000);  
	    String Expected = driver.findElement(By.xpath("//*[@id='Score']/text")).getText();
        System.out.println("Score from app: "+Expected);
	    try{
            String Score =sheet.getCell(33, rowIndex).getContents();
            System.out.println("Score from excel sheet: "+Score);    
            Assert.assertEquals(Score, Expected);
            Log.info("Administration completed successfully for " +Expected+ ": Passed");
            System.out.println("Administration completed successfully for " +Expected+ ": Passed");
	    }catch (Error e) {
            e.getMessage();
            System.out.println("Administration not completed successfully for " +Expected+ ": Failed");
            Log.info("Administration not completed successfully for " +Expected+ ": Failed");
	    }	    
	    driver.findElement(By.id("logoutbutton")).click();
        }
	}
	
	@AfterClass 
	public void afterTest() {
			driver.quit();
    }
}	
