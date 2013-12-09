<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<%-- Abstract Layout I: /WEB-INF/templates/classic1.jsp --%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${requestScope.css1}" type="text/css" rel="stylesheet" />
<link href="${requestScope.css2}" type="text/css" rel="stylesheet" />
<%-- <link href="${requestScope.css3}" type="text/css" rel="stylesheet" /> --%>
<link href="${requestScope.css4}" type="text/css" rel="stylesheet" />
<script src="${requestScope.js1}" type="text/javascript"></script>
<script src="${requestScope.js2}" type="text/javascript"></script>
<script src="${requestScope.js3}" type="text/javascript"></script>
<script src="${requestScope.js4}" type="text/javascript"></script>

<title> ${requestScope.title} </title>
</head>
<body>

	<div id="main">
    	<jsp:include page="${requestScope.header}" />
    	
        <jsp:include page="${requestScope.nav}" />
        
    	<jsp:include page="${requestScope.body}" />
    	
        <!--<jsp:include page="${requestScope.footer}" />-->
    </div>
		
</body>
</html>