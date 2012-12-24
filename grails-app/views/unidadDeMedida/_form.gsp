<%@ page import="tesis.articulo.UnidadDeMedida" %>



<div class="fieldcontain ${hasErrors(bean: unidadDeMedidaInstance, field: 'abreviacion', 'error')} ">
    <label for="abreviacion">
        <g:message code="unidadDeMedida.abreviacion.label" default="Abreviacion"/>

    </label>
    <g:textField name="abreviacion" value="${unidadDeMedidaInstance?.abreviacion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: unidadDeMedidaInstance, field: 'nombre', 'error')} ">
    <label for="nombre">
        <g:message code="unidadDeMedida.nombre.label" default="Nombre"/>

    </label>
    <g:textField name="nombre" value="${unidadDeMedidaInstance?.nombre}"/>
</div>

