package tesis.direccion

class DireccionService {
    
    def search(search, sort, order, max, offset) {
        def results = Direccion.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("calle", '%' + search + '%')
                eq("numero", search)
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(calle, localidad, numero, sort, order, max, offset) {
        def results = Direccion.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(calle){
                    ilike("calle", '%' + calle + '%')
                }
                if(localidad){
                    eq("localidad", localidad)
                }
                if(numero){
                    eq("numero", numero)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
