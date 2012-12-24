<%import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass;
import plugin.ui.scaffold.ScaffoldDomain
%><%=packageName ? "package ${packageName}\n\n" : ''
%>class ${className}Service {
    <%
    def domain = new DefaultGrailsDomainClass( domainClass.clazz )
    def searchFields = ScaffoldDomain.getDomainProperties(domainClass, comparator)
    %>
    def search(search, sort, order, max, offset) {
        def results = ${className}.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        <%def paramNames = ""
        if(searchFields.size() > 0){
        %>{
            or{<%
                searchFields.each { field ->
                    paramNames += field.name + ", "
                    if(field.type == String){%>
                ilike("${field.name}", '%' + search + '%')<%
                    }
                    if(field.type == Integer || field.type == Double){%>
                eq("${field.name}", search)<%
                    }
                }%>
            }
        }
        <%	}
        %>return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(${paramNames}sort, order, max, offset) {
        def results = ${className}.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        <%if(searchFields.size() > 0){
        %>{
            and{<%
                searchFields.eachWithIndex { field, idx ->
                    if(idx < 6){
                        %>
                if(${field.name}){<%
                        if(field.type == String){%>
                    ilike("${field.name}", '%' + ${field.name} + '%')
                }<%
                }
                        else{%>
                    eq("${field.name}", ${field.name})
                }<%
                        }
                    }
                }%>
            }
        }
        <%	}
    %>return [total:results.getTotalCount(), results:results]
    }
}
