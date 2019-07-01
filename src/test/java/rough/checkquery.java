package rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;

import base.DBUtil;
import base.Testbase;

public class checkquery  {
	public static Properties Query = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	static String Option="14034";
	static String comp="1";
	static String chare="Saddle Stitching";
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphBindStitch.properties");
		Query.load(fis);
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		Config.load(fis);
		HashMap<Integer, HashMap<String, String>> EstQTYPage = new HashMap<Integer, HashMap<String, String>>();
		
		HashSet<Integer> HS=new HashSet<>();
		
		ResultSet rs;
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil("jdbc:sqlserver://monarchqa18:1433;databaseName=iQuote", "sa", "efi@India");
		//System.out.println(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", "1"));
		//rs = iqdb.RunQuery(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", "1"));
		//rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##IdItemOption##", Option));
		rs=iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##IdItemOption##", Option).replace("##CompOrder##", comp).replace("##CharteristicDescp##", chare));
		while(rs.next()){
			
			

			String IdItemOptionQty=rs.getString("OptionDescription");
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
	}
}

