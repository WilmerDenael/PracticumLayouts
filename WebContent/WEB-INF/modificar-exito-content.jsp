
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>


<div id="backContenido">
  	<div id="contenido">
		<h2>Se han modificado los campos</h2>
		<meta http-equiv="refresh" content="2;URL=index.jsp" >
	</div>
</div>