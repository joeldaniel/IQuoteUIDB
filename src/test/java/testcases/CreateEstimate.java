package testcases;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Testbase;
import pages.Desktop;
import pages.Estimate;
import pages.IquoteLogin;
import pages.JobPage;
import utilities.CommonFunctions;
import utilities.ReadData;

public class CreateEstimate extends Testbase {
	
	
	@Test
	public void createestimate() throws Exception {
		test = extent.createTest("createestimate");
		int Optionnum=1;
		String newest="";
		String CutomerPONum="PO"+CommonFunctions.randInt(1000, 9999);
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\");
		Estimate.deleteFiles(file);
		System.out.println("Base Est : " +Config.getProperty("EstimateIDs"));
		IquoteLogin.Login(Config.getProperty("UserName"), Config.getProperty("Password"));
		Desktop.NavigateToEstimatePage();
		test.log(Status.INFO, "Creating a new Estimate");
		
		Estimate.ClickonNewEstimate();
		Estimate.CreateNewEstimate(Integer.parseInt(Config.getProperty("EstimateIDs")));
		
		HashSet<String> Options=new HashSet<>();
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
		
		Estimate.CalculateEstimate();
		Estimate.NavigateToNegotiationTab();
		newest=Estimate.SaveEstimateNumber();
		Estimate.NegotiaionAndPrint("Actualfor"+newest.replace(",", "")+".pdf");
		test.log(Status.INFO, "Creation Of estimate ends");
		/*Estimate.StatusChangeTo("Release to production", "In Production",CutomerPONum,"");
		Estimate.CloseEstimateTab();
		JobPage.NavigateToJobPage();
		JobPage.searchJobWithEstimateNumber(newest);
		JobPage.NavigateToJobGeneral();
		JobPage.NavigateToJobPlanning();
		JobPage.NavigateToJobMaterials();*/
		
		
		
	}
}
