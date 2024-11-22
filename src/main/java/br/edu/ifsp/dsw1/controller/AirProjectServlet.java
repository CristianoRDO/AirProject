package br.edu.ifsp.dsw1.controller;

import java.io.IOException;
import java.time.LocalTime;
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

/*
 * O AirProjectServlet serve como controlador que gerencia a lógica principal de interação com o sistema. 
 * Ele faz isso interpretando a ação solicitada pelo usuário e executando a ação apropriada, 
 * redirecionando para as páginas corretas ou retornando uma resposta, dependendo da requisição do usuário. 
 * No caso deste sistema, a principal função do servlet é gerenciar o cadastro, atualização e exibição de voos, 
 * autenticação de usuários (login/logout), e o redirecionamento para páginas específicas do sistema.
 * 
 * */

@WebServlet("/airport.do")
public class AirProjectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private FlightDataCollection datasource;
	private TotemModel totemArriving;
	private TotemModel totemBoarding;
	private TotemModel totemTookOff;
	private TotemModel totemTakingOff;
	
	// Método de inicialização do servlet, chamado quando o servlet é carregado pela primeira vez.
	public void init() throws ServletException {
		super.init();
		
		// Inicializa a coleção de dados de voos
		datasource = new FlightDataCollection();
		
		// Inicializa as instâncias dos totems observadores para diferentes estados dos voos
		totemArriving = TotemArriving.getInstance();
		totemBoarding = TotemBoarding.getInstance();
		totemTakingOff = TotemTakingOff.getInstance();
		totemTookOff = TotemTookOff.getInstance();
		
		// Registra os totems na coleção de dados de voos
		datasource.register(totemArriving);
		datasource.register(totemBoarding);
		datasource.register(totemTakingOff);
		datasource.register(totemTookOff);
	}
    
	// Método doGet é chamado quando o servlet recebe uma requisição HTTP GET.
    // Esse método chama o método processRequest para tratar a requisição.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response); // Chama o método processRequest para tratar a requisição.
	}
	
	// Método doPost é chamado quando o servlet recebe uma requisição HTTP POST.
    // Esse método chama o método processRequest para tratar a requisição.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response); // Chama o método processRequest para tratar a requisição.
	}
	
	// O método processRequest é o coração da lógica de controle do servlet.
    // Ele verifica qual ação o usuário está tentando realizar e chama a função correspondente.
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String view;
		
		// Verifica qual ação o usuário deseja realizar e chama o método correspondente
		if (Constants.LOGIN.equals(action)) {
		    view = handleLogin(request, response);
		} else if (Constants.LOGOUT.equals(action)) {
		    view = handleLogout(request, response);
		} else if (Constants.REGISTER_FLIGHT.equals(action)) {
		    view = handleRegisterFlight(request, response);
		} else if (Constants.UPDATE_FLIGHT.equals(action)) {
		    view = handleUpdateStateFlight(request, response);
		} else if (Constants.REDIRECTTO.equals(action)) {
			
			// Se for uma ação de redirecionamento, verifica a página para onde deve redirecionar
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
		
	// Método responsável pela realização de login no sistema.
	private String handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var user = request.getParameter("user");
		var password = request.getParameter("password");
		
		// Se os dados estiverem vazios, retorna mensagem de erro
		if (user == null || user.isBlank() || password == null || password.isBlank()) {
			return setErrorAndRedirect(request, "Usuário e Senha são Obrigatórios.", Constants.LOGIN_ADMIN);
	    }
		
		// Se os dados estiverem inválidos, retorna mensagem de erro
		if(!validateLogin(user, password)){
			return setErrorAndRedirect(request, "Dados Inválidos.", Constants.LOGIN_ADMIN);
		} 
		
		// Se o login for bem-sucedido, armazena o usuário na sessão e redireciona para a página administrativa
		var session = request.getSession();
		session.setAttribute("user", user);
		return Constants.PAGE_ADMIN;
		
	}
	
	// Função de validação de login
	private boolean validateLogin(String user, String password)
	{
		// Verifica se o usuário e a senha correspondem aos valores esperados
		return Constants.USER.equals(user) && Constants.PASSWORD.equals(password);
	}
	
	// Método responsável pelo logout, invalidando a sessão do usuário.
	private String handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		var session = request.getSession(false);
		session.invalidate();
		return Constants.LOGIN_ADMIN;
	}
	
	// Método responsável pelo registro de novos voos.
	private String handleRegisterFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		var flightNumberStr = request.getParameter("flightNumber");
		var flightCompany = request.getParameter("flightCompany");
		var flightTime = request.getParameter("flightTime");
		
		if (isBlankOrNull(flightNumberStr)) {
			return setErrorAndRedirect(request, "Número do Voo é Obrigatório.", Constants.PAGE_FORM_FLIGHT);
	    }

	    if (isBlankOrNull(flightCompany)) {
	    	return setErrorAndRedirect(request, "Companhia Aérea é Obrigatória.", Constants.PAGE_FORM_FLIGHT);
	    }

	    if (isBlankOrNull(flightTime)) {
	    	return setErrorAndRedirect(request, "Horário do Voo é Obrigatório.", Constants.PAGE_FORM_FLIGHT);
	    }

	    try {
	        Long flightNumber = Long.parseLong(flightNumberStr);

	        // Verifica se o voo já foi cadastrado
	        if (findFlightByNumber(flightNumber)) {
	        	return setErrorAndRedirect(request, "Voo já Cadastrado.", Constants.PAGE_FORM_FLIGHT);
	        }
	        
	        // Verifica se o horário do voo está no passado
	        if (isArrivalTimePast(flightTime)) {
	        	return setErrorAndRedirect(request, "Horário Inválido. O Voo Deve ter um Horário Futuro.", Constants.PAGE_FORM_FLIGHT);
	        }

	        FlightData flight = new FlightData(flightNumber, flightCompany, flightTime);
	        flight.setState(Arriving.getIntance());
	        datasource.insertFlight(flight);
	        
	        return setSuccessAndRedirect(request, "Voo Cadastrado com Sucesso!", Constants.PAGE_FORM_FLIGHT);

	    } catch (NumberFormatException e) {
	    	return setErrorAndRedirect(request, "O Número do Voo Deve Ser um Número Válido.", Constants.PAGE_FORM_FLIGHT);
	    }

	}
	
	// Método auxiliar para verificar se uma string está em branco ou nula
	private boolean isBlankOrNull(String value) {
	    return value == null || value.isBlank();
	}
	
	// Método para definir mensagem de erro e redirecionar para uma página
	private String setErrorAndRedirect(HttpServletRequest request, String errorMessage, String redirectPage) {
	    request.setAttribute("error", errorMessage); // Define a mensagem de erro
	    return redirectPage;
	}
	
	// Método para definir mensagem de sucesso e redirecionar para uma página
	private String setSuccessAndRedirect(HttpServletRequest request, String successMessage, String redirectPage) {
	    request.setAttribute("success", successMessage); // Define a mensagem de sucesso
	    return redirectPage; // Retorna a página para onde o usuário será redirecionado
	}
	
	// Método para encontrar voo pelo número
	private boolean findFlightByNumber(Long flightNumber) {
	    return datasource.getAllFligthts().stream()
	            .anyMatch(f -> f.getFlightNumber().equals(flightNumber));
	}
	
	// Método para verificar se o horário de chegada do voo é no passado
	private boolean isArrivalTimePast(String flightTime) {
		if (flightTime == null || flightTime.isBlank()) {
	        return false; // Horário inválido é tratado como não sendo passado.
	    }

	    try {
	        LocalTime time = LocalTime.parse(flightTime);
	        return time.isBefore(LocalTime.now());
	    } catch (DateTimeParseException e) {
	        return false; // Se o horário não puder ser analisado, considera-se inválido.
	    }
	}
	
	// Método para redirecionar o usuário para a página de adminstrador.
	private String handlePageAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listFlights", datasource.getAllFligthts());
		request.setAttribute("loadData", true);
		
		return Constants.PAGE_ADMIN;
	}
	
	// Método para redirecionar o usuário para a página de login de adminstrador.
	private String handleLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return Constants.LOGIN_ADMIN;
	}
	
	// Método para redirecionar o usuário para a página de exibição do formulário de cadastro de voos.
	private String handlePageFormFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    return Constants.PAGE_FORM_FLIGHT;
	}
	
	// Método para atualizar o estado de um voo.
	private String handleUpdateStateFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flightNumberStr = request.getParameter("flightNumberUpdate");

		// Verifica se o número do voo é válido
	    if (isBlankOrNull(flightNumberStr)) {
	        return setErrorAndRedirect(request, "Opss.. Não Foi Possível Atualizar o Voo. O Número está Vazio.", Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_ADMIN);
	    }

	    try {
	        Long flightNumber = Long.parseLong(flightNumberStr);
	        datasource.updateFlight(flightNumber);  // Atualiza o voo
	        return Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_ADMIN; // Redireciona para a página de admin
	    } catch (NumberFormatException e) {
	        return setErrorAndRedirect(request, "Opss.. Não Foi Possível Atualizar o Voo. O Número é Inválido.", Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_ADMIN);
	    }
	}
	
	// Método genérico para lidar com a exibição de voos em diferentes estados (chegando, embarcando, decolando, etc.).
	private String handlePageFlights(HttpServletRequest request, HttpServletResponse response, TotemModel totem, String attributeName, String pageName) throws ServletException, IOException {
	    request.setAttribute(attributeName, totem.getFlights());
	    request.setAttribute("loadData", true);
	    return pageName;
	}
	
	// Método específico para lidar com a exibição de voos desembarcando.
	private String handlePageFlightsArriving(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    return handlePageFlights(request, response, totemArriving, "listTotemFlightsArriving", Constants.PAGE_SHOW_FLIGHTS_ARRIVING);
	}
	
	// Método específico para lidar com a exibição de voos embarcando.
	private String handlePageFlightsBoarding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    return handlePageFlights(request, response, totemBoarding, "listTotemFlightsBoarding", Constants.PAGE_SHOW_FLIGHTS_BOARDING);
	}

	// Método específico para lidar com a exibição de voos decolando.
	private String handlePageFlightsTakingOff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    return handlePageFlights(request, response, totemTakingOff, "listTotemFlightsTakingOff", Constants.PAGE_SHOW_FLIGHTS_TAKING_OFF);
	}
	
	// Método específico para lidar com a exibição de voos decolados.
	private String handlePageFlightsTookOff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    return handlePageFlights(request, response, totemTookOff, "listTotemFlightsTookOff", Constants.PAGE_SHOW_FLIGHTS_TOOK_OFF);
	}

}
