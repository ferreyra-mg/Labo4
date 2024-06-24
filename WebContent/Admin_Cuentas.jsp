<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Insert title here</title>
</head>
<body class="cl">
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

	<div class="adm-cuentas">
	
		<div>Cliente: <input type="text" name="dni-cliente"></div>
		<div>Nombre de la cuenta: <input type="text" name="nm-cliente"></div>
		<div>contraseña: <input type="password" name="psw-cliente"></div>
		<div>repita la contraseña: contraseña: <input type="password" name="psw-cliente2"></div>
		<div>CBU: <input type="text" name="cbu-cliente"></div>
		<div>
			Tipo de cuenta:
			<select name="tipo" id="tipo">
			    <option value="1">[tipo 1]</option>
		        <option value="2">[tipo 2]</option>
				<option value="3">[tipo 3]</option>
			</select>
		</div>
		
		<button type="button" name="aceptar" class="c-cuenta">Aceptar</button>
		
	</div>


	<div class="error-message">
		<%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
	</div>
</body>
</html>