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

public class createproductandcomponents extends Testbase{
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
		Desktop.NavigateToEstimatePage();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='ltv_ ltv--inline']//input[@class='numeric dot-input']")).sendKeys("26038");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//footer[@class='lkvg']//button[@class='lkv']")).click();
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath("//span[contains(text(),'26,038')]"));
		actions.doubleClick(elementLocator).perform();
		Thread.sleep(5000);
		String Actualname= Estimate.NegotiaionAndPrint("Fse","Sfe");
		//String newest="18,076";
		//test.log(Status.INFO, "main");
		//newest=newest.replace(",", "");
		/*JobPage.NavigateToJobPage();
		JobPage.searchJobWithEstimateNumber(newest);
		JobPage.NavigateToJobGeneral();
		JobPage.NavigateToJobPlanning();
		JobPage.PushPlanningData(Config.getProperty("EstimateIDs"), "Planning");
		JobPage.NavigateToJobMaterials();
		JobPage.PushMaterialData(Config.getProperty("EstimateIDs"), "Material");
		JobPage.NavigateToJobEngineering();
		JobPage.VerifyJobEngineering(Config.getProperty("EstimateIDs"));
		JobPage.CloseJobTab();
		if(JobPage.VerifyJobPlanning(Config.getProperty("EstimateIDs"), "Planning")) {
			System.out.println("Pass");
		}else {
			System.out.println("Fail");
		}
		if(JobPage.VerifyJobMaterial(Config.getProperty("EstimateIDs"), "Material")) {
			System.out.println("Pass");
		}else {
			System.out.println("Fail");
		}*/
		//for image
		JobPage.NavigateToJobPage();
		//JobPage.searchJobWithEstimateNumber(newest);
		JobPage.NavigateToJobEngineering();
		WebElement webElement = driver.findElement(By.cssSelector("svg.diagram__canvas"));
		//Screenshot screenshot= new AShot().takeScreenshot(driver, webElement);
		Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, webElement);
		
		ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") +"\\src\\test\\resources\\Documents\\googleLogo.png"));
		
		
		
		BufferedImage actualImage = screenshot.getImage();
		
		BufferedImage expectedImage = ImageIO.read(new File("C:\\Joel\\SeleniumProjects\\IQuoteUIDB\\src\\test\\resources\\Documents\\googleLogo.png"));
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        BufferedImage diffImage = diff.getMarkedImage();
        ImageIO.write(actualImage, "PNG", new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\diffImage.png"));
       
        if(diff.hasDiff()==true)
        {
         System.out.println("Images are Not Same");
        }
        else {
         System.out.println("Images are Same");
        }
            
      //driver.quit();
	}

}
