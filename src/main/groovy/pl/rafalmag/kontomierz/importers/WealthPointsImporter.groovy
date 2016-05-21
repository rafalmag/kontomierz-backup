package pl.rafalmag.kontomierz.importers

import pl.rafalmag.kontomierz.ImportFrom

import javax.inject.Inject

class WealthPointsImporter extends ListWithObjectsImporter {

    @Inject
    @ImportFrom
    String importFrom

    @Override
    Map<String, String> getQuery() {
        [api_key: apiKey, start_on: importFrom]
    }
}
