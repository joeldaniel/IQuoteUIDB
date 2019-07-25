package rough;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Testbase;
import pages.Desktop;
import pages.Estimate;
import pages.IquoteLogin;
import pages.JobPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import utilities.CommonFunctions;

public class createproductandcomponents2 extends Testbase{
	@Test
	public  void method() throws Exception {
		test = extent.createTest("test");
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://iquoteappdbqry:81/WebController.dll?Start");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
		 wait = new WebDriverWait(driver, 180);
		//String filename="NEG_"+Config.getProperty("EstimateIDs");
		//String CutomerPONum="PO"+CommonFunctions.randInt(1000, 9999);
		IquoteLogin.Login(Config.getProperty("UserName"), Config.getProperty("Password"));
		//String ExcelPath="C:\\Joel\\SeleniumProjects\\IQuoteUIDB\\src\\test\\resources\\Documents\\25904\\Base\\JobMaterial.xlsx";
		//String ExcelSheetPath2="C:\\Joel\\SeleniumProjects\\IQuoteUIDB\\src\\test\\resources\\Documents\\25904\\Actual\\JobMaterial.xlsx";
		//String ExcelPath1="C:\\Joel\\SeleniumProjects\\IQuoteUIDB\\src\\test\\resources\\Documents\\25904\\Base\\JobMaterial.xlsx";
		/*Desktop.NavigateToEstimatePage();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='ltv_ ltv--inline']//input[@class='numeric dot-input']")).sendKeys("25927");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//footer[@class='lkvg']//button[@class='lkv']")).click();
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath("//span[contains(text(),'25,927')]"));
		actions.doubleClick(elementLocator).perform();
		Thread.sleep(5000);*/
		//String newest="26,011";
		test.log(Status.INFO, "main");
		//newest=newest.replace(",", "");
		String filename="NEG_";
		Desktop.NavigateToEstimatePage();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='ltv_ ltv--inline']//input[@class='numeric dot-input']")).sendKeys("26,042");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//footer[@class='lkvg']//button[@class='lkv']")).click();
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath("//span[contains(text(),'26,042')]"));
		actions.doubleClick(elementLocator).perform();
		Thread.sleep(5000);
		Estimate.NavigateToNegotiationTab();
		String newest=Estimate.SaveEstimateNumber();
		String Actualname= Estimate.NegotiaionAndPrint(filename,"564");
		
		
		
		
		
      //driver.quit();
	}

}
