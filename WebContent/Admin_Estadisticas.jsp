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

	<div class="estadisticas">Estadisticas</div>
	
    <form action="ServletEstadisticas" method="post">
	    <label for="startDate">Fecha de inicio:</label>
		<input type="date" id="startDate" name="startDate" required>
		<label for="endDate">Fecha de fin:</label>
	    <input type="date" id="endDate" name="endDate" required>
	    <input type="submit" name="generar_datos" value="generar datos">
    </form>
    
    <% 
    //si da error muestra estos datos
    float monto = 0; 
    int cuentas = 0;
    int movimientos = 0;
    int prestamos = 0;
    
    //ahora cuando lee los datos
    if(request.getAttribute("monto") != null) {
        monto = Float.parseFloat(request.getAttribute("monto").toString());
    }
    if(request.getAttribute("cuentas") != null) {
        cuentas = Integer.parseInt(request.getAttribute("cuentas").toString());
    }
    if(request.getAttribute("movimientos") != null) {
        movimientos = Integer.parseInt(request.getAttribute("movimientos").toString());
    }
    if(request.getAttribute("prestamos") != null) {
        prestamos = Integer.parseInt(request.getAttribute("prestamos").toString());
    }	
    %>  
    
    <div>
    	Monto: <%=monto %>
    </div>
    <div>
    	Cuentas: <%=cuentas %>
    </div>
    <div>
    	Movimientos: <%=movimientos %>
    </div>
    <div>
    	Prestamos: <%=prestamos %>
    </div>
    
</body>
</html>