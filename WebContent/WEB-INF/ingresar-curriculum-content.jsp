<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<div id="backContenido">
	<div id="contenido">
		<h2>Ingreso de curriculum:</h2>
		<form action="/PracticumLayouts/ingresarCurriculum">
		
			<label>Ingrese su curriculum en formato .doc o .pdf</label></br>
			<input name="archivo" type="file" size="20"> 
			</br><input type="submit" value="Guardar">
		</form></br>

		<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>
  	</div>
</div>