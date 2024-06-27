package servlets;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import excepcion.ClienteInvalidoException;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCliente() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int dni = Integer.parseInt(request.getSession().getAttribute("dni").toString());
		if(request.getParameter("dni")!=null) {
			dni = Integer.parseInt(request.getParameter("dni").toString());
		}
		RequestDispatcher rd = null;
		boolean confirmacionInsert = false;
		boolean confirmacionUpdate = false;
		String confirmacionEliminar = request.getParameter("confirmacionEliminar");
		System.out.println(confirmacionEliminar);
		if(request.getParameter("btn_traerClientes") != null)
		{
			cargarClientes(request);
		    rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
		    rd.forward(request, response);
		    return;
		}
		
		ClienteNegocio cliNeg = new ClienteNegocioImpl();


		if(request.getParameter("btnAceptar")!=null) 
		{
				
			
			Cliente cliConsult = cliNeg.obtenerCliente(dni);
			if(dni < 0)
		    {
                //throw new ClienteInvalidoException();
				 request.setAttribute("msj_error", "Dni invalido.");
                 cargarClientes(request);
                 rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
                 rd.forward(request, response);
		    }			
			if(cliConsult.getDni() != 0) 
			{
				request.setAttribute("msj_error", "Ya existe un cliente con ese DNI");
				cargarClientes(request);
		        rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
		        rd.forward(request, response);					
			}
			
			
			  
			int cuil = Integer.parseInt(request.getParameter("cuil").toString());
			if(cuil < 0)
		    {
                //throw new ClienteInvalidoException();
				 request.setAttribute("msj_error", "Cuil invalido.");
                 cargarClientes(request);
                 rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
                 rd.forward(request, response);
		    } 
			
			try {
				String sexoStr = request.getParameter("sex");
				if (sexoStr == null)
			    {
			    	throw new ClienteInvalidoException();
	            }
			}catch(ClienteInvalidoException e)
			{
				request.setAttribute("msj_error", "Debe seleccionar un sexo.");
	            cargarClientes(request);
	            rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
	            rd.forward(request, response);
			}
			
			
		    long telefono = Long.parseLong(request.getParameter("telefono"));
		    if(telefono < 0)
		    {
                //throw new ClienteInvalidoException();
				 request.setAttribute("msj_error", "Nro. telefono invalido.");
                 cargarClientes(request);
                 rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
                 rd.forward(request, response);
		    }
			
			
			String contra1 = request.getParameter("contra1");
			String contra2 = request.getParameter("contra2");
		    
			if(!contra1.equals(contra2)) {
				request.setAttribute("msj_error", "Las contrasenias no coinciden");
				cargarClientes(request);
		        rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
		        rd.forward(request, response);
			}
			String confirmacion = request.getParameter("confirmacion");
			
			if ("true".equals(confirmacion)) {
			Cliente cli = new Cliente();
		        
			try {
				cli.setDni(dni);
				cli.setCuil(request.getParameter("cuil")); // String para cuil
			    cli.setNombre(request.getParameter("nombre")); // String para nombre
			    cli.setApellido(request.getParameter("apellido")); // String para apellido
			    cli.setDireccion(request.getParameter("direccion")); // String para direccion
			    cli.setLocalidad(request.getParameter("localidad")); // String para localidad
			    cli.setProvincia(request.getParameter("provincia")); // String para provincia
			    cli.setCorreoElectronico(request.getParameter("correo")); // String para correoElectronico
			    cli.setTelefono(Integer.parseInt(request.getParameter("telefono"))); // int para telefono
			    cli.setNacionalidad(request.getParameter("Nacionalidad").toString());
			    
			    String fechaNacimientoStr = request.getParameter("fechaNacimiento");
			    java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);
			    cli.setFechaNacimiento(fechaNacimiento);	
			    
			    String sexoStr = request.getParameter("sex");
			    if (sexoStr == null)
			    {
                    throw new ClienteInvalidoException();
                }
			    boolean sexo = Boolean.parseBoolean(sexoStr); // Convierte la cadena "true" o "false" a booleano
			    cli.setSexo(sexo);
			    if(cli.getTelefono() < 0)
			    {
                    throw new ClienteInvalidoException();
			    }
  
			    
			    cli.setContrasena(contra1); // String para contrasena
		        
		        confirmacionInsert = cliNeg.agregarCliente(cli);
				
				
			} catch (ClienteInvalidoException e)
			{
				request.setAttribute("msj_error", "Debe seleccionar un sexo.");
                cargarClientes(request);
                rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
                rd.forward(request, response);
			}
			
			catch (Exception e) {
				request.setAttribute("msj_error", "Error: " + e.getMessage());
			}
			
	        if(confirmacionInsert) {
	        	request.setAttribute("msj_error", "Cliente agregado exitosamente");
	        } else {
	        	request.setAttribute("msj_error", "Error al agregar el cliente");
	        }

        } else {
            request.setAttribute("msj_error", "Acci�n cancelada por el usuario");
        }
	        cargarClientes(request);
	        rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
	        rd.forward(request, response);
	    }
		
		
			if(request.getParameter("btnModificar")!=null) 
			{
				
				if(cliNeg.obtenerCliente(dni)!=null) 
				{
					Cliente cli2 = new Cliente();
					cli2 = cliNeg.obtenerCliente(dni);
					request.setAttribute("clienteModificar", cli2);
				}
			}
			
			
			if(request.getParameter("btnConfirModif")!=null)
			{
				
				Cliente cli = new Cliente();
			        
				try {
					
					cli = cliNeg.obtenerCliente(dni);
				    cli.setNombre(request.getParameter("nombreM")); // String para nombre
				    cli.setApellido(request.getParameter("apellidoM")); // String para apellido
				    String sexoStr = request.getParameter("sexM");
				    boolean sexo = Boolean.parseBoolean(sexoStr); // Convierte la cadena "true" o "false" a booleano
				    cli.setSexo(sexo);
				    cli.setNacionalidad(request.getParameter("nacionalidadM").toString()); // String para nacionalidad
				    				    
				    cli.setDireccion(request.getParameter("direccionM")); // String para direccion
				    cli.setLocalidad(request.getParameter("localidadM")); // String para localidad
				    cli.setProvincia(request.getParameter("provinciaM")); // String para provincia
				    cli.setCorreoElectronico(request.getParameter("correoM")); // String para correoElectronico
				    cli.setTelefono(Integer.parseInt(request.getParameter("telefonoM"))); // int para telefono
				    cli.setContrasena(request.getParameter("contra1M")); // String para contrasena

			        
				    confirmacionUpdate = cliNeg.modificarCliente(cli);
				    request.setAttribute("clienteModificar", null);
					
					} catch (Exception e) {
						e.getMessage();
						}
				 
		        cargarClientes(request);
		        rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
		        rd.forward(request, response);
			}

			
							
				if ("true".equals(confirmacionEliminar)) {
					int dniEliminar = 0;
					dniEliminar = Integer.parseInt(request.getParameter("dniCliente").toString());
					if(cliNeg.obtenerCliente(dniEliminar)!=null)
					{
						System.out.println(dniEliminar);
						boolean confirmDelete = false;
						confirmDelete = cliNeg.eliminarCliente(dniEliminar);
						request.setAttribute("confirmDelete", confirmDelete);
					}
				}
		
		cargarClientes(request);
		rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
		rd.forward(request, response);
	}
	
    private void cargarClientes(HttpServletRequest request) {
        ClienteNegocio cliNeg = new ClienteNegocioImpl();
        ArrayList<Cliente> listaTClientes = cliNeg.obtenerTodosLosClientes();
        request.setAttribute("listaTClientes", listaTClientes);
    }

}
