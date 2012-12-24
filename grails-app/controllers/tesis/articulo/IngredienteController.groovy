package tesis.articulo


import org.springframework.dao.DataIntegrityViolationException

class IngredienteController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def ingredienteService

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

    private def getTableModel() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.q = params.search?.trim()
        params.q = params.q ?: ""
        params.offset = params.int('offset') ?: 0

        def articulo = params.articulo ? tesis.articulo.Articulo.findById(params.articulo) : null
        def cantidad = params.cantidad ? params.double('cantidad') : null

        def results = []
        if (articulo != null || cantidad != null)
            results = ingredienteService.advancedSearch(articulo, cantidad, params.sort, params.order, params.max, params.offset)
        else
            results = ingredienteService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [ingredienteInstanceList: itemsList, ingredienteInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [ingredienteInstance: new Ingrediente(params)]
                break
            case 'POST':
                def ingredienteInstance = new Ingrediente(params)
                if (!ingredienteInstance.save(flush: true)) {
                    render view: 'create', model: [ingredienteInstance: ingredienteInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'ingrediente.label', default: 'Ingrediente'), ingredienteInstance.id])
                redirect action: 'show', id: ingredienteInstance.id
                break
        }
    }

    def show() {
        def ingredienteInstance = Ingrediente.get(params.id)
        if (!ingredienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ingrediente.label', default: 'Ingrediente'), params.id])
            redirect action: 'list'
            return
        }

        [ingredienteInstance: ingredienteInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def ingredienteInstance = Ingrediente.get(params.id)
                if (!ingredienteInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'ingrediente.label', default: 'Ingrediente'), params.id])
                    redirect action: 'list'
                    return
                }

                [ingredienteInstance: ingredienteInstance]
                break
            case 'POST':
                def ingredienteInstance = Ingrediente.get(params.id)
                if (!ingredienteInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'ingrediente.label', default: 'Ingrediente'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (ingredienteInstance.version > version) {
                        ingredienteInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'ingrediente.label', default: 'Ingrediente')] as Object[],
                                "Another user has updated this Ingrediente while you were editing")
                        render view: 'edit', model: [ingredienteInstance: ingredienteInstance]
                        return
                    }
                }

                ingredienteInstance.properties = params

                if (!ingredienteInstance.save(flush: true)) {
                    render view: 'edit', model: [ingredienteInstance: ingredienteInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'ingrediente.label', default: 'Ingrediente'), ingredienteInstance.id])
                redirect action: 'show', id: ingredienteInstance.id
                break
        }
    }

    def delete() {
        def ingredienteInstance = Ingrediente.get(params.id)
        if (!ingredienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ingrediente.label', default: 'Ingrediente'), params.id])
            redirect action: 'list'
            return
        }

        try {
            ingredienteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ingrediente.label', default: 'Ingrediente'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ingrediente.label', default: 'Ingrediente'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
