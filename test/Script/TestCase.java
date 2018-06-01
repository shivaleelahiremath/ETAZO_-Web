package Script;
import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCase {

    String[][] tabArray = null;
    Workbook workbk;
    Sheet sheet;
    int rowCount, colCount;
    String sheetPath = "test/Resources/Data/Auto_Increment.xls";
	WebDriver login;
	//int eRow, eCol, sRow = 0, sCol = 0;


    @BeforeSuite
	public void setUp(){
		login = new FirefoxDriver();
		login.get("http://etazo.tangosoftware.com");
		System.out.println("select the etazo web link..");
	}

    @DataProvider
    public Object[][] getLoginData() throws Exception {
        Object[][] retObjArr = getExcelData(sheetPath, "Sheet1");
        System.out.println("getData function executed!!");
        return retObjArr;
    }

 // Excel API to read test data from excel workbook
    public String[][] getExcelData(String xlPath, String shtName)
            throws Exception {
        Workbook workbk = Workbook.getWorkbook(new File(xlPath));
        Sheet sht = workbk.getSheet(shtName);
        rowCount = sht.getRows();
        colCount = sht.getColumns();
        tabArray = new String[rowCount][colCount - 2];
        System.out.println("erow: " + rowCount);
        System.out.println("ecol: " + colCount);
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < 3; j++) {
                tabArray[i][j] = sht.getCell(j, i).getContents();
            }
        }
        return (tabArray);
    }
    
    @Test(dataProvider = "getLoginData")
    public void LoginData(String distID, String asmtId, String studID)
            throws InterruptedException, BiffException, IOException {
      /*
		System.out.println("Values received are login data method:"+distID+" "+asmtId+" "+studID);
		login.findElement(By.xpath("html/body/main/leftpanel/mmenu/menuitem[1]")).click();
	    login.findElement(By.id("districtID")).sendKeys(distID);
	    login.findElement(By.id("assessmentID")).sendKeys(asmtId);
	    login.findElement(By.id("studentID")).sendKeys(studID);
	    login.findElement(By.id("loginbutton")).click();
	    Thread.sleep(2000); 
	    System.out.println("Login function executed!!");*/
        Administartion(distID, asmtId, studID);
    }

    public void Administartion(String distID, String asmtId, String studID)
            throws BiffException, IOException {
        Workbook workbk = Workbook.getWorkbook(new File(sheetPath));
        Sheet sht = workbk.getSheet("Sheet1");
        int currRow = sht.findCell(studID).getRow();
        String sIndex =sht.getCell(3, currRow).getContents();
        String sValue =sht.getCell(4, currRow).getContents();
        int iIndex = (int) Integer.parseInt(sIndex);
		int iValue = (int) Integer.parseInt(sValue);
        System.out.println(sht.getCell(3, currRow).getContents() + " Index ");
        System.out.println(sht.getCell(4, currRow).getContents() + " Answer selection");
        login.findElement(By.xpath("//*[@id='question-"+iIndex+"']/bubbles/circle["+iValue+"]")).click();     

    }

}
