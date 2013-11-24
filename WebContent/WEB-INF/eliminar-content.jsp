<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<div id="backContenido">
 	<div id="contenido">
		<h2>Esta seguro en eliminar su cuenta?</h2>
		<form action="/Practicum/eliminarCuenta">
			<input type="submit" value="Eliminar cuenta">
		</form>
		</br>
		<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>
  	</div>
</div>