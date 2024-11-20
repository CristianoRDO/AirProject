<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	var isLogged = session.getAttribute("user"); 
	List<FlightData> datasource = null;
    
    if(isLogged == null)
    {
    	response.sendRedirect("airport.do?action=redirectTo");
    	return;
    }
   	else
    {
    	var isLoadData = request.getAttribute("loadData");
    	
   		if(isLoadData == null)
    	{
    		response.sendRedirect("airport.do?action=redirectTo&page=pageAdmin");
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
	<%@ include file="/includes/head.html" %>
</head>
<body>

	<%@ include file="/includes/navBar.jsp" %>
	
	<a href = "formFlight.jsp">Cadastrar Voo</a>
	
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
	
	<%@ include file="/includes/scripts.html" %>
</body>
</html>