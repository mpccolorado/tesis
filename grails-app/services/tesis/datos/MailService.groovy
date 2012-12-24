package tesis.datos

class MailService {
    
    def search(search, sort, order, max, offset) {
        def results = Mail.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        return [total:results.getTotalCount(), results:results]
    }

    def advancedSearch(sort, order, max, offset) {
        def results = Mail.createCriteria().list(max: max, offset: offset, sort: sort, order: order)
        return [total:results.getTotalCount(), results:results]
    }
}
