<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cancella Municipio</title>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Cancellazione</h3>
		</div>


		<form action="ExecuteEliminaMunicipioServlet" method="post">
			<div class="container-fluid">
				<dl class="row">
					<dt class="col-sm-3 text-right">Id</dt>
					<dd class="col-sm-9">${municipioAttr.id }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Descrizione</dt>
					<dd class="col-sm-9">${municipioAttr.descrizione }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Codice</dt>
					<dd class="col-sm-9">${municipioAttr.codice }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Ubicazione</dt>
					<dd class="col-sm-9">${municipioAttr.ubicazione }</dd>
				</dl>
			</div>

			<input type=hidden name="idMunicipio" value="${municipioAttr.id }">
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