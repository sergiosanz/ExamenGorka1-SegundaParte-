package Proyecto.ConsolasExamen;

import java.util.Date;

public class Empresas {
	
	private String nombreE;
	private Date fechaCreacion;
	
	public String getNombre() {
		return nombreE;	
	}
			
	public void setNombre(String nombreE) {		
		this.nombreE = nombreE;
	}
			
	public Date getfechaCreacion() {		
		return fechaCreacion;		
	}
			
	public void setFechaCreacion(Date fechaCreacion) {		
		this.fechaCreacion = fechaCreacion;	
	}

}
