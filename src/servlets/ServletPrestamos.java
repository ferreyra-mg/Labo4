package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuota;
import entidad.Prestamo;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("usuarioLogueado") == null) {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		
		Prestamo prestamo = null;
		
		if (request.getParameter("pagar_cuota_prestamo") != null) {
			int id = Integer.parseInt(request.getParameter("pagar_cuota_prestamo"));
			prestamo = (new PrestamoNegocioImpl()).traerPrestamo(id);
		}
		
		request.setAttribute("prestamo_a_pagar", prestamo);
		
		request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Cliente cli = (Cliente) request.getSession().getAttribute("usuarioLogueado");
		
		if (request.getParameter("solicitar") != null) {
			
			final int cta = Integer.parseInt(request.getParameter("cuenta"));
			float monto_solicitado = Float.parseFloat(request.getParameter("capital"));
			int cant_meses = Integer.parseInt(request.getParameter("meses"));
			
			if (cta == 0){
				request.setAttribute("msj_error", "Se debe indicar la Cuenta donde se acreditar� el Prestamo en caso de ser aprobado.");
				request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
			}
			
			if (monto_solicitado == 0){
				request.setAttribute("msj_error", "Se debe indicar un monto a solicitar v�lido.");
				request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
			}
			
			if (cant_meses == 0){
				request.setAttribute("msj_error", "Se debe indicar una cantidad de meses v�lida.");
				request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
			}
			
			final float INTERES_ANUAL = (float) 0.75;
			final float FACTOR_INTERES_TOTAL = 1 + ((INTERES_ANUAL / 12) * cant_meses);
			
			final float TOTAL_A_PAGAR = monto_solicitado * FACTOR_INTERES_TOTAL;
			final float A_PAGAR_MENSUAL = TOTAL_A_PAGAR / cant_meses;
			
			// int id, float prestamo, int idCliente, int numCuenta, int cantCuotas, int cantMeses, boolean pagado, boolean peticion
			Prestamo prestamo = new Prestamo(monto_solicitado, cta, cant_meses, A_PAGAR_MENSUAL, TOTAL_A_PAGAR);
			
			if (prestamo.solicitar()) {
				request.setAttribute("msj_error", "Solicitud realizada con exito. Ahora su peticion esta siendo revisada por el administrador. Espere pacientemente.");
				request.getRequestDispatcher("/Cliente_Home.jsp").forward(request, response);
			}
			
			request.setAttribute("msj_error", "No se pudo procesar la solicitud del prestamo. Pruebe nuevamente mas tarde.");
		}
		
		if (request.getAttribute("prestamo-id-a-pagar") != null) {
			int idPrestamo = (int) request.getAttribute("prestamo-id-a-pagar");
			float monto = (float) request.getAttribute("cuota");
			
			Cuota cuota = new Cuota(idPrestamo, monto);
			
			if (cuota.grabar()) {
				request.setAttribute("msj_error", "Pago realizado con exito.");
				request.getRequestDispatcher("/Cliente_Home.jsp").forward(request, response);
			}
			request.setAttribute("msj_error", "No se pudo procesar el pago de la cuota. Pruebe nuevamente mas tarde.");
		}
		
		request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
		//doGet(request, response);
	}

}
