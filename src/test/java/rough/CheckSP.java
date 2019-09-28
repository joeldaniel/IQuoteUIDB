package rough;


import pages.Negotiation;

public class CheckSP {
	
	/*public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		HashSet<Integer> HS=new HashSet<>();
		ResultSet rs;
		Connection con=null; 
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\Negotiation\\Header.properties");
		Config.load(fis);
		String Query1=Config.getProperty("Query");
		
		DBUtil iqdb=new DBUtil();
		con=iqdb.Createconnection("jdbc:sqlserver://iquotedbdbqry:1433;databaseName=HubLabels", "iquote", "1quot3p@ss");
		try {
		rs=iqdb.RunQuery(Query1.replace("+BaseEstimate+", "1076").replace("+ActualEstimate+", "1181").replace("+ActualDBserver+", "iquoteuiautodb71").replace("+BaseDBserver+", "iquotedbdbqry").replace("+DB+", "HubLabels"));
		while(rs.next()) {
			String OptionDescp=rs.getString("OptionDesc");
			System.out.println("Fse");
		}
		
		}
		finally {
			iqdb.Closeconnection();
		}
	}*/
	public static void main(String[] args) {
		
		//String DB,String BaseDBserver,String ActualDBserver,String BaseEstimate,String ActualEstimate
		Negotiation.VerifyHeader("HubLabels","iquotedbdbqry", "iquoteuiautodb71", "1076", "1181");
	}
	}
