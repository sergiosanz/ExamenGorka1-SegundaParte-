package Proyecto.assembler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import Proyecto.ConsolasExamen.VideoJuegos;

public class VideojuegosAssembler {
	
	public static VideoJuegos createVideojuegoFromRequest(HttpServletRequest req) {
		VideoJuegos videojuego = new VideoJuegos();
		String titulo = req.getParameter("titulo");
		String edadRecomendada = req.getParameter("edadRecomendada");
		Date fechaLanzamiento = req.getParameter("fechaLanzamiento");
		videojuego.setTitulo(titulo);
		videojuego.setEdadRecomendada(edadRecomendada);
		videojuego.setFechaLanzamiento(fechaLanzamiento);
		return videojuego;
	}

}