package tesis.articulo


import org.springframework.dao.DataIntegrityViolationException

class ArticuloCompradoController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def articuloCompradoService

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

        def afectaStock = params.afectaStock ? params.boolean('afectaStock') : null
        def descripcion = params.descripcion ?: null
        def lugarDePreparacion = params.lugarDePreparacion ? tesis.articulo.LugarDePreparacion.findById(params.lugarDePreparacion) : null
        def marca = params.marca ? tesis.articulo.Marca.findById(params.marca) : null
        def nombre = params.nombre ?: null
        def presentacion = params.presentacion ? tesis.articulo.Presentacion.findById(params.presentacion) : null

        def results = []
        if (afectaStock != null || descripcion != null || lugarDePreparacion != null || marca != null || nombre != null || presentacion != null)
            results = articuloCompradoService.advancedSearch(afectaStock, descripcion, lugarDePreparacion, marca, nombre, presentacion, params.sort, params.order, params.max, params.offset)
        else
            results = articuloCompradoService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [articuloCompradoInstanceList: itemsList, articuloCompradoInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [articuloCompradoInstance: new ArticuloComprado(params)]
                break
            case 'POST':
                def articuloCompradoInstance = new ArticuloComprado(params)
                if (!articuloCompradoInstance.save(flush: true)) {
                    render view: 'create', model: [articuloCompradoInstance: articuloCompradoInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'articuloComprado.label', default: 'ArticuloComprado'), articuloCompradoInstance.id])
                redirect action: 'show', id: articuloCompradoInstance.id
                break
        }
    }

    def show() {
        def articuloCompradoInstance = ArticuloComprado.get(params.id)
        if (!articuloCompradoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articuloComprado.label', default: 'ArticuloComprado'), params.id])
            redirect action: 'list'
            return
        }

        [articuloCompradoInstance: articuloCompradoInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def articuloCompradoInstance = ArticuloComprado.get(params.id)
                if (!articuloCompradoInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'articuloComprado.label', default: 'ArticuloComprado'), params.id])
                    redirect action: 'list'
                    return
                }

                [articuloCompradoInstance: articuloCompradoInstance]
                break
            case 'POST':
                def articuloCompradoInstance = ArticuloComprado.get(params.id)
                if (!articuloCompradoInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'articuloComprado.label', default: 'ArticuloComprado'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (articuloCompradoInstance.version > version) {
                        articuloCompradoInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'articuloComprado.label', default: 'ArticuloComprado')] as Object[],
                                "Another user has updated this ArticuloComprado while you were editing")
                        render view: 'edit', model: [articuloCompradoInstance: articuloCompradoInstance]
                        return
                    }
                }

                articuloCompradoInstance.properties = params

                if (!articuloCompradoInstance.save(flush: true)) {
                    render view: 'edit', model: [articuloCompradoInstance: articuloCompradoInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'articuloComprado.label', default: 'ArticuloComprado'), articuloCompradoInstance.id])
                redirect action: 'show', id: articuloCompradoInstance.id
                break
        }
    }

    def delete() {
        def articuloCompradoInstance = ArticuloComprado.get(params.id)
        if (!articuloCompradoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'articuloComprado.label', default: 'ArticuloComprado'), params.id])
            redirect action: 'list'
            return
        }

        try {
            articuloCompradoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'articuloComprado.label', default: 'ArticuloComprado'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'articuloComprado.label', default: 'ArticuloComprado'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
