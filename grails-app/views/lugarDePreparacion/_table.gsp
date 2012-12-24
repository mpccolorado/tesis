<%@ page import="tesis.articulo.LugarDePreparacion" %>
<ui:table paginationTotal="${lugarDePreparacionInstanceTotal}" paginationParams="${params}" action="table">
    <ui:theader>
        <g:sortableColumn class='ajax' action='table' property='descripcion' params='${params}'
                          title='${message(code: 'lugarDePreparacion.descripcion.label', default: 'Descripcion')}'/>
        <g:sortableColumn class='ajax' action='table' property='nombre' params='${params}'
                          title='${message(code: 'lugarDePreparacion.nombre.label', default: 'Nombre')}'/>

        <th class="actionsColumn"></th>
    </ui:theader>
    <ui:tbody>
        <tr>
            <td>
                <input name="descripcion" type="text" value="${params.descripcion}" class="input-block-level"/>
            </td>
            <td>
                <input name="nombre" type="text" value="${params.nombre}" class="input-block-level"/>
            </td>

            <td><button type="submit" class="btn btn-primary">
                <ui:iconApplicationSearch/>
            </button></td>
        </tr>
        <g:each in="${lugarDePreparacionInstanceList}" var="lugarDePreparacionInstance">
            <tr>
                <td>
                    <g:link action='show' id='${lugarDePreparacionInstance.id}'>
                        ${fieldValue(bean: lugarDePreparacionInstance, field: 'descripcion')}
                    </g:link>
                </td>
                <td>
                    ${fieldValue(bean: lugarDePreparacionInstance, field: 'nombre')}
                </td>

                <ui:actionColumn id="${lugarDePreparacionInstance.id}"/>
            </tr>
        </g:each>
    </ui:tbody>
</ui:table>