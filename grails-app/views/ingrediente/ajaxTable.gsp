<%@ page import="tesis.articulo.Ingrediente" %>
<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'ingrediente.label', default: 'Ingrediente')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:layoutResources/>
</head>

<body>
<g:render template="table"/>
<r:layoutResources/>
</body>
</html>