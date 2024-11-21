<%@page import="br.edu.ifsp.dsw1.utils.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
	var isLogged = session.getAttribute("user"); 
	
	if (isLogged != null) {
		response.sendRedirect(Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_ADMIN);
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
        
        <form action = "<%= Constants.ACTION_LOGIN_URL %>" method = "post">
        	<div class = "field">
        		<label for = "user">Usuário </label>
        		<input type = "text" name = "user" id = "user" required>
        	</div>
			
			<div class = "field">
				<label for = "user">Senha </label>
				<input type = "password" name = "password" id = "password" required>
			</div>
			
			<%
			    String errorMsg = (String) request.getAttribute("error");
			
			    if (errorMsg != null) {
			%>
			        <span style="color: red;"><%= errorMsg %></span>
			<%
			    }
			%>
			<button type = "submit">LOGIN</button>
		</form>
	</div>
	
	<jsp:include page="/includes/scripts.html" />
</body>
</html>