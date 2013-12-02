<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<div id="backContenido">
 	<div id="contenido">
 		<div style="text-align:center;">
			<h2>¿Esta seguro en eliminar su cuenta?</h2>
			<p>Debe tener en cuenta que sus datos personales y su curriculum seran borrados de nuestro sistema.</p>
			<form action="/Practicum/eliminarCuenta">
				<input type="submit" value="Si, deseo eliminar mi cuenta">
			</form>
			</br></br>
			<a href="index.jsp" target="_self"> <input type="button" name="boton" value="Volver" /> </a>
		</div>
  	</div>
</div>