<%@ page import="tesis.articulo.Marca" %>
<ui:table paginationTotal="${marcaInstanceTotal}" paginationParams="${params}" action="table">
    <ui:theader>
        <g:sortableColumn class='ajax' action='table' property='nombre' params='${params}'
                          title='${message(code: 'marca.nombre.label', default: 'Nombre')}'/>

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
        <g:each in="${marcaInstanceList}" var="marcaInstance">
            <tr>
                <td>
                    <g:link action='show' id='${marcaInstance.id}'>
                        ${fieldValue(bean: marcaInstance, field: 'nombre')}
                    </g:link>
                </td>

                <ui:actionColumn id="${marcaInstance.id}"/>
            </tr>
        </g:each>
    </ui:tbody>
</ui:table>