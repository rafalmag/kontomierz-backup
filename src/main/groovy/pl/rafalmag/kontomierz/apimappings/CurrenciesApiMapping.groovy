package pl.rafalmag.kontomierz.apimappings

import pl.rafalmag.kontomierz.importers.TagsImporter

import javax.inject.Inject

class CurrenciesApiMapping extends ApiMapping {
    @Inject
    CurrenciesApiMapping(TagsImporter importer) {
        super("currencies", "/k4/currencies.json", "currencies", importer, "id")
    }
}
