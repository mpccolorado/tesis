package tesis.persona

class PersonaService {
    
    def search(search, sort, order, max, offset) {
        def results = Persona.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("apellido", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(apellido, direccion, nombre, sort, order, max, offset) {
        def results = Persona.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(apellido){
                    ilike("apellido", '%' + apellido + '%')
                }
                if(direccion){
                    eq("direccion", direccion)
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
