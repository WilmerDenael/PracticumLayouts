<%-- Main Content: /WEB-INF/home-main.jsp --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<div id="backContenido">
 	<div id="contenido">
		<% if (currentUser.getType() == 1) {%>
			<h2><center>Interfaz de alumno Run: <%= Integer.toString(currentUser.getRut()) %></center></h2>
			
			<a href="testListas.jsp">TEST LISTAR IDIOMAS</a></br>
			<!--  <form action="/Practicum/testListas">
				<input type="hidden" name="test">
				<input type="submit" value="TEST LISTA">
			</form>-->
			
		<% }else if (currentUser.getType() == 2) { %>
			<h2><center>Interfaz de empresa Rut: <%= Integer.toString(currentUser.getRut()) %></center></h2>
			
		<% } %>
		
		
    </div>
</div>