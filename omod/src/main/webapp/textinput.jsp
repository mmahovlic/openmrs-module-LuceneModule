<%@ include file="/WEB-INF/template/include.jsp" %>

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp" %>
<%@page import="org.openmrs.module.lucenemodule.web.controller.model.IndexingResult"%>

<center>

<form method="post">
	ID Pacijenta: <input name="id" type="text"> <br>
	Ime: <input name="name" type="text"> <br>
	Prezime: <input name="surname" type="text"><br>
	Srednje ime: <input name="middlename" type="text"><br>
	Opis bolesti: <textarea name="description" maxLength=1000 cols="25" rows="5">Opisite bolest...</textarea> <br>
	Dodatne zabiljeske:<textarea name="additionalnotes" maxLength=1000 cols="25" rows="5">Dodajte zabiljeske...</textarea><br>
	<input type="submit" value="Submit">
</form>
<%
	if (request.getAttribute("indexingResult") != null) {
		IndexingResult result = (IndexingResult) request
				.getAttribute("indexingResult");
		if (result.getError() == "") {
			out.print("Success!\n");
		} else
			out.print(result.getError());
	}
%>
</center>
<br/><br/>

<%@ include file="/WEB-INF/template/footer.jsp" %>