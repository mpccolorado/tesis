<%@ page import="tesis.articulo.ArticuloElaborado" %>



<div class="fieldcontain ${hasErrors(bean: articuloElaboradoInstance, field: 'descripcion', 'error')} ">
    <label for="descripcion">
        <g:message code="articuloElaborado.descripcion.label" default="Descripcion"/>

    </label>
    <g:textField name="descripcion" value="${articuloElaboradoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloElaboradoInstance, field: 'lugarDePreparacion', 'error')} required">
    <label for="lugarDePreparacion">
        <g:message code="articuloElaborado.lugarDePreparacion.label" default="Lugar De Preparacion"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="lugarDePreparacion" name="lugarDePreparacion.id" from="${tesis.articulo.LugarDePreparacion.list()}"
              optionKey="id" required="" value="${articuloElaboradoInstance?.lugarDePreparacion?.id}"
              class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloElaboradoInstance, field: 'nombre', 'error')} ">
    <label for="nombre">
        <g:message code="articuloElaborado.nombre.label" default="Nombre"/>

    </label>
    <g:textField name="nombre" value="${articuloElaboradoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloElaboradoInstance, field: 'presentacion', 'error')} required">
    <label for="presentacion">
        <g:message code="articuloElaborado.presentacion.label" default="Presentacion"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="presentacion" name="presentacion.id" from="${tesis.articulo.Presentacion.list()}" optionKey="id"
              required="" value="${articuloElaboradoInstance?.presentacion?.id}" class="many-to-one"/>
</div>

