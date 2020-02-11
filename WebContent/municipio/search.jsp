<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca Municipio</title>
</head>
<body>

<div class="container">

   <%@ include file="../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Ricerca Municipio</h3>
	</div>

      	<form class="form-horizontal" action="ExecuteSearchMunicipioServlet" method="post">
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="descrizioneInputId">Descrizione:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="descrizioneInputId" name="descrizioneInput" >
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="codiceInputId">Codice:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="codiceInputId" name="codiceInput" >
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="ubicazioneInputId">Ubicazione:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="ubicazioneInputId" name="ubicazioneInput" >
			 	</div>
  			</div>
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Effetua Ricerca</button>
		        <a href="${pageContext.request.contextPath}/PrepareInsertMunicipioServlet" class="btn btn-primary btn-md">Inserisci Nuovo Elemento</a>
		      </div>
		    </div>
		</form>
		
		
		<%@ include file="../footer.jsp"%>
    </div><!-- /.container -->



</body>
</html>