import java.sql.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class light_project {
 public static void main(String[] args){
	 
 }
 public void control_algo() throws Exception {
	 
	 	int count = 0;
	 	int learned_intensity = 0;
	 	String dateFormat=null;  
	 	String weekday = null;
	 	String tt = null;
	 	//count=input port2;
	 	//  input_intensity=from port
	 	if(count == 0){
	 		//output port = 0;
	 		//wait_algo(input_intensity);
	 	}
		 
	 	dateFormat="EEE";
	 	Calendar cal = Calendar.getInstance();
	 	SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat);
	 	weekday = sdf1.format(cal.getTime());
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
		
		dateFormat = "K";
		SimpleDateFormat sdf2 = new SimpleDateFormat(dateFormat);
		tt = sdf2.format(cal.getTime());
		query = "SELECT lv" + tt + "from ml_table where weekday =" + weekday + ";";
		rs = st.executeQuery(query);
		while (rs.next())
		{
			learned_intensity =  Integer.parseInt(rs.getString(1));
			//System.out.println(rs.getString(1));
		}
		// output=learned_intensity-input;
		}
		catch (SQLException e1){
			System.err.println(e1);
		}
		catch (ClassNotFoundException e2){
			System.err.println(e2);
		}
 }
 public void wait_algo(int old_intensity) throws Exception{
	 int count =0;
	 int new_intensity=0;
	 while(true){
		 
	 //new_intensity from port;
	 // count=input from port;
	 if (count!=0){
		 if (( new_intensity  >=  old_intensity * 110/100) || new_intensity  <= old_intensity * 90/100){
		 control_algo();
		 }
	 }
	 else{
		 try
	      {
	        Thread.sleep(4000);        
	             
	      }
		 catch (InterruptedException ie)
	            {
	        continue;
	            }
	        }
	 }
		 
 }

}
