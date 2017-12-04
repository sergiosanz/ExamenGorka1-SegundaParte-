package Proyecto.ConsolasExamen;

import java.util.Date;

public class VideoJuegos {
	
	private String titulo;
	private String edadRecomendada;
	private Date fechaLanzamiento;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEdadRecomendada() {
		return edadRecomendada;
	}
	public void setEdadRecomendada(String edadRecomendada) {
		this.edadRecomendada = edadRecomendada;
	}
	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}
	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

}
