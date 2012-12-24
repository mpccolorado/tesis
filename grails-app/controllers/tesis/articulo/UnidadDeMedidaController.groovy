package tesis.articulo


import org.springframework.dao.DataIntegrityViolationException

class UnidadDeMedidaController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def unidadDeMedidaService

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

        def abreviacion = params.abreviacion ?: null
        def nombre = params.nombre ?: null

        def results = []
        if (abreviacion != null || nombre != null)
            results = unidadDeMedidaService.advancedSearch(abreviacion, nombre, params.sort, params.order, params.max, params.offset)
        else
            results = unidadDeMedidaService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [unidadDeMedidaInstanceList: itemsList, unidadDeMedidaInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [unidadDeMedidaInstance: new UnidadDeMedida(params)]
                break
            case 'POST':
                def unidadDeMedidaInstance = new UnidadDeMedida(params)
                if (!unidadDeMedidaInstance.save(flush: true)) {
                    render view: 'create', model: [unidadDeMedidaInstance: unidadDeMedidaInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'unidadDeMedida.label', default: 'UnidadDeMedida'), unidadDeMedidaInstance.id])
                redirect action: 'show', id: unidadDeMedidaInstance.id
                break
        }
    }

    def show() {
        def unidadDeMedidaInstance = UnidadDeMedida.get(params.id)
        if (!unidadDeMedidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadDeMedida.label', default: 'UnidadDeMedida'), params.id])
            redirect action: 'list'
            return
        }

        [unidadDeMedidaInstance: unidadDeMedidaInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def unidadDeMedidaInstance = UnidadDeMedida.get(params.id)
                if (!unidadDeMedidaInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadDeMedida.label', default: 'UnidadDeMedida'), params.id])
                    redirect action: 'list'
                    return
                }

                [unidadDeMedidaInstance: unidadDeMedidaInstance]
                break
            case 'POST':
                def unidadDeMedidaInstance = UnidadDeMedida.get(params.id)
                if (!unidadDeMedidaInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadDeMedida.label', default: 'UnidadDeMedida'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (unidadDeMedidaInstance.version > version) {
                        unidadDeMedidaInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'unidadDeMedida.label', default: 'UnidadDeMedida')] as Object[],
                                "Another user has updated this UnidadDeMedida while you were editing")
                        render view: 'edit', model: [unidadDeMedidaInstance: unidadDeMedidaInstance]
                        return
                    }
                }

                unidadDeMedidaInstance.properties = params

                if (!unidadDeMedidaInstance.save(flush: true)) {
                    render view: 'edit', model: [unidadDeMedidaInstance: unidadDeMedidaInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'unidadDeMedida.label', default: 'UnidadDeMedida'), unidadDeMedidaInstance.id])
                redirect action: 'show', id: unidadDeMedidaInstance.id
                break
        }
    }

    def delete() {
        def unidadDeMedidaInstance = UnidadDeMedida.get(params.id)
        if (!unidadDeMedidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadDeMedida.label', default: 'UnidadDeMedida'), params.id])
            redirect action: 'list'
            return
        }

        try {
            unidadDeMedidaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'unidadDeMedida.label', default: 'UnidadDeMedida'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'unidadDeMedida.label', default: 'UnidadDeMedida'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
