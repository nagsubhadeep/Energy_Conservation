

import java.sql.*;
public class test1 {
public static void main (String[] args) throws Exception{
	Connection db;
	Statement st;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3306/temp";
	String query = null;
	
	String usr = "root";
	String pass = "";
	try{
	Class.forName("com.mysql.jdbc.Driver");
	db = DriverManager.getConnection(url,usr,pass);
	st = db.createStatement();
	//String query = "update temp set sno = 11 where sno = 12;";
	//String query = "delete from temp where sno = 12;";
	for (int i = 1; i<20 ; i++){
				query = "insert into temp values (" + i + ");";
		int n = st.executeUpdate(query);
	}
	
	
	query = "select * from temp;";
	rs = st.executeQuery(query);
	while (rs.next())
	{
		System.out.println(rs.getString(1));
	}
	
	}
	catch (SQLException e1){
		System.err.println(e1);
	}
	catch (ClassNotFoundException e2){
		System.err.println(e2);
	}
}
}
