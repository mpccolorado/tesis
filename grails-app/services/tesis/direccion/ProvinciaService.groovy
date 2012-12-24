package tesis.direccion

class ProvinciaService {
    
    def search(search, sort, order, max, offset) {
        def results = Provincia.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(nombre, pais, sort, order, max, offset) {
        def results = Provincia.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
                if(pais){
                    eq("pais", pais)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
