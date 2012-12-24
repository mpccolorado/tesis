package tesis.articulo


import org.springframework.dao.DataIntegrityViolationException

class PresentacionController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def presentacionService

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
            results = presentacionService.advancedSearch(nombre, params.sort, params.order, params.max, params.offset)
        else
            results = presentacionService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [presentacionInstanceList: itemsList, presentacionInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [presentacionInstance: new Presentacion(params)]
                break
            case 'POST':
                def presentacionInstance = new Presentacion(params)
                if (!presentacionInstance.save(flush: true)) {
                    render view: 'create', model: [presentacionInstance: presentacionInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'presentacion.label', default: 'Presentacion'), presentacionInstance.id])
                redirect action: 'show', id: presentacionInstance.id
                break
        }
    }

    def show() {
        def presentacionInstance = Presentacion.get(params.id)
        if (!presentacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'presentacion.label', default: 'Presentacion'), params.id])
            redirect action: 'list'
            return
        }

        [presentacionInstance: presentacionInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def presentacionInstance = Presentacion.get(params.id)
                if (!presentacionInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'presentacion.label', default: 'Presentacion'), params.id])
                    redirect action: 'list'
                    return
                }

                [presentacionInstance: presentacionInstance]
                break
            case 'POST':
                def presentacionInstance = Presentacion.get(params.id)
                if (!presentacionInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'presentacion.label', default: 'Presentacion'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (presentacionInstance.version > version) {
                        presentacionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'presentacion.label', default: 'Presentacion')] as Object[],
                                "Another user has updated this Presentacion while you were editing")
                        render view: 'edit', model: [presentacionInstance: presentacionInstance]
                        return
                    }
                }

                presentacionInstance.properties = params

                if (!presentacionInstance.save(flush: true)) {
                    render view: 'edit', model: [presentacionInstance: presentacionInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'presentacion.label', default: 'Presentacion'), presentacionInstance.id])
                redirect action: 'show', id: presentacionInstance.id
                break
        }
    }

    def delete() {
        def presentacionInstance = Presentacion.get(params.id)
        if (!presentacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'presentacion.label', default: 'Presentacion'), params.id])
            redirect action: 'list'
            return
        }

        try {
            presentacionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'presentacion.label', default: 'Presentacion'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'presentacion.label', default: 'Presentacion'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
