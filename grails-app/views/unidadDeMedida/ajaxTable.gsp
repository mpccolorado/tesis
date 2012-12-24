<%@ page import="tesis.articulo.UnidadDeMedida" %>
<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'unidadDeMedida.label', default: 'UnidadDeMedida')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:layoutResources/>
</head>

<body>
<g:render template="table"/>
<r:layoutResources/>
</body>
</html>