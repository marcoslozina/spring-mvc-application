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

<title>Sistema de gestão de empréstimos</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Sistema de gestão de empréstimos</a>
			</div>
			<div>
				<ul class="nav navbar-nav navbar-right">
					<li class="text-right"><a href="<c:url value='/indexGeral' />">Início</a></li>
					<li class="text-right"><a href="#">Ajuda</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div id="main" class="container-fluid" style="margin-top: 50px">
		<div id="top" class="row">
			<div class="col-sm-3"></div>

		</div>
		<hr />
		<div class="panel panel-primary">
			<div class="panel-heading text-center">Menú principal</div>
			<div class="panel-body">
				<div id="list" class="row text-center">
					<div class="table-responsive col-md-12">

						<h3>
							<a href="<c:url value='/listpessoas' />">Gestão de pessoas</a>
						</h3>
						<br>
						<h3>
							<a href="<c:url value='/listlivros' />">Gestão de livros</a>
						</h3>
						<br>
						<h3>
							<a href="<c:url value='/listemprestimos' />">Gestão de empréstimos</a>
						</h3>
						<br>
					</div>
				</div>
				<!-- /#list -->
			</div>
		</div>
	</div>
	<!-- /#main -->

	
</body>
</html>