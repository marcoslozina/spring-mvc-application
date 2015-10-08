<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">


<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css"
	rel="stylesheet" />

<link
	href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.css"
	rel="stylesheet" />
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.js"></script>

<title><fmt:message key="site.titulo"/></title>
</head>
<body>
<div class="generic-container">
	<div class="alert alert-success lead">
    	${success}
	</div>
	
	<span class="well floatRight">
		<fmt:message key="registration.sucess.ir.na"/> <a href="<c:url value='/listlivros' />"><fmt:message key="registration.sucess.lista.de.livros"/></a>
	</span>
</div>
</body>

</html>