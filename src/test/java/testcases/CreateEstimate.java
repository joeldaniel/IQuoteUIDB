package testcases;


import java.util.HashSet;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Testbase;
import pages.Desktop;
import pages.Estimate;
import pages.IquoteLogin;
import pages.JobPage;
import utilities.CommonFunctions;
import utilities.HTML_File_Creator;
import utilities.ReadData;

public class CreateEstimate extends Testbase {
	
	
	@Test
	public void createestimate() throws Exception {
		test = extent.createTest("createestimate");
		HTML_File_Creator HTMLF= new HTML_File_Creator();
		HTMLF.HTMLFileGenerator(Config.getProperty("EstimateIDs")+".html", "IQuote Test", CommonFunctions.CurrentDateTime());
		int Optionnum=1;
		String newest="";
		String CutomerPONum="PO"+CommonFunctions.randInt(1000, 9999);
		String filename="NEG_"+Config.getProperty("EstimateIDs");
		System.out.println("Base Est : " +Config.getProperty("EstimateIDs"));
		HTMLF.addrow("Comment","Customer Estimate ID" , "", "", "", "",Config.getProperty("EstimateIDs")+".html");
		HTMLF.addrow_Twoparm("Comment","Estimate ID From Customer DB#" , "", Config.getProperty("EstimateIDs"), "", "",Config.getProperty("EstimateIDs")+".html");
		IquoteLogin.Login(Config.getProperty("UserName"), Config.getProperty("Password"));
		Desktop.NavigateToEstimatePage();
		test.log(Status.INFO, "Creating of Estimate Started");
		
		Estimate.ClickonNewEstimate();
		Estimate.CreateNewEstimate(Integer.parseInt(Config.getProperty("EstimateIDs")));
		
		HashSet<String> Options=new HashSet<>();
		ReadData name = new ReadData();
		Options=name.NoOfOptions(Config.getProperty("EstimateIDs"));
		test.log(Status.INFO, "Options for Estimate are : "+Options);
		for(String Option:Options) {
			test.log(Status.INFO, "Creating Option : "+Option);
			Estimate.CreateOption(Optionnum);
			test.log(Status.INFO, "Creating Product and components");
			Estimate.CreateProduct_and_Components(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
			test.log(Status.INFO, "Parent child combination");
			Estimate.ParentChildCombination(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
			test.log(Status.INFO, "Adding characteristics for components");
			Estimate.ComponentandCharacteristics_ForPaperSpec(Integer.parseInt(Config.getProperty("EstimateIDs")),Option);
			Estimate.NavigateToQtyPriceTab();
			test.log(Status.INFO, "Adding Quantity");
			Estimate.AddQuantity(Integer.parseInt(Config.getProperty("EstimateIDs")), Option);
			Optionnum+=1;
		}
		test.log(Status.INFO, "Saving Estimate");
		Estimate.SaveEstimate();
		test.log(Status.INFO, "Calculating Estimate");
		Estimate.CalculateEstimate();
		
		Estimate.NavigateToEngineeringTab();
		test.log(Status.INFO, "Verifying Engineering Diagrams");
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
		String HTMLfilepath=System.getProperty("user.dir")+ "\\HTMLReports\\"+Config.getProperty("EstimateIDs")+".html"; 
		System.out.println("HTML File generated path is :- "+HTMLfilepath);
		

	}
}
