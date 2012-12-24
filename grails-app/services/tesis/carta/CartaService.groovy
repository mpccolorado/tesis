package tesis.carta

class CartaService {
    
    def search(search, sort, order, max, offset) {
        def results = Carta.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("descripcion", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(descripcion, estaVigente, fechaDeCreacion, nombre, sort, order, max, offset) {
        def results = Carta.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(descripcion){
                    ilike("descripcion", '%' + descripcion + '%')
                }
                if(estaVigente){
                    eq("estaVigente", estaVigente)
                }
                if(fechaDeCreacion){
                    eq("fechaDeCreacion", fechaDeCreacion)
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
