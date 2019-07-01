package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import base.DBUtil;
import base.Testbase;

public class ReadData extends Testbase{
	public static Properties Query = new Properties();
	public static FileInputStream fis;
	public static HashMap<String, String> EstDetailsNewEstimate = new HashMap<String, String>();
	public static HashMap<String, HashMap<String, String>> EStCreateproductComp = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> ParentChildCombinationWithLinkData = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> EstQTYPage = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> EstCharacteristicEachComponent = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> EstDetailsPaperSpec = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, String> CharCPGraphBindStitch	 = new HashMap<String, String>();
	public static HashMap<String, HashMap<String, String>> EstDetailsColorandVarnish = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> EstDetailsPaperFormat = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> CharCPGraphStampingItem = new HashMap<String, HashMap<String, String>>();
	String DBuser=Config.getProperty("DBUsername");
	String DBpass=Config.getProperty("DBPassWord");
	String DBurl=Config.getProperty("DBUrl");
	ResultSet rs,rs1;
	String OptionDesc=null;
	
	HashSet<String> Options=new HashSet<>();
	HashMap<String,HashSet<String>>Products=new HashMap<String,HashSet<String>>();
	HashMap<String,HashMap<String,HashSet<Double>>> Quantities= new HashMap<String,HashMap<String,HashSet<Double>>>();
		
	
	public HashMap<String, String> IdentificationPage(int EstimateId) throws IOException, ClassNotFoundException, SQLException
	{
		
		EstDetailsNewEstimate.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\IdentificationPage.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")));

		try
		{
			
			while(rs.next())
			{
				String CustCode=rs.getString("CustomerCode");
				String CustName=rs.getString("CustomerName");
				String ConctName=rs.getString("ContactName");
				String CodeAgency=rs.getString("AgencyCode");
				String NameAgency=rs.getString("AgencyName");
				String Codesalesperson=rs.getString("SalesPersonCode");
				String NameSalesperson=rs.getString("SalesPersonName");
				String NameCsr=rs.getString("CSRName");
				String CodeProject=rs.getString("ProjectCode");
				String DescProject=rs.getString("ProjectDesc");
				String EstType=rs.getString("EstimateType");
				String EstTitle=rs.getString("EstimateTitle");
				String ProdLine=rs.getString("ProductLine");
				String VersionSpecification=rs.getString("VersionSpec");
				String ProductSubline=rs.getString("SublineProduct");
				String DlryType=rs.getString("DeliveryType");
				String TypeSales=rs.getString("SaleType");
				String Payterms=rs.getString("PaymentTerm");
				String RemurationGrp=rs.getString("RemunerationGroup");
				String Quantity=rs.getString("Qty");
				

				EstDetailsNewEstimate.put("CustomerCode", CustCode != null ? CustCode : "");
				EstDetailsNewEstimate.put("CustomerName", CustName != null ? CustName : "");
				EstDetailsNewEstimate.put("ContactName", ConctName != null ? ConctName : "");
				EstDetailsNewEstimate.put("AgencyCode", CodeAgency != null ? CodeAgency : "");
				EstDetailsNewEstimate.put("AgencyName", NameAgency != null ? NameAgency : "");
				EstDetailsNewEstimate.put("SalesPersonCode", Codesalesperson != null ? Codesalesperson : "");
				EstDetailsNewEstimate.put("SalesPersonName", NameSalesperson != null ? NameSalesperson : "");
				EstDetailsNewEstimate.put("CSRName", NameCsr != null ? NameCsr : "");
				EstDetailsNewEstimate.put("ProjectCode", CodeProject != null ? CodeProject : "");
				EstDetailsNewEstimate.put("ProjectDesc", DescProject != null ? DescProject : "");
				EstDetailsNewEstimate.put("EstimateType", EstType != null ? EstType : "");
				EstDetailsNewEstimate.put("EstimateTitle", EstTitle != null ? EstTitle : "");
				EstDetailsNewEstimate.put("ProductLine", ProdLine != null ? ProdLine : "");
				EstDetailsNewEstimate.put("VersionSpec", VersionSpecification != null ? VersionSpecification : "");
				EstDetailsNewEstimate.put("SublineProduct", ProductSubline != null ? ProductSubline : "");
				EstDetailsNewEstimate.put("DeliveryType", DlryType != null ? DlryType : "");
				EstDetailsNewEstimate.put("SaleType", TypeSales != null ? TypeSales : "");
				EstDetailsNewEstimate.put("PaymentTerm", Payterms != null ? Payterms : "");
				EstDetailsNewEstimate.put("RemunerationGroup", RemurationGrp != null ? RemurationGrp : "");
				EstDetailsNewEstimate.put("Qty", Quantity != null ? Quantity : "");
				
			}
			     
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}

		iqdb.Closeconnection();
		return EstDetailsNewEstimate;

	
	}
	
