package rough;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Testbase;
import pages.IquoteLogin;
import pages.JobPage;
import utilities.CommonFunctions;

public class createproductandcomponents extends Testbase{
	@Test
	public  void method() throws Exception {
		test = extent.createTest("test");
		String filename="NEG_"+Config.getProperty("EstimateIDs");
		String CutomerPONum="PO"+CommonFunctions.randInt(1000, 9999);
		IquoteLogin.Login(Config.getProperty("UserName"), Config.getProperty("Password"));
		String ExcelPath="C:\\Joel\\SeleniumProjects\\IQuoteUIDB\\src\\test\\resources\\Documents\\25904\\Base\\JobMaterial.xlsx";
		String ExcelSheetPath2="C:\\Joel\\SeleniumProjects\\IQuoteUIDB\\src\\test\\resources\\Documents\\25904\\Actual\\JobMaterial.xlsx";
		String ExcelPath1="C:\\Joel\\SeleniumProjects\\IQuoteUIDB\\src\\test\\resources\\Documents\\25904\\Base\\JobMaterial.xlsx";
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
		String newest="25,931";
		test.log(Status.INFO, "main");
		//newest=newest.replace(",", "");
		JobPage.NavigateToJobPage();
		JobPage.searchJobWithEstimateNumber(newest);
		JobPage.NavigateToJobGeneral();
		JobPage.NavigateToJobPlanning();
		JobPage.PushPlanningData(newest, "Planning");
		
	}

}
