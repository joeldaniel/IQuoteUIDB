package pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.Test;

import base.DBUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import utilities.AllureLogger;
import utilities.CommonFunctions;

public class Negotiation extends CommonFunctions{

	/*static ResultSet rs1;
	static ResultSet rs2;*/
	static HashMap<String,String> name = new HashMap<String,String>();
	
	public static void VerifyHeader(String DB,String BaseDBserver,String ActualDBserver,String BaseEstimate,String ActualEstimate) {
		Properties prop=initialize_properties("Header");
		String query=prop.getProperty("Query");
		ResultSet rs;
		boolean flag=true;
		try {
			//query=;
			//iqdb.Createconnection("jdbc:sqlserver://"+BaseDBserver+":1433;databaseName="+DB+"", "iquote", "1quot3p@ss");
			rs=iqdb.RunQuery(query.replace("+BaseEstimate+", BaseEstimate).replace("+ActualEstimate+", ActualEstimate).replace("+ActualDBserver+", ActualDBserver).replace("+BaseDBserver+", BaseDBserver).replace("+DB+", DB));
			while(rs.next()) {
				String OptionDesc=rs.getString("OptionDesc");
				String Customer=rs.getString("Customer");
				String SalesPerson=rs.getString("SalesPerson");
				String PaymentTerm=rs.getString("PaymentTerm");
				String Title=rs.getString("Title");
				String CurrencySymbol=rs.getString("CurrencySymbol");
				String Currency=rs.getString("Currency");
				String Contact=rs.getString("Contact");
				String Agency=rs.getString("Agency");
				//String CostRefDate=rs.getString("CostRefDate");
				String Product=rs.getString("Product");
				String TotalCmp=rs.getString("TotalCmp");
				
				name.put("OptionDesc", OptionDesc != null ? OptionDesc : "");
				name.put("Customer", Customer != null ? Customer : "");
				name.put("SalesPerson", SalesPerson != null ? SalesPerson : "");
				name.put("PaymentTerm", PaymentTerm != null ? PaymentTerm : "");
				name.put("Title", Title != null ? Title : "");
				name.put("CurrencySymbol", CurrencySymbol != null ? CurrencySymbol : "");
				name.put("Currency", Currency != null ? Currency : "");
				name.put("Contact", Contact != null ? Contact : "");
				name.put("Agency", Agency != null ? Agency : "");
				//name.put("CostRefDate", CostRefDate != null ? CostRefDate : "");
				name.put("Product", Product != null ? Product : "");
				name.put("TotalCmp", TotalCmp != null ? TotalCmp : "");
				
				  for (Map.Entry<String,String> entry : name.entrySet())  
			           if(!entry.getValue().contains("Both are Equal")) {
			        	   System.out.println(entry.getKey()+" Has mismatched value");
			        	   flag=false;
			        	   break;
			           }
				if(flag)
					System.out.println("All column values matched in header query");
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void VerifyHeaderResults(String BaseEstimate,String ActualEstimate) {
		ResultSet rs1,rs2;
		Properties prop=initialize_properties("TransformationCost");
		String query=prop.getProperty("Query");
		String base=query.replace("##Estimate##", BaseEstimate);
		String Actual=query.replace("##Estimate##", ActualEstimate);
		boolean flag=false;
		try {
			
		
			rs1=iqdb.RunQuery(base);
			rs2=iqdb.RunQuery(Actual);
			
			flag=DBUtil.compareResultSets(rs1,rs2,"");
			
		}
		catch(Exception e) {
			
		}
	}
	@Step("Verifying the base estimate reports with : {0} actual estimate : {1}")
	public static void VerifyActualvsBase(String BaseEstimate,String ActualEstimate,String [] fields) throws ClassNotFoundException, SQLException
	{
		 AllureLogger.logStep("Verifying report for "+fields);
		 ResultSet rs1,rs2;
		//String [] fields = {"ComponentDetails","Header","OtherCosts","OtherRawMaterialCost","OutSourcingCost","SalesPrice","SubstrateCost","TransformationCost","JobPlanning","JobMaterial"};
		for(String field:fields) {
			Properties prop=initialize_properties(field);
			String cquery=prop.getProperty("RowCountQuery");
			
			rs1=iqdb.RunQuery(cquery.replace("##Estimate##", BaseEstimate));
			rs2=iqdb.RunQuery(cquery.replace("##Estimate##", ActualEstimate));
			while(rs1.next() && rs2.next()) 
	          {
				  if(rs1.getString("rows").equals(rs2.getString("rows")) && Integer.parseInt(rs1.getString("rows"))!=0) {
					  	AllureLogger.markStepAsPassed("Base Table row count is : "+rs1.getString("rows")+" Actual Table row count is : "+rs2.getString("rows"));
					  	System.out.println("Row Count matched");
					    rs1.close();
						rs2.close();
						String query=prop.getProperty("Query");
						String base=query.replace("##Estimate##", BaseEstimate);
						String Actual=query.replace("##Estimate##", ActualEstimate);
						boolean flag=false;
						try {
							
						
							rs1=iqdb.RunQuery(base);
							rs2=iqdb.RunQuery(Actual);
							
							flag=DBUtil.compareResultSets(rs1,rs2,field);
							System.out.println(flag?"Pass for : "+field:"Fail for : "+field);
							//Allure.step(flag?"Pass for : "+field:"Fail for : "+field);
							if(flag)
								
								AllureLogger.markStepAsPassed("Pass for Field : "+field.toUpperCase());
							else
								AllureLogger.markStepAsFailed("Fail for Field : "+field.toUpperCase());
				  
						  }
							catch(Exception e) {
								
							}
				  }
				  else if(Integer.parseInt(rs1.getString("rows"))==0 && Integer.parseInt(rs2.getString("rows"))==0) {
					  AllureLogger.markStepAsPassed("Base table row count and actual table row count is Zero");
					  AllureLogger.markStepAsPassed("Verified for Field : "+field.toUpperCase());
				  }
				  else {
						  AllureLogger.markStepAsFailed("Base Table row count is : "+rs1.getString("rows")+" Actual Table row count is : "+rs2.getString("rows"));
						  AllureLogger.markStepAsFailed("Fail for Field : "+field.toUpperCase());
				  }
				
				
			}
	

	
		}
	}
}
