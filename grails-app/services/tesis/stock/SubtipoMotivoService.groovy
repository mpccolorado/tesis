package tesis.stock

class SubtipoMotivoService {
    
    def search(search, sort, order, max, offset) {
        def results = SubtipoMotivo.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("descripcion", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(descripcion, nombre, tipoMotivo, sort, order, max, offset) {
        def results = SubtipoMotivo.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(descripcion){
                    ilike("descripcion", '%' + descripcion + '%')
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
                if(tipoMotivo){
                    eq("tipoMotivo", tipoMotivo)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
