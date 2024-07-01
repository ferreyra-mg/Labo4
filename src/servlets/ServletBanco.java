package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import entidad.Administrador;
import entidad.Cliente;
import entidad.Cuenta;
import negocio.AdministradorNegocio;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocioImpl.AdministradorNegocioImpl;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;


@WebServlet("/ServletBanco")
public class ServletBanco extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClienteNegocio clNeg = new ClienteNegocioImpl();
	private CuentaNegocio cuNeg = new CuentaNegocioImpl();
	private AdministradorNegocio adNeg = new AdministradorNegocioImpl();
       
    public ServletBanco() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		
		if(request.getParameter("btn_logear")!=null)
		{
			String usuario = request.getParameter("txt_user");
			String contra1 = request.getParameter("psw1");
			String contra2 = request.getParameter("psw2");

			if(!contra1.equals(contra2))
			{
				request.setAttribute("msj_error", "Las contraseñas no coinciden");
				rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
				return;
			}
			
			// logear al admin
			Administrador ad = adNeg.logear(usuario, contra1);
			if(ad != null)
			{
				session.setAttribute("usuarioLogueado", ad);  //TODO: borrar
				session.setAttribute("tipoUsuario", "administrador");
				session.setAttribute("nm_user", usuario);
				rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
				rd.forward(request, response);
				return;
			}
			Cliente cl = clNeg.logear(usuario, contra1);
			if(cl != null) {
				session.setAttribute("usuarioLogueado", cl);
                session.setAttribute("tipoUsuario", "cliente");
                String aux = cuNeg.obtenerUsuario( cl.getDni() ).getUsuario();
                request.getSession().setAttribute("usuario", aux);
                session.setAttribute("nm_user", aux);
                session.setAttribute("dni", cl.getDni());
                rd = request.getRequestDispatcher("/Cliente_Home.jsp");
                rd.forward(request, response);
				return;
			}
		}
		rd = request.getRequestDispatcher("/Login.jsp");
		request.setAttribute("msj_error", "Usuario y/o contraseña incorrecta");
		rd.forward(request, response);
	}
}
