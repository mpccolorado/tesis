package tesis.articulo


import org.springframework.dao.DataIntegrityViolationException

class RubroController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def rubroService

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

        def descripcion = params.descripcion ?: null
        def nombre = params.nombre ?: null

        def results = []
        if (descripcion != null || nombre != null)
            results = rubroService.advancedSearch(descripcion, nombre, params.sort, params.order, params.max, params.offset)
        else
            results = rubroService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [rubroInstanceList: itemsList, rubroInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [rubroInstance: new Rubro(params)]
                break
            case 'POST':
                def rubroInstance = new Rubro(params)
                if (!rubroInstance.save(flush: true)) {
                    render view: 'create', model: [rubroInstance: rubroInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'rubro.label', default: 'Rubro'), rubroInstance.id])
                redirect action: 'show', id: rubroInstance.id
                break
        }
    }

    def show() {
        def rubroInstance = Rubro.get(params.id)
        if (!rubroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rubro.label', default: 'Rubro'), params.id])
            redirect action: 'list'
            return
        }

        [rubroInstance: rubroInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def rubroInstance = Rubro.get(params.id)
                if (!rubroInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'rubro.label', default: 'Rubro'), params.id])
                    redirect action: 'list'
                    return
                }

                [rubroInstance: rubroInstance]
                break
            case 'POST':
                def rubroInstance = Rubro.get(params.id)
                if (!rubroInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'rubro.label', default: 'Rubro'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (rubroInstance.version > version) {
                        rubroInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'rubro.label', default: 'Rubro')] as Object[],
                                "Another user has updated this Rubro while you were editing")
                        render view: 'edit', model: [rubroInstance: rubroInstance]
                        return
                    }
                }

                rubroInstance.properties = params

                if (!rubroInstance.save(flush: true)) {
                    render view: 'edit', model: [rubroInstance: rubroInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'rubro.label', default: 'Rubro'), rubroInstance.id])
                redirect action: 'show', id: rubroInstance.id
                break
        }
    }

    def delete() {
        def rubroInstance = Rubro.get(params.id)
        if (!rubroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rubro.label', default: 'Rubro'), params.id])
            redirect action: 'list'
            return
        }

        try {
            rubroInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rubro.label', default: 'Rubro'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rubro.label', default: 'Rubro'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
