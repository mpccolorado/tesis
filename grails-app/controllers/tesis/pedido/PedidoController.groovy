package tesis.pedido


import org.springframework.dao.DataIntegrityViolationException

class PedidoController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def pedidoService

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

        def cliente = params.cliente ? tesis.cliente.Cliente.findById(params.cliente) : null
        def cobro = params.cobro ? tesis.caja.Cobro.findById(params.cobro) : null
        def empleado = params.empleado ? tesis.empleado.Empleado.findById(params.empleado) : null
        def fecha = params.fecha ? params.date('fecha') : null
        def observaciones = params.observaciones ?: null
        def tipoDeDescuento = params.tipoDeDescuento ? tesis.pedido.TipoDeDescuento.findById(params.tipoDeDescuento) : null

        def results = []
        if (cliente != null || cobro != null || empleado != null || fecha != null || observaciones != null || tipoDeDescuento != null)
            results = pedidoService.advancedSearch(cliente, cobro, empleado, fecha, observaciones, tipoDeDescuento, params.sort, params.order, params.max, params.offset)
        else
            results = pedidoService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [pedidoInstanceList: itemsList, pedidoInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [pedidoInstance: new Pedido(params)]
                break
            case 'POST':
                def pedidoInstance = new Pedido(params)
                if (!pedidoInstance.save(flush: true)) {
                    render view: 'create', model: [pedidoInstance: pedidoInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'pedido.label', default: 'Pedido'), pedidoInstance.id])
                redirect action: 'show', id: pedidoInstance.id
                break
        }
    }

    def show() {
        def pedidoInstance = Pedido.get(params.id)
        if (!pedidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedido.label', default: 'Pedido'), params.id])
            redirect action: 'list'
            return
        }

        [pedidoInstance: pedidoInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def pedidoInstance = Pedido.get(params.id)
                if (!pedidoInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedido.label', default: 'Pedido'), params.id])
                    redirect action: 'list'
                    return
                }

                [pedidoInstance: pedidoInstance]
                break
            case 'POST':
                def pedidoInstance = Pedido.get(params.id)
                if (!pedidoInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedido.label', default: 'Pedido'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (pedidoInstance.version > version) {
                        pedidoInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'pedido.label', default: 'Pedido')] as Object[],
                                "Another user has updated this Pedido while you were editing")
                        render view: 'edit', model: [pedidoInstance: pedidoInstance]
                        return
                    }
                }

                pedidoInstance.properties = params

                if (!pedidoInstance.save(flush: true)) {
                    render view: 'edit', model: [pedidoInstance: pedidoInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'pedido.label', default: 'Pedido'), pedidoInstance.id])
                redirect action: 'show', id: pedidoInstance.id
                break
        }
    }

    def delete() {
        def pedidoInstance = Pedido.get(params.id)
        if (!pedidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedido.label', default: 'Pedido'), params.id])
            redirect action: 'list'
            return
        }

        try {
            pedidoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pedido.label', default: 'Pedido'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pedido.label', default: 'Pedido'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
