package base;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
public class Testbase {

	public static WebDriver driver;
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
   
    protected static DBUtil iqdb = new DBUtil();
	
   
	@BeforeSuite
	public void setUp() throws IOException {
		if (driver == null) {
			
			System.out.println("DB Selected is : "+System.getenv("DataBase"));
			System.out.println("Estimate ID's are : "+System.getenv("Estimates"));
			
			//for Jenkins
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\"+System.getenv("DataBase")+".properties");
			Config.load(fis);
			//for normal work
			/*fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\PremierPress.properties");
			Config.load(fis);*/
			
			//uncomment this for jenkins
			Config.setProperty("EstimateIDs", System.getenv("Estimates"));
			saveProperties(Config,System.getenv("DataBase"));
			
			//DBUtil iqdb=new DBUtil(Config.getProperty("DBUsername"), Config.getProperty("DBPassWord"), Config.getProperty("DBUrl"));
			
			
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
				
				browser = System.getenv("browser");
			}else{
				
				browser = Config.getProperty("browser");
				
			}
			
			Config.setProperty("browser", browser);
			System.out.println("The browser selected : "+browser);
			
		
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
					System.getProperty("user.dir") + "\\target\\surefire-reports\\MyOwnReport.html");
			System.out.println(System.getProperty("user.dir") + "\\target\\surefire-reports\\MyOwnReport.html");
			
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
	        iqdb.Createconnection(Config.getProperty("DBUrl"), Config.getProperty("DBUsername"), Config.getProperty("DBPassWord"));
	    }	

	}
	protected static void saveProperties(Properties p,String val) throws IOException
    {
        FileOutputStream fr = new FileOutputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\"+val+".properties");
        p.store(fr, "Properties");
        fr.close();
        System.out.println("After saving properties: " + p);
    }
	@AfterMethod
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
       
    }
	
	@AfterSuite
	public void tearDown() throws SQLException {
		
		  driver.quit();
		  iqdb.Closeconnection();
		 extent.flush();
		
	}

}
