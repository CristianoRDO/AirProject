package br.edu.ifsp.dsw1.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.entity.TotemArriving;
import br.edu.ifsp.dsw1.model.entity.TotemBoarding;
import br.edu.ifsp.dsw1.model.entity.TotemModel;
import br.edu.ifsp.dsw1.model.entity.TotemTakingOff;
import br.edu.ifsp.dsw1.model.entity.TotemTookOff;
import br.edu.ifsp.dsw1.model.flightstates.Arriving;
import br.edu.ifsp.dsw1.utils.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/airport.do")
public class AirProjectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
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
		
		if (Constants.LOGIN.equals(action)) {
		    view = handleLogin(request, response);
		} else if (Constants.LOGOUT.equals(action)) {
		    view = handleLogout(request, response);
		} else if (Constants.REGISTER_FLIGHT.equals(action)) {
		    view = handleRegisterFlight(request, response);
		} else if (Constants.UPDATE_FLIGHT.equals(action)) {
		    view = handleUpdateStateFlight(request, response);
		} else if (Constants.REDIRECTTO.equals(action)) {
		    String page = request.getParameter(Constants.PAGE);
		    
		    if (Constants.PAGE_ADMIN.equals(page)) {
		        view = handlePageAdmin(request, response);
		    } else if (Constants.LOGIN_ADMIN.equals(page)) {
		        view = handleLoginAdmin(request, response);
		    }else if (Constants.PAGE_SHOW_FLIGHTS_ARRIVING.equals(page)) {
		        view = handlePageFlightsArriving(request, response);
		    } else if (Constants.PAGE_SHOW_FLIGHTS_BOARDING.equals(page)) {
		        view = handlePageFlightsBoarding(request, response);
		    } else if (Constants.PAGE_SHOW_FLIGHTS_TAKING_OFF.equals(page)) {
		        view = handlePageFlightsTakingOff(request, response);
		    } else if (Constants.PAGE_SHOW_FLIGHTS_TOOK_OFF.equals(page)) {
		        view = handlePageFlightsTookOff(request, response);
		    } else if (Constants.PAGE_FORM_FLIGHT.equals(page)) {
		        view = handlePageFormFlight(request, response);
		    } else {
		        view = Constants.INDEX;
		    }
		} else {
		    view = Constants.INDEX;
		}

		var dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
		
	
	private String handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var user = request.getParameter("user");
		var password = request.getParameter("password");
		
		if (user == null || user.isBlank() || password == null || password.isBlank()) {
			return setErrorAndRedirect(request, "Usuário e Senha são Obrigatórios.", Constants.LOGIN_ADMIN);
	    }
		
		if(!validateLogin(user, password)){
			return setErrorAndRedirect(request, "Dados Inválidos.", Constants.LOGIN_ADMIN);
		} 
		
		var session = request.getSession();
		session.setAttribute("user", user);
		return Constants.PAGE_ADMIN;
		
	}
	
	private boolean validateLogin(String user, String password)
	{
		if(Constants.USER.equals(user) && Constants.PASSWORD.equals(password)){
			return true;
		}
		
		return false;
	}
	
	private String handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		var session = request.getSession(false);
		session.invalidate();
		return Constants.INDEX;
	}
	
	private String handleRegisterFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		var flightNumberStr = request.getParameter("flightNumber");
		var flightCompany = request.getParameter("flightCompany");
		var flightTime = request.getParameter("flightTime");
		
		if (isBlank(flightNumberStr)) {
			return setErrorAndRedirect(request, "Número do Voo é Obrigatório.", Constants.PAGE_FORM_FLIGHT);
	    }

	    if (isBlank(flightCompany)) {
	    	return setErrorAndRedirect(request, "Companhia Aérea é Obrigatória.", Constants.PAGE_FORM_FLIGHT);
	    }

	    if (isBlank(flightTime)) {
	    	return setErrorAndRedirect(request, "Horário do Voo é Obrigatório.", Constants.PAGE_FORM_FLIGHT);
	    }

	    try {
	        Long flightNumber = Long.parseLong(flightNumberStr);

	        if (findFlightByNumber(flightNumber)) {
	        	return setErrorAndRedirect(request, "Voo já Cadastrado.", Constants.PAGE_FORM_FLIGHT);
	        }

	        if (isArrivalTimePast(flightTime)) {
	        	return setErrorAndRedirect(request, "Horário Inválido. O Voo Deve ter Horário Futuro.", Constants.PAGE_FORM_FLIGHT);
	        }

	        FlightData flight = new FlightData(flightNumber, flightCompany, flightTime);
	        flight.setState(Arriving.getIntance());
	        datasource.insertFlight(flight);
	        
	        return setSuccessAndRedirect(request, "Voo Cadastrado com Sucesso!", Constants.PAGE_FORM_FLIGHT);

	    } catch (NumberFormatException e) {
	    	return setErrorAndRedirect(request, "O Número do Voo Deve Ser um Número Válido.", Constants.PAGE_FORM_FLIGHT);
	    }

	}
	
	private boolean findFlightByNumber(Long flightNumber) {
	    return datasource.getAllFligthts().stream()
	            .anyMatch(f -> f.getFlightNumber().equals(flightNumber));
	}
		
	private boolean isArrivalTimePast(String flightTime) {
		if (flightTime == null || flightTime.isBlank()) {
	        return false; // Data inválida é tratada como não sendo futura.
	    }

	    try {
	        LocalDateTime time = LocalDateTime.parse(flightTime);
	        return time.isBefore(LocalDateTime.now());
	    } catch (DateTimeParseException e) {
	        return false; // Se não puder ser analisada, considera-se inválida.
	    }
	}
	
	private String handlePageAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listFlights", datasource.getAllFligthts());
		request.setAttribute("loadData", true);
		
		return Constants.PAGE_ADMIN;
	}
	
	private String handleLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return Constants.LOGIN_ADMIN;
	}
	
	private String handlePageFormFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    return Constants.PAGE_FORM_FLIGHT;
	}
	
	private String handleUpdateStateFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flightNumberStr = request.getParameter("flightNumberUpdate");

	    if (isBlank(flightNumberStr)) {
	    	return setErrorAndRedirect(request, "O Número do Voo é Obrigatório Para Atualização.", Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_ADMIN);
	    }

	    try {
	        Long flightNumber = Long.parseLong(flightNumberStr);
	        datasource.updateFlight(flightNumber);
	        return setSuccessAndRedirect(request, "Voo Atualizado Com Sucesso!", Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_ADMIN);
	    } catch (NumberFormatException e) {
	    	return setErrorAndRedirect(request, "O Número do Voo Deve Ser um Número Válido.", Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_ADMIN);
	    }
	}
	
	private String handlePageFlights(HttpServletRequest request, HttpServletResponse response, TotemModel totem, String attributeName, String pageName) throws ServletException, IOException {
	    request.setAttribute(attributeName, totem.getFlights());
	    request.setAttribute("loadData", true);
	    return pageName;
	}

	private String handlePageFlightsArriving(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    return handlePageFlights(request, response, totemArriving, "listTotemFlightsArriving", Constants.PAGE_SHOW_FLIGHTS_ARRIVING);
	}

	private String handlePageFlightsBoarding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    return handlePageFlights(request, response, totemBoarding, "listTotemFlightsBoarding", Constants.PAGE_SHOW_FLIGHTS_BOARDING);
	}

	private String handlePageFlightsTakingOff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    return handlePageFlights(request, response, totemTakingOff, "listTotemFlightsTakingOff", Constants.PAGE_SHOW_FLIGHTS_TAKING_OFF);
	}

	private String handlePageFlightsTookOff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    return handlePageFlights(request, response, totemTookOff, "listTotemFlightsTookOff", Constants.PAGE_SHOW_FLIGHTS_TOOK_OFF);
	}
	
	private boolean isBlank(String value) {
	    return value == null || value.isBlank();
	}
	
	private String setErrorAndRedirect(HttpServletRequest request, String errorMessage, String redirectPage) {
	    request.setAttribute("error", errorMessage);
	    return redirectPage;
	}

	private String setSuccessAndRedirect(HttpServletRequest request, String successMessage, String redirectPage) {
	    request.setAttribute("success", successMessage);
	    return redirectPage;
	}

}
