<%@ page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="br.edu.ifsp.dsw1.utils.Constants"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	var isLogged = session.getAttribute("user"); 
	List<FlightData> datasource = null;
    
    if(isLogged == null)
    {
    	response.sendRedirect(Constants.ACTION_REDIRECTTO_URL + Constants.LOGIN_ADMIN);
    	return;
    }
   	else
    {
    	var isLoadData = request.getAttribute("loadData");
    	
   		if(isLoadData == null)
    	{
    		response.sendRedirect(Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_ADMIN);
    		return;
    	}
   		else
   		{
   			datasource = (List<FlightData>) request.getAttribute("listFlights");
   		}
    }
%> 

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AirPort - Página Adminstrador</title>
	<jsp:include page="/includes/head.html" />
</head>
<body>
	<jsp:include page="/includes/navBar.jsp">
    	<jsp:param name="isLogged" value="<%= (isLogged != null) ? \"true\" : \"false\" %>" />
	</jsp:include>
	
	<div class = container>
		<div class = "conteudoPrincipal">
	
			<h1 class = "titlePage">Gerenciamento de Voos</h1>
		
			<%  
		        if (datasource != null) {
		            if (datasource.size() != 0) {
		    %>			
		    			<a href="<%= Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_FORM_FLIGHT %>" class = "btnCadastrarVoo">Cadastrar Voo</a>
		    			<div class = "tabela table-responsive">
			                <table class="table">
			                    <thead>
			                        <tr>
			                            <th scope="col">Número</th>
			                            <th scope="col">Compania</th>
			                            <th scope="col">Data</th>
			                            <th scope="col">Estado</th>
			                            <th scope="col">Ações</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                        <% for (FlightData flight : datasource) { %>
			                            <tr>
			                                <td><%= flight.getFlightNumber() %></td>
			                                <td><%= flight.getCompany() %></td>
			                                <td><%= flight.getTime() %></td>
			                                <td><%= flight.getState().getClass().getSimpleName() %></td>
			                                <td>
			                                    <a href="<%= Constants.ACTION_UPDATE_FLIGHT_URL + flight.getFlightNumber() %>">Atualizar</a>
			                                </td>
			                            </tr>
			                        <% } %>
			                    </tbody>
			                </table>
			            </div>
		    <%      
		            } else {
		    %>
		                <div class="text-center">
					        <lord-icon
					            src="https://cdn.lordicon.com/dicvhxpz.json"
					            trigger="loop"
					            state="hover-look-around"
					            delay="3000"
					            colors="primary:#000000,secondary:#000000"
					            style="width:10rem;height:10rem">
					        </lord-icon>
					        <h4>Opss... Nenhum Voo Cadastrado No Banco.</h4>
					    </div>
					    
					    <a href="<%= Constants.ACTION_REDIRECTTO_URL + Constants.PAGE_FORM_FLIGHT %>" class = "btnCadastrarVoo">Cadastrar Voo</a>
		    <%      
		            }
		        }
		    %>
		</div>
	</div>
	
	<jsp:include page="/includes/footer.html" />
	<jsp:include page="/includes/scripts.html" />
</body>
</html>