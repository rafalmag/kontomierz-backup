package pl.rafalmag.kontomierz.importers

import groovy.util.logging.Slf4j
import groovyx.net.http.RESTClient
import pl.rafalmag.kontomierz.ApiKey
import pl.rafalmag.kontomierz.apimappings.ApiMapping

import javax.inject.Inject

@Slf4j
class CategoriesImporter implements Importer {

    @Inject
    RESTClient restClient

    @ApiKey
    @Inject
    String apiKey

    final String direction

    CategoriesImporter(String direction) {
        this.direction = direction
    }

    @Override
    public List<Map> doImport(ApiMapping apiMapping) {
        def response = restClient.get(path: apiMapping.urlPath, query: [
                api_key  : apiKey,
                direction: direction,
                in_wallet: true
        ])
        assert response.status == 200
        log.debug("Received response for $apiMapping.collectionName: {}", response.getData())
        response.getData().get("category_groups")
    }
}
