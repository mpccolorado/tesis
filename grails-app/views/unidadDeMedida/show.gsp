<%@ page import="tesis.articulo.UnidadDeMedida" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'unidadDeMedida.label', default: 'UnidadDeMedida')}"/>
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
            <g:if test='${unidadDeMedidaInstance?.abreviacion}'>
                <dt><g:message code='unidadDeMedida.abreviacion.label' default='Abreviacion'/></dt>
                <dd><g:fieldValue bean='${unidadDeMedidaInstance}' field='abreviacion'/></dd>
            </g:if>
            <g:if test='${unidadDeMedidaInstance?.nombre}'>
                <dt><g:message code='unidadDeMedida.nombre.label' default='Nombre'/></dt>
                <dd><g:fieldValue bean='${unidadDeMedidaInstance}' field='nombre'/></dd>
            </g:if>

        </dl>

        <g:form>
            <g:hiddenField name="id" value="${unidadDeMedidaInstance?.id}"/>
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${unidadDeMedidaInstance?.id}">
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
