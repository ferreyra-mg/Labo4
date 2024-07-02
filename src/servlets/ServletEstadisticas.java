package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;



@WebServlet("/ServletEstadisticas")
public class ServletEstadisticas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletEstadisticas() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		if(request.getParameter("generar_datos") != null)
		{
			Date inicio = null;
            Date fin = null;
            float monto = -1;
			int cuentas = -1;
			int movimientos = -1;
			int prestamos = -1;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                inicio = dateFormat.parse(request.getParameter("startDate"));
                fin = dateFormat.parse(request.getParameter("endDate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
			
			if(inicio != null && fin !=null)
			{
				CuentaNegocio cuNeg = new CuentaNegocioImpl();
				MovimientoNegocio mvNeg = new MovimientoNegocioImpl();
				PrestamoNegocio prNeg = new PrestamoNegocioImpl();
				monto = mvNeg.obtenerMontoEntre(inicio, fin);
				cuentas = cuNeg.obtenerCuentasEntre(inicio,fin);
				movimientos = mvNeg.obtenerMovimientosEntre(inicio,fin);
				prestamos = prNeg.obtenerPrestamosEntre(inicio,fin);
			}
			
			request.setAttribute("monto", monto);
			request.setAttribute("cuentas", cuentas);
			request.setAttribute("movimientos", movimientos);
			request.setAttribute("prestamos", prestamos);
			
			rd = request.getRequestDispatcher("/Admin_Estadisticas.jsp");
		    rd.forward(request, response);
		    return;
		}
	}

}
