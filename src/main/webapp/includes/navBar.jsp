<%
    var isLogged = request.getParameter("isLogged");
%>

<nav class="navbar navbar-expand-lg cabecalho">
    <div class="container-fluid">
        <!-- Logo à esquerda -->
        <a class="navbar-brand" href="index.jsp">SkyPort</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <!-- Seção do meio: Links principais centralizados -->
            <div class="navbar-nav ms-auto">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="airport.do?action=redirectTo&page=pageAdmin.jsp">Administração</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="airport.do?action=redirectTo&page=pageShowFlightsBoarding.jsp">Sala de Embarque</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="airport.do?action=redirectTo&page=pageShowFlightsArriving.jsp">Sala de Desembarque</a>
                    </li>
                    <li class="nav-item">
                    	
                        <a class="nav-link" href="airport.do?action=redirectTo&page=pageShowFlightsTakingOff.jsp">Hall 1</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="airport.do?action=redirectTo&page=pageShowFlightsTookOff.jsp">Hall 2</a>
                    </li>
                    <li class="nav-item">
                		<% 
		                    if(isLogged != null && isLogged.equals("true")) {
		                        out.println("<a class='nav-link' href='airport.do?action=logout'>Logout</a>");
		                    } else {
		                        out.println("<a class='nav-link btn btn-secondary' href='loginAdmin.jsp'>Login</a>");
		                    }
		                %>
                	</li>
                </ul>
            </div>
        </div>
    </div>
</nav>
