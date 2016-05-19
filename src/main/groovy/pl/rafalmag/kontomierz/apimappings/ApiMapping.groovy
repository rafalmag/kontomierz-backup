package pl.rafalmag.kontomierz.apimappings

import groovy.transform.Canonical
import pl.rafalmag.kontomierz.importers.Importer

@Canonical
class ApiMapping {
    String collectionName
    String urlPath
    String objectName
    Importer importer
    String id
}
