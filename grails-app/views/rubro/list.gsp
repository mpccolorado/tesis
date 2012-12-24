<%@ page import="tesis.articulo.Rubro" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'rubro.label', default: 'Rubro')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:require module="ajaxTable"/>
</head>

<body>
<div class="row-fluid">

    <g:render template="/controls/leftMenu" plugin="user-interface"/>

    <div class="span9">

        <div class="page-header">
            <h1><g:message code="default.list.label" args="[entityName]"/></h1>
        </div>

        <g:if test="${flash.message}">
            <ui:alert class="alert-info">${flash.message}</ui:alert>
        </g:if>

        <g:render template="table"></g:render>
    </div>

</div>
</body>
</html>
