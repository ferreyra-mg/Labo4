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
       
    MovimientoNegocio movNeg = new MovimientoNegocioImpl();
    CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	
	
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
		    
		    String usuarioLogueado = request.getSession().getAttribute("usuario").toString();
	        Cuenta cuenta = cuentaNeg.obtenerCuentaxUsuario(usuarioLogueado);
	        String tipoCuentaCbu = request.getParameter("SelecCuenta");
	        
		    //Esto es para transferir de un cbu a otro.
		    if (request.getParameter("enviarMonto")!=null) {
			RequestDispatcher rd = null;
	        String cbuDestino = request.getParameter("cbuDestino");
	        float monto = Float.parseFloat(request.getParameter("monto"));
	        

	        


	        boolean exito = false;
	        
	        	
	        	float saldoActual = movNeg.VerificarSaldoxCuenta(usuarioLogueado, tipoCuentaCbu);
	        	
	        	if(saldoActual < monto) {
	        		request.setAttribute("msjTransferencia", "No tiene saldo suficiente.");
	        		rd = request.getRequestDispatcher("/Cliente_Transferencia.jsp");
	        		rd.forward(request, response);
	        		return;
	        	}
	        	
	        	exito = movNeg.transferirCbu(cuenta, cbuDestino, monto, tipoCuentaCbu);
	        	
	      
	        
	        	if (exito == true) {
			  		request.setAttribute("msjTransferencia", "Dinero transferido.");
			  		rd = request.getRequestDispatcher("/Cliente_Transferencia.jsp");
			  		rd.forward(request, response);
		        } else {
		            request.setAttribute("msjTransferencia", "Error en la transferencia. Verifique los datos ingresados.");
		            request.getRequestDispatcher("/Cliente_Transferencia.jsp").forward(request, response);
		        }
	        
		    }
	        
	        
	        
	        
	        //esto es para transferir de una cuenta a otra.

	        String tipoCuentaEmisora = request.getParameter("cuentaEmisora");
	        String tipoCuentaReceptora = request.getParameter("cuentaReceptora");
	        float montoCuenta = Float.parseFloat(request.getParameter("montoCuenta"));
	        
	        boolean exitoCuentas = false;
	        RequestDispatcher rd2 = null;
	        if(request.getParameter("enviarMontoCuenta")!=null) {
	        	
	        	float saldoxCuenta = movNeg.VerificarSaldoxCuenta(usuarioLogueado, tipoCuentaEmisora);
	        	
	        	if(saldoxCuenta < montoCuenta) {
	        		request.setAttribute("msjTransferenciaCuentas", "No tiene saldo suficiente en la siguiente cuenta: " + tipoCuentaEmisora);
	        		rd2 = request.getRequestDispatcher("/Cliente_Transferencia.jsp");
	        		rd2.forward(request, response);
	        		return;
	        	}
	        	if(tipoCuentaEmisora.equals(tipoCuentaReceptora)) {
	        		request.setAttribute("msjTransferenciaCuentas", "No puede enviarse el dinero a la misma cuenta");
	        		rd2 = request.getRequestDispatcher("/Cliente_Transferencia.jsp");
	        		rd2.forward(request, response);
	        		return;
	        	}
		        
		        
		        String cbuCuentaDestino = movNeg.ObtenerCbu(tipoCuentaReceptora, cuenta);
		        String cbuCuentaEmisor = movNeg.ObtenerCbu(tipoCuentaEmisora, cuenta);
		        
		        if(cbuCuentaDestino != "" && cbuCuentaEmisor != "") {
			        exitoCuentas = movNeg.TransferirEntreCuentas(cuenta, cbuCuentaDestino, cbuCuentaEmisor, montoCuenta);
		        } else {
		            request.setAttribute("msjTransferenciaCuentas", "Error en verificar las cuentas.");
		            request.getRequestDispatcher("/Cliente_Transferencia.jsp").forward(request, response);
		        }
		        
	        }
	        

	        
	        if(exitoCuentas == true) {
	      		request.setAttribute("msjTransferenciaCuentas", "Dinero transferido.");
	      		rd2 = request.getRequestDispatcher("/Cliente_Transferencia.jsp");
	      		rd2.forward(request, response);
	        } else {
	            request.setAttribute("msjTransferenciaCuentas", "Error en mandar el dinero a tu otra cuenta.");
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
