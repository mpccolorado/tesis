package tesis.empleado

class EmpleadoService {
    
    def search(search, sort, order, max, offset) {
        def results = Empleado.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("apellido", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
                ilike("numeroCuil", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(apellido, cargo, direccion, fechaNacimiento, nombre, numeroCuil, sort, order, max, offset) {
        def results = Empleado.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(apellido){
                    ilike("apellido", '%' + apellido + '%')
                }
                if(cargo){
                    eq("cargo", cargo)
                }
                if(direccion){
                    eq("direccion", direccion)
                }
                if(fechaNacimiento){
                    eq("fechaNacimiento", fechaNacimiento)
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
                if(numeroCuil){
                    ilike("numeroCuil", '%' + numeroCuil + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
