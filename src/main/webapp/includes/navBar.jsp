<%
    var isLogged = request.getParameter("isLogged");
%>

<nav class="navbar navbar-expand-lg bg-secondary-subtle">
    <div class="container-fluid">

        <a class="navbar-brand" href="index.jsp">Airport DSW1</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mx-auto">
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

                <!-- Login/Logout -->
                <% 
                    if(isLogged.equals("true")){
                        out.println("<li class='nav-item'><a class='nav-link' href='airport.do?action=logout'>Logout</a></li>");
                    }
                    else {
                        out.println("<li class='nav-item'><a class='nav-link' href='loginAdmin.jsp'>Login</a></li>");
                    }
                %>
            </ul>
        </div>
    </div>
</nav>