package tesis.datos

class MailService {
    
    def search(search, sort, order, max, offset) {
        def results = Mail.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            or{
                ilike("direccion", '%' + search + '%')
            }
        }
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(direccion, sort, order, max, offset) {
        def results = Mail.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        {
            and{
                if(direccion){
                    ilike("direccion", '%' + direccion + '%')
                }
            }
        }
        return [total:results.getTotalCount(), results:results]
    }
}
