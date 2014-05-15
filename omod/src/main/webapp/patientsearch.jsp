<%@ include file="/WEB-INF/template/include.jsp" %>

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp" %>
<%@page import="org.openmrs.module.lucenemodule.web.controller.model.SearchResult"%>


<form method="post">
	Pronadi pacijenta s ID-em: <input name="id" type="text"> <br>
	Pretrazi tekst: <textarea name="query" maxLength=1000 cols="25" rows="5"></textarea> <br>
		<input type="submit" value="Submit">
</form>
<hr>
<%
	// 	ObjectMapper mapper = new ObjectMapper();
	if (request.getAttribute("searchResult") != null) {
		SearchResult result = (SearchResult) request
				.getAttribute("searchResult");
		if (result.getError() == "") {
			out.print(result.getSearchResultJson());
		} else
			out.print(result.getError());
	}
	else out.print("No results found!");
%>

<br/><br/>

<%@ include file="/WEB-INF/template/footer.jsp" %>