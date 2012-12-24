<%import plugin.ui.scaffold.ScaffoldDomain
import org.codehaus.groovy.grails.commons.GrailsClassUtils;
%><%=packageName ? "package ${packageName}\n\n" : ''%>
import org.springframework.dao.DataIntegrityViolationException
<%def serviceNameInCamelCase = className[0].toLowerCase() + className[1..className.size()-1] + "Service"
%>class ${className}Controller {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
	def ${serviceNameInCamelCase}
    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        getTableModel();
    }
	
	def table() {
		def model = getTableModel();
		render(view: "ajaxTable", model: model)
	}
	
	private def getTableModel(){
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.q = params.search?.trim()
		params.q = params.q?:""
		params.offset = params.int('offset')?:0

        <%def props = ScaffoldDomain.getDomainProperties(domainClass, comparator)
        def paramsCondition = ""
        def advancedParams = ""
        props.eachWithIndex { p, i ->
            if(i<6){
        %>def ${p.name} = ${getConvertedParameter(p)}
        <%
                paramsCondition += p.name + " != null"
                advancedParams += p.name + ", "
                if(i != 5 && i!= props.size() - 1){
                    paramsCondition += " || "
                }
            }
        }
		%>
        def results = []
        if(${paramsCondition})
            results = ${serviceNameInCamelCase}.advancedSearch(${advancedParams}params.sort, params.order, params.max, params.offset)
        else
            results = ${serviceNameInCamelCase}.search(params.q, params.sort, params.order, params.max, params.offset)
		def total = results.total
		def itemsList = results.results
		
		[${propertyName}List: itemsList, ${propertyName}Total: total, params: params]
	}

    <%
    def getConvertedParameter(parameter){
        if(parameter.isAssociation())
            return "params.${parameter.name} ? ${parameter.getType().name}.findById(params.${parameter.name}) : null"
        else if (parameter.type == Boolean || parameter.type == boolean)
            return "params.${parameter.name} ? params.boolean('${parameter.name}') : null"
        else if (parameter.type == Date || parameter.type == java.sql.Date || parameter.type == java.sql.Time || parameter.type == Calendar)
            return "params.${parameter.name} ? params.date('${parameter.name}') : null"
        else if (parameter.type == Short)
            return "params.${parameter.name} ? params.short('${parameter.name}') : null"
        else if (parameter.type == Integer)
            return "params.${parameter.name} ? params.int('${parameter.name}') : null"
        else if (parameter.type == Double)
            return "params.${parameter.name} ? params.double('${parameter.name}') : null"
        else
            return "params.${parameter.name} ?: null"
    }
%>

    def create() {
		switch (request.method) {
		case 'GET':
        	[${propertyName}: new ${className}(params)]
			break
		case 'POST':
	        def ${propertyName} = new ${className}(params)
	        if (!${propertyName}.save(flush: true)) {
	            render view: 'create', model: [${propertyName}: ${propertyName}]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.id])
	        redirect action: 'show', id: ${propertyName}.id
			break
		}
    }

    def show() {
        def ${propertyName} = ${className}.get(params.id)
        if (!${propertyName}) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
            redirect action: 'list'
            return
        }

        [${propertyName}: ${propertyName}]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def ${propertyName} = ${className}.get(params.id)
	        if (!${propertyName}) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [${propertyName}: ${propertyName}]
			break
		case 'POST':
	        def ${propertyName} = ${className}.get(params.id)
	        if (!${propertyName}) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (${propertyName}.version > version) {<% def lowerCaseName = grails.util.GrailsNameUtils.getPropertyName(className) %>
	                ${propertyName}.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: '${domainClass.propertyName}.label', default: '${className}')] as Object[],
	                          "Another user has updated this ${className} while you were editing")
	                render view: 'edit', model: [${propertyName}: ${propertyName}]
	                return
	            }
	        }

	        ${propertyName}.properties = params

	        if (!${propertyName}.save(flush: true)) {
	            render view: 'edit', model: [${propertyName}: ${propertyName}]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.id])
	        redirect action: 'show', id: ${propertyName}.id
			break
		}
    }

    def delete() {
        def ${propertyName} = ${className}.get(params.id)
        if (!${propertyName}) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
            redirect action: 'list'
            return
        }

        try {
            ${propertyName}.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
