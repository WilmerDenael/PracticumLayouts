<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.*" %>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>
<div id="backContenido">
	<div id="contenido">
	<% if (currentUser.getType() == 1) {%>
		<h2>Datos de alumno:</h2>
		<%  AlumnoBean currentAlumno = ((AlumnoBean) (session.getAttribute("currentSessionUser")));%>
		<p>Nombre completo: <%= currentAlumno.getPrimerNombre()%> <%= currentAlumno.getSegundoNombre()%> 
					<%= currentAlumno.getApellidoPaterno()%> <%= currentAlumno.getApellidoMaterno()%></br>
		Run: <%= currentAlumno.getRut()%></br>
		Correo electronico: <%= currentAlumno.getEmail()%></br>
		Contraseña: <%= currentAlumno.getPassword()%></br>
		Estado civil: <%= currentAlumno.getEstadoCivil()%></br>
		Dirección: <%= currentAlumno.getDireccion()%></br>
		Nacionalidad: <%= currentAlumno.getNacionalidad()%></br>
		<!-- POSIBLEMENTE DESPUES AGREGAR REFERENCIAS Y DATOS EXTRAS -->
		</p>
		
	<% }else if (currentUser.getType() == 2) { %>
		<h2>Datos de empresa:</h2>
		<%  EmpresaBean currentEmpresa = ((EmpresaBean) (session.getAttribute("currentSessionUser")));%>
		<p>Nombre: <%= currentEmpresa.getNombre()%></br>
		Rut: <%= currentEmpresa.getRut()%></br>
		Correo electronico: <%= currentEmpresa.getEmail()%></br>
		Contraseña: <%= currentEmpresa.getPassword()%></br>
		N° de trabajadores: <%= currentEmpresa.getNumeroTrabajadores()%></br>
		Dirección: <%= currentEmpresa.getDireccion()%></br>
		Nacionalidad: <%= currentEmpresa.getNacionalidad()%></br>
		Area laboral: <%= currentEmpresa.getAreaLaboral()%></br>
		Descripción: <%= currentEmpresa.getDescripcion()%></br>
		<!-- POSIBLEMENTE DESPUES AGREGAR REFERENCIAS Y DATOS EXTRAS -->
		</p>
	
	<% } %>
	
	<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>
	
	</div>    		
</div>