package Script;




import java.io.File;

import junit.framework.Assert;
import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.commons.logging.Log;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.BeforeClass;

public class Etazo_Login {
	
	WebDriver login;
	
	@BeforeSuite
	public void setUp(){
		login = new FirefoxDriver();
		login.get("http://etazo.tangosoftware.com");

		System.out.println("select the etazo web link..");
	}
	
	@DataProvider
	public Object[][] getData() throws Exception{	
		Object[][] retObjArr= getExcelData("test/Resources/Data/Assessment_INFO.xls","Sheet1");
		System.out.println("getData function executed!!");
		return retObjArr;	
	}
	
	 //Excel API to read test data from excel workbook
	private String[][] getExcelData(String xlPath, String shtName) throws Exception{
	    String[][] tabArray=null;
	    Workbook workbk = Workbook.getWorkbook(new File(xlPath));
	    Sheet sht = workbk.getSheet(shtName);
	    int eRow, eCol, sRow = 0, sCol = 0;
	    eRow = sht.getRows();
	    eCol = sht.getColumns();
	    tabArray=new String[eRow-1][eCol]; 
	    int ci=0;
	    for (int i=sRow+1;i<eRow;i++,ci++){
	     int cj=0;
	      for (int j=sCol;j<eCol;j++,cj++){
	        tabArray[ci][cj]=sht.getCell(j,i).getContents();
	        System.out.println("Values from xls : "+tabArray[ci][cj]);

	      }
	    }
	    return(tabArray);
	  }
	
	@Test(dataProvider="getData")
	public void LoginData(String distID, String asmtId, String studID) throws InterruptedException{
		
		System.out.println("Values received are :"+distID+" "+asmtId+" "+studID);
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
	
	
	@SuppressWarnings("deprecation")
	public void Administartion() throws InterruptedException{
	System.out.println(login.getTitle());
		
	  /*  //login.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
	     
	    login.findElement(By.id("districtID")).sendKeys("161970");
	    login.findElement(By.id("assessmentID")).sendKeys("246D3D0");
	    login.findElement(By.id("studentID")).sendKeys("80803");
	    login.findElement(By.id("loginbutton")).click();
	    Thread.sleep(2000); */
	    login.findElement(By.xpath("//*[@id='question-1']/bubbles/circle[1]")).click();	
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
	    login.findElement(By.xpath("html/body/div[3]/div[3]/div/button")).click();   
	    //login.findElement(By.id("Score")).sendKeys(": 100");
	    String Expected;
	    Expected = login.findElement(By.xpath("//*[@id='Score']/text")).getText();
	    System.out.println("Score: " +Expected);
	    //*[@id='Score']/text/score
	    //*[@id='Score']/text
	    Assert.assertEquals("Score: 100", Expected);   
	    //login.findElement(By.id("logoutbutton")).click();
	}

	@AfterClass
	public void tearDown(){
		login.quit();
	}
}
