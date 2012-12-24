package tesis.cliente

class ClienteService {
    
    def search(search, sort, order, max, offset) {
        def results = Cliente.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("apellido", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
                ilike("numeroDeCuit", '%' + search + '%')
                ilike("razonSocial", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(apellido, condicionIVA, direccion, esPersonaFisica, fechaNacimiento, nombre, numeroDeCuit, razonSocial, sort, order, max, offset) {
        def results = Cliente.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(apellido){
                    ilike("apellido", '%' + apellido + '%')
                }
                if(condicionIVA){
                    eq("condicionIVA", condicionIVA)
                }
                if(direccion){
                    eq("direccion", direccion)
                }
                if(esPersonaFisica){
                    eq("esPersonaFisica", esPersonaFisica)
                }
                if(fechaNacimiento){
                    eq("fechaNacimiento", fechaNacimiento)
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
