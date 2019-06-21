package rough;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import base.Testbase;
import pages.Desktop;
import pages.Estimate;
import pages.IquoteLogin;
import utilities.CommonFunctions;
import utilities.ReadData;
import java.util.Collection;

public class createproductandcomponents extends Testbase{
	@Test
	public  void test() throws Exception {
		IquoteLogin.Login(Config.getProperty("UserName"), Config.getProperty("Password"));
		Desktop.NavigateToEstimatePage();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='ltv_ ltv--inline']//input[@class='numeric dot-input']")).sendKeys("12997");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//footer[@class='lkvg']//button[@class='lkv']")).click();
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath("//span[contains(text(),'12,997')]"));
		actions.doubleClick(elementLocator).perform();
		Thread.sleep(5000);
		
		Estimate name = new Estimate();
		
		name.DeleteRenamedProductsandComponents();
		
		
	}

}
