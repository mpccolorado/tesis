<%@ page import="tesis.articulo.TipoDeArticulo" %>



<div class="fieldcontain ${hasErrors(bean: tipoDeArticuloInstance, field: 'nombre', 'error')} ">
    <label for="nombre">
        <g:message code="tipoDeArticulo.nombre.label" default="Nombre"/>

    </label>
    <g:textField name="nombre" value="${tipoDeArticuloInstance?.nombre}"/>
</div>

