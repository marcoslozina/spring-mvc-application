<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			<fmt:message key="panel.dados.emprestimo" />
		</div>
		<div class="panel-body">
			<div class="container">
				<form:form method="POST" modelAttribute="livro"
					class="form-horizontal">
					<form:input type="hidden" path="idlivro" id="idlivro" />

					<div class="row">
						<div class="form-group">
							<label class="col-md-3 control-lable align-right" for="nome"><fmt:message
									key="label.livro.nome" /></label>
							<div class="col-xs-3 ">
								<form:input type="text" path="nome" id="nome"
									class="form-control col-xs-4" />
								<div class="has-error">
									<form:errors path="nome" class="help-inline" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group">
							<label class="col-md-3 control-lable align-right" for="nome"><fmt:message
									key="label.livro.escritor" /></label>
							<div class="col-xs-3 ">
								<form:input type="text" path="escritor" id="escritor"
									class="form-control col-xs-4" />
								<div class="has-error">
									<form:errors path="escritor" class="help-inline" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group">
							<label class="col-md-3 control-lable align-right" for="anoedicao"><fmt:message
									key="label.livro.anoedicao" /></label>
							<div class="col-xs-3 ">
								<form:input type="number" path="anoedicao" id="anoedicao"
									class="form-control col-xs-4" />
								<div class="has-error">
									<form:errors path="anoedicao" class="help-inline" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group">
							<label class="col-md-3 control-lable align-right"
								for="classificacao"><fmt:message
									key="label.livro.clasificação" /></label>
							<div class="col-xs-3 ">
								<form:input type="number" path="classificacao"
									id="classificacao" class="form-control col-xs-4" />
								<div class="has-error">
									<form:errors path="classificacao" class="help-inline" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<c:choose>
								<c:when test="${edit}">
									<input type="submit" value="Atualizar"
										class="btn btn-primary btn-sm" /> ou <a
										class="btn btn-default" href="<c:url value='/listlivros' />"><fmt:message
											key="btn.cancel" /></a>
								</c:when>
								<c:otherwise>
									<div class="well">
										<input type="submit" value="Cadastrar" class="btn btn-primary" />
										ou <a class="btn btn-default"
											href="<c:url value='/listlivros' />"><fmt:message
												key="btn.cancel" /></a>
									</div>


								</c:otherwise>
							</c:choose>
						</div>
					</div>

				</form:form>
			</div>
</body>
</html>