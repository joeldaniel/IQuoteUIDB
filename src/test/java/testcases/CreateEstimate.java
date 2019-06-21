package testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import base.Testbase;
import pages.Desktop;
import pages.Estimate;
import pages.IquoteLogin;
import utilities.ReadData;

public class CreateEstimate extends Testbase {
	
	
	@Test
	public void createestimate() throws Exception {
		
			
		System.out.println("Base Est : " +Config.getProperty("EstimateIDs"));
		IquoteLogin.Login(Config.getProperty("UserName"), Config.getProperty("Password"));
		Desktop.NavigateToEstimatePage();
		System.out.println("****************************Creating a new Estimate****************************");
		Estimate.ClickonNewEstimate();
		Estimate.CreateNewEstimate(Integer.parseInt(Config.getProperty("EstimateIDs")));
		
		HashSet<String> Options=new HashSet<>();
		HashMap<String,HashSet<String>>Products=new HashMap<String,HashSet<String>>();
		HashMap<String,HashMap<String,HashSet<Double>>> Quantities= new HashMap<String,HashMap<String,HashSet<Double>>>();
		ReadData name = new ReadData();
		Options=name.NoOfOptions(Config.getProperty("EstimateIDs"));
		//System.out.println(Options);
		int i=1;
		for(String Option:Options) {
			if(i>1) {
				 Estimate.Navigate_to_ProductTab();
				 driver.findElement(By.xpath("//span[@class='wvtb__prop']//button")).click();
				 driver.findElement(By.xpath("//span[@class='wvtb__prop']//button")).click();
		    	 Thread.sleep(1000);
		    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'New Option')]")));
		    	 driver.findElement(By.xpath("//li[contains(text(),'New Option')]")).click();
		    	 Estimate.Navigate_to_ProductTab();
		    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='wvtb__prop']//button")));
		    	 driver.findElement(By.xpath("//span[@class='wvtb__prop']//button")).click();
		    	 Thread.sleep(1000);
		    	 System.out.println("//li[contains(text(),'Option "+(i-1)+"')]//following::li"+0+"");
		    	driver.findElement(By.xpath("//li[contains(text(),'Option "+(i-1)+"')]//following::li["+1+"]")).click();
		    	 Estimate.CreateProduct_and_Components(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
		    	 Estimate.ParentChildCombination(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
				Estimate.NavigateToQtyPriceTab();
				Estimate.AddQuantity(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
				
			}
			else {
			Estimate.CreateProduct_and_Components(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
			Estimate.ParentChildCombination(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
			Estimate.NavigateToQtyPriceTab();
			
			Estimate.AddQuantity(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
			
			i+=1;
			}
			
		}
		
	}
}
