package application;
import java.sql.*;

public class databaseconnection {
	public Connection databaselink;
	public Connection getConncetion(){
		String dbName="employee";
		String dbUser="root";
		String dbPassword="Thomas@004";
		String url="jdbc:mysql://localhost/"+dbName;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaselink = DriverManager.getConnection(url,dbUser,dbPassword);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return databaselink;
	}
}
