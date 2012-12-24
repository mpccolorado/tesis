<%@ page import="tesis.articulo.ArticuloElaborado" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'articuloElaborado.label', default: 'ArticuloElaborado')}"/>
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
            <g:if test='${articuloElaboradoInstance?.descripcion}'>
                <dt><g:message code='articuloElaborado.descripcion.label' default='Descripcion'/></dt>
                <dd><g:fieldValue bean='${articuloElaboradoInstance}' field='descripcion'/></dd>
            </g:if>
            <g:if test='${articuloElaboradoInstance?.lugarDePreparacion}'>
                <dt><g:message code='articuloElaborado.lugarDePreparacion.label' default='Lugar De Preparacion'/></dt>
                <dd><g:link controller='lugarDePreparacion' action='show'
                            id='${articuloElaboradoInstance?.lugarDePreparacion?.id}'>${articuloElaboradoInstance?.lugarDePreparacion?.encodeAsHTML()}</g:link></dd>
            </g:if>
            <g:if test='${articuloElaboradoInstance?.nombre}'>
                <dt><g:message code='articuloElaborado.nombre.label' default='Nombre'/></dt>
                <dd><g:fieldValue bean='${articuloElaboradoInstance}' field='nombre'/></dd>
            </g:if>
            <g:if test='${articuloElaboradoInstance?.presentacion}'>
                <dt><g:message code='articuloElaborado.presentacion.label' default='Presentacion'/></dt>
                <dd><g:link controller='presentacion' action='show'
                            id='${articuloElaboradoInstance?.presentacion?.id}'>${articuloElaboradoInstance?.presentacion?.encodeAsHTML()}</g:link></dd>
            </g:if>

        </dl>

        <g:form>
            <g:hiddenField name="id" value="${articuloElaboradoInstance?.id}"/>
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${articuloElaboradoInstance?.id}">
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
