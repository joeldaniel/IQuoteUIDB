package pages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.Testbase;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import utilities.CommonFunctions;
import utilities.HTML_File_Creator;
import utilities.ReadAndUpdate;
import utilities.ScreenShot;

public class JobPage extends Testbase{


	//ReadAndUpdate dbConnection = new ReadAndUpdate();
	
	//Author Sonali
	static HTML_File_Creator HTMLF= new HTML_File_Creator();
	
      
      public  static void NavigateToJobPage()throws Exception
      {
    	    CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Desktop_Label")));
            CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Nav_ToPlanning")));
            CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Nav_PlannerLogo")));
            CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Nav_jobLogo")));
            CommonFunctions.waitForPageLoad(driver);
            CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//b[text()='Job']"), 180);
            CommonFunctions.waitForPageLoad(driver);
            //CommonFunctions.ClickElement(driver, By.xpath(Locators.getProperty(Locators.PartialJob)));
            int val =driver.findElements(By.xpath(OR.getProperty("JobNumber_Field"))).size();
           // System.out.println(val);
            if (val>0)
            {
                  System.out.println("Navigation to Job Page Successfull");
            }
            else 
            {
                  System.err.println("Navigation to job Page Failed");
                  
            }
              
//         TakeScreenShot.ScreenShotWindow(driver,"JobPage");            
      }

      public static void NavigateToJobGeneralSettingage() throws Exception
      {
    	  driver.findElement(By.xpath("//label[text()='Planning']")).click();
    	  driver.findElement(By.xpath("//label[text()='Records']")).click();
    	  CommonFunctions.ClickElement(driver, By.xpath("//ul[@class='select__tree lkv__menu__select']//label[text()='Planner']"));
    	  CommonFunctions.ClickElement(driver, By.xpath("//span[@class='inline-img pointer']//label[text()='Job General Settings']"));
    	  if (driver.findElements(By.xpath("//b[text()='Job General Settings']")).size()>0)
    	  {
    		  System.out.println("Navigation To General Setting page is Successfull");
    	  }
    	  else
    	  {
    		  System.err.println("Navigation To General Setting page is not Successfull");
    		 
    	  }
      }
      
      public static void Change_jobCreationType_JobGeneralSetting(String JobCreationType) throws InterruptedException, IOException
      {
    	  ////label[text()='Create a Job for the First Product and SubJobs for Each Additional Product']
    	  driver.findElement(By.xpath("//label[text()='MIS Job Creation Type']/parent::span/span/span/label")).click();
    	  Thread.sleep(2000);
    	  driver.findElement(By.xpath("//label[text()='"+JobCreationType+"']")).click();
    	  
    	  
    	  Thread.sleep(2000);
    	  String SelectType= driver.findElement(By.xpath("//label[text()='MIS Job Creation Type']/parent::span/span/span/label")).getText();
    	  
    	  
    	  if (SelectType.equals(JobCreationType))
    	  {
    		  System.out.println("Job Type selection is "+JobCreationType);
    	  }
    	  else
    	  {
    		  System.err.println("Job Type selection is not selected "+JobCreationType);
    	  }
    	  
    	  driver.findElement(By.xpath("//button[@title='Save']")).click();
    	  
    	     	  
    	  
      }
      
      
      public static void searchJobWithEstimateNumber (String eEstimateNumber)throws Exception
      {
    	  Thread.sleep(15000);
    	  driver.findElement(By.xpath("//label[@class='inline-img renderer']")).click();
    	  Thread.sleep(2000);
    	  driver.findElement(By.xpath("//span[text()='Script Filter']")).click();
    	  Thread.sleep(2000);
    	  CommonFunctions.ClickElement(driver, By.xpath("//i[text()='Estimate > ']//ancestor::header//following-sibling::div//input"));
			driver.findElement(By.xpath("//i[text()='Estimate > ']//ancestor::header//following-sibling::div//input")).sendKeys(eEstimateNumber);
			driver.findElement(By.xpath("//i[text()='Estimate > ']//ancestor::header//following-sibling::div//input")).sendKeys(Keys.TAB);
			Thread.sleep(5000);
			CommonFunctions.ClickElement(driver, By.xpath("//label[text()='Search']"));
			//driver.findElement(By.xpath("//label[text()='Search']")).click();
			CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//span[text()='"+eEstimateNumber+"']"), 120000);
			driver.findElement(By.xpath("//span[text()='"+eEstimateNumber+"']")).click();
			Thread.sleep(6000);
			CommonFunctions.ClickElement(driver, By.xpath("//button[@title='Duplicate Job']/../button[2]/span/img")); 
			CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//header[text()='Job']"), 12000);
			CommonFunctions.waitUntilElementisVisible(driver, By.xpath("//header[text()='Job']"), 10000);
			String JobPageValidation= "//header[text()='Job']";
			if (driver.findElements(By.xpath(JobPageValidation)).size()==1)
			{
				System.out.println("Job Search Successful");
			}
			else
			{
				System.err.println("Job Search Failed");
			}
      
      }
//      
//      public static void SearchJob(String JobNumber)throws Exception
//      {//Author Sonali
//            Thread.sleep(5000);
//            CommonFunctions.ClickElement(driver, By.xpath(Locators.getProperty(Locators.JobNumber_Field)));
//            CommonFunctions.SendValue(driver, By.xpath(Locators.getProperty(Locators.JobNumber_Field)), JobNumber);
//            Thread.sleep(5000);
//            String JobNum= CommonFunctions.getAttribute(driver, By.xpath(Locators.getProperty(Locators.JobNumber_Field)), "value");
//        System.out.println("Job Number is "+JobNum);
//            CommonFunctions.ClickElement(driver, By.xpath(Locators.getProperty(Locators.Estimate_Search_Button)));
//            CommonFunctions.waitForPageLoad(driver);
//            Thread.sleep(4000);
//            String JobValidation= "//span[@class='grid__cell grid__cell--alpha grid__current ']/span[text()='"+JobNum+"']";
//            if (driver.findElements(By.xpath(JobValidation)).size()==1)
//            {
//                  System.out.println("Job Search Successful");
//            }
//            else
//            {
//                  System.err.println("Job Search Failed");
//            }
//            CommonFunctions.ClickElement(driver, By.xpath(JobValidation));
//        CommonFunctions.ClickElement(driver, By.xpath(Locators.getProperty(Locators.Job_edit_Button)));
//        CommonFunctions.waitForPageLoad(driver);
//            
//            
//      //TakeScreenShot.ScreenShotWindow(driver,"EstimatePage");            
//            
//      }
      //Author Sonali
   
