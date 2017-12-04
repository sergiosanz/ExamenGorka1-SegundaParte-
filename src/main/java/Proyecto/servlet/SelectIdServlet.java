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

public class SelectIdServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ConnectionManagerConsolas manager = new H2ConnectionConsolas();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = manager.selectByNombre("Daniel");
		req.setAttribute("selectByNombre", user);
		redirect(req,resp);
	}
	
	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/mostrarUser.jsp");
		dispatcher.forward(req,resp);
	}//Este metodo nos redireccionara a la pagina welcome.jsp
	
	

}
