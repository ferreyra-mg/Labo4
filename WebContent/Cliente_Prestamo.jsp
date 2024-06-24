<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidad.Cliente"%>
<%@ page import="entidad.Cuenta"%>
<%@ page import="entidad.Prestamo"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Prestamo</title>
</head>
<body class="cl">

	<%
		Cliente cli = null;
	
		if (session.getAttribute("usuarioLogueado") != null)
			cli = (Cliente) session.getAttribute("usuarioLogueado");
	
	%>

	<nav class="mask">
		<div class="name-user">
			<%= session.getAttribute("nm_user") != null ? session.getAttribute("nm_user") : "" %>
		</div>
		<ul class="list">
			<li><a href="Cliente_Transferencia.jsp">Transferencia</a></li>
			<li><a href="Cliente_Home.jsp">Historial</a></li>
			<li><a href="ServletPrestamos">Prestamo</a></li>
			<li><a href="Cliente_Perfil.jsp">Perfil</a></li>
		</ul>
	</nav>

	<div class="prestamo">
		<div class="pedir_prestamo">

			<% if (request.getAttribute("prestamo_a_pagar") != null) { 
				Prestamo prestamo = (Prestamo) request.getAttribute("prestamo_a_pagar"); 
			%>
				<form action="ServletPrestamos" method="post" class="nuevo-prestamo">
					
					<input type="hidden" name="prestamo-id-a-pagar" value="<%= prestamo.getId() %>" />
				
					<div class="inputs">
						<label for="capital">Cuota:</label>
						<input type="number" id="capital" name="capital" placeholder="Ingrese el capital" value="<%= prestamo.getCuotas().size() + 1 %>" required>			
						<label for="meses">De:</label>
						<input type="number" id="meses" name="meses" placeholder="Ingrese meses" min="1" step="1" required value="<%= prestamo.getCantMeses() %>" >				
					</div>
		
					<div class="filtrar_cuentas a-debitar">
						<label>Elige una cuenta desde la que debitar el pago:</label>
						<select name="cuenta" id="cuenta">
							<% for (Cuenta cta : cli.cuentas()) {%>
								<option value="<%= cta.getId() %>"><%= cta.getTipo() + " [" + cta.getCBU() + "]"   %></option>	
							<%} %>
						</select>
					</div>                 
					
					<div class="monto-a-debitar">
						<label for="meses">Monto a debitar:</label>
						<input type="number" id="cuota" name="cuota" required value="<%= prestamo.getMontoMensual() %>" >
					</div>
					
					<input type="submit" class="btn-aceptar btn-solicitar-prestamo" name="pagar" value="CONFIRMAR PAGO">
				
				</form>
			<% } else {%>			
				<form action="ServletPrestamos" method="post" class="nuevo-prestamo">
				
					<div class="inputs">
						<label for="capital">Capital:</label>
						<input type="number" id="capital" name="capital" placeholder="Ingrese el capital" min="100" step="100.00" required autofocus="autofocus">			
						<label for="meses">Meses:</label>
						<input type="number" id="meses" name="meses" placeholder="Ingrese meses" min="1" step="1" required value="12" >				
					</div>
		
	
				<div class="filtrar_cuentas">
						<label>Elige una cuenta:</label>
						<select name="cuenta" id="cuenta">
							<% for (Cuenta cta : cli.cuentas()) {%>
								<option value="<%= cta.getId() %>"><%= cta.getTipo() + " [" + cta.getCBU() + "]"   %></option>	
							<%} %>
						</select>
					</div>                 
					
					<div class="montos">
						<div class="monto-mensual">
							<span>Monto Mensual:</span>
							<span id="total-mensual"></span>
						</div>
						<div class="monto-total">
							<span>Monto Total:</span>
							<span id="total"></span>
						</div>				
					</div>
					
					<input type="submit" class="btn-aceptar btn-solicitar-prestamo" name="solicitar" value="SOLICITAR">
				
				</form>
			<% } %>
			

			<div class="error-message">
				<%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
			</div>
		</div>


		<div class="lista_prestamo">

			
			<%
			    // Simulamos datos de movimientos para este ejemplo
			    List<String[]> movimientos = new ArrayList<String[]>();
			    for (int i = 1; i <= 50; i++) {
			        movimientos.add(new String[]{ "" + i, "$" + (i*69.69 + 1), ""});
			    }
			
			    // Par�metros de paginaci�n
			    int pageSize = 10; // N�mero de elementos por p�gina
			    int pageNumber = 1; // N�mero de la p�gina actual
			    if (request.getParameter("page") != null) {
			        pageNumber = Integer.parseInt(request.getParameter("page"));
			    }
			
			    // C�lculo de la paginaci�n
			    int totalItems = movimientos.size();
			    int totalPages = (int) Math.ceil((double) totalItems / pageSize);
			    int startIndex = (pageNumber - 1) * pageSize;
			    int endIndex = Math.min(startIndex + pageSize, totalItems);
			
			    // Sublista para la p�gina actual
			    List<String[]> pageItems = movimientos.subList(startIndex, endIndex);
			%>
			
			<table class="tabla-prestamos">
				<thead>
					<tr>
						<th colspan="5">Historial de Prestamos</th>
					</tr>
					<tr>
						<th>Prestamo Nro.</th>
						<th>Fecha</th>
						<th>Monto</th>
						<th>Cuotas Pendientes</th>
						<th>Estado</th>
						<th>Acci�n</th>
					</tr>
				</thead>
				<% for (Prestamo prestamo : cli.prestamos()) { %>
					<tr>
						<td><%= prestamo.getId() %></td>
						<td><%= prestamo.getFecha() %></td>
						<td><%= prestamo.getPrestamo() %></td>
						<td>05/12</td>
						<% if (prestamo.autorizado()) { %> 
							<td>Otorgado</td>
							<td>
								<a href="ServletPrestamos?pagar_cuota_prestamo=<%= prestamo.getId() %>" >Pagar</a>
							</td>
						<% } else {%>
							<td>Pendiente</td>
							<td></td>
						<% } %>
					</tr>
				<% } %>
			</table>

			<div class="pagination">
				<% for (int i = 1; i <= totalPages; i++) { %>
				<a href="?page=<%= i %>"
					class="<%= (i == pageNumber) ? "active" : "" %>"><%= i %></a>
				<% } %>
			</div>

		</div>
	</div>

	<script>
	document.addEventListener('DOMContentLoaded', function() {
		
		const input_capital = document.getElementById('capital');
		const input_meses = document.getElementById('meses');
		const total = document.getElementById('total');
		const total_mensual = document.getElementById('total-mensual');
		const INTERES_ANUAL = 0.75; // 75%
		
		function actualizarMontos() {
			
		    const monto = parseFloat(input_capital.value) || 0;
		    const meses = parseFloat(input_meses.value) || 0;
		
		    const FACTOR_INTERES_MENSUAL = (1 + (INTERES_ANUAL / 12) * meses);
		    const total_final = monto * FACTOR_INTERES_MENSUAL;
		    const mensual = total_final / meses;
		
		    total.textContent = total_final.toFixed(2);
		    total_mensual.textContent = mensual.toFixed(2);
		}
		
		input_capital.addEventListener('input', actualizarMontos);
		input_meses.addEventListener('input', actualizarMontos);
		
	});
	</script>

</body>
</html>