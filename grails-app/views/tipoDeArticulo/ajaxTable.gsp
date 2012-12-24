<%@ page import="tesis.articulo.TipoDeArticulo" %>
<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'tipoDeArticulo.label', default: 'TipoDeArticulo')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:layoutResources/>
</head>

<body>
<g:render template="table"/>
<r:layoutResources/>
</body>
</html>