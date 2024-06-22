<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Transferencia</title>
</head>
<body class="cl">

	<nav class="mask">
		[nombre del usuario]
		<ul class="list">
			<li><a href="Cliente_Transferencia.jsp">Transferencia</a></li>
			<li><a href="Cliente_Home.jsp">Historial</a></li>
			<li><a href="prestamos">Prestamo</a></li>
			<li><a href="Cliente_Perfil.jsp">Perfil</a></li>
		</ul>
	</nav>
	
	<div class="transferencia">
		<div class="tr1">
			<p>Transferir</p>
			<label for="cbu">CBU:</label>
        	<input type="text" id="cbu" placeholder="Ingrese CBU">
        	<br><br>
        	<label for="monto1">Monto:</label>
        	<input type="number" id="monto_1" placeholder="Ingrese el Monto">
        	<br><br>
        	<button class="btn-aceptar">Aceptar</button>
		</div>

		<div class="tr2">
			<p>Transferir a tus cuentas</p>
			<div class="filtrar_cuentas">
				<label>Elige una cuenta:</label> 
				<select name="cuenta" id="cuenta">
			        <option value="1">[cuenta 1]</option>
			        <option value="2">[cuenta 2]</option>
					<option value="3">[cuenta 3]</option>
				</select>
			</div>

        	<label for="monto2">Monto:</label>
        	<input type="number" id="monto_2" placeholder="Ingrese el Monto">
        	<br><br>
        	<button class="btn-aceptar">Aceptar</button>
			
		</div>
		<div class="tr3">
			<div class="error-message">
				<%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
			</div>
		</div>
		<div class="tr4">
			<div class="error-message">
				<%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
			</div>
		</div>
	</div>

	

</body>
</html>