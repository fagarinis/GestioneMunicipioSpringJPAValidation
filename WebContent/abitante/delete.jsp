<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cancella Abitante</title>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Cancellazione</h3>
		</div>

		<form action="ExecuteEliminaAbitanteServlet" method="post">
			<div class="container-fluid">
				<dl class="row">
					<dt class="col-sm-3 text-right">Id</dt>
					<dd class="col-sm-9">${abitanteAttr.id }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Nome</dt>
					<dd class="col-sm-9">${abitanteAttr.nome }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Cognome</dt>
					<dd class="col-sm-9">${abitanteAttr.cognome }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Eta:</dt>
					<dd class="col-sm-9">${abitanteAttr.eta}</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Indirizzo</dt>
					<dd class="col-sm-9">${abitanteAttr.residenza }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Municipio</dt>
					<dd class="col-sm-9">${abitanteAttr.municipio.descrizione }</dd>
				</dl>
			</div>

			<input type=hidden name="idAbitante" value="${abitanteAttr.id }">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-md">Cancella</button>
					<a href="javascript:history.back()" class="btn btn-primary btn-md">Torna
						Indietro</a>
				</div>
			</div>
			<%@ include file="../footer.jsp"%>
		</form>
	</div>


</body>
</html>