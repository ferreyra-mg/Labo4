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
import entidad.Movimiento;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

@WebServlet("/ServletMovimiento")
public class ServletMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletMovimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		HttpSession session = request.getSession(false);
		    if (session == null || session.getAttribute("nm_user") == null) {
		        response.sendRedirect("/Login.jsp");
		        return;
		    }
		    RequestDispatcher rd = null;
		    String usuarioLogueado = request.getSession().getAttribute("usuario").toString();
	        String cbuDestino = request.getParameter("cbuDestino");
	        float monto = Float.parseFloat(request.getParameter("monto"));
	        
	        MovimientoNegocio movNeg = new MovimientoNegocioImpl();
	        CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	        
	        Cuenta cuenta = cuentaNeg.obtenerCuentaxUsuario(usuarioLogueado);

	        boolean exito = false;
	        if (request.getParameter("enviarMonto")!=null) {
	        	
	        	float saldoActual = movNeg.VerificarSaldo(usuarioLogueado);
	        	
	        	if(saldoActual < monto) {
	        		request.setAttribute("msjTransferencia", "No tiene saldo suficiente.");
	        		rd = request.getRequestDispatcher("/Cliente_Transferencia.jsp");
	        		rd.forward(request, response);
	        		return;
	        	}
	        	
	        	exito = movNeg.transferirCbu(cuenta, cbuDestino, monto);
	        	
	        }
	        
	        if (exito == true) {
  		request.setAttribute("msjTransferencia", "Dinero transferido.");
  		rd = request.getRequestDispatcher("/Cliente_Transferencia.jsp");
  		rd.forward(request, response);
	        } else {
	            request.setAttribute("msjTransferencia", "Error en la transferencia. Verifique los datos ingresados.");
	            request.getRequestDispatcher("/Cliente_Transferencia.jsp").forward(request, response);
	        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = null;
		String idS = request.getAttribute("cuenta").toString();
		
		int id = Integer.parseInt(idS);
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
