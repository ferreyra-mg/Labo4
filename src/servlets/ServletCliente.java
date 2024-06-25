package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import entidad.Cliente;

/**
 * Servlet implementation class ServletCliente
 */
@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		RequestDispatcher rd = null;
		boolean confirmacionInsert = false;
		boolean confirmacionUpdate = false;
		String confirmacionEliminar = request.getParameter("confirmacionEliminar");

		if(request.getParameter("btn_traerClientes") != null)
		{
			cargarClientes(request);
		    rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
		    rd.forward(request, response);
		    return;
		}
		
		ClienteDao CliDao = new ClienteDaoImpl();


		if(request.getParameter("btnAceptar")!=null) 
		{
			String confirmacion = request.getParameter("confirmacion");
							
			if ("true".equals(confirmacion)) {
			Cliente cli = new Cliente();
		        
			try {
				
				
				cli.setCuil(request.getParameter("cuil")); // String para cuil
			    cli.setNombre(request.getParameter("nombre")); // String para nombre
			    cli.setApellido(request.getParameter("apellido")); // String para apellido
			    
			    String sexoStr = request.getParameter("sex");
	               if (sexoStr == null) {
	                    request.setAttribute("msj_error", "Debe seleccionar un sexo.");
	                    cargarClientes(request);
	                    rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
	                    rd.forward(request, response);
	                    return;
	                }
			    boolean sexo = Boolean.parseBoolean(sexoStr); // Convierte la cadena "true" o "false" a booleano
			    cli.setSexo(sexo);

			    cli.setNacionalidad(request.getParameter("Nacionalidad").toString());

			    String fechaNacimientoStr = request.getParameter("fechaNacimiento");
			    java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);
			    cli.setFechaNacimiento(fechaNacimiento);
			    
			    
			    cli.setDireccion(request.getParameter("direccion")); // String para direccion
			    cli.setLocalidad(request.getParameter("localidad")); // String para localidad
			    cli.setProvincia(request.getParameter("provincia")); // String para provincia
			    cli.setCorreoElectronico(request.getParameter("correo")); // String para correoElectronico
			    cli.setTelefono(Integer.parseInt(request.getParameter("telefono"))); // int para telefono
			    
				String contra1 = request.getParameter("contra1");
				String contra2 = request.getParameter("contra2");
			    
				if(!contra1.equals(contra2)) {
					request.setAttribute("msj_error", "Las contrasenias no coinciden");
					cargarClientes(request);
			        rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
			        rd.forward(request, response);
				}
				
				
				
				if(CliDao.obtenerCliente(Integer.parseInt(request.getParameter("dni"))) != null) 
				{
					request.setAttribute("msj_error", "Ya existe un cliente con ese DNI");
					cargarClientes(request);
			        rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
			        rd.forward(request, response);					
				}
				cli.setDni(Integer.parseInt(request.getParameter("dni")));
			    cli.setContrasena(contra1); // String para contrasena
		        
		        confirmacionInsert = CliDao.agregarCliente(cli);
		        

				
				
			} catch (Exception e) {
				request.setAttribute("msj_error", "Error: " + e.getMessage());
			}
			
	        if(confirmacionInsert) {
	        	request.setAttribute("msj_error", "Cliente agregado exitosamente");
	        } else {
	        	request.setAttribute("msj_error", "Error al agregar el cliente");
	        }

        } else {
            request.setAttribute("msj_error", "Acción cancelada por el usuario");
        }
			
			
	        cargarClientes(request);
	        rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
	        rd.forward(request, response);
	    }
		
		
			if(request.getParameter("btnModificar")!=null) 
			{
				
				int dni = Integer.parseInt(request.getParameter("dniCliente").toString());
				if(CliDao.obtenerCliente(dni)!=null) 
				{
					Cliente cli2 = new Cliente();
					cli2 = CliDao.obtenerCliente(dni);
					request.setAttribute("clienteModificar", cli2);
				}
			}
			
			
			if(request.getParameter("btnConfirModif")!=null)
			{
				
				Cliente cli = new Cliente();
			        
				try {
					
					cli.setDni(Integer.parseInt(request.getParameter("dniM")));
					cli.setCuil(request.getParameter("cuilM")); // String para cuil
				    cli.setNombre(request.getParameter("nombreM")); // String para nombre
				    cli.setApellido(request.getParameter("apellidoM")); // String para apellido
				    String sexoStr = request.getParameter("sexM");
				    boolean sexo = Boolean.parseBoolean(sexoStr); // Convierte la cadena "true" o "false" a booleano
				    cli.setSexo(sexo);
				    cli.setNacionalidad(request.getParameter("nacionalidadM").toString()); // String para nacionalidad

				    String fechaNacimientoStr = request.getParameter("fechaNacimientoM");
				    java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);
				    cli.setFechaNacimiento(fechaNacimiento);
				    				    
				    cli.setDireccion(request.getParameter("direccionM")); // String para direccion
				    cli.setLocalidad(request.getParameter("localidadM")); // String para localidad
				    cli.setProvincia(request.getParameter("provinciaM")); // String para provincia
				    cli.setCorreoElectronico(request.getParameter("correoM")); // String para correoElectronico
				    cli.setTelefono(Integer.parseInt(request.getParameter("telefonoM"))); // int para telefono
				    cli.setContrasena(request.getParameter("contra1M")); // String para contrasena

			        
				    confirmacionUpdate = CliDao.modificarCliente(cli);
				    request.setAttribute("clienteModificar", null);
					
					} catch (Exception e) {
					// TODO: handle exception
						}
				 
		        cargarClientes(request);
		        rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
		        rd.forward(request, response);
			}
		
					
			
				
				if ("true".equals(confirmacionEliminar)) {
				int dni = Integer.parseInt(request.getParameter("dniCliente").toString());
					if(CliDao.obtenerCliente(dni)!=null) 
					{
						boolean confirmDelete = false;
						confirmDelete = CliDao.eliminarCliente(dni);
						request.setAttribute("confirmDelete", confirmDelete);
					}
				}
			
					
		
		cargarClientes(request);
		rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
		rd.forward(request, response);
	}
	
    private void cargarClientes(HttpServletRequest request) {
        ClienteDao CliDao = new ClienteDaoImpl();
        ArrayList<Cliente> listaTClientes = CliDao.obtenerTodosLosClientes();
        request.setAttribute("listaTClientes", listaTClientes);
    }

}
