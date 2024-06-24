package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import negocio.TransferenciaNegocio;

/**
 * Servlet implementation class ServletTransferencia
 */
@WebServlet("/ServletTransferencia")
public class ServletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    HttpSession session = request.getSession(false);
		    if (session == null || session.getAttribute("nm_user") == null) {
		        response.sendRedirect("/Login.jsp");
		        return;
		    }

		    String usuarioLogueado = request.getSession().getAttribute("nm_user").toString();
	        String enviarMonto = request.getParameter("enviarMonto");
	        String cbuDestino = request.getParameter("cbuDestino");
	        String cuentaSeleccionada = request.getParameter("cuenta");
	        double monto = Double.parseDouble(request.getParameter("monto"));
	        
	        boolean exito = false;
	        if ("transferirOtroCbu".equals(enviarMonto)) {
	            try {
					exito = TransferenciaNegocio.transferirOtroCbu(usuarioLogueado, cbuDestino, monto);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else if ("transferirPropiaCuenta".equals(enviarMonto)) {
	            int idCuentaDestino = Integer.parseInt(cuentaSeleccionada);
	            try {
					exito = TransferenciaNegocio.transferirPropiaCuenta(usuarioLogueado, idCuentaDestino, monto);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	        if (exito) {
	            response.sendRedirect("/Cliente_Home.jsp");
	        } else {
	            request.setAttribute("msj_error", "Error en la transferencia. Verifique el saldo y los datos ingresados.");
	            request.getRequestDispatcher("/Cliente_Transferencia.jsp").forward(request, response);
	        }
	        
	        
	        
	        
	        
	}
	

}