      public  static void NavigateToJobMaterials()throws Exception
      {
           
    	  //CommonFunctions.ClickElement(driver, By.xpath("//span[@class='wizard__step']//label[text()='Materials']"));
    	  driver.findElement(By.xpath("//nav[@class='wizard__nav']//span[6]")).click();
            CommonFunctions.waitForPageLoad(driver);
            
            int val =driver.findElements(By.xpath("//header[text()='Materials']")).size();
           // System.out.println(val);
            if (val>0)
            {
                  System.out.println("Navigation to Job Materials Page Successfull");
            }
            else
            {
                  System.err.println("Navigation to job Materials Page Failed");
                  
            }
              
          
      }
      
      public static void JobStatusToReleaseFromLocked()throws Exception
      {//Author Sonali
    	  CommonFunctions.ClickElement(driver,By.xpath("//label[text()='Print Job']//ancestor::div[@class='wvtb wvtb__size0']//span[starts-with(@class,'wvtb__prop')]//span[@class='input-wraper status']//*[@class='icon dropdown']"));
          Thread.sleep(3000);
          CommonFunctions.ClickElement(driver, By.xpath("//span[text()='Release']"));
          Thread.sleep(3000);
          CommonFunctions.ClickElement(driver, By.xpath("//button[@title='Confirm']"));
          CommonFunctions.waitUntilElementisVisible(driver, By.xpath("//header[text()='Production Information']"), 10000);
         Thread.sleep(4000);
          String StatusChange= driver.findElement(By.xpath(OR.getProperty("statusCheck"))).getText();
          Thread.sleep(5000);
          System.out.println("Value is "+StatusChange);
          if (StatusChange.equals("Released"))
          {
                 System.out.println("Status of the job Changed to Released Successfully ");
          }

          else
          {
                 System.err.println("Job Status Change to Released failed");
          }
      }
      public static void ChangeProductionDivision(String ProductionDiv) throws Exception
      {
    	  CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Job_ProductionDivison")));
    	  Thread.sleep(2000);
    	  CommonFunctions.SendValueWithoutClear(driver, By.xpath(OR.getProperty("Job_ProductionDivison")), ProductionDiv);
  		Thread.sleep(5000);
  		driver.findElement(By.xpath(OR.getProperty("Job_ProductionDivison"))).sendKeys(Keys.DOWN);
  		driver.findElement(By.xpath(OR.getProperty("Job_ProductionDivison"))).sendKeys(Keys.ENTER);
      }
      public boolean ValidateJobStatusChange()throws Exception
      {//Author Sonali
    	  boolean flag=false;
    	  for(int i=0;i<=35;i++)
    	  {
    		  CommonFunctions.ClickElement(driver, By.xpath("//div[@class='wv']//div[@class='wvtb wvtb__size0']/span[@class='input-wraper lkv__menu lkv']//button[@title='More Actions']//*"));
    	      Thread.sleep(2000);
    	      CommonFunctions.ClickElement(driver, By.xpath("//button[@title='Refreshes the Job']"));
    	      Thread.sleep(2000);
    	      if(driver.findElements(By.xpath("//label[text()='Sucessfull']")).size()>0)
    	      {
    	    	  System.out.println("Job status changed to successfull and is pushed to MIS");
    	    	  flag=true;
    	    	  break;
    	      }
    	    
    	      Thread.sleep(10000); 
    	  
    	  }
		return flag;
      }
      //Author Santoshi
      public static String ValidateMIS_JobID() throws Exception
  	{
  		String MISJob = CommonFunctions.GetValue(driver, By.xpath(OR.getProperty("MIS_JobId")));
  		System.out.println("MISJob ID is "+MISJob);
  		return MISJob;
  	}


