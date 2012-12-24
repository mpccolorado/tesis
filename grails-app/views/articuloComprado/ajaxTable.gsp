<%@ page import="tesis.articulo.ArticuloComprado" %>
<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'articuloComprado.label', default: 'ArticuloComprado')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:layoutResources/>
</head>

<body>
<g:render template="table"/>
<r:layoutResources/>
</body>
</html>