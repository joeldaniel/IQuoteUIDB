package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	Connection con;
	
	public Connection Createconnection(String url, String user, String password) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(url +" : "+ user +" : "+ password);
		System.out.println("Connected to sql db");
		return con;	
	}
	
	
	public ResultSet RunQuery(String query) throws ClassNotFoundException, SQLException {
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		return rs;
		
	}
	
	public void Closeconnection() throws SQLException {
		con.close();
		System.out.println("Disconnected to sql db");
	}
	

}
