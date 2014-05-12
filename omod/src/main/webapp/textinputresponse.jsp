<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="org.codehaus.jackson.JsonNode"%>
<%@page import="org.openmrs.module.lucenemodule.web.controller.model.IndexingResult"%>
<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<center>
<%
// 	ObjectMapper mapper = new ObjectMapper();
	IndexingResult result = (IndexingResult) request
			.getAttribute("indexingResult");
	if (result.getError() == "") {
		out.print("Success!\n");
		out.print(result.getHttpResponse().getEntity().getContent().toString());
	} else
		out.print(result.getError());
%>
</center>
<%@ include file="/WEB-INF/template/footer.jsp"%>