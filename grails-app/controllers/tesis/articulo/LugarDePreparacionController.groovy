package tesis.articulo


import org.springframework.dao.DataIntegrityViolationException

class LugarDePreparacionController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def lugarDePreparacionService

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
            results = lugarDePreparacionService.advancedSearch(descripcion, nombre, params.sort, params.order, params.max, params.offset)
        else
            results = lugarDePreparacionService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [lugarDePreparacionInstanceList: itemsList, lugarDePreparacionInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [lugarDePreparacionInstance: new LugarDePreparacion(params)]
                break
            case 'POST':
                def lugarDePreparacionInstance = new LugarDePreparacion(params)
                if (!lugarDePreparacionInstance.save(flush: true)) {
                    render view: 'create', model: [lugarDePreparacionInstance: lugarDePreparacionInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'lugarDePreparacion.label', default: 'LugarDePreparacion'), lugarDePreparacionInstance.id])
                redirect action: 'show', id: lugarDePreparacionInstance.id
                break
        }
    }

    def show() {
        def lugarDePreparacionInstance = LugarDePreparacion.get(params.id)
        if (!lugarDePreparacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lugarDePreparacion.label', default: 'LugarDePreparacion'), params.id])
            redirect action: 'list'
            return
        }

        [lugarDePreparacionInstance: lugarDePreparacionInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def lugarDePreparacionInstance = LugarDePreparacion.get(params.id)
                if (!lugarDePreparacionInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'lugarDePreparacion.label', default: 'LugarDePreparacion'), params.id])
                    redirect action: 'list'
                    return
                }

                [lugarDePreparacionInstance: lugarDePreparacionInstance]
                break
            case 'POST':
                def lugarDePreparacionInstance = LugarDePreparacion.get(params.id)
                if (!lugarDePreparacionInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'lugarDePreparacion.label', default: 'LugarDePreparacion'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (lugarDePreparacionInstance.version > version) {
                        lugarDePreparacionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'lugarDePreparacion.label', default: 'LugarDePreparacion')] as Object[],
                                "Another user has updated this LugarDePreparacion while you were editing")
                        render view: 'edit', model: [lugarDePreparacionInstance: lugarDePreparacionInstance]
                        return
                    }
                }

                lugarDePreparacionInstance.properties = params

                if (!lugarDePreparacionInstance.save(flush: true)) {
                    render view: 'edit', model: [lugarDePreparacionInstance: lugarDePreparacionInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'lugarDePreparacion.label', default: 'LugarDePreparacion'), lugarDePreparacionInstance.id])
                redirect action: 'show', id: lugarDePreparacionInstance.id
                break
        }
    }

    def delete() {
        def lugarDePreparacionInstance = LugarDePreparacion.get(params.id)
        if (!lugarDePreparacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lugarDePreparacion.label', default: 'LugarDePreparacion'), params.id])
            redirect action: 'list'
            return
        }

        try {
            lugarDePreparacionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'lugarDePreparacion.label', default: 'LugarDePreparacion'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lugarDePreparacion.label', default: 'LugarDePreparacion'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
