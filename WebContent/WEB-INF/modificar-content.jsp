

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<div id="backContenido">
	<div id="contenido">
    <h2>Modificacion de datos:</h2>
		<form action="/Practicum/modificarCuenta">
			<label>Ingrese su correo electronico.</label></br>
			<input type="email" name="email" placeholder="correo electrónico" ></br>	
			<label>Ingrese su contraseña.</label></br>
			<input type="password" name="pass" placeholder="contraseña" required></br>
			<input type="submit" value="Modificar cuenta">
		</form></br>
		<a href="eliminarCuenta.jsp">Desea eliminar su cuenta?</a></br></br>
		<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>
</div>