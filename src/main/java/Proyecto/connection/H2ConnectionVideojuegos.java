package Proyecto.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Proyecto.ConsolasExamen.*;


public class H2ConnectionVideojuegos implements ConnectionManagerVideojuegos {

private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";

	public void insert(VideoJuegos videojuegosFormulario) {

	Connection conn = open(jdbcUrl);
	PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO VIDEOJUEGOS (titulo, edadRecomendada, fechaLanzamiento)" + "VALUES (?, ?, ?)");
			preparedStatement.setString(1, videojuegosFormulario.getTitulo());
			preparedStatement.setString(2, videojuegosFormulario.getEdadRecomendada());
			preparedStatement.setString(3, videojuegosFormulario.getFechaLanzamiento());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}
		close(conn);
	}

	public List<VideoJuegos> searchAll() {
		
		List<VideoJuegos> listVideojuegos= new ArrayList<VideoJuegos>();
			Connection conn = open(jdbcUrl);
			ResultSet resultSet = null;
			PreparedStatement prepareStatement = null;
			try {
				prepareStatement = conn.prepareStatement("SELECT * FROM VIDEOJUEGOS ");
				resultSet = prepareStatement.executeQuery();
					while(resultSet.next()){
						VideoJuegos videojuegoInDatabase = new VideoJuegos();
						videojuegoInDatabase.setTitulo(resultSet.getString(1));
						videojuegoInDatabase.setEdadRecomendada(resultSet.getString(2));
						videojuegoInDatabase.setFechaLanzamiento(resultSet.getString(3));
						listVideojuegos.add(videojuegoInDatabase);
					}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				close(resultSet);
				close(prepareStatement);
			}
			close(conn);
			return listVideojuegos;
		}
	
	public VideoJuegos selectByTitulo(String titulo) {

		VideoJuegos videojuego = new VideoJuegos();
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM VIDEOJUEGOS WHERE titulo = ?");
			preparedStatement.setString(1, titulo);
			resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
			videojuego.setTitulo(resultSet.getString("titulo"));
			videojuego.setEdadRecomendada(resultSet.getString("edadRecomendada"));
			videojuego.setFechaLanzamiento(resultSet.getString("fechaLanzamiento"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet);
			close(preparedStatement);
		}
		close(conn);
		return videojuego;
	}


	public void delete(String titulo) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM VIDEOJUEGOS WHERE titulo = ?");
			preparedStatement.setString(1, titulo);
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