package tesis.datos

class RestauranteService {
    
    def search(search, sort, order, max, offset) {
        def results = Restaurante.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("imagenLogo", '%' + search + '%')
                ilike("nombreFantasia", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(direccion, imagenLogo, nombreFantasia, sort, order, max, offset) {
        def results = Restaurante.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(direccion){
                    eq("direccion", direccion)
                }
                if(imagenLogo){
                    ilike("imagenLogo", '%' + imagenLogo + '%')
                }
                if(nombreFantasia){
                    ilike("nombreFantasia", '%' + nombreFantasia + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
