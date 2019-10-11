package base;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestResult;
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
    
    
    protected static DBUtil iqdb = new DBUtil();
	
   
	@BeforeTest(alwaysRun = true)
	@Parameters({ "propertyfile" })
	public void setUp(@Optional("null") String propertyfile) throws IOException, AWTException {
		/*ITestContext testContext = null;
		if(testContext.getCurrentXmlTest()!=null)
			System.out.printf("Running test suite %1$s%n", testContext.getCurrentXmlTest().getSuite().getName());*/
		//if (driver == null) {
			
			//System.out.println("DB Selected is : "+System.getenv("DataBase"));
			//System.out.println("Estimate ID's are : "+System.getenv("Estimates"));
			
			//for Jenkins
			/*fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\"+System.getenv("DataBase")+".properties");
			Config.load(fis);*/
			//for normal work
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\"+propertyfile+".properties");
			Config.load(fis);
			
			//uncomment this for jenkins
			//Config.setProperty("EstimateIDs", System.getenv("Estimates"));
			//saveProperties(Config,System.getenv("DataBase"));
			
			//DBUtil iqdb=new DBUtil(Config.getProperty("DBUsername"), Config.getProperty("DBPassWord"), Config.getProperty("DBUrl"));
			
			
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			if (Config.getProperty("browser").equals("chrome")) {
				
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				
				driver = new ChromeDriver();
				driver.get(Config.getProperty("testsiteurl"));
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
						TimeUnit.SECONDS);
				
				wait = new WebDriverWait(driver, 300);
			}
			/*if (Config.getProperty("browser").equals("firefox")) {
				// System.setProperty("webdriver.gecko.driver", "gecko.exe");
				driver = new FirefoxDriver();
			} else if (Config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				
				driver = new ChromeDriver();
				
			} else if (Config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}*/
			
			
			/*driver.get(Config.getProperty("testsiteurl"));
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);*/
			// wait = new WebDriverWait(driver, 180);
			//Extent
			//htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\src\\test\\resources\\runner\\MyOwnReport.html");
			htmlReporter = new ExtentHtmlReporter(
					System.getProperty("user.dir") + "\\HTMLReports\\Extent Reports\\"+Config.getProperty("EstimateIDs")+".html");
			//System.out.println(System.getProperty("user.dir") + "\\target\\surefire-reports\\MyOwnReport.html");
			
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	         
	        extent.setSystemInfo("OS", "Windows 10 Pro");
	        extent.setSystemInfo("Host Name", "Daniel Joel");
	        extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("User Name", "Daniel joel");
	         
	       // htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setDocumentTitle("IQuote Testing Report");
	        htmlReporter.config().setReportName("My Own Report");
	       // htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	        htmlReporter.config().setTheme(Theme.STANDARD);
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
	/*@AfterMethod
    public void getResult(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
       
    }*/
	@AfterTest
	public void tearDown() throws SQLException {
		if(driver!=null) {
			driver.quit();
			
		}
		 iqdb.Closeconnection();
		
		
	}
	

}