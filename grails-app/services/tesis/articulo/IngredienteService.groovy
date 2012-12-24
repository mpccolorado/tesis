package tesis.articulo

class IngredienteService {
    
    def search(search, sort, order, max, offset) {
        def results = Ingrediente.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                eq("cantidad", search)
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(articulo, cantidad, sort, order, max, offset) {
        def results = Ingrediente.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(articulo){
                    eq("articulo", articulo)
                }
                if(cantidad){
                    eq("cantidad", cantidad)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
