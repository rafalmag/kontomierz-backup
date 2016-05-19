package pl.rafalmag.kontomierz.importers

class WealthPointsImporter extends Importer {

    @Override
    Map<String, String> getQuery() {
        [api_key: apiKey, start_on: "01-01-2010"]
    }
}
