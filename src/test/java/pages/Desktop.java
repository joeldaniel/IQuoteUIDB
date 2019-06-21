package pages;

import org.openqa.selenium.By;

import base.Testbase;
import utilities.CommonFunctions;

public class Desktop extends Testbase {
	
	public static void NavigateToEstimatePage()throws Exception
	{
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Desktop_Label")));

		CommonFunctions.ClickElement(driver, By.xpath("//label[text()='Sales']")); // Click on Sales tab
		CommonFunctions.ClickElement(driver, By.xpath("//label[text()='IQuote']")); //Click on Iquote text
		CommonFunctions.ClickElement(driver, By.xpath("//div[@class='drop-down']//label[text()='Estimate']"));// Click on Estimate from the dropdown



		CommonFunctions.waitForPageLoad(driver);
		Thread.sleep(3000);
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//b[text()='Estimate']"), 120000);
		//CommonFunctions.ClickElement(driver, By.xpath(Locators.getProperty(Locators.PartialJob)));
		Thread.sleep(3000);
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
}
