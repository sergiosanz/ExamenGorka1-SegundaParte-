package Proyecto.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Proyecto.connection.ConnectionManagerConsolas;
import Proyecto.connection.ConnectionManagerEmpresas;
import Proyecto.connection.ConnectionManagerVideojuegos;
import Proyecto.connection.H2ConnectionConsolas;
import Proyecto.connection.H2ConnectionEmpresa;
import Proyecto.connection.H2ConnectionVideojuegos;
import Proyecto.ConsolasExamen.*;

public class SelectIdServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ConnectionManagerConsolas managerConsolas = new H2ConnectionConsolas();
	ConnectionManagerEmpresas managerEmpresas = new H2ConnectionEmpresa();
	ConnectionManagerVideojuegos managerVideojuegos = new H2ConnectionVideojuegos();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Consolas consola = managerConsolas.selectByNombre("Nintendo3DSi");
		Empresas empresa = managerEmpresas.selectByNombreE("Nintendo");
		VideoJuegos videojuego = managerVideojuegos.selectByTitulo("Zelda : Ocarina of time 3D");
		req.setAttribute("selectByNombre", consola);
		req.setAttribute("selectByNombreE", empresa);
		req.setAttribute("selectByTitulo", videojuego);
		redirect(req,resp);
	}
	
	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/mostrarUser.jsp");
		dispatcher.forward(req,resp);
	}
	//Este metodo nos redireccionara a la pagina welcome.jsp		
}
