package tesis.articulo


import org.springframework.dao.DataIntegrityViolationException

class ArticuloElaboradoController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def articuloElaboradoService

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
        def lugarDePreparacion = params.lugarDePreparacion ? tesis.articulo.LugarDePreparacion.findById(params.lugarDePreparacion) : null
        def nombre = params.nombre ?: null
        def presentacion = params.presentacion ? tesis.articulo.Presentacion.findById(params.presentacion) : null

        def results = []
        if (descripcion != null || lugarDePreparacion != null || nombre != null || presentacion != null)
            results = articuloElaboradoService.advancedSearch(descripcion, lugarDePreparacion, nombre, presentacion, params.sort, params.order, params.max, params.offset)
        else
            results = articuloElaboradoService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [articuloElaboradoInstanceList: itemsList, articuloElaboradoInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [articuloElaboradoInstance: new ArticuloElaborado(params)]
                break
            case 'POST':
                def articuloElaboradoInstance = new ArticuloElaborado(params)
                if (!articuloElaboradoInstance.save(flush: true)) {
                    render view: 'create', model: [articuloElaboradoInstance: articuloElaboradoInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'articuloElaborado.label', default: 'ArticuloElaborado'), articuloElaboradoInstance.id])
                redirect action: 'show', id: articuloElaboradoInstance.id
                break
        }
    }

    def show() {
        def articuloElaboradoInstance = ArticuloElaborado.get(params.id)
        if (!articuloElaboradoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articuloElaborado.label', default: 'ArticuloElaborado'), params.id])
            redirect action: 'list'
            return
        }

        [articuloElaboradoInstance: articuloElaboradoInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def articuloElaboradoInstance = ArticuloElaborado.get(params.id)
                if (!articuloElaboradoInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'articuloElaborado.label', default: 'ArticuloElaborado'), params.id])
                    redirect action: 'list'
                    return
                }

                [articuloElaboradoInstance: articuloElaboradoInstance]
                break
            case 'POST':
                def articuloElaboradoInstance = ArticuloElaborado.get(params.id)
                if (!articuloElaboradoInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'articuloElaborado.label', default: 'ArticuloElaborado'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (articuloElaboradoInstance.version > version) {
                        articuloElaboradoInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'articuloElaborado.label', default: 'ArticuloElaborado')] as Object[],
                                "Another user has updated this ArticuloElaborado while you were editing")
                        render view: 'edit', model: [articuloElaboradoInstance: articuloElaboradoInstance]
                        return
                    }
                }

                articuloElaboradoInstance.properties = params

                if (!articuloElaboradoInstance.save(flush: true)) {
                    render view: 'edit', model: [articuloElaboradoInstance: articuloElaboradoInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'articuloElaborado.label', default: 'ArticuloElaborado'), articuloElaboradoInstance.id])
                redirect action: 'show', id: articuloElaboradoInstance.id
                break
        }
    }

    def delete() {
        def articuloElaboradoInstance = ArticuloElaborado.get(params.id)
        if (!articuloElaboradoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articuloElaborado.label', default: 'ArticuloElaborado'), params.id])
            redirect action: 'list'
            return
        }

        try {
            articuloElaboradoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'articuloElaborado.label', default: 'ArticuloElaborado'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'articuloElaborado.label', default: 'ArticuloElaborado'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
