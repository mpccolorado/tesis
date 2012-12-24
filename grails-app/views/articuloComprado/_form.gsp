<%@ page import="tesis.articulo.ArticuloComprado" %>



<div class="fieldcontain ${hasErrors(bean: articuloCompradoInstance, field: 'afectaStock', 'error')} ">
    <label for="afectaStock">
        <g:message code="articuloComprado.afectaStock.label" default="Afecta Stock"/>

    </label>
    <g:checkBox name="afectaStock" value="${articuloCompradoInstance?.afectaStock}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloCompradoInstance, field: 'descripcion', 'error')} ">
    <label for="descripcion">
        <g:message code="articuloComprado.descripcion.label" default="Descripcion"/>

    </label>
    <g:textField name="descripcion" value="${articuloCompradoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloCompradoInstance, field: 'lugarDePreparacion', 'error')} required">
    <label for="lugarDePreparacion">
        <g:message code="articuloComprado.lugarDePreparacion.label" default="Lugar De Preparacion"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="lugarDePreparacion" name="lugarDePreparacion.id" from="${tesis.articulo.LugarDePreparacion.list()}"
              optionKey="id" required="" value="${articuloCompradoInstance?.lugarDePreparacion?.id}"
              class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloCompradoInstance, field: 'marca', 'error')} required">
    <label for="marca">
        <g:message code="articuloComprado.marca.label" default="Marca"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="marca" name="marca.id" from="${tesis.articulo.Marca.list()}" optionKey="id" required=""
              value="${articuloCompradoInstance?.marca?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloCompradoInstance, field: 'nombre', 'error')} ">
    <label for="nombre">
        <g:message code="articuloComprado.nombre.label" default="Nombre"/>

    </label>
    <g:textField name="nombre" value="${articuloCompradoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloCompradoInstance, field: 'presentacion', 'error')} required">
    <label for="presentacion">
        <g:message code="articuloComprado.presentacion.label" default="Presentacion"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="presentacion" name="presentacion.id" from="${tesis.articulo.Presentacion.list()}" optionKey="id"
              required="" value="${articuloCompradoInstance?.presentacion?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloCompradoInstance, field: 'seVende', 'error')} ">
    <label for="seVende">
        <g:message code="articuloComprado.seVende.label" default="Se Vende"/>

    </label>
    <g:checkBox name="seVende" value="${articuloCompradoInstance?.seVende}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloCompradoInstance, field: 'subrubro', 'error')} required">
    <label for="subrubro">
        <g:message code="articuloComprado.subrubro.label" default="Subrubro"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="subrubro" name="subrubro.id" from="${tesis.articulo.Subrubro.list()}" optionKey="id" required=""
              value="${articuloCompradoInstance?.subrubro?.id}" class="many-to-one"/>
</div>

