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
	<%@ include file="/includes/head.html" %>
</head>
<body>
	<%@ include file="/includes/navBar.jsp" %>
	
	<h1>Voos Desembarcando</h1>
		
	<%@ include file="/includes/scripts.html" %>
</body>
</html>