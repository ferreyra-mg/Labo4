package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

@WebServlet("/ServletTransferencia")
public class ServletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    public ServletTransferencia() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	

}
