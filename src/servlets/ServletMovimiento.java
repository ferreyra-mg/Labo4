package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Movimiento;
import negocio.MovimientoNegocio;
import negocioImpl.MovimientoNegocioImpl;

@WebServlet("/ServletMovimiento")
public class ServletMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletMovimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = null;
		int id=1;
		if(request.getParameter("btn_traerMovimientos") != null)
		{
			traerMovimientos(request,id);
		    rd = request.getRequestDispatcher("/Cliente_Home.jsp");
		    rd.forward(request, response);
		    return;
		}
		
	}

	private void traerMovimientos(HttpServletRequest request, int id) {
        MovimientoNegocio mvNeg = new MovimientoNegocioImpl();
        ArrayList<Movimiento> listMovimientos = mvNeg.traerMovimientos(id);
        request.setAttribute("listMovimientos", listMovimientos);
    }
}
