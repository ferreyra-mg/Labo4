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

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id').DataTable();
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
    
    
    
    <% 
        ArrayList<Movimiento> listMovimiento = (ArrayList<Movimiento>) request.getAttribute("listMovimientos");
        
        /*int MOVIMIENTOS_POR_PAGINA = 10;
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
        }*/
     %>

    <table border="1" id="table_id" class="table table-striped table-bordered display" style="width:100%; color:black;">
        <tr> 
            <th>Fecha</th>
            <th>Detalle</th>
            <th>Importe</th>
            <th>Tipo de Movimiento</th>
        </tr>
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

</body>
</html>
