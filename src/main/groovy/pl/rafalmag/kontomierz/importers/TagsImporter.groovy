package pl.rafalmag.kontomierz.importers

import groovy.util.logging.Slf4j
import pl.rafalmag.kontomierz.apimappings.ApiMapping

@Slf4j
class TagsImporter extends Importer {

    public List<Map> doImport(ApiMapping apiMapping) {
        def json = getResponse(apiMapping)
        json.get(apiMapping.getObjectName())
    }
}
