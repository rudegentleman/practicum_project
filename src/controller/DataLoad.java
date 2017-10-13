package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
        HttpSession session = request.getSession();
		ArrayList<Element> elements= new ArrayList <Element>();
		Element element;
		String readings[] = new String[2];
		String elementsNames [] ={"oxygen","carbon","hydrogen","temperature","humidity"};
		DatabaseConnection db;
		int i=0;
		for(String elName: elementsNames){
			db= new DatabaseConnection();
              System.out.println(elementsNames[i]);
			readings=db.read(elementsNames[i]);
			elements.add(new Element("oxygen",Double.valueOf(readings[0]),Double.valueOf(readings[1]),0));
			i++;
		}
		
		session.setAttribute("elements", elements);
		
		RequestDispatcher rd=request.getRequestDispatcher("practicum_project/DataPortal"); 
		rd.forward(request, response);

		//response.getWriter().print(elements.get(2).getElementName());
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
