package testcases;



import java.util.HashSet;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.Testbase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.Desktop;
import pages.Estimate;
import pages.IquoteLogin;
import pages.JobPage;
import pages.Negotiation;
import utilities.AllureLogger;
import utilities.CommonFunctions;
import utilities.HTML_File_Creator;
import utilities.ReadData;


@Listeners(utilities.ListenerUtils.class)
public class CreateEstimate extends Testbase {
	
	HashSet<String> Options=new HashSet<>();
	ReadData name = new ReadData();
	String [] Negotiationfields= {"ComponentDetails","Header","OtherCosts","OtherRawMaterialCost","OutSourcingCost","SalesPrice","SubstrateCost","TransformationCost"};
	String [] Jobfields= {"JobPlanning","JobMaterial"};
	String [] EstEnggg= {"Finishing engineering query - Estimate","Press engineering query - Estimate","Raw material engineering - Estimate"};
	String [] JobEnggg= {"Finishing engineering query - Job","Press engineering query - Job","Raw material engineering - Job"};
	
	@BeforeClass
	@Severity(SeverityLevel.NORMAL)
	@Description("Login to Application")
	public void logintoiquote() {
		AllureLogger.startTest();
		try {
			IquoteLogin.Login(Config.getProperty("UserName"), Config.getProperty("Password"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		AllureLogger.endTest();
	}
	
	@Test(dataProvider = "dp",priority=2,description="Creating an estimate from base estimate")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Creating an estimate from base estimate")
	public void createestimate(String value) throws Exception {
		
		AllureLogger.startTest();
		HTML_File_Creator HTMLF= new HTML_File_Creator();
		HTMLF.HTMLFileGenerator(value+".html", "IQuote Test", CommonFunctions.CurrentDateTime());
		int Optionnum=1;
		String newest="";
		String CutomerPONum="PO"+CommonFunctions.randInt(1000, 9999);
		System.out.println("Base Est : " +value);
		HTMLF.addrow("Comment","Customer Estimate ID" , "", "", "", "",value+".html");
		HTMLF.addrow_Twoparm("Comment","Estimate ID From Customer DB#" , "", value, "", "",value+".html");
		//Desktop.deletefilesinfolder(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+value+"\\Actual\\");
		//Desktop.deletefilesinfolder(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+value+"\\Difference\\");
	
		Desktop.NavigateToEstimatePage();
		
		Estimate.CloseOpenedEstimateTabs();
		Estimate.ClickonNewEstimate();
				
		Estimate.CreateNewEstimate(value);
		
		
		Options=name.NoOfOptions(value);
		
		for(String Option:Options) {
			
			Estimate.CreateOption(Optionnum);
			
			Estimate.CreateProduct_and_Components(value, Option);
			
			Estimate.ParentChildCombination(value, Option);
			
			Estimate.ComponentandCharacteristics(value,Option);
			
			Estimate.NavigateToQtyPriceTab();
			
			Estimate.AddQuantity(value, Option);
			Optionnum+=1;
		}
		
		Estimate.CalculateEstimate();
		
		Estimate.SaveEstimate();
		
		//Estimate.NavigateToEngineeringTab();
		
		//Estimate.VerifyEngineering(value);
		
		
		//Estimate.NavigateToQtyPriceTab();
			
		Estimate.NavigateToNegotiationTab();
		newest=Estimate.SaveEstimateNumber();
		//String newest="26128";
		Negotiation.VerifyActualvsBase(value,newest.replace(",", ""),EstEnggg);
		
		Negotiation.VerifyActualvsBase(value,newest.replace(",", ""),Negotiationfields);
		Estimate.StatusChangeTo("Release to production", "In Production",CutomerPONum,"");
		Estimate.CloseEstimateTab();
		
		//String newest="1234";
		
		if (Options.size()==1) {
			JobPage.NavigateToJobPage();
			JobPage.searchJobWithEstimateNumber(newest);
			JobPage.NavigateToJobGeneral();
			JobPage.NavigateToJobPlanning();
			//Windows-ba
			JobPage.NavigateToJobMaterials();
			//String newest="1234";
			Negotiation.VerifyActualvsBase(value,newest.replace(",", ""),Jobfields);
			JobPage.NavigateToJobEngineering();
			//JobPage.VerifyJobEngineering(value);
			Negotiation.VerifyActualvsBase(value,newest.replace(",", ""),JobEnggg);
			JobPage.CloseJobTab();
		}
		
		String HTMLfilepath=System.getProperty("user.dir")+ "\\HTMLReports\\"+value+".html"; 
		System.out.println("HTML File generated path is :- "+HTMLfilepath);
		AllureLogger.endTest();

	}
	@DataProvider(name = "dp")
	public Object[] getDataFromDataprovider() {
		return Config.getProperty("EstimateIDs").split(",");

	}
	
	
}