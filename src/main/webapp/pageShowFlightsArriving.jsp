<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	var isLogged = session.getAttribute("user"); 
	var isLoadData = request.getAttribute("loadData");
	
	if(isLoadData == null)
	{
		response.sendRedirect("airport.do?action=redirectTo&page=pageShowFlightsArriving");
		return;
	}
%> 

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AirPort - Sala de Desembarque</title>
	<jsp:include page="/includes/head.html" />
</head>
<body>
	<jsp:include page="/includes/navBar.jsp">
    	<jsp:param name="isLogged" value="<%= (isLogged != null) ? \"true\" : \"false\" %>" />
	</jsp:include>
	
	<h1>Voos Desembarcando</h1>
		
	<jsp:include page="/includes/scripts.html" />
</body>
</html>