package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Prestamo;

/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/prestamos")
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
		
		if (request.getSession().getAttribute("usuarioLogueado") != null) {
			request.getRequestDispatcher("/Cliente_Prestamo.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Cliente cli = (Cliente) request.getSession().getAttribute("usuarioLogueado");
		
		if (request.getParameter("SOLICITAR") != null) {
			
			final int cta = Integer.parseInt(request.getParameter("cuenta"));
			float monto_solicitado = Float.parseFloat(request.getParameter("capital"));
			int cant_meses = Integer.parseInt(request.getParameter("meses"));
			
			final float INTERES_ANUAL = (float) 0.75;
			final float FACTOR_INTERES_TOTAL = 1 + ((INTERES_ANUAL / 12) * cant_meses);
			
			final float TOTAL_A_PAGAR = monto_solicitado * FACTOR_INTERES_TOTAL;
			final float A_PAGAR_MENSUAL = TOTAL_A_PAGAR / cant_meses;
			
			// int id, float prestamo, int idCliente, int numCuenta, int cantCuotas, int cantMeses, boolean pagado, boolean peticion
			Prestamo prestamo = new Prestamo(monto_solicitado, cta, cant_meses, A_PAGAR_MENSUAL, TOTAL_A_PAGAR);
			
			
		}
		
		doGet(request, response);
	}

}
