<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">

            <g:render template="/controls/leftMenu" plugin="user-interface"/>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
				</div>

				<g:if test="\${flash.message}">
				<ui:alert class="alert-info">\${flash.message}</ui:alert>
				</g:if>

				<g:hasErrors bean="\${${propertyName}}">
				<ui:alert class="alert-error">
				<ul>
					<g:eachError bean="\${${propertyName}}" var="error">
					<li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></li>
					</g:eachError>
				</ul>
				</ui:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="form-horizontal" action="edit" id="\${${propertyName}?.id}" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
						<g:hiddenField name="version" value="\${${propertyName}?.version}" />
						<fieldset>
							<f:all bean="${propertyName}"/>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.update.label" default="Update" />
								</button>
								<button type="submit" class="btn btn-danger" name="_action_delete" formnovalidate>
									<i class="icon-trash icon-white"></i>
									<g:message code="default.button.delete.label" default="Delete" />
								</button>
							</div>
						</fieldset>
					</g:form>
				</fieldset>

			</div>

		</div>
	</body>
</html>
