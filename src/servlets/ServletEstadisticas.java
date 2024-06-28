package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		System.out.println("no entro");
		if(request.getParameter("generar_datos") != null)
		{
			System.out.println("nada");
			float monto = -1;
			System.out.println("monto");
			int cuentas = -1;
			System.out.println("cuentas");
			int movimientos = -1;
			System.out.println("movimientos");
			int prestamos = -1;
			System.out.println("prestamos");
			
			request.setAttribute("monto", monto);
			System.out.println("monto");
			request.setAttribute("cuentas", cuentas);
			System.out.println("cuentas");
			request.setAttribute("movimientos", movimientos);
			System.out.println("movimientos");
			request.setAttribute("prestamos", prestamos);
			System.out.println("prestamos");
			
			rd = request.getRequestDispatcher("/Admin_Estadisticas.jsp");
		    rd.forward(request, response);
		    return;
		}
	}

}
