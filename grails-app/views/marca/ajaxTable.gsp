<%@ page import="tesis.articulo.Marca" %>
<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'marca.label', default: 'Marca')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:layoutResources/>
</head>

<body>
<g:render template="table"/>
<r:layoutResources/>
</body>
</html>