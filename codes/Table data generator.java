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
	String arr[] = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
	String dateFormat = "EEE"; 
	Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
 	String weekday = sdf.format(cal.getTime());
	String usr = "root";
	String pass = "";
	int index = 0;
	for(int i=0; i<7 ; i++){
	if (weekday.matches(arr[1])){
		index = i;
	}
	}
	
	try{
	Class.forName("com.mysql.jdbc.Driver");
	db = DriverManager.getConnection(url,usr,pass);
	st = db.createStatement();
	

	for (int j=1; j<=120 ; j++){
		query = "insert into light_table (dt,weekday,fd1,fd2,fd3,fd4,fd5,fd6,fd7,fd8,fd9,fd10,fd11,fd12,fd13,fd14,fd15,fd16,fd17,fd18,fd19,fd20,fd21,fd22,fd23,fd24) values((DATE_SUB(CURRENT_DATE(), INTERVAL 120 - " + j + " DAY))," + " \" " + weekday + " \" "  + ",1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,4,5,6,7,8,9);";
		//System.out.print("insert into light_table values(" + j + ",(DATE_ADD((DATE_SUB(CURRENT_DATE(), INTERVAL 120 - " + j + " DAY)),INTERVAL "+ j + " DAY)),CURRENT_TIME()," + " \" " + weekday + " \" "  + ",1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,4,5,6,7,8,9);"); 
			int n = st.executeUpdate(query); 
		for (int i = 1; i<=24 ; i++){
			int gen = r.nextInt(21);		
			query = "update light_table set fd" + i + " = " + gen + " where dt = (DATE_SUB(CURRENT_DATE(), INTERVAL 120 - " + j +" DAY));";
			//System.out.println(query);
			n = st.executeUpdate(query);
	}
	}
	
	for (int i = 0; i<120 ; i++){
			query = "update light_table set weekday = " + " \" " + arr[index % 7] + " \" " + " where dt = (DATE_SUB(CURRENT_DATE(), INTERVAL 120 - " + i +" DAY));";
			index++;
			//System.out.println(query);
			int n = st.executeUpdate(query);
	}
	
	
	
	query = "select * from light_table;";
	rs = st.executeQuery(query);
	while (rs.next())
	{
		System.out.println(rs.getString(1)+rs.getDate(2)+ rs.getString(3) + rs.getString(4) + rs.getString(5));
		for (int i = 6; i<=27 ; i++){
			System.out.print(" " + rs.getString(i));}
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