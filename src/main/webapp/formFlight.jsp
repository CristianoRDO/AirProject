<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   	<%
		var isLogged = session.getAttribute("user"); 
	%> 
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AirPort - Cadastro de Voos</title>
</head>
<body>
	<%@ include file="/includes/navBar.jsp" %>
	
	<div class = "formLogin">
		<h1>Login</h1>
        
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
				<input type = "date" name = "flightTime" id = "flightTime" required>
			</div>
			
			<%
				String msg = (String) request.getAttribute("error");
				if (msg != null ) {
					
					out.print("<span>Dados inválidos</span>");
				}
			%>
			<button type = "submit">LOGIN</button>
		</form>
	</div>

</body>
</html>