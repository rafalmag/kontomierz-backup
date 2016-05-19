package pl.rafalmag.kontomierz.importers

import groovy.util.logging.Slf4j
import groovyx.net.http.RESTClient
import pl.rafalmag.kontomierz.apimappings.ApiMapping

import javax.inject.Inject
import javax.inject.Named

@Slf4j
class Importer {

    @Inject
    RESTClient restClient;

    @Named("apiKey")
    @Inject
    String apiKey;

    public List<Map> doImport(ApiMapping apiMapping) {
        def json = getResponse(apiMapping)
        json.collect { it.get(apiMapping.getObjectName()) }
    }

    def getResponse(ApiMapping apiMapping) {
        def response = restClient.get(path: apiMapping.urlPath, query: getQuery());
        assert response.status == 200
        def json = response.getData()
        log.debug("Received response for $apiMapping.collectionName: {}", json)
        json
    }

    Map<String, String> getQuery() {
        [api_key: apiKey]
    }
}
