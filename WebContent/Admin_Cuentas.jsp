<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidad.Tipo_Cuenta"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Insert title here</title>
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
			<li><a href="ServletBanco?logout=">Cerrar Sesión</a></li>
			
		</ul>
	</nav>

	<form action="ServletDescolgable" method="post">
		<input type="submit" name="btn_traerTipos" value="Traer Tipos de cuenta">
	</form>	
	
	<% 
	
	 ArrayList<Tipo_Cuenta> tipos = null;
	 if(request.getAttribute("tipos") != null) {
		 tipos = (ArrayList<Tipo_Cuenta>) request.getAttribute("tipos");
	 } 
	 %>

	<div class="adm-cuentas">
		<form action="ServletCuenta" method="post">
           
		<div>DNI Cliente: <input type="text" name="dni_cliente"></div>
		<div>Nombre de la cuenta: <input type="text" name="nm_cuenta"></div>
		<div>CBU: <input type="text" name="cbu-cliente"></div>
		<div>
			Tipo de cuenta:			
			<select name="tipo" id=tipo>
				<% if (tipos != null) {
				for (Tipo_Cuenta tipo : tipos) { %>
	            <option value="<%= tipo.getId() %>"><%= tipo.getDescripcion() %></option>
				<% }
				}	%>
			</select>
		</div>
		
		<input type="submit" name="crearCuenta" class="c-cuenta" value="Aceptar">
		
	</div>


	<div class="error-message">
		<%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
	</div>
	<div class="msj_agrego">
		<%= request.getAttribute("msj_agrego") != null ? request.getAttribute("msj_agrego") : "" %>
	</div>
</body>
</html>