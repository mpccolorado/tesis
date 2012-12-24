<%@ page import="tesis.articulo.Subrubro" %>
<ui:table paginationTotal="${subrubroInstanceTotal}" paginationParams="${params}" action="table">
    <ui:theader>
        <g:sortableColumn class='ajax' action='table' property='descripcion' params='${params}'
                          title='${message(code: 'subrubro.descripcion.label', default: 'Descripcion')}'/>
        <g:sortableColumn class='ajax' action='table' property='nombre' params='${params}'
                          title='${message(code: 'subrubro.nombre.label', default: 'Nombre')}'/>
        <th class='header'><g:message code='subrubro.rubro.label' default='Rubro'/></th>

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
            <td>
                <ui:select id="search-rubro" name="rubro" from="${tesis.articulo.Rubro.list()}" required="false"
                           value="${params.rubro}" class="input-block-level"/>
            </td>

            <td><button type="submit" class="btn btn-primary">
                <ui:iconApplicationSearch/>
            </button></td>
        </tr>
        <g:each in="${subrubroInstanceList}" var="subrubroInstance">
            <tr>
                <td>
                    <g:link action='show' id='${subrubroInstance.id}'>
                        ${fieldValue(bean: subrubroInstance, field: 'descripcion')}
                    </g:link>
                </td>
                <td>
                    ${fieldValue(bean: subrubroInstance, field: 'nombre')}
                </td>
                <td>
                    ${fieldValue(bean: subrubroInstance, field: 'rubro')}
                </td>

                <ui:actionColumn id="${subrubroInstance.id}"/>
            </tr>
        </g:each>
    </ui:tbody>
</ui:table>