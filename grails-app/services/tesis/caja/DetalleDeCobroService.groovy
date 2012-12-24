package tesis.caja

class DetalleDeCobroService {
    
    def search(search, sort, order, max, offset) {
        def results = DetalleDeCobro.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                eq("cambio", search)
                eq("monto", search)
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(cambio, fecha, metodo, monto, sort, order, max, offset) {
        def results = DetalleDeCobro.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(cambio){
                    eq("cambio", cambio)
                }
                if(fecha){
                    eq("fecha", fecha)
                }
                if(metodo){
                    eq("metodo", metodo)
                }
                if(monto){
                    eq("monto", monto)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
