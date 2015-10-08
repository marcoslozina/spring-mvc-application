<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<title><fmt:message key="site.titulo"/></title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><fmt:message key="site.titulo"/></a>
			</div>
			<div>
				<ul class="nav navbar-nav navbar-right">
					<li class="text-right"><a href="<c:url value='/indexGeral' />"><fmt:message
								key="nav.bar.inicio" /></a></li>
					<li class="text-right"><a href="#"><fmt:message
								key="nav.bar.ajuda" /></a></li>
				</ul>
			</div>
		</div>
	</nav>

	<br>
	<br>
	<br>
	<br>

	<div class="panel panel-primary">
		<div class="panel-heading"><fmt:message key="label.dados.emprestimos"/></div>
		<div class="panel-body">
			<div class="container">
				<form class="form-horizontal">
					<table class="table table-hover">
						<thead>
							<tr>	<th><fmt:message key="label.emprestimo.propietario"/></th>
								<th><fmt:message key="label.emprestimo.livro.emprestado"/></th>
								<th><fmt:message key="label.emprestimo.data.emprestimo"/></th>
								<th><fmt:message key="label.emprestimo.data.devolucao"/></th>
								<th width="30"></th>
								<th width="30"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${emprestimos}" var="emprestimo">

								<tr>
									<td>${emprestimo.pessoa.nome}</td>
									<td>${emprestimo.livro.nome}</td>
									<td><fmt:formatDate pattern="dd-MM-yyyy"
											value="${emprestimo.dataemprestimo}" /></td>
									<td><fmt:formatDate pattern="dd-MM-yyyy"
											value="${emprestimo.datahoradevolucao}" /></td>
									<td><a
										href="<c:url value='/edit-emprestimo-${emprestimo.idemprestimo}' />"
										class="btn btn-success custom-width"><fmt:message
												key="btn.editar" /></a></td>
									<td><a
										href="<c:url value='/delete-emprestimo-${emprestimo.idemprestimo}' />"
										class="btn btn-danger custom-width"><fmt:message key="btn.apagar"/></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
			<div class="well">
				<a href="<c:url value='/newemprestimo' />"
					class="btn btn-primary align-leff"><fmt:message key="label.novo.emprestimo"/></a>
			</div>
</body>
</html>