package tesis.stock

class StockService {
    
    def search(search, sort, order, max, offset) {
        def results = Stock.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                eq("existenciaMax", search)
                eq("existenciaMin", search)
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(articuloComprado, existenciaMax, existenciaMin, sort, order, max, offset) {
        def results = Stock.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(articuloComprado){
                    eq("articuloComprado", articuloComprado)
                }
                if(existenciaMax){
                    eq("existenciaMax", existenciaMax)
                }
                if(existenciaMin){
                    eq("existenciaMin", existenciaMin)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
