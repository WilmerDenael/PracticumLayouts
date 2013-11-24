<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sesión exitosa</title>
</head>
<body>
	<h2><% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>
	Bienvenido rut: <%= currentUser.getRut() %></h2>
	<!--  <a href="../login.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>-->
	<meta http-equiv="refresh" content="2;URL=index.jsp" >
</body>
</html>