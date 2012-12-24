<%@ page import="tesis.articulo.Marca" %>



<div class="fieldcontain ${hasErrors(bean: marcaInstance, field: 'nombre', 'error')} ">
    <label for="nombre">
        <g:message code="marca.nombre.label" default="Nombre"/>

    </label>
    <g:textField name="nombre" value="${marcaInstance?.nombre}"/>
</div>

