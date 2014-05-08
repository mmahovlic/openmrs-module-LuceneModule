<spring:htmlEscape defaultHtmlEscape="true" />
<ul id="menu">
	<li class="first"><a
		href="${pageContext.request.contextPath}/admin"><spring:message
				code="admin.title.short" /></a></li>

	<li
		<c:if test='<%= request.getRequestURI().contains("/manage") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/lucenemodule/manage.form"><spring:message
				code="lucenemodule.manage" /></a>
	</li>
	
	<li
		<c:if test='<%= request.getRequestURI().contains("/upload") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/lucenemodule/textinput.form"><spring:message
				code="Index text" /></a>
	</li>
	
	<li
		<c:if test='<%= request.getRequestURI().contains("/upload") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/lucenemodule/upload.form"><spring:message
				code="Index uploaded file" /></a>
	</li>
	
	<li
		<c:if test='<%= request.getRequestURI().contains("/upload") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/lucenemodule/databaseinput.form"><spring:message
				code="Index data from OpenMRS database" /></a>
	</li>
	
	<!-- Add further links here -->
</ul>
<h2>
	<spring:message code="lucenemodule.title" />
</h2>
