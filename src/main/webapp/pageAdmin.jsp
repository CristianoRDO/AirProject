<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
		var isLogged = session.getAttribute("user"); 
	%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AirPort - Página Adminstrador</title>
</head>
<body>

	<%@ include file="/includes/navBar.jsp" %>
	
	<a href = "formFlight.jsp">Cadastrar Voo</a>
	
	<% List<FlightData> datasource = (List<FlightData>) request.getAttribute("listFlights"); 
	
		for(FlightData flight: datasource)
		{
			out.println("Numero: " + flight.getFlightNumber());
			out.println("Compania: " + flight.getCompany());
			out.println("Data: " + flight.getTime());
			out.println("Estado: " + flight.getState().getClass().getSimpleName());
			out.println("<a href=\"airport.do?action=updateFlight&flightNumberUpdate=" + flight.getFlightNumber() + "\">Atualizar</a>");
			out.println("\n\n\n");
		}
	
	%>

</body>
</html>