package tesis.pedido

class TipoDeDescuentoService {
    
    def search(search, sort, order, max, offset) {
        def results = TipoDeDescuento.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("descripcion", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
                eq("porcentaje", search)
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(descripcion, nombre, porcentaje, sort, order, max, offset) {
        def results = TipoDeDescuento.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(descripcion){
                    ilike("descripcion", '%' + descripcion + '%')
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
                if(porcentaje){
                    eq("porcentaje", porcentaje)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
