package tesis.pedido


import org.springframework.dao.DataIntegrityViolationException

class PedidoParaDeliveryController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
    def pedidoParaDeliveryService

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
        def direccion = params.direccion ? tesis.direccion.Direccion.findById(params.direccion) : null
        def empleado = params.empleado ? tesis.empleado.Empleado.findById(params.empleado) : null
        def estado = params.estado ?: null
        def fecha = params.fecha ? params.date('fecha') : null

        def results = []
        if (cliente != null || cobro != null || direccion != null || empleado != null || estado != null || fecha != null)
            results = pedidoParaDeliveryService.advancedSearch(cliente, cobro, direccion, empleado, estado, fecha, params.sort, params.order, params.max, params.offset)
        else
            results = pedidoParaDeliveryService.search(params.q, params.sort, params.order, params.max, params.offset)
        def total = results.total
        def itemsList = results.results

        [pedidoParaDeliveryInstanceList: itemsList, pedidoParaDeliveryInstanceTotal: total, params: params]
    }



    def create() {
        switch (request.method) {
            case 'GET':
                [pedidoParaDeliveryInstance: new PedidoParaDelivery(params)]
                break
            case 'POST':
                def pedidoParaDeliveryInstance = new PedidoParaDelivery(params)
                if (!pedidoParaDeliveryInstance.save(flush: true)) {
                    render view: 'create', model: [pedidoParaDeliveryInstance: pedidoParaDeliveryInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'pedidoParaDelivery.label', default: 'PedidoParaDelivery'), pedidoParaDeliveryInstance.id])
                redirect action: 'show', id: pedidoParaDeliveryInstance.id
                break
        }
    }

    def show() {
        def pedidoParaDeliveryInstance = PedidoParaDelivery.get(params.id)
        if (!pedidoParaDeliveryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedidoParaDelivery.label', default: 'PedidoParaDelivery'), params.id])
            redirect action: 'list'
            return
        }

        [pedidoParaDeliveryInstance: pedidoParaDeliveryInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def pedidoParaDeliveryInstance = PedidoParaDelivery.get(params.id)
                if (!pedidoParaDeliveryInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedidoParaDelivery.label', default: 'PedidoParaDelivery'), params.id])
                    redirect action: 'list'
                    return
                }

                [pedidoParaDeliveryInstance: pedidoParaDeliveryInstance]
                break
            case 'POST':
                def pedidoParaDeliveryInstance = PedidoParaDelivery.get(params.id)
                if (!pedidoParaDeliveryInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedidoParaDelivery.label', default: 'PedidoParaDelivery'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (pedidoParaDeliveryInstance.version > version) {
                        pedidoParaDeliveryInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'pedidoParaDelivery.label', default: 'PedidoParaDelivery')] as Object[],
                                "Another user has updated this PedidoParaDelivery while you were editing")
                        render view: 'edit', model: [pedidoParaDeliveryInstance: pedidoParaDeliveryInstance]
                        return
                    }
                }

                pedidoParaDeliveryInstance.properties = params

                if (!pedidoParaDeliveryInstance.save(flush: true)) {
                    render view: 'edit', model: [pedidoParaDeliveryInstance: pedidoParaDeliveryInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'pedidoParaDelivery.label', default: 'PedidoParaDelivery'), pedidoParaDeliveryInstance.id])
                redirect action: 'show', id: pedidoParaDeliveryInstance.id
                break
        }
    }

    def delete() {
        def pedidoParaDeliveryInstance = PedidoParaDelivery.get(params.id)
        if (!pedidoParaDeliveryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedidoParaDelivery.label', default: 'PedidoParaDelivery'), params.id])
            redirect action: 'list'
            return
        }

        try {
            pedidoParaDeliveryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pedidoParaDelivery.label', default: 'PedidoParaDelivery'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pedidoParaDelivery.label', default: 'PedidoParaDelivery'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
