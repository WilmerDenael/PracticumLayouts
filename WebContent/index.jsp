<%-- Composition Page: home.jsp --%>

<%
request.setAttribute("title", "Bienvenido Alumno");
request.setAttribute("header", "/WEB-INF/index-header.jsp");
request.setAttribute("nav", "/WEB-INF/index-nav.jsp");
request.setAttribute("body", "/WEB-INF/index-content.jsp");
%>

<jsp:include page="/WEB-INF/templates/practicum-template.jsp" />