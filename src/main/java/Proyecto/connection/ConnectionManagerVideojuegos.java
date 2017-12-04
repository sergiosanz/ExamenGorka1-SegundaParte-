package Proyecto.connection;

import java.sql.Connection;
import java.util.List;
import Proyecto.ConsolasExamen.*;

public interface ConnectionManagerVideojuegos {
	
public Connection open(String jdbcUrl);
public void close(Connection conn);
	
void insert(VideoJuegos videojuegoFormulario);	
public List<VideoJuegos> searchAll();	
VideoJuegos selectByTitulo(String titulo);

}
