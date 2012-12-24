<%@ page import="tesis.articulo.Ingrediente" %>
<ui:table paginationTotal="${ingredienteInstanceTotal}" paginationParams="${params}" action="table">
    <ui:theader>
        <th class='header'><g:message code='ingrediente.articulo.label' default='Articulo'/></th>
        <g:sortableColumn class='ajax' action='table' property='cantidad' params='${params}'
                          title='${message(code: 'ingrediente.cantidad.label', default: 'Cantidad')}'/>

        <th class="actionsColumn"></th>
    </ui:theader>
    <ui:tbody>
        <tr>
            <td>
                <ui:select id="search-articulo" name="articulo" from="${tesis.articulo.Articulo.list()}"
                           required="false" value="${params.articulo}" class="input-block-level"/>
            </td>
            <td>
                <input name="cantidad" type="text" value="${params.cantidad}" class="input-block-level"/>
            </td>

            <td><button type="submit" class="btn btn-primary">
                <ui:iconApplicationSearch/>
            </button></td>
        </tr>
        <g:each in="${ingredienteInstanceList}" var="ingredienteInstance">
            <tr>
                <td>
                    <g:link action='show' id='${ingredienteInstance.id}'>
                        ${fieldValue(bean: ingredienteInstance, field: 'articulo')}
                    </g:link>
                </td>
                <td>
                    ${fieldValue(bean: ingredienteInstance, field: 'cantidad')}
                </td>

                <ui:actionColumn id="${ingredienteInstance.id}"/>
            </tr>
        </g:each>
    </ui:tbody>
</ui:table>