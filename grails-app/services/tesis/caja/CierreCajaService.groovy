package tesis.caja

class CierreCajaService {
    
    def search(search, sort, order, max, offset) {
        def results = CierreCaja.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                eq("montoCierre", search)
                eq("montoInicial", search)
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(caja, fecha, montoCierre, montoInicial, responsable, sort, order, max, offset) {
        def results = CierreCaja.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(caja){
                    eq("caja", caja)
                }
                if(fecha){
                    eq("fecha", fecha)
                }
                if(montoCierre){
                    eq("montoCierre", montoCierre)
                }
                if(montoInicial){
                    eq("montoInicial", montoInicial)
                }
                if(responsable){
                    eq("responsable", responsable)
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
