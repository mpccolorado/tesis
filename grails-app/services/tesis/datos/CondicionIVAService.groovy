package tesis.datos

class CondicionIVAService {
    
    def search(search, sort, order, max, offset) {
        def results = CondicionIVA.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                eq("alicuota", search)
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(alicuota, nombre, sort, order, max, offset) {
        def results = CondicionIVA.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(alicuota){
                    eq("alicuota", alicuota)
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
