package tesis.articulo

class UnidadDeMedidaService {
    
    def search(search, sort, order, max, offset) {
        def results = UnidadDeMedida.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("abreviacion", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(abreviacion, nombre, sort, order, max, offset) {
        def results = UnidadDeMedida.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(abreviacion){
                    ilike("abreviacion", '%' + abreviacion + '%')
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
