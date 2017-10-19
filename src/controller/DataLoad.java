package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.math3.stat.inference.TTest;


import DatabaseConnection.DatabaseConnection;
import model.*;

/**
 * Servlet implementation class DataLoad
 */
@WebServlet("/DataLoad")
public class DataLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataLoad() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Not Allowed To vew This page").append(request.getContextPath());
		// getting a session from login page
		HttpSession session=request.getSession();
		
		
		// if the visitor is not from the Login Page or the session expired. redirect to Login page
        if (session.getAttribute("permission")==null)
        {
        	  String site = new String("index.jsp");

              response.setStatus(response.SC_MOVED_TEMPORARILY);
              response.setHeader("Location", site);
    		
        	
        }
        

        // an arraylist to store all elements read
		ArrayList<Element> elements= new ArrayList <Element>();
		String readings[] = new String[2];
		String elementsNames [] ={"oxygen","carbon","hydrogen","temperature","humidity"};
		DatabaseConnection db;
		int i=0;
		
		// readings from data base and creating elements for a display
		for(String elName: elementsNames){
			db= new DatabaseConnection();
              //System.out.println(elementsNames[i]);
			readings=db.read(elementsNames[i]);
			elements.add(new Element(elementsNames[i],Double.valueOf(readings[0]),Double.valueOf(readings[1]),0, new double[]{0}));
			i++;
		}
		/**
		 * Data analytics: this part of the codes will alerts the user on the screen based on the findings
		 * a z-score is used to check whether the mean sofar is acceptable or not based on the sample size
		 * and the best mean.
		 * 
		 * */
		// call data analytics modelue here to do z-score or t,test
		
		
		// set an attributes of the readings into the session
		session.setAttribute("elements", elements);
		
		// dispatching the readings to the DataPortal page to be displayed
		RequestDispatcher rd=request.getRequestDispatcher("/Table.jsp"); 
		rd.forward(request, response);

		//response.getWriter().print(elements.get(2).getElementName());
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// redirect to doPost
		doGet(request, response);
	}

}