	//Author Sonali
	public static void SearchJob(String JobNumber)throws Exception
	{
		Thread.sleep(5000);
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("JobNumber_Field")));
		CommonFunctions.SendValue(driver, By.xpath(OR.getProperty("JobNumber_Field")), JobNumber);
		Thread.sleep(5000);
		String JobNum= CommonFunctions.getAttribute(driver, By.xpath(OR.getProperty("JobNumber_Field")), "value");
		System.out.println("Job Number is "+JobNum);
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Estimate_Search_Button")));
		CommonFunctions.waitForPageLoad(driver);
		Thread.sleep(4000);
		String JobValidation= "//span[@class='grid__cell grid__cell--alpha grid__current ']/span[text()='"+JobNum+"']";
		if (driver.findElements(By.xpath(JobValidation)).size()==1)
		{
			System.out.println("Job Search Successful");
		}
		else
		{
			System.err.println("Job Search Failed");
		}
		CommonFunctions.ClickElement(driver, By.xpath(JobValidation));
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Job_edit_Button")));
		CommonFunctions.waitForPageLoad(driver);


		//TakeScreenShot.ScreenShotWindow(driver,"EstimatePage");            

	}
	//Author Sonali
	public static void SaveJob() throws Exception
	{
		//DO not remove below code

		//          Thread.sleep(5000);
		//          CommonFunctions.waitForPageLoad(driver);
		//          Thread.sleep(5000);
		//          CommonFunctions.ClickElement(driver, By.xpath(Locators.getProperty(Locators.Job_Edit_Description)));
		//          CommonFunctions.SendValueWithoutClear(driver, By.xpath(Locators.getProperty(Locators.Job_Edit_Description)), "SheetsSR");
		//          Thread.sleep(5000);
		//    driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Edit_Description))).sendKeys(Keys.TAB);


		CommonFunctions.waitForPageLoad(driver); 
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Estimate_Save")));
		CommonFunctions.waitForPageLoad(driver);
		CommonFunctions.WaitFor_ElementVisiblity(driver, By.xpath("//label[text()='Product']"));
		String EngVisible= driver.findElement(By.xpath("//label[text()='Engineering']/parent::span")).getAttribute("data-enabled");
		System.out.println("Attribute value is :"+EngVisible);
		if(EngVisible.equalsIgnoreCase("True"))
		{
			System.out.println("JOb Save Pass");
		}
		else
		{
			System.out.println("JOb Save Fail");
		}
	}
	public static void NavigateToJobPlanning()throws Exception
	{
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Nav_PlanningTab")));
		CommonFunctions.waitForPageLoad(driver);
        //CommonFunctions.waitUntilElementisPresent(driver, By.xpath(OR.getProperty("Job_ProcessGroup_Label")), 180);
		int val =driver.findElements(By.xpath("//header[text()='Plan']")).size();
		//System.out.println(val);
		if (val>0)
		{
			System.out.println("Navigation to Job Planning Page Successfull");
		}
		else
		{
			System.err.println("Navigation to job Planning Page Failed");

		}
	}
	
	public static void PushPlanningData(String Estimate,String Sheetname) throws Exception {
		Estimate=Estimate.replace(",", "");
		//CommonFunctions.ClickElement(driver, By.xpath("//label[text()='Component']"));
		//label[text()='Component']//ancestor::div[@class='grid__box']//span[@class='cell__sort--a']
		String ExcelPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Actual\\JobPlanning.xlsx";
		ReadAndUpdate RU = new ReadAndUpdate();
		int val =driver.findElements(By.xpath("//label[text()='Component']//ancestor::div[@class='grid__box']//span[@class='cell__sort--a']")).size();
		//System.out.println(val);
		if (val>0)
		{
			System.out.println("Already sorted");
		}
		else
		{
			System.out.println("Sorting the panning now...");
			CommonFunctions.ClickElement(driver, By.xpath("//header[text()='Plan']//..//label[text()='Component']"));
			int val1 =driver.findElements(By.xpath("//label[text()='Component']//ancestor::div[@class='grid__box']//span[@class='cell__sort--a']")).size();
			//System.err.println("After Click Value is "+val1);

		}
		if(driver.findElements(By.xpath("//div[@data-selected='true']//span[contains(@class,'grid__cell')]")).size()>0) {
			String cellXpath = "//div[@data-selected='true']//span[contains(@class,'grid__cell')]";
			List<WebElement> ele = driver.findElements(By.xpath(cellXpath));	
			int rowCount = Integer.parseInt(ele.get(ele.size()-1).getAttribute("data-row"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
	
			WebElement gridBody = driver.findElement(By.xpath("//div[@data-selected='true']//div[@role='grid' and contains(@class,'body')]"));
	
			Long scrollWidth = (Long) js.executeScript("return arguments[0].scrollWidth", gridBody)*10;
			Long clientWidth = scrollWidth;
			js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
			scrollWidth = (Long) js.executeScript("return arguments[0].scrollWidth", gridBody)*10;
			js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
			Thread.sleep(2000);
			List<WebElement> ele1 = driver.findElements(By.xpath(cellXpath));	
			int colCount = Integer.parseInt(ele1.get(ele1.size()-1).getAttribute("data-index").split("/")[1]);
			//System.out.println("row count is: "+rowCount+" column count is :"+colCount);
			Thread.sleep(1000);
			js.executeScript("arguments[0].scrollBy(-"+clientWidth+",0)", gridBody);
			String[] colNames = {"Component","Activity","Planninggroup","ScheduledResource","Start","End","PlannedQty","Producedqty","Status","Productionstarted","Productionconcluded","Plant","ProductionDivision","MISJobID","MISWorkCenter","PlannedResource"};
			RU.Create_Excel(ExcelPath, Sheetname, colNames);
			for(int row=0;row<=rowCount;row++) {
				for(int col=0;col<=colCount;col++) {
					String jobPlanningValueXpath="//div[@data-selected='true']//div[@role='grid' and contains(@class,'body')]//span[@data-index='"+row+"/"+col+"']";
					WebElement Element = driver.findElement(By.xpath(jobPlanningValueXpath));
			        //This will scroll the page till the element is found		
			        js.executeScript("arguments[0].scrollIntoView();", Element);
					String innerHtml="";
					try{
						innerHtml=driver.findElement(By.xpath(jobPlanningValueXpath)).getAttribute("innerHTML").toString();
					}catch(Exception e){
						js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
						scrollWidth = (Long) js.executeScript("return arguments[0].scrollWidth", gridBody)*10;
						js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
						CommonFunctions.waitUntilElementisPresent(driver, By.xpath(jobPlanningValueXpath), 180);
						innerHtml=driver.findElement(By.xpath(jobPlanningValueXpath)).getAttribute("innerHTML").toString();
					}
					String value="";
					if (innerHtml.contains("label")) {
						value = 	driver.findElement(By.xpath(jobPlanningValueXpath+"//label")).getText();
	
					} else if(innerHtml.contains("span")) {
						value = 	driver.findElement(By.xpath(jobPlanningValueXpath+"//span")).getText();
					} else {
						value = "";
					}
					//System.out.println("Value At row "+row+" and col "+col+" is: "+value);
			
				//	RU.UpdateFunction(SheetName, "Planning", scenario+Integer.toString(row), colNames[col], value);
					RU.UpdateFunction_Iquote(ExcelPath, Sheetname, colNames[col], value, row+1);
					//Move ScrollBar to Starting Position after all Columns
					/*if(col==colCount) {
						js.executeScript("arguments[0].scrollBy(-"+clientWidth+",0)", gridBody);
						Thread.sleep(1000);
					}*/
				}
	
			}
		}
		
		
	
	}
	
	public static void PushMaterialData(String Estimate,String Sheetname) throws Exception {
		
		Estimate=Estimate.replace(",", "");
		String ExcelPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Actual\\JobMaterial.xlsx";
		ReadAndUpdate RU = new ReadAndUpdate();
		String cellXpath = "//div[@data-selected='true']//span[contains(@class,'grid__cell')]";
		if(driver.findElements(By.xpath(cellXpath)).size()>0) {
			List<WebElement> ele = driver.findElements(By.xpath(cellXpath));	
			int colCount = Integer.parseInt(ele.get(ele.size()-1).getAttribute("data-index").split("/")[1]);
	
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement gridBody = driver.findElement(By.xpath("//div[@data-selected='true']//div[@role='grid' and contains(@class,'body')]"));
			Long scrollHeight = (Long) js.executeScript("return arguments[0].scrollHeight", gridBody)*20;
			js.executeScript("arguments[0].scrollBy(0,"+scrollHeight+")", gridBody);
			scrollHeight = (Long) js.executeScript("return arguments[0].scrollHeight", gridBody)*10;
			js.executeScript("arguments[0].scrollBy(0,"+scrollHeight+")", gridBody);
			Thread.sleep(2000);
			List<WebElement> ele1 = driver.findElements(By.xpath(cellXpath));	
			int rowCount = Integer.parseInt(ele1.get(ele1.size()-1).getAttribute("data-row"));
	
			//System.out.println("row count is: "+rowCount+" column count is :"+colCount);
			Thread.sleep(1000);
			js.executeScript("arguments[0].scrollBy(0,-"+scrollHeight+")", gridBody);
	
			//String[] colNames = {"CmpType","ProcessGroupDescription","ProcessDescription","CmpName","Qty","UnitofMeasurement","IsSupplied","Inventoryitem","Plant","MISJobID"};
			
			String[] colNames={"Materialtype","Element","Process","Material","Quantity","Measurementunit","Provided","Inventoryitem","Plant","MISJobID"};
			RU.Create_Excel(ExcelPath, Sheetname, colNames);
			for(int row=0;row<=rowCount;row++) {
				for(int col=0;col<=colCount;col++) {
	              
					String jobPlanningValueXpath="//div[@data-selected='true']//div[@role='grid' and contains(@class,'body')]//span[@data-index='"+row+"/"+col+"']";
					WebElement Element = driver.findElement(By.xpath(jobPlanningValueXpath));
			        //This will scroll the page till the element is found		
			        js.executeScript("arguments[0].scrollIntoView();", Element);
				     //CommonFunctions.ClickElement(driver, By.xpath(jobPlanningValueXpath));
				     //driver.findElement(By.xpath(jobPlanningValueXpath)).sendKeys(Keys.DOWN);
				     
					String innerHtml="";
					try{
						 CommonFunctions.waitUntilElementisPresent(driver, By.xpath(jobPlanningValueXpath), 180);
						 CommonFunctions.waitUntilElementisVisible(driver, By.xpath(jobPlanningValueXpath), 180);
						innerHtml=driver.findElement(By.xpath(jobPlanningValueXpath)).getAttribute("innerHTML").toString();
					}catch(Exception e){
						js.executeScript("arguments[0].scrollBy(0,"+scrollHeight+")", gridBody);
						scrollHeight = (Long) js.executeScript("return arguments[0].scrollWidth", gridBody)*20;
						js.executeScript("arguments[0].scrollBy(0,"+scrollHeight+")", gridBody);
						CommonFunctions.waitUntilElementisPresent(driver, By.xpath(jobPlanningValueXpath), 180);
						innerHtml=driver.findElement(By.xpath(jobPlanningValueXpath)).getAttribute("innerHTML").toString();
					}
					String value="";
					if (innerHtml.contains("label")) {
						value = 	driver.findElement(By.xpath(jobPlanningValueXpath+"//label")).getText();
	
					} else if(innerHtml.contains("span")) {
						value = 	driver.findElement(By.xpath(jobPlanningValueXpath+"//span")).getText();
					} else {
						value = "";
					}
					//System.out.println("Value At row "+row+" and col "+col+" is: "+value);
					
					//RU.UpdateFunction(Sheetname, "Material", scenario_+Integer.toString(row), colNames[col], value);
					RU.UpdateFunction_Iquote(ExcelPath, Sheetname, colNames[col], value, row+1);
					//Move ScrollBar to Starting Position after all Columns
					/*if(row==rowCount) {
						js.executeScript("arguments[0].scrollBy(0,-"+scrollHeight+")", gridBody);
						Thread.sleep(1000);
					}*/
				}
	
			}
		}
	}
	
	//Author Sonali

	public static void Nav_JobNote_Tab()throws Exception
	{

		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Nav_JobNoteTab")));
		CommonFunctions.waitForPageLoad(driver);
		Thread.sleep(10000);
		//CommonFunctions.ClickElement(driver, By.xpath(Locators.getProperty(Locators.PartialJob)));
		int val =driver.findElements(By.xpath(OR.getProperty("Button_CopynotesfromotherJob"))).size();
		System.out.println(val);
		if (val>0)
		{
			System.out.println("Navigation to Note Tab Successfull");
		}
		else
		{
			System.err.println("Navigation to Note Tab Failed");

		}

	}

	
	
	public  void jobpagevalidation() throws Exception
	{
		int i=1;
		boolean rowExists = false;
		do{
			/*try{
				String sWc = dbConnection.ReadFunction("PlanningPage", "Estimate", "MONARCH24898_"+i, "MISWorkCenter");
				//String sPlan = dbConnection.ReadFunction("PlanningPage", "Estimate", "MONARCH24898_"+i, "Plant");
				//String sPrdDiv = dbConnection.ReadFunction("PlanningPage", "Estimate", "MONARCH24898_"+i, "ProductionDivision");
				rowExists = true;
				
				if (sPlan.equalsIgnoreCase("Train"))
				{
					if(sWc.equalsIgnoreCase("TR-Sheetfed 203 (1x8c)") && sPrdDiv.equalsIgnoreCase("AC-Sheetfed Division"))
					{
						System.out.println("Validation of train plant success");
						
					}
				} 
				else if(sPlan.equalsIgnoreCase("Accuprint"))
				{
		              if(sWc.equalsIgnoreCase("AC-Cutter") && sPrdDiv.equalsIgnoreCase("AC-Sheetfed Division"))
		              {
			
			                 System.out.println("Validation of Accuprint plant success");
					   }
				}
				
				i=i+1;
				
				
				
			}catch (Exception e) {
				rowExists = false;
			}
			*/
		}
		while(rowExists);
			
			
			
			
		
		
		
	}


	public  void jobPlanning_Validation(WebDriver driver, String scenario, String SheetName) throws Exception
	{
		//CommonFunctions.ClickElement(driver, By.xpath("//label[text()='Component']"));
		//label[text()='Component']//ancestor::div[@class='grid__box']//span[@class='cell__sort--a']
		
		int val =driver.findElements(By.xpath("//label[text()='Component']//ancestor::div[@class='grid__box']//span[@class='cell__sort--a']")).size();
		System.out.println(val);
		if (val>0)
		{
			System.out.println("Already sorted");
		}
		else
		{
			System.out.println("Sorting the panning now...");
			CommonFunctions.ClickElement(driver, By.xpath("//header[text()='Plan']//..//label[text()='Component']"));
			int val1 =driver.findElements(By.xpath("//label[text()='Component']//ancestor::div[@class='grid__box']//span[@class='cell__sort--a']")).size();
			System.err.println("After Click Value is "+val1);

		}
		
		String cellXpath = "//div[@data-selected='true']//span[contains(@class,'grid__cell')]";
		List<WebElement> ele = driver.findElements(By.xpath(cellXpath));	
		int rowCount = Integer.parseInt(ele.get(ele.size()-1).getAttribute("data-row"));
		JavascriptExecutor js = (JavascriptExecutor)driver;

		WebElement gridBody = driver.findElement(By.xpath("//div[@data-selected='true']//div[@role='grid' and contains(@class,'body')]"));

		Long scrollWidth = (Long) js.executeScript("return arguments[0].scrollWidth", gridBody)*10;
		Long clientWidth = scrollWidth;
		js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
		scrollWidth = (Long) js.executeScript("return arguments[0].scrollWidth", gridBody)*10;
		js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
		Thread.sleep(2000);
		List<WebElement> ele1 = driver.findElements(By.xpath(cellXpath));	
		int colCount = Integer.parseInt(ele1.get(ele1.size()-1).getAttribute("data-index").split("/")[1]);
		System.out.println("row count is: "+rowCount+" column count is :"+colCount);
		Thread.sleep(1000);
		js.executeScript("arguments[0].scrollBy(-"+clientWidth+",0)", gridBody);
		String[] colNames = {"Component","Activity","Planninggroup","ScheduledResource","Start","End","PlannedQty","Producedqty","Status","Productionstarted","Productionconcluded","Plant","ProductionDivision","MISJobID","MISWorkCenter","PlannedResource"};
		for(int row=0;row<=rowCount;row++) {
			for(int col=0;col<=colCount;col++) {
				String jobPlanningValueXpath="//div[@data-selected='true']//div[@role='grid' and contains(@class,'body')]//span[@data-index='"+row+"/"+col+"']";
				String innerHtml="";
				try{
					innerHtml=driver.findElement(By.xpath(jobPlanningValueXpath)).getAttribute("innerHTML").toString();
				}catch(Exception e){
					js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
					scrollWidth = (Long) js.executeScript("return arguments[0].scrollWidth", gridBody)*10;
					js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
					innerHtml=driver.findElement(By.xpath(jobPlanningValueXpath)).getAttribute("innerHTML").toString();
				}
				String value="";
				if (innerHtml.contains("label")) {
					value = 	driver.findElement(By.xpath(jobPlanningValueXpath+"//label")).getText();

				} else if(innerHtml.contains("span")) {
					value = 	driver.findElement(By.xpath(jobPlanningValueXpath+"//span")).getText();
				} else {
					value = "";
				}
				System.out.println("Value At row "+row+" and col "+col+" is: "+value);
				//ReadAndUpdate RU = new ReadAndUpdate();
				//RU.UpdateFunction(SheetName, "Planning", scenario+Integer.toString(row), colNames[col], value);
				//Move ScrollBar to Starting Position after all Columns
				if(col==colCount) {
					js.executeScript("arguments[0].scrollBy(-"+clientWidth+",0)", gridBody);
					Thread.sleep(1000);
				}
			}

		}
		
		
		
	}

	
	public  void jobPlanning_Validation_Iquote(String ExcelPath, String Sheetname) throws Exception
	{
		//CommonFunctions.ClickElement(driver, By.xpath("//label[text()='Component']"));
		//label[text()='Component']//ancestor::div[@class='grid__box']//span[@class='cell__sort--a']
		//ReadAndUpdate RU = new ReadAndUpdate();
		int val =driver.findElements(By.xpath("//label[text()='Component']//ancestor::div[@class='grid__box']//span[@class='cell__sort--a']")).size();
		System.out.println(val);
		if (val>0)
		{
			System.out.println("Already sorted");
		}
		else
		{
			System.out.println("Sorting the panning now...");
			CommonFunctions.ClickElement(driver, By.xpath("//header[text()='Plan']//..//label[text()='Component']"));
			int val1 =driver.findElements(By.xpath("//label[text()='Component']//ancestor::div[@class='grid__box']//span[@class='cell__sort--a']")).size();
			System.err.println("After Click Value is "+val1);

		}
		
		String cellXpath = "//div[@data-selected='true']//span[contains(@class,'grid__cell')]";
		List<WebElement> ele = driver.findElements(By.xpath(cellXpath));	
		int rowCount = Integer.parseInt(ele.get(ele.size()-1).getAttribute("data-row"));
		JavascriptExecutor js = (JavascriptExecutor)driver;

		WebElement gridBody = driver.findElement(By.xpath("//div[@data-selected='true']//div[@role='grid' and contains(@class,'body')]"));

		Long scrollWidth = (Long) js.executeScript("return arguments[0].scrollWidth", gridBody)*10;
		Long clientWidth = scrollWidth;
		js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
		scrollWidth = (Long) js.executeScript("return arguments[0].scrollWidth", gridBody)*10;
		js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
		Thread.sleep(2000);
		List<WebElement> ele1 = driver.findElements(By.xpath(cellXpath));	
		int colCount = Integer.parseInt(ele1.get(ele1.size()-1).getAttribute("data-index").split("/")[1]);
		System.out.println("row count is: "+rowCount+" column count is :"+colCount);
		Thread.sleep(1000);
		js.executeScript("arguments[0].scrollBy(-"+clientWidth+",0)", gridBody);
		String[] colNames = {"Component","Activity","Planninggroup","ScheduledResource","Start","End","PlannedQty","Producedqty","Status","Productionstarted","Productionconcluded","Plant","ProductionDivision","MISJobID","MISWorkCenter","PlannedResource"};
		//RU.Create_Excel(ExcelPath, Sheetname, colNames);
		for(int row=0;row<=rowCount;row++) {
			for(int col=0;col<=colCount;col++) {
				String jobPlanningValueXpath="//div[@data-selected='true']//div[@role='grid' and contains(@class,'body')]//span[@data-index='"+row+"/"+col+"']";
				String innerHtml="";
				try{
					innerHtml=driver.findElement(By.xpath(jobPlanningValueXpath)).getAttribute("innerHTML").toString();
				}catch(Exception e){
					js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
					scrollWidth = (Long) js.executeScript("return arguments[0].scrollWidth", gridBody)*10;
					js.executeScript("arguments[0].scrollBy("+scrollWidth+",0)", gridBody);
					CommonFunctions.waitUntilElementisPresent(driver, By.xpath(jobPlanningValueXpath), 180);
					innerHtml=driver.findElement(By.xpath(jobPlanningValueXpath)).getAttribute("innerHTML").toString();
				}
				String value="";
				if (innerHtml.contains("label")) {
					value = 	driver.findElement(By.xpath(jobPlanningValueXpath+"//label")).getText();

				} else if(innerHtml.contains("span")) {
					value = 	driver.findElement(By.xpath(jobPlanningValueXpath+"//span")).getText();
				} else {
					value = "";
				}
				System.out.println("Value At row "+row+" and col "+col+" is: "+value);
		
			//	RU.UpdateFunction(SheetName, "Planning", scenario+Integer.toString(row), colNames[col], value);
				//RU.UpdateFunction_Iquote(ExcelPath, Sheetname, colNames[col], value, row+1);
				//Move ScrollBar to Starting Position after all Columns
				if(col==colCount) {
					js.executeScript("arguments[0].scrollBy(-"+clientWidth+",0)", gridBody);
					Thread.sleep(1000);
				}
			}

		}
		
		
		
	}

	
	public void MaterialPage_Validation( String ExcelPath, String Sheetname) throws Exception
	{
		
		//ReadAndUpdate RU = new ReadAndUpdate();
		String cellXpath = "//div[@data-selected='true']//span[contains(@class,'grid__cell')]";
		List<WebElement> ele = driver.findElements(By.xpath(cellXpath));	
		int colCount = Integer.parseInt(ele.get(ele.size()-1).getAttribute("data-index").split("/")[1]);

		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement gridBody = driver.findElement(By.xpath("//div[@data-selected='true']//div[@role='grid' and contains(@class,'body')]"));
		Long scrollHeight = (Long) js.executeScript("return arguments[0].scrollHeight", gridBody)*20;
		js.executeScript("arguments[0].scrollBy(0,"+scrollHeight+")", gridBody);
		scrollHeight = (Long) js.executeScript("return arguments[0].scrollHeight", gridBody)*10;
		js.executeScript("arguments[0].scrollBy(0,"+scrollHeight+")", gridBody);
		Thread.sleep(2000);
		List<WebElement> ele1 = driver.findElements(By.xpath(cellXpath));	
		int rowCount = Integer.parseInt(ele1.get(ele1.size()-1).getAttribute("data-row"));

		System.out.println("row count is: "+rowCount+" column count is :"+colCount);
		Thread.sleep(1000);
		js.executeScript("arguments[0].scrollBy(0,-"+scrollHeight+")", gridBody);

		//String[] colNames = {"CmpType","ProcessGroupDescription","ProcessDescription","CmpName","Qty","UnitofMeasurement","IsSupplied","Inventoryitem","Plant","MISJobID"};
		
		String[] colNames={"Materialtype","Element","Process","Material","Quantity","Measurementunit","Provided","Inventoryitem","Plant","MISJobID"};
		//RU.Create_Excel(ExcelPath, Sheetname, colNames);
		for(int row=0;row<=rowCount;row++) {
			for(int col=0;col<=colCount;col++) {
              
				String jobPlanningValueXpath="//div[@data-selected='true']//div[@role='grid' and contains(@class,'body')]//span[@data-index='"+row+"/"+col+"']";
			     CommonFunctions.ClickElement(driver, By.xpath(jobPlanningValueXpath));
			     driver.findElement(By.xpath(jobPlanningValueXpath)).sendKeys(Keys.DOWN);
			     
				String innerHtml="";
				try{
					 CommonFunctions.waitUntilElementisPresent(driver, By.xpath(jobPlanningValueXpath), 180);
					 CommonFunctions.waitUntilElementisVisible(driver, By.xpath(jobPlanningValueXpath), 180);
					innerHtml=driver.findElement(By.xpath(jobPlanningValueXpath)).getAttribute("innerHTML").toString();
				}catch(Exception e){
					js.executeScript("arguments[0].scrollBy(0,"+scrollHeight+")", gridBody);
					scrollHeight = (Long) js.executeScript("return arguments[0].scrollWidth", gridBody)*20;
					js.executeScript("arguments[0].scrollBy(0,"+scrollHeight+")", gridBody);
					CommonFunctions.waitUntilElementisPresent(driver, By.xpath(jobPlanningValueXpath), 180);
					innerHtml=driver.findElement(By.xpath(jobPlanningValueXpath)).getAttribute("innerHTML").toString();
				}
				String value="";
				if (innerHtml.contains("label")) {
					value = 	driver.findElement(By.xpath(jobPlanningValueXpath+"//label")).getText();

				} else if(innerHtml.contains("span")) {
					value = 	driver.findElement(By.xpath(jobPlanningValueXpath+"//span")).getText();
				} else {
					value = "";
				}
				System.out.println("Value At row "+row+" and col "+col+" is: "+value);
				
				//RU.UpdateFunction(Sheetname, "Material", scenario_+Integer.toString(row), colNames[col], value);
				//RU.UpdateFunction_Iquote(ExcelPath, Sheetname, colNames[col], value, row+1);
				//Move ScrollBar to Starting Position after all Columns
				if(row==rowCount) {
					js.executeScript("arguments[0].scrollBy(0,-"+scrollHeight+")", gridBody);
					Thread.sleep(1000);
				}
			}

		}
	}
	
    public static String ValidateandSaveJobID(String Sheet, String Scenario, String Testcase, String Job) throws Exception
    { //Author Sonali
          String JobNum = driver.findElement(By.xpath("//label[text()='Job number']/following-sibling::span[@class='ltv__itemcont ltv_']/input")).getAttribute("value");
          System.out.println("Job ID is "+JobNum);
        //  ReadAndUpdate SaveRU = new ReadAndUpdate();
            // SaveRU.UpdateFunction(Sheet, Scenario, Testcase, Job, JobNum);
          return JobNum;
    }


    public static void NavigateToJobGeneral()throws Exception
    {//Author Sonali
          CommonFunctions.ClickElement(driver, By.xpath("//div[@class='wizard']/nav[@class='wizard__nav']//label[text()='General'] "));
          CommonFunctions.waitForPageLoad(driver);
          //CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//header[text()='Job']"), 180);
          int val =driver.findElements(By.xpath("//header[text()='Job']")).size();
          //System.out.println(val);
          if (val>0)
          {
                System.out.println("Navigation to Job General Page Successfull");
                String Jobnum=driver.findElement(By.xpath("//header[text()='Job']/parent::div//label[text()='Job number']/parent::span/span/input")).getAttribute("Value");          
                HTMLF.addrow_Twoparm("Comment", "Job Number# is", "", Jobnum, "", "", Config.getProperty("EstimateIDs")+".html");
    			HTMLF.addrow("Comment","Job Creation" , "", "", "", "",Config.getProperty("EstimateIDs")+".html");
          		String CustomerID= driver.findElement(By.xpath("//label[text()='Customer']/parent::span//input[1]")).getAttribute("value");
          		System.out.println("Job Number and customerID : "+Jobnum+","+CustomerID);
    		}
          else
          {
                System.err.println("Navigation to job General Page Failed");
                
          }
            
     //  TakeScreenShot.ScreenShotWindow(driver,"JobMaterials");            
    }
    public static boolean VerifyJobPlanning(String Estimate,String SheetName1) {
    	
    	Estimate=Estimate.replace(",", "");
    	String ExcelSheetPath1=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Actual\\JobPlanning.xlsx";
    	String ExcelSheetPath2=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Base\\JobPlanning.xlsx";
    	
	      boolean Comp1 = false;
	      try {
	            
	        ArrayList arr1 = new ArrayList();
	        ArrayList arr2 = new ArrayList();
	        ArrayList arr3 = new ArrayList();
	        ArrayList arr4 = new ArrayList();
	        
	        File tempFile = new File(ExcelSheetPath1);
	        if(tempFile.exists()) {
		        FileInputStream file1 = new FileInputStream(new File(
		        		ExcelSheetPath1));
	
		        FileInputStream file2 = new FileInputStream(new File(
		        		ExcelSheetPath2));
	
		        // Get the workbook instance for XLS file
		        XSSFWorkbook workbook1 = new XSSFWorkbook(file1);
		        XSSFWorkbook workbook2 = new XSSFWorkbook(file2);
	
		        // Get first sheet from the workbook
		     //   HSSFSheet sheet1 = workbook1.getSheetAt(0);
		        XSSFSheet sheet1= workbook1.getSheet(SheetName1);
		    //    HSSFSheet sheet2 = workbook2.getSheetAt(0);
		        XSSFSheet sheet2 = workbook2.getSheet(SheetName1);
		        // Compare sheets
	
		        // Get iterator to all the rows in current sheet1
		        Iterator<Row> rowIterator1 = sheet1.iterator();
		        Iterator<Row> rowIterator2 = sheet2.iterator();
	
		        while (rowIterator1.hasNext()) {
		            Row row = rowIterator1.next();
		            // For each row, iterate through all the columns
		            Iterator<Cell> cellIterator = row.cellIterator();
		            
		            //System.out.println("Get last row :-"+row.getLastCellNum()); 
		            while (cellIterator.hasNext()) {
	
		                Cell cell = cellIterator.next();
		                 // System.out.println(cell.getRowIndex());
		                // This is for read only one column from excel
		                if (cell.getColumnIndex() >= 0) {
		                    // Check the cell type and format accordingly
		                    switch (cell.getCellType()) {
		                    case Cell.CELL_TYPE_NUMERIC:
		                       // System.out.print(cell.getNumericCellValue());
		                        arr1.add(cell.getNumericCellValue());
		                        break;
		                    case Cell.CELL_TYPE_STRING:
		                        arr1.add(cell.getStringCellValue());
		                        //System.out.print(cell.getStringCellValue());
		                        break;
		                    case Cell.CELL_TYPE_BOOLEAN:
		                        arr1.add(cell.getBooleanCellValue());
		                        //System.out.print(cell.getBooleanCellValue());
		                        break;
		                    }
	
		                }
	
		            }
	
		            //System.out.println(" ");
		        }
	
		        file1.close();
	
		       // System.out.println("-----------------------------------");
		        // For retrive the second excel data
		        while (rowIterator2.hasNext()) {
		            Row row1 = rowIterator2.next();
		            // For each row, iterate through all the columns
		            Iterator<Cell> cellIterator1 = row1.cellIterator();
	
		            while (cellIterator1.hasNext()) {
	
		                Cell cell1 = cellIterator1.next();
		                // Check the cell type and format accordingly
	
		                // This is for read only one column from excel
		                if (cell1.getColumnIndex() >= 0) {
		                 // System.out.println(cell1.getCellType());
		                    switch (cell1.getCellType()) {
		                    
		           
		                    case Cell.CELL_TYPE_NUMERIC:
		                        arr2.add(cell1.getNumericCellValue());
		                       // System.out.print(cell1.getNumericCellValue());
		                        
		                        break;
		                    case Cell.CELL_TYPE_STRING:
		                        arr2.add(cell1.getStringCellValue());
		                        //System.out.print(cell1.getStringCellValue());
		                        break;
		                    case Cell.CELL_TYPE_BOOLEAN:
		                        arr2.add(cell1.getBooleanCellValue());
		                       // System.out.print(cell1.getBooleanCellValue());
		                        break;
	
		                    }
	
		                }
		                // this continue is for
		                // continue;
		            }
	
		           // System.out.println("");
		        }
	
		       // System.out.println("book1.xls -- " + arr1.size());
		        //System.out.println("book1.xls -- " + arr2.size());
	
		        // compare two arrays
		        for (Object process : arr1) 
		        {
		            if (!arr2.contains(process)) {
		                arr3.add(process);
		                //arr4.add()
		               // System.out.println("False");
		                Comp1=false;
		            }
		            else
		            {
		                //  System.out.println("True");
		                  Comp1=true;
		            }
		            
		            
		        }
		        //System.out.println("arr3 list values - = - = + " + arr3);
		       
		       
	
		        // closing the files
		        file1.close();
		        file2.close();
		        
		        if (arr3.size()>0)
		        {
		        	//System.err.println("Difference in The Excel sheet as as follows:- ");
		        	//System.err.println("arr3 list values - = - = + " + arr3);
		        	//Assert.fail("Difference in The Excel sheet is as follows:- " + arr3);
		        	Comp1= false;
		        }
		        else
		        {
		        	//System.out.println("Difference in The Excel sheet as as follows:- ");
		        	//System.out.println("arr3 list values - = - = + " + arr3);
		        	Comp1 =true;
		        }
	        }
	        else {
	        	Comp1 = true;
	        }
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    String status =Comp1? "PASS" : "FAIL";
	    HTMLF.addrow("Step 7", "Job Planning Validation", ExcelSheetPath2, ExcelSheetPath1, "", status,Config.getProperty("EstimateIDs")+".html");
		return Comp1;
	      
	
    }
    public static boolean VerifyJobMaterial(String Estimate,String SheetName1) {
    	
    	Estimate=Estimate.replace(",", "");
    	String ExcelSheetPath1=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Actual\\JobMaterial.xlsx";
    	String ExcelSheetPath2=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Base\\JobMaterial.xlsx";
    	
	      boolean Comp1 = false;
	      try {
	            
	        ArrayList arr1 = new ArrayList();
	        ArrayList arr2 = new ArrayList();
	        ArrayList arr3 = new ArrayList();
	        ArrayList arr4 = new ArrayList();
	        File tempFile = new File(ExcelSheetPath1);
	        if(tempFile.exists()) {
		        FileInputStream file1 = new FileInputStream(new File(
		        		ExcelSheetPath1));
	
		        FileInputStream file2 = new FileInputStream(new File(
		        		ExcelSheetPath2));
	
		        // Get the workbook instance for XLS file
		        XSSFWorkbook workbook1 = new XSSFWorkbook(file1);
		        XSSFWorkbook workbook2 = new XSSFWorkbook(file2);
	
		        // Get first sheet from the workbook
		     //   HSSFSheet sheet1 = workbook1.getSheetAt(0);
		        XSSFSheet sheet1= workbook1.getSheet(SheetName1);
		    //    HSSFSheet sheet2 = workbook2.getSheetAt(0);
		        XSSFSheet sheet2 = workbook2.getSheet(SheetName1);
		        // Compare sheets
	
		        // Get iterator to all the rows in current sheet1
		        Iterator<Row> rowIterator1 = sheet1.iterator();
		        Iterator<Row> rowIterator2 = sheet2.iterator();
	
		        while (rowIterator1.hasNext()) {
		            Row row = rowIterator1.next();
		            // For each row, iterate through all the columns
		            Iterator<Cell> cellIterator = row.cellIterator();
		            
		           // System.out.println("Get last row :-"+row.getLastCellNum()); 
		            while (cellIterator.hasNext()) {
	
		                Cell cell = cellIterator.next();
		                 // System.out.println(cell.getRowIndex());
		                // This is for read only one column from excel
		                if (cell.getColumnIndex() >= 0) {
		                    // Check the cell type and format accordingly
		                    switch (cell.getCellType()) {
		                    case Cell.CELL_TYPE_NUMERIC:
		                       // System.out.print(cell.getNumericCellValue());
		                        arr1.add(cell.getNumericCellValue());
		                        break;
		                    case Cell.CELL_TYPE_STRING:
		                        arr1.add(cell.getStringCellValue());
		                       // System.out.print(cell.getStringCellValue());
		                        break;
		                    case Cell.CELL_TYPE_BOOLEAN:
		                        arr1.add(cell.getBooleanCellValue());
		                       // System.out.print(cell.getBooleanCellValue());
		                        break;
		                    }
	
		                }
	
		            }
	
		           // System.out.println(" ");
		        }
	
		        file1.close();
	
		        //System.out.println("-----------------------------------");
		        // For retrive the second excel data
		        while (rowIterator2.hasNext()) {
		            Row row1 = rowIterator2.next();
		            // For each row, iterate through all the columns
		            Iterator<Cell> cellIterator1 = row1.cellIterator();
	
		            while (cellIterator1.hasNext()) {
	
		                Cell cell1 = cellIterator1.next();
		                // Check the cell type and format accordingly
	
		                // This is for read only one column from excel
		                if (cell1.getColumnIndex() >= 0) {
		                 // System.out.println(cell1.getCellType());
		                    switch (cell1.getCellType()) {
		                    
		           
		                    case Cell.CELL_TYPE_NUMERIC:
		                        arr2.add(cell1.getNumericCellValue());
		                       // System.out.print(cell1.getNumericCellValue());
		                        
		                        break;
		                    case Cell.CELL_TYPE_STRING:
		                        arr2.add(cell1.getStringCellValue());
		                      //  System.out.print(cell1.getStringCellValue());
		                        break;
		                    case Cell.CELL_TYPE_BOOLEAN:
		                        arr2.add(cell1.getBooleanCellValue());
		                       // System.out.print(cell1.getBooleanCellValue());
		                        break;
	
		                    }
	
		                }
		                // this continue is for
		                // continue;
		            }
	
		            //System.out.println("");
		        }
	
		       // System.out.println("book1.xls -- " + arr1.size());
		        //System.out.println("book1.xls -- " + arr2.size());
	
		        // compare two arrays
		        for (Object process : arr1) 
		        {
		            if (!arr2.contains(process)) {
		                arr3.add(process);
		                //arr4.add()
		               // System.out.println("False");
		                Comp1=false;
		            }
		            else
		            {
		                //  System.out.println("True");
		                  Comp1=true;
		            }
		            
		            
		        }
		       // System.out.println("arr3 list values - = - = + " + arr3);
		       
		       
	
		        // closing the files
		        file1.close();
		        file2.close();
		        
		        if (arr3.size()>0)
		        {
		        	//System.err.println("Difference in The Excel sheet as as follows:- ");
		        	//System.err.println("arr3 list values - = - = + " + arr3);
		        	//Assert.fail("Difference in The Excel sheet is as follows:- " + arr3);
		        	Comp1= false;
		        }
		        else
		        {
		        	//System.out.println("Difference in The Excel sheet as as follows:- ");
		        	//System.out.println("arr3 list values - = - = + " + arr3);
		        	Comp1 =true;
		        }
	        }
	        else {
	        	Comp1 =true;
	        }
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	      String status =Comp1? "PASS" : "FAIL";
		   HTMLF.addrow("Step 7", "Job Material Validation", ExcelSheetPath2, ExcelSheetPath1, "", status,Config.getProperty("EstimateIDs")+".html");
		return Comp1;
	      
	
    }
    public static void CloseJobTab() {
    	driver.findElement(By.xpath("//span[@class='app__tab__close']")).click();
    }
    
    public static void NavigateToJobEngineering() {
    	driver.findElement(By.xpath("//nav[@class='wizard__nav']//span[4]")).click();
    }
    public static void VerifyJobEngineering(String Estimate) throws Exception {
    	Estimate=Estimate.replace(",", "");
		/*String Actualname=ScreenShot.ScreenShotRegion_withPath(driver, By.xpath("//div[@class='eng-di__cont']//div[@class='diagram__cont']"), "Job_ENG", "",Estimate);
		if(!Actualname.isEmpty()) {
			String Status=ScreenShot.imageComparison("Job_ENG.png",Actualname,(Estimate+"Job_ENG_Diff.png"), "No",Estimate);
			System.out.println("Image Comparision of Quantity Diagram : "+Status);
			String sFile1=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Base\\Job_ENG.png";
			String sFile2=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Actual\\"+Actualname;
			String Differencepath=System.getProperty("user.dir")+ "\\src\\test\\resources\\Documents\\"+Estimate+"\\Difference\\"+Estimate+"Job_ENG_Diff.png";
			 HTMLF.addrow("","EST-Engineering Diagram", sFile1, sFile2, Differencepath, Status,Config.getProperty("EstimateIDs")+".html");
		}*/
    	WebElement webElement = driver.findElement(By.cssSelector("svg.diagram__canvas"));
    	Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, webElement);
		//Screenshot screenshot = new AShot().takeScreenshot(driver, webElement);
		ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") +"\\src\\test\\resources\\Documents\\"+Estimate+"\\Actual\\Job_ENG.png"));
		
		BufferedImage actualImage = screenshot.getImage();
		
		BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Base\\Job_ENG.png"));
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
      
        if(diff.hasDiff()==true)
        {
    	 BufferedImage diffImage = diff.getMarkedImage();
         ImageIO.write(actualImage, "PNG", new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Difference\\Job_ENG_Diff.png"));
         System.out.println("ENG Images are Not Same");
        }
        else {
    	 
          System.out.println("ENG Images are Same");
        }
		
    }
}
