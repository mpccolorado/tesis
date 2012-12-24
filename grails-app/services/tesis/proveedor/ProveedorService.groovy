package tesis.proveedor

class ProveedorService {
    
    def search(search, sort, order, max, offset) {
        def results = Proveedor.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("apellido", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
                ilike("nombreFantasia", '%' + search + '%')
                ilike("numeroCuit", '%' + search + '%')
                ilike("razonSocial", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(apellido, condicionIVA, direccion, esPersonaFisica, nombre, nombreFantasia, numeroCuit, razonSocial, sort, order, max, offset) {
        def results = Proveedor.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
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
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
                if(nombreFantasia){
                    ilike("nombreFantasia", '%' + nombreFantasia + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
