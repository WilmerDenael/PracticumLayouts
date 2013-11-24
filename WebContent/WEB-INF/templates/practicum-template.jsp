<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<%-- Template for MyCom Site: /WEB-INF/templates/mycom-template.jsp --%> 

<%-- Setup Context variable to point to component files:
        header, meny, footer, and stylesheet --%> 
<%
request.setAttribute("css", "resources/css/mainStyle.css");
%>

<%-- Include abstract layout --%> 
<jsp:include page="classic.jsp" />