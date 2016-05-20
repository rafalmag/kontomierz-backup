package pl.rafalmag.kontomierz.apimappings

import pl.rafalmag.kontomierz.importers.ObjectWithListImporter

import javax.inject.Inject

class CurrenciesApiMapping extends ApiMapping {
    @Inject
    CurrenciesApiMapping(ObjectWithListImporter importer) {
        super("currencies", "/k4/currencies.json", "currencies", importer, "id")
    }
}
