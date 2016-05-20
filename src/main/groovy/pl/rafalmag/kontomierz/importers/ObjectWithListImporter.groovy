package pl.rafalmag.kontomierz.importers

import groovy.util.logging.Slf4j
import pl.rafalmag.kontomierz.apimappings.ApiMapping

@Slf4j
class ObjectWithListImporter extends ListWithObjectsImporter {

    @Override
    public List<Map> doImport(ApiMapping apiMapping) {
        def json = getResponse(apiMapping)
        json.get(apiMapping.getObjectName())
    }
}
