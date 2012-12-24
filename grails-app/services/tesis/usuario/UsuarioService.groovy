package tesis.usuario

class UsuarioService {
    
    def search(search, sort, order, max, offset) {
        def results = Usuario.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("contrasenia", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(contrasenia, empleado, nombre, sesionActual, tipoUsuario, sort, order, max, offset) {
        def results = Usuario.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(contrasenia){
                    ilike("contrasenia", '%' + contrasenia + '%')
                }
                if(empleado){
                    eq("empleado", empleado)
                }
                if(nombre){
                    ilike("nombre", '%' + nombre + '%')
                }
                if(sesionActual){
                    eq("sesionActual", sesionActual)
                }
                if(tipoUsuario){
                    eq("tipoUsuario", tipoUsuario)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
