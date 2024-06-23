<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Historial</title>

</head>
<body class="cl">

	
	<nav class="mask">
		
		
		<div class="exito-message">
			<%= request.getAttribute("exito_error") != null ? request.getAttribute("exito_error") : "" %>
		</div>
		
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
	<div class="filtrar_cuentas">
		Elige una cuenta: 
		<select name="cuenta" id="cuenta">
	        <option value="1">[cuenta 1]</option>
	        <option value="2">[cuenta 2]</option>
			<option value="3">[cuenta 3]</option>
		</select>
		
		
		<div class="error-message">
			<%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
		</div>
	</div>
	
	
	
	<%
    // Simulamos datos de movimientos para este ejemplo
    List<String[]> movimientos = new ArrayList<String[]>();
    for (int i = 1; i <= 50; i++) {
        movimientos.add(new String[]{"Tipo " + i, "2024-06-" + (i % 30 + 1), "" + (i * 100), "Recibe " + i});
    }

    // Parámetros de paginación
    int pageSize = 10; // Número de elementos por página
    int pageNumber = 1; // Número de la página actual
    if (request.getParameter("page") != null) {
        pageNumber = Integer.parseInt(request.getParameter("page"));
    }

    // Cálculo de la paginación
    int totalItems = movimientos.size();
    int totalPages = (int) Math.ceil((double) totalItems / pageSize);
    int startIndex = (pageNumber - 1) * pageSize;
    int endIndex = Math.min(startIndex + pageSize, totalItems);

    // Sublista para la página actual
    List<String[]> pageItems = movimientos.subList(startIndex, endIndex);
%>
	
	
	<table>
    <tr>
        <th>Tipo de Movimiento</th>
        <th>Fecha</th>
        <th>Cantidad</th>
        <th>Recibe</th>
    </tr>
    <% for (String[] movimiento : pageItems) { %>
    <tr>
        <td><%= movimiento[0] %></td>
        <td><%= movimiento[1] %></td>
        <td><%= movimiento[2] %></td>
        <td><%= movimiento[3] %></td>
    </tr>
    <% } %>
	</table>

		<div class="pagination">
		    <% for (int i = 1; i <= totalPages; i++) { %>
		        <a href="?page=<%= i %>" class="<%= (i == pageNumber) ? "active" : "" %>"><%= i %></a>
		    <% } %>
		</div>
      </body>
</html>