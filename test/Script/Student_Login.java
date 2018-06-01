package Script;

import java.io.File;

//import jxl.Cell;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.Test;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;


public class Student_Login {
	
	WebDriver login;
	
	@BeforeClass
	public void setUp(){
		login = new FirefoxDriver();
		login.get("http://etazo.tangosoftware.com");
		System.out.println("select the etazo web link..");
	}
	
	@DataProvider
	public Object[][] getData() throws Exception{	
		Object[][] retObjArr= getTableArray("test/Resources/Data/Student_info.xls","Sheet1");
		System.out.println("getData function executed!!");
		return retObjArr;	
	}
	
	private String[][] getTableArray(String xlPath, String shtName) throws Exception{
		String tabArray[][]=null;
	    Workbook workbk = Workbook.getWorkbook(new File(xlPath));
	    try{
	    Sheet sht = workbk.getSheet(shtName);
	    int eRow, eCol, sRow = 0, sCol = 0;
	    eRow = sht.getRows();
	    eCol = sht.getColumns();
	    tabArray=new String[eRow-1][eCol];
	    int ci=0;
	    for (int i=sRow+1;i<eRow;i++,ci++){
	       int cj=0;
	        for (int j=sCol;j<eCol;j++,cj++){
	        	Cell cell = sht.getCell(j, i);
	          tabArray[ci][cj]=cell.getContents();
	          System.out.println("Values from xls : "+tabArray[ci][cj]);
	        }
	      }
	    }
	    catch (Exception e){
	    	e.printStackTrace();
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
	    Thread.sleep(1000); 
	    System.out.println("Login function executed!!");
	    //login.findElement(By.id("logoutbutton")).click();
	    //System.out.println("Logout function executed!!");
	    Administartion();
	}
	
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
	    login.findElement(By.id("logoutbutton")).click();
	}

	@AfterClass
	public void tearDown(){
		login.quit();
	}
}
