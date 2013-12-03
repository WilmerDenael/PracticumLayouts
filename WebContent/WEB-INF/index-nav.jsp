<%-- Main Content: /WEB-INF/home-main.jsp --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>
<% if (currentUser.getType() == 1) {%>
<nav>
	<ul>
		<a href="index.jsp" class="buttonNav"><li>Inicio</li></a>
		<a href="mostrarDatosActuales.jsp" class="buttonNav"><li>Datos Alumno</li></a>
		<a href="modificarCuenta.jsp" class="buttonNav"><li>Modificar cuenta</li></a>
		<a href="ingresarInfoPersonal.jsp" class="buttonNav"><li>Información personal</li></a>
		<a href="ingresarCurriculum.jsp" class="buttonNav"><li>Ingresar curriculum</li></a>
	</ul>
</nav>
<% }else if (currentUser.getType() == 2) { %>
	<nav>
	<ul>
		<a href="index.jsp" class="buttonNav"><li>Inicio</li></a>
		<a href="mostrarDatosActuales.jsp" class="buttonNav"><li>Datos Empresa</li></a>
		<a href="modificarCuenta.jsp" class="buttonNav"><li>Modificar cuenta</li></a>
		<a href="ingresarInfoPersonal.jsp" class="buttonNav"><li>Ingresar información</li></a>
	</ul>
</nav>
<% } %>