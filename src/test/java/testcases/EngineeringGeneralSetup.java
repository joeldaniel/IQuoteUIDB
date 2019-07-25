package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Testbase;
import pages.Desktop;
import pages.IquoteLogin;
import utilities.CommonFunctions;

public class EngineeringGeneralSetup extends Testbase {
	
	@Test
	public void engineeringgeneralsetup() throws Exception {
		//Naviage to engieeringgeneralsetup
		test = extent.createTest("engineeringgeneralsetup");
		IquoteLogin.Login(Config.getProperty("UserName"), Config.getProperty("Password"));
		if(Desktop.Quicksearch("Engineering General Setup")) {
			if((CommonFunctions.getAttribute(driver, By.xpath("//label[text()='Do not disable Engineering on Change (Qty, Delivery Date )']/.."), "aria-checked")).equalsIgnoreCase("false")) {
				test.log(Status.INFO, "Check box is unchecked");
				System.out.println("Check box is unchecked");
				Desktop.CloseTab("Engineering General Setup");
				Desktop.NavigateToEstimatePage();
				
				
			}
		}
		
	}

}
