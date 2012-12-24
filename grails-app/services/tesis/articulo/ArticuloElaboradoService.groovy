package tesis.articulo

class ArticuloElaboradoService {
    
    def search(search, sort, order, max, offset) {
        def results = ArticuloElaborado.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("descripcion", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(descripcion, lugarDePreparacion, nombre, presentacion, sort, order, max, offset) {
        def results = ArticuloElaborado.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(descripcion){
                ilike("descripcion", '%' + descripcion + '%')
                        }
                if(lugarDePreparacion){
                    eq("lugarDePreparacion", lugarDePreparacion)
                }
                if(nombre){
                ilike("nombre", '%' + nombre + '%')
                        }
                if(presentacion){
                    eq("presentacion", presentacion)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
