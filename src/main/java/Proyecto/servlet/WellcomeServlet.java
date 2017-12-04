package Proyecto.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Proyecto.assembler.ConsolasAssembler;
import Proyecto.assembler.EmpresasAssembler;
import Proyecto.assembler.VideojuegosAssembler;
import Proyecto.connection.ConnectionManagerConsolas;
import Proyecto.connection.ConnectionManagerEmpresas;
import Proyecto.connection.ConnectionManagerVideojuegos;
import Proyecto.connection.H2ConnectionConsolas;
import Proyecto.connection.H2ConnectionEmpresa;
import Proyecto.connection.H2ConnectionVideojuegos;
import Proyecto.ConsolasExamen.*;

public class WellcomeServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ConsolasAssembler consolaAssembler = new ConsolasAssembler();
	EmpresasAssembler empresaAssembler = new EmpresasAssembler();
	VideojuegosAssembler videojuegosAssembler = new VideojuegosAssembler();
	ConnectionManagerConsolas managerConsolas = new H2ConnectionConsolas();
	ConnectionManagerEmpresas managerEmpresas = new H2ConnectionEmpresa();
	ConnectionManagerVideojuegos managerVideojuegos = new H2ConnectionVideojuegos();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req,resp);
	}//Metodo que nos permite enviar datos al servlet
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}//Metodo que nos permite obtener datos del servlet
	
	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Consolas consola = ConsolasAssembler.createConsolaFromRequest(req);	
		Empresas empresa = EmpresasAssembler.createEmpresaFromRequest(req);
		VideoJuegos videojuego = VideojuegosAssembler.createVideojuegoFromRequest(req);
		managerConsolas.insert(consola);
		managerEmpresas.insert(empresa);
		managerVideojuegos.insert(videojuego);
		
		redirect(req,resp);
	}//Este metodo nos permite realizar la accion, en este caso la de ejecutar el metodo redirect() para redirigirnos a una pagina.
	
	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome.jsp");
		dispatcher.forward(req,resp);
	}//Este metodo nos redireccionara a la pagina welcome.jsp
	

}

