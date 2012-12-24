package tesis.direccion

class PaisService {
    
    def search(search, sort, order, max, offset) {
        def results = Pais.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(nombre, sort, order, max, offset) {
        def results = Pais.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