	public HashMap<String, HashMap<String, String>> CreateProductandComponents(int EstimateId) throws IOException, ClassNotFoundException, SQLException
	{
		EStCreateproductComp.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\CreateProductComponents.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")));
		try
		{
			
			while(rs.next())
			{

				HashMap<String, String> EstProductCreation = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				String QtyDescp=rs.getString("QtyDescription");
				String CompDescp=rs.getString("ComponentDescription");
				String CompOrder=rs.getString("ComponentOrder");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CompFinal=rs.getString("ComponentFinal");

				EstProductCreation.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstProductCreation.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstProductCreation.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstProductCreation.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				EstProductCreation.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				EstProductCreation.put("QtyDescription", QtyDescp != null ? QtyDescp : "");

				EStCreateproductComp.put(CompOrder, EstProductCreation);

			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}

		iqdb.Closeconnection();
		return EStCreateproductComp;

		
	}
	
	public HashMap<String, HashMap<String, String>> CreateProductandComponents(Integer EstimateId,String Option) throws IOException, ClassNotFoundException, SQLException
	{

		EStCreateproductComp.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CreateProductandComponents.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##IdItemOption##", Option));
		try
		{
			
			while(rs.next())
			{

				HashMap<String, String> EstProductCreation = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				//String QtyDescp=rs.getString("QtyDescription");
				String CompDescp=rs.getString("ComponentDescription");
				String CompOrder=rs.getString("ComponentOrder");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CompFinal=rs.getString("ComponentFinal");

				EstProductCreation.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstProductCreation.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstProductCreation.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstProductCreation.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				EstProductCreation.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				//EstProductCreation.put("QtyDescription", QtyDescp != null ? QtyDescp : "");

				EStCreateproductComp.put(CompOrder, EstProductCreation);

			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}

		iqdb.Closeconnection();
		return EStCreateproductComp;

		
	
	}
	public HashMap<String, HashMap<String, String>>ParentChildComponent(int Estimateid,String Option) throws IOException, ClassNotFoundException, SQLException
	{
		ParentChildCombinationWithLinkData.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\ParentChildCombination.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##IdItemOption##", Option));
		
		try
		{
			int keyval=1;
			while(rs.next())
			{

				HashMap<String, String> ProductChildLinkData = new HashMap<String, String>();
				String DBParentComponent=rs.getString("ParentCmp");
				String DBChildComponent=rs.getString("ChildCmp");
				String DBParentChildAggregationType=rs.getString("AggregationType");
				String DBParentChildIsIndependentProduction=rs.getString("IsIndependentProduction");
				String DBParentChildUses=rs.getString("Uses");
				String DBParentChildForEach=rs.getString("ForEach");
				String DBParentChildFixedDemand=rs.getString("FixedDemand");

				ProductChildLinkData.put("ParentComponent", DBParentComponent != null ? DBParentComponent : "");
				ProductChildLinkData.put("ChildComponent", DBChildComponent != null ? DBChildComponent : "");
				ProductChildLinkData.put("AggregationType", DBParentChildAggregationType != null ? DBParentChildAggregationType : "");
				ProductChildLinkData.put("IsIndependentProduction", DBParentChildIsIndependentProduction != null ? DBParentChildIsIndependentProduction : "");
				ProductChildLinkData.put("Uses", DBParentChildUses != null ? DBParentChildUses : "");
				ProductChildLinkData.put("ForEach", DBParentChildForEach != null ? DBParentChildForEach : "");
				ProductChildLinkData.put("FixedDemand", DBParentChildFixedDemand != null ? DBParentChildFixedDemand : "");
				

				ParentChildCombinationWithLinkData.put(keyval+"",ProductChildLinkData);
				++keyval;
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		iqdb.Closeconnection();
		return ParentChildCombinationWithLinkData;
	}

	public HashMap<String, HashMap<String, String>> QtyForEst(int EstimateId) throws SQLException, ClassNotFoundException, IOException
	{
		EstQTYPage.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\QuantityforEstimate.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")));
		
		try
		{
			
			while(rs.next())
			{

				HashMap<String, String> EstQtyPageData = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				String QtyEng=rs.getString("ENG");
				String QtyComponentName=rs.getString("ComponentName");
				String QTYPageQty=rs.getString("Qty");
				String QTYUnit=rs.getString("Unit");
				String QtyComponentId=rs.getString("ComponentID");

				EstQtyPageData.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstQtyPageData.put("ENG", QtyEng != null ? QtyEng : "");
				EstQtyPageData.put("ComponentName", QtyComponentName != null ? QtyComponentName : "");
				EstQtyPageData.put("Qty", QTYPageQty != null ? QTYPageQty : "");
				EstQtyPageData.put("Unit", QTYUnit != null ? QTYUnit : "");
				EstQtyPageData.put("ComponentID", QtyComponentId != null ? QtyComponentId : "");

				EstQTYPage.put(QtyComponentName, EstQtyPageData);

			
			}
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		iqdb.Closeconnection();
		return EstQTYPage;
	}
	public HashMap<Integer, HashMap<String, String>> QtyForEst(int EstimateId,String Option) throws IOException, ClassNotFoundException, SQLException
	{
		HashMap<Integer, HashMap<String, String>> EstQTYPage = new HashMap<Integer, HashMap<String, String>>();
		HashSet<Integer> HS=new HashSet<>();
		
		
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\QuantityForEstimate.properties");
		Query.load(fis);
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		
		ResultSet rs;
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		//DBUtil iqdb=new DBUtil("jdbc:sqlserver://monarchqa18:1433;databaseName=iQuote", "sa", "efi@India");
		//System.out.println(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", "1"));
		//rs = iqdb.RunQuery(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", "1"));
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##IdItemOption##", Option));
		while(rs.next()){
			
			

			String IdItemOptionQty=rs.getString("IdItemOptionQty");
			HS.add(Integer.parseInt(IdItemOptionQty));
			
		}
		System.out.println(HS);
		for(int h1:HS) {
			
			HashMap<String, String> EstQtyPageData = new HashMap<String, String>();
			 rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##IdItemOption##", Option)+" and  qttTMItemOptionQty.IdItemOptionQty= "+h1);
	          
	            while(rs.next())
	            {
	            	
	            	String QtyComponentName=rs.getString("ComponentName");
					String QTYPageQty=rs.getString("Qty");
					EstQtyPageData.put(QtyComponentName, QTYPageQty);
					
	            }
	            EstQTYPage.put(h1, EstQtyPageData);
	            
	      }
		System.out.println(EstQTYPage);		
		return EstQTYPage;
		
	}
	public HashMap<String, HashMap<String, String>> CharacteristicForEachComponent(int Estimateid, String CompOrder) throws IOException, ClassNotFoundException, SQLException
	{
		EstCharacteristicEachComponent.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\CharacteristicsforEachComponent.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##CompOrder##", CompOrder));
		
		try
		{
			
			while(rs.next())
			{

				//	String Quantity=rs.getString("Qty");
				HashMap<String, String> Chardescpval=new HashMap<String,String>();


				String OptionDescp=rs.getString("OptionDescription");
				String QuantityDescp=rs.getString("QtyDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String FixedCharDescp=rs.getString("FixedCharacteristicDesc");
				String CharDescp=rs.getString("CharacteristicDesc");

				Chardescpval.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				Chardescpval.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				Chardescpval.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				Chardescpval.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				Chardescpval.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				Chardescpval.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				Chardescpval.put("QtyDescription", QuantityDescp != null ? QuantityDescp : "");
				Chardescpval.put("FixedCharacteristicDesc", FixedCharDescp != null ? FixedCharDescp : "");

				EstCharacteristicEachComponent.put(CharDescp, Chardescpval);
				//System.out.println(Collections.singletonList(EstDetailsQty));
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		iqdb.Closeconnection();
		return EstCharacteristicEachComponent;
	}
	
	public HashMap<String, HashMap<String,String>> CharacteristicForEachComponent(int Estimateid, String Option,String CompOrder)throws IOException, ClassNotFoundException, SQLException{

		EstCharacteristicEachComponent.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsforEachComponent.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##CompOrder##", CompOrder).replace("##IdItemOption##", Option));
		
		try
		{
			
			while(rs.next())
			{

				//	String Quantity=rs.getString("Qty");
				HashMap<String, String> Chardescpval=new HashMap<String,String>();


				String OptionDescp=rs.getString("OptionDescription");
				String IdPSCmp=rs.getString("IdPSCmp");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String FixedCharDescp=rs.getString("FixedCharacteristicDesc");
				String CharDescp=rs.getString("CharacteristicDesc");

				Chardescpval.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				Chardescpval.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				Chardescpval.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				Chardescpval.put("ComponentOrder", CompOrder1 != null ? CompOrder1 : "");
				Chardescpval.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				Chardescpval.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				Chardescpval.put("IdPSCmp", IdPSCmp != null ? IdPSCmp : "");
				Chardescpval.put("FixedCharacteristicDesc", FixedCharDescp != null ? FixedCharDescp : "");

				EstCharacteristicEachComponent.put(CharDescp, Chardescpval);
				//System.out.println(Collections.singletonList(EstDetailsQty));
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		iqdb.Closeconnection();
		return EstCharacteristicEachComponent;
	
	}
	public HashMap<String, HashMap<String, String>> PaperSpec(int EstimateId, String CompOrder) throws IOException, ClassNotFoundException, SQLException

	{
		EstDetailsPaperSpec.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\CharacteristicsforEachComponent.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs").replace("##ComponentOrder##", CompOrder)));
		try
		{
			int valkey=1;
			while(rs.next())
			{

				HashMap<String, String> EstColorandVarnish = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				String CompDescp=rs.getString("ComponentDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDesc=rs.getString("CharacteristicDesc");
				String CmpCarac=rs.getString("idPSCmpCarac");
				String PageSubstrateType=rs.getString("SubstrateType");
				String PageGrammage=rs.getString("Grammage");
				String PageLine=rs.getString("Line");
				String PageProvided=rs.getString("Provided");
				String Pageformatwidth=rs.getString("formatwidth");
				String Pageformatheight=rs.getString("formatheight");
				String PageGrainDirection=rs.getString("GrainDirection");
				String Pagemoreoptions=rs.getString("moreoptions");
				String PageNotes=rs.getString("Notes");
				String PageManufacturer=rs.getString("Manufacturer");
				String PageOtherManufacturer=rs.getString("OtherManufacturer");
				String PageSimulatesGenericSubstrate=rs.getString("SimulatesGenericSubstrate");

				EstColorandVarnish.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstColorandVarnish.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstColorandVarnish.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstColorandVarnish.put("ComponentOrder", CompOrder1 != null ? CompOrder1 : "");
				EstColorandVarnish.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				EstColorandVarnish.put("CharacteristicDesc", CharDesc != null ? CharDesc : "");
				EstColorandVarnish.put("idPSCmpCarac", CmpCarac != null ? CmpCarac : "");
				EstColorandVarnish.put("SubstrateType", PageSubstrateType != null ? PageSubstrateType : "");
				EstColorandVarnish.put("Grammage", PageGrammage != null ? PageGrammage : "");
				EstColorandVarnish.put("Line", PageLine != null ? PageLine : "");
				EstColorandVarnish.put("Notes", PageNotes != null ? PageNotes : "");
				EstColorandVarnish.put("Provided", PageProvided != null ? PageProvided : "");
				EstColorandVarnish.put("formatwidth", Pageformatwidth != null ? Pageformatwidth : "");
				EstColorandVarnish.put("formatheight", Pageformatheight != null ? Pageformatheight : "");
				EstColorandVarnish.put("GrainDirection", PageGrainDirection != null ? PageGrainDirection : "");

				EstColorandVarnish.put("moreoptions", Pagemoreoptions != null ? Pagemoreoptions : "");
				EstColorandVarnish.put("Manufacturer", PageManufacturer != null ? PageManufacturer : "");
				EstColorandVarnish.put("OtherManufacturer", PageOtherManufacturer != null ? PageOtherManufacturer : "");
				EstColorandVarnish.put("SimulatesGenericSubstrate", PageSimulatesGenericSubstrate != null ? PageSimulatesGenericSubstrate : "");


				EstDetailsPaperSpec.put(CompOrder1, EstColorandVarnish);
				++valkey;
			
			}
			
		}
		catch(SQLException  e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		iqdb.Closeconnection();
		return EstDetailsPaperSpec;
		
	}

	public HashMap<String, String> CPGraphBindStitch(int Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws ClassNotFoundException, SQLException, IOException

	{
		CharCPGraphBindStitch.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphBindStitch.properties");
		Query.load(fis);
		System.out.println("To remove");
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			//loop
			while(rs.next())
			{
				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");


				String CPGraphBindStitchQuantityOfWireStitches=rs.getString("QuantityOfWireStitches");
				String CPGraphBindStitchNote=rs.getString("Note");
				String CPGraphBindStitchWireStichWidth=rs.getString("WireStichWidth");
				String CPGraphBindStitchApplication1=rs.getString("Application1");
				String CPGraphBindStitchShape=rs.getString("Shape");



				CharCPGraphBindStitch.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphBindStitch.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphBindStitch.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphBindStitch.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphBindStitch.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphBindStitch.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphBindStitch.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");


				CharCPGraphBindStitch.put("QuantityOfWireStitches", CPGraphBindStitchQuantityOfWireStitches != null ? CPGraphBindStitchQuantityOfWireStitches : "");
				CharCPGraphBindStitch.put("Note", CPGraphBindStitchNote != null ? CPGraphBindStitchNote : "");
				CharCPGraphBindStitch.put("WireStichWidth", CPGraphBindStitchWireStichWidth != null ? CPGraphBindStitchWireStichWidth : "");
				CharCPGraphBindStitch.put("Application1", CPGraphBindStitchApplication1 != null ? CPGraphBindStitchApplication1 : "");
				CharCPGraphBindStitch.put("Shape", CPGraphBindStitchShape != null ? CPGraphBindStitchShape : "");

			}
			
		}
		catch(SQLException  e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		//iqdb.Closeconnection();
		return CharCPGraphBindStitch;
		
	}

	public HashMap<String,HashMap<String, HashSet<String>>> QuantityPage(String string) throws ClassNotFoundException, SQLException, IOException{
		Set<String> Options=new HashSet<>();
		HashSet<String> Product=new HashSet<>();
		HashSet<String> Quantity=new HashSet<>();
		//HashMap<String,String> Quantity = new HashMap<>();
		HashMap<String, HashSet<String>> Products = new HashMap<String, HashSet<String> >();
		HashMap<String,HashMap<String, HashSet<String>>> ProQty=new HashMap<String,HashMap<String, HashSet<String>>>();
		HashMap<String,HashMap<String,HashMap<String,String>>> Quantities=new HashMap<String,HashMap<String,HashMap<String,String>>>();
		
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Quantities.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")));
		try {
			//loop
			while(rs.next())
			{
				
				String Option=rs.getString("OptionDescription");
				String Product1=rs.getString("ComponentName");
				String Quantity1=rs.getString("Qty");
				
				/*Options.add(Option);
				Product.add(Product1);
				Quantity.put(Product1, Quantity1);
				ProQty.put(Product1, Quantity);
				Products.put(Option, Quantity);
				Quantities.put(Option, ProQty);*/
				Quantity.add(Quantity1);
				Products.put(Product1, Quantity);
				ProQty.put(Option, Products);
				//Quantity.clear();
				//Products.clear();
				
				
				
				
				
				
			}
		}
		catch(SQLException e)
		{
			
			System.out.println("failed");
		}
		iqdb.Closeconnection();
		return ProQty;
	}

	public HashSet<String> NoOfOptions(String EstimateId) throws IOException, ClassNotFoundException, SQLException{
		Options.clear();
		
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\QuotationUniqueOptions.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")));
		
		try
		{
			while(rs.next()){
				String IdItemOption=rs.getString("IdItemOption");
				Options.add(IdItemOption);
			}
			
		}
		catch(SQLException  e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		iqdb.Closeconnection();
		return Options;
	}
	
	public HashMap<String,HashSet<String>> NoOfProductsForOption(String Optionname,String Estimate) throws IOException, ClassNotFoundException, SQLException{
		Products.clear();
		HashSet<String> Product=new HashSet<>();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Quantities.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		String Query2=Query1.replace("##Estimate##", Config.getProperty("EstimateIDs"))+"and qttTMItemOption.Description= '"+Optionname+"'";
		rs = iqdb.RunQuery(Query2);
		
		try
		{
			while(rs.next()) {
				String pro=rs.getString("ComponentName");
				Product.add(pro);
				Products.put(Optionname, Product);
			}
		}
		catch(SQLException  e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		iqdb.Closeconnection();
		
		return Products;
		
	}
	
	public String ReturnOptionDesForEstandOption(int Estimate,String Option) throws IOException, ClassNotFoundException, SQLException {
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\GetOptionDesc.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##IdItemOption##", Option));
		while(rs.next()) {
			OptionDesc=rs.getString("OptionDescription");
		}
		return OptionDesc;
		
	}
	public HashMap<String,HashMap<String,HashSet<Double>>> NoOfQtyForProductForOption(String Product,String Optionname,String Estimate) throws IOException, ClassNotFoundException, SQLException{
		Quantities.clear();
		HashSet<Double> Quantity=new HashSet<>();
		HashMap<String,HashSet<Double>> Product1 = new HashMap<String,HashSet<Double>> ();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Quantities.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		String Query2=Query1.replace("##Estimate##", Config.getProperty("EstimateIDs"))+"and qttTMItemOption.Description= '"+Optionname+"'"+"and qttTMPSCmp.Description= '"+Product+"'";
		rs = iqdb.RunQuery(Query2);
		
		try
		{
			while(rs.next()) {
				String qty=rs.getString("Qty");
				double convertedNumber = Double.parseDouble(qty);
				Quantity.add(convertedNumber);
				String pro=rs.getString("ComponentName");
				Product1.put(pro, Quantity);
				String opt=rs.getString("OptionDescription");
				Quantities.put(opt, Product1);
				
			}
			
		}catch(SQLException  e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		iqdb.Closeconnection();
		
		return Quantities;
	}

	public HashMap<String, HashMap<String, String>> CPGraphColorVanish(int EstimateId, String IdItemOption ,String CompOrderval) throws IOException, SQLException, ClassNotFoundException{
		EstDetailsColorandVarnish.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphColorVanish.properties");
		Query.load(fis);
		System.out.println("To remove");
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs1 = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##CompOrder##", CompOrderval).replace("##IdItemOption##", IdItemOption));
		try
		{
			int valkey1=1;
			while(rs1.next()) {

				HashMap<String, String> EstColorandVarnish = new HashMap<String, String>();

				String OptionDescp=rs1.getString("OptionDescription");
				String CompDescp=rs1.getString("ComponentDescription");
				String CompFinal=rs1.getString("ComponentFinal");
				String CompOrder=rs1.getString("ComponentOrder");
				String CompTypeDescp=rs1.getString("ComponentTypeDesc");
				String CharDesc=rs1.getString("CharacteristicDesc");
				String PageColorOrVarnish=rs1.getString("ColorOrVarnish");
				String PageFrontOrBack=rs1.getString("FrontOrBack");
				String PageMainColor=rs1.getString("MainColor");
				String PageColor=rs1.getString("Color");
				String PageNotes=rs1.getString("Notes");
				String PagePrintInput=rs1.getString("PrintInput");
				String PageAssociation=rs1.getString("Association");
				String PageCoverage=rs1.getString("Coverage");
				String PageAdditionalPlate=rs1.getString("AdditionalPlate");




				EstColorandVarnish.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstColorandVarnish.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstColorandVarnish.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstColorandVarnish.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				EstColorandVarnish.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				EstColorandVarnish.put("CharacteristicDesc", CharDesc != null ? CharDesc : "");
				EstColorandVarnish.put("ColorOrVarnish", PageColorOrVarnish != null ? PageColorOrVarnish : "");
				EstColorandVarnish.put("FrontOrBack", PageFrontOrBack != null ? PageFrontOrBack : "");
				EstColorandVarnish.put("MainColor", PageMainColor != null ? PageMainColor : "");
				EstColorandVarnish.put("Color", PageColor != null ? PageColor : "");
				EstColorandVarnish.put("Notes", PageNotes != null ? PageNotes : "");
				EstColorandVarnish.put("PrintInput", PagePrintInput != null ? PagePrintInput : "");
				EstColorandVarnish.put("Association", PageAssociation != null ? PageAssociation : "");
				EstColorandVarnish.put("Coverage", PageCoverage != null ? PageCoverage : "");
				EstColorandVarnish.put("AdditionalPlate", PageAdditionalPlate != null ? PageAdditionalPlate : "");

				EstDetailsColorandVarnish.put(valkey1+"", EstColorandVarnish);
				++valkey1;
			
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		iqdb.Closeconnection();
		return EstDetailsColorandVarnish;
		
	}
	
	public HashMap<String, HashMap<String, String>> CPGraphRegularFormat(int EstimateId, String IdItemOption,String CompOrder ) throws SQLException, ClassNotFoundException, IOException{
		EstDetailsPaperFormat.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphRegularFormat.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##CompOrder##", CompOrder).replace("##IdItemOption##", IdItemOption));
		try
		{
			while(rs.next()) {

				HashMap<String, String> EstColorandVarnish = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				String CompDescp=rs.getString("ComponentDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDesc=rs.getString("CharacteristicDesc");
				String CmpCarac=rs.getString("idPSCmpCarac");
				String PageFinishedFormatWidth=rs.getString("FinishedFormatWidth");
				String PageFinishedFormatHeight=rs.getString("FinishedFormatHeight");
				String PageFormat=rs.getString("Page");
				String PageBothSheetsEqual=rs.getString("BothSheetsEqual");
				String PageLeftFlap=rs.getString("LeftFlap");
				String PageRightFlap=rs.getString("RightFlap");
				String PageFlatWidth=rs.getString("FlatWidth");
				String PageFlatHeight=rs.getString("FlatHeight");
				String PageFlapRetreat=rs.getString("FlapRetreat");
				String PageFoldingSchemeCode=rs.getString("FoldingSchemeCode");
				String PageFoldingSchemeDescription=rs.getString("FoldingSchemeDescription");
				String PageLines=rs.getString("Lines");
				String PageLabelsacross=rs.getString("Labelsacross");
				String PageBleed=rs.getString("Bleed");
				String PageArrangement=rs.getString("Arrangement");
				String PageApplicationType=rs.getString("ApplicationType");
				String PageTopMargin=rs.getString("TopMargin");
				String PageBottomMargin=rs.getString("BottomMargin");
				String PageLeftMargin=rs.getString("LeftMargin");
				String PageRightMargin=rs.getString("RightMargin");
				String PageAllowsWhiteElementWasteInTheLayout=rs.getString("AllowsWhiteElementWasteInTheLayout");
				String PageQuantityOfVersions=rs.getString("QuantityOfVersions");
				String PagePosition=rs.getString("Position");
				String PageSize=rs.getString("Size");

				EstColorandVarnish.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstColorandVarnish.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstColorandVarnish.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstColorandVarnish.put("ComponentOrder", CompOrder1 != null ? CompOrder1 : "");
				EstColorandVarnish.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				EstColorandVarnish.put("CharacteristicDesc", CharDesc != null ? CharDesc : "");
				EstColorandVarnish.put("idPSCmpCarac", CmpCarac != null ? CmpCarac : "");
				EstColorandVarnish.put("FinishedFormatWidth", PageFinishedFormatWidth != null ? PageFinishedFormatWidth : "");
				EstColorandVarnish.put("FinishedFormatHeight", PageFinishedFormatHeight != null ? PageFinishedFormatHeight : "");
				EstColorandVarnish.put("Line", PageLines != null ? PageLines : "");
				EstColorandVarnish.put("Page", PageFormat != null ? PageFormat : "");
				EstColorandVarnish.put("BothSheetsEqual", PageBothSheetsEqual != null ? PageBothSheetsEqual : "");
				EstColorandVarnish.put("LeftFlap", PageLeftFlap != null ? PageLeftFlap : "");
				EstColorandVarnish.put("RightFlap", PageRightFlap != null ? PageRightFlap : "");
				EstColorandVarnish.put("FlatWidth", PageFlatWidth != null ? PageFlatWidth : "");
				EstColorandVarnish.put("FlatHeight", PageFlatHeight != null ? PageFlatHeight : "");
				EstColorandVarnish.put("FlapRetreat", PageFlapRetreat != null ? PageFlapRetreat : "");
				EstColorandVarnish.put("FoldingSchemeCode", PageFoldingSchemeCode != null ? PageFoldingSchemeCode : "");
				EstColorandVarnish.put("FoldingSchemeDescription", PageFoldingSchemeDescription != null ? PageFoldingSchemeDescription : "");
				EstColorandVarnish.put("Lines", PageLines != null ? PageLines : "");
				EstColorandVarnish.put("Labelsacross", PageLabelsacross != null ? PageLabelsacross : "");
				EstColorandVarnish.put("Bleed", PageBleed != null ? PageBleed : "");
				EstColorandVarnish.put("Arrangement", PageArrangement != null ? PageArrangement : "");
				EstColorandVarnish.put("ApplicationType", PageApplicationType != null ? PageApplicationType : "");
				EstColorandVarnish.put("TopMargin", PageTopMargin != null ? PageTopMargin : "");
				EstColorandVarnish.put("BottomMargin", PageBottomMargin != null ? PageBottomMargin : "");
				EstColorandVarnish.put("LeftMargin", PageLeftMargin != null ? PageLeftMargin : "");
				EstColorandVarnish.put("RightMargin", PageRightMargin != null ? PageRightMargin : "");
				EstColorandVarnish.put("AllowsWhiteElementWasteInTheLayout", PageAllowsWhiteElementWasteInTheLayout != null ? PageAllowsWhiteElementWasteInTheLayout : "");
				EstColorandVarnish.put("QuantityOfVersions", PageQuantityOfVersions != null ? PageQuantityOfVersions : "");
				EstColorandVarnish.put("Position", PagePosition != null ? PagePosition : "");
				EstColorandVarnish.put("Size", PageSize != null ? PageSize : "");



				EstDetailsPaperFormat.put(CompOrder1, EstColorandVarnish);

			
			}
			
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}

		//iqdb.Closeconnection();
		return EstDetailsPaperFormat;
	}
	
	public HashMap<String, HashMap<String, String>> CPGraphHotStamping(int Estimateid, String IdItemOption, String CompOrder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException{
		CharCPGraphStampingItem.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphHotStamping.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##CompOrder##", CompOrder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			int valkey=1;
			HashMap<String, String> CharCPGraphStampingItemHM=new HashMap<String,String>(); 
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");

				String CPGraphStampingItemType=rs.getString("Type");
				String CPGraphStampingItemHeight=rs.getString("Height");
				String CPGraphStampingItemWidth=rs.getString("Width");
				String CPGraphStampingItemSurface=rs.getString("Surface");
				String CPGraphStampingItemInputNumber=rs.getString("InputNumber");
				String CPGraphStampingItemNote=rs.getString("Note");



				CharCPGraphStampingItemHM.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphStampingItemHM.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphStampingItemHM.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphStampingItemHM.put("ComponentOrder", CompOrder1 != null ? CompOrder1 : "");
				CharCPGraphStampingItemHM.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphStampingItemHM.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphStampingItemHM.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");


				CharCPGraphStampingItemHM.put("Type", CPGraphStampingItemType != null ? CPGraphStampingItemType : "");
				CharCPGraphStampingItemHM.put("Height", CPGraphStampingItemHeight != null ? CPGraphStampingItemHeight : "");
				CharCPGraphStampingItemHM.put("Width", CPGraphStampingItemWidth != null ? CPGraphStampingItemWidth : "");
				CharCPGraphStampingItemHM.put("Surface", CPGraphStampingItemSurface != null ? CPGraphStampingItemSurface : "");
				CharCPGraphStampingItemHM.put("InputNumber", CPGraphStampingItemInputNumber != null ? CPGraphStampingItemInputNumber : "");
				CharCPGraphStampingItemHM.put("Note", CPGraphStampingItemNote != null ? CPGraphStampingItemNote : "");

				CharCPGraphStampingItem.put(valkey+"", CharCPGraphStampingItemHM);
				++valkey;
			
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		iqdb.Closeconnection();
		return CharCPGraphStampingItem;
	}
	
	public HashMap<String, HashMap<String, String>> CPGraphMedia(int EstimateId,  String IdItemOption,String CompOrder) throws IOException, ClassNotFoundException, SQLException{
		EstDetailsPaperSpec.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphMedia.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil(DBurl, DBuser, DBpass);
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##ComponentOrder##", CompOrder).replace("##IdItemOption##", IdItemOption));
		try
		{
			int valkey=1;
			while(rs.next()) {

				HashMap<String, String> EstColorandVarnish = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				String CompDescp=rs.getString("ComponentDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDesc=rs.getString("CharacteristicDesc");
				String CmpCarac=rs.getString("idPSCmpCarac");
				String PageSubstrateType=rs.getString("SubstrateType");
				String PageGrammage=rs.getString("Grammage");
				String PageLine=rs.getString("Line");
				String PageProvided=rs.getString("Provided");
				String Pageformatwidth=rs.getString("formatwidth");
				String Pageformatheight=rs.getString("formatheight");
				String PageGrainDirection=rs.getString("GrainDirection");
				String Pagemoreoptions=rs.getString("moreoptions");
				String PageNotes=rs.getString("Notes");
				String PageManufacturer=rs.getString("Manufacturer");
				String PageOtherManufacturer=rs.getString("OtherManufacturer");
				String PageSimulatesGenericSubstrate=rs.getString("SimulatesGenericSubstrate");


				EstColorandVarnish.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstColorandVarnish.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstColorandVarnish.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstColorandVarnish.put("ComponentOrder", CompOrder1 != null ? CompOrder1 : "");
				EstColorandVarnish.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				EstColorandVarnish.put("CharacteristicDesc", CharDesc != null ? CharDesc : "");
				EstColorandVarnish.put("idPSCmpCarac", CmpCarac != null ? CmpCarac : "");
				EstColorandVarnish.put("SubstrateType", PageSubstrateType != null ? PageSubstrateType : "");
				EstColorandVarnish.put("Grammage", PageGrammage != null ? PageGrammage : "");
				EstColorandVarnish.put("Line", PageLine != null ? PageLine : "");
				EstColorandVarnish.put("Notes", PageNotes != null ? PageNotes : "");
				EstColorandVarnish.put("Provided", PageProvided != null ? PageProvided : "");
				EstColorandVarnish.put("formatwidth", Pageformatwidth != null ? Pageformatwidth : "");
				EstColorandVarnish.put("formatheight", Pageformatheight != null ? Pageformatheight : "");
				EstColorandVarnish.put("GrainDirection", PageGrainDirection != null ? PageGrainDirection : "");

				EstColorandVarnish.put("moreoptions", Pagemoreoptions != null ? Pagemoreoptions : "");
				EstColorandVarnish.put("Manufacturer", PageManufacturer != null ? PageManufacturer : "");
				EstColorandVarnish.put("OtherManufacturer", PageOtherManufacturer != null ? PageOtherManufacturer : "");
				EstColorandVarnish.put("SimulatesGenericSubstrate", PageSimulatesGenericSubstrate != null ? PageSimulatesGenericSubstrate : "");


				EstDetailsPaperSpec.put(CompOrder1, EstColorandVarnish);
				++valkey;
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}


		return EstDetailsPaperSpec;
	}

}
