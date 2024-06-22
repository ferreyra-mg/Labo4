<%@ page import="entidad.Cliente"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Admin_Perfiles</title>
</head>
<body class="cl">


<%
    HttpSession Session = request.getSession(false);
    if (Session == null || Session.getAttribute("usuarioLogueado") == null || !"administrador".equals(Session.getAttribute("tipoUsuario"))) {
        response.sendRedirect("Cliente_Home.jsp");
        return;
    }
%>



<nav class="mask">
		[nombre del usuario]
		<ul class="list">
			<li><a href="Admin_Perfiles.jsp">Perfiles</a></li>
			<li><a href="">Prestamos</a></li>
			<li><a href="">estadisticas</a></li>
			<li><a href="">Movimientos</a></li>
		</ul>
	</nav>


<h1 style="text-align: center; text-decoration: underline;">PERFILES</h1>

<div class="tr1">
		<p>Agregar Cliente</p>
		
        <form action="ServletCliente" method="post" style="display:grid; grid-template-columns: repeat(3, 1fr)">
                
            <label for="dni">DNI:
            	<input type="text" id="dni" name="dni" placeholder="Ingrese DNI">
            </label>
            
            <label for="cuil">CUIL:
            	<input type="text" id="cuil" name="cuil" placeholder="Ingrese CUIL">
            </label>
            
            <label for="nombre">NOMBRE:
            	<input type="text" id="nombre" name="nombre" placeholder="Ingrese Nombre">
           	</label>
           	
            <label for="apellido">APELLIDO:
            	<input type="text" id="apellido" name="apellido" placeholder="Ingrese Apellido">
            </label>
            
            <label for="sexo" style="display: flex; align-items: center; margin-left: 160px;">SEXO:
    			<input type="radio" id="sexoM" name="sex" value="true" style="margin-left: 30px;">
    			<label for="sexoM" style="margin-left: 5px; font-weight: normal; font-size: small;">Masculino</label>
    			<input type="radio" id="sexoF" name="sex" value="false" style="margin-left: 10px;">
    			<label for="sexoF" style="margin-left: 5px; font-weight: normal; font-size: small;">Femenino</label>
			</label>
            
            <label for="direccion">DIRECCION:
	            <input type="text" id="direccion" name="direccion" placeholder="">
            </label>
            
            <label for="nacionalidad">NACIONALIDAD:
            	<input type="text" id="nacionalidad" name="nacionalidad" placeholder="Ingresar nacionalidad">
            </label>
            
            <label for="fechaNacimiento">FECHA NAC.:
            	<input type="date" id="fechaNacimiento" name="fechaNacimiento" placeholder="Ingresar fecha de nacimiento">
            </label>
            
            
             <label for="localidad">LOCALIDAD:
            	<input type="text" id="localidad" name="localidad" placeholder="">
            </label>
            
            <label for="provincia">PROVINCIA:
            	<input type="text" id="provincia" name="provincia" placeholder="">
            </label>
            
            <label for="correo">CORREO:
            	<input type="email" id="correo" name="correo" placeholder="">
            </label>
            
            <label for="telefono">TELEFONO:
           		<input type="text" id="telefono" name="telefono" placeholder="">
            </label>
  
            
            <br><br>            
            
            <label for="contra1">CONTRASEÑA:
            	<input type="password" id="contra1" name="contra1" placeholder="">
            </label>
            <label for="contra2">REPETIR CONTRASEÑA:
            	<input type="password" id="contra2" name="contra2" placeholder="">
            </label>
            <br><br>
            <input type="submit" name="btnAceptar" class="btn-aceptar" value="Aceptar">
        </form>
</div>
		
		
<div class="tr2">
 
 <% 
/*  	ArrayList<Cliente>listaClientes = null;
 	if(request.getAttribute("listaTClientes")!=null)
 	{
		listaClientes = (ArrayList<Cliente>) request.getAttribute("listaTClientes");
 	}  */
 	
 	ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) request.getAttribute("listaTClientes");
 	
 	%>
 	
 	
 	
 
 
 
 <table border="1" class="display">
 	<tr> 
 		<th>DNI</th> 
 		<th>NOMBRE</th> 
 		<th>APELLIDO</th> 
 	</tr>
 	
 	<%if(listaClientes != null){
 	for(Cliente cliente : listaClientes)
 		{ %>
 	<tr> 
 		<td><%=cliente.getDni() %></td>  
 		<td><%=cliente.getNombre() %></td> 
 		<td><%=cliente.getApellido() %></td> 
 	</tr>
 	<% } 
 	}%>
 </table>
	

</div>

</body>
</html>