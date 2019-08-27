package pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import base.Testbase;
import de.redsix.pdfcompare.PageArea;
import de.redsix.pdfcompare.PdfComparator;
import ru.yandex.qatools.ashot.Screenshot;
import utilities.CommonFunctions;
import utilities.HTML_File_Creator;
import utilities.ReadData;
import utilities.ScreenShot; 
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
public class Estimate extends Testbase{
	
	static String CommonOriginalHandle=null;
	static HTML_File_Creator HTMLF= new HTML_File_Creator();
	static ReadData val = new ReadData();
	
	
	public static void ClickonNewEstimate()throws Exception
	{
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Nav_Estimatepage_Dropdown")));
		//Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Nav_NewEstimate_Page"))));
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Nav_NewEstimate_Page")));
		CommonFunctions.waitForPageLoad(driver);
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//b[text()='New estimate']"), 100000);
		
		int val =driver.findElements(By.xpath(OR.getProperty("CancelTitle"))).size();
		if (val>0)
		{
			System.out.println("New Estimate Page successfully loaded");
			test.log(Status.PASS, "New Estimate Page successfully loaded");
		}
		else
		{
			System.err.println("New Estimate Page not loaded");
			test.log(Status.FAIL, "New Estimate Page not loaded");

		}
		
	}
	public static void DeleteRenamedProductsandComponents() throws InterruptedException
	{
		int DeleteCompCount=driver.findElements(By.xpath("//span[@class='diagram__item ps--item ps--product']/label[contains(text(),'To Be Deleted')]")).size();
		String s=null;
		
		for(int j=1;j<=DeleteCompCount;j++)
		{
			int i=1;
			//int size;
			System.out.println("//span[@class='diagram__item ps--item ps--product']["+i+"]/label[contains(text(),'To Be Deleted')]");
			driver.findElement(By.xpath("//span[@class='diagram__item ps--item ps--product']["+i+"]/label[contains(text(),'To Be Deleted')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[@class='diagram__item ps--item ps--product']["+i+"]/label[contains(text(),'To Be Deleted')]")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//span[@class='diagram__item ps--item ps--product']["+i+"]/label[contains(text(),'To Be Deleted')]")).click();
			
			if(driver.findElements(By.xpath("//span[text()='Delete Component Set']")).size()>0)
			{
				driver.findElement(By.xpath("//span[text()='Delete Component Set']")).click();

			}
			else
			{
				driver.findElement(By.xpath("//span[text()='Delete Component']")).click();
			}
			
		}
	}

	public static Boolean NavigateToQtyPriceTab()throws Exception
	{
		
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("LabelQTY")));
		CommonFunctions.waitForPageLoad(driver);
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='Option']"), 180);
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='Qty / Price']/parent::span[@data-enabled='true']"), 180);
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath(OR.getProperty("LabelUnderQtyPricePage")), 180);
		int val =driver.findElements(By.xpath(OR.getProperty("LabelUnderQtyPricePage"))).size();
		System.out.println("Navigation Quantity and price size is :- "+ val);
		if (val>0)
		{
			System.out.println("Qty Price Tab Navigation successfully");
			return true ;
		}
		else
		{
			System.out.println("Qty Price Tab Navigation failed");
			return false ;

		}
		

	}
	
	public void Qtypage(String Estimateid)
	{
		try
		{
			HashMap<String, HashMap<String, String>> EstQtypageval = new HashMap<String, HashMap<String, String>>();
			//IquoteDBQuery iqdb=new IquoteDBQuery();
			ReadData iqdb = new ReadData();
			EstQtypageval=iqdb.QtyForEst(Estimateid);
			System.out.println(EstQtypageval.size());
			List<String> ListQty = new ArrayList<String>(EstQtypageval.keySet());
			Collections.sort(ListQty);
			// List<String> ListQty = new ArrayList<String>();
			for (String s : ListQty) 
			{
				System.out.println(s);
			}
	
			for(int i=0;i<ListQty.size();i++)
			{
				String Productval=ListQty.get(i);
				CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//span[text()='"+Productval+"']/parent::span[contains(@class,'grid__cell grid__cell')]"), 10000);
				String xpathforProduct="//span[text()='"+Productval+"']/parent::span[contains(@class,'grid__cell grid__cell')]";
				String QTYvalue=EstQtypageval.get(Productval).get("Qty");
				Thread.sleep(5000);
				System.out.println("Quanttity value is :-"+QTYvalue);
				String dataindexval=driver.findElement(By.xpath(xpathforProduct)).getAttribute("data-row");
	//			String[] arrOfStr = dataindexval.split("/"); 
	//			String valueofindex=arrOfStr[1];
				int resultval = Integer.parseInt(dataindexval);	
				int resultvalnextcoloum=resultval+1;
				System.out.println("Entering value for Product :- "+dataindexval);
	
				String xpathforQtyField="//difv[@class='wv__content']//span[text()='"+Productval+"']/parent::span/following-sibling::span[@data-index='"+dataindexval+"/6']";
				System.out.println(xpathforQtyField);
				driver.findElement(By.xpath(xpathforQtyField)).click();
				String xpathforQtyTextInput="//div[@class='wv__content']//span[text()='"+Productval+"']/parent::span/following-sibling::span[@data-index='"+dataindexval+"/6']/input";
				Thread.sleep(2000);
				CommonFunctions.waitUntilElementisPresent(driver, By.xpath(xpathforQtyTextInput), 180);
				driver.findElement(By.xpath(xpathforQtyTextInput)).sendKeys(QTYvalue+Keys.TAB);
				Thread.sleep(2000);
			}
		}	
		catch(Exception e)
		{
			System.err.println("Not able to Enter Quantity in Quantity tab");
			e.printStackTrace();
			Assert.fail("Failed while entering Quatity in Quantity page");
		}
	}
	public static void Navigate_to_ProductTab()
	{

		try {
			CommonFunctions.ClickElement(driver, By.xpath("//label[text()='Product']/parent::span/label"));
			CommonFunctions.waitUntilElementisVisible(driver, By.xpath("//span[text()='Specification']"), 5000);

		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static void SaveEstimate() throws Exception
	{
		test.log(Status.INFO, "Saving Estimate");
		if(driver.findElements(By.xpath("//div[@class='wv']//button[2]")).size()>0)
		{
			CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Estimate_Save")));
			CommonFunctions.waitForPageLoad(driver);
			WebElement ele=driver.findElement(By.xpath("//label[text()='Calculate']"));
			CommonFunctions.waitForElement(ele, 300);
			Thread.sleep(2000);
			
			int EngVisible= driver.findElements(By.xpath("//label[text()='Calculate']")).size();
			if (EngVisible>0)
			{
				System.out.println("Estimate saved successfully");
			}
			else
			{
				System.out.println("Failed to save");
				//System.err.println("Failed to save");

			}
		}
		
		//	TakeScreenShot.ScreenShotWindow(driver,"NegotiationPageEstimateNumber");  
	
	}
	public static void VerifyEngineering(String EstimateID) throws Exception {
		test.log(Status.INFO, "Verifying Engineering Diagrams");
		
		/*String Actualname=ScreenShot.ScreenShotRegion_withPath(driver, By.xpath("//div[@class='eng-di__cont']//div[@class='diagram__cont']"), "ENG", "",EstimateID);
		if(!Actualname.isEmpty()) {
			String Status=ScreenShot.imageComparison("ENG.png",Actualname,(EstimateID+"ENG_Diff.png"), "No",EstimateID);
			System.out.println("Image Comparision of Engineering Diagram : "+Status);
			String sFile1=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+EstimateID+"\\Base\\ENG.png";
			String sFile2=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+EstimateID+"\\Actual\\"+Actualname;
			String Differencepath=System.getProperty("user.dir")+ "\\src\\test\\resources\\Documents\\"+EstimateID+"\\Difference\\"+EstimateID+"ENG_Diff.png";
			 HTMLF.addrow("","EST-Engineering Diagram", sFile1, sFile2, Differencepath, Status,Config.getProperty("EstimateIDs")+".html");
			
			
		}*/
		WebElement webElement = driver.findElement(By.cssSelector("svg.diagram__canvas"));
		Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, webElement);
		//Screenshot screenshot = new AShot().takeScreenshot(driver, webElement);
		ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") +"\\src\\test\\resources\\Documents\\"+EstimateID+"\\Actual\\ENG.png"));
		
		BufferedImage actualImage = screenshot.getImage();
		
		BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+EstimateID+"\\Base\\ENG.png"));
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
       
        if(diff.hasDiff()==true)
        {
    	 BufferedImage diffImage = diff.getDiffImage();
         ImageIO.write(actualImage, "PNG", new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+EstimateID+"\\Difference\\ENG_Diff.png"));
         System.out.println("ENG Images are Not Same");
        }
        else {
        	
             System.out.println("ENG Images are Same");
          }
		
	}
	public static void VerifyQty(String EstimateID) throws Exception {

		/*String Actualname=ScreenShot.ScreenShotRegion_withPath(driver, By.xpath("//label[text()='Option']/parent::span/parent::div/ancestor::div[@class='grid__box']/ancestor::div[@class='wizard']"), "QTY", "",EstimateID);
		if(!Actualname.isEmpty()) {
			String Status=ScreenShot.imageComparison("QTY.png",Actualname,(EstimateID+"QTY_Diff.png"), "No",EstimateID);
			System.out.println("Image Comparision of Quantity Diagram : "+Status);
			String sFile1=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+EstimateID+"\\Base\\QTY.png";
			String sFile2=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+EstimateID+"\\Actual\\"+Actualname;
			String Differencepath=System.getProperty("user.dir")+ "\\src\\test\\resources\\Documents\\"+EstimateID+"\\Difference\\"+EstimateID+"QTY_Diff.png";
			 HTMLF.addrow("","EST-Engineering Diagram", sFile1, sFile2, Differencepath, Status,Config.getProperty("EstimateIDs")+".html");
			
		}*/
		
		WebElement webElement = driver.findElement(By.cssSelector("div.wizard"));
		Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, webElement);
		//Screenshot screenshot = new AShot().takeScreenshot(driver, webElement);
		
		ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") +"\\src\\test\\resources\\Documents\\"+EstimateID+"\\Actual\\QTY.png"));
		
		BufferedImage actualImage = screenshot.getImage();
		
		BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+EstimateID+"\\Base\\QTY.png"));
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
       
        if(diff.hasDiff()==true)
        {
    	 BufferedImage diffImage = diff.getMarkedImage();
         ImageIO.write(actualImage, "PNG", new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+EstimateID+"\\Difference\\QTY_Diff.png"));
         System.out.println("QTY Images are Not Same");
        }
        else {
    	
         System.out.println("QTY Images are Same");
        }
		
	
	}
	public static void VerifyNegotiation(String Actualfile,String Estimate) throws IOException {
		 
		String file1=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Actual\\"+Actualfile;
		String file2=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Base\\NEG.pdf";
		String diff=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Difference\\diffOutput";
		boolean isEquals = new PdfComparator(file1, file2).with(new PageArea(1, 1407, 58, 2277, 363)).with(new PageArea(1,1798,483,1856,501)).compare().writeTo(diff);
		String status =isEquals? "PASS" : "FAIL";
		if (!isEquals) {
		    System.out.println("Differences found in PDF's!");
		}
		if (isEquals) {
		    System.out.println("No Differences found in PDF's!");
		}
		HTMLF.addrow("Step 4", "Estimate Negotiation PDF Validation", file2, file1, "", status,Config.getProperty("EstimateIDs")+".html");
		test.log(Status.INFO, "Creation Of estimate ends");
	}
	public static void CreateOption(int Option) throws InterruptedException {
		if(Option>1) {
			test.log(Status.INFO, "Creating Option : "+Option);
			driver.findElement(By.xpath("//span[@class='wvtb__prop']//button")).click();
			 driver.findElement(By.xpath("//span[@class='wvtb__prop']//button")).click();
		   	 Thread.sleep(1000);
		   	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'New Option')]")));
		   	 driver.findElement(By.xpath("//li[contains(text(),'New Option')]")).click();
		   	 Thread.sleep(5000);
		   	 //Estimate.Navigate_to_ProductTab();
		 	if (driver.findElements(By.xpath("//nav[@class='wizard__nav']//span[2]")).size()>0) {
		   		driver.findElement(By.xpath("//nav[@class='wizard__nav']//span[2]")).click();
		   	}
		 	else {
		 		System.out.println("object not clicked to go to second option");
		 	}
		    Thread.sleep(1000);
		   	// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='wvtb__prop']//button")));
		   	 driver.findElement(By.xpath("//span[@class='wvtb__prop']//button")).click();
		   	driver.findElement(By.xpath("//span[@class='wvtb__prop']//button")).click();
		   	 Thread.sleep(2000);
		   	 System.out.println("//li[contains(text(),'Option "+(Option)+"')]//following::li"+1+"");
		   	driver.findElement(By.xpath("//li[contains(text(),'Option "+(Option)+"')]//following::li["+1+"]")).click();
		   	System.out.println("Option : "+Option);
		   	//Navigate to product screen
		    Thread.sleep(1000);
		   
			
		}
		else {
			System.out.println("Option : 1");
		}
		
		
	}
	public static void CalculateEstimate() throws Exception
	{
		test.log(Status.INFO, "Calculating Estimate");
		System.out.println("Calculating Estimate");
		//Thread.sleep(40000);
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Calculate_Estimate")));
		WebElement ele=driver.findElement(By.xpath("//nav[@class='wizard__nav']//span[5]"));
		CommonFunctions.waitForElement(ele, 300);
		CommonFunctions.waitForPageLoad(driver);
		Thread.sleep(3000);
		Boolean ele1=wait.until(ExpectedConditions.attributeToBe(By.xpath("//label[text()='Engineering']//parent::span"), "data-enabled", "true"));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Engineering']")));
		//element.click();
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath(OR.getProperty("Engineering_Tab")),180);
		String EngVisible= driver.findElement(By.xpath("//label[text()='Engineering']/parent::span")).getAttribute("data-enabled");
		System.out.println("Attribute value is :"+EngVisible);
		
		if(EngVisible.equalsIgnoreCase("True"))
		{
			System.out.println("Calculation passed");
		}
		else
		{
			System.out.println("Calculation Failed");
		}

	} 
	public static void CloseEstimateTab() {
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='Estimate']/parent::span/parent::li//span[@class='app__tab__close']"), 180);
		driver.findElement(By.xpath("//label[text()='Estimate']/parent::span/parent::li//span[@class='app__tab__close']")).click();
	}
	public static Boolean NavigateToEngineeringTab()throws Exception
	{
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath(OR.getProperty("Engineering_Tab")),180);
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Engineering_Tab")));
		CommonFunctions.waitForPageLoad(driver);
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//span[@class='drop-down-btn diagram__action']/img[@class='icon eng-di__tool-img']"), 180);
      //  CommonFunctions.waitUntilElementisClickable(driver, By.xpath(Locators.getProperty(Locators.Engineering_Tab_zoom_Logo)), 180);
		int val =driver.findElements(By.xpath(OR.getProperty("Engineering_Tab_zoom_Logo"))).size();
		if (val>0)
		{
			System.out.println("Engineering Tab Navigation successfully");
			return true ;
		}
		else
		{
			System.out.println("Engineering Tab Navigation failed");
			return false ;
		}

	}
	
	public static Boolean NavigateToNegotiationTab()throws Exception
	{
		
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Negotiation_Tab")));
		Thread.sleep(2000);
		CommonFunctions.waitForPageLoad(driver);
		if(driver.findElements(By.xpath("//span[@class='input-wraper simple-lookup2']//label[contains(text(),'Default')]")).size()>0) {
			CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='Print']"), 25);
			return true;
		}
		else if(driver.findElements(By.xpath("//span[@class='input-wraper simple-lookup2']//label[contains(text(),'Analysis')]")).size()>0){
			driver.findElement(By.xpath("//span[@class='input-wraper simple-lookup2']//label[contains(text(),'Analysis')]")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[contains(text(),'Default')]"))));
			driver.findElement(By.xpath("//label[contains(text(),'Default')]")).click();
			CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='Print']"), 25);
			return true;
		}
		else {
			return false;
		}

	}
	public static String SaveEstimateNumber()throws Exception

	{

		String sEstimateNumber = driver.findElement(By.xpath("//span[@class='ltv__item__label ltv_']/label[text()='Estimate']/following-sibling::span/span")).getText();
		System.out.println("The New Estimate Number is: "+sEstimateNumber);
		
		return sEstimateNumber;
	}
	
	public static void deleteFiles(File file) {
		  if (file.isDirectory())
		        for (File f : file.listFiles())
		            deleteFiles(f);
		    else
		        file.delete();
	}
	
	public static void SwitchToSecondWindow(WebDriver driver) throws Exception
	{
		String  originalHandle = driver.getWindowHandle();
		String sWindowTitle =driver.getTitle();
		Set<String> availableWindows = driver.getWindowHandles();

		if (!availableWindows.isEmpty()) {

			for (String windowId : availableWindows)
			{
				
				
				if(driver.getWindowHandle().equals(originalHandle))
				{
					driver.switchTo().window(windowId);
					Thread.sleep(1000);
					System.out.println(driver.getTitle());	
					break;
				}	
				else 
				{
					driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}			
		}
	}
	public static String NegotiaionAndPrint(String filename,String Estimate) throws Exception
	{
		DateFormat dateFormat = new SimpleDateFormat("hhmmssaa");
		//filename= filename+"_"+ dateFormat.format(new Date())+".pdf";
		filename= filename+".pdf";
		String filelocation=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Actual\\"+filename;
		System.out.println(filelocation);
		
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Negotiation_Tab")));
		int val =driver.findElements(By.xpath(OR.getProperty("OtherFunctions_Label"))).size();
		if (val>0)
		{
			System.out.println("Navigation to Negotiation tab successful");
		}
		else
		{
			System.err.println("Failed to Navigation to Negotiation tab");

		}
		Thread.sleep(3000);
		//CommonFunctions.SetOriginalWindowHandle(driver);
		if(driver.findElements(By.xpath("//span[@class='input-wraper simple-lookup2']//label[contains(text(),'Default')]")).size()>0) {
			CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Print_Button")));
		}
		else if(driver.findElements(By.xpath("//span[@class='input-wraper simple-lookup2']//label[contains(text(),'Analysis')]")).size()>0){
			driver.findElement(By.xpath("//span[@class='input-wraper simple-lookup2']//label[contains(text(),'Analysis')]")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[contains(text(),'Default')]"))));
			driver.findElement(By.xpath("//label[contains(text(),'Default')]")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Print_Button"))));
			CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Print_Button")));
		}
			

				Thread.sleep(20000);
		
		/*Set<String> s=new HashSet<>();
		
		s=driver.getWindowHandles();
		String current=driver.getWindowHandle();
		System.out.println(current);
		
		for(String o:s) {
			if(!o.equals(current)) {
				System.out.println(o);
			driver.switchTo().window(o);
			}
		}
		driver.findElement(By.cssSelector("paper-button.action-button")).click();
		
		Actions ac=new Actions(driver);
		ac.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		ac.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		ac.sendKeys("s");
		Thread.sleep(2000);
		ac.sendKeys(Keys.chord(Keys.SHIFT,Keys.TAB));
		Thread.sleep(2000);
		ac.sendKeys(Keys.chord(Keys.SHIFT,Keys.TAB));
		Thread.sleep(2000);
		ac.sendKeys(Keys.ENTER);
		setClipboardData(filelocation);
		Thread.sleep(2000);
		ac.sendKeys(Keys.chord(Keys.CONTROL,"v"));
		ac.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		ac.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		ac.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		ac.sendKeys(Keys.ENTER);
		Thread.sleep(3000);*/
		
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_S);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_SHIFT);
		
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
		setClipboardData(filelocation);
		Thread.sleep(2000);
		 robot.keyPress(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_CONTROL);
         Thread.sleep(2000);
         robot.keyPress(KeyEvent.VK_TAB);
         Thread.sleep(2000);
         robot.keyPress(KeyEvent.VK_TAB);
         Thread.sleep(2000);
         
         robot.keyPress(KeyEvent.VK_TAB);
         robot.keyRelease(KeyEvent.VK_TAB);
         Thread.sleep(2000);
         robot.keyPress(KeyEvent.VK_ENTER);
         robot.keyRelease(KeyEvent.VK_ENTER);
         Thread.sleep(3000);
		return filename;
	}
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	public static void UncheckUnwantedCharacteristics(List<String> listCharname)
	{

		System.out.println("*************Deletion Of characteristics started************************");
        try
        {
		int CharonScreensize=driver.findElements(By.xpath("//span[contains(@class,'input-wraper islookup--header ')]/span/label")).size();
		List<String> CharacteristicsList = new ArrayList<String>();
		List<String> RemoveCharacteristicsList = new ArrayList<String>();
		List<String> HyperLinkCharacteristicsList = new ArrayList<String>();
		//List<String> AddCharacteristicsList = new ArrayList<String>();
	    //For HyperLink
			int CharateristicswithHyperlink=driver.findElements(By.xpath("//span[@class='ltv__item ltv_']/div[@class='list']/div[@class='list__item']/header[@class='layout-header']/span[contains(@class,'input-wraper islookup--header islookup--header--drop')]")).size();
			if(!(CharateristicswithHyperlink==0))
			{
			    for(int z=1;z<=CharateristicswithHyperlink;z++)
			    {
			    	String HyperDefaultCharXpath="//span[@class='ltv__item ltv_']/div[@class='list']/div[@class='list__item']/header[@class='layout-header']/span[contains(@class,'input-wraper islookup--header islookup--header--drop')]//label";
			    	String HyperLinkChar=  driver.findElement(By.xpath(HyperDefaultCharXpath)).getText();
			    	HyperLinkCharacteristicsList.add(HyperLinkChar);
			    }
			    
			    for(String HYPChar:HyperLinkCharacteristicsList)
			    {
			    	System.out.println("Hyper Link Charateristic is :- "+HYPChar);
			    	driver.findElement(By.xpath("//label[text()='"+HYPChar+"']")).click();
			        int NoofHyperlinkoptions=driver.findElements(By.xpath("//ul[@class='select islookup--header__select']/li")).size();
			    	System.out.println("Number of Hyper links are :- "+NoofHyperlinkoptions);
			        for(int x=1;x<=NoofHyperlinkoptions;x++)
			    	{
			    		String getSelectOption=driver.findElement(By.xpath("//ul[@class='select islookup--header__select']/li["+x+"]//label")).getText();
			    		if(listCharname.contains(getSelectOption))
			    		{
			    			driver.findElement(By.xpath("//ul[@class='select islookup--header__select']//label[text()='"+getSelectOption+"']")).click();
			    			break;
			    		}
			    		else
			    		{
			    			System.out.println("Hyperlink Charecteristic not found :- "+getSelectOption);
			    		}
			    	}	    	
			    }
			}
		
		
		//For default Characteristics
		Thread.sleep(5000);
		int DefaultCharacterSize=driver.findElements(By.xpath("//span[@class='ltv__item ltv_']/div[@class='list']/div[@class='list__item']/header[@class='layout-header']/span[contains(@class,'input-wraper islookup--header ')]")).size();
		for(int i=1;i<=DefaultCharacterSize;i++)
		{
			String DefaultCharXpath="//span[@class='ltv__item ltv_']/div[@class='list']/div[@class='list__item']["+i+"]/header[@class='layout-header']/span[contains(@class,'input-wraper islookup--header ')]//label";
			String Characterval= driver.findElement(By.xpath(DefaultCharXpath)).getText();
			//if(!Characterval.contains("Number of versions"))
				CharacteristicsList.add(Characterval);
		}

		//For Checked Characteristics
		int CheckedCharSize=driver.findElements(By.xpath("//span[@class='ltv__item ltv_ ltv_last']/div[@class='list']/div[@class='list__item']/header[@class='layout-header']/span[contains(@class,'input-wraper islookup--header ')]")).size();
		for(int j=1;j<=CheckedCharSize;j++)
		{
			String CheckedCharXpath="//span[@class='ltv__item ltv_ ltv_last']/div[@class='list']/div[@class='list__item']["+j+"]/header[@class='layout-header']/span[contains(@class,'input-wraper islookup--header ')]//label";
			String Characterval= driver.findElement(By.xpath(CheckedCharXpath)).getText();
			CharacteristicsList.add(Characterval);
		}

		System.out.println("All the Characteristics in Screen are :- "+CharacteristicsList);

		if (CharacteristicsList.size()==CharonScreensize)
		{
			System.out.println("Characters on screen is :- "+CharonScreensize);

			for(int k=0;k<CharacteristicsList.size();k++)
			{
				String vallist=CharacteristicsList.get(k);
				if(!listCharname.contains(vallist))
				{
					RemoveCharacteristicsList.add(vallist);
				}


			}

			
			if (RemoveCharacteristicsList.size()>0)
			{
				//Removing the character by Unchecking Checkbox
				System.out.println("Remove Chracteristic List is as follows :-");
				System.out.println(RemoveCharacteristicsList);
				for(String s:RemoveCharacteristicsList)
				{
					System.out.println("Remove Characteristics :- "+s.trim());
					String RemoveChar=s.trim();
					String rCharxpath="//label[text()='"+RemoveChar+"']/ancestor::header[@class='layout-header']/button/div";
					driver.findElement(By.xpath(rCharxpath)).click();
					Thread.sleep(5000);
				}
			}
		}
		System.out.println("*************Deletion Of characteristics Ends*********************");
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	
	}
	public static void AddCharacteristics(List<String> listCharname, String Compdescp) throws InterruptedException
	{

		int CharonScreensize=driver.findElements(By.xpath("//span[contains(@class,'input-wraper islookup--header ')]/span/label")).size();
		List<String> CharacteristicsList = new ArrayList<String>();
		
		List<String> AddCharacteristicsList = new ArrayList<String>();
		Thread.sleep(5000);
		
		
		//For default Characteristics
		int DefaultCharacterSize=driver.findElements(By.xpath("//span[@class='ltv__item ltv_']/div[@class='list']/div[@class='list__item']/header[@class='layout-header']/span[contains(@class,'input-wraper islookup--header ')]")).size();
		for(int i=1;i<=DefaultCharacterSize;i++)
		{
			String DefaultCharXpath="//span[@class='ltv__item ltv_']/div[@class='list']/div[@class='list__item']["+i+"]/header[@class='layout-header']/span[contains(@class,'input-wraper islookup--header ')]//label";
			String Characterval= driver.findElement(By.xpath(DefaultCharXpath)).getText();
			//if(!Characterval.contains("Number of versions"))
				CharacteristicsList.add(Characterval);
		}
      
		//For Checked Characteristics
		int CheckedCharSize=driver.findElements(By.xpath("//span[@class='ltv__item ltv_ ltv_last']/div[@class='list']/div[@class='list__item']/header[@class='layout-header']/span[contains(@class,'input-wraper islookup--header ')]")).size();
		for(int j=1;j<=CheckedCharSize;j++)
		{
			String CheckedCharXpath="//span[@class='ltv__item ltv_ ltv_last']/div[@class='list']/div[@class='list__item']["+j+"]/header[@class='layout-header']/span[contains(@class,'input-wraper islookup--header ')]//label";
			String Characterval= driver.findElement(By.xpath(CheckedCharXpath)).getText();
			CharacteristicsList.add(Characterval);
		}

		System.out.println("All the Characteristics in Screen are :- "+CharacteristicsList);

		if (CharacteristicsList.size()==CharonScreensize)
		{
			System.out.println("Characters on screen is :- "+CharonScreensize);

			for(int k=0;k<listCharname.size();k++)
			{
				String vallist=listCharname.get(k);
				if(!CharacteristicsList.contains(vallist))
				{
					AddCharacteristicsList.add(vallist);
				}
			}
		}

		System.out.println("All the Charaecteristic to be added :- "+AddCharacteristicsList);
		for(String s:AddCharacteristicsList)

		{	
			System.out.println("Current Characteristic is :- "+AddCharacteristicsList);

			driver.findElement(By.xpath("//span[@title='Add Characteristic']")).click();
			Thread.sleep(8000);

			int tabCounts=driver.findElements(By.xpath("//span[contains(text(),'"+Compdescp+"')]/ancestor::span[@class='ltv__item ltv_']/following-sibling::span//span[contains(@class,'tabs__tab ')]")).size();
			System.out.println("Seraching For Char:-"+s);

			int p=0;
			for(int l=1;l<=tabCounts;l++)
			{

				if(p==1)
				{
					break;
				}
				String Tabname=driver.findElement(By.xpath("//span[contains(text(),'"+Compdescp+"')]/ancestor::span[@class='ltv__item ltv_']/following-sibling::span//span[contains(@class,'tabs__tab ')]["+l+"]//span")).getText();
				System.out.println("Clicking on Tab:- "+Tabname);
				driver.findElement(By.xpath("//span[text()='"+Tabname+"']")).click();

				Thread.sleep(15000);
				int TotalCharundertab=driver.findElements(By.xpath("//span[text()='"+Tabname+"']/ancestor::span[@class='tabs__tab tabs__selected']/ancestor::div[@class='sheet__tabs']/following-sibling::div//div[@class='list']/div[@class='list__item']")).size();
				for(int x=1;x<=TotalCharundertab;x++)
				{
					String SelectCharvalue=driver.findElement(By.xpath("//span[text()='"+Tabname+"']/ancestor::span[@class='tabs__tab tabs__selected']/ancestor::div[@class='sheet__tabs']/following-sibling::div//div[@class='list']/div[@class='list__item']["+x+"]//label")).getText();

					if(SelectCharvalue.equals(s))
					{
						driver.findElement(By.xpath("//label[text()=\""+SelectCharvalue+"\"]/ancestor::header/button/div")).click();
						p=1;
						break;
					}
					else
					{
						p=0;
					}

				}


			}
			Thread.sleep(25000);
			// Add wait for confirm button
			driver.findElement(By.xpath("//button[@title='Confirm']")).click();	
			Thread.sleep(10000);
		}
	
	}
	public static void RenameExistingProductsandComponents() throws Exception
	{

		//Renaming Parent Products

		int CountParentProducts= driver.findElements(By.xpath("//span[@class='diagram__item ps--item ps--product']")).size();
		String Descp="To Be Deleted";
		for(int i=1;i<=CountParentProducts;i++)
		{
			driver.findElement(By.xpath("//span[@class='diagram__item ps--item ps--product']["+i+"]/label")).click();
			Thread.sleep(2000);
			CommonFunctions.SendValue(driver, By.xpath("//label[text()='Component']/parent::span/following-sibling::span/input"), Descp);
		Thread.sleep(2000);
		}

		//Renaming Child Component 

		int CountComponents=driver.findElements(By.xpath("//span[@class='diagram__item ps--item ps--cmp']")).size();

		for(int j=1;j<=CountComponents;j++)
		{
			driver.findElement(By.xpath("//span[@class='diagram__item ps--item ps--cmp']["+j+"]/label")).click();
			Thread.sleep(2000);
			CommonFunctions.SendValue(driver, By.xpath("//label[text()='Component']/parent::span/following-sibling::span/input"), Descp);
			Thread.sleep(2000);
		}
	
	}
	public static void AddQty(String EstimateId)
	{
		try
		{
			HashMap<String, HashMap<String, String>> EstQtypageval = new HashMap<String, HashMap<String, String>>();
			ReadData val = new ReadData();
			EstQtypageval=val.QtyForEst(EstimateId);
			System.out.println(EstQtypageval.size());
			List<String> ListQty = new ArrayList<String>(EstQtypageval.keySet());
			Collections.sort(ListQty);
			
			for (String s : ListQty) 
			{
				System.out.println(s);
			}
	
			for(int i=0;i<ListQty.size();i++)
			{
				String Productval=ListQty.get(i);
				CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//span[text()='"+Productval+"']/parent::span[contains(@class,'grid__cell grid__cell')]"), 10000);
				String xpathforProduct="//span[text()='"+Productval+"']/parent::span[contains(@class,'grid__cell grid__cell')]";
				String QTYvalue=EstQtypageval.get(Productval).get("Qty");
				Thread.sleep(5000);
				System.out.println("Quanttity value is :-"+QTYvalue);
				String dataindexval=driver.findElement(By.xpath(xpathforProduct)).getAttribute("data-row");
	//			String[] arrOfStr = dataindexval.split("/"); 
	//			String valueofindex=arrOfStr[1];
				int resultval = Integer.parseInt(dataindexval);	
				int resultvalnextcoloum=resultval+1;
				System.out.println("Entering value for Product :- "+dataindexval);
	
				String xpathforQtyField="//div[@class='wv__content']//span[text()='"+Productval+"']/parent::span/following-sibling::span[@data-index='"+dataindexval+"/6']";
				System.out.println(xpathforQtyField);
				driver.findElement(By.xpath(xpathforQtyField)).click();
				String xpathforQtyTextInput="//div[@class='wv__content']//span[text()='"+Productval+"']/parent::span/following-sibling::span[@data-index='"+dataindexval+"/6']/input";
				Thread.sleep(2000);
				CommonFunctions.waitUntilElementisPresent(driver, By.xpath(xpathforQtyTextInput), 180);
				driver.findElement(By.xpath(xpathforQtyTextInput)).sendKeys(QTYvalue+Keys.TAB);
				Thread.sleep(2000);
			}
			
		}
		catch(Exception e)
		{
			System.err.println("Not able to Enter Quantity in Quantity tab");
			e.printStackTrace();
			Assert.fail("Failed while entering Quatity in Quantity page");
		}
	}
	public static void CreateNewEstimate(String EstimateId)throws Exception
	{ 
		try
		{
			HashMap<String, String> EstNewPage = new HashMap<String, String>();
			//ReadData val = new ReadData();
			EstNewPage=val.IdentificationPage(EstimateId);
			
			String CustCode= EstNewPage.get("CustomerCode");
			System.out.println("Customer Code is :- "+CustCode);
			String ProdLine= EstNewPage.get("ProductLine");
			String Sublineprod= EstNewPage.get("SublineProduct");
			String ContName= EstNewPage.get("ContactName");
			String SalesPCode= EstNewPage.get("SalesPersonCode");
			String SalePName= EstNewPage.get("SalesPersonName");
			String AgenCode= EstNewPage.get("AgencyCode");
			String AgenName= EstNewPage.get("AgencyName");
			String CSRN= EstNewPage.get("CSRName");
			String ProjCode= EstNewPage.get("ProjectCode");
			String EstimateType= EstNewPage.get("EstimateType");
			String OppTitle= EstNewPage.get("OpportunityTitle");
			String EstimateTitle= EstNewPage.get("EstimateTitle");
		
			HashMap<String, String> qtyEst = new HashMap<String, String>();
			
			System.out.println(qtyEst);
			String Quantity1= EstNewPage.get("Qty");
			System.out.println(EstNewPage.get("Qty"));
		
		
			//////Enter the Product
			System.out.println("Product name is "+ProdLine);
			test.log(Status.INFO, "Product name is "+ProdLine);
			///// Enter The Customer
			CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("Estimate_Customer")), CustCode);
			driver.findElement(By.xpath(OR.getProperty("Estimate_Customer"))).sendKeys(Keys.TAB);
			
		   //SelectContact
			CommonFunctions.Iquote_SelectFromDropdown(driver, "//label[text()='Contact']/following-sibling::span/span/label", ContName);
		  
			/////////Enter Salesperson
			CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Ne_SalespaersonCode")));
			//Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Ne_SalespaersonCode"))));
			CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("Ne_SalespaersonCode")), SalesPCode);
			driver.findElement(By.xpath(OR.getProperty("Ne_SalespaersonCode"))).sendKeys(Keys.TAB);
		
			//Select Product and Product Line
			CommonFunctions.Iquote_SelectFromDropdown_Text(driver, "//label[text()='Product Line']/following-sibling::span/span/input", ProdLine);
			CommonFunctions.Iquote_SelectFromDropdown_Text(driver, "//label[text()='Sub-line product']/following-sibling::span/span/input", Sublineprod);
			
			//////////Enter Agency
			CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("Ne_AgencyCode")), AgenCode);
			driver.findElement(By.xpath(OR.getProperty("Ne_AgencyCode"))).sendKeys(Keys.TAB);
			//Thread.sleep(5000);
		
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Ne_CSR"))));
			/////////Enter CSR
			CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("Ne_CSR")), CSRN);
			driver.findElement(By.xpath(OR.getProperty("Ne_CSR"))).sendKeys(Keys.TAB);
			//Thread.sleep(5000);
			
			/////////Enter Project COde
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Ne_Projectcode"))));
			CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("Ne_Projectcode")), ProjCode);
			driver.findElement(By.xpath(OR.getProperty("Ne_Projectcode"))).sendKeys(Keys.TAB);
			//Thread.sleep(5000);
		
			/////////Enter Estimate Type
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("NE_EstimateType"))));
			CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("NE_EstimateType")), EstimateType);
			driver.findElement(By.xpath(OR.getProperty("NE_EstimateType"))).sendKeys(Keys.TAB);
			//Thread.sleep(5000);
		
			////////Enter Estimate Title
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("NE_EstimateTitle"))));
			CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("NE_EstimateTitle")), EstimateTitle);
			driver.findElement(By.xpath(OR.getProperty("NE_EstimateTitle"))).sendKeys(Keys.TAB);
			//Thread.sleep(3000);
		
			
			
			CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='Quantities']"), 1000);
			CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Estimate_Quantity")));
			CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("Estimate_Quantity")), Quantity1);
			driver.findElement(By.xpath(OR.getProperty("Estimate_Quantity"))).sendKeys(Keys.TAB);
			
			
			CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Estimate_Confirm")));
			CommonFunctions.waitForPageLoad(driver);
			Thread.sleep(5000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Specification']")));
			//CommonFunctions.WaitFor_ElementVisiblity(driver, By.xpath("//span[text()='Specification']"));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Specification']")));
			int vale =driver.findElements(By.xpath(OR.getProperty("NewEstimate_Spec"))).size();
			if (vale>0)
			{
				System.out.println("Estimate Creation done");
				test.log(Status.PASS, "Estimate Creation done");
			}
			else
			{
				System.err.println("Failed to create Estimate");
				test.log(Status.FAIL, "Failed to create Estimate");
		
			}
		
			
		}
		catch(Exception e)
		{
			System.err.println("Failed in the Estimate Creation page");
			e.printStackTrace();
			
		}
	}
	
	public static void CreateProduct_and_Components(String EstimateId) throws Exception {
		//Renaming Existing Products
		//RenameExistingProductsandComponents();
		ReadData val = new ReadData();
		
		//Create Product and Components
		
		HashMap<String, HashMap<String, String>> CreateProductandComp = new HashMap<String, HashMap<String, String>>();
				
		CreateProductandComp=val.CreateProductandComponents(EstimateId);
		for (Entry<String, HashMap<String, String>> entry : CreateProductandComp.entrySet()) {
	        Map<String, String> childMap = entry.getValue();
	        if(childMap.get("ComponentTypeDesc") != null)
	        {

				driver.findElement(By.xpath("//span[@title='Add Component']")).click();
				Thread.sleep(5000);
				String xpathforComp="//ul[@class='select ']//span[@class='renderer inline-img' and text()='"+childMap.get("ComponentTypeDesc")+"']";
				System.out.println(xpathforComp);
				driver.findElement(By.xpath(xpathforComp)).click();

				Thread.sleep(3000);
				
				int sizeval=driver.findElements(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--product']")).size();
				String xpathval="//span[@class='diagram']/span[@class='diagram__item ps--item ps--product']["+sizeval+"]/label";
				System.out.println("Xpath is :- "+xpathval);
				Thread.sleep(3000);
				driver.findElement(By.xpath(xpathval)).click(); 
				Thread.sleep(2000);
				CommonFunctions.SendValue(driver, By.xpath("//label[text()='Component']/parent::span/following-sibling::span/input"), childMap.get("ComponentDescription"));
				Thread.sleep(3000);
	        }
	        
	    }
	}
	
	public static void CreateProduct_and_Components(String EstimateID,String IdItemOption) throws Exception {
		test.log(Status.INFO, "Creating Product and components");
		//Renaming Existing Products
		RenameExistingProductsandComponents();
		ReadData val = new ReadData();
		
		//Create Product and Components
		
		HashMap<String, HashMap<String, String>> CreateProductandComp = new HashMap<String, HashMap<String, String>>();
				
		//CreateProductandComp=val.CreateProductandComponents(EstimateID);
		CreateProductandComp=val.CreateProductandComponents(EstimateID,IdItemOption);
		for (Entry<String, HashMap<String, String>> entry : CreateProductandComp.entrySet()) {
	        Map<String, String> childMap = entry.getValue();
	        if(childMap.get("ComponentTypeDesc") != null)
	        {
	        	WebElement el=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Add Component']")));
				driver.findElement(By.xpath("//span[@title='Add Component']")).click();
				Thread.sleep(5000);
				String xpathforComp="//ul[@class='select ']//span[@class='renderer inline-img' and text()='"+childMap.get("ComponentTypeDesc")+"']";
				System.out.println(xpathforComp);
				int beforesize=driver.findElements(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--product']")).size();
				int aftersize;
				driver.findElement(By.xpath(xpathforComp)).click();
				do {
					aftersize=driver.findElements(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--product']")).size();
					Thread.sleep(1000);
				}while(aftersize!=beforesize+1);
				
				
				int sizeval=driver.findElements(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--product']")).size();
				Thread.sleep(3000);
				String xpathval="//span[@class='diagram']/span[@class='diagram__item ps--item ps--product']["+sizeval+"]/label";
				System.out.println("Xpath is :- "+xpathval);
				Thread.sleep(3000);
				driver.findElement(By.xpath(xpathval)).click(); 
				Thread.sleep(2000);
				CommonFunctions.SendValue(driver, By.xpath("//label[text()='Component']/parent::span/following-sibling::span/input"), "To be renamed");
				Thread.sleep(2000);
				CommonFunctions.SendValue(driver, By.xpath("//label[text()='Component']/parent::span/following-sibling::span/input"), childMap.get("ComponentDescription"));
				
				
				Thread.sleep(3000);
	        }
	        
	    }
		
		DeleteRenamedProductsandComponents();
		Thread.sleep(3000);
	}
	private static Object Ucase(String attribute) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void ParentChildCombination(int EstimateId) throws ClassNotFoundException, IOException, SQLException, InterruptedException {
		Thread.sleep(5000);
		//DeleteRenamedProductsandComponents();
		ReadData val = new ReadData();
		//Link the product and components
		HashMap<String, HashMap<String, String>> LinkProductandComp = new HashMap<String, HashMap<String, String>>();
		//LinkProductandComp=val.ParentChildComponent(EstimateId, "", "");
		//int u=0;
		for (Entry<String, HashMap<String, String>> entry : LinkProductandComp.entrySet()) {
			 Map<String, String> childMap = entry.getValue();
		        if(childMap.get("ParentComponent") != null)
		        {
		        	driver.findElement(By.xpath("//label[text()='"+childMap.get("ParentComponent")+"']")).click();
					Thread.sleep(3000);
					driver.findElement(By.xpath("//label[text()='"+childMap.get("ParentComponent")+"']")).click();
					Thread.sleep(3000);
					driver.findElement(By.xpath("//span[text()='Composition']")).click();
					if(driver.findElements(By.xpath("//b[text()='Composition']/parent::label/parent::header")).size()>0)
					{
						
						int totalcells=driver.findElements(By.xpath("//header[text()='Composed by']/parent::div//label[text()='Component']/parent::span//ancestor::div[@class='grid__box']//div[@aria-label='grid'][2]//span[contains(@class,'grid__cell grid__cell')]")).size();

						int totalrow=totalcells/6;
						int nextrow=totalrow;
		                System.out.println("Child Component for link is :- "+childMap.get("ChildComponent"));
						int valuepresent=driver.findElements(By.xpath("//header[text()='Composed by']/parent::div//label[text()='Component']/parent::span//ancestor::div[@class='grid__box']//div[@aria-label='grid']//span[text()='"+childMap.get("ChildComponent")+"']")).size();
						String valuepresentStr=Integer.toString(valuepresent);
						System.out.println("Element Present is :- "+valuepresentStr);
						if (valuepresentStr.equals("0"))
						{
							driver.findElement(By.xpath("//header[text()='Composed by']/parent::div/span/div/div/div/button[1]")).click();
							Thread.sleep(2000);
							driver.findElement(By.xpath("//header[text()='Composed by']/parent::div//label[text()='Component']/parent::span//ancestor::div[@class='grid__box']//div[@aria-label='grid'][2]//span[@data-index='"+nextrow+"/0']")).click();
							Thread.sleep(2000);
							String Level1SelectChild="//b[text()='Composition']/ancestor::div[@class='popup__portal program__popup']/following-sibling::div//li/label[text()='"+childMap.get("ChildComponent")+"']";
							System.out.println(Level1SelectChild);
							driver.findElement(By.xpath(Level1SelectChild)).click();
							Thread.sleep(2000);
					
						}
						
						driver.findElement(By.xpath("//button[@title='Confirm']")).click();
						Thread.sleep(2000);
					
					}
		        }
		}
		
	}
	
	public static void ParentChildCombination(String EstimateId,String Option) throws InterruptedException, ClassNotFoundException, IOException, SQLException {
		test.log(Status.INFO, "Parent child combination");
		Thread.sleep(5000);
		//DeleteRenamedProductsandComponents();
		ReadData val = new ReadData();
		//Link the product and components
		HashMap<String, HashMap<String, String>> LinkProductandComp = new HashMap<String, HashMap<String, String>>();
		LinkProductandComp=val.ParentChildComponent(EstimateId,Option);
		//int u=0;
		for (Entry<String, HashMap<String, String>> entry : LinkProductandComp.entrySet()) {
			 Map<String, String> childMap = entry.getValue();
		        if(childMap.get("ParentComponent") != null)
		        {
		        	Thread.sleep(3000);
		        	driver.findElement(By.xpath("//label[text()='"+childMap.get("ParentComponent")+"']")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//label[text()='"+childMap.get("ParentComponent")+"']")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//label[text()='"+childMap.get("ParentComponent")+"']")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//span[text()='Composition']")).click();
					if(driver.findElements(By.xpath("//b[text()='Composition']/parent::label/parent::header")).size()>0)
					{
						
						int totalcells=driver.findElements(By.xpath("//header[text()='Composed by']/parent::div//label[text()='Component']/parent::span//ancestor::div[@class='grid__box']//div[@aria-label='grid'][2]//span[contains(@class,'grid__cell grid__cell')]")).size();

						int totalrow=totalcells/6;
						int nextrow=totalrow;
		                System.out.println("Child Component for link is :- "+childMap.get("ChildComponent"));
						int valuepresent=driver.findElements(By.xpath("//header[text()='Composed by']/parent::div//label[text()='Component']/parent::span//ancestor::div[@class='grid__box']//div[@aria-label='grid']//span[text()='"+childMap.get("ChildComponent")+"']")).size();
						String valuepresentStr=Integer.toString(valuepresent);
						System.out.println("Element Present is :- "+valuepresentStr);
						if (valuepresentStr.equals("0"))
						{
							driver.findElement(By.xpath("//header[text()='Composed by']/parent::div/span/div/div/div/button[1]")).click();
							Thread.sleep(2000);
							driver.findElement(By.xpath("//header[text()='Composed by']/parent::div//label[text()='Component']/parent::span//ancestor::div[@class='grid__box']//div[@aria-label='grid'][2]//span[@data-index='"+nextrow+"/0']")).click();
							Thread.sleep(2000);
							String Level1SelectChild="//b[text()='Composition']/ancestor::div[@class='popup__portal program__popup']/following-sibling::div//li/label[text()='"+childMap.get("ChildComponent")+"']";
							System.out.println(Level1SelectChild);
							Thread.sleep(2000);
							driver.findElement(By.xpath(Level1SelectChild)).click();
							Thread.sleep(2000);
					
						}
						
						driver.findElement(By.xpath("//button[@title='Confirm']")).click();
						Thread.sleep(2000);
					
					}
		        }
		}
		
	
	}
	public static void AddQuantity(String EstimateId,String Option) throws InterruptedException, ClassNotFoundException, IOException, SQLException
	{
		Thread.sleep(2000);
		test.log(Status.INFO, "Adding Quantity");
		ReadData val = new ReadData();
		
		try
		{
			ArrayList<String> Name=new ArrayList<>();
			ArrayList<String> Qtyval = new ArrayList<>();
			 int g = 1;
			HashMap<Integer, HashMap<String, String>> LinkProductandComp = new HashMap<Integer, HashMap<String, String>>();
			LinkProductandComp=val.QtyForEst(EstimateId, Option);
			System.out.println(LinkProductandComp.size());
			Thread.sleep(2000);
			//int i=1;
			 int j=0;
			 for (Entry<Integer, HashMap<String, String>> entry : LinkProductandComp.entrySet()) {
				
				
			            Map<String, String> childMap1 = entry.getValue();
			            for (Entry<String, String> entry2 : childMap1.entrySet())
			            {
					            String childKey = entry2.getKey();
					            String childValue = entry2.getValue();
					          	Name.add(childKey);	   		
					          	Qtyval.add(childValue);
								
					     }
			            Thread.sleep(5000);
			            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='wvtb__prop']//button")));
			             driver.findElement(By.xpath("//span[@class='wvtb__prop']//button")).click();
				    	 Thread.sleep(5000);
				    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'New quantity')]")));
				    	 driver.findElement(By.xpath("//li[contains(text(),'New quantity')]")).click();
				    	
			            for (int q=0;q<Qtyval.size();q++) {
			            	
					    	 System.out.println("//span[contains(text(),'"+Name.get(q)+"')]//..//following-sibling::div//input");
					    	 String proname=Name.get(q);
					    	 System.out.println("//span[contains(text(),'"+proname+"')]//..//following-sibling::div//input");
					    	 List<WebElement> qty=driver.findElements(By.xpath("//span[contains(text(),'"+proname+"')]//..//following-sibling::div//input"));
					    	String temp=Qtyval.get(q);
					    	temp.replace(".0", "");
					    	System.out.println(qty.size());
					    	//int g=qty.size()-1;
					    	Thread.sleep(2000);
					    	String xpath="//span[contains(text(),'"+proname+"')]//..//following-sibling::div//input["+g+"]";
					    	if(g>1) {
					    		xpath="//span[contains(text(),'"+proname+"')]//..//following-sibling::div//input["+qty.size()+"]";
					    		
					    	}
					    	else {
					    		xpath="//span[contains(text(),'"+proname+"')]//..//following-sibling::div//input["+g+"]";
					    	}
					    	
					    	
					    	Thread.sleep(2000);
					    	driver.findElement(By.xpath(xpath)).click();
					    	Thread.sleep(2000);
					    	driver.findElement(By.xpath(xpath)).sendKeys(temp+Keys.TAB);
					    			
					    	 Thread.sleep(2000);
						}
			            g+=1;
			            driver.findElement(By.xpath("//button[@class='lkv' and @title='Confirm']")).click();
			            Thread.sleep(5000);
			            Name.clear();
			            Qtyval.clear();
			        j+=1;
			 }
			
		
			String OptionName=val.ReturnOptionDesForEstandOption(EstimateId, Option);
			System.out.println(OptionName);
			/*if(!OptionName.equalsIgnoreCase("Option")) {
				driver.findElement(By.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']//span[@data-index='"+Optionqty+"/0']//span[contains(text(),'Option')]")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//input[contains(@type,'text') and contains(@value,'Option')]")).sendKeys(OptionName+Keys.TAB);
				Optionqty+=1;
			}*/
			
			List <WebElement> options = driver.findElements(By.xpath("//span[@title='Option']//..//..//following-sibling::div[@role='grid']//span[contains(@data-index,'/0')]//span"));
			int size=options.size();
			String xpath="(//span[@title='Option']//..//..//following-sibling::div[@role='grid']//span[contains(@data-index,'/0')]//span)["+size+"]";
			/*for(WebElement option:options) {
				if(option.getAttribute("textContent")!=null)  {
					if(option.getAttribute("textContent").contains("Option")) {
						option.click();
						Thread.sleep(5000);
						option.sendKeys(OptionName+Keys.TAB);
						break;
						
					}
					
				}
			}*/
			driver.findElement(By.xpath(xpath)).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[contains(@type,'text') and contains(@value,'Option')]")).sendKeys(OptionName+Keys.TAB);
			
			//write save and handle the exception
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='lkv' and @title=' ']")));
			Thread.sleep(5000);
			if(driver.findElements(By.xpath("//button[@class='lkv' and @title=' ']")).size()>0){
				driver.findElement(By.xpath("//button[@class='lkv' and @title=' ']")).click();
				//Thread.sleep(10000);
				Boolean ele1=wait.until(ExpectedConditions.attributeToBe(By.xpath("//button[@class='lkv' and @title=' ']"), "disabled", "true"));
				if(driver.findElements(By.xpath("//button[@class='lkv' and @title='OK']")).size()>0){
					Thread.sleep(2000);
					driver.findElement(By.xpath("//button[@class='lkv' and @title='OK']")).click();
					
					
				}
			}
			else {
				System.out.println("Save is not enable to click to overcome the freeze issue");
			}
			
			
			
			 	
		}
		catch(Exception e)
		{
			System.err.println("Not able to Enter Quantity in Quantity tab");
			e.printStackTrace();
			
		}
	
	}
	public static void ComponentandCharacteristics_ForPaperSpec(String EstimateId,String IdItemOption)
	{
		test.log(Status.INFO, "Adding characteristics for components");
		Estimatepage_Characteristics EPC = new Estimatepage_Characteristics();
		try {
			
			HashMap<String, HashMap<String, String>> CreateProductandComp = new HashMap<String, HashMap<String, String>>();
			HashMap<String, HashMap<String, String>>Characteristicsforcomp = new HashMap<String, HashMap<String, String>>();
			
			CreateProductandComp=val.CreateProductandComponents(EstimateId,IdItemOption);
			List<String> listCompOrder = new ArrayList<String>(CreateProductandComp.keySet());
			// List<String> CharacterCompCharacterComp = new ArrayList<String>();
			Collections.sort(listCompOrder);

			System.out.println("All the Component order are :- "+listCompOrder);

			for(int i=0;i<listCompOrder.size();i++)
			{
				String Comporderval= listCompOrder.get(i);

				Characteristicsforcomp.clear();
				System.out.println("Component order is  :- "+Comporderval);
				Characteristicsforcomp=val.CharacteristicForEachComponent(EstimateId,IdItemOption, Comporderval);
				String CompDescp= CreateProductandComp.get(Comporderval).get("ComponentDescription");
				String XpathForComponent="//label[text()='"+CompDescp+"']";
				Thread.sleep(3000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XpathForComponent)));
				driver.findElement(By.xpath(XpathForComponent)).click();
				//Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Component']")));
				driver.findElement(By.xpath("//label[text()='Component']")).click();
				List<String> CharacterCompCharacterComp = new ArrayList<String>(Characteristicsforcomp.keySet());
				Collections.sort(CharacterCompCharacterComp);
				System.out.println(CharacterCompCharacterComp);

				List<String> FixedCharComponents= new ArrayList<String>();
				for (String FixedChar:CharacterCompCharacterComp)
				{
					String fChar=Characteristicsforcomp.get(FixedChar).get("CharacteristicDesc");
					FixedCharComponents.add(fChar);
				}
				System.out.println("Fixed Characteristics Value are :- "+FixedCharComponents);
				//Removing and adding the characteristics Starts
				Estimate.UncheckUnwantedCharacteristics(FixedCharComponents);
				Estimate.AddCharacteristics(FixedCharComponents, CompDescp);
				for(String Characteristic:CharacterCompCharacterComp)
				{
					System.out.println("Characteristic is :- "+Characteristic);
					String FixedCharDescp=Characteristicsforcomp.get(Characteristic).get("FixedCharacteristicDesc");

					System.out.println("Fixed Characteristic Description is :- "+FixedCharDescp);
					
					switch(FixedCharDescp)
					{
					
					case "qttCModelGraphCarac.qttCPGraphMedia":
						EPC.Charactertics_CPGraphMedia(EstimateId,IdItemOption, Comporderval);	    
						break;			
					case "qttCModelGraphCarac.qttCPGraphBindStitch":
						EPC.Charactertics_CPGraphBindStitch(EstimateId, IdItemOption,Comporderval, Characteristic);
						break;
					case "qttCModelGraphCarac.qttCPGraphColorVanish":
						EPC.Charactertics_CPGraphColorVanish(EstimateId, IdItemOption,Comporderval);
						break;
					case "qttCModelGraphCarac.qttCPGraphRegularCoverFormat":
					case "qttCModelGraphCarac.qttCPGraphRegularFormat":	
					case "qttCModelGraphCarac.qttCPGraphRegularFormatSheet":
					case "qttCModelGraphCarac.qttCPGraphRegularOpenFormatModels":
					case "qttCModelGraphCarac.qttCPGraphEndSheetFormat":	
					case "qttCModelGraphCarac.qttCPGraphRegularCoverOpenFormat":
						EPC.Charactertics_CPGraphRegularFormat(EstimateId,IdItemOption, Comporderval);
						break;
					case "qttCModelGraphCarac.qttCPGraphHotStamping":
						EPC.Charactertics_CPGraphHotStamping(EstimateId, IdItemOption,Comporderval, Characteristic);
						break;
					case "qttCModelGraphCarac.qttCPGraphBindGlue":
						EPC.Charactertics_CPGraphBindGlue(EstimateId, IdItemOption,Comporderval, Characteristic);
						break;
					case "qttCModelPS.qttCPPlant":
						EPC.Charactertics_CPPlant(EstimateId, IdItemOption,Comporderval, Characteristic);
						break;
					case "qttCModelPS.qttCPGenericCPOptionDesc":
					case "qttCModelPS.qttCPGenericCPOptionValues":
						EPC.Charactertics_CPGenericCPOptionDesc(EstimateId,IdItemOption, Comporderval, Characteristic) ;
						break;
					case "qttCModelPS.qttCPNote":
						EPC.Charactertics_CPNote(EstimateId, IdItemOption,Comporderval, Characteristic) ;
						break;
					case "qttCModelGraphCarac.qttCPAGraphPageProof"	:
					case "qttCModelGraphCarac.qttCPAGraphPageProof2":
					case "qttCModelGraphCarac.qttCPAGraphPageProof3":
						EPC.Charactertics_CPAGraphPageProof(EstimateId,IdItemOption, Comporderval, Characteristic);
						break;
					case "qttCModelPS.qttCPValue":
						EPC.Charactertics_CPValue(EstimateId,IdItemOption, Comporderval, Characteristic);
						break;
					case "qttCModelPS.qttCPASimpleQty":
					case "qttCModelPS.qttCPASimple":
						EPC.Charactertics_CPASimpleQty(EstimateId,IdItemOption, Comporderval, Characteristic);
						break;
					case "qttCModelGraphCarac.qttCPGraphFiber":
						EPC.Charactertics_CPGraphFiber(EstimateId, IdItemOption,Comporderval, Characteristic) ;
						break;
					case "qttCModelGraphCarac.qttCPGraphPackagingBox":
						EPC.Charactertics_CPGraphPackBox(EstimateId,IdItemOption, Comporderval, Characteristic) ;
						break;
					case "qttCModelPS.qttCPFileList":
						//EPC.Charactertics_CPFileList(EstimateId,IdItemOption, Comporderval, Characteristic) ;
						break;
					case "qttCModelGraphCarac.qttCPGraphLargeFormat":
						EPC.Charactertics_CPGraphLargeFormat(EstimateId,IdItemOption, Comporderval, Characteristic) ;
						break;
					case "qttCModelGraphCarac.qttCPGraphGiantCoupling":
						//EPC.charactertics_CPGraphGiantCoupling(EstimateId,IdItemOption, Comporderval, Characteristic) ;
						break;
					case "qttCModelGraphCarac.qttCPGraphBleed":
						EPC.Charactertics_CPGraphBleed(EstimateId,IdItemOption, Comporderval, Characteristic) ;
						break;
					case "qttCModelGraphCarac.qttCPGraphUnfinishedFormat":
                        EPC.Charactertics_CPGraphUnfinishedFormat(EstimateId,IdItemOption, Comporderval, Characteristic);
                        break;                        
                  case "qttCModelGraphCarac.qttCPGraphLaminating":
                        EPC.Charactertics_CPGraphInitialLaminating(EstimateId,IdItemOption, Comporderval, Characteristic);
                        break;
                  case "qttCModelGraphCarac.qttCPGraphHardCover":
  						EPC.Charactertics_CPGraphHardCover(EstimateId, IdItemOption,Comporderval, Characteristic) ;     
	  					break;
                  case "qttCModelGraphCarac.qttCPGraphMaxMultiline":
	  					EPC.Charactertics_CPGraphMaxMultiLine(EstimateId, IdItemOption,Comporderval, Characteristic);
	  					break;
                  case "qttCModelGraphCarac.qttCPGraphPackagingPallet":
  						EPC.Charactertics_CPGraphPackPallet(EstimateId,IdItemOption, Comporderval, Characteristic) ; 
  						break;
                  case "qttCModelGraphCarac.qttCPGraphPrintType":	
  					EPC.Charactertics_CPGraphPrintType(EstimateId,IdItemOption, Comporderval, Characteristic);
  					break;
                  case "qttCModelGraphCarac.qttCPGraphPackagingStrapping":
                	 EPC.Charactertics_CPGraphPackagingStrapping(EstimateId,IdItemOption, Comporderval, Characteristic);
                	  break;
                  case "qttCModelGraphCarac.qttCPGraphGIrregFormat":
  					EPC.Charactertics_CPGraphGIrregFormat(EstimateId,IdItemOption, Comporderval, Characteristic);
  					break;
                  case "qttCModelGraphCarac.qttCPGraphLabelFormat":
  					EPC.Charactertics_CPGraphLabelFormat(EstimateId, IdItemOption,Comporderval, Characteristic);
  					break;
                  case "qttCModelPS.qttCPGenericCPOption":
  					EPC.Charactertics_CPGenericCPOption(EstimateId, IdItemOption, Comporderval, Characteristic) ;
  					break;
                  case "qttCModelPS.qttCPAOptionQty":
  					EPC.Charactertics_CPAOptionQty(EstimateId, IdItemOption, Comporderval, Characteristic);
  					break;
                  case "qttCModelGraphCarac.qttCPGraphDieCut":
  					EPC.Charactertics_CPGraphDieCut(EstimateId, IdItemOption, Comporderval, Characteristic);
  					break;
                  case "qttCModelGraphCarac.qttCPGraphWireOBind":
  					EPC.Charactertics_CPGraphWireOBind(EstimateId, IdItemOption, Comporderval, Characteristic);
  					break;
                  case "qttCModelPS.qttCPGenericRawMaterialValue":
  					EPC.Charactertics_CPGenericRawMaterial(EstimateId, IdItemOption, Comporderval, Characteristic);
  					break;

					default:
						System.out.println("Characteristic that is not present : "+FixedCharDescp);
						
					}

				}
			}
				
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void AddQtyForProductForOption(HashMap<String,HashMap<String,HashSet<Double>>> Quantities,String EstimateID) throws Exception {
		ReadData name = new ReadData();
		HashMap<String, HashMap<String, String>> CreateProductandComp = new HashMap<String, HashMap<String, String>>();
		HashMap<String, HashMap<String, String>> ParentChildCombinationWithLinkData = new HashMap<String, HashMap<String, String>>();
		for (Entry<String, HashMap<String, HashSet<Double>>> entry : Quantities.entrySet()) {
	        Map<String, HashSet<Double>> childMap = entry.getValue();

	        for (Entry<String, HashSet<Double>> entry2 : childMap.entrySet()) {
	            String childKey = entry2.getKey();
	            HashSet<Double> childValue = entry2.getValue();
	          
	            
	            for (String key : Quantities.keySet()) {
	            	System.out.println(key + "=" + Quantities.get(key));
	            	
	            	CreateProductandComp=name.CreateProductandComponents(EstimateID,key);
	            	for (Entry<String, HashMap<String, String>> entry3 : CreateProductandComp.entrySet()) {
	        	        Map<String, String> childMap1 = entry3.getValue();
	        	        if(childMap1.get("ComponentTypeDesc") != null)
	        	        {

	        				driver.findElement(By.xpath("//span[@title='Add Component']")).click();
	        				Thread.sleep(5000);
	        				String xpathforComp="//ul[@class='select ']//span[@class='renderer inline-img' and text()='"+childMap1.get("ComponentTypeDesc")+"']";
	        				System.out.println(xpathforComp);
	        				driver.findElement(By.xpath(xpathforComp)).click();

	        				Thread.sleep(3000);
	        				
	        				int sizeval=driver.findElements(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--product']")).size();
	        				String xpathval="//span[@class='diagram']/span[@class='diagram__item ps--item ps--product']["+sizeval+"]/label";
	        				System.out.println("Xpath is :- "+xpathval);
	        				Thread.sleep(3000);
	        				driver.findElement(By.xpath(xpathval)).click(); 
	        				Thread.sleep(2000);
	        				CommonFunctions.SendValue(driver, By.xpath("//label[text()='Component']/parent::span/following-sibling::span/input"), childMap1.get("ComponentDescription"));
	        				Thread.sleep(3000);
	        	        }
	        	        
	            	}
		        //Product and child combination
	            	//ParentChildCombination(EstimateID);
	            	
	           
	            }
	        }
		}
		
	}
	
	public static String StatusChangeTo(String EstimateStatus, String ValidateStatus, String CutomerPONum, String Inventory ) throws Exception
	{


		String ScreenShotStatusChange="";
		
		driver.findElement(By.xpath("//div[starts-with(@class,'wvtb wvtb')]//span[span[img[@class='status-flag']]]//*[@class='icon dropdown']")).click();
		driver.findElement(By.xpath("//div[starts-with(@class,'wvtb wvtb')]//span[span[img[@class='status-flag']]]//*[@class='icon dropdown']")).click();
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//div[@class='drop-down']//span[contains(text(),'"+EstimateStatus+"')]"), 180);  
		CommonFunctions.ClickElement(driver, By.xpath("//div[@class='drop-down']//span[contains(text(),'"+EstimateStatus+"')]"));
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//div[@class='popup__portal program__popup']/div[@class='program']"), 180);  

		if (driver.findElements(By.xpath("//div[@class='popup__portal program__popup']/div[@class='program']")).size()>0)
		{
			CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='Customer Order no.']"), 180);
			CommonFunctions.ClickElement(driver, By.xpath("//label[text()='Customer Order no.']/ancestor::span[@class= 'ltv__item__label ltv_ ltv_last']/span/input"));
			CommonFunctions.SendValue(driver, By.xpath("//label[text()='Customer Order no.']/ancestor::span[@class= 'ltv__item__label ltv_ ltv_last']/span/input"), CutomerPONum);

			String Futuredate= CommonFunctions.futureDateinMMddyyyyFormat(5);
			System.out.println("Future Date is :- "+Futuredate);
			
			int Deliverydatetotalval=driver.findElements(By.xpath("//label[text()='Apply Delivery Date']/ancestor::div[@class='grid grid-normal']//span[contains(@class,'grid__cell grid__cell--')]")).size();
			String valueofDeliverydate=driver.findElement(By.xpath("//label[text()='Apply Delivery Date']/ancestor::div[@class='grid grid-normal']//span[contains(@class,'grid__cell grid__cell--')]["+Deliverydatetotalval+"]")).getAttribute("data-row");
			int TotalRowsFordelivery=Integer.parseInt(valueofDeliverydate);
			System.out.println("Total rows to select for data Change is :- "+TotalRowsFordelivery);
			for(int i=0;i<=TotalRowsFordelivery;i++)
			{
				driver.findElement(By.xpath("//label[text()='Apply Delivery Date']/ancestor::div[@class='grid grid-normal']//span[@data-index='"+i+"/2']")).click();
				driver.findElement(By.xpath("//label[text()='Apply Delivery Date']/ancestor::div[@class='grid grid-normal']//span[@data-index='"+i+"/2']/div/span[@class='datepicker input-wraper']/input")).click();
				/*driver.findElement(By.xpath("//label[text()='Apply Delivery Date']/ancestor::div[@class='grid grid-normal']//span[@data-index='"+i+"/2']/div/span/input")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
				driver.findElement(By.xpath("//label[text()='Apply Delivery Date']/ancestor::div[@class='grid grid-normal']//span[@data-index='"+i+"/2']/div/span/input")).sendKeys(Keys.DELETE);
				driver.findElement(By.xpath("//label[text()='Apply Delivery Date']/ancestor::div[@class='grid grid-normal']//span[@data-index='"+i+"/2']/div/span/input")).sendKeys(Keys.TAB);*/
				Thread.sleep(2000);
				//driver.findElement(By.xpath("//label[text()='Apply Delivery Date']/ancestor::div[@class='grid grid-normal']//span[@data-index='"+i+"/2']/div/span/input")).click();
				driver.findElement(By.xpath("//label[text()='Apply Delivery Date']/ancestor::div[@class='grid grid-normal']//span[@data-index='"+i+"/2']/div/span[@class='datepicker input-wraper']/input")).sendKeys(Keys.HOME);
				driver.findElement(By.xpath("//label[text()='Apply Delivery Date']/ancestor::div[@class='grid grid-normal']//span[@data-index='"+i+"/2']/div/span[@class='datepicker input-wraper']/input")).sendKeys(Futuredate);
				driver.findElement(By.xpath("//label[text()='Apply Delivery Date']/ancestor::div[@class='grid grid-normal']//span[@data-index='"+i+"/2']/div/span[@class='datepicker input-wraper']/input")).sendKeys(Keys.TAB);
				Thread.sleep(2000);
//				driver.findElement(By.xpath("//header[text()='Deliveries']")).click();
//				Thread.sleep(2000);
			}

			if (Inventory !=""|| Inventory !=null)
			{

				System.out.println("Entering Inventory data :"+Inventory);
				CommonFunctions.ClickElement(driver, By.xpath("//header[text()='Select the option that will be approved']/parent::div//div[@class='grid__box']//span[@data-index='0/3']"));
				Thread.sleep(2000);
				//          driver.findElement(By.xpath("//header[text()='Select the option that will be approved']/parent::div//div[@class='grid__box']//span[@data-index='0/3']/span/input[1]")).sendKeys(Inventory);
				//              driver.findElement(By.xpath("//header[text()='Select the option that will be approved']/parent::div//div[@class='grid__box']//span[@data-index='0/3']/span/input[1]")).sendKeys(Keys.TAB);
				
				
			}
			else
			{

				System.out.println("Inventory option is not selected");
			}

		}
		else
		{
			System.err.println("Status Changing Pop-up page is not displayed");
		}

	
		CommonFunctions.ClickElement(driver,By.xpath("//b[text()='Status changing']/ancestor::div[@class='program']/div[@class='program__window']//button[@title='Confirm']"));
		Thread.sleep(20000);
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//span[@class='input-wraper status']//span[contains(text(),'In Production')]"), 120000);
		String StatusChange= driver.findElement(By.xpath(OR.getProperty("statusCheck"))).getText();
		Thread.sleep(5000);
		System.out.println("Value is "+StatusChange);
		if (StatusChange.contains(ValidateStatus))
		{
			System.out.println("Status Changed Successfully ");
		}

		else
		{
			System.err.println("Status not Changed Successfully");
		}
		
		return ScreenShotStatusChange;
	
	}
	//Santoshi
    public String RemunerationGroup(String RemuDesc, String CostAccName,String MarginVal) throws Exception
    {


          driver.findElement(By.xpath("//input[@placeholder='Quick Searching']")).click();

          driver.findElement(By.xpath("//input[@placeholder='Quick Searching']")).sendKeys("Remuneration Group");


          driver.findElement(By.xpath("//input[@placeholder='Quick Searching']")).sendKeys(Keys.ENTER);
          CommonFunctions.waitUntilElementisVisible(driver, (By.xpath("//label[text()='Remuneration Group']")), 5000);
          driver.findElement(By.xpath(("//label[text()='Remuneration Group']"))).click();
          if(driver.findElements(By.xpath("//button[@title='Edit Remuneration Group']")).size()>0)
          {

                System.out.println("Navigate to remuneration page succesfully");
          }
          else
          {
                System.err.println("Navigate to remuneration page Failed");
          }
          
          driver.findElement(By.xpath("//span[text()='"+RemuDesc+"']")).click();
          
          driver.findElement(By.xpath("//button[@title='Edit Remuneration Group']")).click();
          CommonFunctions.waitForPageLoad(driver);
          CommonFunctions.waitUntilElementisClickable(driver, (By.xpath("//label[text()='"+CostAccName+"']/../../following-sibling::span[1]")), 5000);
          
          
          System.out.println("*************Fetching the original Margin Value************************");
          driver.findElement(By.xpath("//label[text()='"+CostAccName+"']/../../following-sibling::span[1]")).click();
          Thread.sleep(5000);
          String OriginalVal= driver.findElement(By.xpath("//label[text()='"+CostAccName+"']/../../following-sibling::span[1]/input")).getAttribute("value");
          Thread.sleep(10000);
          System.out.println("Original value of costAccount:" +OriginalVal);
          System.out.println("*************Fetching the original Margin Value Ends************************");
    
          
          driver.findElement(By.xpath("//label[text()='"+CostAccName+"']/../../following-sibling::span[1]/input")).sendKeys(MarginVal,Keys.TAB);
          Thread.sleep(10000);
          CommonFunctions.waitUntilElementisClickable(driver, (By.xpath("//div[@class='wv']//div[@class='wvtb wvtb__size0']//following-sibling::button[1]")), 10000);
          driver.findElement(By.xpath("//div[@class='wv']//div[@class='wvtb wvtb__size0']//following-sibling::button[1]")).click();
          CommonFunctions.waitUntilElementisVisible(driver, (By.xpath("//button[@title='Edit Remuneration Group']")), 2000);
          if(driver.findElements(By.xpath("//button[@title='Edit Remuneration Group']")).size()>0)
          {
                System.out.println("remuneration margin value saved successfully");
          }
          else
          {
                System.out.println("remuneration margin value saved failed");     
          }
          
          return OriginalVal;
    }
    
    
    
    public void RemuOriginalValue(String RemuDesc, String CostAccName,String OriginalVal) throws Exception
    {


          driver.findElement(By.xpath("//input[@placeholder='Quick Searching']")).click();

          driver.findElement(By.xpath("//input[@placeholder='Quick Searching']")).sendKeys("Remuneration Group");


          driver.findElement(By.xpath("//input[@placeholder='Quick Searching']")).sendKeys(Keys.ENTER);
          CommonFunctions.waitUntilElementisVisible(driver, (By.xpath("//label[text()='Remuneration Group']")), 5000);
          driver.findElement(By.xpath(("//label[text()='Remuneration Group']"))).click();
          
          if(driver.findElements(By.xpath("//button[@title='Edit Remuneration Group']")).size()>0)
          {

                System.out.println("Navigate to remuneration page succesfully");
          }
          else
          {
                System.err.println("Navigate to remuneration page Failed");
          }
          
          driver.findElement(By.xpath("//span[text()='"+RemuDesc+"']")).click();
          
          
          //Revert orginal value
          
          
          driver.findElement(By.xpath("//button[@title='Edit Remuneration Group']")).click();
          CommonFunctions.waitForPageLoad(driver);
          CommonFunctions.waitUntilElementisClickable(driver, (By.xpath("//label[text()='"+CostAccName+"']/../../following-sibling::span[1]")), 5000);
          driver.findElement(By.xpath("//label[text()='"+CostAccName+"']/../../following-sibling::span[1]")).click();
          Thread.sleep(1000);
          
          driver.findElement(By.xpath("//label[text()='"+CostAccName+"']/../../following-sibling::span[1]/input")).sendKeys(OriginalVal,Keys.TAB);
          //click on save
          CommonFunctions.waitUntilElementisClickable(driver, (By.xpath("//div[@class='wv']//div[@class='wvtb wvtb__size0']//following-sibling::button[1]")), 10000);
          driver.findElement(By.xpath("//div[@class='wv']//div[@class='wvtb wvtb__size0']//following-sibling::button[1]")).click();
          CommonFunctions.waitUntilElementisVisible(driver, (By.xpath("//button[@title='Edit Remuneration Group']")), 2000);
          if(driver.findElements(By.xpath("//button[@title='Edit Remuneration Group']")).size()>0)
          {
                System.out.println("Original margin value saved  successfully");
          }
          else
          {
                System.out.println("Original margin value save failed");    
          }
          
    
    }



}
