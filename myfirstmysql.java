package hsu;

// ref:https://blog.judysocute.com/2020/06/08/%E7%AC%AC-18-%E9%80%B1-java-%E9%80%A3%E6%8E%A5-mysql/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class myfirstmysql {

	public static void main(String[] args) throws Exception {
		// load driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		String hostString ="127.0.0.1:";
		String portnumberString ="3306";
		String  mysqlString =  "jdbc:mysql://";
		String databasenameString = "/databasename";
		String usernameString = "";
		String passwordString = "";
		// DB location => jdbc:mysql://hostname:port/databasename
		// jdbc:mysql://localhost:3306/mydb == jdbc:mysql://127.0.0.1:3306/mydb
		Connection conn = DriverManager.getConnection(mysqlString+hostString+portnumberString+databasenameString,usernameString,passwordString );
		Statement stat = conn.createStatement();
		
//		ResultSet resultSet = stat.executeQuery("Select * From number_and_lv");	
		ResultSet resultSet = stat.executeQuery("show full tables");
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		System.out.println("number of column = " + resultSetMetaData.getColumnCount());
		while (resultSet.next()) {
			System.out.print("row="+resultSet.getRow()+",");
			for (int i=1;i<=resultSetMetaData.getColumnCount();i++) {
				System.out.print("column label="+resultSetMetaData.getColumnLabel(i)+",");
//				System.out.println(i+","+"ColumnLabel="+resultSetMetaData.getColumnLabel(i));
				System.out.print(resultSet.getString(i)+",");
			}
			System.out.println();
		}
	}
}
