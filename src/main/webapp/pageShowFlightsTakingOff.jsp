<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	var isLogged = session.getAttribute("user"); 
	var isLoadData = request.getAttribute("loadData");
	
	if(isLoadData == null)
	{
		response.sendRedirect("airport.do?action=redirectTo&page=pageShowFlightsTakingOff");
		return;
	}
%> 
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AirPort - Hall 1</title>
	<%@ include file="/includes/head.html" %>
</head>
<body>
	<%@ include file="/includes/navBar.jsp" %>
	
	<h1>Voos Decolando</h1>
		
	<%@ include file="/includes/scripts.html" %>
</body>
</html>