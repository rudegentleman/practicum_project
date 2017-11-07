package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.MathClass;

public class DatabaseConnection {
	private static final long serialVersionUID = 1L;
	private Connection myConnection=null;
	private PreparedStatement statement;

	/**
	 * DatabaseConnection is the default constructor that connects to the
	 * database
	 */
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String db = "jdbc:mysql://35.157.11.221:3306/practicum";
			myConnection = DriverManager.getConnection(db, "dniwemugisha", "Student@123");
			System.out.println(myConnection==null);
			//myConnection = DriverManager.getConnection(db, "root", "");
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
		ArrayList <String> sample= new ArrayList <String> ();

		String meanColumn= "mean_"+ElementName;

		// this will be used in the next sprint to read the 
		String bestMean = null;
		boolean ok =false;
		java.sql.Date date=null;
		String query="SELECT "+ ElementName+ ", "+meanColumn +" , `Best record` FROM `practicum`.`my_table`";


		try {
			statement = myConnection.prepareStatement(query);
			ResultSet mySet =statement.executeQuery();
			int i=0;
			while(mySet.next()){
				date=mySet.getDate("Best record");
				ok=true;
				if(mySet.getString(ElementName)!=null){
					readings[0] = mySet.getString(ElementName);
					readings[1]= mySet.getString(meanColumn);
					sample.add(mySet.getString(ElementName));

				}
				//System.out.println(Arrays.toString(readings));
				if(ok)
				{
					
					System.out.println("sample " +sample);
					
					
					
                  
					
				}
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


	//A function to inspect an element
	public ArrayList<ArrayList> inspect(String ElementName){
		//an array to hold the two readings
		ArrayList <String> currReadings = new ArrayList<String>();
		ArrayList <String> means = new ArrayList<String>();

		ArrayList<ArrayList> readings= new ArrayList<ArrayList>();

		String meanColumn= "mean_"+ElementName;

		// this will be used in the next sprint to read the 
		String bestMean = null;
		String query="SELECT "+ ElementName+ ", "+meanColumn + ", `Best record` "+ " FROM `practicum`.`my_table`";
		java.sql.Date bestDate= null;

		try {
			statement = myConnection.prepareStatement(query);
			ResultSet mySet =statement.executeQuery();
			int i=0;
			while(mySet.next()){
				currReadings.add( mySet.getString(ElementName));
				means.add(mySet.getString(meanColumn));
				bestDate = mySet.getDate("Best record");
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// closing the connection to the server
		
		
		readings.add(currReadings);
		readings.add(means);

		/**
		 *
		 * retrieving the best record
		 * */

		ArrayList<String>  bestMean_ = new ArrayList<String>();
		query = "Select * from `practicum`.`my_table` WHERE Date = ?  ";
		try {
			statement = myConnection.prepareStatement(query);
			statement.setDate(1,bestDate);
			ResultSet mySet = statement.executeQuery();

			while(mySet.next()){ // adding the best record into the arrayList
				if(!bestMean_.isEmpty())
                bestMean_.remove(0); // remove to add the current reading
				bestMean_.add(0,mySet.getString(meanColumn));// add a new 
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		try {// closing the connection
			myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		

	}

		// adding the best mean to the generic arrayList
		readings.add(bestMean_);

		return readings;

	}



	/**
	 * 
	 * function to record the best record
	 * 
	 * */

	public boolean setBestRecord(String date){

		//String qry ="UPDATE  `practicum`.`my_table` SET `Best record`= NULL";
		System.out.println(date);
		DateFormat df = new SimpleDateFormat("yyyy-dd-MM"); 
		java.sql.Date newDate = null;

		Date parsed;
		try {
			parsed = df.parse(date);
			newDate = new java.sql.Date(parsed.getTime());
			System.out.println(newDate);

		} catch (java.text.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		//String qry ="INSERT INTO `practicum`.`my_table` (`Best record`) VALUES (?) WHERE `oxygen`= ?";
		String qry ="UPDATE  `practicum`.`my_table` SET `Best record`= ?";

		try {
			statement = myConnection.prepareStatement(qry);
			statement.setDate(1,newDate);
			//statement.setString(2,"15");
			statement.executeUpdate();
			//System.out.println("done");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return false;
	}
	
	
	/**
	 * This reads the data from transient table to final table
	 * 
	 * */
	
	public java.sql.Date parseData(java.sql.Date date){
		
		java.sql.Date date2 = null;
		ResultSet set;
		java.sql.Date  bestDate= null;
		ArrayList <String> temp = new ArrayList<String>();
		ArrayList <String> hum = new ArrayList<String>();

		String qry="Select * From practicum.api_data where Date > ?";
		
		try {
			statement = myConnection.prepareStatement(qry);
			statement.setDate(1, date);
			set=statement.executeQuery();
			
			int i=0;
			System.out.println(date);
			
			while(set.next()){
				i++;
				System.out.println("am here");
				
			date2= set.getDate("Date");
			bestDate= set.getDate("Best record");
			temp.add(set.getString("temperature"));
			hum.add(set.getString("humidity"));	
			
			
			}			
			
			if(i!=0)
			{
				
				double humidity= MathClass.mean(hum);
				double temperature= MathClass.mean(temp);
				  String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
				  String str;
				    
				    java.sql.Date currDate =java.sql.Date.valueOf(timeStamp);
				
				qry= "INSERT INTO `practicum`.`my_table` (`Date`,`temperature`,`mean_temperature`,`humidity`,`mean_humidity`,`Best record`) VALUES (?,?,?,?,?,?);";
				statement= myConnection.prepareStatement(qry);
				statement.setString(4, ""+humidity);
				statement.setString(2, ""+temperature);
				statement.setDate(1,currDate);
				statement.setString(3, ""+temperature);
				statement.setString(5,""+humidity);
				statement.setDate(6, bestDate);
				
				statement.executeUpdate();

				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date2;
	}
	
	
	public String bestMean(String elementName){
		String bestMean_ = null;
		java.sql.Date  bestDate =null;
		
		
		
		// selecting the best date
		String qry = "Select `Best record` from `practicum`.`my_table`   ";
		  
		try {
			statement = myConnection.prepareStatement(qry);
			ResultSet set= statement.executeQuery();
			if(set.next())
				bestDate= set.getDate("Best record");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// best date obtained

		
		// getting the best mean from the best date
		qry = "Select * from `practicum`.`my_table` WHERE Date = ?  ";
		
		try {
			statement = myConnection.prepareStatement(qry);
			statement.setDate(1,bestDate);
			ResultSet mySet = statement.executeQuery();

			while(mySet.next()){ // adding the best record into the arrayList
				bestMean_=mySet.getString(elementName);// add a new 
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		try {// closing the connection
			myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		

	}

		// adding the best mean to the generic arrayList

		
		
		return bestMean_;
		
	}
	
}

