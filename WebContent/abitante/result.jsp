<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="author" content="federicof1994">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultati</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
</head>
<body>

	<div class="container">

		<%@ include file="/header.jsp"%>

		<div class="page-header">
			<h3>Pagina dei Risultati</h3>
		</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Action</th>
				</tr>
			</thead>
	
			<c:forEach var="abitanteItem" items = "${listaAbitantiAttributeName}"> 
			<tr>
				<td>${abitanteItem.nome}</td>
				<td>${abitanteItem.cognome}</td>
				<td><a href="VisualizzaDettaglioAbitanteServlet?idAbitante=${abitanteItem.id}"
					class="btn btn-info">Dettaglio</a> 
					<a href="PrepareUpdateAbitanteServlet?idAbitante=${abitanteItem.id}"
					class="btn btn-info">Modifica</a> 
					<a href="PrepareDeleteAbitanteServlet?idAbitante=${abitanteItem.id}"
					class="btn btn-info">Elimina</a>
				</td>
			</tr>
			</c:forEach>
			
		</table>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<a href="PrepareInsertAbitanteServlet"
					class="btn btn-primary btn-md">Inserisci Nuovo Elemento</a>
			</div>
		</div>

		<%@ include file="../footer.jsp"%>
	</div>
</body>
</html>