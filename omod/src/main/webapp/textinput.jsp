<%@ include file="/WEB-INF/template/include.jsp" %>

<%@ include file="/WEB-INF/template/header.jsp" %>

<center>

<form method="post">
	First name: <input name="name" type="text"> <br>
	Last name: <input name="surname" type="text"><br>
	Middle name: <input name="middlename" type="text"><br>
<!-- 	Short:<input name="shortcause" type="text"><br>  -->
<!-- 	Post mortem:<input name="postmortem" type="text"><br>
	Abbreviation:<input name="abbreviation" type="text"><br> -->
<!-- 	Data type name:<input name="dtname" type="text"><br>
	Data type desc:<input name="dtdescription" type="text"><br>
	Concept class name:<input name="classname" type="text"><br>
	concept class desc:<input name="classdescription" type="text"><br>  -->
	Illness Description: <input name="description" type="text"> <br>
	Additional Medical Notes:<input name="additionalnotes" type="text"><br>
	<input type="submit" value="Submit">
</form>
</center>
<br/><br/>

<%@ include file="/WEB-INF/template/footer.jsp" %>