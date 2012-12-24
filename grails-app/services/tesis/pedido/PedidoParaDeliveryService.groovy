package tesis.pedido

class PedidoParaDeliveryService {
    
    def search(search, sort, order, max, offset) {
        def results = PedidoParaDelivery.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("observaciones", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(cliente, cobro, direccion, empleado, estado, fecha, observaciones, tipoDeDescuento, sort, order, max, offset) {
        def results = PedidoParaDelivery.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(cliente){
                    eq("cliente", cliente)
                }
                if(cobro){
                    eq("cobro", cobro)
                }
                if(direccion){
                    eq("direccion", direccion)
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
