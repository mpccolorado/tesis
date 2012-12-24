<%@ page import="tesis.articulo.UnidadDeMedida" %>
<ui:table paginationTotal="${unidadDeMedidaInstanceTotal}" paginationParams="${params}" action="table">
    <ui:theader>
        <g:sortableColumn class='ajax' action='table' property='abreviacion' params='${params}'
                          title='${message(code: 'unidadDeMedida.abreviacion.label', default: 'Abreviacion')}'/>
        <g:sortableColumn class='ajax' action='table' property='nombre' params='${params}'
                          title='${message(code: 'unidadDeMedida.nombre.label', default: 'Nombre')}'/>

        <th class="actionsColumn"></th>
    </ui:theader>
    <ui:tbody>
        <tr>
            <td>
                <input name="abreviacion" type="text" value="${params.abreviacion}" class="input-block-level"/>
            </td>
            <td>
                <input name="nombre" type="text" value="${params.nombre}" class="input-block-level"/>
            </td>

            <td><button type="submit" class="btn btn-primary">
                <ui:iconApplicationSearch/>
            </button></td>
        </tr>
        <g:each in="${unidadDeMedidaInstanceList}" var="unidadDeMedidaInstance">
            <tr>
                <td>
                    <g:link action='show' id='${unidadDeMedidaInstance.id}'>
                        ${fieldValue(bean: unidadDeMedidaInstance, field: 'abreviacion')}
                    </g:link>
                </td>
                <td>
                    ${fieldValue(bean: unidadDeMedidaInstance, field: 'nombre')}
                </td>

                <ui:actionColumn id="${unidadDeMedidaInstance.id}"/>
            </tr>
        </g:each>
    </ui:tbody>
</ui:table>