package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.PaisNegocio;
import negocioImpl.PaisNegocioImpl;


@WebServlet("/ServletDescolgable")
public class ServletDescolgable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletDescolgable() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		traerPaises(request);
        request.getRequestDispatcher("/Admin_Perfiles.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		
		if(request.getParameter("btn_traerPaises") != null)
		{
			traerPaises(request);
		    rd = request.getRequestDispatcher("/Admin_Perfiles.jsp");
		    rd.forward(request, response);
		    return;
		}
	}

	public void traerPaises(HttpServletRequest request)
	{
		PaisNegocio paisNeg = new PaisNegocioImpl();
        ArrayList<String> paises = paisNeg.traerPaises();
        request.setAttribute("paises", paises);
	}
}
