<%@ page import="tesis.articulo.LugarDePreparacion" %>
<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'lugarDePreparacion.label', default: 'LugarDePreparacion')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:layoutResources/>
</head>

<body>
<g:render template="table"/>
<r:layoutResources/>
</body>
</html>