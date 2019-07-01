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
		
		int Optionnum=1;
		System.out.println("Base Est : " +Config.getProperty("EstimateIDs"));
		IquoteLogin.Login(Config.getProperty("UserName"), Config.getProperty("Password"));
		Desktop.NavigateToEstimatePage();
		System.out.println("****************************Creating a new Estimate****************************");
		Estimate.ClickonNewEstimate();
		Estimate.CreateNewEstimate(Integer.parseInt(Config.getProperty("EstimateIDs")));
		
		HashSet<String> Options=new HashSet<>();
		//HashMap<String,HashSet<String>>Products=new HashMap<String,HashSet<String>>();
		//HashMap<String,HashMap<String,HashSet<Double>>> Quantities= new HashMap<String,HashMap<String,HashSet<Double>>>();
		ReadData name = new ReadData();
		Options=name.NoOfOptions(Config.getProperty("EstimateIDs"));
		
		for(String Option:Options) {
			Estimate.CreateOption(Optionnum);
			Estimate.CreateProduct_and_Components(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
			Estimate.ParentChildCombination(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
			Estimate.ComponentandCharacteristics_ForPaperSpec(Integer.parseInt(Config.getProperty("EstimateIDs")),Option);
			Estimate.NavigateToQtyPriceTab();
			Estimate.AddQuantity(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
			Optionnum+=1;
		}
		Estimate.SaveEstimate();
		Estimate.CalculateEstimate();
		Estimate.NavigateToNegotiationTab();
		Estimate.SaveEstimateNumber();
		Estimate.NegotiaionAndPrint("Actual.pdf");
		
		
		
	}
}
