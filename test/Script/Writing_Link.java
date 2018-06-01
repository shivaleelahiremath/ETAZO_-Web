package Script;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Writing_Link {
	
	private static Logger Log = Logger.getLogger("Writing_Link");
	String[][] tabArray = null;
	Workbook workbk;
	Sheet sheet;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/Student.xls";
	WebDriver driver;
	
	@Parameters("browser")
	  @BeforeClass
	  // Passing Browser parameter from TestNG xml
	  public void beforeTest(String browser) {
		Log.info("Writing link administration document released with Displays Score, Display Missed Items and Allow Incomplete selection");
	  // If the browser is Firefox, then do this
	  if(browser.equalsIgnoreCase("firefox")) {
		  driver = new FirefoxDriver();
		  Log.info("-------------------------------------------------");
		  Log.info("   Writing_Link Testing using firefox browser");
		  Log.info("-------------------------------------------------");
		  System.out.println("firefox browser launched..");
	  // If browser is Chrome, then do this	  
	  }else if (browser.equalsIgnoreCase("chrome")) { 
		  // Here I am setting up the path for my ChromeDriver
		  System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
		  driver = new ChromeDriver();
		  Log.info("-------------------------------------------------");
		  Log.info("   Writing_Link Testing using chrome browser");
		  Log.info("-------------------------------------------------");
		  System.out.println("Chrome browser launched..: Student Link");
		  // If browser is safari, then do this	  
	  }else if (browser.equalsIgnoreCase("safari")) {  
		  driver = new SafariDriver();
		  Log.info("-------------------------------------------------");
		  Log.info("   Writing_Link Testing using safari browser");
		  Log.info("-------------------------------------------------");
		  System.out.println("safari browser launched..");
	  } 
	  driver.get("http://etazo.tangosoftware.com"); 
	  }
	
	@Test
	public void getExcelData() throws Exception {
		Workbook workbk = Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = workbk.getSheet("Sheet5");
		rowCount = sht.getRows();
		colCount = sht.getColumns();
		tabArray = new String[rowCount][colCount - 2];
		System.out.println("erow: " + rowCount);
		System.out.println("ecol: " + colCount);
	//	System.setProperty("webdriver.chrome.driver", "test/Resources/Data/chromedriver");
	//	driver = new ChromeDriver();
	//	WebDriver driver = new FirefoxDriver();
		for (int row = 1; row < 6; row++) {
			driver.get("http://etazo.tangosoftware.com");
	     	driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
			administration(sht, row, driver);
		}	
	}
	
	public void StudentLogin(Sheet sheet, int rowIndex, WebDriver driver)
	{
		String DistrictID = sheet.getCell(0, rowIndex).getContents();
		String AssessmentID = sheet.getCell(1, rowIndex).getContents();
		String StudentID = sheet.getCell(2, rowIndex).getContents();
		System.out.println("Values received are :"+DistrictID+" "+AssessmentID+" "+StudentID);
		driver.findElement(By.id("districtID")).sendKeys(DistrictID);
	    driver.findElement(By.id("assessmentID")).sendKeys(AssessmentID);
	    driver.findElement(By.id("studentID")).sendKeys(StudentID);
	//  driver.findElement(By.id("loginbutton")).click();	  
	//  System.out.println("Student Login Successfull...");   
        Log.info("Student and Writing Link administration for Student ID : "+StudentID);
	    try{
		    driver.findElement(By.id("loginbutton")).click();
	        if (driver.getTitle().equals("Etazo Bubble Sheet"))
	        {
	        	System.out.println("Login Successfully : Passed");
	        	Log.info("Login Successful : Passed");
	        } 
	        else if(driver.getTitle().equals("Etazo Score Sheet"))
	        {
	        	System.out.println("Student already administered for this assessment : Failed");
	        	Log.info("Student already administered for this assessment : Passed");
	    	    Thread.sleep(2000);  
	    	    driver.findElement(By.id("logoutbutton")).click();
	    	}
	        }catch(Exception e){
			  	System.out.println("Login not sucessfull..");
	        	Log.info("Login not Successful : Failed");
	    	}
	}
	
	public void WritingLogin(Sheet sheet, int rowIndex, WebDriver driver)
	{
		String DistrictID = sheet.getCell(0, rowIndex).getContents();
		String AssessmentID = sheet.getCell(1, rowIndex).getContents();
		String LoginEmail = sheet.getCell(11, rowIndex).getContents();
		String Password = sheet.getCell(12, rowIndex).getContents();
		System.out.println("Values received are :"+DistrictID+" "+AssessmentID+" "+LoginEmail+""+Password);
		driver.findElement(By.id("districtID")).sendKeys(DistrictID);
	    driver.findElement(By.id("assessmentID")).sendKeys(AssessmentID);
	    driver.findElement(By.id("loginEmail")).sendKeys(LoginEmail);
	    driver.findElement(By.id("password")).sendKeys(Password);    
	    driver.findElement(By.id("loginbutton")).click();	
	    System.out.println("Writing Login Successfull...");		
	}
	
	public void WritingLogin1(Sheet sheet, int rowIndex, WebDriver driver)
	{
		String DistrictID = sheet.getCell(0, rowIndex).getContents();
		String AssessmentID = sheet.getCell(1, rowIndex).getContents();
		String LoginEmail = sheet.getCell(16, rowIndex).getContents();
		String Password = sheet.getCell(17, rowIndex).getContents();
		System.out.println("Values received are :"+DistrictID+" "+AssessmentID+" "+LoginEmail+""+Password);
		driver.findElement(By.id("districtID")).sendKeys(DistrictID);
	    driver.findElement(By.id("assessmentID")).sendKeys(AssessmentID);
	    driver.findElement(By.id("loginEmail")).sendKeys(LoginEmail);
	    driver.findElement(By.id("password")).sendKeys(Password);   
	    driver.findElement(By.id("loginbutton")).click();	  
	}
	
	public void WritingLogin2(Sheet sheet, int rowIndex, WebDriver driver)
	{
		String DistrictID = sheet.getCell(0, rowIndex).getContents();
		String AssessmentID = sheet.getCell(1, rowIndex).getContents();
		String LoginEmail = sheet.getCell(21, rowIndex).getContents();
		String Password = sheet.getCell(22, rowIndex).getContents();
		System.out.println("Values received are :"+DistrictID+" "+AssessmentID+" "+LoginEmail+""+Password);
		driver.findElement(By.id("districtID")).sendKeys(DistrictID);
	    driver.findElement(By.id("assessmentID")).sendKeys(AssessmentID);
	    driver.findElement(By.id("loginEmail")).sendKeys(LoginEmail);
	    driver.findElement(By.id("password")).sendKeys(Password);    
	    driver.findElement(By.id("loginbutton")).click();	  
	}
	
	public void WritingLogin3(Sheet sheet, int rowIndex, WebDriver driver)
	{
		String DistrictID = sheet.getCell(0, rowIndex).getContents();
		String AssessmentID = sheet.getCell(1, rowIndex).getContents();
		String LoginEmail = sheet.getCell(25, rowIndex).getContents();
		String Password = sheet.getCell(26, rowIndex).getContents();
		System.out.println("Values received are :"+DistrictID+" "+AssessmentID+" "+LoginEmail+""+Password);
		driver.findElement(By.id("districtID")).sendKeys(DistrictID);
	    driver.findElement(By.id("assessmentID")).sendKeys(AssessmentID);
	    driver.findElement(By.id("loginEmail")).sendKeys(LoginEmail);
	    driver.findElement(By.id("password")).sendKeys(Password);    
	    driver.findElement(By.id("loginbutton")).click();	  
	}

	public void administration(Sheet sheet, int rowIndex, WebDriver driver) throws BiffException, IOException, InterruptedException {	
	    //driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();		
		StudentLogin(sheet, rowIndex, driver);
		
        if (driver.getTitle().equals("Etazo Bubble Sheet")){  
		       //Multiple choice questions...
				for(int i=3, question=4; i<8; i++, question++){
			        String sValue =sheet.getCell(i, rowIndex).getContents();
					//System.out.println("Index: " +question+ " Answer Selection: " +sValue);
			        String[] answers = sValue.split(",");
					for (int j=0; j< answers.length; j++){
			        	if(!answers[j].isEmpty()){
			        		driver.findElement(By.xpath("//*[@id='question-"+question+"']/bubbles/circle["+answers[j].trim()+"]")).click();
			        	}
			        }
			    }
				
				// True or false
				for (int i = 8, question=9; i < 10; i++, question++) {
					String sValue = sheet.getCell(i, rowIndex).getContents().trim();
					//System.out.println("Index: " + question + " Answer Selection: " + sValue);
					if(!sValue.isEmpty()){
						driver.findElement(By.xpath("//mc[@id='question-"+question+"']/bubbles/circle[text()='"+sValue+"']")).click();
					}
				}
			
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
            String Score =sheet.getCell(10, rowIndex).getContents();
            System.out.println("Score from excel sheet: "+Score);    
            Assert.assertEquals(Score, Expected);
            System.out.println("Administration completed successfully for " +Expected+ ": Passed");
            Log.info("Administration completed successfully for " +Expected+ " : Passed");
	    } catch (Error e) {
            e.getMessage();
            System.out.println("Administration not completed successfully for " +Expected+ ": Failed");
            Log.info("Administration not completed successfully for " +Expected+ ": Failed");
	    }
	    driver.findElement(By.id("logoutbutton")).click();
        }
		
		//First Teacher administartion...
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[3]")).click();	
		WritingLogin(sheet, rowIndex, driver);
		String StudentID = sheet.getCell(2, rowIndex).getContents();
		driver.findElement(By.id("studentid")).sendKeys(StudentID);
		
		 //Essay type questions...
		for(int i=13, question=1; i<16; i++, question++){
	        String sValue =sheet.getCell(i, rowIndex).getContents();
			//System.out.println("Index: " +question+ " Answer Selection: " +sValue);
	        String[] answers = sValue.split(",");
			for (int j=0; j< answers.length; j++){
	        	if(!answers[j].isEmpty()){
	        	    //driver.findElement(By.xpath("//question[1]/choice[1]/label")).click();
	        	    //driver.findElement(By.xpath("(//label[@id='NG'])[1]")).click();
	        	    driver.findElement(By.xpath("//question["+question+"]/choice["+answers[j].trim()+"]/label")).click();
	        	    Thread.sleep(2000);
	        	}
	        }
	    }
		
        driver.findElement(By.id("submit")).click();	
	    Thread.sleep(2000);
	    driver.findElement(By.id("logoutbutton")).click();
	    
	    //Second Teacher administartion..
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[3]")).click();	
	    WritingLogin1(sheet, rowIndex, driver);
		StudentID = sheet.getCell(2, rowIndex).getContents();
		driver.findElement(By.id("studentid")).sendKeys(StudentID);
		
		 //Essay type questions...
		for(int i=18, question=1; i<21; i++, question++){
	        String sValue =sheet.getCell(i, rowIndex).getContents();
			//System.out.println("Index: " +question+ " Answer Selection: " +sValue);
	        String[] answers = sValue.split(",");
			for (int j=0; j< answers.length; j++){
	        	if(!answers[j].isEmpty()){
	        	    //driver.findElement(By.xpath("//question[1]/choice[1]/label")).click();
	        	    //driver.findElement(By.xpath("(//label[@id='NG'])[1]")).click();
	        	    driver.findElement(By.xpath("//question["+question+"]/choice["+answers[j].trim()+"]/label")).click();
	        	    Thread.sleep(2000);
	        	}
	        }
	    }
        driver.findElement(By.id("submit")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("logoutbutton")).click();
	    
	    //Third Teacher administartion..
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[3]")).click();	
	    WritingLogin2(sheet, rowIndex, driver);
		StudentID = sheet.getCell(2, rowIndex).getContents();
		driver.findElement(By.id("studentid")).sendKeys(StudentID);
		
		 //Essay type questions...
		for(int i=23, question=2; i<25; i++, question++){
	        String sValue =sheet.getCell(i, rowIndex).getContents();
			//System.out.println("Index: " +question+ " Answer Selection: " +sValue);
	        String[] answers = sValue.split(",");
			for (int j=0; j< answers.length; j++){
	        	if(!answers[j].isEmpty()){
	        	    //driver.findElement(By.xpath("//question[1]/choice[1]/label")).click();
	        	    //driver.findElement(By.xpath("(//label[@id='NG'])[1]")).click();
	        	    driver.findElement(By.xpath("//question["+question+"]/choice["+answers[j].trim()+"]/label")).click();
	        	    Thread.sleep(3000);
	        	}
	        }
	    }		
        driver.findElement(By.id("submit")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("logoutbutton")).click();
	    
	       //Fourth Teacher administartion..
	  		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[3]")).click();	
	  	    WritingLogin3(sheet, rowIndex, driver);
	  		StudentID = sheet.getCell(2, rowIndex).getContents();
	  		driver.findElement(By.id("studentid")).sendKeys(StudentID);
	  		
	  		 //Essay type questions...
	  		for(int i=27, question=3; i<28; i++, question++){
	  	        String sValue =sheet.getCell(i, rowIndex).getContents();
	  		//	System.out.println("Index: " +question+ " Answer Selection: " +sValue);
	  	        String[] answers = sValue.split(",");
	  			for (int j=0; j< answers.length; j++){
	  	        	if(!answers[j].isEmpty()){
	  	        	    //driver.findElement(By.xpath("//question[1]/choice[1]/label")).click();
	  	        	    //driver.findElement(By.xpath("(//label[@id='NG'])[1]")).click();
	  	        	    driver.findElement(By.xpath("//question["+question+"]/choice["+answers[j].trim()+"]/label")).click();
	  	        	    Thread.sleep(3000);
	  	        	}
	  	        }
	  	    } 		
	        driver.findElement(By.id("submit")).click();	
      	    Thread.sleep(2000);
	  	    driver.findElement(By.id("logoutbutton")).click();
	}
	
	 @AfterClass 
	  public void afterTest() {
			driver.quit();
	  }
}
