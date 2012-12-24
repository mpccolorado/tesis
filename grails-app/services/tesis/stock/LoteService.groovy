package tesis.stock

class LoteService {
    
    def search(search, sort, order, max, offset) {
        def results = Lote.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                eq("cantidad", search)
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(cantidad, fechaVencimiento, nombre, stock, sort, order, max, offset) {
        def results = Lote.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(cantidad){
                    eq("cantidad", cantidad)
                }
                if(fechaVencimiento){
                    eq("fechaVencimiento", fechaVencimiento)
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
                if(stock){
                    eq("stock", stock)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
