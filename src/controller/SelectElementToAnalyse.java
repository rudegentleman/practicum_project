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
import model.Element;

/**
 * Servlet implementation class SelectElementToAnalyse
 */
@WebServlet("/SelectElementToAnalyse")
public class SelectElementToAnalyse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectElementToAnalyse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		if(request.getSession().getAttribute("allowed")==null){
//			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
//			//servlet2 is the url-pattern of the second servlet  
//			  
//			rd.forward(request, response);//method may be include or forward  
//		      
//			
//		}

		HttpSession session = request.getSession();
		DatabaseConnection db = new DatabaseConnection();
		
		ArrayList<ArrayList>anElemnt = db.inspect(request.getParameter("elements"));
		ArrayList <String> element = anElemnt.get(0);
		ArrayList <String> means = anElemnt.get(1);
		ArrayList <String> bestRecord = anElemnt.get(2);
		ArrayList <String> element_ = new ArrayList<>();
		ArrayList <String> means_ = new ArrayList<>();

		//ArrayList <String> element = new ArrayList<>(Arrays.asList(anElemnt));

		int i=0;
		for(String elemnt: element){
			if(null!=elemnt){
				element_.add(elemnt);
				means_.add(means.get(i++));
			}
				
		};
		
		
		System.out.println(element_);

        session.setAttribute("currMeasures",element_ );
        session.setAttribute("means",means_ );
        session.setAttribute("bestRecord", bestRecord);

        RequestDispatcher rd=request.getRequestDispatcher("/Graph.jsp"); 
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		doGet(request, response);
	}

}
