package Proyecto.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Proyecto.connection.ConnectionManagerConsolas;
import Proyecto.connection.ConnectionManagerVideojuegos;
import Proyecto.connection.ConnectionManagerEmpresas;
import Proyecto.connection.H2ConnectionConsolas;
import Proyecto.connection.H2ConnectionEmpresa;
import Proyecto.connection.H2ConnectionVideojuegos;
import Proyecto.ConsolasExamen.*;

public class ListadoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ConnectionManagerConsolas managerConsolas = new H2ConnectionConsolas();
	ConnectionManagerEmpresas managerEmpresas = new H2ConnectionEmpresa();
	ConnectionManagerVideojuegos managerVideojuegos = new H2ConnectionVideojuegos();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Consolas> listAllConsolas = managerConsolas.searchAll();
		List<Empresas> listAllEmpresas= managerEmpresas.searchAll();
		List<VideoJuegos> listAllVideojuegos = managerVideojuegos.searchAll();
		req.setAttribute("listAllConsolas", listAllConsolas);
		req.setAttribute("listAllEmrpesas", listAllEmpresas);
		req.setAttribute("listAllVideojuegos", listAllVideojuegos);
		redirect(req,resp);
	}
	
	
	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listado.jsp");
		dispatcher.forward(req,resp);
	}
}

