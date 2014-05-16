<%@ include file="/WEB-INF/template/include.jsp" %>

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp" %>
<%@page import="org.openmrs.module.lucenemodule.web.controller.model.SearchResult"%>
<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="org.codehaus.jackson.JsonNode"%>


<form method="post">
	Pronadi pacijenta s ID-em: <input name="id" type="text"> <br>
	Pretrazi tekst: <textarea name="query" maxLength=1000 cols="25" rows="1"></textarea> <br>
		<input type="submit" value="Submit">
</form>
<hr>
<%
	if (request.getAttribute("searchResult") != null) {
		SearchResult result = (SearchResult) request.getAttribute("searchResult");
		if (result.getError() == "") {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(result.getSearchResultJson());
			JsonNode results = rootNode.path("response");
			int numberOfResults = results.path("numFound").getIntValue();
			if (numberOfResults <= 0) {
				out.print("No results found!");
			} else {
				JsonNode resultsContent = results.path("docs");
				for (int i = 0; i < resultsContent.size(); i++) {
					out.print(resultsContent.get(i).toString());
				}
			}
		} else
			out.print(result.getError());
	}
%>

<br/><br/>

<%@ include file="/WEB-INF/template/footer.jsp" %>