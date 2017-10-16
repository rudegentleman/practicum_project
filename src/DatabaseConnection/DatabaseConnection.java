package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DatabaseConnection {
	private static final long serialVersionUID = 1L;
	private Connection myConnection;
	private PreparedStatement statement;

	/**
	 * DatabaseConnection is the default constructor that connects to the
	 * database
	 */
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String db = "jdbc:mysql://localhost:3306/practicum";
			//myConnection = DriverManager.getConnection(db, "dniwemug", "tartans");
		    myConnection = DriverManager.getConnection(db, "root", "");
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		
	}
	
	
	
	// this function will insert credentials a registered
		public void register(String name, String secName,String username,String pw){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String query ="INSERT INTO `practicum`.`credentials` (`name`,`secondName`,`username`,`Hash`) VALUES (?,?,?,?);";
			
			try {
				statement = myConnection.prepareStatement(query);
							statement.setString(1,name);

							statement.setString(2,secName);

				statement.setString(3,username);
				statement.setString(4,pw);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try{
					
					myConnection.close();
				}catch(SQLException e){
					
					e.getMessage();
				}
				
			}
			
		}

		public boolean checkPrivilege(String accesCode){
			
			boolean isAllowed = false;
			ResultSet mySet;
			String query = "SELECT * FROM `practicum`.`credentials` WHERE `accessCode`=?;";
			
			try {
				statement = myConnection.prepareStatement(query);
				statement.setString(1, accesCode);
				mySet =statement.executeQuery();
				while(mySet.next()){
					isAllowed|=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			
				try {
					myConnection.close();
				} catch (SQLException e) {
					
					System.out.println("connection to dataBase issue");
				}
			
		}
			
			return isAllowed;
		}
		
		
		// the following function will check the login credentials of the user
		
		public boolean login(String username,String hash){
			String hash_ = null;
			ResultSet mySet;
			
			String query ="SELECT * FROM `practicum`.`credentials` WHERE `username`= ?;";		
	        
			
			try {
				statement = myConnection.prepareStatement(query);
				statement.setString(1, username);
				mySet =statement.executeQuery();
				
				while(mySet.next()){
					hash_ = mySet.getString("Hash");
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// closing the connection to the server
			finally{
				
				try {
					myConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			// checking if the credentials matches
			return(hash.equals(hash_));
			
		}
		
		
		// a function reads the last records of ta component and its mean
		
		
		public String[] read(String ElementName){
			//an array to hold the two readings
			String readings[] = new String[2];
			
			String meanColumn= "mean_"+ElementName;
			
			// this will be used in the next sprint to read the 
			String bestMean = null;
			String query="SELECT "+ ElementName+ ", "+meanColumn +" FROM `practicum`.`my_table`";
			
			
			try {
				statement = myConnection.prepareStatement(query);
				ResultSet mySet =statement.executeQuery();
				while(mySet.next()){
					 readings[0] = mySet.getString(ElementName);
					 readings[1]= mySet.getString(meanColumn);
					 //System.out.println(Arrays.toString(readings));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// closing the connection to the server
			finally{
				
				try {
					myConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
				
		return readings;
			
		}
		
		
		
		
		
}
