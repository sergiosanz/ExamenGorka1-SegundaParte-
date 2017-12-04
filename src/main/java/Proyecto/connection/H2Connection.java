package Proyecto.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Proyecto.ConsolasExamen.*;


public class H2Connection implements ConnectionManagerConsolas {

private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";

	public void insert(User userFormulario) {

	Connection conn = open(jdbcUrl);
	PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO USER (nombre, apellido)" + "VALUES (?, ?)");
			preparedStatement.setString(1, userFormulario.getNombre());
			preparedStatement.setString(2, userFormulario.getApellido());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}
		close(conn);
	}

	public List<User> searchAll() {
		
		List<User> listUsers= new ArrayList<User>();
			Connection conn = open(jdbcUrl);
			ResultSet resultSet = null;
			PreparedStatement prepareStatement = null;
			try {
				prepareStatement = conn.prepareStatement("SELECT * FROM USER");
				resultSet = prepareStatement.executeQuery();
					while(resultSet.next()){
						User userInDatabase = new User();
						userInDatabase.setNombre(resultSet.getString(1));
						userInDatabase.setApellido(resultSet.getString(2));
						listUsers.add(userInDatabase);
					}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				close(resultSet);
				close(prepareStatement);
			}
			close(conn);
			return listUsers;
		}
	
	public User selectByNombre(String nombre) {

		User user = new User();
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM User WHERE nombre = ?");
			preparedStatement.setString(1, nombre);
			resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
			user.setNombre(resultSet.getString("nombre"));
			user.setApellido(resultSet.getString("apellido"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet);
			close(preparedStatement);
		}
		close(conn);
		return user;
	}


	public void delete(String nombre) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM person WHERE nombre = ?");
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