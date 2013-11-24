<%-- Composition Page: home.jsp --%>

<%
request.setAttribute("title", "Modificar cuenta");
request.setAttribute("header", "/WEB-INF/index-header.jsp");
request.setAttribute("nav", "/WEB-INF/index-nav.jsp");
request.setAttribute("body", "/WEB-INF/modificar-content.jsp");
%>

<jsp:include page="/WEB-INF/templates/practicum-template.jsp" />