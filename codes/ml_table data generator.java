import java.sql.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Random;


public class testmodules {
public static void main (String[] args) throws Exception{
	Connection db;
	Statement st;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3306/light";
	String query = null;
	Random r = new Random();
	String usr = "root";
	String pass = "";
	int sum_feedback=0;
	
	String dateFormat = "H";
 	String dateFormat1= "EEEE";
	Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat1);
 	int hour = Integer.parseInt(sdf.format(cal.getTime()));
 	String day = sdf1.format(cal.getTime());
 	
	
 	try{
	Class.forName("com.mysql.jdbc.Driver");
	db = DriverManager.getConnection(url,usr,pass);
	st = db.createStatement();
	
	for(int k=1;k<=15;k++){
		int x=0;
		query = "select fd" + hour + " from light_table where dt = DATE_SUB(CURRENT_DATE(), INTERVAL 7 * " + k + " DAY);";
	   
		rs = st.executeQuery(query);
		while (rs.next())
		{
			x=rs.getInt(1);
			System.out.print(x);
			sum_feedback += x;	
			
		}
	}
	
	day = day.toLowerCase();
	for (int i = 0; i<7 ; i++){
		
			query = "update ml_table set lv" + hour + " = " + (sum_feedback/15) + " where weekday = " + " \"" + day + "\" " + ";";
			//index++;
			System.out.println(query);
			int n = st.executeUpdate(query);
	}
	
	
	
	query = "select * from ml_table;";
	rs = st.executeQuery(query);
	while (rs.next())
	{
		System.out.println(rs.getString(1));
		for (int i = 2; i<=24 ; i++){
			System.out.print(" " + rs.getInt(i));}
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