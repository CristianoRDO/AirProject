<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	var isLogged = session.getAttribute("user"); 
	var isLoadData = request.getAttribute("loadData");
	List<FlightData> datasource = null;
	
	if(isLoadData == null)
	{
		response.sendRedirect("airport.do?action=redirectTo&page=pageShowFlightsBoarding");
		return;
	}
	else
	{
		datasource = (List<FlightData>) request.getAttribute("listTotemFlightsBoarding");
	}
%> 

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AirPort - Sala de Embarque</title>
	<jsp:include page="/includes/head.html" />
</head>
<body>
	<jsp:include page="/includes/navBar.jsp">
    	<jsp:param name="isLogged" value="<%= (isLogged != null) ? \"true\" : \"false\" %>" />
	</jsp:include>
	
	<h1>Voos Embarcando</h1>
	
	<%  
		if(datasource != null)
		{
			for(FlightData flight: datasource)
			{
				out.println("Numero: " + flight.getFlightNumber());
				out.println("Compania: " + flight.getCompany());
				out.println("Data: " + flight.getTime());
				out.println("Estado: " + flight.getState().getClass().getSimpleName());
				out.println("<a href=\"airport.do?action=updateFlight&flightNumberUpdate=" + flight.getFlightNumber() + "\">Atualizar</a>");
				out.println("\n\n\n");
			}
		}
		
	%>
		
	<jsp:include page="/includes/scripts.html" />
</body>
</html>