<%@ page import="tesis.articulo.Subrubro" %>
<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'subrubro.label', default: 'Subrubro')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:layoutResources/>
</head>

<body>
<g:render template="table"/>
<r:layoutResources/>
</body>
</html>