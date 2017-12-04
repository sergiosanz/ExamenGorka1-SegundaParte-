package Proyecto.assembler;

import javax.servlet.http.HttpServletRequest;
import Proyecto.ConsolasExamen.Empresas;

public class EmpresasAssembler {
	
	public static Empresas createEmpresaFromRequest(HttpServletRequest req) {
		Empresas empresa = new Empresas();
		String nombreE = req.getParameter("nombreE");
		String fechaCreacion = req.getParameter("fechaCreacion");
		empresa.setNombre(nombreE);
		empresa.setFechaCreacion(fechaCreacion);
		return empresa;
	}

}
