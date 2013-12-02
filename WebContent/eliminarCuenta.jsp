<%-- Composition Page: home.jsp --%>

<%
request.setAttribute("title", "Eliminar cuenta");
request.setAttribute("header", "/WEB-INF/index-header.jsp");
request.setAttribute("nav", "/WEB-INF/index-nav.jsp");
request.setAttribute("body", "/WEB-INF/eliminar-content.jsp");
request.setAttribute("footer", "/WEB-INF/index-footer.jsp");
%>

<jsp:include page="/WEB-INF/templates/practicum-template.jsp" />