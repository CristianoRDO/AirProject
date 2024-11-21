<%
    var isLogged = request.getParameter("isLogged");
%>

<nav class="navbar navbar-expand-lg navbar-dark cabecalho">
    <div class="container-fluid">
        <!-- Logo à esquerda -->
        <a class="navbar-brand me-5" href="index.jsp">SkyPort</a>

        <!-- Botão de menu para dispositivos menores -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navegação distribuída uniformemente -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav w-100 d-flex justify-content-between">
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
                        if (isLogged != null && isLogged.equals("true")) { 
                    %>
                        <a class="nav-link btn" href="airport.do?action=logout">Logout</a>
                    <% 
                        } else { 
                    %>
                        <a class="nav-link btn" href="airport.do?action=redirectTo&page=loginAdmin.jsp">Login</a>
                    <% 
                        } 
                    %>
                </li>
            </ul>
        </div>
    </div>
</nav>
