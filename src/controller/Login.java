package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.security.MessageDigest;
import java.util.Random;
import java.text.*;
import Database.DatabaseConnection;
import servlet.PageError;
import utils.UtilityMethods;

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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// Checking if username and password aren't null
		if (!username.equalsIgnoreCase("") || !password.equalsIgnoreCase("")) {
			DatabaseConnection db = new DatabaseConnection();
			//PasswordHash pH = new PasswordHash();
			String hashedPassword = PasswordHash.getPasswordHash(password);
			//String userData = db.getUserData(username);
			
			//db.login(username, hashedPassword);
			
			
			
			// Check user credentials entered
			if(db.login(username, hashedPassword)){
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
	}
}