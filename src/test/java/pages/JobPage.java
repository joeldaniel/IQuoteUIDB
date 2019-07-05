package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.Testbase;
import utilities.CommonFunctions;

public class JobPage extends Testbase{


	//ReadAndUpdate dbConnection = new ReadAndUpdate();
	
	//Author Sonali
	
      
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
            System.out.println(val);
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
    	  CommonFunctions.ClickElement(driver, By.xpath("//b[text()='Estimate']/ancestor::span[@class='renderer inline-img']/ancestor::ul[@class='is-filter__browser']//span[@class='ltv__item ltv_ ltv_last']//input[1]"));
			driver.findElement(By.xpath("//b[text()='Estimate']/ancestor::span[@class='renderer inline-img']/ancestor::ul[@class='is-filter__browser']//span[@class='ltv__item ltv_ ltv_last']//input[1]")).sendKeys(eEstimateNumber);
			driver.findElement(By.xpath("//b[text()='Estimate']/ancestor::span[@class='renderer inline-img']/ancestor::ul[@class='is-filter__browser']//span[@class='ltv__item ltv_ ltv_last']//input[1]")).sendKeys(Keys.TAB);
			Thread.sleep(5000);
			CommonFunctions.ClickElement(driver, By.xpath("//label[text()='Search']"));
			//driver.findElement(By.xpath("//label[text()='Search']")).click();
			CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//span[(@class='grid__cell grid__cell--alpha grid__current gs--summarize' ) and (@data-index='0/1')]"), 120000);
			driver.findElement(By.xpath("//span[(@class='grid__cell grid__cell--alpha grid__current gs--summarize' ) and (@data-index='0/1')]")).click();
			Thread.sleep(6000);
			CommonFunctions.ClickElement(driver, By.xpath("//button[@title='Duplicate Job']/../button[2]/span/img")); 
			CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='Synchronization Status']"), 120000);
			CommonFunctions.waitUntilElementisVisible(driver, By.xpath("//label[text()='Synchronization Status']"), 10000);
			String JobPageValidation= "//label[text()='Synchronization Status']";
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
      {//Author Sonali
            CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Nav_MaterialsTab")));
            CommonFunctions.waitForPageLoad(driver);
            CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//div[@class='program']//div[@class='eng-di__cont']"), 180);
            int val =driver.findElements(By.xpath("//div[@class='ps-diagram']//div[@class='diagram__cont']")).size();
            System.out.println(val);
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
        CommonFunctions.waitUntilElementisPresent(driver, By.xpath(OR.getProperty("Job_ProcessGroup_Label")), 180);
		int val =driver.findElements(By.xpath(OR.getProperty("Job_ProcessGroup_Label"))).size();
		System.out.println(val);
		if (val>0)
		{
			System.out.println("Navigation to Job Planning Page Successfull");
		}
		else
		{
			System.err.println("Navigation to job Planning Page Failed");

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
          CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//label[text()='Synchronization Status']"), 180);
          int val =driver.findElements(By.xpath("//label[text()='Synchronization Status']")).size();
          System.out.println(val);
          if (val>0)
          {
                System.out.println("Navigation to Job General Page Successfull");
          }
          else
          {
                System.err.println("Navigation to job General Page Failed");
                
          }
            
     //  TakeScreenShot.ScreenShotWindow(driver,"JobMaterials");            
    }


}
