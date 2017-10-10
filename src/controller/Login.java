package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseConnection.DatabaseConnection;
import model.PasswordHash;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	
	public Login(){
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// Retrieve username and password from the login request
		
		boolean pwMatches =false;
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		// Checking if username and password aren't null
		if (!username.equalsIgnoreCase("") || !password.equalsIgnoreCase("")) {
			DatabaseConnection db = new DatabaseConnection();
			//PasswordHash pH = new PasswordHash();
			String hashedPassword = PasswordHash.getPasswordHash(password);
			
			pwMatches = db.login(username, hashedPassword);
			//System.out.println(hashedPassword);
			System.out.println(pwMatches);
			
			// Check user credentials entered
			if(pwMatches){
				response.sendRedirect("/practicum_project/admin_home.jsp");
			}
			else{
				response.sendRedirect("/practicum_project/index.jsp");
			}
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
}
