<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Movimiento" %>
<%@ page import="entidad.Cuenta" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Historial</title>

<!-- Para usar el datatables -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

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

    <% //revisa que el usuario sea cliente
        if (!"cliente".equals(session.getAttribute("tipoUsuario"))) {
            response.sendRedirect("/Login.jsp");
            return;
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
            <li><a href="ServletMovimiento?mostrar=">Transferencia</a></li>
            <li><a href="Cliente_Home.jsp">Historial</a></li>
            <li><a href="Cliente_Prestamo.jsp">Prestamo</a></li>
            <li><a href="Cliente_Perfil.jsp">Perfil</a></li>
        </ul>
    </nav>
    
    <form action="ServletDescolgable" method="post">
		<input type="submit" name="btn_TCH" value="Traer Cuentas"> <!--  ABREVIATURA Traer Cliente Home-->
	</form>	
    
    <% 
        ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");
     %>
    <form action="ServletMovimiento" method="post">
        <div class="filtrar_cuentas">
            Elige una cuenta: 
            <select name="cuenta" id="cuenta">
                <% if (cuentas != null) {
                    for (Cuenta cuenta : cuentas) { %>
                        <option value="<%= cuenta.getId() %>"><%= cuenta.getUsuario() %></option>
                <%    }
                    }
                %>
            </select>
            
            <div class="error-message">
                <%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
            </div>
        </div>
        
        <input type="submit" name="btn_traerMovimientos" value="Traer movimientos">
    </form>
    
    
<div class="tr2">
    <% 
        ArrayList<Movimiento> listMovimiento = (ArrayList<Movimiento>) request.getAttribute("listMovimientos");
     %>
     

    <table border="1" id="tabla" class="table table-striped table-bordered" style="width:100%; color:black;">
    	<thead>
	        <tr> 
	            <th>Fecha</th>
	            <th>Detalle</th>
	            <th>Importe</th>
	            <th>Tipo de Movimiento</th>
	        </tr>
        </thead>
        <tbody>
            <% if (listMovimiento != null) { %>
                <% for (Movimiento mv : listMovimiento) { %>
                    <tr> 
                        <td><%= mv.getFecha() %></td>
                        <td><%= mv.getDetalle() %></td>
                        <td><%= mv.getImporte() %></td>
                        <td><%= mv.getMovimiento() %></td>
                    </tr>
            <%    }
                }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
