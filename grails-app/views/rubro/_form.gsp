<%@ page import="tesis.articulo.Rubro" %>



<div class="fieldcontain ${hasErrors(bean: rubroInstance, field: 'descripcion', 'error')} ">
    <label for="descripcion">
        <g:message code="rubro.descripcion.label" default="Descripcion"/>

    </label>
    <g:textField name="descripcion" value="${rubroInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rubroInstance, field: 'nombre', 'error')} ">
    <label for="nombre">
        <g:message code="rubro.nombre.label" default="Nombre"/>

    </label>
    <g:textField name="nombre" value="${rubroInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rubroInstance, field: 'subrubros', 'error')} ">
    <label for="subrubros">
        <g:message code="rubro.subrubros.label" default="Subrubros"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${rubroInstance?.subrubros ?}" var="s">
            <li><g:link controller="subrubro" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="subrubro" action="create"
                    params="['rubro.id': rubroInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'subrubro.label', default: 'Subrubro')])}</g:link>
        </li>
    </ul>

</div>

