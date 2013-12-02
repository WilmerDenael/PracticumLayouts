<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.*" %>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>
<div id="backContenido">
	<div id="contenido">
	<% if (currentUser.getType() == 1) {%>
		<h2>Datos de alumno:</h2>
		<%  AlumnoBean currentAlumno = ((AlumnoBean) (session.getAttribute("currentSessionUser")));%>
		<label>Nombre Completo:</label>
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
		<label>Run:</label> <%= currentAlumno.getRut()%></br>
		<label>Correo electronico:</label> <%= currentAlumno.getEmail()%></br>
		<label>Contraseña:</label> <%= currentAlumno.getPassword()%></br>
		<label>Estado civil:</label>
		<% if(currentAlumno.getEstadoCivil()!=null && !currentAlumno.getEstadoCivil().isEmpty() ){%>
			<%=currentAlumno.getEstadoCivil()%>
		<%}%></br>
		<label>Dirección:</label> 
		<% if(currentAlumno.getDireccion()!=null && !currentAlumno.getDireccion().isEmpty() ){%>
			<%=currentAlumno.getDireccion()%>
		<%}%></br>
		<label>Fecha de nacimiento:</label> 
		<% if(currentAlumno.getFechaNacimiento()!=null && !currentAlumno.getFechaNacimiento().isEmpty() ){%>
			<%=currentAlumno.getFechaNacimiento()%>
		<%}%></br>
		<label>Nacionalidad:</label> 
		<% if(currentAlumno.getNacionalidad()!=null && !currentAlumno.getNacionalidad().isEmpty() ){%>
			<%=currentAlumno.getNacionalidad()%>
		<%}%></br>
		<label>Referencias:</label>
		<% if(currentAlumno.getReferencia()!=null && !currentAlumno.getReferencia().isEmpty() ){%>
			<%=currentAlumno.getReferencia()%>
		<%}%></br>
		<label>Datos extras:</label> 
		<% if(currentAlumno.getDatosExtra()!=null && !currentAlumno.getDatosExtra().isEmpty() ){%>
			<%=currentAlumno.getDatosExtra()%>
		<%}%></br></br>
		
		
	<% }else if (currentUser.getType() == 2) { %>
		<h2>Datos de empresa:</h2>
		<%  EmpresaBean currentEmpresa = ((EmpresaBean) (session.getAttribute("currentSessionUser")));%>
		<label>Nombre:</label> 
		<% if(currentEmpresa.getNombre()!=null && !currentEmpresa.getNombre().isEmpty() ){%>
			<%=currentEmpresa.getNombre()%>
		<%}%></br>
		<label>Rut:</label> <%= currentEmpresa.getRut()%></br>
		<label>Correo electronico:</label> <%= currentEmpresa.getEmail()%></br>
		<label>Contraseña:</label> <%= currentEmpresa.getPassword()%></br>
		<label>N° de trabajadores:</label>
		<% if(currentEmpresa.getNumeroTrabajadores()!=0 ){%>
			<%=currentEmpresa.getNumeroTrabajadores()%>
		<%}%></br>
		<label>Dirección:</label>
		<% if(currentEmpresa.getDireccion()!=null && !currentEmpresa.getDireccion().isEmpty() ){%>
			<%=currentEmpresa.getDireccion()%>
		<%}%></br>
		<label>Nacionalidad:</label> 
		<% if(currentEmpresa.getNacionalidad()!=null && !currentEmpresa.getNacionalidad().isEmpty() ){%>
			<%=currentEmpresa.getNacionalidad()%>
		<%}%></br>
		<label>Area laboral:</label> 
		<% if(currentEmpresa.getAreaLaboral()!=null && !currentEmpresa.getAreaLaboral().isEmpty() ){%>
			<%=currentEmpresa.getAreaLaboral()%>
		<%}%></br>
		<label>Descripción:</label> 
		<% if(currentEmpresa.getDescripcion()!=null && !currentEmpresa.getDescripcion().isEmpty() ){%>
			<%=currentEmpresa.getDescripcion()%>
		<%}%></br>
		
	
	<% } %>
	
	<a href="index.jsp" target="_self"> <input type="button" name="boton" value="Volver" /> </a>
	
	</div>    		
</div>