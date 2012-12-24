<%@ page import="tesis.articulo.Presentacion" %>
<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'presentacion.label', default: 'Presentacion')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:layoutResources/>
</head>

<body>
<g:render template="table"/>
<r:layoutResources/>
</body>
</html>