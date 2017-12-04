package Proyecto.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Proyecto.connection.ConnectionManagerConsolas;
import Proyecto.connection.H2ConnectionConsolas;
import Proyecto.ConsolasExamen.*;

public class ListadoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ConnectionManagerConsolas managerConsolas = new H2ConnectionConsolas();
	ConnectionManagerEmrpesas managerEmpresas = new H2ConnectionConsolas();
	ConnectionManagerConsolas managerConsolas = new H2ConnectionConsolas();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Consolas> listAllConsolas = managerConsolas.searchAll();
		List<Empresas> listAllEmpresas= manager.searchAll();
		List<VideoJuegos> listAllVideojuegos = manager.searchAll();
		req.setAttribute("listAllConsolas", listAllConsolas);
		req.setAttribute("listAllEmrpesas", listAllEmpresas);
		req.setAttribute("listAllConsolas", listAllConsolas);
		redirect(req,resp);
	}
	
	
	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listado.jsp");
		dispatcher.forward(req,resp);
	}
}

