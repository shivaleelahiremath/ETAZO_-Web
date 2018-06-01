package Script;

//import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class SecondTest {
	
	@BeforeTest
	public void test(){
		System.out.println("It will execute befor executing every tests");
		//throw new SkipException("Skip this class");
	}
	
	@Test
	public void OpenBrowser(){
		System.out.println("Opening browser");
	}
	
	@Test
	public void CloseBrowser(){
		System.out.println("closing browser");
	}
	
}
