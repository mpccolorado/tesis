<%@ page import="tesis.articulo.ArticuloElaborado" %>
<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'articuloElaborado.label', default: 'ArticuloElaborado')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:layoutResources/>
</head>

<body>
<g:render template="table"/>
<r:layoutResources/>
</body>
</html>