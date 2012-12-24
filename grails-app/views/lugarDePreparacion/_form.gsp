<%@ page import="tesis.articulo.LugarDePreparacion" %>



<div class="fieldcontain ${hasErrors(bean: lugarDePreparacionInstance, field: 'descripcion', 'error')} ">
    <label for="descripcion">
        <g:message code="lugarDePreparacion.descripcion.label" default="Descripcion"/>

    </label>
    <g:textField name="descripcion" value="${lugarDePreparacionInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lugarDePreparacionInstance, field: 'nombre', 'error')} ">
    <label for="nombre">
        <g:message code="lugarDePreparacion.nombre.label" default="Nombre"/>

    </label>
    <g:textField name="nombre" value="${lugarDePreparacionInstance?.nombre}"/>
</div>

