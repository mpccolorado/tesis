package tesis.direccion

class LocalidadService {
    
    def search(search, sort, order, max, offset) {
        def results = Localidad.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(nombre, provincia, sort, order, max, offset) {
        def results = Localidad.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
                if(provincia){
                    eq("provincia", provincia)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
