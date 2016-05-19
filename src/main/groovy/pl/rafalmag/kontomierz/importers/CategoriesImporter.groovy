package pl.rafalmag.kontomierz.importers

import groovy.util.logging.Slf4j
import pl.rafalmag.kontomierz.apimappings.ApiMapping

@Slf4j
class CategoriesImporter extends Importer {

    String direction = ""

    CategoriesImporter(String direction) {
        this.direction = direction
    }

    @Override
    public List<Map> doImport(ApiMapping apiMapping) {
        def response = restClient.get(path: apiMapping.urlPath, query: [
                api_key  : apiKey,
                direction: direction,
                in_wallet: true
        ]);
        assert response.status == 200
        log.debug("CategoriesImporter response: {}", response.getData())
        log.debug("CategoriesImporter response.get: {}", response.getData().get("category_groups"))
        response.getData().get("category_groups")
    }
}
