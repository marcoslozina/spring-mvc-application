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

<script>
$(document).ready(function() {
	  $("#selectPessoas").select2();
	  $("#selectLivros").select2();

	});
</script>


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
					<li class="text-right"><a href="<c:url value='/indexGeral' />"><fmt:message key="nav.bar.inicio"/></a></li>
					<li class="text-right"><a href="#"><fmt:message key="nav.bar.ajuda"/></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<br>
	<br>
	<br>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading"><fmt:message key="panel.dados.emprestimo"/></div>
		<div class="panel-body">
			<div class="container">
				<form:form method="POST" modelAttribute="emprestimo"
					class="form-horizontal">
					<form:input type="hidden" path="idemprestimo" id="idemprestimo" />

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="pessoa"><fmt:message key="label.pessoa"/></label>
							<div class="col-md-3">
								<form:select id="selectPessoas" path="pessoa" items="${pessoas}"
									multiple="false" itemValue="idpessoa" itemLabel="nome"
									class="form-control col-xs-3" />
								<div class="has-error">
									<form:errors path="pessoa" class="help-inline" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="pessoa"><fmt:message key="label.livro"/></label>
							<div class="col-md-3">
								<form:select id="selectLivros" path="livro" items="${livros}"
									multiple="false" itemValue="idlivro" itemLabel="nome"
									class="form-control col-xs-3" />
								<div class="has-error">
									<form:errors path="livro" class="help-inline" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group">
							<label class="col-md-3 control-lable" for="dataemprestimo"><fmt:message key="label.dataemprestimo"/></label>
							<div class="col-xs-3">
								<form:input type="date" path="dataemprestimo" required="true" 
									id="dataemprestimo" class="form-control col-xs-3" />
								<div class="has-error">
									<form:errors path="dataemprestimo" class="help-inline" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group">
							<label class="col-md-3 control-lable" for="datahoradevolucao"><fmt:message key="label.datadevolucao"/></label>
							<div class="col-xs-3">
								<form:input type="date" path="datahoradevolucao" placeholder="dd/mm/yyyy hh:mm "
									id="datahoradevolucao" class="form-control col-xs-3" />
									
								<div class="has-error">
									<form:errors path="datahoradevolucao" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
			</div>



			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Atualizar"
								class="btn btn-primary btn-sm" /> ou <a class="btn btn-default"
								href="<c:url value='/listemprestimos' />"><fmt:message key="btn.cancel"/></a>
						</c:when>
						<c:otherwise>
							<div class="well">
								<input type="submit" value="Cadastrar" class="btn btn-primary" />
								ou <a class="btn btn-default"
									href="<c:url value='/listemprestimos' />"><fmt:message key="btn.cancel"/></a>
							</div>


						</c:otherwise>
					</c:choose>
				</div>
			</div>

			</form:form>
		</div>
</body>
</html>