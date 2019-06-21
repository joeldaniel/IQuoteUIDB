package rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import base.DBUtil;
import base.Testbase;

public class colorvanish  {
	public static Properties Query = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		HashMap<String,String> HS=new HashMap<>();
		HashMap<String,HashMap<String,String>> HS1=new HashMap<String,HashMap<String,String>>();
		Set<String> st1=new HashSet<>();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\colorandvarnish.properties");
		Query.load(fis);
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		Config.load(fis);
		
		ResultSet rs,rs1;
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		DBUtil iqdb=new DBUtil("jdbc:sqlserver://monarchqa18:1433;databaseName=iQuote", "sa", "efi@India");
		//System.out.println(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", "1"));
		//rs = iqdb.RunQuery(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", "1"));
		rs = iqdb.RunQuery(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", Config.getProperty("CompOrder")));
		System.out.println(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", Config.getProperty("CompOrder")));
		while(rs.next()){
			String idPSCmp=rs.getString("idPSCmpCarac");
			rs1=iqdb.RunQuery(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", Config.getProperty("CompOrder")));
			int valkey=1;
			while(rs1.next()) {
				if(rs.getString("idPSCmpCarac").equals(idPSCmp)) {
					
					String color=rs1.getString("Color");
					if(st1.add(color)) {
						HS.put("color", rs1.getString("Color") != null ? rs1.getString("Color") : "");
						HS.put("S", rs1.getString("FrontOrBack") != null ? rs1.getString("FrontOrBack") : "");
						HS.put("Sd", rs1.getString("ColorOrVarnish") != null ? rs1.getString("ColorOrVarnish") : "");
						HS.put("sdw", rs1.getString("MainColor") != null ? rs1.getString("MainColor") : "");
						HS.put("se", rs1.getString("Coverage") != null ? rs1.getString("Coverage") : "");
						HS.put("sdfw", rs1.getString("AdditionalPlate") != null ? rs1.getString("AdditionalPlate") : "");
						valkey+=1;
					}
				}
			}
			st1.clear();
			HS1.put(idPSCmp, HS);
			
		}
		System.out.println(HS1);
		iqdb.Closeconnection();
	}
}
