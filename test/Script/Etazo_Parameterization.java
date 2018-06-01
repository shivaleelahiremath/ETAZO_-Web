package Script;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Etazo_Parameterization {
WebDriver login;
	
	@BeforeClass
	public void setUp(){
		//driver = new ChromeDriver();
		login = new FirefoxDriver();
		login.get("http://etazo.tangosoftware.com");
		System.out.println("select the etazo web link..");
	}
	

	@DataProvider
	public Object[][] getData() throws Exception{	 
		Object[][] data = new Object[2][3];
		
		data[0][0] = "161970";
		data[0][1] = "246D3D0";
		data[0][2] = "81203";
		
	    data[1][0] = "161970";
		data[1][1] = "246D3D0";
		data[1][2] = "80931";
		System.out.println("getData function executed!!");
		return data;
	}
	
	@Test(dataProvider="getData")
	public void Login(String distID, String asmtId, String studID) throws InterruptedException{
		
		System.out.println("Values received are :"+distID+" "+asmtId+" "+studID);
	    login.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
	    login.findElement(By.id("districtID")).sendKeys(distID);
	    login.findElement(By.id("assessmentID")).sendKeys(asmtId);
	    login.findElement(By.id("studentID")).sendKeys(studID);
	    login.findElement(By.id("loginbutton")).click();
	    Thread.sleep(2000); 
	    System.out.println("Login function executed!!");
	    Administartion();
	}
	
	
	public void Administartion() throws InterruptedException{
	System.out.println(login.getTitle());
		
	  /*//login.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
	     
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
