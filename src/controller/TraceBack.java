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

import DatabaseConnection.DatabaseConnection;

/**
 * Servlet implementation class TraceBack
 */
@WebServlet("/TraceBack")
public class TraceBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraceBack() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String recordedeDate = request.getParameter("tracedDate");
		 DatabaseConnection db = new DatabaseConnection();
		 ArrayList <ArrayList> data=db.trace(recordedeDate);
		 ArrayList<String> temperature = data.get(0);
		 ArrayList<String> humidity = data.get(1);
		 HttpSession session = request.getSession();
		 session.setAttribute("data", data);
	     RequestDispatcher rd=request.getRequestDispatcher("/Traced.jsp"); 
	     rd.forward(request, response);
		
		 
		 

		 
		// response.getWriter().print(temperature+"\n "+humidity);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
