package pages;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.Testbase;
import io.qameta.allure.Step;
import utilities.AllureLogger;
import utilities.CommonFunctions;
import utilities.ReadData;

public class Estimatepage_Characteristics extends Testbase {
	
	ReadData name = new ReadData();
	
	@Step("Adding CPGraphMedia Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2}")
	public void Charactertics_CPGraphMedia(String Estimateid,String IdItemOption, String Comporderval) throws ClassNotFoundException, IOException, SQLException
	{
		HashMap<String, HashMap<String, String>> EstPageSpec = new HashMap<String, HashMap<String, String>>();

		//EstPageSpec=name.Papegetpec(Estimateid, Comporderval); 
		try {
			EstPageSpec=name.CPGraphMedia(Estimateid,IdItemOption, Comporderval);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		String OptDescp= EstPageSpec.get(Comporderval).get("OptionDescription");
		String CompDescp1= EstPageSpec.get(Comporderval).get("ComponentDescription");
		String CompFinal= EstPageSpec.get(Comporderval).get("ComponentFinal");
		String CompOrder= EstPageSpec.get(Comporderval).get("ComponentOrder");
		String CompTypedescp= EstPageSpec.get(Comporderval).get("ComponentTypeDesc");
		String CharacTypeDesc= EstPageSpec.get(Comporderval).get("CharacteristicDesc");
		String Substarte= EstPageSpec.get(Comporderval).get("SubstrateType");
		String PageGrammage= EstPageSpec.get(Comporderval).get("Grammage");
		String PageLine= EstPageSpec.get(Comporderval).get("Line");
		String PageProvide= EstPageSpec.get(Comporderval).get("Provided");
		String PageFormatWidth= EstPageSpec.get(Comporderval).get("formatwidth");
		String PageFormatHeight= EstPageSpec.get(Comporderval).get("formatheight");
		String Pagemoreoptions= EstPageSpec.get(Comporderval).get("moreoptions");
		String PageNotes= EstPageSpec.get(Comporderval).get("Notes");
		String PageManufacturer= EstPageSpec.get(Comporderval).get("Manufacturer");
		String PageOtherManufacturer= EstPageSpec.get(Comporderval).get("OtherManufacturer");
		String PageSimulatesGenericSubstrate= EstPageSpec.get(Comporderval).get("SimulatesGenericSubstrate");
		String PageGrainDirection= EstPageSpec.get(Comporderval).get("GrainDirection");


		try {
			CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("NE_Substrate_Type")));
			Thread.sleep(3000);
			CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("NE_Substrate_Type")), Substarte);
			Thread.sleep(10000);
			driver.findElement(By.xpath(OR.getProperty("NE_Substrate_Type"))).sendKeys(Keys.DOWN);
			driver.findElement(By.xpath(OR.getProperty("NE_Substrate_Type"))).sendKeys(Keys.ENTER);      

			Thread.sleep(5000);
			if(!PageLine.isEmpty()) {
				CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("NE_Line_Click")));
				String SelectLine="//html/body/div[@class='drop-down']//li[.//label[text()='"+PageLine+"']]";
				CommonFunctions.ClickElement(driver, By.xpath((SelectLine)));
				Thread.sleep(3000);
			}
			
			//int Grammage= Integer.parseInt(PageGrammage);

			String Grammage1= PageGrammage.replace(".0", "");
			//System.out.println("Grammage is :- "+Grammage1);
			/*driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).click();
			//CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("NE_Grammage")), Grammage1);
			 driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).sendKeys(Grammage1);
			driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).sendKeys(Keys.DOWN);
			Thread.sleep(2000);
			driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).sendKeys(Keys.TAB);*/
			
			driver.findElement(By.xpath("(//label[text()='Grammage/ Thickness']/following-sibling::span/span//..)[3]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='"+Grammage1+"']")).click();
			
			
			String pr=driver.findElement(By.xpath("//label[text()='Provided']//parent::button")).getAttribute("aria-checked");
			if(PageProvide.equalsIgnoreCase("0")) {
				 if(pr.equalsIgnoreCase("true")) {
					  driver.findElement(By.xpath("//label[text()='Provided']")).click();
				  }
			}
			else if(PageProvide.equalsIgnoreCase("1")) {
				 if(pr.equalsIgnoreCase("false")) {
					  driver.findElement(By.xpath("//label[text()='Provided']")).click();
				  }
			}
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//label[text()='"+CharacTypeDesc+"']/ancestor::div[@class='list__item']//label[text()='Details']/parent::button")).click();

			if (driver.findElements(By.xpath("//header[text()='Details']")).size()>0)
			{   CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='Format']/parent::span//span[@class='ltv__itemcont ltv_']/input"), 180);
				//System.out.println("Details page is displayed");
				String SFFinishedFormat=PageFormatWidth+" x "+PageFormatHeight;
				driver.findElement(By.xpath("//label[text()='Format']/parent::span//span[@class='ltv__itemcont ltv_']/input")).sendKeys(SFFinishedFormat+Keys.TAB);
				driver.findElement(By.xpath("//label[text()='Grain Direction']/parent::span//span[@class='ltv__itemcont ltv_']/span[@class='input-wraper simple-lookup2']")).click();
				Thread.sleep(3000);
				if (PageGrainDirection!=null || PageGrainDirection!="")
				{
					String XpathforgrainDirection="//div[@class='drop-down']//label[text()='"+PageGrainDirection+"']"; 
					driver.findElement(By.xpath(XpathforgrainDirection)).click();
				}

				//MOre Options
				//driver.findElement(By.xpath("//label[text()='More Options']/parent::div//button[1]")).click();

				driver.findElement(By.xpath("//button[@title='OK']")).click();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	}
	@Step("Adding CPGraphBindStitch Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphBindStitch(String Estimateid, String IdItemOption,String Comporderval, String CharteristicDescp) throws Exception
	{
		
		HashMap<String, String> CharCPGraphBindStitch = new HashMap<String, String>();
		try {
			CharCPGraphBindStitch=name.CPGraphBindStitch(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		}
		catch(Exception e) {
			e.printStackTrace();
		}


		String CPGraphBindStitchQty=CharCPGraphBindStitch.get("QuantityOfWireStitches");
		String CPGraphBindStitchNote=CharCPGraphBindStitch.get("Note");
		String CPGraphBindStitchWireStichWidth=CharCPGraphBindStitch.get("WireStichWidth");
		String CPGraphBindStitchApplication1=CharCPGraphBindStitch.get("Application1");
		String CPGraphBindStitchShape=CharCPGraphBindStitch.get("Shape");
		

		String XpathWitCPGraphBindStitchQty="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Quantity of wire stitches']/parent::span/span/input";
		String XpathWitCPGraphBindStitchNote = "//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span/span/div/textarea";	
		String XpathWitCPGraphBindStitchDetails ="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Stitch Detail']/parent::button";
		String XpathWitCPGraphBindStitchWireStichWidth= "//label[text()='Wire Stitch Width']/parent::span/span/input";
		String XpathWitCPGraphBindStitchApplication1="//label[text()='Application']/parent::span/span[@class='ltv__itemcont ltv_']/span/label";
		String XpathWitCPGraphBindStitchShape="//label[text()='Shape']/parent::span/span/span/input";
		
		try {
			
			
			// for Quantity of wire stitches
			driver.findElement(By.xpath(XpathWitCPGraphBindStitchQty)).click();
			driver.findElement(By.xpath(XpathWitCPGraphBindStitchQty)).sendKeys(Keys.DELETE);
			driver.findElement(By.xpath(XpathWitCPGraphBindStitchQty)).sendKeys(CPGraphBindStitchQty+Keys.TAB);
	
			// for Note
			driver.findElement(By.xpath(XpathWitCPGraphBindStitchNote)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphBindStitchNote)).sendKeys(CPGraphBindStitchNote+Keys.TAB);
	
	
	
			// for stitch Details
			driver.findElement(By.xpath(XpathWitCPGraphBindStitchDetails)).click();
			if(driver.findElements(By.xpath("//header[text()='Stitch Detail']")).size()>0)
			{
				System.out.println("Stitch Details page is displayed");
	
				//For Wire Stitch Width
				driver.findElement(By.xpath(XpathWitCPGraphBindStitchWireStichWidth)).clear(); 
				driver.findElement(By.xpath(XpathWitCPGraphBindStitchWireStichWidth)).sendKeys(CPGraphBindStitchWireStichWidth+Keys.TAB);
	
				//For Application
				CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphBindStitchApplication1, CPGraphBindStitchApplication1);
	
				//For Shape
				CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWitCPGraphBindStitchShape, CPGraphBindStitchShape);
				Thread.sleep(2000);
	
				//click on ok button
				driver.findElement(By.xpath("//button[@title='OK']")).click();
			}
			else
			{
				System.err.println("Stitch Details page is not displayed. Please check");
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	
	}
	@Step("Adding CPGraphColorVanish Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2}")
	public void Charactertics_CPGraphColorVanish(String Estimateid,  String IdItemOption,String Comporderval) throws ClassNotFoundException, IOException, SQLException
	{

		try
		{
		HashMap<String, HashMap<String, String>> EstPageColorandVarnish = new HashMap<String, HashMap<String, String>>();
		try {
			EstPageColorandVarnish=name.CPGraphColorVanish(Estimateid, IdItemOption,Comporderval);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

		List<String> listCompDesc = new ArrayList<String>(EstPageColorandVarnish.keySet());

		//For Front pages 
		List<String> ListFront = new ArrayList<String>();
		for (String s : listCompDesc)
		{
			//System.out.println(s);
			String FrontKey= EstPageColorandVarnish.get(s).get("FrontOrBack");
			String COV=EstPageColorandVarnish.get(s).get("ColorOrVarnish");
			if((FrontKey.equals("Front")) && COV.equals("Color"))

				ListFront.add(s);
		}
		Collections.sort(ListFront);
		//System.out.println("Front value available are as follows :- "+ListFront);
		//System.out.println(ListFront.size());
		//System.out.println("Total number of components are "+listCompDesc.size());
		if (ListFront.size()>0)
		{
			//				String xpathForProduct= "//label[text()='"+CompDesc.trim()+"']";	
			//				driver.findElement(By.xpath(xpathForProduct)).click();
			//				Thread.sleep(5000);
			driver.findElement(By.xpath("//label[text()='Colors']/parent::span//span[@class='ltv__item ltv_'][1]//img")).click();
			
			//for (int i=1;i<=ListFront.size();i++)
			//{
			

			
			int i=1;
			for(String idps:ListFront) {
				//Entering Data Front page
				int j=i-1;
				//String Val=ListFront.get(idps);
				String optDescp= EstPageColorandVarnish.get(idps).get("OptionDescription");
				String CompDesc= EstPageColorandVarnish.get(idps).get("ComponentDescription");
				String CompFinal= EstPageColorandVarnish.get(idps).get("ComponentFinal");
				String CompOrder= EstPageColorandVarnish.get(idps).get("ComponentOrder");
				String CompTypeDesc= EstPageColorandVarnish.get(idps).get("ComponentTypeDesc");
				String CharDesc= EstPageColorandVarnish.get(idps).get("CharacteristicDesc");
				String PagecolororVarnish= EstPageColorandVarnish.get(idps).get("ColorOrVarnish");
				String PageFrontorBack= EstPageColorandVarnish.get(idps).get("FrontOrBack");
				String PageMainColor= EstPageColorandVarnish.get(idps).get("MainColor");
				String PageColor= EstPageColorandVarnish.get(idps).get("Color");
				String PageNotes= EstPageColorandVarnish.get(idps).get("Notes");
				String PagePrintInput= EstPageColorandVarnish.get(idps).get("PrintInput");
				String PageAsso= EstPageColorandVarnish.get(idps).get("Association");
				String PageCoverage= EstPageColorandVarnish.get(idps).get("Coverage");
				String PageAdditionalplate= EstPageColorandVarnish.get(idps).get("AdditionalPlate");


				if (driver.findElements(By.xpath("//div[contains(@class,'dialog dialog__view')]")).size()>0)
				{
					driver.findElement(By.xpath("//div[@class='dialog__content']//div[@class='listtb']//button[1]")).click();  
					
					String Xpathformaincolor="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[starts-with(@data-index,'"+j+"/0')]";
					driver.findElement(By.xpath(Xpathformaincolor)).click();
					driver.findElement(By.xpath(Xpathformaincolor)).sendKeys(PageMainColor);
					Thread.sleep(5000);
					//driver.findElement(By.xpath("//label[text()='"+PageMainColor+"']")).click();
					String Xpathforcolor="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[starts-with(@data-index,'"+j+"/1')]";
					driver.findElement(By.xpath(Xpathforcolor)).click();
					driver.findElement(By.xpath(Xpathforcolor)).sendKeys(PageColor);
					Thread.sleep(2000);
					String XpathforCoverage="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[starts-with(@data-index,'"+j+"/2')]";
					driver.findElement(By.xpath(XpathforCoverage)).click();
					driver.findElement(By.xpath(XpathforCoverage)).sendKeys(PageCoverage+Keys.TAB);
					Thread.sleep(2000);
					String XpathforAdditionalPlate="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[starts-with(@data-index,'"+j+"/4')]";
					driver.findElement(By.xpath(XpathforAdditionalPlate)).click();
					driver.findElement(By.xpath(XpathforAdditionalPlate)).sendKeys(PageAdditionalplate+Keys.TAB);
					Thread.sleep(2000);
					String XpathforNotes="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[starts-with(@data-index,'"+j+"/5')]";
					driver.findElement(By.xpath(XpathforNotes)).click();
					driver.findElement(By.xpath(XpathforNotes)).sendKeys(PageAdditionalplate+Keys.TAB);
					Thread.sleep(2000);
					String XpathforPrintInput="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[starts-with(@data-index,'"+j+"/6')]";
					CommonFunctions.waitUntilElementisClickable(driver, By.xpath(XpathforPrintInput), 180);
					
//					driver.findElement(By.xpath(XpathforPrintInput)).click();
//					driver.findElement(By.xpath(XpathforPrintInput)).sendKeys(PagePrintInput+Keys.TAB);

					Thread.sleep(5000);

				}
				i=i+1;
			}
			driver.findElement(By.xpath("//button[@title='OK']")).click();
	
		}

		//For Back pages 
		List<String> ListBack = new ArrayList<String>();
		for (String t : listCompDesc)
		{
			//System.out.println(t);
			String FrontKey= EstPageColorandVarnish.get(t).get("FrontOrBack");
			String COVback=EstPageColorandVarnish.get(t).get("ColorOrVarnish");
			if(FrontKey.equals("Back")  && COVback.equals("Color") )

				ListBack.add(t);
		}
		Collections.sort(ListBack);
		//System.out.println("Back value available are as follows :- "+ListBack);

		if (ListBack.size()>0)
		{


			driver.findElement(By.xpath("//label[text()='Colors']/parent::span//span[@class='ltv__item ltv_ ltv_last'][1]//img")).click();		
			//for (int k=1;k<=ListBack.size();k++)
			//{
			int k=1;
			for(String idps1:ListBack) {
				//Entering Data Back page
				int l=k-1;

				//String ValBack=ListBack.get(l);
				String optDescp= EstPageColorandVarnish.get(idps1).get("OptionDescription");
				String CompDesc= EstPageColorandVarnish.get(idps1).get("ComponentDescription");
				String CompFinal= EstPageColorandVarnish.get(idps1).get("ComponentFinal");
				String CompOrder= EstPageColorandVarnish.get(idps1).get("ComponentOrder");
				String CompTypeDesc= EstPageColorandVarnish.get(idps1).get("ComponentTypeDesc");
				String CharDesc= EstPageColorandVarnish.get(idps1).get("CharacteristicDesc");
				String PagecolororVarnish= EstPageColorandVarnish.get(idps1).get("ColorOrVarnish");
				String PageFrontorBack= EstPageColorandVarnish.get(idps1).get("FrontOrBack");
				String PageMainColor= EstPageColorandVarnish.get(idps1).get("MainColor");
				String PageColor= EstPageColorandVarnish.get(idps1).get("Color");
				String PageNotes= EstPageColorandVarnish.get(idps1).get("Notes");
				String PagePrintInput= EstPageColorandVarnish.get(idps1).get("PrintInput");
				String PageAsso= EstPageColorandVarnish.get(idps1).get("Association");
				String PageCoverage= EstPageColorandVarnish.get(idps1).get("Coverage");
				String PageAdditionalplate= EstPageColorandVarnish.get(idps1).get("AdditionalPlate");


				String xpathForProduct= "//label[text()='"+CompDesc.trim()+"']";	
				//driver.findElement(By.xpath(xpathForProduct)).click();
				Thread.sleep(5000);
				

				if (driver.findElements(By.xpath("//div[starts-with(@class,'dialog dialog__view')]")).size()>0)
				{
					driver.findElement(By.xpath("//div[@class='dialog__content']//div[@class='listtb']//button[1]")).click();
					String Xpathformaincolor="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[starts-with(@data-index,'"+l+"/0')]";
					driver.findElement(By.xpath(Xpathformaincolor)).click();
					driver.findElement(By.xpath(Xpathformaincolor)).sendKeys(PageMainColor+Keys.ENTER+Keys.TAB);
					//driver.findElement(By.xpath("//label[text()='"+PageMainColor+"']")).click();
					Thread.sleep(2000);
					String Xpathforcolor="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[starts-with(@data-index,'"+l+"/1')]";
					driver.findElement(By.xpath(Xpathforcolor)).click();
					driver.findElement(By.xpath(Xpathforcolor)).sendKeys(PageColor);
					Thread.sleep(2000);
					driver.findElement(By.xpath(Xpathforcolor)).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					driver.findElement(By.xpath(Xpathforcolor)).sendKeys(Keys.TAB);
					Thread.sleep(2000);
					String XpathforCoverage="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[starts-with(@data-index,'"+l+"/2')]";
					driver.findElement(By.xpath(XpathforCoverage)).click();
					driver.findElement(By.xpath(XpathforCoverage)).sendKeys(PageCoverage+Keys.TAB);
					Thread.sleep(2000);
					String XpathforAdditionalPlate="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[starts-with(@data-index,'"+l+"/4')]";
					driver.findElement(By.xpath(XpathforAdditionalPlate)).click();
					driver.findElement(By.xpath(XpathforAdditionalPlate)).sendKeys(PageAdditionalplate+Keys.TAB);
					Thread.sleep(2000);
					String XpathforNotes="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[starts-with(@data-index,'"+l+"/5')]";
					driver.findElement(By.xpath(XpathforNotes)).click();
					driver.findElement(By.xpath(XpathforNotes)).sendKeys(PageAdditionalplate+Keys.TAB);
					Thread.sleep(2000);

				}
				k=k+1;
			}
			driver.findElement(By.xpath("//button[@title='OK']")).click();
		} 

		//For Varnish
				List<String> VarnishList = new ArrayList<String>();
				for (String varnishval : listCompDesc)
				{
				//	System.out.println(varnishval);
					String FrontKey= EstPageColorandVarnish.get(varnishval).get("FrontOrBack");
					String COVback=EstPageColorandVarnish.get(varnishval).get("ColorOrVarnish");
					if(COVback.equals("Varnish") )

						VarnishList.add(varnishval);
				}
				Collections.sort(VarnishList);
				//System.out.println("Varnish value available are :- "+VarnishList);
			      for (String CloredvalinDB:VarnishList)
			      {
			    	  
			      }
				
				if (VarnishList.size()>0)
				{
					
					
					//for(int p=1;p<=VarnishList.size();p++)
				
					int p=1;
					for(String idps2:VarnishList) {
						int q=p-1;
					  driver.findElement(By.xpath("//label[text()='Varnishes']/ancestor::div[@class='listtb']//button[1]")).click();
					  String Valueforvar=VarnishList.get(q);
					  String VarnishColor=EstPageColorandVarnish.get(Valueforvar).get("Color");
					  String VarnishCoverage=EstPageColorandVarnish.get(Valueforvar).get("Coverage");
					  String VarnishPosition=EstPageColorandVarnish.get(Valueforvar).get("FrontOrBack");
					  
					  String xpathForVarnishColor="//label[text()='Varnishes']/ancestor::div[@class='listtb']/parent::div//span[contains(@class,'grid__cell grid__cell') and (starts-with(@data-index,'"+q+"/0'))]";
					  String xpathForVarnishColortext=xpathForVarnishColor+"//input";
					  driver.findElement(By.xpath(xpathForVarnishColor)).click();
					  Thread.sleep(2000);
					  driver.findElement(By.xpath(xpathForVarnishColortext)).sendKeys(VarnishColor);
					  Thread.sleep(2000);
					  driver.findElement(By.xpath("//label[text()='Varnishes']")).click();
					  Thread.sleep(2000);
					  String xpathForVarnishCoverage="//label[text()='Varnishes']/ancestor::div[@class='listtb']/parent::div//span[contains(@class,'grid__cell grid__cell') and (starts-with(@data-index,'"+q+"/1'))]";
					  driver.findElement(By.xpath(xpathForVarnishCoverage)).click();
					  Thread.sleep(2000);
					  
					  if(VarnishCoverage.equals("100.0"))
					  {
						  driver.findElement(By.xpath("//li/label[text()='Total']")).click();
					  }
					  else
					  {
						  driver.findElement(By.xpath("//li/label[text()='Spot']")).click();
					  }
					  String xpathForPosition="//label[text()='Varnishes']/ancestor::div[@class='listtb']/parent::div//span[contains(@class,'grid__cell grid__cell') and (starts-with(@data-index,'"+q+"/2'))]";
					  driver.findElement(By.xpath(xpathForPosition)).click();
					  driver.findElement(By.xpath(xpathForPosition)).click();
					  String fr=driver.findElement(By.xpath("//span[text()='Front']//parent::li//button")).getAttribute("aria-checked");
					  String bc=driver.findElement(By.xpath("//span[text()='Back']//parent::li//button")).getAttribute("aria-checked");
					  if(VarnishPosition.equalsIgnoreCase("Front")) {
						  if(fr.equalsIgnoreCase("false")) {
							  driver.findElement(By.xpath("//span[text()='Front']")).click();
						  }
						  if(bc.equalsIgnoreCase("true")) {
							  driver.findElement(By.xpath("//span[text()='Back']")).click();
						  }
					  }
					  else if(VarnishPosition.equalsIgnoreCase("Back")){
						  if(fr.equalsIgnoreCase("true")) {
							  driver.findElement(By.xpath("//span[text()='Front']")).click();
						  }
						  if(bc.equalsIgnoreCase("false")) {
							  driver.findElement(By.xpath("//span[text()='Back']")).click();
						  }
					  }

					p=p+1;
					}
					
					
				}
				
		}
		catch(WebDriverException e)
		{
			System.out.println("Color and varnish Failed for Estimate :- "+ Estimateid);
			e.printStackTrace();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
			
		}
		catch(Exception e)
		{
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
			
	}
	@Step("Adding CPGraphRegularFormat Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2}")
	public void Charactertics_CPGraphRegularFormat(String Estimateid,  String IdItemOption,String Comporderval) throws ClassNotFoundException, SQLException, IOException, InterruptedException 
	{

		HashMap<String, HashMap<String, String>> EstPageFormat = new HashMap<String, HashMap<String, String>>();
		try {
			EstPageFormat=name.CPGraphRegularFormat(Estimateid, IdItemOption,Comporderval);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

		//System.out.println("Component is "+Comporderval);
		String OptDescp= EstPageFormat.get(Comporderval).get("OptionDescription");
		String CompDescp= EstPageFormat.get(Comporderval).get("ComponentDescription");
		String CompFinal= EstPageFormat.get(Comporderval).get("ComponentFinal");
		String CompOrder= EstPageFormat.get(Comporderval).get("ComponentOrder");
		String CompTypedescp= EstPageFormat.get(Comporderval).get("ComponentTypeDesc");
		String CharacTypeDesc= EstPageFormat.get(Comporderval).get("CharacteristicDesc");
		String FormatidPSCmpCarac= EstPageFormat.get(Comporderval).get("idPSCmpCarac");
		String PageFinishedFormatWidth= EstPageFormat.get(Comporderval).get("FinishedFormatWidth");
		String PageFinishedFormatHeight= EstPageFormat.get(Comporderval).get("FinishedFormatHeight");
		String PageFormat= EstPageFormat.get(Comporderval).get("Page");
		String FormatBothSheetsEqual= EstPageFormat.get(Comporderval).get("BothSheetsEqual");
		String PageFormatLeftFlap= EstPageFormat.get(Comporderval).get("LeftFlap");
		String PageFormatRightFlap= EstPageFormat.get(Comporderval).get("RightFlap");
		String PageFormatFlatWidth= EstPageFormat.get(Comporderval).get("FlatWidth");
		String PageFormatFlatHeight= EstPageFormat.get(Comporderval).get("FlatHeight");
		String PageFormatFlapRetreat= EstPageFormat.get(Comporderval).get("FlapRetreat");
		String PageFormatFoldingSchemeCode= EstPageFormat.get(Comporderval).get("FoldingSchemeCode");
		String PageFormatFoldingSchemeDescription= EstPageFormat.get(Comporderval).get("FoldingSchemeDescription");
		String PageFormatLines= EstPageFormat.get(Comporderval).get("Lines");
		String PageFormatLabelsacross= EstPageFormat.get(Comporderval).get("Labelsacross");
		String PageFormatBleed= EstPageFormat.get(Comporderval).get("Bleed");
		String PageFormatArrangement= EstPageFormat.get(Comporderval).get("Arrangement");
		String PageFormatApplicationType= EstPageFormat.get(Comporderval).get("ApplicationType");
		String PageFormatTopMargin= EstPageFormat.get(Comporderval).get("TopMargin");
		String PageFormatBottomMargin= EstPageFormat.get(Comporderval).get("BottomMargin");
		String PageFormatLeftMargin= EstPageFormat.get(Comporderval).get("LeftMargin");
		String PageFormatRightMargin= EstPageFormat.get(Comporderval).get("RightMargin");
		String PageFormatAllowsWhiteElementWasteInTheLayout= EstPageFormat.get(Comporderval).get("AllowsWhiteElementWasteInTheLayout");
		String PageFormatQuantityOfVersions= EstPageFormat.get(Comporderval).get("QuantityOfVersions");
		String PageFormatPosition= EstPageFormat.get(Comporderval).get("Position");
		String PageFormatSize= EstPageFormat.get(Comporderval).get("Size");

		String SFFinishedFormat=PageFinishedFormatWidth+" x "+PageFinishedFormatHeight;
		String FlatFormat=PageFormatFlatWidth+" x "+PageFinishedFormatHeight;
		Thread.sleep(5000);  
		try {	
			switch(CharacTypeDesc.toLowerCase().trim())
			{
			case "folded format (model)":
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Closed (WxH)']/parent::span/span/div/span[1]/input")).sendKeys(SFFinishedFormat+Keys.TAB);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Flat (WxH)']/parent::span/span/div/span[1]/input")).sendKeys(FlatFormat+Keys.TAB);
				break;
			case "leaf format":
			case "blank size regular":
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Size (WxH)']/parent::span/span/div/span[1]/input")).sendKeys(SFFinishedFormat+Keys.TAB);
				break;
			case "pages format":
			case "signature format":
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Finished Format (WxH)']/parent::span/span/div/span[1]/input")).sendKeys(SFFinishedFormat+Keys.TAB);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Page']/parent::span/following-sibling::span/input")).sendKeys(PageFormat);
				break;
			case "cover format":
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Closed (WxH)']/parent::span/span/div/span[1]/input")).sendKeys(SFFinishedFormat+Keys.TAB);
				if(!PageFormatLeftFlap.isEmpty()) {
					driver.findElement(By.xpath("//label[text()='Left Flap']/..//following-sibling::span[1]//child::input")).sendKeys(PageFormatLeftFlap+Keys.TAB);
				}
				if(!PageFormatRightFlap.isEmpty()) {
					driver.findElement(By.xpath("//label[text()='Right Flap']/..//following-sibling::span[1]//child::input")).sendKeys(PageFormatRightFlap+Keys.TAB);
				}
				break;
			case "end sheet format":
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Size (WxH)']/parent::span/span/div/span[1]/input")).sendKeys(SFFinishedFormat+Keys.TAB);
				String val=driver.findElement(By.xpath("//label[text()='Both sheets equal']/..")).getAttribute("aria-checked");
				if(FormatBothSheetsEqual.equalsIgnoreCase("1")) {
					if(val.equalsIgnoreCase("true")) {
						driver.findElement(By.xpath("//label[text()='Both sheets equal']/..")).click();
					}
				}
				else {
					if(val.equalsIgnoreCase("true")) {
						driver.findElement(By.xpath("//label[text()='Both sheets equal']/..")).click();
					}
				}
				break;
			
				
				
				
			default:
				System.out.println("component type is not coded : "+CompTypedescp);
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	
	}
	@Step("Adding CPGraphHotStamping Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphHotStamping(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception 
	{


		HashMap<String, HashMap<String, String>> CharCPGraphStampingItem = new HashMap<String, HashMap<String, String>>();
		try {
			CharCPGraphStampingItem=name.CPGraphHotStamping(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		List<String> listCPGraphStampingItem = new ArrayList<String>(CharCPGraphStampingItem.keySet());
		if(!(listCPGraphStampingItem.size()==0))
		{
			int z=0;
			for(String Embval:listCPGraphStampingItem)
			{

				String CPGraphStampingItemType=CharCPGraphStampingItem.get(Embval).get("Type");
				String CPGraphStampingItemHeight=CharCPGraphStampingItem.get(Embval).get("Height");
				String CPGraphStampingItemWidth=CharCPGraphStampingItem.get(Embval).get("Width");
				String CPGraphStampingItemTypeSurface=CharCPGraphStampingItem.get(Embval).get("Surface");
				String CPGraphStampingItemInputNumber=CharCPGraphStampingItem.get(Embval).get("InputNumber");
				String CPGraphStampingItemNote=CharCPGraphStampingItem.get(Embval).get("Note");
				
				//System.out.println(CPGraphStampingItemType+" "+CPGraphStampingItemHeight+" "+CPGraphStampingItemWidth+" "+CPGraphStampingItemTypeSurface+" "+CPGraphStampingItemInputNumber+" "+CPGraphStampingItemNote);


				String XpathWitCPGraphStampingItemAddButton="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//div[@class='listtb']//button[1]";
				String XpathWitCPGraphStampingItemTypeClick="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//div[@class='grid__box']//span[contains(@class,'grid__cell grid__cell') and (@data-index='"+z+"/0')]";
				String XpathWitCPGraphStampingItemType="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//div[@class='grid__box']//span[contains(@class,'grid__cell grid__cell') and (@data-index='"+z+"/0')]//input";
				String XpathWitCPGraphStampingItemHeight="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//div[@class='grid__box']//span[contains(@class,'grid__cell grid__cell') and (@data-index='"+z+"/1')]/span";	
				String XpathWitCPGraphStampingItemHeightinput="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//div[@class='grid__box']//span[contains(@class,'grid__cell grid__cell') and (@data-index='"+z+"/1')]//input";	
				String XpathWitCPGraphStampingItemWidth="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//div[@class='grid__box']//span[contains(@class,'grid__cell grid__cell') and (@data-index='"+z+"/2')]/span";	
				String XpathWitCPGraphStampingItemWidthinput="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//div[@class='grid__box']//span[contains(@class,'grid__cell grid__cell') and (@data-index='"+z+"/2')]//input";	 
				//String XpathWitCPGraphStampingItemSurface="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//div[@class='grid__box']//span[contains(@class,'grid__cell grid__cell') and (@data-index='"+z+"/3')]/span/label";	 
				//String XpathWitCPGraphStampingItemSurface="//label[text()='Hot Stamping ']/ancestor::div[@class='list__item']//div[@class='grid__box']//span[contains(@class,'grid__cell grid__cell') and (@data-index='0/3')]/span/label";
				String XpathWitCPGraphStampingItemSurface="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//div[@class='grid__box']//span[@class='grid__cell grid__cell--simplelookup grid__current ' and @data-index='"+z+"/3']";
				String XpathWitCPGraphStampingItemInputNum = "//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//div[@class='grid__box']//span[contains(@class,'grid__cell grid__cell') and (@data-index='"+z+"/4')]/span/label";
				try {
	
					//click on add new record button
					driver.findElement(By.xpath(XpathWitCPGraphStampingItemAddButton)).click();
	
					//For type
					driver.findElement(By.xpath(XpathWitCPGraphStampingItemTypeClick)).click();
					CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWitCPGraphStampingItemType, CPGraphStampingItemType);
	
					//For Weight
					driver.findElement(By.xpath(XpathWitCPGraphStampingItemWidth)).click();
					driver.findElement(By.xpath(XpathWitCPGraphStampingItemWidth)).click();
					Thread.sleep(4000);
					driver.findElement(By.xpath(XpathWitCPGraphStampingItemWidthinput)).sendKeys(CPGraphStampingItemWidth);
	
					//For Height
					driver.findElement(By.xpath(XpathWitCPGraphStampingItemHeight)).click();
				//	driver.findElement(By.xpath(XpathWitCPGraphStampingItemHeight)).click();
					
					Thread.sleep(3000);
					driver.findElement(By.xpath(XpathWitCPGraphStampingItemHeightinput)).sendKeys(CPGraphStampingItemHeight);
					driver.findElement(By.xpath(XpathWitCPGraphStampingItemHeightinput)).sendKeys(Keys.TAB);
					
					//For Surface
					if(CPGraphStampingItemTypeSurface.equals("0")) {
						CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphStampingItemSurface, "Front");
					}
						else {
							CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphStampingItemSurface, "Back");
						}
					//changing to go with index For Surface
					//CommonFunctions.selectDropdownByIndex(driver,By.xpath(XpathWitCPGraphStampingItemSurface),Integer.parseInt(CPGraphStampingItemTypeSurface));
					
	
					//for Input Number
					switch(Integer.parseInt(CPGraphStampingItemInputNumber))
					{
					
					case 1:
						CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphStampingItemInputNum, "1st Input");	    
						break;	
					case 2:
						CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphStampingItemInputNum, "2nd Input");	    
						break;	
					case 3:
						CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphStampingItemInputNum, "3rd Input");	    
						break;	
					case 4:
						CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphStampingItemInputNum, "4th Input");	    
						break;	
					case 5:
						CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphStampingItemInputNum, "5th Input");	    
						break;	
					default:
						System.out.println("Value of CPGraphStampingItemInputNumber is not selected with : "+CPGraphStampingItemInputNumber);
					}
				}
				catch(Exception e) {
					System.out.println("Failed in characteristics adding so exiting"+Estimateid);
					e.getMessage();
					AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
					Estimate.CloseEstimateTab();
					Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
				}

			}
		}

	
		
	}
	@Step("Adding CPPlant Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPPlant(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception 
	{

		HashMap<String, String> CharCPPlant = new HashMap<String, String>();
		CharCPPlant=name.CPPlant(Estimateid,IdItemOption, Comporderval, CharteristicDescp);

		String CPPlantPlant=CharCPPlant.get("Plant");
      	String XpathWithCPPlantPlant="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//span//label[text()='Plant']/parent::span/span/span/input";
    	    	
    	try {
    	CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWithCPPlantPlant, CPPlantPlant);
    	}
    	catch(Exception e) {
    		System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
    	}
    		
	
	}
	@Step("Adding CPGenericCPOptionDesc Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGenericCPOptionDesc(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception 
	{

		HashMap<String, String> CharCPGenericCPOptionDesc = new HashMap<String, String>();
		//String newCharteristicDescp=CharteristicDescp.replace("'", "''");
		CharCPGenericCPOptionDesc=name.CPGenericCPOptionDesc(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		
		String CPGenericCPOptionDesc=CharCPGenericCPOptionDesc.get("Description");
		String CPGenericCPOptionOptions=CharCPGenericCPOptionDesc.get("Options");
		String CPGenericCPOptionQuantity=CharCPGenericCPOptionDesc.get("Quantity");

		try {
			String XpathForOption="//label[text()=\""+CharteristicDescp+"\"]/ancestor::header/following-sibling::div//label[text()='Option']/parent::span/span//input";
			CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathForOption, CPGenericCPOptionOptions);
	
			String XpathForDescp="//label[text()=\""+CharteristicDescp+"\"]/ancestor::header/following-sibling::div//label[text()='Description']/parent::span/span//input";
			//CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathForDescp, CPGenericCPOptionDesc);
			CommonFunctions.Iquote_EnterDataintoTextfield(driver, XpathForDescp, CPGenericCPOptionDesc);
			
			
			String XpathForQuantity="//label[text()=\""+CharteristicDescp+"\"]/ancestor::header/following-sibling::div//label[text()='Quantity']/parent::span/span//input";
			if(driver.findElements(By.xpath(XpathForQuantity)).size()>0)
			{
			CommonFunctions.Iquote_EnterDataintoTextfield(driver, XpathForQuantity, CPGenericCPOptionQuantity);
			}
			else
			{
				System.out.println("Quantity field is not present for CPOption Desc :- "+CharteristicDescp);
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	
	}
	@Step("Adding CPNote Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPNote(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception 
	{

		HashMap<String, String> CharCPNote = new HashMap<String, String>();
		CharCPNote=name.CPNote(Estimateid,IdItemOption, Comporderval, CharteristicDescp);
		String CPNoteNote=CharCPNote.get("Note");

		String xpathforNoteText="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//div[@class='text']/textarea";
		try {
			driver.findElement(By.xpath(xpathforNoteText)).click();
			driver.findElement(By.xpath(xpathforNoteText)).sendKeys(CPNoteNote);
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	
	}
	@Step("Adding CPAGraphPageProof Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPAGraphPageProof(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception 
	{

		HashMap<String, String> CharCPAGraphPageProof	 = new HashMap<String, String>();
		CharCPAGraphPageProof=name.CPAGraphPageProof(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String CPAGraphPageProofDefinedPages=CharCPAGraphPageProof.get("DefinedPages");
		String CPAGraphPageProofDefinedFormat=CharCPAGraphPageProof.get("DefinedFormat");
		String CPAGraphPageProofNotes=CharCPAGraphPageProof.get("Notes");
		String CPAGraphPageQuantity=CharCPAGraphPageProof.get("Quantity");
		String CPAGraphPageWidth=CharCPAGraphPageProof.get("Width");
		String CPAGraphPageHeight=CharCPAGraphPageProof.get("Height");


		String xpathForDefinedpages="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Defined Pages']/parent::button/div";
		String xpathForDefinedpagesButton="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Defined Pages']/parent::button";
		String xpathForQuantity="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Quantity']/parent::span/span/input";

		String xpathForDefinedFormat="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Defined Format']/parent::button/div";
		String xpathForDefinedFormatButton="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Defined Format']/parent::button";
		String xpathForPagewh="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Page (WxH)']/parent::span/span/input";
		String PageWHval=CPAGraphPageWidth+" x "+CPAGraphPageHeight;
		String XpathForNotes="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Notes']/parent::span/span/div/textarea";
		try {
			//For Defined pages
			if (CPAGraphPageProofDefinedPages.equals("0"))
			{
				String getCheckStatus=driver.findElement(By.xpath(xpathForDefinedpagesButton)).getAttribute("aria-checked");
				if(getCheckStatus.equals("true"))
				{
					driver.findElement(By.xpath(xpathForDefinedpages)).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath(xpathForQuantity)).clear();
					driver.findElement(By.xpath(xpathForQuantity)).sendKeys(CPAGraphPageQuantity);
	
	
				}
	
	
			}
			else if(CPAGraphPageProofDefinedPages.equals("1"))
			{
				String getCheckStatus=driver.findElement(By.xpath(xpathForDefinedpagesButton)).getAttribute("aria-checked");
				if(getCheckStatus.equals("false"))
				{
					driver.findElement(By.xpath(xpathForDefinedpages)).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath(xpathForQuantity)).clear();
					driver.findElement(By.xpath(xpathForQuantity)).sendKeys(CPAGraphPageQuantity);
				}
	
			}
			//For Defined pages
	
			//For Defined Format
			if(CPAGraphPageProofDefinedFormat.equals("0"))
			{
				String getCheckStatusDF=driver.findElement(By.xpath(xpathForDefinedFormatButton)).getAttribute("aria-checked");
				if(getCheckStatusDF.equals("true"))
				{
					driver.findElement(By.xpath(xpathForDefinedFormat)).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath(xpathForPagewh)).clear();
					driver.findElement(By.xpath(xpathForPagewh)).sendKeys(PageWHval+Keys.TAB);
				}
			}
			else if(CPAGraphPageProofDefinedFormat.equals("1"))
			{
				String getCheckStatusDF=driver.findElement(By.xpath(xpathForDefinedFormatButton)).getAttribute("aria-checked");
				if(getCheckStatusDF.equals("false"))
				{
					driver.findElement(By.xpath(xpathForDefinedFormat)).click();
					Thread.sleep(2000);
	
					driver.findElement(By.xpath(xpathForPagewh)).clear();
					driver.findElement(By.xpath(xpathForPagewh)).sendKeys(PageWHval+Keys.TAB);
				}
			}
	
			//For Notes
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForNotes)).clear();
			driver.findElement(By.xpath(XpathForNotes)).sendKeys(CPAGraphPageProofNotes);
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	}
	@Step("Adding CPValue Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPValue(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception 
	{

		HashMap<String, String> CharCPvalue = new HashMap<String, String>();
		CharCPvalue=name.CPValueQTY(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String Cpvalue=CharCPvalue.get("Value");

		String XpathForComponent="//label[text()='"+CharteristicDescp+"']/ancestor::header/following-sibling::div//input";
		try {
			driver.findElement(By.xpath(XpathForComponent)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForComponent)).sendKeys(Cpvalue);
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	
	}
	@Step("Adding CPASimpleQty Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPASimpleQty(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception 
	{

		HashMap<String, String> CharCPASimpleQty = new HashMap<String, String>();
		CharCPASimpleQty=name.CPASimpleQty(Estimateid, IdItemOption,Comporderval, CharteristicDescp);


		String CPASimpleQtyQuantity=CharCPASimpleQty.get("Quantity");
		String CPASimpleQtyNote=CharCPASimpleQty.get("Note");


		
		try {

			if(CPASimpleQtyQuantity!=null) {
					// for Quantity
					String XpathWitCPASimpleQtyQuantity="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Quantity']/parent::span/span/input";
					driver.findElement(By.xpath(XpathWitCPASimpleQtyQuantity)).click();
					driver.findElement(By.xpath(XpathWitCPASimpleQtyQuantity)).clear(); 
					driver.findElement(By.xpath(XpathWitCPASimpleQtyQuantity)).sendKeys(CPASimpleQtyQuantity+Keys.TAB); 
			}
					// for Note
			if(!CPASimpleQtyNote.isEmpty()) {
					String XpathWitCPASimpleQtyNote="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span/span/div/textarea";
					driver.findElement(By.xpath(XpathWitCPASimpleQtyNote)).click();
					driver.findElement(By.xpath(XpathWitCPASimpleQtyNote)).clear(); 
					driver.findElement(By.xpath(XpathWitCPASimpleQtyNote)).sendKeys(CPASimpleQtyNote+Keys.TAB);
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	}
	@Step("Adding CPGraphFiber Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphFiber(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

		HashMap<String, String> CharCPGraphFiber = new HashMap<String, String>();
		CharCPGraphFiber=name.CPGraphFiber(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String CPGraphFiberGrainDirection=CharCPGraphFiber.get("GrainDirection");



		String XpathForGrainDirection="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Grain Direction']/parent::span//span/input";
		try {
			driver.findElement(By.xpath(XpathForGrainDirection)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForGrainDirection)).sendKeys(CPGraphFiberGrainDirection+Keys.ENTER);
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	
	}
	@Step("Adding CPGraphPackBox Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphPackBox(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception 
	{
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		HashMap<String, String> CharCPGraphPackBox = new HashMap<String, String>();
		CharCPGraphPackBox=name.CPGraphPackBox(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String CPGraphPackBoxUnitsintheBox=CharCPGraphPackBox.get("UnitsInThebox");
		String CPGraphPackBoxBoxType=CharCPGraphPackBox.get("BoxType");
		String CPGraphPackBox=CharCPGraphPackBox.get("Box");
		String CPGraphPackNotes=CharCPGraphPackBox.get("Note");
		try {
			if(CPGraphPackBoxUnitsintheBox!=null) {
				//String XpathForUnitsintheBox="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Units in the Box']/parent::span/span/input";
				String XpathForUnitsintheBox="//label[contains(text(),'Box')]/..//parent::span/..//parent::div[@class='list__item']//label[text()='Units in the Box']/..//input";
				driver.findElement(By.xpath(XpathForUnitsintheBox)).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath(XpathForUnitsintheBox)).sendKeys(CPGraphPackBoxUnitsintheBox+Keys.ENTER);
				Thread.sleep(2000);
			}
	
			
			if(CPGraphPackBoxBoxType!=null) {
			//String XpathForBoxType="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Box Type']/parent::span/span//input";
			String XpathForBoxType=	"//label[contains(text(),'Box')]/..//parent::span/..//parent::div[@class='list__item']//label[text()='Box Type']/..//input";
			WebElement Element = driver.findElement(By.xpath(XpathForBoxType));
	        //This will scroll the page till the element is found		
	        js.executeScript("arguments[0].scrollIntoView();", Element);
			driver.findElement(By.xpath(XpathForBoxType)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForBoxType)).sendKeys(CPGraphPackBoxBoxType+Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForBoxType)).sendKeys(Keys.TAB);
			}
			
			if(CPGraphPackBox.length()>0) {
				Thread.sleep(2000);
			//String XpathForBox="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Box']/parent::span/span//input";
			/*String XpathForBox=	"//label[text()='Box (Packaging)']/..//parent::span/..//parent::div[@class='list__item']//label[text()='Box']/..//input";
			driver.findElement(By.xpath(XpathForBox)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForBox)).sendKeys(CPGraphPackBox+Keys.ENTER);*/
			
			driver.findElement(By.xpath("(//label[text()='Box']//following::span[1]//span//.)[3]")).click();
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='"+CPGraphPackBox+"']")).click();
			}
			
			if(!CPGraphPackNotes.isEmpty()) {
				Thread.sleep(2000);
			//String XpathForNote="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span//span/div/textarea";
			String XpathForNote="//label[contains(text(),'Box')]/..//parent::span/..//parent::div[@class='list__item']//label[text()='Note']/..//textarea";
			driver.findElement(By.xpath(XpathForNote)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForNote)).sendKeys(CPGraphPackNotes);
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	}
	@Step("Adding CPFileList Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPFileList(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

		HashMap<String, String> CPFileList = new HashMap<String, String>();
		CPFileList=name.CPFileList(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String CPFileList_file=CPFileList.get("Files");
		//String CPFileList_notes=CPFileList.get("Notes");
		
		//write code to take files dont know how to write
		if(CPFileList_file!=null) {
			System.out.println("Need to write code");
		}
		else {
			System.out.println("No files to upload");
		}
	}
	@Step("Adding CPGraphLargeFormat Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphLargeFormat(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception 
	{
		HashMap<String, String> CPGraphLargeFormat = new HashMap<String, String>();
		CPGraphLargeFormat=name.CPGraphLargeFormat(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String FinishedFormatWidth=CPGraphLargeFormat.get("FinishedFormatWidth");
		String FinishedFormatHeight=CPGraphLargeFormat.get("FinishedFormatHeight");
		String PrintingDirection=CPGraphLargeFormat.get("PrintingDirection");
		String Note=CPGraphLargeFormat.get("Note");
		String Margins=CPGraphLargeFormat.get("Margins");
		String IrregularFit=CPGraphLargeFormat.get("IrregularFit");
		String IrregularFitCB=CPGraphLargeFormat.get("IrregularFitCB");
		
		/*String TopMargin=CPGraphLargeFormat.get("TopMargin");
		String BottomMargin=CPGraphLargeFormat.get("BottomMargin");
		String LeftMargin=CPGraphLargeFormat.get("LeftMargin");
		String RightMargin=CPGraphLargeFormat.get("RightMargin");
		String TopBleed=CPGraphLargeFormat.get("TopBleed");
		String BottomBleed=CPGraphLargeFormat.get("BottomBleed");
		String LeftBleed=CPGraphLargeFormat.get("LeftBleed");
		String RightBleed=CPGraphLargeFormat.get("RightBleed");*/
		String Splicing=CPGraphLargeFormat.get("Splicing");
		String SplicingBleed=CPGraphLargeFormat.get("SplicingBleed");
		String LayoutWidht=CPGraphLargeFormat.get("LayoutWidht");
		String LayoutHeight=CPGraphLargeFormat.get("LayoutHeight");
		String Utilization=CPGraphLargeFormat.get("Utilization");
		String Numberup=CPGraphLargeFormat.get("Numberup");
		
		try {
			if(FinishedFormatWidth!=null && FinishedFormatHeight!=null) {
				String SFFinishedFormat=FinishedFormatWidth+" x "+FinishedFormatHeight;
				driver.findElement(By.xpath("//label[text()='Finished Format (WxH)']/../span/div/span/input")).clear();
				driver.findElement(By.xpath("//label[text()='Finished Format (WxH)']/../span/div/span/input")).sendKeys(SFFinishedFormat+Keys.TAB);
			}
			if(Note!=null) {
				driver.findElement(By.xpath("//label[text()='Note']/../span/input")).sendKeys(Note+Keys.TAB);
			}
			if(PrintingDirection!=null) {
				Select dropDownObject = new Select(driver.findElement(By.xpath("//label[text()='Printing Direction']//following::span[@class='input-wraper simple-lookup2']")));
				dropDownObject.selectByValue(PrintingDirection);
			}
			
			if(Float.valueOf(Splicing).floatValue()!=0.0 && Float.valueOf(SplicingBleed).floatValue()!=0.0) {
				driver.findElement(By.xpath("//label[text()='Splicing']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Splicing']//following-sibling::span/input")).sendKeys(Splicing+Keys.TAB);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='+ Bleed']//following-sibling::span/input")).sendKeys(SplicingBleed+Keys.TAB);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@title='OK']")).click();
			}
			
			WebElement ele=driver.findElement(By.xpath("//label[text()='Margins']//parent::button"));
			String option=ele.getAttribute("aria-checked");
			if(Margins.equals("1")) {
				if(option.equalsIgnoreCase("true")) {
					
				}else {
					ele.click();
				}
			}else if(Margins.equals("0")){
				if(option.equalsIgnoreCase("true")) {
					ele.click();
				}else {
					
				}
			}
			WebElement ele1=driver.findElement(By.xpath("//label[text()='Irregular Fit']//parent::button"));
			String option1=ele1.getAttribute("aria-checked");
			String Layoutformat=LayoutWidht+" x "+LayoutHeight;
			if(IrregularFitCB.equals("1")) {
				if(option1.equalsIgnoreCase("true")) {
					driver.findElement(By.xpath("//label[text()='Layout format (WxH)']/../span/div/span/input")).sendKeys(Layoutformat+Keys.TAB);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//label[text()='Number Up']/../span/input")).sendKeys(Numberup+Keys.TAB);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//label[text()='Utilization']/../span/input")).sendKeys(Utilization+Keys.TAB);
				}else {
					ele1.click();
				}
			}else if(IrregularFitCB.equals("0")){
				if(option1.equalsIgnoreCase("true")) {
					ele1.click();
				}else {
					
				}
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
		
	}
	@Step("Adding CPGraphGiantCoupling Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void charactertics_CPGraphGiantCoupling(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {
		
	}
	@Step("Adding CPGraphBindGlue Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphBindGlue(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception 
	{

		HashMap<String, String> CharCPGraphBindGlue = new HashMap<String, String>();
		CharCPGraphBindGlue=name.CPGraphBindGlue(Estimateid, IdItemOption,Comporderval, CharteristicDescp);


		String CPGraphBindGlueGlueType=CharCPGraphBindGlue.get("GlueType");
		String CPGraphBindGlueIsSewn=CharCPGraphBindGlue.get("IsSewn");
		String CPGraphBindGlueNote=CharCPGraphBindGlue.get("Note");


		String XpathWitCPGraphBindGlueGlueType="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Glue Type']/parent::span/span/span/input";
		String XpathWitCPGraphBindGlueIsSewn="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Sewn']";
		String XpathWitCPGraphBindGlueNote="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span/span/div/textarea";
		try {
			
			// for Glue Type
	//		driver.findElement(By.xpath(XpathWitCPGraphBindGlueGlueType)).clear(); 
	//		driver.findElement(By.xpath(XpathWitCPGraphBindGlueGlueType)).sendKeys(CPGraphBindGlueGlueType+Keys.TAB); 
			//System.out.println("Glue Type is :- "+CPGraphBindGlueGlueType);
			CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWitCPGraphBindGlueGlueType, CPGraphBindGlueGlueType);
	
			// for ISSewn
			CommonFunctions.Iquote_SelectCheckbox(driver, XpathWitCPGraphBindGlueIsSewn, CPGraphBindGlueIsSewn);
			// for Note
			driver.findElement(By.xpath(XpathWitCPGraphBindGlueNote)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphBindGlueNote)).sendKeys(CPGraphBindGlueNote+Keys.TAB);
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}

	
	}
	@Step("Adding CPGraphUnfinishedFormat Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphUnfinishedFormat(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
	{
		HashMap<String, String> CharCPGraphUnfinishedFormat = new HashMap<String, String>();
		CharCPGraphUnfinishedFormat=name.CPGraphUnfinishedFormat(Estimateid,IdItemOption, Comporderval, CharteristicDescp);

		String CPGraphUnfinishedFormatWidth=CharCPGraphUnfinishedFormat.get("Width");
		String CPGraphUnfinishedFormatHeight=CharCPGraphUnfinishedFormat.get("Height");
		String CPGraphUnfinishedFormatThickness=CharCPGraphUnfinishedFormat.get("Thickness");
		String CPGraphUnfinishedFormatWeight=CharCPGraphUnfinishedFormat.get("Weight");
		String CPGraphUnfinishedFormatInventoryItemCode=CharCPGraphUnfinishedFormat.get("InventoryItemCode");
		//String CPGraphUnfinishedFormatInventoryItemDescription=CharCPGraphUnfinishedFormat.get("InventoryItemDescription");
		String CPGraphUnfinishedFormatQuantity=CharCPGraphUnfinishedFormat.get("Quantity");
		String CPGraphUnfinishedFormatNote=CharCPGraphUnfinishedFormat.get("Note");

		String PageWHval=CPGraphUnfinishedFormatWidth+" x "+CPGraphUnfinishedFormatHeight;

		String XpathWitCPGraphUnfinishedFormatSize ="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Size (WxH)']/parent::span/span/input";
		String XpathWitCPGraphUnfinishedFormatThickness="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Thickness']/parent::span/span/input";
		String XpathWitCPGraphUnfinishedFormatWeight="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Weight']/parent::span/span/input";
		String XpathWitCPGraphUnfinishedFormatInventoryItemCode="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Inventory item']/parent::span/span/span/input[1]";
		String XpathWitCPGraphUnfinishedFormatQuantity="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Quantity']/parent::span/span/input";
		String XpathWitCPGraphUnfinishedFormatNote="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span/span/div/textarea";
		try {
			//iNSERT iNVENTORY iTEM FIRST AS IT RESETS OTHER VALUES
			//FOR iNVENTORY iTEM
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatInventoryItemCode)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatInventoryItemCode)).sendKeys(CPGraphUnfinishedFormatInventoryItemCode+Keys.TAB); 
	
			// for size(WxH)
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatSize)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatSize)).sendKeys(PageWHval+Keys.TAB); 
	
	
			// for Thickness
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatThickness)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatThickness)).sendKeys(CPGraphUnfinishedFormatThickness+Keys.TAB); 
	
			// for Weight
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatWeight)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatWeight)).sendKeys(CPGraphUnfinishedFormatWeight+Keys.TAB); 
	
			// for Quantity
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatQuantity)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatQuantity)).sendKeys(CPGraphUnfinishedFormatQuantity+Keys.TAB); 
	
			// for Note
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatNote)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphUnfinishedFormatNote)).sendKeys(CPGraphUnfinishedFormatNote+Keys.TAB);
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
			
		}

	}
	@Step("Adding CPGraphBleed Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphBleed(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws ClassNotFoundException, IOException, SQLException, InterruptedException
	{
		HashMap<String, String> CharCPGraphBleed= new HashMap<String, String>();
		CharCPGraphBleed=name.CPGraphBleed(Estimateid,IdItemOption, Comporderval, CharteristicDescp);
		
		String CPGraphBleed=CharCPGraphBleed.get("Bleed");
		
		String XpathWitCPGraphBleed =" //label[text()='Bleed']/../span/input";
		try {
			if(!CPGraphBleed.isEmpty()) {
				driver.findElement(By.xpath(XpathWitCPGraphBleed)).click();
				
				//driver.findElement(By.xpath(XpathWitCPGraphBleed)).clear(); 
				Thread.sleep(1000);
				driver.findElement(By.xpath(XpathWitCPGraphBleed)).sendKeys(CPGraphBleed+Keys.TAB); 
				Thread.sleep(100);
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
		
	}
	@Step("Adding CPGraphInitialLaminating Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphInitialLaminating(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
	{
		HashMap<String, String> CharCPGraphInitialLaminating = new HashMap<String, String>();
		CharCPGraphInitialLaminating=name.CPGraphInitialLaminating(Estimateid,IdItemOption, Comporderval, CharteristicDescp);


		String CPGraphInitialLaminatingFront=CharCPGraphInitialLaminating.get("Front");
		String CPGraphInitialLaminatingBack=CharCPGraphInitialLaminating.get("Back");
		String CPGraphInitialLaminatingNote=CharCPGraphInitialLaminating.get("Note");
		String CPGraphInitialLaminatingRollWidthFront=CharCPGraphInitialLaminating.get("RollWidthFront");
		String CPGraphInitialLaminatingRollWidthBack=CharCPGraphInitialLaminating.get("RollWidthBack");


		String XpathWitCPGraphInitialLaminatingFront="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Front']/parent::span/span/span/input";
		String XpathWitCPGraphInitialLaminatingBack="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Back']/parent::span/span/span/input";
		String XpathWitCPGraphInitialLaminatingNote = "//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span/span/div/textarea";	
		String XpathWitCPGraphInitialLaminatingDetails ="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Details']/parent::button";
		String XpathWitCPGraphInitialLaminatingRollWidthFront= "//header[text()='Roll Width - Front']/parent::div/span/span/input";
		String XpathWitCPGraphInitialLaminatingRollWidthBack= "//header[text()='Roll Width - Back']/parent::div/span/span/input";
		try {
			// for Front
			CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWitCPGraphInitialLaminatingFront, CPGraphInitialLaminatingFront);
	
			// for Back
			CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWitCPGraphInitialLaminatingBack, CPGraphInitialLaminatingBack);
	
	
			// for Note
			driver.findElement(By.xpath(XpathWitCPGraphInitialLaminatingNote)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphInitialLaminatingNote)).sendKeys(CPGraphInitialLaminatingNote+Keys.TAB);
	
			// for Details
			driver.findElement(By.xpath(XpathWitCPGraphInitialLaminatingDetails)).click();
			if(driver.findElements(By.xpath("//header[text()='Details']")).size()>0)
			{
				//System.out.println("Details page is displayed");
	
				//products roll width front format
				driver.findElement(By.xpath(XpathWitCPGraphInitialLaminatingRollWidthFront)).clear(); 
				driver.findElement(By.xpath(XpathWitCPGraphInitialLaminatingRollWidthFront)).sendKeys(CPGraphInitialLaminatingRollWidthFront+Keys.TAB);
	
				//products roll width Back format
				driver.findElement(By.xpath(XpathWitCPGraphInitialLaminatingRollWidthBack)).clear(); 
				driver.findElement(By.xpath(XpathWitCPGraphInitialLaminatingRollWidthBack)).sendKeys(CPGraphInitialLaminatingRollWidthBack+Keys.TAB);
	
				Thread.sleep(2000);
	
				//click on ok button
				driver.findElement(By.xpath("//button[@title='OK']")).click();
			}
			else
			{
				System.err.println("Details page is not displayed. Please check");
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}

	}
	@Step("Adding CPGraphHardCover Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphHardCover(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
	{

		HashMap<String, String> CharCPGraphHardCover = new HashMap<String, String>();
		CharCPGraphHardCover=name.CPGraphHardCover(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		//                String CPGraphPackBoxUnitsintheBox=CharCPGraphHardCover.get("UnitsInThebox");
		//                String CPGraphPackBoxBoxType=CharCPGraphHardCover.get("BoxType");
		//                String CPGraphPackBox=CharCPGraphHardCover.get("Box");
		String CPGraphHardCoverGlueType=CharCPGraphHardCover.get("GlueType");
		String CPGraphPackNotes=CharCPGraphHardCover.get("Note");
		String CPGraphPackSewn=CharCPGraphHardCover.get("Sewn");
		String CPGraphPerfectBindRound=CharCPGraphHardCover.get("PerfectBindRound");
		String CPGraphCoverCardboard=CharCPGraphHardCover.get("CoverCardboard");
		String CPGraphChangeDefaultFoldOver=CharCPGraphHardCover.get("ChangeDefaultFoldOver");
		String CPGraphHeadadjust=CharCPGraphHardCover.get("Headadjust");
		String CPGraphFaceadjust=CharCPGraphHardCover.get("Faceadjust");
		String CPGraphFootadjust=CharCPGraphHardCover.get("Footadjust");
		String CPGraphBackingMaterial=CharCPGraphHardCover.get("BackingMaterial");

		try {

			String XpathForGlueType="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Glue Type']/parent::span/span//input";
			driver.findElement(By.xpath(XpathForGlueType)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForGlueType)).sendKeys(CPGraphHardCoverGlueType+Keys.ENTER);
	
			Thread.sleep(2000);
			String XpathForSewn="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Sewn']/parent::button/div";
			//System.out.println("Xpath For Sewn:- "+XpathForSewn);
			//System.out.println("CPGraphPackSewn is :"+CPGraphPackSewn);
			if (!CPGraphPackSewn.equals("0"))
			{
				driver.findElement(By.xpath(XpathForSewn)).click();
			}
	
			Thread.sleep(2000);
			String XpathForPerfectBindRound="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Perfect Bind Round']/parent::button/div";
			if (!CPGraphPerfectBindRound.equals("0"))
			{
				driver.findElement(By.xpath(XpathForPerfectBindRound)).click();
			}
	
			Thread.sleep(2000);
			String XpathForNote="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span/span//div/textarea";
			driver.findElement(By.xpath(XpathForNote)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForNote)).sendKeys(CPGraphPackNotes);
	
			//Clikcing on Details
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Details']/parent::button")).click();
	
			if(driver.findElements(By.xpath("//header[text()='Details']")).size()>0)
			{
				if(!CPGraphCoverCardboard.isEmpty()) {
				driver.findElement(By.xpath("//label[text()='Cover Cardboard']/parent::span//input")).click();
				driver.findElement(By.xpath("//label[text()='Cover Cardboard']/parent::span//input")).sendKeys(CPGraphCoverCardboard+Keys.ENTER);;
				}
				if(!CPGraphCoverCardboard.isEmpty()) {
				driver.findElement(By.xpath("//label[text()='Spine Cardboard']/parent::span//input")).click();
				driver.findElement(By.xpath("//label[text()='Spine Cardboard']/parent::span//input")).sendKeys(CPGraphCoverCardboard+Keys.ENTER);;
				
				driver.findElement(By.xpath("//label[text()='Joint Gap']/parent::span//input")).click();
				driver.findElement(By.xpath("//label[text()='Joint Gap']/parent::span//input")).sendKeys(CPGraphCoverCardboard);;
				}
	
				if(!CPGraphChangeDefaultFoldOver.equals("0"))
				{
					driver.findElement(By.xpath("//label[text()='Change Default Fold Over']/parent::button/div")).click(); 
				}
	
	
	
				driver.findElement(By.xpath("//label[text()='Head adjust']/parent::span//input")).click();
				driver.findElement(By.xpath("//label[text()='Head adjust']/parent::span//input")).sendKeys(CPGraphHeadadjust);
				Thread.sleep(2000);
	
				driver.findElement(By.xpath("//label[text()='Face adjust']/parent::span//input")).click();
				driver.findElement(By.xpath("//label[text()='Face adjust']/parent::span//input")).sendKeys(CPGraphFaceadjust); 
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Foot adjust']/parent::span//input")).click();
				driver.findElement(By.xpath("//label[text()='Foot adjust']/parent::span//input")).sendKeys(CPGraphFootadjust); 
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Backing Material']/parent::span//input")).click();
				driver.findElement(By.xpath("//label[text()='Backing Material']/parent::span//input")).sendKeys(CPGraphBackingMaterial); 
				driver.findElement(By.xpath("//label[text()='Backing Material']/parent::span//input")).sendKeys(Keys.ENTER);  
	
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@title='OK']")).click();
			}
			else
			{
				System.err.println("Details Page is not displayed");
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	
	}
	@Step("Adding CPGraphMaxMultiLine Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphMaxMultiLine(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
	{

		HashMap<String, String> CharCPGraphMaxMultiLine = new HashMap<String, String>();
		CharCPGraphMaxMultiLine=name.CPGraphCollection(Estimateid,IdItemOption, Comporderval, CharteristicDescp);
		String CPGraphMaxMultiLineMaximum=CharCPGraphMaxMultiLine.get("Maximum");
		try {
			String XpathForCPGraphMaxMultiLineMaximum="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Maximum']/parent::span//input";
			CommonFunctions.Iquote_EnterDataintoTextfield(driver, XpathForCPGraphMaxMultiLineMaximum, CPGraphMaxMultiLineMaximum);
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	
	}
	@Step("Adding CPGraphPackPallet Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphPackPallet(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
	{

		HashMap<String, String> CharCPGraphPackPallet = new HashMap<String, String>();
		CharCPGraphPackPallet=name.CPGraphPackPallet(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String CPGraphPackPalletUnitsInThePallet=CharCPGraphPackPallet.get("UnitsInThePallet");
		String CPGraphPackPalletMaterial=CharCPGraphPackPallet.get("Material");
		String CPGraphPackPalletNotes=CharCPGraphPackPallet.get("Note");
		
		try {
			//String XpathForUnitsInThePallet="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Units in the Pallet']/parent::span/span/input";
			String XpathForUnitsInThePallet="//label[text()='Units in the Pallet']/..//input";
			driver.findElement(By.xpath(XpathForUnitsInThePallet)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForUnitsInThePallet)).sendKeys(CPGraphPackPalletUnitsInThePallet+Keys.TAB);
	
			Thread.sleep(2000);
			//String XpathForMaterial="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Material']/parent::span/span//input";
			/*String XpathForMaterial="//label[text()='Material']/..//input";
			driver.findElement(By.xpath(XpathForMaterial)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForMaterial)).sendKeys(CPGraphPackPalletMaterial+Keys.ENTER);*/
			driver.findElement(By.xpath("(//label[text()='Material']//following::span[1]//span//.)[3]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='"+CPGraphPackPalletMaterial+"']")).click();
	
	
			Thread.sleep(2000);
			//String XpathForNote="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span//span/div/textarea";
			String XpathForNote="//label[text()='Pallet']//ancestor::div[@class='list__item']//label[text()='Note']/..//textarea";
			driver.findElement(By.xpath(XpathForNote)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathForNote)).sendKeys(CPGraphPackPalletNotes);
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	}
	@Step("Adding CPGraphPrintType Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphPrintType(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
	{

		HashMap<String, String> CharCPGraphPrintType = new HashMap<String, String>();
		CharCPGraphPrintType=name.CPGraphPrintType(Estimateid,IdItemOption, Comporderval, CharteristicDescp);


		String CPGraphPrintTypePrintingProcess=CharCPGraphPrintType.get("PrintingProcess");
       // System.out.println("Printing Process Value is :- "+CPGraphPrintTypePrintingProcess);  

		String XpathWitCPGraphPrintTypePrintingProcess="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Printing process']/parent::span/span[@class='ltv__itemcont ltv_']/span/label ";

		try {
		// for Printing Process
			CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphPrintTypePrintingProcess, CPGraphPrintTypePrintingProcess);
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}

	
	}
	@Step("Adding CPGraphPackagingStrapping Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphPackagingStrapping(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
	{
		
		HashMap<String, String> CharCPGraphPrintType = new HashMap<String, String>();
		CharCPGraphPrintType=name.CPGraphPackagingStrapping(Estimateid,IdItemOption, Comporderval, CharteristicDescp);


		String UnitsInTheStrap=CharCPGraphPrintType.get("UnitsInTheStrap");
		String Material=CharCPGraphPrintType.get("Material");
		String Application=CharCPGraphPrintType.get("Application");
		String Note=CharCPGraphPrintType.get("Note");
		try {
			if(!UnitsInTheStrap.isEmpty()) {
				driver.findElement(By.xpath("//label[text()='Units in the strap']/..//input")).clear();
				driver.findElement(By.xpath("//label[text()='Units in the strap']/..//input")).click();
				driver.findElement(By.xpath("//label[text()='Units in the strap']/..//input")).sendKeys(UnitsInTheStrap);
			}
			if(!Material.isEmpty()) {
				driver.findElement(By.xpath("//label[text()='Strap (Packaging)']//following::label[text()='Material']/..//input")).clear();
				driver.findElement(By.xpath("//label[text()='Strap (Packaging)']//following::label[text()='Material']/..//input")).click();
				driver.findElement(By.xpath("//label[text()='Strap (Packaging)']//following::label[text()='Material']/..//input")).sendKeys(Material);
			}
			if(!Application.isEmpty()) {
				driver.findElement(By.xpath("//label[text()='Strap (Packaging)']//following::label[text()='Application']/..//input")).clear();
				driver.findElement(By.xpath("//label[text()='Strap (Packaging)']//following::label[text()='Application']/..//input")).click();
				driver.findElement(By.xpath("//label[text()='Strap (Packaging)']//following::label[text()='Application']/..//input")).sendKeys(Application);
			}
			if(!Note.isEmpty()) {
				driver.findElement(By.xpath("//label[text()='Strap (Packaging)']//following::label[text()='Note']/..//div")).clear();
				driver.findElement(By.xpath("//label[text()='Strap (Packaging)']//following::label[text()='Note']/..//div")).click();
				driver.findElement(By.xpath("//label[text()='Strap (Packaging)']//following::label[text()='Note']/..//div")).sendKeys(Note);
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
         
		

	}
	@Step("Adding CPGraphGIrregFormat Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphGIrregFormat(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
	{

	HashMap<String, HashMap<String, String>> CharCPGraphGIrregFormat= new HashMap<String, HashMap<String, String>>();
	CharCPGraphGIrregFormat=name.CPGraphGIrregFormat(Estimateid,IdItemOption, Comporderval, CharteristicDescp);
	List<String> ListIrregFormat = new ArrayList<String>(CharCPGraphGIrregFormat.keySet());

	if(!(ListIrregFormat.size()==0))
	{  
		for (String valIrregFormat:ListIrregFormat)
		{

			String IrregFormatClosedWidth = CharCPGraphGIrregFormat.get(valIrregFormat).get("ClosedWidth");
			String IrregFormatClosedHeight = CharCPGraphGIrregFormat.get(valIrregFormat).get("ClosedHeight");
			String IrregFormatOpenedWidth = CharCPGraphGIrregFormat.get(valIrregFormat).get("OpenedWidth");
			String IrregFormatOpenedHeight = CharCPGraphGIrregFormat.get(valIrregFormat).get("OpenedHeight");
			String IrregFormatDieApportionment = CharCPGraphGIrregFormat.get(valIrregFormat).get("DieApportionment");
			String IrregFormatKnifeCode = CharCPGraphGIrregFormat.get(valIrregFormat).get("KnifeCode");
			String IrregFormatEditDie = CharCPGraphGIrregFormat.get(valIrregFormat).get("EditDie");
			String IrregFormatSizeWidth = CharCPGraphGIrregFormat.get(valIrregFormat).get("SizeWidth");
			String IrregFormatSizeHeight = CharCPGraphGIrregFormat.get(valIrregFormat).get("SizeHeight");
			String IrregFormatNumberUp = CharCPGraphGIrregFormat.get(valIrregFormat).get("NumberUp");
			String IrregFormatDieCutterLength = CharCPGraphGIrregFormat.get(valIrregFormat).get("DieCutterLength");
			String IrregFormatFixedDieLength = CharCPGraphGIrregFormat.get(valIrregFormat).get("FixedDieLength");
			String IrregFormatDifficulty = CharCPGraphGIrregFormat.get(valIrregFormat).get("Difficulty");
			String IrregFormatUtilization = CharCPGraphGIrregFormat.get(valIrregFormat).get("Utilization");
			String IrregFormatDoNotChargeForDie = CharCPGraphGIrregFormat.get(valIrregFormat).get("DoNotChargeForDie");
			String IrregFormatInverted = CharCPGraphGIrregFormat.get(valIrregFormat).get("Inverted");
			String IrregFormatFixNumberUp = CharCPGraphGIrregFormat.get(valIrregFormat).get("FixNumberUp");
			String IrregFormatHasADifferentSlot = CharCPGraphGIrregFormat.get(valIrregFormat).get("HasADifferentSlot");
			String IrregFormatAutoGenerated = CharCPGraphGIrregFormat.get(valIrregFormat).get("AutoGenerated");
			String IrregFormatNotes = CharCPGraphGIrregFormat.get(valIrregFormat).get("Notes");
			String IrregFormatDieSizeWidth = CharCPGraphGIrregFormat.get(valIrregFormat).get("DieSizeWidth");
			String IrregFormatDieSizeHeight = CharCPGraphGIrregFormat.get(valIrregFormat).get("DieSizeHeight");
			String IrregFormatOriginalNumberUp = CharCPGraphGIrregFormat.get(valIrregFormat).get("OriginalNumberUp");
			String IrregFormatOriginalDieLength = CharCPGraphGIrregFormat.get(valIrregFormat).get("OriginalDieLength");

			String ValueforClosedwh=IrregFormatClosedWidth+" x "+IrregFormatClosedHeight;
			String ValueforFlatwh=IrregFormatOpenedWidth+" x "+IrregFormatOpenedHeight;
			String ValueforSize=IrregFormatSizeWidth+" x "+IrregFormatSizeHeight;

			String xpathForClosedWh="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Closed (WxH)']/parent::span/span[@class='ltv__itemcont ltv_']//input";
			String xpathForFlatWh="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Flat (WxH)']/parent::span/span[@class='ltv__itemcont ltv_']//input";
			String xpathFormatDieWh="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Format Die layout (WxH)']/parent::span/span[@class='ltv__itemcont ltv_']//input";
			String xpathForQuantityIntheDieLayout="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Quantity in the die layout']/parent::span[@class='ltv__item ltv_']/following-sibling::span/input";
			String xpathForDieApportionment="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Die Apportionment']/parent::span//input";
			try {
				CommonFunctions.Iquote_EnterDataintoTextfield(driver, xpathForClosedWh, ValueforClosedwh);
				CommonFunctions.Iquote_EnterDataintoTextfield(driver, xpathForFlatWh, ValueforFlatwh);
				CommonFunctions.Iquote_EnterDataintoTextfield(driver, xpathFormatDieWh, ValueforFlatwh);
				CommonFunctions.Iquote_EnterDataintoTextfield(driver, xpathForDieApportionment, IrregFormatDieApportionment);
	
				driver.findElement(By.xpath("//label[text()='Fit options']/parent::button")).click();
	
					if(driver.findElements(By.xpath("//header[text()='Fit options']")).size()>0)
					{
						//System.out.println("Fit Option Window is displayed");
						Thread.sleep(3000);
						driver.findElement(By.xpath("//label[text()='New']/parent::button")).click();
						if(driver.findElements(By.xpath("//label/b[text()='New']")).size()>0)
						{
							//System.out.println("New Window is displayed");
							CommonFunctions.Iquote_EnterDataintoTextfield(driver, "//label[text()='Fit format (wxh)']/parent::span/span/div//input", ValueforSize);
							CommonFunctions.Iquote_EnterDataintoTextfield(driver, "//label[contains(text(),'Die cutter length')]/parent::span/span//input", IrregFormatDieCutterLength);
							CommonFunctions.Iquote_EnterDataintoTextfield(driver, "//label[text()='Number Up']/parent::span/span//input", IrregFormatNumberUp);
							CommonFunctions.Iquote_EnterDataintoTextfield(driver, "//label[text()='Utilization']/parent::span/span//input", IrregFormatUtilization);
							CommonFunctions.Iquote_EnterDataintoTextfield(driver, "//label[text()='Knife code']/parent::span/span//input", IrregFormatKnifeCode);
							CommonFunctions.Iquote_EnterDataintoTextfield(driver, "//label[text()='Notes']/parent::span/span//input", IrregFormatNotes);
							CommonFunctions.Iquote_SelectFromDropdown_Text(driver, "//label[text()='Difficulty']/parent::span/span//input", IrregFormatDifficulty);
							CommonFunctions.Iquote_SelectCheckbox(driver, "//span[@class='ltv__itemcont ltv_']//label[text()='Inverted']", IrregFormatInverted);
							CommonFunctions.Iquote_SelectCheckbox(driver, "//span[@class='ltv__itemcont ltv_']//label[text()='Fixed Die Length']", IrregFormatFixedDieLength);
							CommonFunctions.Iquote_SelectCheckbox(driver, "//span[@class='ltv__itemcont ltv_']//label[text()='Do not charge for die']", IrregFormatDoNotChargeForDie);
							driver.findElement(By.xpath("//button[@title='Confirm']")).click();
							//CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='New']"), 10000);
							List <WebElement> options = driver.findElements(By.xpath("//span[@title='Knife code']//..//..//following-sibling::div[@role='grid']//span[contains(@data-index,'/0')]"));
							int size=options.size();
							String xpath="(//span[@title='Knife code']//..//..//following-sibling::div[@role='grid']//span[contains(@data-index,'/0')])["+(size-1)+"]";
							driver.findElement(By.xpath(xpath)).click();
							Thread.sleep(5000);
							if(size>1)
								driver.findElement(By.xpath("(//label[text()='Create Die']//following::button)[1]")).click();
						}
						else
						{
							System.err.println("New Window is not displayed Please Investigate"); 
						}
						//Selecting Newly added Coloum
						Thread.sleep(5000);
						/*int TotalColum=driver.findElements(By.xpath("//header[text()='Fit options']/parent::div//span[contains(@class,'grid__cell grid__cell')]")).size();
						String DataColum=driver.findElement(By.xpath("//header[text()='Fit options']/parent::div//span[contains(@class,'grid__cell grid__cell')]["+TotalColum+"]")).getAttribute("data-row");
						//header[text()='Fit options']/parent::div//span[contains(@class,'grid__cell grid__cell') and @data-index='2/0']
						
						int AllRowCount= Integer.parseInt(DataColum);
						 System.out.println("Total Coloum :- "+DataColum);
					     System.out.println("Irregular Format Deleting unwanted Rows");
						for(int Rowcountval=0;Rowcountval<AllRowCount;Rowcountval++)
						{
							CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//header[text()='Fit options']/parent::div//span[contains(@class,'grid__cell grid__cell') and @data-index='0/1']"), 180);
							CommonFunctions.ClickElement(driver, By.xpath("//header[text()='Fit options']/parent::div//span[contains(@class,'grid__cell grid__cell') and @data-index='0/1']"));
							Thread.sleep(2000);
							driver.findElement(By.xpath("//header[text()='Fit options']/parent::div//span[contains(@class,'grid__cell grid__cell') and @data-index='0/1']")).click();
							Thread.sleep(2000);
							driver.findElement(By.xpath("//header[text()='Fit options']/parent::div//div[@class='listtb']//button[4]")).click();
							Thread.sleep(2000);	
						}*/
						
		//				driver.findElement(By.xpath("//header[text()='Fit options']/parent::div//span[contains(@class,'grid__cell grid__cell') and @data-index='"+DataColum+"/0']")).click();
		//				Thread.sleep(2000);
						driver.findElement(By.xpath("//button[@title='OK']")).click();   
						CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//span[text()='Specification']"), 1000);
					}
					else
					{
						System.err.println("Fit Option Window is not displayed please investigate");
					}
				}
				catch(Exception e) {
					System.out.println("Failed in characteristics adding so exiting"+Estimateid);
					e.getMessage();
					AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
					Estimate.CloseEstimateTab();
					Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
				}
			}

		}
	
	}
	@Step("Adding CPGraphLabelFormat Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphLabelFormat(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
	{

		HashMap<String, String> CharCPGraphLabelFormat = new HashMap<String, String>();
		CharCPGraphLabelFormat=name.CPGraphLabelFormat(Estimateid, IdItemOption,Comporderval, CharteristicDescp);


		String CPGraphLabelFormatLabelType=CharCPGraphLabelFormat.get("LabelType");
		String CPGraphLabelFormatDeliverytype=CharCPGraphLabelFormat.get("Deliverytype");
		String CPGraphLabelFormatFormatWidth=CharCPGraphLabelFormat.get("FormatWidth");
		String CPGraphLabelFormatFormatHeight=CharCPGraphLabelFormat.get("FormatHeight");
		String CPGraphLabelFormatColumns=CharCPGraphLabelFormat.get("Columns");
		String CPGraphLabelFormatGapacross=CharCPGraphLabelFormat.get("Gapacross");
		String CPGraphLabelFormatGapDown=CharCPGraphLabelFormat.get("GapDown");
		String CPGraphLabelFormatKisscut=CharCPGraphLabelFormat.get("Kisscut");
		String CPGraphLabelFormatspecialdiecut=CharCPGraphLabelFormat.get("SpecialDiecut");
		String CPGraphLabelFormatTrimmargin=CharCPGraphLabelFormat.get("Trimmargin");
		
		//double width=Math.round(Double.parseDouble(CPGraphLabelFormatFormatWidth));
		//double height=Math.round(Double.parseDouble(CPGraphLabelFormatFormatHeight));
		String PageWHval=CPGraphLabelFormatFormatWidth+" x "+CPGraphLabelFormatFormatHeight;	

		String XpathWitCPGraphLabelFormatSize="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Format (WxH)']/parent::span//span/input";
		String XpathWitCPGraphLabelFormatColumn = "//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Columns']/parent::span/span/input";	
		String XpathWitCPGraphLabelFormatGapacross= "//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Gap across']/parent::span/span/input";
		String XpathWitCPGraphLabelFormatGapDown= "//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Gap down']/parent::span/span/input";	
		String XpathWitCPGraphLabelFormatKisscut =" //label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Kiss cut']";
		String XpathforSpecialdiecutting=" //label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Special die cutting']";
		String XpathforTrimmargin=" //label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Trim margin']";
		String XpathWitCPGraphLabelFormatLabelType="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Label Type']/parent::span//input";
		String XpathWitCPGraphLabelFormatDeliverytype ="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Delivery type']/parent::span//input";
		try {
				
			
			//for LabelType
			//CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWitCPGraphLabelFormatLabelType, CPGraphLabelFormatLabelType);
			driver.findElement(By.xpath("(//label[text()='Label Type']//following::span[1]//span//.)[3]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='"+CPGraphLabelFormatLabelType+"']")).click();
			
			
			//CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWitCPGraphLabelFormatDeliverytype, CPGraphLabelFormatDeliverytype);
			//driver.findElement(By.xpath("(//label[text()='Delivery type']//following::span[1]//span//.)[3]")).click();
			//Thread.sleep(2000);
			//driver.findElement(By.xpath("//label[text()='"+CPGraphLabelFormatDeliverytype+"']")).click();
			
			// for Format Size
			driver.findElement(By.xpath(XpathWitCPGraphLabelFormatSize)).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(XpathWitCPGraphLabelFormatSize)).sendKeys(Keys.CONTROL+"a"+Keys.DELETE);
			Thread.sleep(1000);
			driver.findElement(By.xpath(XpathWitCPGraphLabelFormatSize)).sendKeys(PageWHval+Keys.TAB);
			Thread.sleep(2000);
			// for Column
			if(driver.findElements(By.xpath(XpathWitCPGraphLabelFormatColumn)).size()>0) {
				driver.findElement(By.xpath(XpathWitCPGraphLabelFormatColumn)).sendKeys(Keys.DELETE);
				Thread.sleep(2000);
				driver.findElement(By.xpath(XpathWitCPGraphLabelFormatColumn)).sendKeys(CPGraphLabelFormatColumns+Keys.TAB);
			}
			
			Thread.sleep(2000);
			// for GapAcross
			if(driver.findElements(By.xpath("//label[text()='Gap accross']//following::span[1]//input")).size()>0) {
				driver.findElement(By.xpath("//label[text()='Gap accross']//following::span[1]//input")).clear(); 
				driver.findElement(By.xpath("//label[text()='Gap accross']//following::span[1]//input")).sendKeys(CPGraphLabelFormatGapacross+Keys.TAB);
				Thread.sleep(2000);
			}
			// for GapDown
			if(driver.findElements(By.xpath("//label[text()='Gap down']//following::span[1]//input")).size()>0) {
				driver.findElement(By.xpath("//label[text()='Gap down']//following::span[1]//input")).clear(); 
				driver.findElement(By.xpath("//label[text()='Gap down']//following::span[1]//input")).sendKeys(CPGraphLabelFormatGapDown+Keys.TAB+Keys.TAB);
			}
			Thread.sleep(2000);
			if(driver.findElements(By.xpath(XpathWitCPGraphLabelFormatKisscut)).size()>0)
				CommonFunctions.Iquote_SelectCheckbox(driver, XpathWitCPGraphLabelFormatKisscut, CPGraphLabelFormatKisscut);
			if(driver.findElements(By.xpath(XpathforSpecialdiecutting)).size()>0)
				CommonFunctions.Iquote_SelectCheckbox(driver, XpathforSpecialdiecutting, CPGraphLabelFormatspecialdiecut);
			if(driver.findElements(By.xpath(XpathforTrimmargin)).size()>0)
				CommonFunctions.Iquote_SelectCheckbox(driver, XpathforTrimmargin, CPGraphLabelFormatTrimmargin);
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	 			 			 
	
	}
	@Step("Adding CPGenericCPOption Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGenericCPOption(String Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
	{

		HashMap<String, String> CharCPGenericCPOption = new HashMap<String, String>();
		String XpathForComponent=null;
		CharCPGenericCPOption=name.CPGenericCPOption(Estimateid,IdItemOption, Comporderval, CharteristicDescp);
		String CpGenericvalue=CharCPGenericCPOption.get("Description");
		if(CharteristicDescp.equalsIgnoreCase("Shape"))
			 XpathForComponent="//label[text()='Shape']/ancestor::header/following-sibling::div//span[@class='input-wraper islookup']//input";
		else
			XpathForComponent="//label[text()='"+CharteristicDescp+"']/ancestor::header/following-sibling::div//input";
		try {
			
		
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement Element = driver.findElement(By.xpath(XpathForComponent));
			js.executeScript("arguments[0].scrollIntoView();", Element);	
			 
			driver.findElement(By.xpath(XpathForComponent)).click();
			Thread.sleep(2000);
			try {
			driver.findElement(By.xpath(XpathForComponent)).sendKeys(CpGenericvalue);
			driver.findElement(By.xpath(XpathForComponent)).sendKeys(Keys.ENTER);
			}catch(Exception e) {
				e.toString();
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}

	
	}
	@Step("Adding CPAOptionQty Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPAOptionQty(String Estimateid, String IdItemOption,String Comporderval, String CharteristicDescp) throws Exception
	{

		HashMap<String, String> CharCPAOptionQty = new HashMap<String, String>();
		CharCPAOptionQty =name.CPAOptionQty(Estimateid, IdItemOption,Comporderval, CharteristicDescp);


		String CPAOptionQtyOptionDesc=CharCPAOptionQty .get("OptionDesc");
		String CPAOptionQtyNote=CharCPAOptionQty.get("Note");
		String CPAOptionQtyQuantity=CharCPAOptionQty.get("Quantity");
	
		
		
		String XpathWitCPAOptionQtyOptionDesc="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Option']/parent::span//input";
		String XpathWitCPAOptionQtyQuantity="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Quantity']/parent::span//input";
		String XpathWitCPAOptionNote="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Note']/parent::span/span/div/textarea";
		
		try {
			//for Option
			CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWitCPAOptionQtyOptionDesc, CPAOptionQtyOptionDesc);
			
		
			// for Quantity
			if(!CPAOptionQtyQuantity.isEmpty()) {
				driver.findElement(By.xpath(XpathWitCPAOptionQtyQuantity)).click();
				driver.findElement(By.xpath(XpathWitCPAOptionQtyQuantity)).sendKeys(Keys.CONTROL+"a"+Keys.DELETE);
				driver.findElement(By.xpath(XpathWitCPAOptionQtyQuantity)).sendKeys(CPAOptionQtyQuantity+Keys.TAB);
			}
			Thread.sleep(2000);
			
			// for Note
			if(!CPAOptionQtyNote.isEmpty()) {
				driver.findElement(By.xpath(XpathWitCPAOptionNote)).clear(); 
				driver.findElement(By.xpath(XpathWitCPAOptionNote)).sendKeys(CPAOptionQtyNote+Keys.TAB);
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	}
	@Step("Adding CPGraphDieCut Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphDieCut(String Estimateid, String IdItemOption,String Comporderval, String CharteristicDescp) throws Exception
	{

		HashMap<String, String> CharCPGraphDieCut = new HashMap<String, String>();
		CharCPGraphDieCut=name.CPGraphDieCut(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String CPGraphDieCutWithWasteStripping=CharCPGraphDieCut.get("WithWasteStripping");
		String CPGraphDieCutNeedtrimming=CharCPGraphDieCut.get("Needtrimming");
		String CPGraphDieCutIsKnifeFormatInformed=CharCPGraphDieCut.get("IsKnifeFormatInformed");
		String CPGraphDieCutExistingDie=CharCPGraphDieCut.get("ExistingDie");
		String CPGraphDieCutInformedcuttinglength=CharCPGraphDieCut.get("Informedcuttinglength");
		String CPGraphDieCutDifficulty=CharCPGraphDieCut.get("Difficulty");
		String CPGraphDieCutNotes=CharCPGraphDieCut.get("Notes");



		String XpathWithWasteStripping = "//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='With waste stripping']/parent::button/div ";
		String XpathWithWasteStrippingButton = "//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='With waste stripping']/parent::button ";
		String XpathWithNeedTrimming = "//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Need trimming']/parent::button/div ";
		String XpathWithNeedTrimmingButton = "//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Need trimming']/parent::button ";
		String XpathWitCPGraphDieCutNotes="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span/span/div/textarea ";
		String XpathWitCPGraphDieCutDetails="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Details']/parent::button ";
		String XpathWithCPGraphDifficulty="//label[text()='Difficulty']/parent::span/span/span/input";	
		//For Waste Stripping
		try {
			if(CPGraphDieCutWithWasteStripping.equals("0"))
			{
				String StrippingButtonValue=driver.findElement(By.xpath(XpathWithWasteStrippingButton)).getAttribute("aria-checked");
				if(StrippingButtonValue.equals("true"))
				{
					driver.findElement(By.xpath(XpathWithWasteStripping)).click();
				}
	
			}
			else if(CPGraphDieCutWithWasteStripping.equals("1"))
			{
				String StrippingButtonValue=driver.findElement(By.xpath(XpathWithWasteStrippingButton)).getAttribute("aria-checked");
				if(StrippingButtonValue.equals("false"))
				{
					driver.findElement(By.xpath(XpathWithWasteStripping)).click();
				}
	
	
				//for  need trimming
				if(CPGraphDieCutNeedtrimming.equals("0"))
				{
					String NeedTrimmingButtonValue=driver.findElement(By.xpath(XpathWithNeedTrimmingButton)).getAttribute("aria-checked");
					if(StrippingButtonValue.equals("true"))
					{
						driver.findElement(By.xpath(XpathWithNeedTrimming)).click();
					}
	
				}
				else if(CPGraphDieCutNeedtrimming.equals("1"))
				{
					String NeedTrimmingButtonValue=driver.findElement(By.xpath(XpathWithNeedTrimmingButton)).getAttribute("aria-checked");
					if(StrippingButtonValue.equals("false"))
					{
						driver.findElement(By.xpath(XpathWithNeedTrimming)).click();
					}
				}
			}
	
			driver.findElement(By.xpath(XpathWitCPGraphDieCutNotes)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphDieCutNotes)).sendKeys(CPGraphDieCutNotes);
	
			//for details
			driver.findElement(By.xpath(XpathWitCPGraphDieCutDetails)).click();
			CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//header[text()='Details']"), 1200);
	
			if(driver.findElements(By.xpath("//header[text()='Details']")).size()>0)
			{
			//for informed die format
			if(CPGraphDieCutIsKnifeFormatInformed.equals("1"))
			{
				driver.findElement(By.xpath("//label[text()='Informed die format']/ancestor::button/div")).click();
			}
	        Thread.sleep(2000);
		//	CommonFunctions.Iquote_SelectCheckbox(driver, "//label[text()='Informed die format']", CPGraphDieCutIsKnifeFormatInformed);
			//for Existing die
			if(CPGraphDieCutExistingDie.equals("1"))
			{
				driver.findElement(By.xpath("//label[text()='Existing die']/ancestor::button/div")).click();
			}
			
		//	CommonFunctions.Iquote_SelectCheckbox(driver, "//label[text()='Existing die']", CPGraphDieCutExistingDie);
	//		//for Informed cutting length
			if(CPGraphDieCutInformedcuttinglength.equals("1"))
			{
				driver.findElement(By.xpath("//label[text()='Informed cutting length']/ancestor::button/div")).click();
			}
			//for difficulty
		//	CommonFunctions.Iquote_SelectCheckbox(driver, "//label[text()='Informed cutting length']", CPGraphDieCutInformedcuttinglength);
			
			driver.findElement(By.xpath(XpathWithCPGraphDifficulty)).clear();
			driver.findElement(By.xpath(XpathWithCPGraphDifficulty)).sendKeys(CPGraphDieCutDifficulty);
			Thread.sleep(2000);
			driver.findElement(By.xpath(XpathWithCPGraphDifficulty)).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//button[@title='OK']")).click();
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	}
	@Step("Adding CPGraphWireOBind Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGraphWireOBind(String Estimateid, String IdItemOption, String Comporderval,String CharteristicDescp) throws Exception
	{

		HashMap<String, String> CharCPGraphWireOBind = new HashMap<String, String>();
		CharCPGraphWireOBind=name.CPGraphWireOBind(Estimateid, IdItemOption,Comporderval, CharteristicDescp);


		String CPGraphWireOBindPosition=CharCPGraphWireOBind.get("Position");
		String CPGraphWireOBindColor=CharCPGraphWireOBind.get("Color");
		String CPGraphWireOBindWithHanger=CharCPGraphWireOBind.get("WithHanger");
		String CPGraphWireOBindNote=CharCPGraphWireOBind.get("Note");
		String CPGraphWireOBindProductRingsQuantity=CharCPGraphWireOBind.get("ProductRingsQuantity");
		String CPGraphWireOBindSeperationBetweenRings=CharCPGraphWireOBind.get("SeperationBetweenRings");
		String CPGraphWireOBindInchesOfWireO=CharCPGraphWireOBind.get("InchesOfWireO");


		String XpathWitCPGraphWireOBindPosition="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Position']/parent::span/span[@class='ltv__itemcont ltv_']/span/label ";
		String XpathWitCPGraphWireOBindColor ="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//label[text()='Color']/parent::span/span/span/input";
		String XpathWitCPGraphWireOBindWithHanger ="//label[text()='Wire-o Binding']/..//ancestor::div[@class='list__item']//label[text()='With hanger']";
		String XpathWitCPGraphWireOBindNote = "//label[text()='Wire-o Binding']/..//ancestor::div[@class='list__item']//label[text()='Note']/..//textarea";	
		String XpathWitCPGraphWireOBindDetails="//label[text()='Wire-o Binding']/..//ancestor::div[@class='list__item']//label[text()='Details']/.//parent::button";
		String XpathWitCPGraphWireOBindProductRings ="//label[text()='Product rings quantity']/..//input";
		String XpathWitCPGraphWireOBindSeperationBetweenRings= "//label[text()='Separation between Rings']/..//span[@class='input-wraper simple-lookup2']";
		String XpathWitCPGraphWireOBindInchesOfWireO= "//label[text()='Inches of Wire-O']/..//span[@class='input-wraper islookup']";
		try {
			
		
			// for Position
			CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphWireOBindPosition, CPGraphWireOBindPosition); 
	
			// for color
			CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWitCPGraphWireOBindColor, CPGraphWireOBindColor);
	
			//for with Hanger
			CommonFunctions.Iquote_SelectCheckbox(driver, XpathWitCPGraphWireOBindWithHanger, CPGraphWireOBindWithHanger);
	
			// for Note
			driver.findElement(By.xpath(XpathWitCPGraphWireOBindNote)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphWireOBindNote)).sendKeys(CPGraphWireOBindNote+Keys.TAB);
	
			// for Details
			driver.findElement(By.xpath(XpathWitCPGraphWireOBindDetails)).click();
			if(driver.findElements(By.xpath("//header[text()='Details']")).size()>0)
			{
				System.out.println("Details page is displayed");
	
				//products rings quantity
				driver.findElement(By.xpath(XpathWitCPGraphWireOBindProductRings)).clear(); 
				driver.findElement(By.xpath(XpathWitCPGraphWireOBindProductRings)).sendKeys(CPGraphWireOBindProductRingsQuantity+Keys.TAB);
	
				//Separation between rings
				CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphWireOBindSeperationBetweenRings, CPGraphWireOBindSeperationBetweenRings); 
				
				// for inches of wire
				//CommonFunctions.Iquote_SelectFromDropdown(driver, XpathWitCPGraphWireOBindInchesOfWireO, CPGraphWireOBindInchesOfWireO);
				if(!CPGraphWireOBindInchesOfWireO.isEmpty()) {
					driver.findElement(By.xpath(XpathWitCPGraphWireOBindInchesOfWireO)).sendKeys(CPGraphWireOBindInchesOfWireO+Keys.TAB);
				}
				Thread.sleep(2000);
	
				//click on ok button
				driver.findElement(By.xpath("//button[@title='OK']")).click();
			}
			else
			{
				System.err.println("Details page is not displayed. Please check");
			}
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	
	}
	@Step("Adding CPGenericRawMaterial Charateristics of an Estimate  : {0} having OptionID: {1} for the component : {2} with Description : {3}")
	public void Charactertics_CPGenericRawMaterial(String Estimateid, String IdItemOption, String Comporderval, String CharteristicDescp) throws Exception
	{

		HashMap<String, String> CharCPGenericRawMateriald = new HashMap<String, String>();
		CharCPGenericRawMateriald=name.CharCPGenericRawMaterial(Estimateid,IdItemOption, Comporderval, CharteristicDescp);
		String Materialval=CharCPGenericRawMateriald.get("RawMaterial");
		String RMValue=CharCPGenericRawMateriald.get("Value");
		String RMDescription=CharCPGenericRawMateriald.get("Description");

		String XpathForRawmaterial="//label[contains(text(),'Material Purchased')]//ancestor::header//following-sibling::div//label[text()='Raw Material']/..//input";
		String XpathForRMValue="//label[contains(text(),'Material Purchased')]//ancestor::header//following-sibling::div//label[text()='Value']/..//input";
		String XpathForRMDescription="//label[contains(text(),'Material Purchased')]//ancestor::header//following-sibling::div//label[text()='Description']/..//input";
		try {
			//For Raw material Field
			CommonFunctions.SendValue(driver, By.xpath(XpathForRawmaterial), Materialval);
			Thread.sleep(2000);
			CommonFunctions.SendValue(driver, By.xpath(XpathForRMValue), RMValue);
			Thread.sleep(2000);
			CommonFunctions.SendValue(driver, By.xpath(XpathForRMDescription), RMDescription);
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("Failed in characteristics adding so exiting"+Estimateid);
			e.getMessage();
			AllureLogger.markStepAsFailed(driver, "Failed in characteristics adding so exiting"+Estimateid);
			Estimate.CloseEstimateTab();
			Assert.assertEquals("Failed in characteristics adding so exiting", Estimateid);
		}
	
	
	}
	
	
}
