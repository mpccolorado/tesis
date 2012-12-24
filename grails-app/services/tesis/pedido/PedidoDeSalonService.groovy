package tesis.pedido

class PedidoDeSalonService {
    
    def search(search, sort, order, max, offset) {
        def results = PedidoDeSalon.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("observaciones", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(cantidadDePersonas, cliente, cobro, empleado, estado, fecha, mesa, observaciones, tipoDeDescuento, sort, order, max, offset) {
        def results = PedidoDeSalon.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(cantidadDePersonas){
                    eq("cantidadDePersonas", cantidadDePersonas)
                }
                if(cliente){
                    eq("cliente", cliente)
                }
                if(cobro){
                    eq("cobro", cobro)
                }
                if(empleado){
                    eq("empleado", empleado)
                }
                if(estado){
                    eq("estado", estado)
                }
                if(fecha){
                    eq("fecha", fecha)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
