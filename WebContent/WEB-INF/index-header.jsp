<%-- Main Content: /WEB-INF/home-main.jsp --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<header>
	<div id="logo">
		<img src="resources/css/img/u12_normal.png" />
			<div style="float:right; margin:25px auto 0px auto;">
			<img src="resources/css/img/u10_normal.png" />
		</div>
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