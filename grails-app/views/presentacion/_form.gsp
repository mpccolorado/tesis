<%@ page import="tesis.articulo.Presentacion" %>



<div class="fieldcontain ${hasErrors(bean: presentacionInstance, field: 'nombre', 'error')} ">
    <label for="nombre">
        <g:message code="presentacion.nombre.label" default="Nombre"/>

    </label>
    <g:textField name="nombre" value="${presentacionInstance?.nombre}"/>
</div>

