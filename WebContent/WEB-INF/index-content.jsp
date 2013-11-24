<%-- Main Content: /WEB-INF/home-main.jsp --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<div id="backContenido">
 	<div id="contenido">
		<h2>Inicio usuario rut: <%= Integer.toString(currentUser.getRut()) %></h2>
		<a href="mostrarDatosActuales.jsp">Mostrar datos actuales</a></br>
		<a href="modificarCuenta.jsp">Modificar datos de cuenta</a></br>
		<a href="ingresarInfoPersonal.jsp">Ingresar información personal</a></br>
		<a href="ingresarCurriculum.jsp">Ingresar curriculum</a></br>
		<a href="#"></a>
		<a href="logout.jsp">Salir</a></br>
    </div>
</div>