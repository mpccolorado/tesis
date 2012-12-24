package tesis.articulo


import org.springframework.dao.DataIntegrityViolationException

class SubrubroController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def subrubroService

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
        def rubro = params.rubro ? tesis.articulo.Rubro.findById(params.rubro) : null

        def results = []
        if (descripcion != null || nombre != null || rubro != null)
            results = subrubroService.advancedSearch(descripcion, nombre, rubro, params.sort, params.order, params.max, params.offset)
        else
            results = subrubroService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [subrubroInstanceList: itemsList, subrubroInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [subrubroInstance: new Subrubro(params)]
                break
            case 'POST':
                def subrubroInstance = new Subrubro(params)
                if (!subrubroInstance.save(flush: true)) {
                    render view: 'create', model: [subrubroInstance: subrubroInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'subrubro.label', default: 'Subrubro'), subrubroInstance.id])
                redirect action: 'show', id: subrubroInstance.id
                break
        }
    }

    def show() {
        def subrubroInstance = Subrubro.get(params.id)
        if (!subrubroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subrubro.label', default: 'Subrubro'), params.id])
            redirect action: 'list'
            return
        }

        [subrubroInstance: subrubroInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def subrubroInstance = Subrubro.get(params.id)
                if (!subrubroInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'subrubro.label', default: 'Subrubro'), params.id])
                    redirect action: 'list'
                    return
                }

                [subrubroInstance: subrubroInstance]
                break
            case 'POST':
                def subrubroInstance = Subrubro.get(params.id)
                if (!subrubroInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'subrubro.label', default: 'Subrubro'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (subrubroInstance.version > version) {
                        subrubroInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'subrubro.label', default: 'Subrubro')] as Object[],
                                "Another user has updated this Subrubro while you were editing")
                        render view: 'edit', model: [subrubroInstance: subrubroInstance]
                        return
                    }
                }

                subrubroInstance.properties = params

                if (!subrubroInstance.save(flush: true)) {
                    render view: 'edit', model: [subrubroInstance: subrubroInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'subrubro.label', default: 'Subrubro'), subrubroInstance.id])
                redirect action: 'show', id: subrubroInstance.id
                break
        }
    }

    def delete() {
        def subrubroInstance = Subrubro.get(params.id)
        if (!subrubroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subrubro.label', default: 'Subrubro'), params.id])
            redirect action: 'list'
            return
        }

        try {
            subrubroInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'subrubro.label', default: 'Subrubro'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'subrubro.label', default: 'Subrubro'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
