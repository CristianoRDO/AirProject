<%@page import="br.edu.ifsp.dsw1.utils.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	var isLogged = session.getAttribute("user"); 
   	
	if(isLogged == null)
	{
		response.sendRedirect(Constants.ACTION_REDIRECTTO_URL + Constants.INDEX);
		return;
	}
%> 
	
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AirPort - Cadastro de Voos</title>
	<jsp:include page="/includes/head.html" />
</head>
<body>
	<jsp:include page="/includes/navBar.jsp">
    	<jsp:param name="isLogged" value="<%= (isLogged != null) ? \"true\" : \"false\" %>" />
	</jsp:include>
	
	<div class = container>
		<div class = "conteudoPrincipal">
	
			<div class = "formModel">
				<h1>Cadastrar Voo</h1>
		        
		        <form action = "<%= Constants.ACTION_REGISTER_FLIGHT_URL %>" method = "post">
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
					    String errorMsg = (String) request.getAttribute("error");
					    String successMsg = (String) request.getAttribute("success");
					
					    if (errorMsg != null) {
					%>
					        <p style="color: red; text-align: center"><%= errorMsg %></p>
					<%
					    } else if (successMsg != null) {
					%>
					        <p style="color: green; text-align: center"><%= successMsg %></p>
					<%
					    }
					%>
					<button type = "submit">CADASTRAR</button>
				</form>
			</div>
		</div>
	</div>
	
	<jsp:include page="/includes/scripts.html" />
</body>
</html>