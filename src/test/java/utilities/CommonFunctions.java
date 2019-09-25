package utilities;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.Testbase;

public class CommonFunctions extends Testbase {
	public static final int minWait = 5;
	public static final int midWait = 20;
	public static final int maxWait = 30;
	private static SecureRandom random = new SecureRandom();
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
	public static String unique6LengthNumeric()
	{
		DateFormat dateFormat = new SimpleDateFormat("hhssmm");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		if(String.valueOf(sUniqueNumber.charAt(0)).equals("0")){sUniqueNumber=sUniqueNumber.replaceFirst("0", String.valueOf(random.nextInt(8)+1));}
		return sUniqueNumber;
	}
	public static String CurrentDateTime()
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));

		/*getting current date time using calendar class 
		 * An Alternative of above*/
		Calendar calobj = Calendar.getInstance();
		System.out.println(df.format(calobj.getTime()));
		String CDate=df.format(calobj.getTime());
		return CDate;
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
	public static String GetValue(WebDriver driver, By locator) throws Exception
	{
		
		String sValue="";
		if(isElementPresent(driver, locator))
		{
			sValue=driver.findElement(locator).getAttribute("value").trim();
		}
		else
		{
			
			Assert.fail("Not able to get value.Locator is not present "+locator);
		}
		System.out.println("sValue is "+ sValue);
		return sValue;
	}
	public static String getAttribute(WebDriver driver, By locator, String attribute) throws Exception
	{	
		String sAttr = "";

		if (isElementPresent(driver, locator))
		{
			sAttr = driver.findElement(locator).getAttribute(attribute);
		}
		else
		{
			System.err.println("Object doesn't exists");			
		}

		return sAttr;
	}
	public static void Iquote_SelectCheckbox(WebDriver driver, String XpathForLocator, String CheckBoxState) throws Exception
	{
		String XpathForButtonstare=XpathForLocator+"/ancestor::button[@class='ckb ']";
		String CheckboxCurrentstate=driver.findElement(By.xpath(XpathForButtonstare)).getAttribute("aria-checked");
		if(CheckBoxState.equals("0"))
		{

			if(CheckboxCurrentstate.equals("true"))
			{
				driver.findElement(By.xpath(XpathForLocator)).click();
			}
			else
			{
				System.out.println("Checkbox status is False");
			}

		}
		else if(CheckBoxState.equals("1"))
		{
			if(CheckboxCurrentstate.equals("false"))
			{
				driver.findElement(By.xpath(XpathForLocator)).click();
			}
			else
			{
				System.out.println("Checkbox status is true");
			}
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
			WebDriverWait wait = new WebDriverWait(driver, TimeOut);
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
	public static boolean waitForElement(WebElement ele,int timeOut){
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		try{
			wait.until(ExpectedConditions.visibilityOf(ele));
			driver.manage().timeouts().implicitlyWait(minWait, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(minWait, TimeUnit.SECONDS);
			return false;
		}
		
	}
	public static void Iquote_EnterDataintoTextfield(WebDriver driver, String XpathForLocator, String Fieldvalue) throws Exception
	{
		driver.findElement(By.xpath(XpathForLocator)).clear();
		driver.findElement(By.xpath(XpathForLocator)).click(); 
		driver.findElement(By.xpath(XpathForLocator)).sendKeys(Fieldvalue+Keys.TAB+Keys.TAB);
		
	}
	public static void SendValueWithoutClear(WebDriver driver, By locator, String sValue) throws Exception
	{
		WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(locator));
		if (isElementPresent(driver, locator))
		{			
			driver.findElement(locator).sendKeys(sValue+Keys.TAB);
		} else {
			Assert.fail("Element "+locator+" is not present");
		}
	}
	public static void Iquote_SelectFromDropdown(WebDriver driver, String XpathForLocator, String Fieldvalue) throws Exception
	{
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XpathForLocator)));
		driver.findElement(By.xpath(XpathForLocator)).click();
		Thread.sleep(2000);
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//li/label[contains(text(),'"+Fieldvalue+"')]"), 180);
		driver.findElement(By.xpath("//li/label[contains(text(),'"+Fieldvalue+"')]")).click();
		Thread.sleep(2000);
		
		String Selectedval= driver.findElement(By.xpath(XpathForLocator)).getText();
		if (Selectedval.contains(Fieldvalue))
		{
			System.out.println("Selected from dropdown : "+Selectedval);
		}
		else
		{
			System.out.println("Correct value is not selected from dropdown please investigate");
		}
	
	}
	public static void Iquote_SelectFromDropdown_Text(WebDriver driver, String XpathForLocator, String Fieldvalue) throws Exception
    {
		driver.findElement(By.xpath(XpathForLocator)).click();
         driver.findElement(By.xpath(XpathForLocator)).clear();
         driver.findElement(By.xpath(XpathForLocator)).click();
         driver.findElement(By.xpath(XpathForLocator)).sendKeys(Fieldvalue);
          Thread.sleep(2000);
          driver.findElement(By.xpath(XpathForLocator)).sendKeys(Keys.DOWN);
          driver.findElement(By.xpath(XpathForLocator)).sendKeys(Keys.ENTER);
          
          String Selectedval= driver.findElement(By.xpath(XpathForLocator)).getAttribute("value");
          if (Selectedval.equals(Fieldvalue))
          {
                System.out.println("Selected from dropdown : "+Selectedval);
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
	public static void selectDropdownByIndex(WebDriver driver, By locator, int index) throws Exception
	{
		System.out.println("Selecting dropdown with locator "+locator+" by index "+index);
		index+=1;
		if (isElementPresent(driver, locator))
		{
			if (index != 0)
			{
				// select the multiple values from a dropdown
				Select selectByValue = new Select(driver.findElement(locator));
				selectByValue.selectByIndex(index);
			} 
			Thread.sleep(1000);
		}
	}
	
	public static String futureDateinMMddyyyyFormat (int NumberOfdaysToAdd)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, NumberOfdaysToAdd);
		String sfutureDate = sdf.format(c.getTime());
		return sfutureDate;
	}

	public static int randInt(int min, int max) 
	{

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;

	}
}
