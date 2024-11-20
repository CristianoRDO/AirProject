package br.edu.ifsp.dsw1.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
			if("logout".equals(action))
			{
				view = handleLogout(request, response);
			}
			else
			{
				if("redirectTo".equals(action))
				{
					view = handlePageAdmin(request, response);
				}
				else
				{
					if("registerFlight".equals(action)) 
					{
						view = handleRegisterFlight(request, response);
					}
					else
					{
						if("updateFlight".equals(action))
						{
							view = handleUpdateStateFlight(request, response);
						}
						else
						{
							view = "index.jsp";
						}
					}
				}
				
			}
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
	
	private String handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		var session = request.getSession(false);
		session.invalidate();
		return "index.jsp";
	}
	
	private String handleRegisterFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		var flightNumber = Long.parseLong(request.getParameter("flightNumber"));
		var flightCompany = request.getParameter("flightCompany");
		var flightTime = request.getParameter("flightTime");
		
		if(!findFlightByNumber(flightNumber))
		{
			if(!isFutureArrivalTime(flightTime))
			{
				FlightData flight = new FlightData(flightNumber, flightCompany, flightTime);
				flight.setState(Arriving.getIntance());
				
				datasource.insertFlight(flight);
				request.setAttribute("success", "Voo Cadastrado Com Sucesso!");
				
				return "airport.do?action=redirectTo";
			}
			else
			{
				request.setAttribute("error", "Data Inválida.");
			}
		}
		else
		{
			request.setAttribute("error", "Voo Já Cadastrado.");
		}
		
		return "formFlight.jsp";
	}
	
	private boolean findFlightByNumber(Long flightNumber) {
	    return datasource.getAllFligthts().stream()
	            .anyMatch(f -> f.getFlightNumber().equals(flightNumber));
	}
		
	private boolean isFutureArrivalTime(String flightTime) 
	{
		var time = LocalDateTime.parse(flightTime);
		return time.isBefore(LocalDateTime.now());
	}
	
	private String handlePageAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("listFlights", datasource.getAllFligthts());
		
		return "pageAdmin.jsp";
	}
	
	private String handleUpdateStateFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Long flightNumber = Long.parseLong(request.getParameter("flightNumberUpdate"));
		
		datasource.updateFlight(flightNumber);
		
		return "airport.do?action=redirectTo";
	}
	
}
