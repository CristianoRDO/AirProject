<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
	var isLogged = session.getAttribute("user"); 
	
	if (isLogged != null) {
		response.sendRedirect("airport.do?action=redirectTo&page=pageAdmin");
	}
%> 
   
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AirPort - Login</title>
	<jsp:include page="/includes/head.html" />
</head>
<body>
	<jsp:include page="/includes/navBar.jsp">
    	<jsp:param name="isLogged" value="<%= (isLogged != null) ? \"true\" : \"false\" %>" />
	</jsp:include>
		
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
	
	<jsp:include page="/includes/scripts.html" />
</body>
</html>