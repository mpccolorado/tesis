<%@ page import="tesis.articulo.ArticuloElaborado" %>
<ui:table paginationTotal="${articuloElaboradoInstanceTotal}" paginationParams="${params}" action="table">
    <ui:theader>
        <g:sortableColumn class='ajax' action='table' property='descripcion' params='${params}'
                          title='${message(code: 'articuloElaborado.descripcion.label', default: 'Descripcion')}'/>
        <th class='header'><g:message code='articuloElaborado.lugarDePreparacion.label'
                                      default='Lugar De Preparacion'/></th>
        <g:sortableColumn class='ajax' action='table' property='nombre' params='${params}'
                          title='${message(code: 'articuloElaborado.nombre.label', default: 'Nombre')}'/>
        <th class='header'><g:message code='articuloElaborado.presentacion.label' default='Presentacion'/></th>

        <th class="actionsColumn"></th>
    </ui:theader>
    <ui:tbody>
        <tr>
            <td>
                <input name="descripcion" type="text" value="${params.descripcion}" class="input-block-level"/>
            </td>
            <td>
                <ui:select id="search-lugarDePreparacion" name="lugarDePreparacion"
                           from="${tesis.articulo.LugarDePreparacion.list()}" required="false"
                           value="${params.lugarDePreparacion}" class="input-block-level"/>
            </td>
            <td>
                <input name="nombre" type="text" value="${params.nombre}" class="input-block-level"/>
            </td>
            <td>
                <ui:select id="search-presentacion" name="presentacion" from="${tesis.articulo.Presentacion.list()}"
                           required="false" value="${params.presentacion}" class="input-block-level"/>
            </td>

            <td><button type="submit" class="btn btn-primary">
                <ui:iconApplicationSearch/>
            </button></td>
        </tr>
        <g:each in="${articuloElaboradoInstanceList}" var="articuloElaboradoInstance">
            <tr>
                <td>
                    <g:link action='show' id='${articuloElaboradoInstance.id}'>
                        ${fieldValue(bean: articuloElaboradoInstance, field: 'descripcion')}
                    </g:link>
                </td>
                <td>
                    ${fieldValue(bean: articuloElaboradoInstance, field: 'lugarDePreparacion')}
                </td>
                <td>
                    ${fieldValue(bean: articuloElaboradoInstance, field: 'nombre')}
                </td>
                <td>
                    ${fieldValue(bean: articuloElaboradoInstance, field: 'presentacion')}
                </td>

                <ui:actionColumn id="${articuloElaboradoInstance.id}"/>
            </tr>
        </g:each>
    </ui:tbody>
</ui:table>