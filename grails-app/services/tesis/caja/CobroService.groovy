package tesis.caja

class CobroService {
    
    def search(search, sort, order, max, offset) {
        def results = Cobro.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(fecha, sort, order, max, offset) {
        def results = Cobro.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(fecha){
                    eq("fecha", fecha)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
