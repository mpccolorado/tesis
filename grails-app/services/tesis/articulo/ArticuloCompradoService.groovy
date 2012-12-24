package tesis.articulo

class ArticuloCompradoService {
    
    def search(search, sort, order, max, offset) {
        def results = ArticuloComprado.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("descripcion", '%' + search + '%')
                ilike("nombre", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(afectaStock, descripcion, lugarDePreparacion, marca, nombre, presentacion, seVende, subrubro, sort, order, max, offset) {
        def results = ArticuloComprado.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(afectaStock){
                    eq("afectaStock", afectaStock)
                }
                if(descripcion){
                ilike("descripcion", '%' + descripcion + '%')
                        }
                if(lugarDePreparacion){
                    eq("lugarDePreparacion", lugarDePreparacion)
                }
                if(marca){
                    eq("marca", marca)
                }
                if(nombre){
                ilike("nombre", '%' + nombre + '%')
                        }
                if(presentacion){
                    eq("presentacion", presentacion)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
