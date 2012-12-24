package tesis.usuario

class PermisoService {
    
    def search(search, sort, order, max, offset) {
        def results = Permiso.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(puedeAgregar, puedeConsultar, puedeEliminar, puedeFiltrar, puedeImprimir, puedeModificar, sort, order, max, offset) {
        def results = Permiso.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(puedeAgregar){
                    eq("puedeAgregar", puedeAgregar)
                }
                if(puedeConsultar){
                    eq("puedeConsultar", puedeConsultar)
                }
                if(puedeEliminar){
                    eq("puedeEliminar", puedeEliminar)
                }
                if(puedeFiltrar){
                    eq("puedeFiltrar", puedeFiltrar)
                }
                if(puedeImprimir){
                    eq("puedeImprimir", puedeImprimir)
                }
                if(puedeModificar){
                    eq("puedeModificar", puedeModificar)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
