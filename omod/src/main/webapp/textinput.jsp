<%@ include file="/WEB-INF/template/include.jsp" %>

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp" %>
<%@page import="org.openmrs.module.lucenemodule.web.controller.model.IndexingResult"%>

<center>

<form method="post">
	Ime: <input name="name" type="text"> <br>
	Prezime: <input name="surname" type="text"><br>
	Srednje ime: <input name="middlename" type="text"><br>
<!-- 	Short:<input name="shortcause" type="text"><br> style="width: 300px; height: 300px"  type="text"--> 
<!-- 	Post mortem:<input name="postmortem" type="text"><br>
	Abbreviation:<input name="abbreviation" type="text"><br> -->
<!-- 	Data type name:<input name="dtname" type="text"><br>
	Data type desc:<input name="dtdescription" type="text"><br>
	Concept class name:<input name="classname" type="text"><br>
	concept class desc:<input name="classdescription" type="text"><br>  -->
	Opis bolesti: <textarea name="description" maxLength=1000 cols="25" rows="5">Opisite bolest...</textarea> <br>
	Dodatne zabiljeske:<textarea name="additionalnotes" maxLength=1000 cols="25" rows="5">Dodajte zabiljeske...</textarea><br>
	<input type="submit" value="Submit">
</form>
<%
	// 	ObjectMapper mapper = new ObjectMapper();
	if (request.getAttribute("indexingResult") != null) {
		IndexingResult result = (IndexingResult) request
				.getAttribute("indexingResult");
		if (result.getError() == "") {
			out.print("Success!\n");
			out.print(result.getHttpResponse().getEntity().getContent()
					.toString());
		} else
			out.print(result.getError());
	}
%>
</center>
<br/><br/>

<%@ include file="/WEB-INF/template/footer.jsp" %>