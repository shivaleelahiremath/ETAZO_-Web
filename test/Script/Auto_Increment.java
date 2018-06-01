package Script;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Auto_Increment {	
	
		WebDriver login;
		String[][] tabArray=null;
		Workbook workbk;
		Sheet sht;
		int eRow, eCol, sRow = 0, sCol = 0;
		//WebDriver driver = new ChromeDriver();
	    String sheetPath = "test/Resources/Data/Auto_Increment.xls";

		@BeforeSuite
		public void setUp(){
			login = new FirefoxDriver();
			login.get("http://etazo.tangosoftware.com");
			System.out.println("select the etazo web link..");
		}
		
		//assertTrue(login.findElement(By.cssSelector("BODY")).getText().matches("FAIRLY OK Media")); 
			
		@DataProvider
		public Object[][] getLoginData() throws Exception{	
			Object[][] retObjArr= getExcelData("sheetPath","Sheet1");
			System.out.println("getData function executed!!");
			return retObjArr;	
		}

		//Excel API to read test data from excel workbook
		public String[][] getExcelData(String xlPath, String shtName) throws Exception{
		    Workbook workbk = Workbook.getWorkbook(new File(xlPath));
		    Sheet sht = workbk.getSheet(shtName);
		    eRow = sht.getRows();
		    eCol = sht.getColumns();
		    tabArray=new String[eRow-1][3]; 
		    int ci=0;
		    System.out.println("erow: "+eRow);
		    System.out.println("ecol: "+eCol);
		    for (int i=sRow+1;i<eRow;i++,ci++){
		    int cj=0;
		      for (int j=sCol;j<3;j++,cj++){
		        tabArray[ci][cj]=sht.getCell(j,i).getContents();
		        System.out.println("Values from xls : "+tabArray[ci][cj]);
		      }
		    }
		    return(tabArray);
		  }
		
		@Test(dataProvider="getLoginData")
		public void LoginData(String distID, String asmtId, String studID) throws InterruptedException{			
			System.out.println("Values received are logindata method:"+distID+" "+asmtId+" "+studID);
		    login.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
		    login.findElement(By.id("districtID")).sendKeys(distID);
		    login.findElement(By.id("assessmentID")).sendKeys(asmtId);
		    login.findElement(By.id("studentID")).sendKeys(studID);
		    login.findElement(By.id("loginbutton")).click();
		    Thread.sleep(2000); 
		    System.out.println("Login function executed!!");
		    //login.findElement(By.id("logoutbutton")).click();
		    //System.out.println("Logout function executed!!");
		    Administartion();
		}
		
		public void Administartion() throws InterruptedException{
	    System.out.println(login.getTitle());
	    tabArray=new String[4][5];
	    int ci=0;
	    for (int i=sRow+1;i<eRow;i++,ci++){
	        int cj=0;
	        for(int j=sCol+4;j<eCol;j++,cj++){
	            tabArray[ci][cj]=sht.getCell(j,i).getContents();
	            System.out.println("Values index and answer selection : "+tabArray[ci][cj]);
	            String sIndex = tabArray[ci][cj];
	            int iIndex = (int)Integer.parseInt(sIndex);
	            login.findElement(By.xpath("//*[@id='question-" + iIndex + "]/bubbles/circle[" + iIndex + "]")).click();
	            System.out.println("Values from xls : "+iIndex);
	            }
	        }
	    
	    //Administration..
	    //int index=1, value=3;
	    // String xpath_Start="//*[@id='question-";
		//String xpath_Midd="']/bubbles/circle[";
		//String xpath_End="]";	
		//login.findElement(By.xpath("//*[@id='question-" + index + "']/bubbles/circle[" + value + "]")).click();
	
		/*  login.findElement(By.xpath("//*[@id='question-1']/bubbles/circle[1]")).click();	
		    login.findElement(By.xpath("//*[@id='question-2']/bubbles/circle[2]")).click();	
		    login.findElement(By.xpath("//*[@id='question-3']/bubbles/circle[3]")).click();	
		    login.findElement(By.xpath("//*[@id='question-4']/bubbles/circle[1]")).click();	
		    login.findElement(By.xpath("//*[@id='question-5']/bubbles/circle[2]")).click();	
		    login.findElement(By.xpath("//*[@id='question-6']/bubbles/circle[3]")).click();	
		    login.findElement(By.xpath("//*[@id='question-7']/bubbles/circle[1]")).click();	
		    login.findElement(By.xpath("//*[@id='question-8']/bubbles/circle[2]")).click();	
		    login.findElement(By.xpath("//*[@id='question-9']/bubbles/circle[3]")).click();	
		    login.findElement(By.xpath("//*[@id='question-10']/bubbles/circle[1]")).click();
		    login.findElement(By.id("submitbutton")).click();	
		    Thread.sleep(3000);
		    login.findElement(By.xpath("html/body/div[3]/div[3]/div/button[1]")).click();
		    Thread.sleep(3000);  
		    login.findElement(By.xpath("//*[@id='confirm']/text")).click();
		    login.findElement(By.xpath("html/body/div[3]/div[3]/div/button")).click();*/
		String Expected;
	    Expected = login.findElement(By.xpath("//*[@id='Score']/text")).getText();
	    System.out.println("Score: " +Expected);
	    //*[@id='Score']/text/score
	    //*[@id='Score']/text
	    try{
	    Assert.assertEquals("Score: 100", Expected);  
	    } catch (Error e) { 
	    e.getMessage();
	    } 
		login.findElement(By.id("logoutbutton")).click();
		}

		@AfterClass
		public void tearDown(){
			login.quit();
		}
		
	}




