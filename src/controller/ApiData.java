package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseConnection.DatabaseConnection;

/**
 * Servlet implementation class ApiData
 */
@WebServlet("/ApiData")
public class ApiData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApiData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// DatabaseConnection db= new DatabaseConnection();


		java.util.Date currDate = new Date();	

		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currDate);




		//		    String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

		Timestamp date=java.sql.Timestamp.valueOf(timeStamp); //converting string into sql date
		Timestamp date2 ;



      int minutes = new Date().getMinutes();
      int countable =1;
      Date date3;

		while(true){
    
			while(((date3 =new Date())).getMinutes() - minutes<countable){
				
				if(date3.getMinutes()<=60)// do something here;
				
				date3=null;
			}
			
			System.out.println("about to go");
			date2= new DatabaseConnection().parseData(date); 
				if(date2!=null)
					date=date2;
			
			minutes=new Date().getMinutes();
			}

			


		}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);


	}

}
