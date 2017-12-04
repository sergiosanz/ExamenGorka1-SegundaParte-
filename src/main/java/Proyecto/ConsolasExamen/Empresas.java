package Proyecto.ConsolasExamen;

import java.util.Date;

public class Empresas {
	
	private String nombre;
	private Date fechaCreacion;
	
	public String getNombre() {
		return nombre;	
	}
			
	public void setNombre(String nombre) {		
		this.nombre = nombre;
	}
			
	public Date getfechaCreacion() {		
		return fechaCreacion;		
	}
			
	public void setFechaCreacion(Date fechaCreacion) {		
		this.fechaCreacion = fechaCreacion;	
	}

}
