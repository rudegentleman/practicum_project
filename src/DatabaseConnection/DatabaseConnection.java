package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.MathClass;

public class DatabaseConnection {
	private Connection myConnection=null;
	private PreparedStatement statement;
	java.sql.Date bDate =null;


	/**
	 * DatabaseConnection is the default constructor that connects to the
	 * database
	 */
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String db = "jdbc:mysql://localhost:3306/practicum";
			myConnection = DriverManager.getConnection(db, "root", "password@123");
			//			String db = "jdbc:mysql://localhost:3306/inmarsat_rmt";
			//	myConnection = DriverManager.getConnection(db, "dniwemugisha", "Student@123");		

			//myConnection = DriverManager.getConnection(db, "root", "");
			System.out.println("done");
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}


	}



	// this function will insert credentials of a user
	public void register(String name, String secName,String username,String pw){
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
		//		finally{
		//			try{
		//
		//				myConnection.close();
		//			}catch(SQLException e){
		//
		//				e.getMessage();
		//			}
		//
		//		}

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

			try { // closing the connection
				if(myConnection!=null)
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


	public String[] read(String ElementName, boolean closeConnection){
//		//an array to hold the two readings
//		String readings[] = {"0","0"};
//		//		ArrayList <String> sample= new ArrayList <String> ();
//
//		String meanColumn= "mean_"+ElementName;
//
//		boolean ok =false;
//		String query="SELECT "+ ElementName+ ", "+meanColumn +" , `Best record` FROM `practicum`.`my_table`";// ORDER BY temperature DESC LIMIT 1";
//
//
//		try {
//			statement = myConnection.prepareStatement(query);
//			ResultSet mySet =statement.executeQuery();
//			while(mySet.next()){
//				ok=true;
//				if(mySet.getString(ElementName)!=null){
//					readings[0] = mySet.getString(ElementName);
//					readings[1]= mySet.getString(meanColumn);
//					System.out.println("I read in"+meanColumn +" "+readings[1]);
//					//sample.add(mySet.getString(ElementName));
//
//				}
//
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// closing the connection to the server
//		finally{
//
//			try {
//				if(closeConnection)
//					myConnection.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//
//
//		return readings;
		//an array to hold the two readings
				String readings[] = {"0","0"};
				//		ArrayList <String> sample= new ArrayList <String> ();

				String meanColumn= "mean_"+ElementName;

				boolean ok =false;
				String query="SELECT "+ ElementName+ ", "+meanColumn +" , `Best record` FROM `practicum`.`my_table`";// ORDER BY temperature DESC LIMIT 1";


				try {
					statement = myConnection.prepareStatement(query);
					ResultSet mySet =statement.executeQuery();
					while(mySet.next()){
						ok=true;
						if(mySet.getDate("Best record")!=null)
							bDate =mySet.getDate("Best record");
						
						if(mySet.getString(ElementName)!=null){
							readings[0] = mySet.getString(ElementName);
							readings[1]= mySet.getString(meanColumn);

						}

					}
					
					if(bDate!=null){
						
						String date = bDate.toString();
						setBestRecord(date);
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closing the connection to the server
				finally{

					try {
						if(closeConnection)
							myConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}


				return readings;

	}


	//A function to inspect an element
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> inspect(String elementName){
		//an array to hold the two readings
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
		java.sql.Date date = java.sql.Date.valueOf(timeStamp);
		System.out.println("date "+ date);
		ArrayList <String> currReadings = new ArrayList<String>();
		ArrayList <String> means = new ArrayList<String>();

		ArrayList<ArrayList> readings= new ArrayList<ArrayList>();

		String meanColumn= "mean_"+elementName;

		String query="SELECT "+ elementName+ ", "+meanColumn + ", `Best record` "+ " FROM `practicum`.`my_table` WHERE Date = ?"; // where date is current date
		java.sql.Date bestDate= null;

		try {
			statement = myConnection.prepareStatement(query);
			statement.setDate(1, date);
			ResultSet mySet =statement.executeQuery();
			while(mySet.next()){
				currReadings.add( mySet.getString(elementName));
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
		query = "Select * from `practicum`.`my_table` WHERE Date = ? ";
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

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		java.sql.Date newDate = null;

		Date parsed;
		try {
			parsed = df.parse(date);
			newDate = new java.sql.Date(parsed.getTime());

		} catch (java.text.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		//String qry ="INSERT INTO `practicum`.`my_table` (`Best record`) VALUES (?) WHERE `oxygen`= ?";
		String qry ="UPDATE  `practicum`.`my_table` SET `Best record`= ?";

		try {
			statement = myConnection.prepareStatement(qry);
			statement.setDate(1,newDate);
			System.out.println("date ......."+ newDate.toString());
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

	public Timestamp parseData(Timestamp date){

		Timestamp date2 = date;
		ResultSet set;
		//java.sql.Date  bestDate= null;
		ArrayList <String> temp = new ArrayList<String>();
		ArrayList <String> hum = new ArrayList<String>();

		String qry="Select * From practicum.api_data where time_stamp > ?";

		try {
			statement = myConnection.prepareStatement(qry);
			statement.setTimestamp(1, date);
			set=statement.executeQuery();

			int i=0;
			System.out.println("my date"+date);
			while(set.next()){
				i++;

				date2= set.getTimestamp("time_stamp");
				//bestDate= set.getDate("Best record");
				temp.add(set.getString("temperature"));
				hum.add(set.getString("humidity"));	
				System.out.println("I am in");
			}			






			if(i!=0)
			{
                
				int numofReadings = count();
				// humidity
				String humidity;//= MathClass.mean(hum);
				humidity = hum.get(hum.size()-1);
				String lastMeanH=read("humidity",false)[1];// the current mean of humidity
				double meanHum= MathClass.dailyMean(Double.parseDouble(humidity), Double.parseDouble(lastMeanH), numofReadings);

				//temperature
				String temperature;//= MathClass.mean(temp);
				temperature = temp.get(temp.size()-1);;
				String lastMeanT=read("temperature",false)[1];// the current mean of temperature
				double meantem= MathClass.dailyMean(Double.parseDouble(temperature), Double.parseDouble(lastMeanT), numofReadings);





				String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
				java.sql.Date currDate =java.sql.Date.valueOf(timeStamp);

				qry= "INSERT INTO `practicum`.`my_table` (`Date`,`temperature`,`mean_temperature`,`humidity`,`mean_humidity`) VALUES (?,?,?,?,?);";
				statement= myConnection.prepareStatement(qry);
				statement.setString(4, ""+humidity);
				statement.setString(2, ""+temperature);
				statement.setDate(1,currDate);
				statement.setString(3, ""+meantem);

				statement.setString(5,""+meanHum);
				//				statement.setDate(6, bestDate);

				statement.executeUpdate();



			}

		} catch (SQLException e) {
			System.out.println("The reason is ================"+ e.getMessage());
		}


		if (myConnection!=null)
			try {
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		return date2;
	}

	/**
	 * 
	 * 
	 * 
	 * */
	private int count() {


		String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		// for testing purpose
		java.sql.Date date = java.sql.Date.valueOf(timeStamp);//replace t with timestamp
		int numofReadings =0;

		String query="SELECT temperature, `Best record` FROM `practicum`.`my_table` where Date=?";


		try {
			statement = myConnection.prepareStatement(query);
			statement.setDate(1, date);
			ResultSet mySet =statement.executeQuery();
			while(mySet.next()){
				System.out.println("number of reading = "+numofReadings);
				numofReadings++;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// closing the connection to the server

		System.out.println("Date  is "+ date);
		return numofReadings;
	}



	/**
	 * 
	 * 
	 * */


	public String bestMean(String elementName){
		String bestMean_ = null;
		java.sql.Date  bestDate =null;


		// selecting the best date
		String qry = "Select `Best record` from `practicum`.`my_table`";

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
				bestMean_=mySet.getString("mean_"+elementName);// add a new 
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


	// a function to return an arayList of sample for t-test
	public ArrayList <Double> testSample(String elementName){
		ArrayList<String> sample= new ArrayList<String>();

		ArrayList <Double> sample_=new ArrayList<Double>();
		ResultSet set;
		Date currDate = new Date();	
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(currDate);
		// for testing purpose
		java.sql.Date date = java.sql.Date.valueOf(timeStamp);//replace t with timestamp


		String qry = "Select * from `practicum`.`my_table` WHERE Date=?";
		try {
			statement = myConnection.prepareStatement(qry);
			statement.setDate(1, date);
			set= statement.executeQuery();


			while(set.next()){
				if(set.getString(elementName)!=null)
					sample.add(set.getString(elementName));				

			}

			for (String dataPoint: sample){

				sample_.add(Double.parseDouble(dataPoint));

			}




		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if(myConnection!=null)
				myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		return sample_;

	}

	/**
	 * 
	 * Here other components re supposed to be added if the project is extended
	 * */

	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> trace(String recordedeDate) {
		ArrayList<ArrayList> traceables = new ArrayList<ArrayList>();


		ArrayList<String> temperature = new ArrayList<String>();
		ArrayList<String> humidity = new ArrayList<String>();

		ResultSet set;
		String qry = "Select * from practicum.my_table where Date = ?";
		java.sql.Date date = java.sql.Date.valueOf(recordedeDate);
		System.out.println("timestamppppp" + date);

		try {
			statement = myConnection.prepareStatement(qry);
			statement.setDate(1, date);
			set = statement.executeQuery();


			while(set.next()){

				temperature.add(set.getString("temperature"));
				humidity.add(set.getString("humidity"));


			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		traceables.add(temperature);
		traceables.add(humidity);

		if(myConnection!=null)
			try {
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return traceables;
		// TODO Auto-generated method stub

	}


}

