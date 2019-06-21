package pages;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import base.Testbase;
import utilities.CommonFunctions;
import utilities.ReadData;

public class Estimatepage_Characteristics extends Testbase {
	
	ReadData name = new ReadData();
	private Object valueofClosedWidth;
	public void Charactertics_GraphMedia(int Estimateid, String Comporderval) throws ClassNotFoundException, IOException, SQLException
	{
		HashMap<String, HashMap<String, String>> EstPageSpec = new HashMap<String, HashMap<String, String>>();

		EstPageSpec=name.PaperSpec(Estimateid, Comporderval); 
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
			CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("NE_Line_Click")));
			String SelectLine="//html/body/div[@class='drop-down']//li[.//label[text()='"+PageLine+"']]";
			CommonFunctions.ClickElement(driver, By.xpath((SelectLine)));
			Thread.sleep(3000);
			//int Grammage= Integer.parseInt(PageGrammage);

			String Grammage1= PageGrammage.replace(".0", "");
			System.out.println("Grammage is :- "+Grammage1);
			driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).click();
			CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("NE_Grammage")), Grammage1);
			// driver.findElement(By.xpath(Locators.getProperty(Locators.NE_Grammage))).sendKeys(Grammage1);
			driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).sendKeys(Keys.DOWN);
			driver.findElement(By.xpath(OR.getProperty("NE_Grammage"))).sendKeys(Keys.ENTER);
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
	public void Charactertics_CPGraphBindStitch(int Estimateid, String Comporderval, String CharteristicDescp) throws Exception
	{
		
		HashMap<String, String> CharCPGraphBindStitch = new HashMap<String, String>();
		CharCPGraphBindStitch=name.CPGraphBindStitch(Estimateid, Comporderval, CharteristicDescp);


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
		driver.findElement(By.xpath(XpathWitCPGraphBindStitchQty)).clear(); 
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

}
