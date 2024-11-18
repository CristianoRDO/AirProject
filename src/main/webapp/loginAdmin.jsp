<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%
	var isLogged = session.getAttribute("user"); 

	out.print("isLogged: " + isLogged);
	
	if (isLogged != null) {
		response.sendRedirect("airport.do?action=validateIsLogged");
	}
	
%> 
   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>AirPort - Login</title>
</head>
<body>
		
		
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
	
</body>
</html>