package tesis.pedido

class MesaService {
    
    def search(search, sort, order, max, offset) {
        def results = Mesa.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("descripcion", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(descripcion, estado, sort, order, max, offset) {
        def results = Mesa.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(descripcion){
                    ilike("descripcion", '%' + descripcion + '%')
                }
                if(estado){
                    eq("estado", estado)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
