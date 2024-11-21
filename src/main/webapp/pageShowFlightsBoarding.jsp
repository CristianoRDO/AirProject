<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="br.edu.ifsp.dsw1.utils.Constants"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	var isLogged = session.getAttribute("user"); 
	var isLoadData = request.getAttribute("loadData");
	List<FlightData> datasource = null;
	
	if(isLoadData == null)
	{
		response.sendRedirect(Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_SHOW_FLIGHTS_BOARDING);
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
				out.println("\n\n\n");
			}
		}
		
	%>
		
	<jsp:include page="/includes/scripts.html" />
</body>
</html>