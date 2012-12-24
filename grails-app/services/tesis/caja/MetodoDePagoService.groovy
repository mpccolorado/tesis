package tesis.caja

class MetodoDePagoService {
    
    def search(search, sort, order, max, offset) {
        def results = MetodoDePago.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(nombre, seDaCambio, sort, order, max, offset) {
        def results = MetodoDePago.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
                if(seDaCambio){
                    eq("seDaCambio", seDaCambio)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
