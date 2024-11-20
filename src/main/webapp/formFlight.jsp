<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	var isLogged = session.getAttribute("user"); 
   	
	if(isLogged == null)
	{
		response.sendRedirect("airport.do?action=redirectTo");
		return;
	}
%> 
	
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AirPort - Cadastro de Voos</title>
	<%@ include file="/includes/head.html" %>
</head>
<body>
	<%@ include file="/includes/navBar.jsp" %>
	
	<div class = "formLogin">
		<h1>Cadastrar Voo</h1>
        
        <form action = "airport.do?action=registerFlight" method = "post">
        	<div class = "field">
        		<label for = "flightNumber">Numero do Voo </label>
        		<input type = "number" name = "flightNumber" id = "flightNumber" required>
        	</div>
			
			<div class = "field">
				<label for = "flightCompany">Compania </label>
				<input type = "text" name = "flightCompany" id = "flightCompany" required>
			</div>
			
			<div class = "field">
				<label for = "flightTime">Data </label>
				<input type = "datetime-local" name = "flightTime" id = "flightTime" required>
			</div>
			
			<%
				String msg = (String) request.getAttribute("error");
				if (msg != null ) {
					
					out.print("<span>"+ msg +"</span>");
				}
			%>
			<button type = "submit">CADASTRAR</button>
		</form>
	</div>
	
	<%@ include file="/includes/scripts.html" %>
</body>
</html>