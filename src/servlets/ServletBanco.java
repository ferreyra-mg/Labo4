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

import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import entidad.Administrador;
import entidad.Cliente;
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
		String usuario = request.getParameter("txt_user");
		String contra1 = request.getParameter("psw1");
		String contra2 = request.getParameter("psw2");
		
		if(!contra1.equals(contra2)) {
			request.setAttribute("msj_error", "Las contrasenias no coinciden");
			rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
			return;
		}
		
		
		Administrador ad = adNeg.logear(usuario, contra1);
		if(ad != null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogueado", ad);
			session.setAttribute("tipoUsuario", "administrador");
			//se logeo el admin
			rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
		}
		 else {
			Cliente cl = clNeg.logear(usuario, contra1);
			if(cl != null) {
				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogueado", cl);
				session.setAttribute("tipoUsuario", "cliente");
				// almaceno el nombre de la primera cuenta
				request.setAttribute("nm_user", cuNeg.obtenerUsuario(cl.getDni()));
				session.setAttribute("nm_user", request.getAttribute("nm_user"));
			//se logeo el cliente
			rd = request.getRequestDispatcher("/Cliente_Home.jsp");
			}
			else {
				request.setAttribute("msj_error", "Usuario y/o contrasenia incorrecta");
				rd = request.getRequestDispatcher("/Login.jsp");
				} 
		}
			
        cargarClientes(request);
		if (rd == null) rd = request.getRequestDispatcher("/Login.jsp");
		rd.forward(request, response);
		
		
		
		
	}
	
    private void cargarClientes(HttpServletRequest request) {
        ClienteDao CliDao = new ClienteDaoImpl();
        ArrayList<Cliente> listaTClientes = CliDao.obtenerTodosLosClientes();
        request.setAttribute("listaTClientes", listaTClientes);
    }

}