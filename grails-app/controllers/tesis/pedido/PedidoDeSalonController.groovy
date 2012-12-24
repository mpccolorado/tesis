package tesis.pedido


import org.springframework.dao.DataIntegrityViolationException
class PedidoDeSalonController {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: ['GET', 'POST']]
	def pedidoDeSalonService
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
	
	private def getTableModel(){
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.q = params.search?.trim()
		params.q = params.q?:""
		params.offset = params.int('offset')?:0

        def cantidadDePersonas = params.cantidadDePersonas ?: null
        def cliente = params.cliente ? tesis.cliente.Cliente.findById(params.cliente) : null
        def cobro = params.cobro ? tesis.caja.Cobro.findById(params.cobro) : null
        def empleado = params.empleado ? tesis.empleado.Empleado.findById(params.empleado) : null
        def estado = params.estado ?: null
        def fecha = params.fecha ? params.date('fecha') : null
        
        def results = []
        if(cantidadDePersonas != null || cliente != null || cobro != null || empleado != null || estado != null || fecha != null)
            results = pedidoDeSalonService.advancedSearch(cantidadDePersonas, cliente, cobro, empleado, estado, fecha, params.sort, params.order, params.max, params.offset)
        else
            results = pedidoDeSalonService.search(params.q, params.sort, params.order, params.max, params.offset)
		def total = results.total
		def itemsList = results.results
		
		[pedidoDeSalonInstanceList: itemsList, pedidoDeSalonInstanceTotal: total, params: params]
	}

    

    def create() {
		switch (request.method) {
		case 'GET':
        	[pedidoDeSalonInstance: new PedidoDeSalon(params)]
			break
		case 'POST':
	        def pedidoDeSalonInstance = new PedidoDeSalon(params)
	        if (!pedidoDeSalonInstance.save(flush: true)) {
	            render view: 'create', model: [pedidoDeSalonInstance: pedidoDeSalonInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'pedidoDeSalon.label', default: 'PedidoDeSalon'), pedidoDeSalonInstance.id])
	        redirect action: 'show', id: pedidoDeSalonInstance.id
			break
		}
    }

    def show() {
        def pedidoDeSalonInstance = PedidoDeSalon.get(params.id)
        if (!pedidoDeSalonInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedidoDeSalon.label', default: 'PedidoDeSalon'), params.id])
            redirect action: 'list'
            return
        }

        [pedidoDeSalonInstance: pedidoDeSalonInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def pedidoDeSalonInstance = PedidoDeSalon.get(params.id)
	        if (!pedidoDeSalonInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedidoDeSalon.label', default: 'PedidoDeSalon'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [pedidoDeSalonInstance: pedidoDeSalonInstance]
			break
		case 'POST':
	        def pedidoDeSalonInstance = PedidoDeSalon.get(params.id)
	        if (!pedidoDeSalonInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedidoDeSalon.label', default: 'PedidoDeSalon'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (pedidoDeSalonInstance.version > version) {
	                pedidoDeSalonInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'pedidoDeSalon.label', default: 'PedidoDeSalon')] as Object[],
	                          "Another user has updated this PedidoDeSalon while you were editing")
	                render view: 'edit', model: [pedidoDeSalonInstance: pedidoDeSalonInstance]
	                return
	            }
	        }

	        pedidoDeSalonInstance.properties = params

	        if (!pedidoDeSalonInstance.save(flush: true)) {
	            render view: 'edit', model: [pedidoDeSalonInstance: pedidoDeSalonInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'pedidoDeSalon.label', default: 'PedidoDeSalon'), pedidoDeSalonInstance.id])
	        redirect action: 'show', id: pedidoDeSalonInstance.id
			break
		}
    }

    def delete() {
        def pedidoDeSalonInstance = PedidoDeSalon.get(params.id)
        if (!pedidoDeSalonInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedidoDeSalon.label', default: 'PedidoDeSalon'), params.id])
            redirect action: 'list'
            return
        }

        try {
            pedidoDeSalonInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'pedidoDeSalon.label', default: 'PedidoDeSalon'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pedidoDeSalon.label', default: 'PedidoDeSalon'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
