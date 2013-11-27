<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.*" %>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>
<div id="backContenido">
	<div id="contenido">
	<% if (currentUser.getType() == 1) {%>
		<h2>Datos de alumno:</h2>
		<%  AlumnoBean currentAlumno = ((AlumnoBean) (session.getAttribute("currentSessionUser")));%>
		<p>Nombre Completo:
		<% if(currentAlumno.getPrimerNombre()!=null && !currentAlumno.getPrimerNombre().isEmpty() ){%>
			<%=currentAlumno.getPrimerNombre()%>
		<%}%>
		<% if(currentAlumno.getSegundoNombre()!=null && !currentAlumno.getSegundoNombre().isEmpty() ){%>
			<%=currentAlumno.getSegundoNombre()%>
		<%}%>
		<% if(currentAlumno.getApellidoPaterno()!=null && !currentAlumno.getApellidoPaterno().isEmpty() ){%>
			<%=currentAlumno.getApellidoPaterno()%>
		<%}%>
		<% if(currentAlumno.getApellidoMaterno()!=null && !currentAlumno.getApellidoMaterno().isEmpty() ){%>
			<%=currentAlumno.getApellidoMaterno()%>
		<%}%></br>
		Run: <%= currentAlumno.getRut()%></br>
		Correo electronico: <%= currentAlumno.getEmail()%></br>
		Contraseña: <%= currentAlumno.getPassword()%></br>
		Estado civil: 
		<% if(currentAlumno.getEstadoCivil()!=null && !currentAlumno.getEstadoCivil().isEmpty() ){%>
			<%=currentAlumno.getEstadoCivil()%>
		<%}%></br>
		Dirección: 
		<% if(currentAlumno.getDireccion()!=null && !currentAlumno.getDireccion().isEmpty() ){%>
			<%=currentAlumno.getDireccion()%>
		<%}%></br>
		Nacionalidad: 
		<% if(currentAlumno.getNacionalidad()!=null && !currentAlumno.getNacionalidad().isEmpty() ){%>
			<%=currentAlumno.getNacionalidad()%>
		<%}%></br>
		Referencias: 
		<% if(currentAlumno.getReferencia()!=null && !currentAlumno.getReferencia().isEmpty() ){%>
			<%=currentAlumno.getReferencia()%>
		<%}%></br>
		Datos extras: 
		<% if(currentAlumno.getDatosExtra()!=null && !currentAlumno.getDatosExtra().isEmpty() ){%>
			<%=currentAlumno.getDatosExtra()%>
		<%}%></br>
		</p>
		
	<% }else if (currentUser.getType() == 2) { %>
		<h2>Datos de empresa:</h2>
		<%  EmpresaBean currentEmpresa = ((EmpresaBean) (session.getAttribute("currentSessionUser")));%>
		<p>Nombre: 
		<% if(currentEmpresa.getNombre()!=null && !currentEmpresa.getNombre().isEmpty() ){%>
			<%=currentEmpresa.getNombre()%>
		<%}%>
		Rut: <%= currentEmpresa.getRut()%></br>
		Correo electronico: <%= currentEmpresa.getEmail()%></br>
		Contraseña: <%= currentEmpresa.getPassword()%></br>
		N° de trabajadores: 
		<% if(currentEmpresa.getNumeroTrabajadores()!=0 ){%>
			<%=currentEmpresa.getNumeroTrabajadores()%>
		<%}%></br>
		Dirección: 
		<% if(currentEmpresa.getDireccion()!=null && !currentEmpresa.getDireccion().isEmpty() ){%>
			<%=currentEmpresa.getDireccion()%>
		<%}%></br>
		Nacionalidad: 
		<% if(currentEmpresa.getNacionalidad()!=null && !currentEmpresa.getNacionalidad().isEmpty() ){%>
			<%=currentEmpresa.getNacionalidad()%>
		<%}%></br>
		Area laboral: 
		<% if(currentEmpresa.getAreaLaboral()!=null && !currentEmpresa.getAreaLaboral().isEmpty() ){%>
			<%=currentEmpresa.getAreaLaboral()%>
		<%}%></br>
		Descripción: 
		<% if(currentEmpresa.getDescripcion()!=null && !currentEmpresa.getDescripcion().isEmpty() ){%>
			<%=currentEmpresa.getDescripcion()%>
		<%}%></br>
		</p>
	
	<% } %>
	
	<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>
	
	</div>    		
</div>