<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>


<div id="backContenido">
	<div id="contenido">
	
	<% if (currentUser.getType() == 1) {%>

		<form action="/Practicum/ingresarInfoPersonal">
			<h2>Ingreso de datos personales:</h2>
		
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
		
<% }else if (currentUser.getType() == 2) { %>
		<form action="/Practicum/ingresarInfoEmpresa">
			<h2>Ingreso de datos de empresa:</h2>
		
			<label>Nombre empresa.</label></br>
			<input type="text" name="nombre" ></br>
			<label>Numero de trabajadores.</label></br>
			<input type="number" min=1 name="numTrab"></br>
			<label>Nacionalidad.</label></br>
			<input type="text" name="nacionalidad"></br>
			<label>Area laboral.</label></br>
			<input type="text" name="areaLaboral"></br>
			<label>Direccion.</label></br>
			<input type="text" name="direccion"></br>
			<label>Descripcion.</label></br>
			<input type="text" name="descripcion"></br>
		</br><input type="submit" value="Guardar">
		</form></br>
		
<% } %>
	
	
		<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>
  	</div>
</div>