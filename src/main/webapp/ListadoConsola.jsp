<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado Consola</title>
</head>
<body>
	<form action="cargarListado" method="post">
		<input type="submit" value="ver listado">
	</form>

	<table border="1">
		<thead>
			<tr>
				<td>Nombre</td>
				<td>Empresa</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="consola" items="${consolas}">
				<tr>
					<td><c:out value="${consola.nombre}" /></td>
					<td><c:out value="${consola.empresa}" /></td>
					<td><a href="/delete?id=${consola.nombre}">borrar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<select>
		<c:forEach var="consola" items="${consolas}">
			<option value="${consola.nombre}">${consola.nombre}</option>
		</c:forEach>
	</select>
	
</body>
</html>