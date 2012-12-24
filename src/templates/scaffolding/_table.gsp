<% import plugin.ui.scaffold.ScaffoldTable %>
<%=packageName%>
<ui:table paginationTotal="\${${propertyName}Total}" paginationParams="\${params}" action="table">
	<ui:theader>
		${ScaffoldTable.getTableHeaders(domainClass, comparator)}
		<th class="actionsColumn"></th>
	</ui:theader>
	<ui:tbody>
        <tr>
        ${ScaffoldTable.getSearchRow(domainClass, comparator, propertyName)}
            <td><button type="submit" class="btn btn-primary">
            <ui:iconApplicationSearch/>
        </button></td>
        </tr>
		<g:each in="\${${propertyName}List}" var="${propertyName}">
			<tr>
				${ScaffoldTable.getTableRow(domainClass, comparator, propertyName)}
				<ui:actionColumn id="\${${propertyName}.id}"/>
			</tr>
		</g:each>
	</ui:tbody>
</ui:table>