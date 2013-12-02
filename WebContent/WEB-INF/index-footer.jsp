<%-- Main Content: /WEB-INF/home-main.jsp --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<footer>
	<p>Escuela de Ingeniería Civil en Informática</br>
	<img src="resources/css/img/barra.png" ></br>
	Escuela de Ingeniería Civil en Informática, Facultad de Ingeniería de la Universidad de Valparaíso.</br>
	Avenida Gran Bretaña #1091, Fono: (56-32) 2508300 - Fax (56-32) 2508301</br>
	Playa Ancha, Valparaíso - Chile.</p>
</footer>