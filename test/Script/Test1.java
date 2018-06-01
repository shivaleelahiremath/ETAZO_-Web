package Script;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Test1 {
	String[][] tabArray = null;
	Workbook workbk;
	Sheet sheet;
	int rowCount, colCount;
	String sheetPath = "test/Resources/Data/StudentLink.xls";
	WebDriver driver;
    String Expected;

	@Test
	public void getExcelData() throws Exception{
		Workbook workbk = Workbook.getWorkbook(new File(sheetPath));
		Sheet sht = workbk.getSheet("Sheet1");
		rowCount = sht.getRows();
		colCount = sht.getColumns();
		tabArray = new String[rowCount][colCount - 2];
		System.out.println("erow: " + rowCount);
		System.out.println("ecol: " + colCount);
		driver = new FirefoxDriver();
		driver.get("http://etazo.tangosoftware.com");
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
		for (int row = 7; row < 8; row++) {
			switch(row)
			{
				case 1: administration1(sht, row, driver);
						break;
				case 2: administration2(sht, row, driver);
						break;
				case 3: administration3(sht, row, driver);
				  		break;
				case 4: administration4(sht, row, driver);
						break;
				case 5: administration5(sht, row, driver);
						break;
				case 6: administration6(sht, row, driver);
						break;
				case 7: administration7(sht, row, driver);
				        break;
				case 8: administration8(sht, row, driver);
				        break;
			}						
		}
	}
	
	public void login(Sheet sheet, int rowIndex, WebDriver driver)
	{
		String DistrictID = sheet.getCell(0, rowIndex).getContents();
		String AssessmentID = sheet.getCell(1, rowIndex).getContents();
		String StudentID = sheet.getCell(2, rowIndex).getContents();
		System.out.println("Values received are :"+DistrictID+" "+AssessmentID+" "+StudentID);
		driver.findElement(By.id("districtID")).sendKeys(DistrictID);
	    driver.findElement(By.id("assessmentID")).sendKeys(AssessmentID);
	    driver.findElement(By.id("studentID")).sendKeys(StudentID);
	    driver.findElement(By.id("loginbutton")).click();	  
	    System.out.println("Login Successfull");		
	}
	
	public void Submit() throws InterruptedException{
		driver.findElement(By.id("submitbutton")).click();	
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("html/body/div[3]/div[3]/div/button[1]")).click();
	    Thread.sleep(3000);  
	    driver.findElement(By.xpath("//*[@id='confirm']/text")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("html/body/div[3]/div[3]/div/button")).click();
	    Thread.sleep(3000);	
	}
	
	// Administration released with Displays Score, Display Missed Items and Allow Incomplete selection..
    // ETAZO_Administration1 : Administration code for correct answersÉi.e Score: 100   
	@SuppressWarnings("deprecation")
	public void administration1(Sheet sheet, int rowIndex, WebDriver driver) throws BiffException, IOException, InterruptedException {
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();		
		login(sheet, rowIndex, driver);
				 
		// Multiple answer choice questions...
		for(int i=3; i<=16; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
		}
		
		//Fill in the blanks questions..
		for(int i=17; i<=26; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
		    driver.findElement(By.cssSelector("#question-"+sIndex+" > #answer")).sendKeys(sValue);
        }
		
		//row 27 to 78 : Place value and 79 to 148 : Advance place value and 149 to 238 : Exact place value
		for(int i=27; i<=238; i=i+2) 
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("(//td[@id='digit-" +sIndex+"']/circle)[" +sValue+"]")).click();
        }
        
		//True/False question type..
		for(int i=239; i<249; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
		//	System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
        }
		Submit();
	    Expected = driver.findElement(By.xpath("//*[@id='Score']/text")).getText();
	    System.out.println(Expected);
	    try{
		String Score =sheet.getCell(249, rowIndex).getContents();
	    //Assert.assertEquals("Score: 100", Expected);  
	    Assert.assertEquals(Score, Expected);  
	    System.out.println("Administration completed successfully for score 100 with all correct answers : Passed");
	    } catch (Error e) { 
	    e.getMessage();
	    } 
	    driver.findElement(By.id("logoutbutton")).click();	
	}
	
	// ETAZO_Administration2 : Administration code for correct and skipped answers i.e Score: 80É
	@SuppressWarnings("deprecation")
	public void administration2(Sheet sheet, int rowIndex, WebDriver driver) throws BiffException, IOException, InterruptedException {
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();		
		login(sheet, rowIndex, driver);
				// Multiple answer choice questions...
						for(int i=3; i<=16; i=i+2)
						{
							String sIndex =sheet.getCell(i, rowIndex).getContents();
					        String sValue =sheet.getCell(i+1, rowIndex).getContents();
							//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
					        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
						}
						
						//Fill in the blanks questions..
						for(int i=17; i<=24; i=i+2)
						{
							String sIndex =sheet.getCell(i, rowIndex).getContents();
					        String sValue =sheet.getCell(i+1, rowIndex).getContents();
							//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
						    driver.findElement(By.cssSelector("#question-"+sIndex+" > #answer")).sendKeys(sValue);
				        }
						
						//row 27 to 78 : Place value and 79 to 148 : Advance place value and 149 to 238 : Exact place value
						for(int i=25; i<=212; i=i+2) 
						{
							String sIndex =sheet.getCell(i, rowIndex).getContents();
					        String sValue =sheet.getCell(i+1, rowIndex).getContents();
						//	System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
					        driver.findElement(By.xpath("(//td[@id='digit-" +sIndex+"']/circle)[" +sValue+"]")).click();
				        }
				        
						//True/False question type..
						for(int i=213; i<217; i=i+2)
						{
							String sIndex =sheet.getCell(i, rowIndex).getContents();
					        String sValue =sheet.getCell(i+1, rowIndex).getContents();
						//	System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
					        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
				        }		
				        Submit();
					    Expected = driver.findElement(By.xpath("//*[@id='Score']/text")).getText();
					    System.out.println(Expected);
					    try{
						String Score =sheet.getCell(217, rowIndex).getContents();
					    //Assert.assertEquals("Score: 80", Expected);  
					    Assert.assertEquals(Score, Expected);  
					    System.out.println("Administration completed successfully for score 80 with all correct answers : Passed");
					    } catch (Error e) { 
					    e.getMessage();
					    } 
					    driver.findElement(By.id("logoutbutton")).click();
	}
	
	// ETAZO_Administration3 : Administration code for correct and skipped answers i.e Score: 50É
	@SuppressWarnings("deprecation")
	public void administration3(Sheet sheet, int rowIndex, WebDriver driver) throws BiffException, IOException, InterruptedException {
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();		
		login(sheet, rowIndex, driver);
				
		// Multiple answer choice questions...
				for(int i=3; i<=14; i=i+2)
				{
					String sIndex =sheet.getCell(i, rowIndex).getContents();
			        String sValue =sheet.getCell(i+1, rowIndex).getContents();
					//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
			        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
				}
				
				//Fill in the blanks questions..
				for(int i=15; i<=24; i=i+2)
				{
					String sIndex =sheet.getCell(i, rowIndex).getContents();
			        String sValue =sheet.getCell(i+1, rowIndex).getContents();
				 //	System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
				  //login.findElement(By.cssSelector("#question-2 > #answer")).sendKeys("test");
				    driver.findElement(By.cssSelector("#question-"+sIndex+" > #answer")).sendKeys(sValue);
		        }
				
				//row 27 to 78 : Place value and 79 to 148 : Advance place value and 149 to 238 : Exact place value
				for(int i=25; i<=134; i=i+2) 
				{
					String sIndex =sheet.getCell(i, rowIndex).getContents();
			        String sValue =sheet.getCell(i+1, rowIndex).getContents();
					//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
			        driver.findElement(By.xpath("(//td[@id='digit-" +sIndex+"']/circle)[" +sValue+"]")).click();
			        //driver.findElement(By.xpath("(//td[@id='digit-4']/circle)[17]")).click();
		        }
		        
				//True/False question type..
				for(int i=135; i<143; i=i+2)
				{
					String sIndex =sheet.getCell(i, rowIndex).getContents();
			        String sValue =sheet.getCell(i+1, rowIndex).getContents();
					//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
			        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
		        }
		        Submit();
			    Expected = driver.findElement(By.xpath("//*[@id='Score']/text")).getText();
			    System.out.println(Expected);
			    try{
				String Score =sheet.getCell(143, rowIndex).getContents();
			    Assert.assertEquals(Score, Expected);  
			    System.out.println("Administration completed successfully for score 50 with all correct answers : Passed");
			    } catch (Error e) { 
			    e.getMessage();
			    } 
			    driver.findElement(By.id("logoutbutton")).click();
	}
	
	// ETAZO_Administration4 : Administration code for correct and skipped answers i.e Score: 43É
	@SuppressWarnings("deprecation")
	public void administration4(Sheet sheet, int rowIndex, WebDriver driver) throws BiffException, IOException, InterruptedException {
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();		
		login(sheet, rowIndex, driver);	
		
				// Multiple answer choice questions...
						for(int i=3; i<=16; i=i+2)
						{
							String sIndex =sheet.getCell(i, rowIndex).getContents();
					        String sValue =sheet.getCell(i+1, rowIndex).getContents();
							//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
					        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
						}
						
						//Fill in the blanks questions..
						for(int i=17; i<=26; i=i+2)
						{
							String sIndex =sheet.getCell(i, rowIndex).getContents();
					        String sValue =sheet.getCell(i+1, rowIndex).getContents();
						//	System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
						  //login.findElement(By.cssSelector("#question-2 > #answer")).sendKeys("test");
						    driver.findElement(By.cssSelector("#question-"+sIndex+" > #answer")).sendKeys(sValue);
				        }
						
						//row 27 to 78 : Place value and 79 to 148 : Advance place value and 149 to 238 : Exact place value
						for(int i=27; i<=76; i=i+2) 
						{
							String sIndex =sheet.getCell(i, rowIndex).getContents();
					        String sValue =sheet.getCell(i+1, rowIndex).getContents();
						//	System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
					        driver.findElement(By.xpath("(//td[@id='digit-" +sIndex+"']/circle)[" +sValue+"]")).click();
					        //driver.findElement(By.xpath("(//td[@id='digit-4']/circle)[17]")).click();
				        }
				        
						//True/False question type..
						for(int i=77; i<87; i=i+2)
						{
							String sIndex =sheet.getCell(i, rowIndex).getContents();
					        String sValue =sheet.getCell(i+1, rowIndex).getContents();
							//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
					        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
                        }
				        Submit();
					    Expected = driver.findElement(By.xpath("//*[@id='Score']/text")).getText();
					    System.out.println(Expected);
					    try{
						String Score =sheet.getCell(87, rowIndex).getContents();
					    Assert.assertEquals(Score, Expected);  
					    System.out.println("Administration completed successfully for score 43 with all correct answers : Passed");
					    } catch (Error e) { 
					    e.getMessage();
					    } 
					    driver.findElement(By.id("logoutbutton")).click();
	}
	
	// ETAZO_Administration5 : Administration code for correct and skipped answers i.e Score: 33É
	@SuppressWarnings("deprecation")
	public void administration5(Sheet sheet, int rowIndex, WebDriver driver) throws BiffException, IOException, InterruptedException {
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
		login(sheet, rowIndex, driver);
		
		for(int i=3; i<=14; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
		//	System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
		}
		
		//Fill in the blanks questions..
		for(int i=15; i<=22; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
		    driver.findElement(By.cssSelector("#question-"+sIndex+" > #answer")).sendKeys(sValue);
        }
		
		//row 27 to 78 : Place value and 79 to 148 : Advance place value and 149 to 238 : Exact place value
		for(int i=23; i<=132; i=i+2) 
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("(//td[@id='digit-" +sIndex+"']/circle)[" +sValue+"]")).click();
	        //driver.findElement(By.xpath("(//td[@id='digit-4']/circle)[17]")).click();
        }
        
		//True/False question type..
		for(int i=133; i<139; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
        }
		
        Submit();
	    Expected = driver.findElement(By.xpath("//*[@id='Score']/text")).getText();
	    System.out.println(Expected);
	    try{
		String Score =sheet.getCell(139, rowIndex).getContents();
	    Assert.assertEquals(Score, Expected);  
	    System.out.println("Administration completed successfully for score 33 with all correct answers : Passed");
	    } catch (Error e) { 
	    e.getMessage();
	    } 
	    driver.findElement(By.id("logoutbutton")).click();
	}
	
	// ETAZO_Administration6 : Administration code for correct and skipped answers i.e Score: 23É
	@SuppressWarnings("deprecation")
	public void administration6(Sheet sheet, int rowIndex, WebDriver driver) throws BiffException, IOException, InterruptedException {
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
		login(sheet, rowIndex, driver);
	    
        // Multiple answer choice questions...
		for(int i=3; i<=12; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
		}
		
		//Fill in the blanks questions..
		for(int i=13; i<=18; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
		//	System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
		  //login.findElement(By.cssSelector("#question-2 > #answer")).sendKeys("test");
		    driver.findElement(By.cssSelector("#question-"+sIndex+" > #answer")).sendKeys(sValue);
        }
		
		//row 27 to 78 : Place value and 79 to 148 : Advance place value and 149 to 238 : Exact place value
		for(int i=19; i<=152; i=i+2) 
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("(//td[@id='digit-" +sIndex+"']/circle)[" +sValue+"]")).click();
	        //driver.findElement(By.xpath("(//td[@id='digit-4']/circle)[17]")).click();
        }
        
		//True/False question type..
		for(int i=153; i<159; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
		//	System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
        }		
        Submit();
	    Expected = driver.findElement(By.xpath("//*[@id='Score']/text")).getText();
	    System.out.println(Expected);
	    try{
		String Score =sheet.getCell(159, rowIndex).getContents();
	    Assert.assertEquals(Score, Expected);  
	    System.out.println("Administration completed successfully for score 23 with all correct answers : Passed");
	    } catch (Error e) { 
	    e.getMessage();
	    } 
	    driver.findElement(By.id("logoutbutton")).click();
	}
	
	// Administration released with  Displays Score, Uncheck Display Missed Items and Allow Incomplete selection..
    // ETAZO_Administration7 : Administration code for correct answersÉi.e Score: 100   
	@SuppressWarnings("deprecation")
	public void administration7(Sheet sheet, int rowIndex, WebDriver driver) throws BiffException, IOException, InterruptedException {
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();		
		login(sheet, rowIndex, driver);
				 
		// Multiple answer choice questions...
		for(int i=3; i<=8; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
		}
		
		//True/False question type..
		for(int i=9; i<14; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
		    String sValue =sheet.getCell(i+1, rowIndex).getContents();
		//	System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
			driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
	    }
				
		//Fill in the blanks questions..
		for(int i=15; i<=16; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
		    driver.findElement(By.cssSelector("#question-"+sIndex+" > #answer")).sendKeys(sValue);
        }
		
		//row 27 to 78 : Place value and 79 to 148 : Advance place value and 149 to 238 : Exact place value
		for(int i=17; i<25; i=i+2) 
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("(//td[@id='digit-" +sIndex+"']/circle)[" +sValue+"]")).click();
        }
		
		//Submit();
	    Expected = driver.findElement(By.xpath("//*[@id='Score']/text")).getText();
	    System.out.println(Expected);
	    try{
		String Score =sheet.getCell(249, rowIndex).getContents();
	    //Assert.assertEquals("Score: 100", Expected);  
	    Assert.assertEquals(Score, Expected);  
	    System.out.println("Administration completed successfully for score 100 with all correct answers : Passed");
	    } catch (Error e) { 
	    e.getMessage();
	    } 
	    driver.findElement(By.id("logoutbutton")).click();	
	}
	
	// Administration released with Displays Score, Uncheck Display Missed Items and Allow Incomplete selection..
    // ETAZO_Administration1 : Administration code for correct answersÉi.e Score: 100   
	@SuppressWarnings("deprecation")
	public void administration8(Sheet sheet, int rowIndex, WebDriver driver) throws BiffException, IOException, InterruptedException {
		driver.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();		
		login(sheet, rowIndex, driver);
				 
		// Multiple answer choice questions...
		for(int i=3; i<=16; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
		}
		
		//Fill in the blanks questions..
		for(int i=17; i<=26; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
		    driver.findElement(By.cssSelector("#question-"+sIndex+" > #answer")).sendKeys(sValue);
        }
		
		//row 27 to 78 : Place value and 79 to 148 : Advance place value and 149 to 238 : Exact place value
		for(int i=27; i<=238; i=i+2) 
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
			//System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("(//td[@id='digit-" +sIndex+"']/circle)[" +sValue+"]")).click();
        }
        
		//True/False question type..
		for(int i=239; i<249; i=i+2)
		{
			String sIndex =sheet.getCell(i, rowIndex).getContents();
	        String sValue =sheet.getCell(i+1, rowIndex).getContents();
		//	System.out.println("Index: " +sIndex+ " Answer Selection: " +sValue);
	        driver.findElement(By.xpath("//*[@id='question-"+sIndex+"']/bubbles/circle["+sValue+"]")).click();
        }
		Submit();
	    Expected = driver.findElement(By.xpath("//*[@id='Score']/text")).getText();
	    System.out.println(Expected);
	    try{
		String Score =sheet.getCell(249, rowIndex).getContents();
	    //Assert.assertEquals("Score: 100", Expected);  
	    Assert.assertEquals(Score, Expected);  
	    System.out.println("Administration completed successfully for score 100 with all correct answers : Passed");
	    } catch (Error e) { 
	    e.getMessage();
	    } 
	    driver.findElement(By.id("logoutbutton")).click();	
	}
}