package Proyecto.connection;

import java.sql.Connection;
import java.util.List;
import Proyecto.ConsolasExamen.*;

public interface ConnectionManagerEmpresas {
	
public Connection open(String jdbcUrl);
public void close(Connection conn);
	
void insert(Empresas empresaFormulario);	
public List<Empresas> searchAll();	
Empresas selectByNombreE(String nombreE);


}