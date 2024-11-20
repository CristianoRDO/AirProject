<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
		var isLogged = session.getAttribute("user"); 
	%> 
     
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AirPort - Inicial</title>
	<%@ include file="/includes/head.html" %>
</head>
<body>
	<%@ include file="/includes/navBar.jsp" %>
	
	<h1>Index</h1>
		
	<%@ include file="/includes/scripts.html" %>
</body>
</html>