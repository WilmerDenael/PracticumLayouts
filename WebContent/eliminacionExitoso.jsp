<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eliminacion exitoso</title>
</head>
<body>
	<h2>Usuario eliminado</h2>
		<!--  <a href="../login.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>-->
		<meta http-equiv="refresh" content="2;URL=../login.jsp" >
<% 
session.invalidate();
response.sendRedirect("login.jsp");
%>
</body>
</html>