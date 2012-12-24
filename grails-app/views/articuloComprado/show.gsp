<%@ page import="tesis.articulo.ArticuloComprado" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'articuloComprado.label', default: 'ArticuloComprado')}"/>
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
            <g:if test='${articuloCompradoInstance?.afectaStock}'>
                <dt><g:message code='articuloComprado.afectaStock.label' default='Afecta Stock'/></dt>
                <dd><g:formatBoolean boolean='${articuloCompradoInstance?.afectaStock}'/></dd>
            </g:if>
            <g:if test='${articuloCompradoInstance?.descripcion}'>
                <dt><g:message code='articuloComprado.descripcion.label' default='Descripcion'/></dt>
                <dd><g:fieldValue bean='${articuloCompradoInstance}' field='descripcion'/></dd>
            </g:if>
            <g:if test='${articuloCompradoInstance?.lugarDePreparacion}'>
                <dt><g:message code='articuloComprado.lugarDePreparacion.label' default='Lugar De Preparacion'/></dt>
                <dd><g:link controller='lugarDePreparacion' action='show'
                            id='${articuloCompradoInstance?.lugarDePreparacion?.id}'>${articuloCompradoInstance?.lugarDePreparacion?.encodeAsHTML()}</g:link></dd>
            </g:if>
            <g:if test='${articuloCompradoInstance?.marca}'>
                <dt><g:message code='articuloComprado.marca.label' default='Marca'/></dt>
                <dd><g:link controller='marca' action='show'
                            id='${articuloCompradoInstance?.marca?.id}'>${articuloCompradoInstance?.marca?.encodeAsHTML()}</g:link></dd>
            </g:if>
            <g:if test='${articuloCompradoInstance?.nombre}'>
                <dt><g:message code='articuloComprado.nombre.label' default='Nombre'/></dt>
                <dd><g:fieldValue bean='${articuloCompradoInstance}' field='nombre'/></dd>
            </g:if>
            <g:if test='${articuloCompradoInstance?.presentacion}'>
                <dt><g:message code='articuloComprado.presentacion.label' default='Presentacion'/></dt>
                <dd><g:link controller='presentacion' action='show'
                            id='${articuloCompradoInstance?.presentacion?.id}'>${articuloCompradoInstance?.presentacion?.encodeAsHTML()}</g:link></dd>
            </g:if>
            <g:if test='${articuloCompradoInstance?.seVende}'>
                <dt><g:message code='articuloComprado.seVende.label' default='Se Vende'/></dt>
                <dd><g:formatBoolean boolean='${articuloCompradoInstance?.seVende}'/></dd>
            </g:if>
            <g:if test='${articuloCompradoInstance?.subrubro}'>
                <dt><g:message code='articuloComprado.subrubro.label' default='Subrubro'/></dt>
                <dd><g:link controller='subrubro' action='show'
                            id='${articuloCompradoInstance?.subrubro?.id}'>${articuloCompradoInstance?.subrubro?.encodeAsHTML()}</g:link></dd>
            </g:if>

        </dl>

        <g:form>
            <g:hiddenField name="id" value="${articuloCompradoInstance?.id}"/>
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${articuloCompradoInstance?.id}">
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
