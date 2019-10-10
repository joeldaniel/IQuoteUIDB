package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.Status;

import base.Testbase;
import io.qameta.allure.Step;
import utilities.CommonFunctions;

public class Desktop extends Testbase {
	
	@Step("Navigating to estimate page...")
	public static void NavigateToEstimatePage()throws Exception
	{
		
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Desktop_Label")));
		Thread.sleep(2000);
		CommonFunctions.ClickElement(driver, By.xpath("//label[text()='Sales']")); 
		Thread.sleep(2000);// Click on Sales tab
		CommonFunctions.ClickElement(driver, By.xpath("//label[text()='IQuote']")); //Click on Iquote text
		CommonFunctions.ClickElement(driver, By.xpath("//div[@class='drop-down']//label[text()='Estimate']"));// Click on Estimate from the dropdown



		CommonFunctions.waitForPageLoad(driver);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Estimate']")));
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//b[text()='Estimate']"), 120000);
		//CommonFunctions.ClickElement(driver, By.xpath(Locators.getProperty(Locators.PartialJob)));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("Estimate_Field"))));
		int val =driver.findElements(By.xpath(OR.getProperty("Estimate_Field"))).size();
		System.out.println(val);
		if (val>0)
		{
			System.out.println("Navigation to Estimate Page Successfull");
			
		}
		else
		{
			System.err.println("Navigation to Estimate Page Failed");
			

		}

		    
	}

	public  static boolean Quicksearch(String searchpage) throws Exception
    {
          driver.findElement(By.xpath("//input[@placeholder='Quick Searching']")).click();

          driver.findElement(By.xpath("//input[@placeholder='Quick Searching']")).sendKeys(searchpage);


          driver.findElement(By.xpath("//input[@placeholder='Quick Searching']")).sendKeys(Keys.ENTER);
          CommonFunctions.waitUntilElementisVisible(driver, (By.xpath("//label[text()='"+searchpage+"']")), 5000);
          driver.findElement(By.xpath(("//label[text()='"+searchpage+"']"))).click();
          CommonFunctions.WaitFor_ElementVisiblity(driver, By.xpath("//header[@class='program__header']//label//b[text()='"+searchpage+"']"));
          if(driver.findElements(By.xpath("//header[@class='program__header']//label//b[text()='"+searchpage+"']")).size()>0)
          {
                System.out.println("Search successfully");
                return true;
          }
          else
          {
                System.out.println("Search failed");    
                return false;
                
          }
    }
	
	public static boolean CloseTab(String title)
	{
		if(driver.findElements(By.xpath("//span[@class='app__tab__close']//preceding::span[@title='"+title+"']")).size()>0){
			driver.findElement(By.xpath("//span[@class='app__tab__close']//preceding::span[@title='\"+title+\"']")).click();
			return true;
				
		}
		else {
			return false;
		}
	}
	public static boolean deletefilesinfolder(String directoryname) {
		File directory = new File(directoryname);
		File[] files = directory.listFiles();
		for(File file:files) {
			if(!file.delete()) {
				return false;
				
			}
			
		}
		return true;
		
	}
}
