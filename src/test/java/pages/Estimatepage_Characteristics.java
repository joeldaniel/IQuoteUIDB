package pages;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.Testbase;
import utilities.CommonFunctions;
import utilities.ReadData;

public class Estimatepage_Characteristics extends Testbase {
	
	ReadData name = new ReadData();
	private Object valueofClosedWidth;
	public void Charactertics_CPGraphMedia(int Estimateid,String IdItemOption, String Comporderval) throws ClassNotFoundException, IOException, SQLException
	{
		HashMap<String, HashMap<String, String>> EstPageSpec = new HashMap<String, HashMap<String, String>>();

		//EstPageSpec=name.Papegetpec(Estimateid, Comporderval); 
		EstPageSpec=name.CPGraphMedia(Estimateid,IdItemOption, Comporderval);
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
			System.out.println("Grammage is :- "+Grammage1);
			driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).click();
			//CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("NE_Grammage")), Grammage1);
			 driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).sendKeys(Grammage1);
			driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).sendKeys(Keys.DOWN);
			Thread.sleep(2000);
			driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).sendKeys(Keys.TAB);

			Thread.sleep(3000);
			driver.findElement(By.xpath("//label[text()='"+CharacTypeDesc+"']/ancestor::div[@class='list__item']//label[text()='Details']/parent::button")).click();

			if (driver.findElements(By.xpath("//header[text()='Details']")).size()>0)
			{   CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='Format']/parent::span//span[@class='ltv__itemcont ltv_']/input"), 180);
				System.out.println("Details page is displayed");
				driver.findElement(By.xpath("//label[text()='Format']/parent::span//span[@class='ltv__itemcont ltv_']/input")).sendKeys("");
				driver.findElement(By.xpath("//label[text()='Grain Direction']/parent::span//span[@class='ltv__itemcont ltv_']/span[@class='input-wraper simple-lookup2']")).click();

				if (PageGrainDirection!=null || PageGrainDirection!="")
				{
					String XpathforgrainDirection="//div[@class='drop-down']//label[text()='"+PageGrainDirection+"']"; 
					driver.findElement(By.xpath(XpathforgrainDirection)).click();
				}

				//MOre Options
				driver.findElement(By.xpath("//label[text()='More Options']/parent::div//button[1]")).click();

				driver.findElement(By.xpath("//button[@title='OK']")).click();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Charactertics_CPGraphBindStitch(int Estimateid, String IdItemOption,String Comporderval, String CharteristicDescp) throws Exception
	{
		
		HashMap<String, String> CharCPGraphBindStitch = new HashMap<String, String>();
		CharCPGraphBindStitch=name.CPGraphBindStitch(Estimateid, IdItemOption,Comporderval, CharteristicDescp);


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
	public void Charactertics_CPGraphColorVanish(int Estimateid,  String IdItemOption,String Comporderval) throws ClassNotFoundException, IOException, SQLException {

		try
		{
		HashMap<String, HashMap<String, String>> EstPageColorandVarnish = new HashMap<String, HashMap<String, String>>();
		EstPageColorandVarnish=name.CPGraphColorVanish(Estimateid, IdItemOption,Comporderval);

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
		System.out.println("Front value available are as follows :- "+ListFront);
		System.out.println(ListFront.size());
		System.out.println("Total number of components are "+listCompDesc.size());
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


				if (driver.findElements(By.xpath("//div[@class='dialog dialog__view']")).size()>0)
				{
					driver.findElement(By.xpath("//div[@class='dialog__content']//div[@class='listtb']//button[1]")).click();
					String Xpathformaincolor="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+j+"/0']";
					driver.findElement(By.xpath(Xpathformaincolor)).click();
					driver.findElement(By.xpath(Xpathformaincolor)).sendKeys(PageMainColor);
					Thread.sleep(5000);
					//driver.findElement(By.xpath("//label[text()='"+PageMainColor+"']")).click();
					String Xpathforcolor="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+j+"/1']";
					driver.findElement(By.xpath(Xpathforcolor)).click();
					driver.findElement(By.xpath(Xpathforcolor)).sendKeys(PageColor);
					Thread.sleep(2000);
					String XpathforCoverage="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+j+"/2']";
					driver.findElement(By.xpath(XpathforCoverage)).click();
					driver.findElement(By.xpath(XpathforCoverage)).sendKeys(PageCoverage+Keys.TAB);
					Thread.sleep(2000);
					String XpathforAdditionalPlate="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+j+"/4']";
					driver.findElement(By.xpath(XpathforAdditionalPlate)).click();
					driver.findElement(By.xpath(XpathforAdditionalPlate)).sendKeys(PageAdditionalplate+Keys.TAB);
					Thread.sleep(2000);
					String XpathforNotes="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+j+"/5']";
					driver.findElement(By.xpath(XpathforNotes)).click();
					driver.findElement(By.xpath(XpathforNotes)).sendKeys(PageAdditionalplate+Keys.TAB);
					Thread.sleep(2000);
					String XpathforPrintInput="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+j+"/6']";
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
		System.out.println("Back value available are as follows :- "+ListBack);

		if (ListBack.size()>0)
		{

			//				String Listbackstartvalue= ListBack.get(1);
			//				int backstartval=Integer.parseInt(Listbackstartvalue);

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


				if (driver.findElements(By.xpath("//div[@class='dialog dialog__view']")).size()>0)
				{
					driver.findElement(By.xpath("//div[@class='dialog__content']//div[@class='listtb']//button[1]")).click();
					String Xpathformaincolor="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+l+"/0']";
					driver.findElement(By.xpath(Xpathformaincolor)).click();
					driver.findElement(By.xpath(Xpathformaincolor)).sendKeys(PageMainColor+Keys.ENTER+Keys.TAB);
					//driver.findElement(By.xpath("//label[text()='"+PageMainColor+"']")).click();
					Thread.sleep(2000);
					String Xpathforcolor="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+l+"/1']";
					driver.findElement(By.xpath(Xpathforcolor)).click();
					driver.findElement(By.xpath(Xpathforcolor)).sendKeys(PageColor);
					Thread.sleep(2000);
					driver.findElement(By.xpath(Xpathforcolor)).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					driver.findElement(By.xpath(Xpathforcolor)).sendKeys(Keys.TAB);
					Thread.sleep(2000);
					String XpathforCoverage="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+l+"/2']";
					driver.findElement(By.xpath(XpathforCoverage)).click();
					driver.findElement(By.xpath(XpathforCoverage)).sendKeys(PageCoverage+Keys.TAB);
					Thread.sleep(2000);
					String XpathforAdditionalPlate="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+l+"/4']";
					driver.findElement(By.xpath(XpathforAdditionalPlate)).click();
					driver.findElement(By.xpath(XpathforAdditionalPlate)).sendKeys(PageAdditionalplate+Keys.TAB);
					Thread.sleep(2000);
					String XpathforNotes="//div[@class='dialog__content']//div[@class='grid__box']//div[@aria-label='grid'][2]//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+l+"/5']";
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
				System.out.println("Varnish value available are :- "+VarnishList);
			      for (String CloredvalinDB:VarnishList)
			      {
			    	  
			      }
				
				if (VarnishList.size()>0)
				{
					
					
					//for(int p=1;p<=VarnishList.size();p++)
					//{
					int p=1;
					for(String idps2:VarnishList) {
						int q=p-1;
					  driver.findElement(By.xpath("//label[text()='Varnishes']/ancestor::div[@class='listtb']//button[1]")).click();
					  String Valueforvar=VarnishList.get(q);
					  String VarnishColor=EstPageColorandVarnish.get(Valueforvar).get("Color");
					  String VarnishCoverage=EstPageColorandVarnish.get(Valueforvar).get("Coverage");
					  
					  String xpathForVarnishColor="//label[text()='Varnishes']/ancestor::div[@class='listtb']/parent::div//span[contains(@class,'grid__cell grid__cell') and (@data-index='"+q+"/0')]";
					  String xpathForVarnishColortext=xpathForVarnishColor+"//input";
					  driver.findElement(By.xpath(xpathForVarnishColor)).click();
					  Thread.sleep(2000);
					  driver.findElement(By.xpath(xpathForVarnishColortext)).sendKeys(VarnishColor);
					  Thread.sleep(2000);
					  driver.findElement(By.xpath("//label[text()='Varnishes']")).click();
					  Thread.sleep(1000);
					  String xpathForVarnishCoverage="//label[text()='Varnishes']/ancestor::div[@class='listtb']/parent::div//span[contains(@class,'grid__cell grid__cell') and (@data-index='"+q+"/1')]";
					  driver.findElement(By.xpath(xpathForVarnishCoverage)).click();
					  Thread.sleep(2000);
					  
					  if(VarnishCoverage.equals("100"))
					  {
						  driver.findElement(By.xpath("//li/label[text()='Total']")).click();
					  }
					  else
					  {
						  driver.findElement(By.xpath("//li/label[text()='Spot']")).click();
					  }
					  

					p=p+1;
					}
					
					
				}
				
		}
		catch(WebDriverException e)
		{
			System.out.println("Color and varnish Failed for Estimate :- "+ Estimateid);
			e.printStackTrace();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	public void Charactertics_CPGraphRegularFormat(int Estimateid,  String IdItemOption,String Comporderval) throws ClassNotFoundException, SQLException, IOException, InterruptedException {

		HashMap<String, HashMap<String, String>> EstPageFormat = new HashMap<String, HashMap<String, String>>();
		EstPageFormat=name.CPGraphRegularFormat(Estimateid, IdItemOption,Comporderval);

		System.out.println("Component is "+Comporderval);
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
		switch(CharacTypeDesc.toLowerCase())
		{
		case "folded format (model)":
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='Closed (WxH)']/parent::span/span/div/span[1]/input")).sendKeys(SFFinishedFormat+Keys.TAB);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='Flat (WxH)']/parent::span/span/div/span[1]/input")).sendKeys(FlatFormat+Keys.TAB);
			break;
		case "leaf format":
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='Size (WxH)']/parent::span/span/div/span[1]/input")).sendKeys(SFFinishedFormat+Keys.TAB);
			break;
		case "pages format":
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='Finished Format (WxH)']/parent::span/span/div/span[1]/input")).sendKeys(SFFinishedFormat+Keys.TAB);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//label[text()='Page']/parent::span/following-sibling::span/input")).sendKeys(PageFormat);
			break;
		case "cover format":
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='Closed (WxH)']/parent::span/span/div/span[1]/input")).sendKeys(SFFinishedFormat+Keys.TAB);
			break;
		default:
			System.out.println("component type is not coded : "+CompTypedescp);
		}
		
	
	}
	
	public void Charactertics_CPGraphHotStamping(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {


		HashMap<String, HashMap<String, String>> CharCPGraphStampingItem = new HashMap<String, HashMap<String, String>>();

		CharCPGraphStampingItem=name.CPGraphHotStamping(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
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
				
				System.out.println(CPGraphStampingItemType+" "+CPGraphStampingItemHeight+" "+CPGraphStampingItemWidth+" "+CPGraphStampingItemTypeSurface+" "+CPGraphStampingItemInputNumber+" "+CPGraphStampingItemNote);


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
		}

	
		
	}
	
	public void Charactertics_CPPlant(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

		HashMap<String, String> CharCPPlant = new HashMap<String, String>();
		CharCPPlant=name.CPPlant(Estimateid,IdItemOption, Comporderval, CharteristicDescp);

		String CPPlantPlant=CharCPPlant.get("Plant");
      	String XpathWithCPPlantPlant="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//span//label[text()='Plant']/parent::span/span/span/input";
    	    	
    	
    	CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWithCPPlantPlant, CPPlantPlant);
    	
    		
	
	}
	
	public void Charactertics_CPGenericCPOptionDesc(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

		HashMap<String, String> CharCPGenericCPOptionDesc = new HashMap<String, String>();
		//String newCharteristicDescp=CharteristicDescp.replace("'", "''");
		CharCPGenericCPOptionDesc=name.CPGenericCPOptionDesc(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		
		String CPGenericCPOptionDesc=CharCPGenericCPOptionDesc.get("Description");
		String CPGenericCPOptionOptions=CharCPGenericCPOptionDesc.get("Options");
		String CPGenericCPOptionQuantity=CharCPGenericCPOptionDesc.get("Quantity");


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
	public void Charactertics_CPNote(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

		HashMap<String, String> CharCPNote = new HashMap<String, String>();
		CharCPNote=name.CPNote(Estimateid,IdItemOption, Comporderval, CharteristicDescp);
		String CPNoteNote=CharCPNote.get("Note");

		String xpathforNoteText="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']/div//div[@class='text']/textarea";

		driver.findElement(By.xpath(xpathforNoteText)).click();
		driver.findElement(By.xpath(xpathforNoteText)).sendKeys(CPNoteNote);
		Thread.sleep(2000);
	
	}
	public void Charactertics_CPAGraphPageProof(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

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
	
	public void Charactertics_CPValue(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

		HashMap<String, String> CharCPvalue = new HashMap<String, String>();
		CharCPvalue=name.CPValueQTY(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String Cpvalue=CharCPvalue.get("Value");

		String XpathForComponent="//label[text()='"+CharteristicDescp+"']/ancestor::header/following-sibling::div//input";
		driver.findElement(By.xpath(XpathForComponent)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(XpathForComponent)).sendKeys(Cpvalue);

	
	}
	public void Charactertics_CPASimpleQty(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

		HashMap<String, String> CharCPASimpleQty = new HashMap<String, String>();
		CharCPASimpleQty=name.CPASimpleQty(Estimateid, IdItemOption,Comporderval, CharteristicDescp);


		String CPASimpleQtyQuantity=CharCPASimpleQty.get("Quantity");
		String CPASimpleQtyNote=CharCPASimpleQty.get("Note");


		String XpathWitCPASimpleQtyQuantity="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Quantity']/parent::span/span/input";
		String XpathWitCPASimpleQtyNote="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span/span/div/textarea";


		// for Quantity
		driver.findElement(By.xpath(XpathWitCPASimpleQtyQuantity)).clear(); 
		driver.findElement(By.xpath(XpathWitCPASimpleQtyQuantity)).sendKeys(CPASimpleQtyQuantity+Keys.TAB); 

		// for Note
		driver.findElement(By.xpath(XpathWitCPASimpleQtyNote)).clear(); 
		driver.findElement(By.xpath(XpathWitCPASimpleQtyNote)).sendKeys(CPASimpleQtyNote+Keys.TAB);

	
	}
	public void Charactertics_CPGraphFiber(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

		HashMap<String, String> CharCPGraphFiber = new HashMap<String, String>();
		CharCPGraphFiber=name.CPGraphFiber(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String CPGraphFiberGrainDirection=CharCPGraphFiber.get("GrainDirection");



		String XpathForGrainDirection="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Grain Direction']/parent::span//span/input";
		driver.findElement(By.xpath(XpathForGrainDirection)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(XpathForGrainDirection)).sendKeys(CPGraphFiberGrainDirection+Keys.ENTER);
		Thread.sleep(2000);

	
	}
	public void Charactertics_CPGraphPackBox(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

		HashMap<String, String> CharCPGraphPackBox = new HashMap<String, String>();
		CharCPGraphPackBox=name.CPGraphPackBox(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String CPGraphPackBoxUnitsintheBox=CharCPGraphPackBox.get("UnitsInThebox");
		String CPGraphPackBoxBoxType=CharCPGraphPackBox.get("BoxType");
		String CPGraphPackBox=CharCPGraphPackBox.get("Box");
		String CPGraphPackNotes=CharCPGraphPackBox.get("Note");


		String XpathForUnitsintheBox="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Units in the Box']/parent::span/span/input";
		driver.findElement(By.xpath(XpathForUnitsintheBox)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(XpathForUnitsintheBox)).sendKeys(CPGraphPackBoxUnitsintheBox+Keys.ENTER);

		Thread.sleep(2000);
		String XpathForBoxType="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Box Type']/parent::span/span//input";
		driver.findElement(By.xpath(XpathForBoxType)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(XpathForBoxType)).sendKeys(CPGraphPackBoxBoxType+Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath(XpathForBoxType)).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		String XpathForBox="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Box']/parent::span/span//input";
		driver.findElement(By.xpath(XpathForBox)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(XpathForBox)).sendKeys(CPGraphPackBox+Keys.ENTER);

		Thread.sleep(2000);
		String XpathForNote="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span//span/div/textarea";
		driver.findElement(By.xpath(XpathForNote)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(XpathForNote)).sendKeys(CPGraphPackNotes);
	
	
	}
	public void Charactertics_CPFileList(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

		HashMap<String, String> CPFileList = new HashMap<String, String>();
		CPFileList=name.CPFileList(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String CPFileList_file=CPFileList.get("Files");
		String CPFileList_notes=CPFileList.get("Notes");
		
		//write code to take files dont know how to write
		if(CPFileList_file!=null) {
			System.out.println("Need to write code");
		}
		else {
			System.out.println("No files to upload");
		}
	}
	public void Charactertics_CPGraphLargeFormat(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {
		HashMap<String, String> CPGraphLargeFormat = new HashMap<String, String>();
		CPGraphLargeFormat=name.CPGraphLargeFormat(Estimateid, IdItemOption,Comporderval, CharteristicDescp);
		String FinishedFormatWidth=CPGraphLargeFormat.get("FinishedFormatWidth");
		String FinishedFormatHeight=CPGraphLargeFormat.get("FinishedFormatHeight");
		String PrintingDirection=CPGraphLargeFormat.get("PrintingDirection");
		String Note=CPGraphLargeFormat.get("Note");
		String Margins=CPGraphLargeFormat.get("Margins");
		String IrregularFit=CPGraphLargeFormat.get("IrregularFit");
		String TopMargin=CPGraphLargeFormat.get("TopMargin");
		String BottomMargin=CPGraphLargeFormat.get("BottomMargin");
		String LeftMargin=CPGraphLargeFormat.get("LeftMargin");
		String RightMargin=CPGraphLargeFormat.get("RightMargin");
		String TopBleed=CPGraphLargeFormat.get("TopBleed");
		String BottomBleed=CPGraphLargeFormat.get("BottomBleed");
		String LeftBleed=CPGraphLargeFormat.get("LeftBleed");
		String RightBleed=CPGraphLargeFormat.get("RightBleed");
		String Splicing=CPGraphLargeFormat.get("Splicing");
		String SplicingBleed=CPGraphLargeFormat.get("SplicingBleed");
		String LayoutWidht=CPGraphLargeFormat.get("LayoutWidht");
		String LayoutHeight=CPGraphLargeFormat.get("LayoutHeight");
		String Utilization=CPGraphLargeFormat.get("Utilization");
		String Numberup=CPGraphLargeFormat.get("Numberup");
		
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
		if(Integer.parseInt(Splicing)!=0 && Integer.parseInt(SplicingBleed)!=0) {
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
		if(Margins.equals("1")) {
			if(option1.equalsIgnoreCase("true")) {
				driver.findElement(By.xpath("//label[text()='Layout format (WxH)']/../span/div/span/input")).sendKeys(Layoutformat+Keys.TAB);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Number Up']/../span/input")).sendKeys(Numberup+Keys.TAB);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//label[text()='Utilization']/../span/input")).sendKeys(Utilization+Keys.TAB);
			}else {
				ele1.click();
			}
		}else if(Margins.equals("0")){
			if(option1.equalsIgnoreCase("true")) {
				ele1.click();
			}else {
				
			}
		}
		
	}
	public void charactertics_CPGraphGiantCoupling(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {
		
	}
	
	public void Charactertics_CPGraphBindGlue(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception {

		HashMap<String, String> CharCPGraphBindGlue = new HashMap<String, String>();
		CharCPGraphBindGlue=name.CPGraphBindGlue(Estimateid, IdItemOption,Comporderval, CharteristicDescp);


		String CPGraphBindGlueGlueType=CharCPGraphBindGlue.get("GlueType");
		String CPGraphBindGlueIsSewn=CharCPGraphBindGlue.get("IsSewn");
		String CPGraphBindGlueNote=CharCPGraphBindGlue.get("Note");


		String XpathWitCPGraphBindGlueGlueType="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Glue Type']/parent::span/span/span/input";
		String XpathWitCPGraphBindGlueIsSewn="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Sewn']";
		String XpathWitCPGraphBindGlueNote="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span/span/div/textarea";


		// for Glue Type
//		driver.findElement(By.xpath(XpathWitCPGraphBindGlueGlueType)).clear(); 
//		driver.findElement(By.xpath(XpathWitCPGraphBindGlueGlueType)).sendKeys(CPGraphBindGlueGlueType+Keys.TAB); 
		System.out.println("Glue Type is :- "+CPGraphBindGlueGlueType);
		CommonFunctions.Iquote_SelectFromDropdown_Text(driver, XpathWitCPGraphBindGlueGlueType, CPGraphBindGlueGlueType);

		// for ISSewn
		CommonFunctions.Iquote_SelectCheckbox(driver, XpathWitCPGraphBindGlueIsSewn, CPGraphBindGlueIsSewn);
		// for Note
		driver.findElement(By.xpath(XpathWitCPGraphBindGlueNote)).clear(); 
		driver.findElement(By.xpath(XpathWitCPGraphBindGlueNote)).sendKeys(CPGraphBindGlueNote+Keys.TAB);

	
	}
	public void Charactertics_CPGraphUnfinishedFormat(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
	{
		HashMap<String, String> CharCPGraphUnfinishedFormat = new HashMap<String, String>();
		CharCPGraphUnfinishedFormat=name.CPGraphUnfinishedFormat(Estimateid,IdItemOption, Comporderval, CharteristicDescp);

		String CPGraphUnfinishedFormatWidth=CharCPGraphUnfinishedFormat.get("Width");
		String CPGraphUnfinishedFormatHeight=CharCPGraphUnfinishedFormat.get("Height");
		String CPGraphUnfinishedFormatThickness=CharCPGraphUnfinishedFormat.get("Thickness");
		String CPGraphUnfinishedFormatWeight=CharCPGraphUnfinishedFormat.get("Weight");
		String CPGraphUnfinishedFormatInventoryItemCode=CharCPGraphUnfinishedFormat.get("InventoryItemCode");
		String CPGraphUnfinishedFormatInventoryItemDescription=CharCPGraphUnfinishedFormat.get("InventoryItemDescription");
		String CPGraphUnfinishedFormatQuantity=CharCPGraphUnfinishedFormat.get("Quantity");
		String CPGraphUnfinishedFormatNote=CharCPGraphUnfinishedFormat.get("Note");

		String PageWHval=CPGraphUnfinishedFormatWidth+" x "+CPGraphUnfinishedFormatHeight;

		String XpathWitCPGraphUnfinishedFormatSize ="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Size (WxH)']/parent::span/span/input";
		String XpathWitCPGraphUnfinishedFormatThickness="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Thickness']/parent::span/span/input";
		String XpathWitCPGraphUnfinishedFormatWeight="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Weight']/parent::span/span/input";
		String XpathWitCPGraphUnfinishedFormatInventoryItemCode="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Inventory item']/parent::span/span/span/input[1]";
		String XpathWitCPGraphUnfinishedFormatQuantity="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Quantity']/parent::span/span/input";
		String XpathWitCPGraphUnfinishedFormatNote="//label[text()='"+CharteristicDescp+"']/ancestor::div[@class='list__item']//label[text()='Note']/parent::span/span/div/textarea";

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
	public void Charactertics_CPGraphBleed(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws ClassNotFoundException, IOException, SQLException
	{
		HashMap<String, String> CharCPGraphBleed= new HashMap<String, String>();
		CharCPGraphBleed=name.CPGraphBleed(Estimateid,IdItemOption, Comporderval, CharteristicDescp);
		
		String CPGraphBleed=CharCPGraphBleed.get("Bleed");
		
		String XpathWitCPGraphBleed =" //label[text()='Bleed']/../span/input";
		
		if(!CPGraphBleed.isEmpty()) {
			driver.findElement(By.xpath(XpathWitCPGraphBleed)).click();
			driver.findElement(By.xpath(XpathWitCPGraphBleed)).clear(); 
			driver.findElement(By.xpath(XpathWitCPGraphBleed)).sendKeys(CPGraphBleed+Keys.TAB); 

		}
		
	}
	
	public void Charactertics_CPGraphInitialLaminating(int Estimateid,  String IdItemOption,String Comporderval,String CharteristicDescp) throws Exception
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
			System.out.println("Details page is displayed");

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
}
