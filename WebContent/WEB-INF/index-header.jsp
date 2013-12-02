<%-- Main Content: /WEB-INF/home-main.jsp --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<header>
    <div id="logo1">
		<img src="resources/css/img/menu_marca.png" >
	</div>
	<div id="logo2">
		<img src="resources/css/img/header.png" >
	</div>
    
    
    <div id="menuUser">
    	<ul>
    		<li>Bienvenido <%= Integer.toString(currentUser.getRut()) %>,</li>
        	<a href="#"><li>Cuenta</li></a>
            <a href="#"><li>Ayuda</li></a>
            <a href="logout.jsp"><li>Salir</li></a>
        </ul>
    </div>
</header>