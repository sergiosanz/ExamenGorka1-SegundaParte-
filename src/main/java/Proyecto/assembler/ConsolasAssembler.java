package Proyecto.assembler;

import javax.servlet.http.HttpServletRequest;

import Proyecto.ConsolasExamen.Consolas;

public class ConsolasAssembler {
	
	public static Consolas createConsolaFromRequest(HttpServletRequest req) {
		Consolas consola = new Consolas();
		String nombre = req.getParameter("nombre");
		String empresa = req.getParameter("empresa");
		consola.setNombre(nombre);
		consola.setEmpresa(empresa);
		return consola;
	}

}