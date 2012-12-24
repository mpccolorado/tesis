package tesis.articulo

class SubrubroService {
    
    def search(search, sort, order, max, offset) {
        def results = Subrubro.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("descripcion", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(descripcion, nombre, rubro, sort, order, max, offset) {
        def results = Subrubro.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(descripcion){
                    ilike("descripcion", '%' + descripcion + '%')
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
                if(rubro){
                    eq("rubro", rubro)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
