<%@ page import="entidad.Cliente"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" http-equiv="width=device-width, initial-scale=1.0" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Admin_Perfiles</title>

<!-- Para usar el datatables -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>


    <script>
    function confirmarEnvio() {
    	if (confirm("¿Está seguro que desea agregar este cliente a la base de datos?")) {
        document.getElementById('confirmacion').value = 'true';
        return true;
    	} else {
        document.getElementById('confirmacion').value = 'false';
        return false;
    	}
    }
    function confirmarEliminacion(form) {
    	if (confirm("¿Está seguro que desea eliminar este cliente a la base de datos?")) {
        form.confirmacionEliminar.value = 'true';
        return true;
    	} else {
    	form.confirmacionEliminar.value = 'false';
        return false;
    	}
    }
    
    </script>
    
    <!-- Para usar el datatables y ponerlo en español-->
    
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


<%
	//esto es para se limpien los inputs cuando se agregue el cliente, ya que si no se agrega el cliente por x motivo, no se limpian los inputs
	String dni = request.getParameter("dni");
    String cuil = request.getParameter("cuil");
    String nombre = request.getParameter("nombre");
    String apellido = request.getParameter("apellido");
    String email = request.getParameter("email");
    String telefono = request.getParameter("telefono");
    String direccion = request.getParameter("direccion");
    String nacionalidad = request.getParameter("nacionalidad");
    String fechaNacimiento = request.getParameter("fechaNacimiento");
    String localidad = request.getParameter("localidad");
    String provincia = request.getParameter("provincia");
    
    String msjError = (String) request.getAttribute("msj_error");
    
    String dniValue = (msjError != null && msjError.equals("Cliente agregado exitosamente")) ? "" : (dni != null ? dni : "");
    String cuilValue = (msjError != null && msjError.equals("Cliente agregado exitosamente")) ? "" : (cuil != null ? cuil : "");
    String nombreValue = (msjError != null && msjError.equals("Cliente agregado exitosamente")) ? "" : (nombre != null ? nombre : "");
    String apellidoValue = (msjError != null && msjError.equals("Cliente agregado exitosamente")) ? "" : (apellido != null ? apellido : "");
    String emailValue = (msjError != null && msjError.equals("Cliente agregado exitosamente")) ? "" : (email != null ? email : "");
    String telefonoValue = (msjError != null && msjError.equals("Cliente agregado exitosamente")) ? "" : (telefono != null ? telefono : "");
    String direccionValue = (msjError != null && msjError.equals("Cliente agregado exitosamente")) ? "" : (direccion != null ? direccion : "");
    String nacionalidadValue = (msjError != null && msjError.equals("Cliente agregado exitosamente")) ? "" : (nacionalidad != null ? nacionalidad : "");
    String fechaNacimientoValue = (msjError != null && msjError.equals("Cliente agregado exitosamente")) ? "" : (fechaNacimiento != null ? fechaNacimiento : "");
    String localidadValue = (msjError != null && msjError.equals("Cliente agregado exitosamente")) ? "" : (localidad != null ? localidad : "");
    String provinciaValue = (msjError != null && msjError.equals("Cliente agregado exitosamente")) ? "" : (provincia != null ? provincia : "");

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


<h1 style="text-align: center; text-decoration: underline;">PERFILES</h1>

<form action="ServletDescolgable" method="post">
	<input type="submit" name="btn_traerPaises" value="Traer Paises">
</form>	

<% 

 ArrayList<String> paises = null;
 if(request.getAttribute("paises") != null) {
	 paises = (ArrayList<String>) request.getAttribute("paises");
 } 
 %>


