<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Insert title here</title>
</head>
<body class="cl">
	<nav class="mask">
		[nombre del usuario]
		<ul class="list">
			<li><a href="Admin_Perfiles.jsp">Perfiles</a></li>
			<li><a href="Admin_Prestamos.jsp">Prestamos</a></li>
			<li><a href="Admin_Estadisticas.jsp">estadisticas</a></li>
			<li><a href="Admin_Cuentas.jsp">Cuentas</a></li>
		</ul>
	</nav>
	
	<div class="filtros-prestamos">
		Capital pedido:
		<input type="number">
		Usuario
		<input type="text">
		<br><br>
		<div class="btn-filtrar-prestamos">
			<button type="button" name="aceptar-f">Aceptar</button>
			<button type="button" name="remover-f">Remover filtros</button>
		</div>
		<br><br>
	</div>
	
	<% 
	// Parámetros de paginación
    int pageSize = 10; // Número de elementos por página
    int pageNumber = 1; // Número de la página actual
    if (request.getParameter("page") != null) {
        pageNumber = Integer.parseInt(request.getParameter("page"));
    }

    // Cálculo de la paginación
    //int totalItems = movimientos.size();
    //int totalPages = (int) Math.ceil((double) totalItems / pageSize);
    //int startIndex = (pageNumber - 1) * pageSize;
    //int endIndex = Math.min(startIndex + pageSize, totalItems);
	%>
	
	<table>
    <tr>
        <th>Usuario</th>
        <th>Capital</th>
        <th>Meses</th>
        <th>Monto a Pagar</th>
        <th>Aceptar</th>
        <th>Rechazar</th>
    </tr>
    <!--  for (MovimientoXTransferencia movimiento : pageItems) {   -->
    <tr>
        <td> [nombre usuario]		</td>
        <td> [capital pedido]		</td>
        <td> [meses en los que va a pagar] 	</td>
        <td> [monto a pagar]  	</td>
        <td> <button type="button" name="aceptar">Aceptar</button> 	</td>
        <td> <button type="button" name="rechazar">Rechazar</button>   	</td>
    </tr>
    <!-- } --> 
</table>
	
	<div class="error-message">
		<%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
	</div>
	
</body>
</html>