package pl.rafalmag.kontomierz.importers

import groovy.util.logging.Slf4j
import groovyx.net.http.RESTClient
import pl.rafalmag.kontomierz.ApiKey
import pl.rafalmag.kontomierz.ImportFrom
import pl.rafalmag.kontomierz.apimappings.ApiMapping

import javax.inject.Inject

import static pl.rafalmag.kontomierz.Looper.loop

@Slf4j
class MoneyTransactionImporter implements Importer {

    @Inject
    RESTClient restClient

    @ApiKey
    @Inject
    String apiKey

    @ImportFrom
    @Inject
    String importFrom

    @Override
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
                per_page: 100,
                start_on: importFrom

        ])
        assert response.status == 200
        log.debug("Received response for $apiMapping.collectionName page $page: {}", response.getData())
        def json = response.getData()
        json.collect { it.get(apiMapping.getObjectName()) }
    }
}
