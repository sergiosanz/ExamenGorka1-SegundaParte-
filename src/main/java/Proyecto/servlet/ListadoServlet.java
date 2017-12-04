package Proyecto.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Proyecto.connection.ConnectionManagerConsolas;
import Proyecto.connection.H2Connection;
import Proyecto.ConsolasExamen.*;

public class ListadoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ConnectionManagerConsolas manager = new H2Connection();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Consolas> listAllConsolas = manager.searchAll();
		List<Empresas> listAllEmpresas= manager.searchAll();
		List<VideoJuegos> listAllVideojuegos = manager.searchAll();
		req.setAttribute("listAllUsers", listAllUsers);
		redirect(req,resp);
	}
	
	
	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listado.jsp");
		dispatcher.forward(req,resp);
	}
}

