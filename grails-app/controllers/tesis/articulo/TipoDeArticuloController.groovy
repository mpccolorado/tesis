package tesis.articulo


import org.springframework.dao.DataIntegrityViolationException

class TipoDeArticuloController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def tipoDeArticuloService

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
            results = tipoDeArticuloService.advancedSearch(nombre, params.sort, params.order, params.max, params.offset)
        else
            results = tipoDeArticuloService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [tipoDeArticuloInstanceList: itemsList, tipoDeArticuloInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [tipoDeArticuloInstance: new TipoDeArticulo(params)]
                break
            case 'POST':
                def tipoDeArticuloInstance = new TipoDeArticulo(params)
                if (!tipoDeArticuloInstance.save(flush: true)) {
                    render view: 'create', model: [tipoDeArticuloInstance: tipoDeArticuloInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'tipoDeArticulo.label', default: 'TipoDeArticulo'), tipoDeArticuloInstance.id])
                redirect action: 'show', id: tipoDeArticuloInstance.id
                break
        }
    }

    def show() {
        def tipoDeArticuloInstance = TipoDeArticulo.get(params.id)
        if (!tipoDeArticuloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDeArticulo.label', default: 'TipoDeArticulo'), params.id])
            redirect action: 'list'
            return
        }

        [tipoDeArticuloInstance: tipoDeArticuloInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def tipoDeArticuloInstance = TipoDeArticulo.get(params.id)
                if (!tipoDeArticuloInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDeArticulo.label', default: 'TipoDeArticulo'), params.id])
                    redirect action: 'list'
                    return
                }

                [tipoDeArticuloInstance: tipoDeArticuloInstance]
                break
            case 'POST':
                def tipoDeArticuloInstance = TipoDeArticulo.get(params.id)
                if (!tipoDeArticuloInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDeArticulo.label', default: 'TipoDeArticulo'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (tipoDeArticuloInstance.version > version) {
                        tipoDeArticuloInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'tipoDeArticulo.label', default: 'TipoDeArticulo')] as Object[],
                                "Another user has updated this TipoDeArticulo while you were editing")
                        render view: 'edit', model: [tipoDeArticuloInstance: tipoDeArticuloInstance]
                        return
                    }
                }

                tipoDeArticuloInstance.properties = params

                if (!tipoDeArticuloInstance.save(flush: true)) {
                    render view: 'edit', model: [tipoDeArticuloInstance: tipoDeArticuloInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoDeArticulo.label', default: 'TipoDeArticulo'), tipoDeArticuloInstance.id])
                redirect action: 'show', id: tipoDeArticuloInstance.id
                break
        }
    }

    def delete() {
        def tipoDeArticuloInstance = TipoDeArticulo.get(params.id)
        if (!tipoDeArticuloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDeArticulo.label', default: 'TipoDeArticulo'), params.id])
            redirect action: 'list'
            return
        }

        try {
            tipoDeArticuloInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoDeArticulo.label', default: 'TipoDeArticulo'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoDeArticulo.label', default: 'TipoDeArticulo'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
