package pl.rafalmag.kontomierz.importers

import pl.rafalmag.kontomierz.apimappings.ApiMapping

interface Importer {
    List<Map> doImport(ApiMapping apiMapping)
}