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

<!-- Para usar el datatables -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<!-- Para usar el datatables y ponerlo en español-->
    
    <script type="text/javascript">
		$(document).ready( function () {
			$('#tabla').DataTable({
				//cambiar lo que esta en ingles a español
				"language":{
					"lengthMenu": "Mostrar _MENU_ registros",
					"zeroRecords": "No se encontraron resultados",
					"info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
					"infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
					"infoFiltered": "(filtrado de un total de _MAX_ registros)",
					"sSearch": "Buscar:",
					"oPaginate": {
						"sFirst": "Primero",
						"sLast": "Ultimo",
						"sNext": "Siguiente",
						"sPrevious": "Anterior"
					},
					"sProcessing": "Procesando...",
				}
			});
		});
	</script>

</head>
<body class="cl">

	<% //revisa que el usuario sea cliente
		if(session.getAttribute("tipoUsuario") != "cliente"  )
		{
			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}	

			Cliente cli = null;
			if (session.getAttribute("usuarioLogueado") != null) {
				cli = (Cliente) session.getAttribute("usuarioLogueado");
			}
	%>
	

	<nav class="mask">
		<div class="name-user">
			<%= session.getAttribute("nm_user") != null ? session.getAttribute("nm_user") : "" %>
		</div>
		<ul class="list">
			<li><a href="ServletMovimiento?mostrar=">Transferencia</a></li>
			<li><a href="Cliente_Home.jsp">Historial</a></li>
			<li><a href="Cliente_Prestamo.jsp">Prestamo</a></li>
			<li><a href="Cliente_Perfil.jsp">Perfil</a></li>
		</ul>
	</nav>

	<form action="ServletDescolgable" method="post">
		<input type="submit" name="btn_TCPr" value="Traer Cuentas"> <!--  ABREVIATURA Traer Cliente Home-->
	</form>	
	
	<% 
	ArrayList<Cuenta> cuentas = null;
	if(request.getAttribute("cuentas") != null) {
		cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");
	}
	%>

	<div class="prestamo">
		<div class="pedir_prestamo">
			
			<form action="ServletPrestamos" method="post" class="nuevo-prestamo">
			
				<div class="inputs">
					<label for="capital">Capital:</label>
					<input type="number" id="capital" name="capital" placeholder="Ingrese el capital" min="100" step="100.00" required autofocus="autofocus">			
					
					<label for="meses">Meses:</label>
					<input type="number" id="meses" name="meses" placeholder="Ingrese meses" min="1" step="1" max="12" required value="12" >				
				</div>
	


				<div class="filtrar_cuentas">
					<label>Elige una cuenta:</label>
					<select name="cuenta" id="cuenta">
					<%
						if (cuentas != null) {
						for (Cuenta cuenta : cuentas) {
					%>
					<option value="<%= cuenta.getId() %>"><%= cuenta.getUsuario() %></option>
					<%
						}
					}
					%>
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
			

			<div class="error-message">
				<%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
			</div>
		</div>


		<div class="lista_prestamo">	
		
			<form action="ServletPrestamos" method="post">
				<div class="filtrar_cuentas">
					<label>Elige una cuenta:</label>
					<select name="cuentas" id="cuentas">
					<%	if (cuentas != null) {
						for (Cuenta cuenta : cuentas) {	%>
					<option value="<%= cuenta.getId() %>"><%= cuenta.getUsuario() %></option>
					<%	}
					}	%>
					</select>
				</div>  
				<input type="submit" name="traerPrestamos" value="traer prestamos">
			</form>
			<%

			 ArrayList<Prestamo> prestamos = null;
			 if(request.getAttribute("prestamos") != null) {
				 prestamos = (ArrayList<Prestamo>) request.getAttribute("prestamos");
			 } 
			%>
	<table id="tabla" class="table table-striped table-bordered" style="width:100%; color: black;" border="1">
    	<thead>
        	<tr>
           		<th colspan="5">Historial de Prestamos</th>
        	</tr>
        <tr>
            <th>Prestamo Nro.</th>
            <th>Fecha</th>
            <th>Monto Mensual</th>
            <th>Monto Total</th>
            <th>Pagar</th>
        </tr>
        </thead>
    <tbody>
        <% if(prestamos != null) {
        for (Prestamo prestamo : prestamos) { %>
            <tr>
                <form action="ServletPrestamos" method="post">
                    <td><%= prestamo.getId() %><input type="hidden" name="idPrestamo" value="<%= prestamo.getId() %>"></td>
                    <td><%= prestamo.getFecha() %></td>
                    <td><%= prestamo.getMontoMensual() %></td>
                    <td><%= prestamo.getMontoTotal() %></td>
                    <td><input type="submit" name="pagarCuota" value="Pagar"></td>
                </form>
            </tr>
        <% } 
       	} %>
    </tbody>
</table>


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