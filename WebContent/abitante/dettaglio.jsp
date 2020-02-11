<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ABITANTE</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jqueryUI/jquery-ui.min.css" />
<style>
.ui-autocomplete-loading {
	background: white url("../img/anim_16x16.gif") right center no-repeat;
}
</style>
</head>
<body>
	
	<div class="container">
	
		
		<%@ include file="/header.jsp"%> 

		<div class="page-header">
			<h3>DETTAGLIO ABITANTE</h3>
		</div>
		<div class="container-fluid">
			<dl class="row">
				<dt class="col-sm-3 text-right">Nome:</dt>
				<dd class="col-sm-9">${abitanteAttr.nome}</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Cognome:</dt>
				<dd class="col-sm-9">${abitanteAttr.cognome}</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Eta:</dt>
				<dd class="col-sm-9">${abitanteAttr.eta}</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Indirizzo:</dt>
				<dd class="col-sm-9">${abitanteAttr.indirizzo}</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Municipio:</dt>
				<dd class="col-sm-9">descrizione:(${abitanteAttr.municipio.descrizione})  ubicazione:(${abitanteAttr.municipio.ubicazione})</dd>
			</dl>
			<span onclick="window.history.back()" class="btn btn-info">Torna Indietro</span>
		</div>
	</div>

</body>
</html>