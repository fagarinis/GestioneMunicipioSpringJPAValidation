<%@page import="it.prova.gestionemunicipiospringjpa.model.Municipio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="cirotrent">
<title>Modifica Municipio</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
</head>
<body>
	

	<div class="container">

		<%@ include file="../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di update municipio</h3>
		</div>
		
		<div class="alert alert-danger ${not empty municipioErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${municipioErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
		</div>
		<form class="form-horizontal" action="${pageContext.request.contextPath }/ExecuteModificaMunicipioServlet" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="descrizioneInputId">Descrizione:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="descrizioneInputId"
						name="descrizioneInput"
						value="${municipioAttribute.descrizione }">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="ubicazioneInputId">Ubicazione:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="ubicazioneInputId"
						name="ubicazioneInput"
						value="${municipioAttribute.ubucazione }">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="codiceInputId">Codice:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="codiceInputId"
						name="codiceInput" value="${municipioAttribute.codice }">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<h3>Sei sicuro?</h3>
					<button type="submit" class="btn btn-primary btn-md">Modifica</button>
				</div>
			</div>

			<input type="hidden" name="idMunicipio"
				value="${municipioAttribute.descrizione }">

		</form>
		<%@ include file="../footer.jsp"%>

	</div>
	<!-- /.container -->

</body>
</html>