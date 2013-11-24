<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>


<div id="backContenido">
	<div id="contenido">
		<h2>Ingreso de datos personales:</h2>
		<form action="/PracticumLayouts/ingresarInfoPersonal">

			<label>Nombre completo.</label></br>
			<input type="text" name="primerNombre" placeholder="primer nombre" >
			<input type="text" name="segundoNombre" placeholder="segundo nombre" >
			<input type="text" name="paterno" placeholder="apellido paterno" >
			<input type="text" name="materno" placeholder="apellido materno" ></br>
			<label>Estado civil.</label></br>
			<input type="text" name="civil"></br>
			<label>Direccion.</label></br>
			<input type="text" name="direccion"></br>
			<label>Nacionalidad.</label></br>
			<input type="text" name="nacionalidad"></br>
			<label>Fecha de nacimiento.</label></br>
			<input type="date" name="nacimiento"></br>
			<!--  <label>Conocimiento idioma.</label></br> 
			<input type="text" name="idioma"></br>-->
			<!-- HAY QUE AGREGAR IDIOMAS, REFERENCIAS, DATOS EXTRAS -->
			</br><input type="submit" value="Guardar">
		</form></br>

		<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>
  	</div>
</div>