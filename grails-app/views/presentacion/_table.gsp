<%@ page import="tesis.articulo.Presentacion" %>
<ui:table paginationTotal="${presentacionInstanceTotal}" paginationParams="${params}" action="table">
    <ui:theader>
        <g:sortableColumn class='ajax' action='table' property='nombre' params='${params}'
                          title='${message(code: 'presentacion.nombre.label', default: 'Nombre')}'/>

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
        <g:each in="${presentacionInstanceList}" var="presentacionInstance">
            <tr>
                <td>
                    <g:link action='show' id='${presentacionInstance.id}'>
                        ${fieldValue(bean: presentacionInstance, field: 'nombre')}
                    </g:link>
                </td>

                <ui:actionColumn id="${presentacionInstance.id}"/>
            </tr>
        </g:each>
    </ui:tbody>
</ui:table>