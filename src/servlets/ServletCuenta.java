package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import excepcion.CuentaInvalidaException;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;

@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		
		if (request.getParameter("crearCuenta") != null) {
		    // Traigo los obj
		    String dniStr = request.getParameter("dni_cliente");
		    String nombre = request.getParameter("nm_cuenta").toString();
		    String cbu = request.getParameter("cbu-cliente").toString();
		    String tipo = "";

		    try {
		        tipo = request.getParameter("tipo").toString();
		        if(tipo == null && tipo.isEmpty())
		        {
		        	throw new CuentaInvalidaException();
		        }
		        // Realizo las validaciones
		        if (dniStr != null && !dniStr.isEmpty() && Integer.parseInt(dniStr) > 0 &&
		            nombre != null && !nombre.isEmpty() &&
		            cbu != null && !cbu.isEmpty() &&
		            tipo != null && !tipo.isEmpty()) {
		        	
		            // Reviso que el dni exista
		            int dni = Integer.parseInt(dniStr);
		            ClienteNegocio clNeg = new ClienteNegocioImpl();
		            if (clNeg.existeDNI(dni)) {
		            	
		                // Reviso que tenga menos de 3 cuentas
		                CuentaNegocio cuNeg = new CuentaNegocioImpl();
		                int nCuentas = cuNeg.numeroCuentas(dni);
		                if (nCuentas < 3) {
		                	
		                    // Asigno variables
		                    Cuenta aux = new Cuenta();
		                    aux.setUsuario(nombre);
		                    aux.setDni(dni);
		                    aux.setCBU(cbu);
		                    aux.setCreacion(new Date());
		                    aux.setTipo(tipo);
		                    aux.setSaldo(10000);
		                    aux.setEstado(true);
		                    boolean seAgrego = cuNeg.crearCuenta(aux);
		                    
		                    // Mensaje aclaratorio OK y sale
		                    if (seAgrego) {
		                        request.setAttribute("msj_agrego", "La cuenta se creo con exito");
		                        rd = request.getRequestDispatcher("/Admin_Cuentas.jsp");
		                        rd.forward(request, response);
		                        return;
		                    }
		                }
		            }
		        }
		        request.setAttribute("msj_error", "No se pudo crear la cuenta");
		        rd = request.getRequestDispatcher("/Admin_Cuentas.jsp");
		        rd.forward(request, response);

		    }catch (CuentaInvalidaException e) {
		        request.setAttribute("msj_error", "Debe seleccionar un tipo valido.");
		        rd = request.getRequestDispatcher("/Admin_Cuentas.jsp");
		        rd.forward(request, response);

		    } catch (Exception e) {
		        request.setAttribute("msj_error", "Ocurrio un error inesperado");
		        rd = request.getRequestDispatcher("/Admin_Cuentas.jsp");
		        rd.forward(request, response);
		    }
		}

	}

}
