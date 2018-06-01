package Script;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstTest {
	
	WebDriver login;
	@BeforeClass
	public void setUp(){
		login = new FirefoxDriver();
		login.get("http://etazo.tangosoftware.com");
		System.out.println("select the etazo web link..");
		//throw new SkipException("Skip this class");
	}

	@DataProvider
	public Object[][] getData() throws Exception{	 
		
		Object[][] data = new Object[1][4];
		data[0][0] = "161970";
		data[0][1] = "3616284";
		data[0][2] = "cantubelsa@TXRioGrandeCityCISDTest05.com";
		data[0][3] = "tango123";
		System.out.println("getData function executed!!");
		return data;
	}
	
	@Test(dataProvider="getData")
	public void Login(String distID, String asmtId, String Email, String pwd) throws InterruptedException{
		
		System.out.println("Values received are :"+distID+" "+asmtId+" "+Email+" "+pwd);
	    login.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[2]")).click();
	    login.findElement(By.id("districtID")).sendKeys(distID);
	    login.findElement(By.id("assessmentID")).sendKeys(asmtId);
	    login.findElement(By.id("loginEmail")).sendKeys(Email);
	    login.findElement(By.id("password")).sendKeys(pwd);
	    login.findElement(By.id("loginbutton")).click();
	    Thread.sleep(2000); 
	    login.findElement(By.id("studentID")).sendKeys("80957");
	    login.findElement(By.id("loginbutton")).click();
	    System.out.println("Login function executed!!");
	    Administartion();
	}
	
	//steps to generate xslt reports using testng in mac

	public void Administartion() throws InterruptedException{
	System.out.println(login.getTitle());
		
	  /*//login.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();   
	    login.findElement(By.id("districtID")).sendKeys("161970");
	    login.findElement(By.id("assessmentID")).sendKeys("246D3D0");
	    login.findElement(By.id("studentID")).sendKeys("80803");
	    login.findElement(By.id("loginbutton")).click();
	    Thread.sleep(2000); */
	
	/*click("xpath=//input[1][@id='button' and @value='Edit']")
	.click("//input[@id='button' and @value='Edit'][1]");
	String cssSelectorOfSameElements="input[type='submit'][id='button']";
    */
	    System.out.println("entered into admin fun..");
	    login.findElement(By.xpath("//*[@id='question-1']/bubbles/circle[2]")).click();	
	    login.findElement(By.cssSelector("#question-2 > #answer")).sendKeys("test");
        login.findElement(By.cssSelector("#question-3 > #answer")).sendKeys("etazo");
        login.findElement(By.xpath("(//td[@id='digit-1']/circle)[1]")).click();
        login.findElement(By.xpath("(//td[@id='digit-2']/circle)[4]")).click();
        login.findElement(By.xpath("(//td[@id='digit-3']/circle)[5]")).click();
        login.findElement(By.xpath("(//td[@id='digit-4']/circle)[1]")).click();
        login.findElement(By.xpath("(//td[@id='digit-5']/circle)[7]")).click();
        login.findElement(By.xpath("(//td[@id='digit-6']/circle)[2]")).click();
        login.findElement(By.xpath("(//td[@id='digit-7']/circle)[11]")).click();
        login.findElement(By.xpath("(//td[@id='digit-1']/circle)[4]")).click();
        login.findElement(By.xpath("(//td[@id='digit-2']/circle)[13]")).click();
        login.findElement(By.xpath("(//td[@id='digit-3']/circle)[12]")).click();
        login.findElement(By.xpath("(//td[@id='digit-4']/circle)[17]")).click();
        login.findElement(By.xpath("(//td[@id='digit-5']/circle)[18]")).click();
        login.findElement(By.xpath("(//td[@id='digit-6']/circle)[16]")).click();
        login.findElement(By.xpath("//*[@id='question-6']/bubbles/circle[1]")).click();
        login.findElement(By.xpath("//*[@id='question-7']/bubbles/circle[2]")).click();
        login.findElement(By.xpath("(//td[@id='digit-1']/circle)[7]")).click();
        login.findElement(By.xpath("(//td[@id='digit-2']/circle)[23]")).click();
        login.findElement(By.xpath("(//td[@id='digit-3']/circle)[24]")).click();
        login.findElement(By.xpath("(//td[@id='digit-4']/circle)[27]")).click();
        login.findElement(By.xpath("(//td[@id='digit-1']/circle)[17]")).click();
        login.findElement(By.xpath("(//td[@id='digit-2']/circle)[42]")).click();       
	    login.findElement(By.xpath("//*[@id='question-10']/bubbles/circle[3]")).click();	
	    login.findElement(By.id("submitbutton")).click();	
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

	

