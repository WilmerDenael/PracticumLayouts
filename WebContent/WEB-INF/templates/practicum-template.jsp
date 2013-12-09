<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<%-- Template for MyCom Site: /WEB-INF/templates/mycom-template.jsp --%> 

<%-- Setup Context variable to point to component files:
        header, meny, footer, and stylesheet --%> 
<%
request.setAttribute("css1", "resources/css/mainStyle.css");
request.setAttribute("js1", "resources/js/jquery-2.0.3.js");
request.setAttribute("js2", "resources/js/jquery.multi-select.js");
request.setAttribute("js3", "resources/js/cloneFieldset.js");
request.setAttribute("js4", "resources/js/chosen.jquery.js");
%>

<%-- Include abstract layout --%> 
<jsp:include page="classic.jsp" />