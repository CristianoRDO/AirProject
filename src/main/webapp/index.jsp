<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% var isLogged = session.getAttribute("user"); %> 
     
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>SkyPort - Inicial</title>
	<jsp:include page="/includes/head.html" />
</head>
<body>
	<jsp:include page="/includes/navBar.jsp">
    	<jsp:param name="isLogged" value="<%= (isLogged != null) ? \"true\" : \"false\" %>" />
	</jsp:include>
	
	<div class = container>
		<div class = "conteudoPrincipal">
			<div class = "textoInicial">
				<h1>Bem-Vindo ao <span class="corDetalhe">SkyPort</span></h1>
				<h3>Sua Jornada Começa Aqui</h3>
				<p>Explore um mundo de viagens sem interrupções com atualizações em tempo real de todos os voos. Seja para <strong>embarque</strong>, <strong>desembarque</strong>, <strong>chegada</strong> ou <strong>partida</strong>, nossa plataforma garante que você esteja sempre informado e preparado.</p>
				<p>Descubra o luxo de viajar sem estresse com a mais recente tecnologia ao seu alcance. Experimente conveniência, confiabilidade e excelência — tudo em um só lugar.</p>
			</div> 
			
			<div class = "secaoCardVoos">
				<h2>Seções de Voo</h2>
				<p>Está perdido? Confira o agendamento de nossos voos!</p>


				<div class="row row-cols-1 row-cols-md-4 g-4">
				  <div class="col">
				    <div class="cardVoo card h-100">
				      <div class="card-img-top text-center">
				        <span class="material-icons" style = "font-size: 5rem; color: #DCBB87;">luggage</span>
				      </div>
				      <div class="card-body d-flex flex-column">
				        <h5 class="card-title">Embarcando</h5>
				        <p class="card-text">Veja os voos que estão em processo de embarque em <strong>Sala de Embarque</strong>.</p>
				      </div>
				    </div>
				  </div>

				  <div class="col">
				    <div class="cardVoo card h-100">
				      <div class="card-img-top text-center">
				        <span class="material-icons icon" style = "font-size: 5rem; color: #DCBB87;">flight_land</span>
				      </div>
				      <div class="card-body d-flex flex-column">
				        <h5 class="card-title">Desembarcando</h5>
				        <p class="card-text">Veja os voos que estão em processo de desembarque em <strong>Sala de Desembarque</strong>.</p>
				      </div>
				    </div>
				  </div>

				  <div class="col">
				    <div class="cardVoo card h-100">
				      <div class="card-img-top text-center">
				        <span class="material-icons" style = "font-size: 5rem; color: #DCBB87;">flight_takeoff</span>
				      </div>
				      <div class="card-body d-flex flex-column">
				        <h5 class="card-title">Decolando</h5>
				        <p class="card-text">Veja os voos que estão em processo de decolagem em <strong>Hall 1</strong>.</p>
				      </div>
				    </div>
				  </div>

				  <div class="col">
				    <div class="cardVoo card h-100">
				      <div class="card-img-top text-center">
				        <span class="material-icons" style = "font-size: 5rem; color: #DCBB87;">flight</span>
				      </div>
				      <div class="card-body d-flex flex-column">
				        <h5 class="card-title">Decolados</h5>
				        <p class="card-text">Veja os voos que já decolaram em <strong>Hall 2</strong>.</p>
				      </div>
				    </div>
				  </div>
				</div>
			</div> 
		</div>
	</div>
	<jsp:include page="/includes/footer.html" />
	<jsp:include page="/includes/scripts.html" />
</body>
</html>