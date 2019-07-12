package utilities;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
public class HTML_File_Creator {

	public void HTMLFileGenerator(String FileName, String TestCaseName, String CurrentDate)
    {
    try {
           StringBuilder htmlStringBuilder=new StringBuilder();
           htmlStringBuilder.append("<html><head><title>IQuote Test Results</title><link href=\"css/dot.css\" rel=\"stylesheet\"><link href=\"css/app.css\" rel=\"stylesheet\"></head>");
           htmlStringBuilder.append("<div class=\"dot-wrapper\" id=\"main\">");
           htmlStringBuilder.append("<div class=\"dot-leftpane\">");
           htmlStringBuilder.append("<div class=\"suitelogo\">");
           htmlStringBuilder.append("</div>");
           htmlStringBuilder.append("<div class=\"efilogo\">");
           htmlStringBuilder.append("<a target=\"_blank\" href=\"http://www.efi.com\" class=\"logo-container\"></a>");
           htmlStringBuilder.append("<div class=\"version-container\" style=\"display: none;\">");
           htmlStringBuilder.append("<span>Generator: Nubmer appears here </span><span>DSMI: Nubmer appears here x64</span><span>Copyright information</span></div></div></div><div class=\"dot-rightpane\" style=\"padding-left: 48px;\"><div class=\"dot-header\"><div class=\"dot-drawer\" onclick=\"toggleSidebarWidth(event, 'dot-leftpane', 'dot-rightpane')\"><span class=\"dot-icon dot-list-icon\"></span></div><div class=\"product-name\"><span>IQuote</span> </div></div></head>");
                        
           
           
           htmlStringBuilder.append("<img src=\"IquoteLogo.png\" alt=\"logo\" width=\"200\" height=\"100\"/><p><strong>TestCase Executed : </strong> "+TestCaseName+"</p> <p><strong>DateTime Executed : </strong>"+CurrentDate+"</p>");
           htmlStringBuilder.append("<body>");
           htmlStringBuilder.append("<div class=\"dot-secondary-contentarea dot-sidepadding__none\">");
           htmlStringBuilder.append("<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" class=\"grid-table\">");
           //htmlStringBuilder.append("<tr class=\"dot-text__caps\"> <td><b>"+TestcaseName+"</b></td><td><b>"+BaseScreenshotpath+"</b></td><td><b>"+ActualScreenshotpath+"</b></td><td><b>"+DiffScreenshotpath+"</b></td><td><b>"+BasePDFPath+"</b></td><td><b>"+ActaulPDFPath+"</b></td>v<td><b>"+DiffPDFTextPath+"</b></td></tr>");
           htmlStringBuilder.append("<thead><tr class=\"dot-text__caps\"><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Difference Result</th><th>Status</th></tr> </thead>");
           htmlStringBuilder.append("<tbody>");          
           
           //addrow(TestcaseName, BaseScreenshotpath, ActualScreenshotpath, DiffScreenshotpath, BasePDFPath, ActaulPDFPath, DiffPDFTextPath) ;
           //append row
     // htmlStringBuilder.append("<tr><td><b>TestcaseName</b></td><td><b>BaseScreenshotpath</b></td><td><b>ActualScreenshotpath</b></td><td><b>DiffScreenshotpath</b></td><td><b>BasePDFPath</b></td><td><b>ActaulPDFPath</b></td>v<td><b>DiffPDFTextPath</b></td></tr>");
      //close html file
     // htmlStringBuilder.append("</table></body></html>");
      //write html string content to a file
   
                  WriteToFile(htmlStringBuilder.toString(),FileName);
                  
                  //addrow(TestcaseName, BaseScreenshotpath, ActualScreenshotpath, DiffScreenshotpath, BasePDFPath, ActaulPDFPath, DiffPDFTextPath,"testfile.html") ;
           } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           }
  
           
    
    }
    
    public static void WriteToFile(String fileContent, String fileName) throws IOException {
            String projectPath = System.getProperty("user.dir");
            //System.out.println(projectPath);
            String tempFile = projectPath+ "\\HTMLReports\\"+fileName;
            File file = new File(tempFile);
            // if file does exists, then delete and create a new file
            if (file.exists()) {
                File newFileName = new File(projectPath+"\\HTMLReports" + File.separator+ "backup_"+fileName);
         // file.renameTo(newFileName);
                        file.delete();
            }
            //write to file with OutputStreamWriter
            OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile(),true);
            Writer writer=new OutputStreamWriter(outputStream);
            writer.write(fileContent);
            writer.close();

        }
    
     public static void WriteToRow(String fileContent, String fileName) throws IOException {
            String projectPath = System.getProperty("user.dir");
            //System.out.println("The Project Path in Write to Row is - "+projectPath);
            String tempFile = projectPath+ "\\HTMLReports\\"+fileName;
            File file = new File(tempFile);
            // if file does exists, then delete and create a new file
            if (file.exists()) {
                File newFileName = new File(projectPath+"\\HTMLReports" + File.separator+ "backup_"+fileName);
      // file.renameTo(newFileName);
                  
            }
            //write to file with OutputStreamWriter
           // System.out.println("The Write to Row ");
            OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile(),true);
            Writer writer=new OutputStreamWriter(outputStream);
            writer.write(fileContent);
            writer.close();

        }


		
		public void HTMLFileGenerator_old(String FileName, String TestCaseName, String CurrentDate)
	    {
	    try {
	           StringBuilder htmlStringBuilder=new StringBuilder();
	           htmlStringBuilder.append("<html><head><title>DSF Test Results</title><link href=\"css/dot.css\" rel=\"stylesheet\"><link href=\"css/app.css\" rel=\"stylesheet\"></head>");
	           htmlStringBuilder.append("<div class=\"dot-wrapper\" id=\"main\">");
	           htmlStringBuilder.append("<div class=\"dot-leftpane\">");
	           htmlStringBuilder.append("<div class=\"suitelogo\">");
	           htmlStringBuilder.append("</div>");
	           htmlStringBuilder.append("<div class=\"efilogo\">");
	           htmlStringBuilder.append("<a target=\"_blank\" href=\"http://www.efi.com\" class=\"logo-container\"></a>");
	           htmlStringBuilder.append("<div class=\"version-container\" style=\"display: none;\">");
	           htmlStringBuilder.append("<span>Generator: Nubmer appears here </span><span>DSMI: Nubmer appears here x64</span><span>Copyright information</span></div></div></div><div class=\"dot-rightpane\" style=\"padding-left: 48px;\"><div class=\"dot-header\"><div class=\"dot-drawer\" onclick=\"toggleSidebarWidth(event, 'dot-leftpane', 'dot-rightpane')\"><span class=\"dot-icon dot-list-icon\"></span></div><div class=\"product-name\"><span>iQuote</span> </div></div></head>");
	                        
	           
	           
	           htmlStringBuilder.append("<img src=\"IquoteLogo.png\" alt=\"logo\" width=\"200\" height=\"100\"/><p><strong>TestCase Executed : </strong> "+TestCaseName+"</p> <p><strong>DateTime Executed : </strong>"+CurrentDate+"</p>");
	           htmlStringBuilder.append("<body>");
	           htmlStringBuilder.append("<div class=\"dot-secondary-contentarea dot-sidepadding__none\">");
	           htmlStringBuilder.append("<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" class=\"grid-table\">");
	           //htmlStringBuilder.append("<tr class=\"dot-text__caps\"> <td><b>"+TestcaseName+"</b></td><td><b>"+BaseScreenshotpath+"</b></td><td><b>"+ActualScreenshotpath+"</b></td><td><b>"+DiffScreenshotpath+"</b></td><td><b>"+BasePDFPath+"</b></td><td><b>"+ActaulPDFPath+"</b></td>v<td><b>"+DiffPDFTextPath+"</b></td></tr>");
	           htmlStringBuilder.append("<thead><tr class=\"dot-text__caps\"><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Difference Result</th><th>Status</th></tr> </thead>");
	           htmlStringBuilder.append("<tbody>");          
	           
	           //addrow(TestcaseName, BaseScreenshotpath, ActualScreenshotpath, DiffScreenshotpath, BasePDFPath, ActaulPDFPath, DiffPDFTextPath) ;
	           //append row
	     // htmlStringBuilder.append("<tr><td><b>TestcaseName</b></td><td><b>BaseScreenshotpath</b></td><td><b>ActualScreenshotpath</b></td><td><b>DiffScreenshotpath</b></td><td><b>BasePDFPath</b></td><td><b>ActaulPDFPath</b></td>v<td><b>DiffPDFTextPath</b></td></tr>");
	      //close html file
	     // htmlStringBuilder.append("</table></body></html>");
	      //write html string content to a file
	   
	                  WriteToFile(htmlStringBuilder.toString(),FileName);
	                  
	                  //addrow(TestcaseName, BaseScreenshotpath, ActualScreenshotpath, DiffScreenshotpath, BasePDFPath, ActaulPDFPath, DiffPDFTextPath,"testfile.html") ;
	           } catch (IOException e) {
	                  // TODO Auto-generated catch block
	                  e.printStackTrace();
	           }
	  
	           
	    
	    }
	    
	    public static void WriteToFile_old(String fileContent, String fileName) throws IOException {
	            String projectPath = System.getProperty("user.dir");
	          //  System.out.println(projectPath);
	            String tempFile = projectPath+ "\\HTMLReports\\"+fileName;
	            File file = new File(tempFile);
	            // if file does exists, then delete and create a new file
	            if (file.exists()) {
	                File newFileName = new File(projectPath+"\\HTMLReports" + File.separator+ "backup_"+fileName);
	         // file.renameTo(newFileName);
	                        file.delete();
	            }
	            //write to file with OutputStreamWriter
	            OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile(),true);
	            Writer writer=new OutputStreamWriter(outputStream);
	            writer.write(fileContent);
	            writer.close();

	        }
	    
	     public static void WriteToRow_old(String fileContent, String fileName) throws IOException {
	            String projectPath = System.getProperty("user.dir");
	           // System.out.println(projectPath);
	            String tempFile = projectPath+ "\\HTMLReports\\"+fileName;
	            File file = new File(tempFile);
	            // if file does exists, then delete and create a new file
	            if (file.exists()) {
	                File newFileName = new File(projectPath+"\\HTMLReports" + File.separator+ "backup_"+fileName);
	      // file.renameTo(newFileName);
	                  
	            }
	            //write to file with OutputStreamWriter
	            OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile(),true);
	            Writer writer=new OutputStreamWriter(outputStream);
	            writer.write(fileContent);
	            writer.close();

	        }
	    
	     
	     
	     public  void addrow(String StepNumber, String StepDescription, String BaseURL, String ActualResult, String DifferenceResult, String Status,String Filepath)
	     {
	       //System.out.println("Adding a row in HTML File"); 
	       try {
	              StringBuilder htmlStringBuilder=new StringBuilder();
	              String Spancode = null;
	              
	              if (Status.equals("Pass")){
	                    Spancode = "dot-badge dot-badge__success";
	              }
	              if (Status.equals("Warning")){
	                    Spancode = "dot-badge dot-badge__warning";
	              }
	              if (Status.equals("Fail")){
	                    Spancode = "dot-badge dot-badge__default";
	              }
	             // System.out.println("Adding a row in HTML File");
	              
	              BaseURL = BaseURL.replace(System.getProperty("user.dir"), "..");
	              ActualResult = ActualResult.replace(System.getProperty("user.dir"), "..");
	              DifferenceResult=DifferenceResult.replace(System.getProperty("user.dir"), "..");
	             
	              
//	              if(BaseURL == ""){
//	                    //htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><b>"+Status+"</b></td></tr>");
//	                  htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><b></b></td><td><b>"+ActualResult+"</b></td><td></td><td></td></tr>");
//	                  WriteToRow(htmlStringBuilder.toString(),Filepath);
//	                    
//	              }
//	              else
//	              {
//	              
//	              //htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><b>"+Status+"</b></td></tr>");
//	              htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><span class=\""+ Spancode + "\"><b>"+Status+"</b></td></tr>");
//	              WriteToRow(htmlStringBuilder.toString(),Filepath);
//	              }

//	              if (StepNumber =="ScreenShot"){
//	                   
//	            	  htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><b></b></td><td><p><a href=\""+ActualResult+"\"> ScreenShot </a></p></td><td></td><td></td></tr>");
//		                 WriteToRow(htmlStringBuilder.toString(),Filepath);
//	                   
//	             }
//	              
//	              if (StepNumber =="Console Output"){
//	                   
//	            	  htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><b></b></td><td><p><a href=\""+ActualResult+"\"> Console Output </a></p></td><td></td><td></td></tr>");
//		                 WriteToRow(htmlStringBuilder.toString(),Filepath);
//	                   
//	             }
	              
	              
	              
	              
	              if (StepNumber =="Comment"){
	                   
	                   htmlStringBuilder.append("<tr><td><b><font color="+"red"+">"+StepDescription+"</font></b></td><td><b></b></td><td></td><td></td><td></td></tr>");
	                 WriteToRow(htmlStringBuilder.toString(),Filepath);
	                   
	             }
	             else
	             {
	                        
	             
	             if(BaseURL == "" & Status != "" ){
	                   //htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><b>"+Status+"</b></td></tr>");
	                 htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><b></b></td><td><b>"+ActualResult+"</b></td><td></td><td><span class=\""+ Spancode + "\"><b>"+Status+"</b></td></tr>");
	                 WriteToRow(htmlStringBuilder.toString(),Filepath);
	                   
	             }
	             else  if(BaseURL == "" & Status == "")
	             {
	            //htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><b>"+Status+"</b></td></tr>");
	                 htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><b></b></td><td><p><a href=\""+ActualResult+"\"> Screen Shot </a></p></td><td></td><td></td></tr>");
	                 WriteToRow(htmlStringBuilder.toString(),Filepath);
	             }
	             else if((BaseURL.contains(".png")) || (BaseURL.contains(".xlsx")) ||(BaseURL.contains(".pdf")) )
	             {
	                               
	             //htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><b>"+Status+"</b></td></tr>");
	             htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><span class=\""+ Spancode + "\"><b>"+Status+"</b></td></tr>");
	             WriteToRow(htmlStringBuilder.toString(),Filepath);
	             }
	             else
	             {
	                   //htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><b>"+Status+"</b></td></tr>");
	                 htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><b>"+BaseURL+"</b> Expected Result </a></p></td><td><p><b>"+ActualResult+"</b> Actual Result </a></p></td><td></td><td><span class=\""+ Spancode + "\"><b>"+Status+"</b></td></tr>");
	                 WriteToRow(htmlStringBuilder.toString(),Filepath);
	             
	             }

	             }	       
	       } catch (IOException e) {
	              e.printStackTrace();
	          }
	     }

	     
	     
	     
	    public static void addrow_0ld(String StepNumber, String StepDescription, String BaseURL, String ActualResult, String DifferenceResult, String Status,String Filepath)
	    {
	         try {
	             StringBuilder htmlStringBuilder=new StringBuilder();
	             String Spancode = null;
	             if (Status.equals("Pass")){
	            	 Spancode = "dot-badge dot-badge__success";
	             }
	             if (Status.equals("Warning")){
	            	 Spancode = "dot-badge dot-badge__warning";
	             }
	             if (Status.equals("Fail")){
	            	 Spancode = "dot-badge dot-badge__default";
	             }
	             BaseURL = BaseURL.replace(System.getProperty("user.dir"), "..");
	             ActualResult = ActualResult.replace(System.getProperty("user.dir"), "..");
	             DifferenceResult=DifferenceResult.replace(System.getProperty("user.dir"), "..");
	             
	             if(BaseURL == ""){
	            	 //htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><b>"+Status+"</b></td></tr>");
	                 htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><b></b></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td></td><td></td></tr>");
	                 WriteToRow(htmlStringBuilder.toString(),Filepath);
	            	 
	             }
	             else
	             {
	             
	             //htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><b>"+Status+"</b></td></tr>");
	             htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><span class=\""+ Spancode + "\"><b>"+Status+"</b></td></tr>");
	             WriteToRow(htmlStringBuilder.toString(),Filepath);
	             }
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	    }
	    
	    
	    
	    public static void addrow_Screenshot(String StepNumber, String StepDescription, String BaseURL, String ActualResult, String DifferenceResult, String Status,String Filepath)
		 {
	    	try {
	             StringBuilder htmlStringBuilder=new StringBuilder();
	             String Spancode = null;
	             if (Status.equals("Pass")){
	            	 Spancode = "dot-badge dot-badge__success";
	             }
	             if (Status.equals("Warning")){
	            	 Spancode = "dot-badge dot-badge__warning";
	             }
	             if (Status.equals("Fail")){
	            	 Spancode = "dot-badge dot-badge__default";
	             }
	              
	      
	             htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><b></b></td><td><p><a href=\""+ActualResult+"\"> ScreenShot </a></p></td><td></td><td></td></tr>");
                 WriteToRow(htmlStringBuilder.toString(),Filepath);
	             
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
		 }  
	    public static void addrow_ConsoleOutput(String StepNumber, String StepDescription, String BaseURL, String ActualResult, String DifferenceResult, String Status,String Filepath)
		 {
	    	try {
	             StringBuilder htmlStringBuilder=new StringBuilder();
	             String Spancode = null;
	             if (Status.equals("Pass")){
	            	 Spancode = "dot-badge dot-badge__success";
	             }
	             if (Status.equals("Warning")){
	            	 Spancode = "dot-badge dot-badge__warning";
	             }
	             if (Status.equals("Fail")){
	            	 Spancode = "dot-badge dot-badge__default";
	             }
	              
	      
	             htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><b></b></td><td><p><a href=\""+ActualResult+"\"> Console Output </a></p></td><td></td><td></td></tr>");
                 WriteToRow(htmlStringBuilder.toString(),Filepath);
	             
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
		 }  
	    public  void addrow_Twoparm(String StepNumber, String StepDescription, String BaseURL, String ActualResult, String DifferenceResult, String Status,String Filepath)
		 {
	    	try {
	             StringBuilder htmlStringBuilder=new StringBuilder();
	             String Spancode = null;
	             if (Status.equals("Pass")){
	            	 Spancode = "dot-badge dot-badge__success";
	             }
	             if (Status.equals("Warning")){
	            	 Spancode = "dot-badge dot-badge__warning";
	             }
	             if (Status.equals("Fail")){
	            	 Spancode = "dot-badge dot-badge__default";
	             }
	              
	      
	             //htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><p><a href=\""+BaseURL+"\"> Expected Result </a></p></td><td><p><a href=\""+ActualResult+"\"> Actual Result </a></p></td><td><p><a href=\""+DifferenceResult+"\"> Difference Result </a></p></td><td><b>"+Status+"</b></td></tr>");
	             htmlStringBuilder.append("<tr><td><b>"+StepDescription+"</b></td><td><b></b></td><td><b>"+ActualResult+"</b></td><td></td><td></td></tr>");
                 WriteToRow(htmlStringBuilder.toString(),Filepath);
	             
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
		 }  

}
