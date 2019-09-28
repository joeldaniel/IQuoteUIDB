package testcases;

import java.io.File;
import java.sql.SQLException;
import java.util.HashSet;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Testbase;
import pages.Desktop;
import pages.Estimate;
import pages.IquoteLogin;
import pages.JobPage;
import pages.Negotiation;
import utilities.CommonFunctions;
import utilities.HTML_File_Creator;
import utilities.ReadData;

@Listeners(utilities.ListenerUtils.class)
public class CreateEstimate extends Testbase {
	
	@BeforeClass
	public void launchbrowser() throws Exception {
		/*Runtime.getRuntime().exec("TASKKILL /IM chrome.exe /F");
		Thread.sleep(2000);
		Runtime.getRuntime().exec("TASKKILL /IM chromedriver.exe /F");*/
		if (Config.getProperty("browser").equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			
			driver = new ChromeDriver();
			driver.get(Config.getProperty("testsiteurl"));
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			 wait = new WebDriverWait(driver, 300);
			 IquoteLogin.Login(Config.getProperty("UserName"), Config.getProperty("Password"));
		}
	}
	
	@Test(dataProvider = "dp")
	public void createestimate(String value) throws Exception {
		
		 test = extent.createTest("Creating Estimate for : "+value,"This test creates an estimate from base estimate");
		HTML_File_Creator HTMLF= new HTML_File_Creator();
		
		HTMLF.HTMLFileGenerator(value+".html", "IQuote Test", CommonFunctions.CurrentDateTime());
		int Optionnum=1;
		String newest="";
		String CutomerPONum="PO"+CommonFunctions.randInt(1000, 9999);
		String filename="NEG"+value;
		System.out.println("Base Est : " +value);
		HTMLF.addrow("Comment","Customer Estimate ID" , "", "", "", "",value+".html");
		HTMLF.addrow_Twoparm("Comment","Estimate ID From Customer DB#" , "", value, "", "",value+".html");
		Desktop.deletefilesinfolder(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+value+"\\Actual\\");
		Desktop.deletefilesinfolder(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+value+"\\Difference\\");
		Desktop.NavigateToEstimatePage();
		
		
		Estimate.ClickonNewEstimate();
		Estimate.CreateNewEstimate(value);
		
		HashSet<String> Options=new HashSet<>();
		ReadData name = new ReadData();
		Options=name.NoOfOptions(value);
		
		for(String Option:Options) {
			
			Estimate.CreateOption(Optionnum);
			
			Estimate.CreateProduct_and_Components(value, Option);
			
			Estimate.ParentChildCombination(value, Option);
			
			Estimate.ComponentandCharacteristics_ForPaperSpec(value,Option);
			Estimate.NavigateToQtyPriceTab();
			
			Estimate.AddQuantity(value, Option);
			Optionnum+=1;
		}
		
		Estimate.SaveEstimate();
		
		Estimate.CalculateEstimate();
		
		Estimate.NavigateToEngineeringTab();
		
		Estimate.VerifyEngineering(value);
		
		Estimate.NavigateToQtyPriceTab();
		Estimate.VerifyQty(value);
		Estimate.NavigateToNegotiationTab();
		newest=Estimate.SaveEstimateNumber();
		test.log(Status.INFO, "New Estimate Number is : "+newest);
		//String Actualname= Estimate.NegotiaionAndPrint(filename,value);
		//Estimate.VerifyNegotiation(Actualname,value);
		
		Negotiation.VerifyHeader("HubLabels","iquotedbdbqry", "iquoteuiautodb71", value, newest);
		
		
		Estimate.StatusChangeTo("Release to production", "In Production",CutomerPONum,"");
		Estimate.CloseEstimateTab();
		//Job Verification Starts
		JobPage.NavigateToJobPage();
		JobPage.searchJobWithEstimateNumber(newest);
		JobPage.NavigateToJobGeneral();
		JobPage.NavigateToJobPlanning();
		JobPage.PushPlanningData(value, "Planning");
		JobPage.NavigateToJobMaterials();
		JobPage.PushMaterialData(value, "Material");
		JobPage.NavigateToJobEngineering();
		JobPage.VerifyJobEngineering(value);
		JobPage.CloseJobTab();
		if(JobPage.VerifyJobPlanning(value, "Planning")) {
			System.out.println("Pass");
			test.log(Status.PASS, "Job Planning data is verified");
		}else {
			System.out.println("Fail");
			test.log(Status.FAIL, "Job Planning verification failed");
		}
		if(JobPage.VerifyJobMaterial(value, "Material")) {
			System.out.println("Pass");
			test.log(Status.PASS, "Job Material data is verified");
		}else {
			System.out.println("Fail");
			test.log(Status.FAIL, "Job Material verification failed");
		}
		Optionqty=0;
		String HTMLfilepath=System.getProperty("user.dir")+ "\\HTMLReports\\"+value+".html"; 
		System.out.println("HTML File generated path is :- "+HTMLfilepath);
		

	}
	@DataProvider(name = "dp")
	public Object[] getDataFromDataprovider() {
		return Config.getProperty("EstimateIDs").split(",");

	}
	
	
}
