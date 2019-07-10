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
		String filename="NEG_"+Config.getProperty("EstimateIDs");
		//Estimate.deleteFiles(file);
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
		Estimate.SaveEstimate();
		Estimate.CalculateEstimate();
		
		Estimate.NavigateToEngineeringTab();
		Estimate.VerifyEngineering(Config.getProperty("EstimateIDs"));
		Estimate.NavigateToQtyPriceTab();
		Estimate.VerifyQty(Config.getProperty("EstimateIDs"));
		Estimate.NavigateToNegotiationTab();
		newest=Estimate.SaveEstimateNumber();
		String Actualname= Estimate.NegotiaionAndPrint(filename,Config.getProperty("EstimateIDs"));
		Estimate.VerifyNegotiation(Actualname,Config.getProperty("EstimateIDs"));
		test.log(Status.INFO, "Creation Of estimate ends");
		
		Estimate.StatusChangeTo("Release to production", "In Production",CutomerPONum,"");
		Estimate.CloseEstimateTab();
		//Job Verification Starts
		JobPage.NavigateToJobPage();
		JobPage.searchJobWithEstimateNumber(newest);
		JobPage.NavigateToJobGeneral();
		JobPage.NavigateToJobPlanning();
		JobPage.PushPlanningData(Config.getProperty("EstimateIDs"), "Planning");
		JobPage.NavigateToJobMaterials();
		JobPage.PushMaterialData(Config.getProperty("EstimateIDs"), "Material");
		JobPage.NavigateToJobEngineering();
		JobPage.VerifyJobEngineering(Config.getProperty("EstimateIDs"));
		JobPage.CloseJobTab();
		if(JobPage.VerifyJobPlanning(Config.getProperty("EstimateIDs"), "Planning")) {
			System.out.println("Pass");
		}else {
			System.out.println("Fail");
		}
		if(JobPage.VerifyJobMaterial(Config.getProperty("EstimateIDs"), "Material")) {
			System.out.println("Pass");
		}else {
			System.out.println("Fail");
		}
		
		
		
		
	}
}
