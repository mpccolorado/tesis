<%@ page import="tesis.articulo.ArticuloComprado" %>
<ui:table paginationTotal="${articuloCompradoInstanceTotal}" paginationParams="${params}" action="table">
    <ui:theader>
        <g:sortableColumn class='ajax' action='table' property='afectaStock' params='${params}'
                          title='${message(code: 'articuloComprado.afectaStock.label', default: 'Afecta Stock')}'/>
        <g:sortableColumn class='ajax' action='table' property='descripcion' params='${params}'
                          title='${message(code: 'articuloComprado.descripcion.label', default: 'Descripcion')}'/>
        <th class='header'><g:message code='articuloComprado.lugarDePreparacion.label'
                                      default='Lugar De Preparacion'/></th>
        <th class='header'><g:message code='articuloComprado.marca.label' default='Marca'/></th>
        <g:sortableColumn class='ajax' action='table' property='nombre' params='${params}'
                          title='${message(code: 'articuloComprado.nombre.label', default: 'Nombre')}'/>
        <th class='header'><g:message code='articuloComprado.presentacion.label' default='Presentacion'/></th>

        <th class="actionsColumn"></th>
    </ui:theader>
    <ui:tbody>
        <tr>
            <td>
                <input name="afectaStock" type="text" value="${params.afectaStock}" class="input-block-level"/>
            </td>
            <td>
                <input name="descripcion" type="text" value="${params.descripcion}" class="input-block-level"/>
            </td>
            <td>
                <ui:select id="search-lugarDePreparacion" name="lugarDePreparacion"
                           from="${tesis.articulo.LugarDePreparacion.list()}" required="false"
                           value="${params.lugarDePreparacion}" class="input-block-level"/>
            </td>
            <td>
                <ui:select id="search-marca" name="marca" from="${tesis.articulo.Marca.list()}" required="false"
                           value="${params.marca}" class="input-block-level"/>
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
        <g:each in="${articuloCompradoInstanceList}" var="articuloCompradoInstance">
            <tr>
                <td>
                    <g:link action='show' id='${articuloCompradoInstance.id}'>
                        <g:formatBoolean boolean='${articuloCompradoInstance.afectaStock}'/>
                    </g:link>
                </td>
                <td>
                    ${fieldValue(bean: articuloCompradoInstance, field: 'descripcion')}
                </td>
                <td>
                    ${fieldValue(bean: articuloCompradoInstance, field: 'lugarDePreparacion')}
                </td>
                <td>
                    ${fieldValue(bean: articuloCompradoInstance, field: 'marca')}
                </td>
                <td>
                    ${fieldValue(bean: articuloCompradoInstance, field: 'nombre')}
                </td>
                <td>
                    ${fieldValue(bean: articuloCompradoInstance, field: 'presentacion')}
                </td>

                <ui:actionColumn id="${articuloCompradoInstance.id}"/>
            </tr>
        </g:each>
    </ui:tbody>
</ui:table>