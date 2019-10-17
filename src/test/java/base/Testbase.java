package base;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testbase {

	public static  WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static String browser;
    public static Actions actions;
    public static int Optionqty=0;
    public static  Robot robot;
    String nodeURL;
    
    protected static DBUtil iqdb = new DBUtil();
	
   
	@BeforeTest(alwaysRun = true)
	@Parameters({ "propertyfile" ,"portNO"})
	public void setUp(@Optional("null") String propertyfile,@Optional("null") String portNO) throws IOException, AWTException {
		
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\"+propertyfile+".properties");
			Config.load(fis);
			
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			
			if(portNO!=null && Config.getProperty("browser").equals("chrome"))
			{
				nodeURL = "http://danieljio-w10lt:4547/wd/hub";
				System.out.println("Chrome Browser Test Environment created");
				DesiredCapabilities cap1 = DesiredCapabilities.chrome();			
				cap1.setBrowserName("chrome");
				//cap1.setPlatform(Platform.WINDOWS);
				
				driver = new RemoteWebDriver(new URL(nodeURL),cap1);			
				driver.manage().window().maximize();
				driver.navigate().to(Config.getProperty("testsiteurl"));
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				wait = new WebDriverWait(driver, 300);
			}
			else if(portNO==null && Config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				
				driver = new ChromeDriver();
				driver.get(Config.getProperty("testsiteurl"));
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
						TimeUnit.SECONDS);
				
				wait = new WebDriverWait(driver, 300);
			}
		    robot = new Robot();
	        iqdb.Createconnection(Config.getProperty("DBUrl"), Config.getProperty("DBUsername"), Config.getProperty("DBPassWord"));
	        BasicConfigurator.configure();
	       
	  //  }	

	}
	protected static void saveProperties(Properties p,String val) throws IOException
    {
        FileOutputStream fr = new FileOutputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\"+val+".properties");
        p.store(fr, "Properties");
        fr.close();
        System.out.println("After saving properties: " + p);
    }
	public static synchronized WebDriver getdriver() {
		return driver;
	}
	
	@AfterTest
	public void tearDown() throws SQLException {
		if(driver!=null) {
			driver.quit();
			
		}
		 iqdb.Closeconnection();
		
		
	}
	

}