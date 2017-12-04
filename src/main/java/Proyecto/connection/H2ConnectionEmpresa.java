package Proyecto.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Proyecto.ConsolasExamen.*;


public class H2ConnectionEmpresa implements ConnectionManagerEmpresas {

private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";

	public void insert(Empresas emrpesasFormulario) {

	Connection conn = open(jdbcUrl);
	PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO EMPRESAS (nombre, fechaCreacion)" + "VALUES (?, ?)");
			preparedStatement.setString(1, emrpesasFormulario.getNombre());
			preparedStatement.setString(2, emrpesasFormulario.getfechaCreacion());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}
		close(conn);
	}

	public List<Empresas> searchAll() {
		
		List<Empresas> listEmrpesas= new ArrayList<Empresas>();
			Connection conn = open(jdbcUrl);
			ResultSet resultSet = null;
			PreparedStatement prepareStatement = null;
			try {
				prepareStatement = conn.prepareStatement("SELECT * FROM CONSOLAS");
				resultSet = prepareStatement.executeQuery();
					while(resultSet.next()){
						Empresas empresaInDatabase = new Empresas();
						empresaInDatabase.setNombre(resultSet.getString(1));
						empresaInDatabase.setFechaCreacion(resultSet.getString(2));
						listEmrpesas.add(empresaInDatabase);
					}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				close(resultSet);
				close(prepareStatement);
			}
			close(conn);
			return listEmrpesas;
		}
	
	public Empresas selectByNombreE(String nombre) {

		Empresas empresa = new Empresas();
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM EMPRESAS WHERE nombre = ?");
			preparedStatement.setString(1, nombre);
			resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
			empresa.setNombre(resultSet.getString("nombre"));
			empresa.setFechaCreacion(resultSet.getString("fechaCreacion"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet);
			close(preparedStatement);
		}
		close(conn);
		return empresa;
	}


	public void delete(String nombre) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM EMPRESAS WHERE nombre = ?");
			preparedStatement.setString(1, nombre);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
			close(conn);
		}
	}
	
	public Connection open(String jdbcUrl) {

		Connection conn = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(jdbcUrl + ";INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'", "sa","");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return conn;
	}

	public void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}