<div class="tr1">
		<p>Agregar Cliente</p>
		
        <form id="formCliente" action="ServletCliente" method="post" onsubmit= "return confirmarEnvio()">
                
                <input type="hidden" id="confirmacion" name="confirmacion" value="false">
            <div style="display:grid; grid-template-columns: repeat(2, 1fr);">
	            <div>
	            	<label for="dni">DNI:</label>
	            	<input type="number" id="dni" name="dniValue" placeholder="Ingrese DNI" maxlength="8" min="0" value="<%= dniValue  %>" required>
	            </div>
	            
	            <div>
	            	<label for="cuil">CUIL:</label>
	            	<input type="number" id="cuil" name="cuil" placeholder="Ingrese CUIL" maxlength="12" min="0" value="<%= cuilValue %>" required>
	            </div>
	            
	            <div>
	            	<label for="nombre">NOMBRE:</label>
	            	<input type="text" id="nombre" name="nombre" placeholder="Ingrese Nombre" value="<%= nombreValue %>" required>
	           	</div>
	           	
	           	<div>
	            	<label for="apellido">APELLIDO:</label>
	            	<input type="text" id="apellido" name="apellido" placeholder="Ingrese Apellido" value="<%= apellidoValue %>" required>
	            </div>
	            
	            <div>
	            	<label for="sexo" style="display: flex; align-items: center; margin-left: 160px;">SEXO:</label>
	    			<input type="radio" id="sexoM" name="sex" value="true" style="margin-left: 30px;" <%= "true".equals(request.getParameter("sex")) ? "checked" : "" %>>
	    			<label for="sexoM" style="margin-left: 5px; font-weight: normal; font-size: small;">Masculino</label>
	    			<input type="radio" id="sexoF" name="sex" value="false" style="margin-left: 10px;" <%= "false".equals(request.getParameter("sex")) ? "checked" : "" %>>
	    			<label for="sexoF" style="margin-left: 5px; font-weight: normal; font-size: small;">Femenino</label>
	            </div>
	            
	            <div>
	            	<label for="direccion">DIRECCION:</label>
		            <input type="text" id="direccion" name="direccion" placeholder="" value="<%= direccionValue %>" required>
				</div>
	            
	            <div>
	            	<label for="nacionalidad">NACIONALIDAD:</label>
		            <select name="nacionalidad">
		                <%
		                    if (paises != null) {
		                        for (String pais : paises) {
		                %>
		                <option value="<%= pais %>"><%= pais %></option>
		                <%
		                        }
		                    }
		                %>
		            </select>
				</div>
	            
	            <div>
	            	<label for="fechaNacimiento">FECHA NAC.:</label>
	            	<input type="date" id="fechaNacimiento" name="fechaNacimiento" placeholder="Ingresar fecha de nacimiento" value="<%= fechaNacimientoValue %>" required>
	            </div>
	            
	            <div>
	             	<label for="localidad">LOCALIDAD:</label>
	            	<input type="text" id="localidad" name="localidad" placeholder="" value="<%= localidadValue %>" required>
	            </div>
	            
	            <div>
	           		<label for="provincia">PROVINCIA:</label>
	            	<input type="text" id="provincia" name="provincia" placeholder="" value="<%= provinciaValue %>" required>
	            </div>
	            
	            <div>
	            	<label for="correo">CORREO:</label>
	            	<input type="email" id="correo" name="correo" placeholder="" value="<%= emailValue %>" required>
	            </div>
	            
	            <div>
	            	<label for="telefono">TELEFONO:</label>
	           		<input type="text" id="telefono" name="telefono" placeholder="" maxlength="11" value="<%= telefonoValue %>" required>
	            </div>
	        
	            <div>
	            	<label for="contra1">CONTRASEÑA:</label>
	            	<input type="password" id="contra1" name="contra1" placeholder="" required>
	            </div>
            </div>
            
            <input type="submit" name="btnAceptar" class="btn-aceptar" value="Aceptar">
            
            <div class="error-message">
            	<%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
            </div>
        </form>
        
        
</div>
	
<form action="ServletCliente" method="post">
	<input type="submit" name="btn_traerClientes" value="Traer Clientes">
</form>	

<div class="tr2">
 
 <% 

 ArrayList<Cliente> listaClientes = null;
 if(request.getAttribute("listaTClientes") != null) {
     listaClientes = (ArrayList<Cliente>) request.getAttribute("listaTClientes");
 } 
 %>


