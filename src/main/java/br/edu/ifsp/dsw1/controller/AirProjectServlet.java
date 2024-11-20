package br.edu.ifsp.dsw1.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.TotemArriving;
import br.edu.ifsp.dsw1.model.entity.TotemBoarding;
import br.edu.ifsp.dsw1.model.entity.TotemModel;
import br.edu.ifsp.dsw1.model.entity.TotemTookOff;
import br.edu.ifsp.dsw1.model.entity.TotemTakingOff;
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
	private TotemModel totemArriving;
	private TotemModel totemBoarding;
	private TotemModel totemTookOff;
	private TotemModel totemTakingOff;
	
	public void init() throws ServletException {
		super.init();
		datasource = new FlightDataCollection();
		
		totemArriving = TotemArriving.getInstance();
		totemBoarding = TotemBoarding.getInstance();
		totemTakingOff = TotemTakingOff.getInstance();
		totemTookOff = TotemTookOff.getInstance();
		
		datasource.register(totemArriving);
		datasource.register(totemBoarding);
		datasource.register(totemTakingOff);
		datasource.register(totemTookOff);
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
					String page = request.getParameter("page");
					
					if("pageAdmin".equals(page))
					{
						view = handlePageAdmin(request, response);
					}
					else
					{
						if("pageShowFlightsArriving".equals(page))
						{
							view = handlePageFlightsArriving(request, response);
						}
						else
						{
							if("pageShowFlightsBoarding".equals(page))
							{
								view = handlePageFlightsBoarding(request, response);
							}
							else
							{
								if("pageShowFlightsTakingOff".equals(page))
								{
									view = handlePageFlightsTakingOff(request, response);
								}
								else
								{
									if("pageShowFlightsTookOff".equals(page))
									{
										view = handlePageFlightsTookOff(request, response);
									}
									else
									{
										if("loginAdmin".equals(page))
										{
											view = handleLoginAdmin(request, response);
										}
										else
										{
											view = "index.jsp";
										}
									}
								}
							}
						}
					}
					
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
		
		if(validateLogin(user, password)){
			var session = request.getSession();
			session.setAttribute("user", user);
			
			return "pageAdmin.jsp";
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
				
				return "airport.do?action=redirectTo&page=pageAdmin";
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
		request.setAttribute("loadData", true);
		
		return "pageAdmin.jsp";
	}
	
	private String handleLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		return "loginAdmin.jsp";
	}
	
	private String handleUpdateStateFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Long flightNumber = Long.parseLong(request.getParameter("flightNumberUpdate"));
		
		datasource.updateFlight(flightNumber);
		
		return "airport.do?action=redirectTo&page=pageAdmin";
	}
	
	private String handlePageFlightsArriving(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("listTotemFlightsArriving", totemArriving.getFlights());
		request.setAttribute("loadData", true);
		return "pageShowFlightsArriving.jsp";
	}
	
	private String handlePageFlightsBoarding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listTotemFlightsBoarding", totemBoarding.getFlights());
		request.setAttribute("loadData", true);
		return "pageShowFlightsBoarding.jsp";
	}
	
	private String handlePageFlightsTakingOff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listTotemFlightsTakingOff", totemTakingOff.getFlights());
		request.setAttribute("loadData", true);
		return "pageShowFlightsTakingOff.jsp";
	}
	
	private String handlePageFlightsTookOff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listTotemFlightsTookOff", totemTookOff.getFlights());
		request.setAttribute("loadData", true);
		return "pageShowFlightsTookOff.jsp";
	}
	
}
