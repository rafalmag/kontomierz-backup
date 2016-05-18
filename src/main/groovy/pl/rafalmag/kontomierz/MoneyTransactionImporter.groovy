package pl.rafalmag.kontomierz

import groovy.util.logging.Slf4j
import pl.rafalmag.kontomierz.apimappings.ApiMapping

import static pl.rafalmag.kontomierz.Looper.loop

@Slf4j
class MoneyTransactionImporter extends Importer {

    public List<Map> doImport(ApiMapping apiMapping) {
        int page = 1
        def result = []
        List<Map> pageResult = []
        loop {
            pageResult = importPerPage(apiMapping, page)
            result.addAll(pageResult)
            page++
        } until { pageResult.empty }
        result
    }

    private List<Map> importPerPage(ApiMapping apiMapping, int page) {
        def response = restClient.get(path: apiMapping.getUrlPath(), query: [
                api_key : apiKey,
                page    : page,
                per_page: 100
        ]);
        assert response.status == 200
        log.debug("Result for page $page: {}",response.getData())
        response.getData()
    }
}
