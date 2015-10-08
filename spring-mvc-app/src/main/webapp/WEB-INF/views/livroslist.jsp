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
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title><fmt:message key="site.titulo" /></title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><fmt:message key="site.titulo" /></a>
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
		<div class="panel-heading">
			<fmt:message key="label.dados.livros" />
		</div>
		<div class="panel-body">
			<div class="container">
				<form class="form-horizontal">

					<table class="table table-hover">
						<thead>
							<tr>
								<th><fmt:message key="label.nome.livro" /></th>
								<th><fmt:message key="label.nome.escritor" /></th>
								<th><fmt:message key="label.nome.edicao" /></th>
								<th><fmt:message key="label.nome.clasificacao" /></th>
								<th width="30"></th>
								<th width="30"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${livros}" var="livro">

								<tr>
									<td>${livro.nome}</td>
									<td>${livro.escritor}</td>
									<td><fmt:message key="label.ano.edicao" />
										${livro.anoedicao}</td>
									<td><fmt:message key="label.idade.minima" />
										${livro.classificacao} <fmt:message key="label.pessoa.anos" /> </td>

									<td><a
										href="<c:url value='/edit-livro-${livro.idlivro}' />"
										class="btn btn-success custom-width"><fmt:message
												key="btn.editar" /></a></td>
									<td><a
										href="<c:url value='/delete-livro-${livro.idlivro}' />"
										class="btn btn-danger custom-width"><fmt:message
												key="btn.apagar" /></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
			<div class="well">
				<a href="<c:url value='/newlivro' />"
					class="btn btn-primary align-leff"><fmt:message
						key="label.novo.livro" /></a>
			</div>
</body>
</html>