<table id="tabla" class="table table-striped table-bordered" style="width:100%; color: black;" border="1" >
	<thead>
    	<tr> 
       		<th>DNI</th> 	<th>NOMBRE</th> 	<th>APELLIDO</th> 		<th>ACCIÓN</th> 
    	</tr>
    </thead>
    <tbody>
    <% if(listaClientes != null) {
        for(Cliente cliente : listaClientes) { %>
            <tr> 
                <form action="ServletCliente" method="Post">
                    <td><%=cliente.getDni() %><input type="hidden" name="dniCliente" value="<%=cliente.getDni() %>"> </td>  
                    <td><%=cliente.getNombre() %></td> 
                    <td><%=cliente.getApellido() %></td>
                    <td>
                        <input type="hidden" id="confirmacionEliminar" name="confirmacionEliminar" value="false">
                        <input type="submit" name="btnModificar" value="Modificar">
                        <input type="submit" name="btnEliminar" value="Eliminar" onclick="return confirmarEliminacion(this.form)">
                    </td>
                </form>
            </tr>
        <% }
    } %>
    
</table>




	

</div>

<% Cliente clienteModif = null;
   clienteModif = (Cliente)request.getAttribute("clienteModificar");%>

<%if(clienteModif != null){ %>

<div class="tr3">
		<p>Modificar Cliente</p>
		
        <form action="ServletCliente" method="post" style="display:grid; grid-template-columns: repeat(3, 1fr)">
            
            <label for="nombreM">NOMBRE:
            	<input type="text" id="nombreM" name="nombreM" value="<%= clienteModif.getNombre() %>" required>
           	</label>
           	
            <label for="apellidoM">APELLIDO:
            	<input type="text" id="apellidoM" name="apellidoM" value="<%= clienteModif.getApellido() %>" required>
            </label>
            
            <label for="sexoM" style="display: flex; align-items: center;">SEXO:
    		<input type="radio" id="sexoMM" name="sexM" value="true" style="margin-left: 30px;" <%= clienteModif.getSexo() ? "checked" : "" %>>
    		<label for="sexoMM" style="margin-left: 5px; font-weight: normal; font-size: small;">Masculino</label>
    		<input type="radio" id="sexoFM" name="sexM" value="false" style="margin-left: 10px;" <%= !clienteModif.getSexo() ? "checked" : "" %>>
    		<label for="sexoFM" style="margin-left: 5px; font-weight: normal; font-size: small;">Femenino</label>
			</label>
            
            <label for="direccionM">DIRECCION:
	            <input type="text" id="direccionM" name="direccionM" value="<%= clienteModif.getDireccion() %>" required>
            </label>
            
            <b>NACIONALIDAD:</b>
            	<select name="nacionalidadM">
					<option value="Argentina">Argentina</option>
					<option value="Brasil">Brasil</option>
					<option value="Chile">Chile</option>
				</select>
            
            
             <label for="localidadM">LOCALIDAD:
            	<input type="text" id="localidadM" name="localidadM" value="<%= clienteModif.getLocalidad() %>" required>
            </label>
            
            <label for="provinciaM">PROVINCIA:
            	<input type="text" id="provinciaM" name="provinciaM" value="<%= clienteModif.getProvincia() %>" required>
            </label>
            
            <label for="correoM">CORREO:
            	<input type="email" id="correoM" name="correoM" value="<%= clienteModif.getCorreoElectronico() %>" required>
            </label>
            
            <label for="telefonoM">TELEFONO:
           		<input type="text" id="telefonoM" name="telefonoM" value="<%= clienteModif.getTelefono() %>" required>
            </label>
            <br><br>            
            <label for="contra1M">CONTRASEÑA:
            	<input type="password" id="contra1M" name="contra1M" value="<%= clienteModif.getContrasena() %>" required>
            </label>
            <label for="contra2M">REPETIR CONTRASEÑA:
            	<input type="password" id="contra2M" name="contra2M" value="<%= clienteModif.getContrasena() %>" required>
            </label>
            <br><br>
            <input type="submit" name="btnConfirModif" class="btn-aceptar" value="Confirmar modificacion">
        </form>
</div>
<%} %>

</body>
</html>