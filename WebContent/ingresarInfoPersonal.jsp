<%-- Composition Page: home.jsp --%>

<%
request.setAttribute("title", "Ingresar información personal");
request.setAttribute("header", "/WEB-INF/index-header.jsp");
request.setAttribute("nav", "/WEB-INF/index-nav.jsp");
request.setAttribute("body", "/WEB-INF/ingresar-infoper-content.jsp");
%>

<jsp:include page="/WEB-INF/templates/practicum-template.jsp" />