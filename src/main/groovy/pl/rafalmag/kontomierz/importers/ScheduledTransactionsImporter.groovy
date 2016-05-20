package pl.rafalmag.kontomierz.importers

import pl.rafalmag.kontomierz.apimappings.ApiMapping

abstract class ScheduledTransactionsImporter extends Importer {

    public static final String START_ON = "01-01-2013"

    public List<Map> doImport(ApiMapping apiMapping) {
        def json = getResponse(apiMapping)
        json.get(apiMapping.getObjectName())
    }


}
