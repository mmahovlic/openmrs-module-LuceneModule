<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="org.openmrs.module.lucenemodule.web.controller.model.IndexingResult"%>

<script language="JavaScript">
function Validate()
  {
	 var file =document.getElementById("datafile").value;
	 if(file!=''){
		  var checkfile = file.toLowerCase();
		  if (!checkimg.match(/(\.xml|\.csv)$/)){
			  alert("Please select file with .xml or .csv extensions");
			  document.getElementById("datafile").focus();
			  return false;
		    }
		 }
	 return true;
 }	
 
</script>
<form enctype="multipart/form-data" method="post" onSubmit="return Validate();">
<p>
Please specify a CSV file:<br>
<input type="file" name="datafile" size="40">
</p>
<div>
<input type="submit" value="Upload">
</div>
</form>
<hr>
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


<%@ include file="/WEB-INF/template/footer.jsp"%>