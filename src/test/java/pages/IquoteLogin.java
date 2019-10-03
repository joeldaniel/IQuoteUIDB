package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.Status;

import base.Testbase;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import utilities.CommonFunctions;

public class IquoteLogin extends Testbase{
	@Step("Logging into iquote application with username : {0}")
	public static void Login(String UserName,String Password) throws Exception {

		
		
        System.out.println("Login Started");
        
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user")));
		
		if (CommonFunctions.isElementPresent(driver, By.id("user")))
		{
			CommonFunctions.ClickElement(driver, By.id("user"));
			CommonFunctions.SendValue(driver, By.id("user"), UserName);
			CommonFunctions.SendValue(driver, By.id("pwd"), Password);

			CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("First_ok")));
			CommonFunctions.waitForPageLoad(driver);
			Thread.sleep(10000);
			//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(OR.getProperty("Login_OKButton"))));
			/*if (driver.findElements(By.xpath("//label[text()='Desktop']")).size()==0)
			{
			CommonFunctions.ClickElement(driver, By.cssSelector("input#company_ok"));
			}*/
			
			try {
				if(driver.findElements(By.cssSelector("input#company_ok")).size()>0) {
					driver.findElement(By.cssSelector("input#company_ok")).click();
				}
			}
			catch(Exception e) {
				
			}
			
			
			CommonFunctions.waitForPageLoad(driver);
			CommonFunctions.waitUntilElementisVisible(driver, By.xpath("//label[text()='Sales']"), 180000);
			if (CommonFunctions.isElementPresent(driver, By.xpath(OR.getProperty("Desktop_Labl"))))
			{
				System.out.println("Login To Iquote Successfull");
				//test.log(Status.PASS, "Login To Iquote Successfull");
				Allure.step("Login To Iquote Successfull");
				
			}
			else
			{
				System.out.println("Failed to Login To Iquote");
				//test.log(Status.FAIL, "Login To Iquote Successfull");
				
			}
		
		}
		else
		{
			System.out.println("Failed to navigate to PSV login Page");
			
		}
		
		
	
	
	}
	
	public static void Login(String first,String second,String pass) {
		System.out.println("test");
	}
	

}
