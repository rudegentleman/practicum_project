package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

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
	 * @throws ServletException,IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	public void sendredirect_(boolean pwMatches,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
		
		
		
		if(pwMatches){
			System.out.println("ok");
			response.sendRedirect("/practicum_project/DataLoad");
		}
		else{
			response.sendRedirect("/practicum_project/index.jsp");
			return;
		}
	};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// Retrieve username and password from the login request
		HttpSession session= request.getSession(true);
		session.setAttribute("permission", true);
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
			// timer
			Date date = new Date();
			int start = date.getMinutes();
			
		//	while(true){
			
			//if(new Date().getMinutes()>=start){
//			if(pwMatches){
//				System.out.println("ok");
//				response.sendRedirect("/practicum_project/DataLoad");
//			}
//			else{
//				response.sendRedirect("/practicum_project/index.jsp");
//				return;
//			}
			
			sendredirect_(pwMatches,request,response);
			//System.out.println("here I am ");
			//start+=1;
		//	}
		//}
		}
		
		
//		Thread aThread= new Thread(){
//			
//			public void run(){
//				
//				int i=0;
//				while(true){
//				
//				System.out.println(this.currentThread().getName());
//				if(i++>100)
//					try {
//						sendredirect_(true, request, response);
//					} catch (ServletException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				
//				}
//				
//			}
//			
//		};
//		
//		aThread.start();
//		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
}
