<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado Edad Recomendada</title>
</head>
<body>
	<form action="cargarListado" method="post">
	  <select name="item">
	    <option value="1">TP</option>
	    <option value="2">+7</option>
	    <option value="3">+13</option>
	    <option value="4">+16</option>
	    <option value="5">+18</option>
	  </select>
	  <input type="submit" value="Submit">
	</form>
	<table border="1">
		<thead>
			<tr>
				<td>Nombre</td>
				<td>Lanzamiento</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="juego" items="${juegos}">
				<tr>
					<td><c:out value="${juego.nombre}" /></td>
					<td><c:out value="${juego.fechaLanzamiento}" /></td>
					<td><a href="/delete?id=${juego.nombre}">borrar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<select>
		<c:forEach var="juego" items="${juegos}">
			<option value="${juego.nombre}">${juego.nombre}</option>
		</c:forEach>
	</select>
</body>
</html>