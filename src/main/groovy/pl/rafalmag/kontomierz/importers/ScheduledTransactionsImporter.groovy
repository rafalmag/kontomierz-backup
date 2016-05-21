package pl.rafalmag.kontomierz.importers

import pl.rafalmag.kontomierz.ImportFrom

import javax.inject.Inject

//TODO remove this class
abstract class ScheduledTransactionsImporter extends ObjectWithListImporter {

    @Inject
    @ImportFrom
    String importFrom

}
