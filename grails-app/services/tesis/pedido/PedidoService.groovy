package tesis.pedido

class PedidoService {
    
    def search(search, sort, order, max, offset) {
        def results = Pedido.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("observaciones", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(cliente, cobro, empleado, fecha, observaciones, tipoDeDescuento, sort, order, max, offset) {
        def results = Pedido.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(cliente){
                    eq("cliente", cliente)
                }
                if(cobro){
                    eq("cobro", cobro)
                }
                if(empleado){
                    eq("empleado", empleado)
                }
                if(fecha){
                    eq("fecha", fecha)
                }
                if(observaciones){
                ilike("observaciones", '%' + observaciones + '%')
                        }
                if(tipoDeDescuento){
                    eq("tipoDeDescuento", tipoDeDescuento)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
