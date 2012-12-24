<%@ page import="tesis.articulo.Rubro" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'rubro.label', default: 'Rubro')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="row-fluid">

    <g:render template="/controls/leftMenu" plugin="user-interface"/>

    <div class="span9">

        <div class="page-header">
            <h1><g:message code="default.create.label" args="[entityName]"/></h1>
        </div>

        <g:if test="${flash.message}">
            <ui:alert class="alert-info">${flash.message}</ui:alert>
        </g:if>

        <g:hasErrors bean="${rubroInstance}">
            <ui:alert class="alert-error">
                <ul>
                    <g:eachError bean="${rubroInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                error="${error}"/></li>
                    </g:eachError>
                </ul>
            </ui:alert>
        </g:hasErrors>

        <fieldset>
            <g:form class="form-horizontal" action="create">
                <fieldset>
                    <f:all bean="rubroInstance"/>
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            <i class="icon-ok icon-white"></i>
                            <g:message code="default.button.create.label" default="Create"/>
                        </button>
                    </div>
                </fieldset>
            </g:form>
        </fieldset>

    </div>

</div>
</body>
</html>
