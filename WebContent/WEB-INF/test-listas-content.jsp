<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.*" import="com.practicum.dao.*" import="java.util.*"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>
<div id="backContenido">
	<div id="contenido">	
<%for(Idioma idioma : GenericDAO.cargarIdiomas()) {%>
	<%=idioma.getIdioma() %></br>
<% }%>
	<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>
	
	</div>    		
</div>