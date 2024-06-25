<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Movimiento" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Historial</title>

</head>
<body class="cl">

	<% //revisa que el usuario sea cliente
		if(session.getAttribute("tipoUsuario") != "cliente"  )
		{
			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}
	%>
	
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
	
	<form action="ServletMovimiento" method="post">
		<input type="submit" name="btn_traerMovimientos" value="Traer movimientos">
	</form>
	
 <% 

 ArrayList<Movimiento> listMovimiento = null;
 if(request.getAttribute("listMovimientos") != null) {
	 listMovimiento = (ArrayList<Movimiento>) request.getAttribute("listMovimientos");
 } 
 
 int MOVIMIENTOS_POR_PAGINA = 10;
 int totalMovimientos = (listMovimiento != null) ? listMovimiento.size() : 0;
 int totalPages = (int) Math.ceil((double) totalMovimientos / MOVIMIENTOS_POR_PAGINA);
 int pageNumber = 1;

 if (request.getParameter("page") != null) {
     pageNumber = Integer.parseInt(request.getParameter("page"));
 }

 int start = (pageNumber - 1) * MOVIMIENTOS_POR_PAGINA;
 int end = Math.min(start + MOVIMIENTOS_POR_PAGINA, totalMovimientos);
 ArrayList<Movimiento> paginatedList = new ArrayList<>();

 if (listMovimiento != null) {
     paginatedList = new ArrayList<>(listMovimiento.subList(start, end));
 }
 %>


<table border="1" id="tabla" class="table table-striped table-bordered" style="width:100%">
    <tr> 
        <th>Fecha</th>
        <th>Detalle</th>
        <th>Importe</th>
        <th>Tipo de Movimiento</th>
    </tr>
    <tbody>
    <% if(listMovimiento != null) {
        for(Movimiento mv : listMovimiento) { %>
            <tr> 
                <td><%= mv.getFecha() %></td>
		        <td><%= mv.getDetalle() %></td>
		        <td><%= mv.getImporte() %></td>
		        <td><%= mv.getMovimiento() %></td>
            </tr>
        <% }
    } %>
    </tbody>
</table>

		<div class="pagination">
		    <% for (int i = 1; i <= totalPages; i++) { %>
            <form action="Cliente_Home.jsp" method="post" style="display:inline;">
                <input type="hidden" name="page" value="<%= i %>">
                <input type="submit" value="<%= i %>" class="<%= (i == pageNumber) ? "active" : "" %>">
            </form>
        <% } %>
		</div>
      </body>
</html>