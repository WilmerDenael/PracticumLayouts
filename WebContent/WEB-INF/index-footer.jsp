<%-- Main Content: /WEB-INF/home-main.jsp --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<footer>
	<p>Escuela de Ingenier�a Civil en Inform�tica</br>
	<img src="resources/css/img/barra.png" ></br>
	Escuela de Ingenier�a Civil en Inform�tica, Facultad de Ingenier�a de la Universidad de Valpara�so.</br>
	Avenida Gran Breta�a #1091, Fono: (56-32) 2508300 - Fax (56-32) 2508301</br>
	Playa Ancha, Valpara�so - Chile.</p>
</footer>