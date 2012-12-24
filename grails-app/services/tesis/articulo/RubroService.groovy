package tesis.articulo

class RubroService {
    
    def search(search, sort, order, max, offset) {
        def results = Rubro.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("descripcion", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(descripcion, nombre, sort, order, max, offset) {
        def results = Rubro.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(descripcion){
                    ilike("descripcion", '%' + descripcion + '%')
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
