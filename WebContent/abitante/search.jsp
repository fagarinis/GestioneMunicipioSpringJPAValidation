<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="author" content="MohamedMohamedali">
<meta charset="ISO-8859-1">
<title>Ricerca Abitante</title>
</head>
<body>


	<div class="container">
	
		

		<%@ include file="../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Ricerca Abitante</h3>
		</div>
		
		<%-- MESSAGGIO ERRORE --%>
		<div class="alert alert-danger ${abitanteErrors!=null?'':'d-none' }" role="alert">
			<c:forEach var ="errorItem" items ="${ abitanteErrors}">
				${errorItem}
			</c:forEach>
		</div>

		<form class="form-horizontal" action="ExecuteSearchAbitanteServlet"
			method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="nomeInputId">Nome:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="nomeInputId"
						name="nomeInput">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="cognomeInputId">Cognome:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="cognomeInputId"
						name="cognomeInput">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="etaInputId">Età:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="etaInputId"
						name="etaInput">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="residenzaInputId">Residenza:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="residenzaInputId"
						name="residenzaInput">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="municipioInputId">Municipio:</label>
				<div class="col-sm-4">

					<!-- menu a tendina scelta municipio -->
					<select class="form-control" name="idMunicipio">
						<option class="form-control" value="0">Qualsiasi</option>
						<c:forEach var = "m" items = "${listaMunicipiAttributeName}">
							<option class="form-control" value="${m.id}">${m.descrizione}</option>
						</c:forEach>
					</select>


				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-md">Effettua Ricerca</button>

					<a href="PrepareInsertAbitanteServlet"
						class="btn btn-primary btn-md">Inserisci Nuovo Elemento</a>
				</div>
			</div>
		</form>
		<%@ include file="../footer.jsp"%>
	</div>
	<!-- /.container -->
	
</body>
</html>