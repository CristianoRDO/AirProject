package br.edu.ifsp.dsw1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;

@WebServlet("/airport.do")
public class AirProjectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final String user = "admin";
	private final String password = "admin";
	private FlightDataCollection datasource;
	
	public void init() throws ServletException {
		super.init();
		datasource = new FlightDataCollection();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String view;
		
		if("login".equals(action))
		{
			view = handleLogin(request, response);
		}
		else
		{
			view = "index.jsp";
		}
		
		var dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}
	
	private String handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var user = request.getParameter("user");
		var password = request.getParameter("password");
		String message;
		
		if(validateLogin(user, password)){
			var session = request.getSession();
			session.setAttribute("user", user);
			
			return "index.jsp";
		} 
		else {
			request.setAttribute("message", "Dados Inválidos.");
			return "loginAdmin.jsp";
		}
	}
	
	private boolean validateLogin(String user, String password)
	{
		if(this.user.equals(user) && this.password.equals(password)){
			return true;
		}
		
		return false;
	}
	

}
