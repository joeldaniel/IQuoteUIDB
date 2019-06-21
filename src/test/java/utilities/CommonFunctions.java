package utilities;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.Testbase;

public class CommonFunctions extends Testbase {
	
	public static boolean isElementPresent(WebDriver driver, By by)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			driver.findElement(by);
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			return true;
		} catch (NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			return false;
		}
	}
	public static boolean Elementdisplayed_Enabled(WebElement Element)
	{
		if(Element.isDisplayed())
		{
			if(Element.isEnabled())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	public static void scrolltoWebElement(WebDriver driver, By locator) throws Exception
	{
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
	}
	public static void waitForPageLoad (WebDriver driver)
	{
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) 
			{
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};

		WebDriverWait wait = new WebDriverWait(driver,180);
		try 
		{
			wait.until(expectation);
		}
		catch(Throwable error) 
		{
			System.out.println("Timeout waiting for Page Load Request to complete.");
		}
	}
	public static boolean waitUntilElementisClickable (WebDriver driver, By locator, int TimeOut) throws Exception
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 180);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (TimeoutException e) {
			System.err.println("Element did not become clickable even after waiting for "+TimeOut+" seconds");
			return false;
		}
	}
	public static void SetOriginalWindowHandle(WebDriver driver )
	{
		String CommonOriginalHandle=null;
		String CommonOrignalwindowTitle=null;
		System.out.println("-----Setting WebDriver Control To original window -----");

		CommonOriginalHandle= driver.getWindowHandle();		
		CommonOrignalwindowTitle =driver.getTitle();
	}
	public static void ClickElement(WebDriver driver, By locator) throws Exception
	{
		if (isElementPresent(driver, locator))
		{
			if (!Elementdisplayed_Enabled(driver.findElement(locator)))
			{
				scrolltoWebElement(driver, locator);
			}

			try {
				driver.findElement(locator).click();
				Thread.sleep(1000);
			} catch (Exception e)
			{
				(new Actions(driver)).moveToElement(driver.findElement(locator)).click().build().perform();
				Thread.sleep(1000);
			}
		}
	}
	public static void SendValue(WebDriver driver, By locator, String sValue) throws Exception
	{
		if (isElementPresent(driver, locator))
		{
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys("."+ Keys.CONTROL + "a" + Keys.DELETE);
			driver.findElement(locator).sendKeys(sValue);
			driver.findElement(locator).sendKeys(Keys.TAB);
		} else {
			Assert.fail("Element "+locator+" is not present");
		}
	}
	public static boolean waitUntilElementisPresent (WebDriver driver, By locator, int TimeOut)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 300);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			System.err.println("Element did not appear even after waiting for "+TimeOut+" seconds");
			return false;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	public static void SendValueWithoutClear(WebDriver driver, By locator, String sValue) throws Exception
	{
		if (isElementPresent(driver, locator))
		{			
			driver.findElement(locator).sendKeys(sValue+Keys.TAB);
		} else {
			Assert.fail("Element "+locator+" is not present");
		}
	}
	public static void Iquote_SelectFromDropdown(WebDriver driver, String XpathForLocator, String Fieldvalue) throws Exception
	{
		
		driver.findElement(By.xpath(XpathForLocator)).click();
		Thread.sleep(2000);
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//li/label[contains(text(),'"+Fieldvalue+"')]"), 180);
		driver.findElement(By.xpath("//li/label[contains(text(),'"+Fieldvalue+"')]")).click();
		Thread.sleep(2000);
		
		String Selectedval= driver.findElement(By.xpath(XpathForLocator)).getText();
		if (Selectedval.equals(Fieldvalue))
		{
			System.out.println("Correct value is selected from dropdown");
		}
		else
		{
			System.err.println("Correct value is not selected from dropdown please investigate");
		}
	
	}
	public static void Iquote_SelectFromDropdown_Text(WebDriver driver, String XpathForLocator, String Fieldvalue) throws Exception
    {
		
         driver.findElement(By.xpath(XpathForLocator)).clear();
         driver.findElement(By.xpath(XpathForLocator)).click();
         driver.findElement(By.xpath(XpathForLocator)).sendKeys(Fieldvalue);
          Thread.sleep(2000);
          driver.findElement(By.xpath(XpathForLocator)).sendKeys(Keys.DOWN);
          driver.findElement(By.xpath(XpathForLocator)).sendKeys(Keys.ENTER);
          
          String Selectedval= driver.findElement(By.xpath(XpathForLocator)).getAttribute("value");
          if (Selectedval.equals(Fieldvalue))
          {
                System.out.println("Correct value is selected from dropdown");
          }
          else
          {
                System.err.println("Correct value is not selected from dropdown please investigate");
          }
         
               
    }
	public static void WaitFor_ElementVisiblity(WebDriver driver,By Loc)
	{
		WebDriverWait wait = new WebDriverWait(driver, 180); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(Loc));
	}
	public static boolean waitUntilElementisVisible (WebDriver driver, By locator, int TimeOut) throws Exception
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 180);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			System.err.println("Element did not appear even after waiting for "+TimeOut+" seconds");
			return false;
		}
	}
}
