<%-- Main Content: /WEB-INF/home-main.jsp --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>
<% if (currentUser.getType() == 1) {%>
<nav>
	<ul>
		<a href="index.jsp" class="buttonNav"><li>Inicio</li></a>
		<a href="#" class="buttonNav"><li>Practicas</li></a>
		<a href="#" class="buttonNav"><li>Alumno</li></a>
		<a href="#" class="buttonNav"><li>Alumno</li></a>
		<a href="#" class="buttonNav"><li>Alumno</li></a>
	</ul>
</nav>
<% }else if (currentUser.getType() == 2) { %>
	<nav>
	<ul>
		<a href="index.jsp" class="buttonNav"><li>Inicio</li></a>
		<a href="#" class="buttonNav"><li>Practicas</li></a>
		<a href="#" class="buttonNav"><li>Empresa</li></a>
		<a href="#" class="buttonNav"><li>Empresa</li></a>
		<a href="#" class="buttonNav"><li>Empresa</li></a>
	</ul>
</nav>
<% } %>