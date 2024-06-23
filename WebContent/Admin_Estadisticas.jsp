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
		[nombre del usuario]
		<ul class="list">
			<li><a href="Admin_Perfiles.jsp">Perfiles</a></li>
			<li><a href="Admin_Prestamos.jsp">Prestamos</a></li>
			<li><a href="Admin_Estadisticas.jsp">estadisticas</a></li>
			<li><a href="Admin_Cuentas.jsp">Cuentas</a></li>
			
		</ul>
	</nav>

	<div class="estadisticas">Estadisticas</div>
	
	<label for="startDate">Fecha de inicio:</label>
	<input type="date" id="startDate" name="startDate" required>
	<label for="endDate">Fecha de fin:</label>
    <input type="date" id="endDate" name="endDate" required>
    <button type="button" name="generar_datos">Aceptar</button>
    <div>
    	Monto: [VALOR]
    </div>
    <div>
    	Cuentas: [VALOR]
    </div>
    <div>
    	Movimientos: [VALOR]
    </div>
    <div>
    	Transferencias: [VALOR]
    </div>
    <div>
    	Prestamos: [VALOR]
    </div>
    
</body>
</html>