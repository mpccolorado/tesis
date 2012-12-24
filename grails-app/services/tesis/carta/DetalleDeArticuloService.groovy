package tesis.carta

class DetalleDeArticuloService {
    
    def search(search, sort, order, max, offset) {
        def results = DetalleDeArticulo.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("imagen", '%' + search + '%')
                eq("precio", search)
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(articulo, imagen, orden, precio, sort, order, max, offset) {
        def results = DetalleDeArticulo.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(articulo){
                    eq("articulo", articulo)
                }
                if(imagen){
                    ilike("imagen", '%' + imagen + '%')
                }
                if(orden){
                    eq("orden", orden)
                }
                if(precio){
                    eq("precio", precio)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
