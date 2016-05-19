package pl.rafalmag.kontomierz.apimappings

import pl.rafalmag.kontomierz.importers.MoneyTransactionImporter

import javax.inject.Inject


class MoneyTransactionApiMapping extends ApiMapping {

    @Inject
    MoneyTransactionApiMapping(MoneyTransactionImporter importer){
        super("money_transactions", "/k4/money_transactions.json", "money_transaction", importer, "id")
    }
}
