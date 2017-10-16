package DatabaseConnection;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PasswordHash;

/**
 * Servlet implementation class DbConnection
 */
@WebServlet("/Register")
public class DbConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbConnection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.print(
				 "<html>"+
				 "<head>"
				 +"<title> Registration</title>"
				 +"</head>"
				 +"<body>"
				 +"<h2>Rgegister here</h2>"
				 
						+ "<form action = \"Register\" method=\"post\">"
						+ "<br>Name <input type=\"text\" name=\"name\" value=\"\"> "
						+ "<br>Family Name <input type=\"text\" name=\"name2\" value=\"\"> "
						+ "<br>User name <input type=\"text\" name=\"name_\" value=\"\"> "
						+ "<br>Password <input type=\"password\" name=\"password\">"
						+ "<input type=\"submit\" value=\"sign up\">"
						+ "</body></form>"
						
						+"</html>"
);
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name= request.getParameter("name");
		String name2= request.getParameter("name2");
		String username= request.getParameter("name_");
		String pw= request.getParameter("password");
		String hash = PasswordHash.getPasswordHash(pw);

		DatabaseConnection bd = new DatabaseConnection();
		System.out.println(name+name2+username+pw);
       bd.register(name, name2, username, hash);
	}

}
