<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
	var isLogged = session.getAttribute("user"); 
	
	if (isLogged != null) {
		response.sendRedirect("airport.do?action=validateIsLogged");
	}
	
%> 
   
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AirPort - Login</title>
	<%@ include file="/includes/head.html" %>
</head>
<body>
	<%@ include file="/includes/navBar.jsp" %>
		
	<div class = "formLogin">
		<h1>Login</h1>
        
        <form action = "airport.do?action=login" method = "post">
        	<div class = "field">
        		<label for = "user">Usuário </label>
        		<input type = "text" name = "user" id = "user" required>
        	</div>
			
			<div class = "field">
				<label for = "user">Senha </label>
				<input type = "password" name = "password" id = "password" required>
			</div>
			
			<%
				String msg = (String) request.getAttribute("message");
				if (msg != null ) {
					
					out.print("<span>Dados inválidos</span>");
				}
			%>
			<button type = "submit">LOGIN</button>
		</form>
	</div>
	
	<%@ include file="/includes/scripts.html" %>
</body>
</html>