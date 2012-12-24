<%@ page import="tesis.articulo.TipoDeArticulo" %>
<ui:table paginationTotal="${tipoDeArticuloInstanceTotal}" paginationParams="${params}" action="table">
    <ui:theader>
        <g:sortableColumn class='ajax' action='table' property='nombre' params='${params}'
                          title='${message(code: 'tipoDeArticulo.nombre.label', default: 'Nombre')}'/>

        <th class="actionsColumn"></th>
    </ui:theader>
    <ui:tbody>
        <tr>
            <td>
                <input name="nombre" type="text" value="${params.nombre}" class="input-block-level"/>
            </td>

            <td><button type="submit" class="btn btn-primary">
                <ui:iconApplicationSearch/>
            </button></td>
        </tr>
        <g:each in="${tipoDeArticuloInstanceList}" var="tipoDeArticuloInstance">
            <tr>
                <td>
                    <g:link action='show' id='${tipoDeArticuloInstance.id}'>
                        ${fieldValue(bean: tipoDeArticuloInstance, field: 'nombre')}
                    </g:link>
                </td>

                <ui:actionColumn id="${tipoDeArticuloInstance.id}"/>
            </tr>
        </g:each>
    </ui:tbody>
</ui:table>