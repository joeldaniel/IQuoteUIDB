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

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import base.Testbase;
import pages.Desktop;
import pages.Estimate;
import pages.IquoteLogin;

public class ImageComparisionusingShutter extends Testbase{
	

	@Test
	public void compareimages() throws Exception {	
	test = extent.createTest("test");
	System.setProperty("webdriver.chrome.driver",
			System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
	driver = new ChromeDriver();
	
	driver.get("http://iquoteuiautoapp:83/webcontroller.dll?start");
	
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
	Desktop.NavigateToEstimatePage();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='ltv_ ltv--inline']//input[@class='numeric dot-input']")).sendKeys("33282");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//footer[@class='lkvg']//button[@class='lkv']")).click();
	Thread.sleep(1000);
	Actions actions = new Actions(driver);
	WebElement elementLocator = driver.findElement(By.xpath("//span[contains(text(),'33,282')]"));
	actions.doubleClick(elementLocator).perform();
	Thread.sleep(5000);
	Estimate.NavigateToEngineeringTab();
	WebElement element=driver.findElement(By.cssSelector("svg.diagram__canvas"));
	//Shutterbug.shootElement(driver, element).save(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\33229\\");
	
	//to write image in folder
	//ImageIO.write(Shutterbug.shootElement(driver, element).getImage(),"PNG",new File(System.getProperty("user.dir") +"\\src\\test\\resources\\Documents\\"+33229+"\\ENG.png"));
	
	
	File image=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\33229\\Base\\ENG.png");
	BufferedImage base=ImageIO.read(image);
	
	//to compare
	boolean flag=Shutterbug.shootElement(driver, element).equals(base);
	if(flag!=true) {
		//to write the diff image
		Shutterbug.shootElement(driver, element).equalsWithDiff(base, System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\33229\\daniel");	
	}
	System.out.println(flag);
	}
}
