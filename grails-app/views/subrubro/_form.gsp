<%@ page import="tesis.articulo.Subrubro" %>



<div class="fieldcontain ${hasErrors(bean: subrubroInstance, field: 'descripcion', 'error')} ">
    <label for="descripcion">
        <g:message code="subrubro.descripcion.label" default="Descripcion"/>

    </label>
    <g:textField name="descripcion" value="${subrubroInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: subrubroInstance, field: 'nombre', 'error')} ">
    <label for="nombre">
        <g:message code="subrubro.nombre.label" default="Nombre"/>

    </label>
    <g:textField name="nombre" value="${subrubroInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: subrubroInstance, field: 'rubro', 'error')} required">
    <label for="rubro">
        <g:message code="subrubro.rubro.label" default="Rubro"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="rubro" name="rubro.id" from="${tesis.articulo.Rubro.list()}" optionKey="id" required=""
              value="${subrubroInstance?.rubro?.id}" class="many-to-one"/>
</div>

