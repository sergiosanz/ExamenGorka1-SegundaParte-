package Proyecto.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Proyecto.ConsolasExamen.*;


public class H2ConnectionConsolas implements ConnectionManagerConsolas {

private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";

	public void insert(Consolas consolasFormulario) {

	Connection conn = open(jdbcUrl);
	PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO CONSOLAS (nombre, emrpesa)" + "VALUES (?, ?)");
			preparedStatement.setString(1, consolasFormulario.getNombre());
			preparedStatement.setString(2, consolasFormulario.getEmpresa());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}
		close(conn);
	}

	public List<Consolas> searchAll() {
		
		List<Consolas> listConsolas= new ArrayList<Consolas>();
			Connection conn = open(jdbcUrl);
			ResultSet resultSet = null;
			PreparedStatement prepareStatement = null;
			try {
				prepareStatement = conn.prepareStatement("SELECT * FROM CONSOLAS ");
				resultSet = prepareStatement.executeQuery();
					while(resultSet.next()){
						Consolas consolaInDatabase = new Consolas();
						consolaInDatabase.setNombre(resultSet.getString(1));
						consolaInDatabase.setEmpresa(resultSet.getString(2));
						listConsolas.add(consolaInDatabase);
					}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				close(resultSet);
				close(prepareStatement);
			}
			close(conn);
			return listConsolas;
		}
	
	public Consolas selectByNombre(String nombre) {

		Consolas consola = new Consolas();
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM CONSOLAS WHERE nombre = ?");
			preparedStatement.setString(1, nombre);
			resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
			consola.setNombre(resultSet.getString("nombre"));
			consola.setEmpresa(resultSet.getString("empresa"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet);
			close(preparedStatement);
		}
		close(conn);
		return consola;
	}


	public void delete(String nombre) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM CONSOLAS WHERE nombre = ?");
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