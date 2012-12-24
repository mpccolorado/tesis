package tesis.caja

class CajaService {
    
    def search(search, sort, order, max, offset) {
        def results = Caja.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("descripcion", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(cierreActual, descripcion, sort, order, max, offset) {
        def results = Caja.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(cierreActual){
                    eq("cierreActual", cierreActual)
                }
                if(descripcion){
                    ilike("descripcion", '%' + descripcion + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
