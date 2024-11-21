package br.edu.ifsp.dsw1.utils;

public final class Constants {
	
	//
	public static final String SERVLET_NAME = "airport.do";
	
	// Caminhos para as páginas
    public static final String PAGE_ADMIN = "pageAdmin.jsp";
    public static final String LOGIN_ADMIN = "loginAdmin.jsp";
    public static final String INDEX = "index.jsp";
    public static final String PAGE_SHOW_FLIGHTS_ARRIVING = "pageShowFlightsArriving.jsp";
    public static final String PAGE_SHOW_FLIGHTS_BOARDING = "pageShowFlightsBoarding.jsp";
    public static final String PAGE_SHOW_FLIGHTS_TAKING_OFF = "pageShowFlightsTakingOff.jsp";
    public static final String PAGE_SHOW_FLIGHTS_TOOK_OFF = "pageShowFlightsTookOff.jsp";
    public static final String FORM_FLIGHT = "formFlight.jsp";

    // Ações
    
 // Prefixo de ação
    public static final String ACTION_PREFIX = "action";

    // Ações gerais
    public static final String LOGOUT = "logout";
    public static final String LOGIN = "login";
    public static final String REDIRECTTO = "redirectTo";
    public static final String PAGE = "page";
    public static final String REGISTER_FLIGHT = "registerFlight";
    public static final String UPDATE_FLIGHT = "updateFlight";
    
    // Caminhos de ação
    public static final String ACTION_LOGIN_URL = SERVLET_NAME + "?" + ACTION_PREFIX + "=" + LOGIN;
    public static final String ACTION_LOGOUT_URL = SERVLET_NAME + "?" + ACTION_PREFIX + "=" + LOGOUT;
    public static final String ACTION_REDIRECTTO_URL = SERVLET_NAME + "?" + ACTION_PREFIX + "=" + REDIRECTTO + "&" + PAGE + "=";
    public static final String ACTION_REGISTER_FLIGHT_URL = SERVLET_NAME + "?" + ACTION_PREFIX + "=" + REGISTER_FLIGHT;
    public static final String ACTION_UPDATE_FLIGHT_URL = SERVLET_NAME + "?" + ACTION_PREFIX + "=" + UPDATE_FLIGHT + "&flightNumberUpdate=";
    
    // Parâmetros de login
    public static final String USER = "admin";
    public static final String PASSWORD = "admin";
    
    // Mensagens de erro e sucesso
    public static final String ERROR_INVALID_DATA = "Dados Inválidos.";
    public static final String ERROR_INVALID_DATE = "Data Inválida.";
    public static final String ERROR_FLIGHT_ALREADY_EXISTS = "Voo Já Cadastrado.";
    public static final String SUCCESS_FLIGHT_REGISTERED = "Voo Cadastrado Com Sucesso!";
}
