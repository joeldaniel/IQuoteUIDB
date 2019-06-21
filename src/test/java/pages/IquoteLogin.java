package pages;

import org.openqa.selenium.By;

import base.Testbase;
import utilities.CommonFunctions;

public class IquoteLogin extends Testbase{
	
	public static void Login(String UserName,String Password) throws Exception {

		
        System.out.println("Login Started");
        
		Thread.sleep(5000);
		if (CommonFunctions.isElementPresent(driver, By.id("user")))
		{
			CommonFunctions.ClickElement(driver, By.id("user"));
			CommonFunctions.SendValue(driver, By.id("user"), UserName);
			CommonFunctions.SendValue(driver, By.id("pwd"), Password);

			CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("First_ok")));
			CommonFunctions.waitForPageLoad(driver);
			Thread.sleep(15000);
			if (CommonFunctions.isElementPresent(driver, By.xpath(OR.getProperty("Login_OKButton"))))
			{
			CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Login_OKButton")));
			}
			CommonFunctions.waitForPageLoad(driver);
			CommonFunctions.waitUntilElementisVisible(driver, By.xpath("//label[text()='Sales']"), 180000);
			if (CommonFunctions.isElementPresent(driver, By.xpath(OR.getProperty("Desktop_Labl"))))
			{
				System.out.println("Login To Iquote Successfull");
			}
			else
			{
				System.out.println("Failed to Login To Iquote");
				
			}
		
		}
		else
		{
			System.out.println("Failed to navigate to PSV login Page");
			
		}
		
		
	
	}
	

}
