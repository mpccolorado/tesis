<%@ page import="tesis.articulo.Ingrediente" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'ingrediente.label', default: 'Ingrediente')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="row-fluid">

    <g:render template="/controls/leftMenu" plugin="user-interface"/>

    <div class="span9">

        <div class="page-header">
            <h1><g:message code="default.show.label" args="[entityName]"/></h1>
        </div>

        <g:if test="${flash.message}">
            <ui:alert class="alert-info">${flash.message}</ui:alert>
        </g:if>

        <dl>
            <g:if test='${ingredienteInstance?.articulo}'>
                <dt><g:message code='ingrediente.articulo.label' default='Articulo'/></dt>
                <dd><g:link controller='articulo' action='show'
                            id='${ingredienteInstance?.articulo?.id}'>${ingredienteInstance?.articulo?.encodeAsHTML()}</g:link></dd>
            </g:if>
            <g:if test='${ingredienteInstance?.cantidad}'>
                <dt><g:message code='ingrediente.cantidad.label' default='Cantidad'/></dt>
                <dd><g:fieldValue bean='${ingredienteInstance}' field='cantidad'/></dd>
            </g:if>

        </dl>

        <g:form>
            <g:hiddenField name="id" value="${ingredienteInstance?.id}"/>
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${ingredienteInstance?.id}">
                    <i class="icon-pencil"></i>
                    <g:message code="default.button.edit.label" default="Edit"/>
                </g:link>
                <button class="btn btn-danger" type="submit" name="_action_delete">
                    <i class="icon-trash icon-white"></i>
                    <g:message code="default.button.delete.label" default="Delete"/>
                </button>
            </div>
        </g:form>

    </div>

</div>
</body>
</html>
