<%@ page import="tesis.articulo.Subrubro" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'subrubro.label', default: 'Subrubro')}"/>
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
            <g:if test='${subrubroInstance?.descripcion}'>
                <dt><g:message code='subrubro.descripcion.label' default='Descripcion'/></dt>
                <dd><g:fieldValue bean='${subrubroInstance}' field='descripcion'/></dd>
            </g:if>
            <g:if test='${subrubroInstance?.nombre}'>
                <dt><g:message code='subrubro.nombre.label' default='Nombre'/></dt>
                <dd><g:fieldValue bean='${subrubroInstance}' field='nombre'/></dd>
            </g:if>
            <g:if test='${subrubroInstance?.rubro}'>
                <dt><g:message code='subrubro.rubro.label' default='Rubro'/></dt>
                <dd><g:link controller='rubro' action='show'
                            id='${subrubroInstance?.rubro?.id}'>${subrubroInstance?.rubro?.encodeAsHTML()}</g:link></dd>
            </g:if>

        </dl>

        <g:form>
            <g:hiddenField name="id" value="${subrubroInstance?.id}"/>
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${subrubroInstance?.id}">
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
