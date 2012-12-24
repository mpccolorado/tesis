<%@ page import="tesis.articulo.Rubro" %>
<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'rubro.label', default: 'Rubro')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:layoutResources/>
</head>

<body>
<g:render template="table"/>
<r:layoutResources/>
</body>
</html>