package tesis.articulo


import org.springframework.dao.DataIntegrityViolationException

class MarcaController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def marcaService

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

        def nombre = params.nombre ?: null

        def results = []
        if (nombre != null)
            results = marcaService.advancedSearch(nombre, params.sort, params.order, params.max, params.offset)
        else
            results = marcaService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [marcaInstanceList: itemsList, marcaInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [marcaInstance: new Marca(params)]
                break
            case 'POST':
                def marcaInstance = new Marca(params)
                if (!marcaInstance.save(flush: true)) {
                    render view: 'create', model: [marcaInstance: marcaInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'marca.label', default: 'Marca'), marcaInstance.id])
                redirect action: 'show', id: marcaInstance.id
                break
        }
    }

    def show() {
        def marcaInstance = Marca.get(params.id)
        if (!marcaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'marca.label', default: 'Marca'), params.id])
            redirect action: 'list'
            return
        }

        [marcaInstance: marcaInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def marcaInstance = Marca.get(params.id)
                if (!marcaInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'marca.label', default: 'Marca'), params.id])
                    redirect action: 'list'
                    return
                }

                [marcaInstance: marcaInstance]
                break
            case 'POST':
                def marcaInstance = Marca.get(params.id)
                if (!marcaInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'marca.label', default: 'Marca'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (marcaInstance.version > version) {
                        marcaInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'marca.label', default: 'Marca')] as Object[],
                                "Another user has updated this Marca while you were editing")
                        render view: 'edit', model: [marcaInstance: marcaInstance]
                        return
                    }
                }

                marcaInstance.properties = params

                if (!marcaInstance.save(flush: true)) {
                    render view: 'edit', model: [marcaInstance: marcaInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'marca.label', default: 'Marca'), marcaInstance.id])
                redirect action: 'show', id: marcaInstance.id
                break
        }
    }

    def delete() {
        def marcaInstance = Marca.get(params.id)
        if (!marcaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'marca.label', default: 'Marca'), params.id])
            redirect action: 'list'
            return
        }

        try {
            marcaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'marca.label', default: 'Marca'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'marca.label', default: 'Marca'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
