package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.Prestamo;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.PrestamoNegocio;
import negocio.CuotaNegocio;
import negocioImpl.CuotaNegocioImpl;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletPrestamos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("usuarioLogueado") == null) {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		if(request.getParameter("mostrar")!=null) {
		    RequestDispatcher rdCuentas = null;
		    traerCuentas(request);
	        rdCuentas = request.getRequestDispatcher("/Cliente_Prestamo.jsp");
	        rdCuentas.forward(request, response);
        }
		request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Cliente cli = (Cliente) request.getSession().getAttribute("usuarioLogueado");

		PrestamoNegocio preNeg = new PrestamoNegocioImpl();
		
		
		if(request.getParameter("pagarCuota")!=null)
		{
			System.out.println("verificacion 1");
			CuotaNegocio cuotaNeg = new CuotaNegocioImpl();
			System.out.println("verificacion 2");
			int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
			System.out.println("verificacion 3");
			boolean exito = cuotaNeg.pagarCuota(idPrestamo);
			System.out.println("verificacion 4");
			if(exito)
			{
				request.setAttribute("msj_error", "Se realizo el pago de una cuota correctamente");
				request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("msj_error", "hubo un error al pagar la cuota");
				request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
				return;
			}
		}
		
		if(request.getParameter("aceptar-f") != null){
			RequestDispatcher rd = null;
			float minimo = 0;
			float maximo = 0;
			if(request.getParameter("capMin") != null && request.getParameter("capMax") != null)
			{
				minimo = Float.parseFloat(request.getParameter("capMin").toString());
				maximo = Float.parseFloat(request.getParameter("capMax").toString());
				if(maximo < minimo || maximo < 0 || minimo < 0)
				{
					request.setAttribute("msj_error", "no se puede ingresar montos negativos");
					rd = request.getRequestDispatcher("/Admin_Prestamos.jsp");
					rd.forward(request, response);
					return;
				}
				ArrayList<Prestamo> prestamo = preNeg.prestamosXCapital(minimo, maximo);
				request.setAttribute("listaTPrestamos",prestamo );
			}
			else {
				request.setAttribute("msj_error", "no se ingresaron montos");	
			}
			rd = request.getRequestDispatcher("/Admin_Prestamos.jsp");
			rd.forward(request, response);
			return;	
		}
		
		if(request.getParameter("remover-f") != null)
		{
			ArrayList<Prestamo> prestamo = preNeg.traerPendientes();
			request.setAttribute("listaTPrestamos",prestamo );
			prestamosPendientes(request, response);
			return;
		}
			
		if(request.getParameter("btn_traerPrestamos") != null)
		{
			ArrayList<Prestamo> prestamo = preNeg.traerPendientes();
			request.setAttribute("listaTPrestamos",prestamo );
			prestamosPendientes(request, response);
			return;
		}
			
		
		if(request.getParameter("aceptar") != null)
		{
			int idCuenta = 0;
			idCuenta = Integer.parseInt(request.getParameter("idCuenta").toString());
			
			boolean confirmAutoriz = preNeg.autortizacionPrestamo(idCuenta, true);
			
			if(confirmAutoriz){
				request.setAttribute("msj_error","Se autorizo correctamente");
			}
			else{
				request.setAttribute("msj_error","Hubo un error al atorizar el prestamo");
			}
			request.getRequestDispatcher("/Admin_Prestamos.jsp").forward(request, response);
			return;
		}
		if(request.getParameter("rechazar") != null)
		{
			Prestamo prest = new Prestamo();
			int idCuenta = 0;
			idCuenta = Integer.parseInt(request.getParameter("idCuenta").toString());
			
			boolean confirmAutoriz = preNeg.autortizacionPrestamo(idCuenta, false);
			
			if(confirmAutoriz){
				request.setAttribute("msj_error","Se autorizo correctamente");
			}
			else{
				request.setAttribute("msj_error","Hubo un error al atorizar el prestamo");
			}
			request.getRequestDispatcher("/Admin_Prestamos.jsp").forward(request, response);
			return;
		}
		
		
		
		
		
		if (request.getParameter("solicitar") != null) {
			
			final int cta = Integer.parseInt(request.getParameter("cuenta"));
			float monto_solicitado = Float.parseFloat(request.getParameter("capital"));
			int cant_meses = Integer.parseInt(request.getParameter("meses"));
			int id = Integer.parseInt(request.getParameter("cuenta").toString());
			if (cta == 0){
				traerCuentas(request);
				request.setAttribute("msj_error", "Se debe indicar la Cuenta donde se acreditará el Prestamo en caso de ser aprobado.");
				request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
			}
			
			if (monto_solicitado == 0){
				traerCuentas(request);
				request.setAttribute("msj_error", "Se debe indicar un monto a solicitar válido.");
				request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
			}
			
			if (cant_meses == 0){
				traerCuentas(request);
				request.setAttribute("msj_error", "Se debe indicar una cantidad de meses válida.");
				request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
			}
			
			final float INTERES_ANUAL = (float) 0.75;
			final float FACTOR_INTERES_TOTAL = 1 + ((INTERES_ANUAL / 12) * cant_meses);
			
			final float TOTAL_A_PAGAR = monto_solicitado * FACTOR_INTERES_TOTAL;
			final float A_PAGAR_MENSUAL = TOTAL_A_PAGAR / cant_meses;
			
			Prestamo prestamo = new Prestamo(monto_solicitado, cta, cant_meses, A_PAGAR_MENSUAL, TOTAL_A_PAGAR);
			prestamo.setId(id);
			PrestamoNegocio prNeg = new PrestamoNegocioImpl();
			boolean seGrabo = prNeg.grabar(prestamo);
			if (seGrabo) {
				traerCuentas(request);
				request.setAttribute("msj_error", "Solicitud realizada con exito. Ahora su peticion esta siendo revisada por el administrador. Espere pacientemente.");
				request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
				return;
			} else {
				traerCuentas(request);
				request.setAttribute("msj_error", "No se pudo procesar la solicitud del prestamo. Pruebe nuevamente mas tarde.");
				request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
				return;
			}
		}
		if (request.getParameter("traerPrestamos") != null)
		{
			int idCuenta = 0;
			if(request.getAttribute("cuentas") != null)
			{
				idCuenta=Integer.parseInt(request.getAttribute("cuentas").toString());
			}
			ArrayList<Prestamo> prestamos = preNeg.traerPrestamos(idCuenta);
			if (prestamos != null)
			{
				request.setAttribute("prestamos",prestamos);
				request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
				return;
			}
			request.setAttribute("msj_error", "hubo un error al traer prestamos");
			request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
			return;
			
			
		}
		
		
		
		
	}
	
	private void cargarPrestamos(HttpServletRequest request) {
        PrestamoNegocio preNeg = new PrestamoNegocioImpl();
        ArrayList<Prestamo> listaTPrestamos = preNeg.traerPendientes();
        request.setAttribute("listaTPrestamos", listaTPrestamos);
    }

    private void prestamosPendientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	RequestDispatcher rd = null;
    	cargarPrestamos(request);
		rd = request.getRequestDispatcher("/Admin_Prestamos.jsp");
		rd.forward(request, response);
    }

	public void traerCuentas(HttpServletRequest request)
	{
		CuentaNegocio cuNeg = new CuentaNegocioImpl();
		HttpSession session = request.getSession();
		int dni = (int)session.getAttribute("dni");
		ArrayList<Cuenta> cuentas = cuNeg.obtenerTodasLasCuentas(dni);
        request.setAttribute("cuentas", cuentas);
	}
}
