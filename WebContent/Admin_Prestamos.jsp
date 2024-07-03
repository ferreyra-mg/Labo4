<%@ page import="entidad.Prestamo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

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

	<% //revisa que el usuario sea Admin
		if(session.getAttribute("tipoUsuario") != "administrador"  )
		{
			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}
	%>

	<nav class="mask">
		<div class="name-user">
			<%= session.getAttribute("nm_user") != null ? session.getAttribute("nm_user") : "" %>
		</div>
		<ul class="list">
			<li><a href="Admin_Perfiles.jsp">Perfiles</a></li>
			<li><a href="Admin_Prestamos.jsp">Prestamos</a></li>
			<li><a href="Admin_Estadisticas.jsp">estadisticas</a></li>
			<li><a href="Admin_Cuentas.jsp">Cuentas</a></li>
		</ul>
	</nav>
	<form action="ServletPrestamos" method="Post">
	<div class="filtros-prestamos">
		Capital Min.:
		<input type="number" name="capMin" required>
		<br><br>
		Capital Max.:
		<input type="number" name="capMax" required>
		<br><br>
		<div class="btn-filtrar-prestamos">
			<input type="submit" value="aceptar" name="aceptar-f">
			<input type="submit" value="remover" name="remover-f">
		</div>
		<br><br>
	</div>
	</form>
	
	<% 
	// Parámetros de paginación
    int pageSize = 10; // Número de elementos por página
    int pageNumber = 1; // Número de la página actual
    if (request.getParameter("page") != null) {
        pageNumber = Integer.parseInt(request.getParameter("page"));
    }

    
    
    ArrayList<Prestamo> listaPrestamos = null;
	if(request.getAttribute("listaTPrestamos") != null) {
    listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaTPrestamos");
	} 
    
    // Cálculo de la paginación
    //int totalItems = movimientos.size();
    //int totalPages = (int) Math.ceil((double) totalItems / pageSize);
    //int startIndex = (pageNumber - 1) * pageSize;
    //int endIndex = Math.min(startIndex + pageSize, totalItems);    
	%>
	
	<form action="ServletPrestamos" method="post">
	<input type="submit" name="btn_traerPrestamos" value="Traer Prestamos Pendientes">
	</form>	
	<div class="tr2">
	<table id="tabla" class="table table-striped table-bordered" style="width:100%; color: black;" border="1">
    <tr>
    
    <th>Id Cuenta</th><th>Capital</th><th>Meses</th><th>Valor Cuota</th><th>Aceptar</th><th>Rechazar</th>
    
    </tr>
    <!--  for (MovimientoXTransferencia movimiento : pageItems) {   -->
    <tbody>
    <% if(listaPrestamos != null) {
        for(Prestamo prestamo : listaPrestamos) { %>
    <tr>
    	<form action="ServletPrestamos" method="post">
        	<td> <%=prestamo.getIdCuenta() %><input type="hidden" name="idCuenta" value="<%=prestamo.getIdCuenta() %>">	</td>
        	<td> <%=prestamo.getMontoTotal() %></td>
        	<td> <%=prestamo.getCantMeses() %></td>
       		<td> <%=prestamo.getMontoMensual() %></td>
        	<td> <input type="submit" name="aceptar" value="aceptar"></td>
        	<td> <input type="submit" name="rechazar" value="rechazar"></td>
    	</form>
    </tr>
            <% }
    } %>
    <!-- } --> 
    </tbody>
	</table>
	</div>
	<div class="error-message">
		<%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
	</div>
	
</body>
</html>