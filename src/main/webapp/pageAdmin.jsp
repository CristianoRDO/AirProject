<%@ page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="br.edu.ifsp.dsw1.utils.Constants"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	var isLogged = session.getAttribute("user"); 
	List<FlightData> datasource = null;
    
    if(isLogged == null)
    {
    	response.sendRedirect(Constants.ACTION_REDIRECTTO_URL + Constants.LOGIN_ADMIN);
    	return;
    }
   	else
    {
    	var isLoadData = request.getAttribute("loadData");
    	
   		if(isLoadData == null)
    	{
    		response.sendRedirect(Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_ADMIN);
    		return;
    	}
   		else
   		{
   			datasource = (List<FlightData>) request.getAttribute("listFlights");
   		}
    }
%> 

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AirPort - Página Adminstrador</title>
	<jsp:include page="/includes/head.html" />
</head>
<body>
	<jsp:include page="/includes/navBar.jsp">
    	<jsp:param name="isLogged" value="<%= (isLogged != null) ? \"true\" : \"false\" %>" />
	</jsp:include>
	
	<a href = "formFlight.jsp">Cadastrar Voo</a>
	
	<%  
		if(datasource != null)
		{
			for (FlightData flight : datasource) {
			    out.println("Numero: " + flight.getFlightNumber());
			    out.println("Compania: " + flight.getCompany());
			    out.println("Data: " + flight.getTime());
			    out.println("Estado: " + flight.getState().getClass().getSimpleName());
			    out.println("<a href=\"" + Constants.ACTION_UPDATE_FLIGHT_URL + flight.getFlightNumber() + "\">Atualizar</a>");
			    out.println("\n\n\n");
			}

		}
		
	
	%>
	
	<jsp:include page="/includes/scripts.html" />
</body>
</html>