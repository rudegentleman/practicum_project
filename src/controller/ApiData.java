package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
		
				   String str="2017-10-03";  

		
//		    String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

   	    java.sql.Date date=Date.valueOf(str); //converting string into sql date
   	    java.sql.Date date2 ;
		 
		    
		    
		    
		    
		   while(true){
		    	
		    	try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		     date2= new DatabaseConnection().parseData(date); 
		     if(date2!=null)
		    	 date=date2;
		    	
		    		    	
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
