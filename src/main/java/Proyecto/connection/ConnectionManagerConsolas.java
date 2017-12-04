package Proyecto.connection;

import java.sql.Connection;
import java.util.List;
import Proyecto.ConsolasExamen.*;

public interface ConnectionManagerConsolas {
	
public Connection open(String jdbcUrl);
public void close(Connection conn);
	
void insert(Consolas consolaFormulario);	
public List<Consolas> searchAll();	
Consolas selectByNombre(String nombre);

}

