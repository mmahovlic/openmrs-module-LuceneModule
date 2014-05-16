<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<form enctype="multipart/form-data" method="post">
<p>
Please specify a file:<br>
<input type="file" name="datafile" size="40">
</p>
<div>
<input type="submit" value="Upload">
</div>
</form>


<%@ include file="/WEB-INF/template/footer.jsp"%>