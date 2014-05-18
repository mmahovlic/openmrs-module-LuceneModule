<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
<%@page import="org.openmrs.module.lucenemodule.web.controller.model.IndexingResult"%>

<html>
<head>
<title>Upload File Page</title>
</head>
<body>
<form:form method="POST" enctype="multipart/form-data"  > 
<!--    modelAttribute="uploadedFile">   -->
   <table>  
    <tr>  
     <td>Upload File: </td>  
     <td><input type="file" name="file" />  
     </td>  
    </tr>  
    <tr>  
     <td> </td>  
     <td><input type="submit" value="Upload" />  
     </td>  
     <td> </td>  
    </tr>  
   </table>  
  </form:form>  
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
</body>
</html>


<%@ include file="/WEB-INF/template/footer.jsp"%>