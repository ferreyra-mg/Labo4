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
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import excepcion.ClienteInvalidoException;
import negocio.ClienteNegocio;
import negocio.LocalidadNegocio;
import negocio.PaisNegocio;
import negocio.ProvinciaNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCliente() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = null;
		ClienteNegocio cliNeg = new ClienteNegocioImpl();
		boolean confirmacionInsert = false;
		boolean confirmacionUpdate = false;
		String confirmacionEliminar = request.getParameter("confirmacionEliminar");
		
		if(request.getParameter("btn_traerInfoP")!=null){
			String var = request.getSession().getAttribute("dni").toString();
			int dniSession = Integer.parseInt(var);
			Cliente clienteSession = null;
			clienteSession = cliNeg.obtenerCliente(dniSession);
			request.setAttribute("clienteSession", clienteSession);
	        rd = request.getRequestDispatcher("/Cliente_Perfil.jsp");
	        rd.forward(request, response);
			
		}

		
		if(request.getParameter("btn_traerClientes") != null)
		{
			clientesXPerfil(request, response);
			return;
		}
		
		


		if(request.getParameter("btnAceptar")!=null) 
		{
			int dni = Integer.parseInt(request.getParameter("dniValue").toString());
			if(request.getParameter("dni")!=null) {
				dni= Integer.parseInt(request.getParameter("dniValue").toString());
			}
			
			Cliente cliConsult = cliNeg.obtenerCliente(dni);
			if(dni < 0)
		    {
				 request.setAttribute("msj_error", "Dni invalido.");
				 clientesXPerfil(request, response);
					return;
		    }			
			if(cliConsult.getDni() != 0) 
			{
				request.setAttribute("msj_error", "Ya existe un cliente con ese DNI");
				clientesXPerfil(request, response);
				return;				
			}
			
			int cuil = Integer.parseInt(request.getParameter("cuil").toString());
			if(cuil < 0)
		    {
				 request.setAttribute("msj_error", "Cuil invalido.");
				 clientesXPerfil(request, response);
					return;
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
				clientesXPerfil(request, response);
				return;
			}
			
			
		    long telefono = Long.parseLong(request.getParameter("telefono"));
		    if(telefono < 0)
		    {
				 request.setAttribute("msj_error", "Nro. telefono invalido.");
				 clientesXPerfil(request, response);
				return;
		    }
			if(request.getParameter("localidad") != null && request.getParameter("provincia")!= null && request.getParameter("nacionalidad")!=null)
			{
				LocalidadNegocio loNeg = new LocalidadNegocioImpl();
				ProvinciaNegocio proNeg = new ProvinciaNegocioImpl();
				PaisNegocio paNeg = new PaisNegocioImpl();
				Localidad lo = loNeg.obtenerLocalidad(Integer.parseInt(request.getParameter("localidad").toString()));
				Provincia pro = proNeg.obtenerProvincia(Integer.parseInt(request.getParameter("provincia").toString()));
				Pais pais = paNeg.obtenerPais(Integer.parseInt(request.getParameter("nacionalidad").toString()));
				
				if(lo.getId_Provincia() != pro.getId() || pro.getId_Pais() != pais.getId())
				{
					request.setAttribute("msj_error", "Localidad, Provincia o Nacionalidad incorrecto");
					clientesXPerfil(request, response);
					return;
				}
			}
			String contra1 = request.getParameter("contra1");
			String confirmacion = request.getParameter("confirmacion");
			
			if ("true".equals(confirmacion)) {
			Cliente cli = new Cliente();
		        
			try {
				cli.setDni(dni);
				cli.setCuil(request.getParameter("cuil").toString());// String para cuil
			    cli.setNombre(request.getParameter("nombre").toString()); // String para nombre
			    cli.setApellido(request.getParameter("apellido").toString()); // String para apellido
			    cli.setDireccion(request.getParameter("direccion").toString()); // String para direccion
			    cli.setLocalidad(request.getParameter("localidad").toString()); // String para localidad
			    cli.setProvincia(request.getParameter("provincia").toString()); // String para provincia
			    cli.setCorreoElectronico(request.getParameter("correo").toString()); // String para correoElectronico
			    cli.setTelefono(Integer.parseInt(request.getParameter("telefono"))); // int para telefono
			    cli.setNacionalidad(request.getParameter("nacionalidad").toString());
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
				clientesXPerfil(request, response);
				return;
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
            request.setAttribute("msj_error", "Acción cancelada por el usuario");
        }
			clientesXPerfil(request, response);
			return;
	    }
		
		int dni = 0;
			if(request.getParameter("btnModificar")!=null) 
			{
				dni = Integer.parseInt(request.getParameter("dniCliente").toString());
				request.getSession().setAttribute("aux", dni);
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
				//int dniMod = Integer.parseInt(request.getAttribute("dniCliente").toString()); 
				try {
					dni = Integer.parseInt(request.getSession().getAttribute("aux").toString());
					System.out.println("aca vale: " + dni);
					cli = cliNeg.obtenerCliente(dni);
					cli.setDni(dni);
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
				 
				clientesXPerfil(request, response);
				return;
			}

			
							
				if ("true".equals(confirmacionEliminar)) {
					int dniEliminar = 0;
					dniEliminar = Integer.parseInt(request.getParameter("dniCliente").toString());
					if(cliNeg.obtenerCliente(dniEliminar)!=null)
					{
						boolean confirmDelete = false;
						confirmDelete = cliNeg.eliminarCliente(dniEliminar);
						request.setAttribute("confirmDelete", confirmDelete);
					}
				}
		
				clientesXPerfil(request, response);
	}
	
    private void cargarClientes(HttpServletRequest request) {
        ClienteNegocio cliNeg = new ClienteNegocioImpl();
        ArrayList<Cliente> listaTClientes = cliNeg.obtenerTodosLosClientes();
        request.setAttribute("listaTClientes", listaTClientes);
    }

    private void clientesXPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	RequestDispatcher rd = null;
    	cargarClientes(request);
		rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
		rd.forward(request, response);
    }
}