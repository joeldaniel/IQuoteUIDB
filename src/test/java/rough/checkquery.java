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
import pages.Negotiation;

public class checkquery  {
	public static Properties Query = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	static String estimate="11766";
	static String Option="23282";
	static String comp="103";
	static String chare="EP";
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		/*fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphPackagingStrapping.properties");
		Query.load(fis);
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\PremierPress.properties");
		Config.load(fis);
		HashMap<Integer, HashMap<String, String>> EstQTYPage = new HashMap<Integer, HashMap<String, String>>();*/
		
		
		HashSet<Integer> HS=new HashSet<>();
		
		ResultSet rs;
		//String Query1=Query.getProperty("Query");
		String Query1="Fse";
		// opening database connection to MySQL server
	//	DBUtil iqdb=new DBUtil();
		//iqdb.Createconnection("jdbc:sqlserver://iquotedbdbqry:1433;databaseName=HubLabels", "iquote", "1quot3p@ss");
		//DBUtil iqdb=new DBUtil("jdbc:sqlserver://iquotedbdbqry:1433;databaseName=PremierPress", "iquote", "1quot3p@ss");
		//System.out.println(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", "1"));
		//rs = iqdb.RunQuery(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", "1"));
		//rs = iqdb.RunQuery(Query1.replace("##Estimate##", Config.getProperty("EstimateIDs")).replace("##IdItemOption##", Option));
		//rs=iqdb.RunQuery(Query1.replace("##Estimate##", estimate).replace("##IdItemOption##", Option).replace("##CompOrder##", comp).replace("##CharteristicDescp##", chare));
		//rs=iqdb.RunQuery(Query1);
		/*while(rs.next()){
			
			

			String IdItemOptionQty=rs.getString("OptionDescription");
			HS.add(Integer.parseInt(IdItemOptionQty));
			
		}*/
		System.out.println(HS);
		for(int h1:HS) {
			
			HashMap<String, String> EstQtyPageData = new HashMap<String, String>();
			 
	        //    EstQTYPage.put(h1, EstQtyPageData);
	            
	      }
	
		
		//System.out.println(EstQTYPage);
	}
}

