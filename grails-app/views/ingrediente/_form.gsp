<%@ page import="tesis.articulo.Ingrediente" %>



<div class="fieldcontain ${hasErrors(bean: ingredienteInstance, field: 'articulo', 'error')} required">
    <label for="articulo">
        <g:message code="ingrediente.articulo.label" default="Articulo"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="articulo" name="articulo.id" from="${tesis.articulo.Articulo.list()}" optionKey="id" required=""
              value="${ingredienteInstance?.articulo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ingredienteInstance, field: 'cantidad', 'error')} required">
    <label for="cantidad">
        <g:message code="ingrediente.cantidad.label" default="Cantidad"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="cantidad" value="${fieldValue(bean: ingredienteInstance, field: 'cantidad')}" required=""/>
</div>

