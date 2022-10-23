package hsu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class mysqiupdate {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String hostString ="127.0.0.1:";
		String portnumberString ="3306";
		String  mysqlString =  "jdbc:mysql://";
		String databasenameString = "/girl_front_line";
		String usernameString = " ";
		String passwordString = " ";
		Connection conn = DriverManager.getConnection(mysqlString+hostString+portnumberString+databasenameString,usernameString,passwordString );
		Statement stat = conn.createStatement();
		stat.executeUpdate("Drop table detail");

	}

}
