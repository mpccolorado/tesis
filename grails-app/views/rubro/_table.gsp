<%@ page import="tesis.articulo.Rubro" %>
<ui:table paginationTotal="${rubroInstanceTotal}" paginationParams="${params}" action="table">
    <ui:theader>
        <g:sortableColumn class='ajax' action='table' property='descripcion' params='${params}'
                          title='${message(code: 'rubro.descripcion.label', default: 'Descripcion')}'/>
        <g:sortableColumn class='ajax' action='table' property='nombre' params='${params}'
                          title='${message(code: 'rubro.nombre.label', default: 'Nombre')}'/>

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
        <g:each in="${rubroInstanceList}" var="rubroInstance">
            <tr>
                <td>
                    <g:link action='show' id='${rubroInstance.id}'>
                        ${fieldValue(bean: rubroInstance, field: 'descripcion')}
                    </g:link>
                </td>
                <td>
                    ${fieldValue(bean: rubroInstance, field: 'nombre')}
                </td>

                <ui:actionColumn id="${rubroInstance.id}"/>
            </tr>
        </g:each>
    </ui:tbody>
</ui:table>